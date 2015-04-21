package io.nonamed;

import io.nonamed.dao.department.DeptRepository;
import io.nonamed.dao.organization.OrganRepository;
import io.nonamed.dao.user.UserRepository;
import io.nonamed.domain.department.Dept;
import io.nonamed.domain.organization.Organ;
import io.nonamed.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.junit.*;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class AppTests {
    @Autowired
    DeptRepository deptRepository;
    @Autowired
    OrganRepository organRepository;
    @Autowired
    UserRepository userRepository;

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
            * 겸직부서
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

    @Ignore
    @Test
    public void insertDept() {
        Dept deptData = new Dept();
        String deptCode = "d1000";
        String deptName = "Nonamed Company";
        String upDeptCodes = "d1000";
        deptData.setDeptCode(deptCode);
        deptData.setDeptName(deptName);
        deptData.setDeptLocation(upDeptCodes);

        deptRepository.save(deptData);

        Dept dbDept = deptRepository.findOne(deptCode);
        assertNotNull(dbDept);
        assertNotNull(dbDept.getDeptCode());
        assertThat(deptCode, is(dbDept.getDeptCode()));
    }

    @Ignore
    @Test
    public void insertUser() {
        User userData = new User();
        String userId = "ceo";
        String userName = "노미정";
        userData.setUserId(userId);
        userData.setUserName(userName);

        userRepository.save(userData);

        User dbUser = userRepository.findOne(userId);
        assertNotNull(dbUser);
        assertNotNull(dbUser.getUserId());
        assertThat(userId, is(dbUser.getUserId()));
    }

    @Test
    public void insertOrgan_UserDept() {
        insertDept();
        insertUser();

        Organ organData = new Organ();
        String userId = "ceo";
        String organDeptCode = "d1000";
        String organDeptName = "Nonamed Company";
        organData.setOrganDeptName(organDeptName);

        Dept deptData = new Dept();
        deptData.setDeptCode(organDeptCode);

        User userData = new User();
        userData.setUserId(userId);

        organData.setUsers(userData);   // select user
        organData.setDepts(deptData);   // select dept

        organRepository.save(organData);
        Organ dbOrgan = organRepository.findOne(1);
        assertNotNull(dbOrgan);
        assertThat(organDeptName, is(dbOrgan.getOrganDeptName()));
    }

    @Test
    public void insertOrgan_Dept() {
        Organ organData = new Organ();
        String organDeptName = "Nonamed Company";
        String organDeptCode = "d1000";
        organData.setOrganDeptName(organDeptName);

        organRepository.save(organData);
        Organ dbOrgan = organRepository.findOne(1);
        assertNotNull(dbOrgan);
        assertThat(organDeptName, is(dbOrgan.getOrganDeptName()));
    }

    @Test
    public void initInsertOrgan_AllData() {
        // Insert into Dept
        Dept dept = new Dept();
        String deptCode = "d1000";
        String deptName = "Nonamed Company";
        String deptLocation = "d1000";
        dept.setDeptCode(deptCode);
        dept.setDeptName(deptName);
        dept.setDeptLocation(deptLocation);
        deptRepository.save(dept);

        // Insert into Organ from Dept
        Dept deptOrgan = new Dept();
        deptOrgan.setDeptCode(deptCode);

        Organ organDept = new Organ();
        organDept.setOrganDeptName(deptName);
        organDept.setOrganUpDeptCodes(deptLocation);
        organDept.setDepts(deptOrgan);
        organRepository.save(organDept);

        // Insert into User
        String userId = "ceo";
        String userName = "노미정";
        User user = new User();
        user.setUserId(userId);
        user.setUserName(userName);
        userRepository.save(user);

        // Insert into Organ from UserDept
        String organDeptCode = "d1000";
        String organDeptName = "Nonamed Company";
        Dept deptUser = new Dept();
        deptUser.setDeptCode(organDeptCode);

        Organ organUserDept = new Organ();
        organUserDept.setOrganDeptName(organDeptName);
        organUserDept.setOrganUpDeptCodes(deptLocation);

        organUserDept.setUsers(user);       // select user
        organUserDept.setDepts(deptUser);   // select dept
        organRepository.save(organUserDept);
        System.out.println("ok");
    }
}