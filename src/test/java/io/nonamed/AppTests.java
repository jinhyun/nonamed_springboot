package io.nonamed;

import io.nonamed.domain.department.Department;
import io.nonamed.domain.organization.Organization;
import io.nonamed.domain.user.User;
import io.nonamed.service.organization.OrganizationService;
import io.nonamed.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.junit.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import java.util.List;
import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class AppTests {
    RestTemplate template = new TestRestTemplate();
    String indexUrl = "http://localhost:8080";

    @Test
    public void testRequest() throws Exception {
        assertThat(template.getForObject(indexUrl, String.class),
                containsString("Nonamed World"));
    }

    /*
    Feature List
        []organization, 조직도를 조회한다(부서별 사용자)
            * 유연성, 비종속적
                * 부서, 겸직부서, 사용자의 이동 및 삭제에 주의
            * 도메인
                * 부서
                    * 부서코드/명: 최상위 부서인경우 root
                    * 상위부서코드/명: 없는 경우 root
                * 사용자
                    * 부서코드/명
                        * 없는경우?
                    * 겸직부서코드/명
                    * 사용자 아이디(email)
                    * 사용자명
            * 조회
                * Condition: 부서.상위부서코드 + 부서.부서코드
                * Relation: User + Department
                * 현재단계와 한단계 하위까지의 결과를 조회한다.
            ex)트리 구조
                |Nonamed Company, root, null
                    |-CEO, root
                    |경영지원, d0001, root
                    |개발본부, d0002, root
                        |-개발본부장, d0002
                        |개발1팀, d0003, d0002
                            |-1팀장, d0003
                            |-1팀고급개발자, d0003
                        |개발2팀, d0004, d0002
                            |-2팀장, d0004
                        |개발3팀, d0005, d0002
                    |운영본부, d0006, root
     */
    @Autowired
    UserService userService;
    @Autowired
    OrganizationService organizationService;

    @Test
    public void testGetOrganizationDept() {
        String deptCode = "root";
        Organization organization = organizationService.getOrganizationDept(deptCode);

        // 조회하는 부서정보
        assertThat("Nonamed Company",
                is(organization.getDepartment().getDeptName()));

        // 하위 사용자정보
        List <User> userList = organization.getUserList();
        assertThat("CEO", is(userList.get(0).getName()));
        assertThat("ceo@nonamed.io", is(userList.get(0).getEmail()));
        assertThat("root", is(userList.get(0).getDeptCode()));

        // 하위 부서정보
        List <Department> departmentList = organization.getDepartmentList();
        assertThat("경영지원", is(departmentList.get(0).getDeptName()));
        assertThat("d0001", is(departmentList.get(0).getDeptCode()));
        assertThat("root", is(departmentList.get(0).getUpDeptCode()));

        assertThat("개발본부", is(departmentList.get(1).getDeptName()));
        assertThat("d0002", is(departmentList.get(1).getDeptCode()));
        assertThat("root", is(departmentList.get(1).getUpDeptCode()));
    }

    @Test
    public void testGetOrganization_선택한부서에서_최상위부서까지조회() {
        String deptCode = "d0004";
        Organization organization =
                organizationService.getOrganizationToRoot(deptCode);

//        List<Department> departmentList = organization.getDepartmentList();
//        assertThat("Nonamed Company", is(departmentList.get(0).getDeptName()));
//        assertThat("개발본부", is(departmentList.get(1).getDeptName()));
//        assertThat("개발1팀", is(departmentList.get(2).getDeptName()));
//        assertThat("개발2팀", is(departmentList.get(3).getDeptName()));

//        List<User> userList = organization.getUserList();
//        assertThat("1팀장", not(userList.get(2).getName()));
    }
}