package io.nonamed;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.nonamed.dao.department.DeptRepository;
import io.nonamed.dao.organization.OrganRepository;
import io.nonamed.dao.user.UserRepository;
import io.nonamed.domain.department.Dept;
import io.nonamed.domain.organization.*;
import io.nonamed.domain.user.User;
import io.nonamed.service.organization.OrganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.junit.*;

import java.util.List;

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
    @Autowired
    OrganService organService;

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
    public void getOrganAllList() {
        insertOrgan_initData();
        List<Organ> organList = organService.getOrganAllList();
        // TODO: <Organ>형을 변경할수 있어야 파일단위의 json을 만들수있는데..

        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();

        OrganJson organJson = new OrganJson();
        organJson.setOrganList(organList);
        String str = gson.toJson(organJson, OrganJson.class);
        System.out.println(">>>>>>>>" + str);
    }

    @Ignore
    @Test
    public void insertOrgan_initData() {
        // Tree
        System.out.println(">>> Start Insert Init Data <<<");
        insertDeptAndOrgan("d1000", "Nonamed Company", "d1000");
            insertUserAndOrgan("ceo", "노미정", "d1000");
            insertDeptAndOrgan("d1001", "경영지원", "d1001, d1000");
            insertDeptAndOrgan("d1002", "개발본부", "d1002, d1000");
                insertUserAndOrgan("devBon", "개발본부장", "d1002");
            insertDeptAndOrgan("d1003", "개발1팀", "d1003, d1002, d1000");
                insertUserAndOrgan("oneTeamLeader", "1팀장", "d1003");
                insertUserAndOrgan("oneTeamAdvanceDeveloper", "1팀고급개발자", "d1003");
            insertDeptAndOrgan("d1004", "개발2팀", "d1004, d1002, d1000");
                insertUserAndOrgan("twoTeamLeader", "2팀장", "d1004");
            insertDeptAndOrgan("d1005", "개발3팀", "d1005, d1002, d1000");
                insertUserAndOrgan("threeTeamLeader", "3팀장", "d1005");
                insertUserAndOrgan("threeTeamBasicDeveloper", "3팀초급개발자", "d1005");
            insertDeptAndOrgan("d1006", "운영본부", "d1006, d1002, d1000");
        System.out.println(">>> End Insert Init Data <<<");
    }

    public void insertUserAndOrgan(String userId, String userName, String deptCode) {
        // Insert into User
        User user = new User();
        user.setUserId(userId);
        user.setUserName(userName);
        userRepository.save(user);

        // Insert into Organ from UserDept
        Dept dbDept = deptRepository.findOne(deptCode);

        Organ organ = new Organ();
        organ.setOrganDeptName(dbDept.getDeptName());
        organ.setOrganDeptLocation(dbDept.getDeptLocation());

        organ.setUsers(user);   // select user
        organ.setDepts(dbDept); // select dbDept
        organRepository.save(organ);
    }

    public void insertDeptAndOrgan(
            String deptCode, String deptName, String deptLocation) {
        // Insert into Dept
        Dept dept = new Dept();
        dept.setDeptCode(deptCode);
        dept.setDeptName(deptName);
        dept.setDeptLocation(deptLocation);
        deptRepository.save(dept);

        // Insert into Organ from Dept
        Dept dbDept = deptRepository.findOne(deptCode);

        Organ organ = new Organ();
        organ.setOrganDeptName(deptName);
        organ.setOrganDeptLocation(deptLocation);
        organ.setDepts(dbDept);
        organRepository.save(organ);
    }
}