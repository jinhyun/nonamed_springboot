package io.nonamed.service.organization;

import io.nonamed.dao.department.DeptRepository;
import io.nonamed.dao.organization.OrganRepository;
import io.nonamed.dao.user.UserRepository;
import io.nonamed.domain.department.Dept;
import io.nonamed.domain.organization.Organ;
import io.nonamed.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganService {
    @Autowired
    OrganRepository organRepository;

    @Autowired
    DeptRepository deptRepository;

    @Autowired
    UserRepository userRepository;

    public List<Organ> getOrganAllList() {
        insertOrgan_initData();
        return organRepository.findAll();
    }

    public void insertOrgan_initData() {
        deleteUserAndDeptAndOrgan();
        // Tree
        System.out.println(">>> Start Insert Init Data <<<");
        insertDeptAndOrgan("d1000", "Nonamed Company", "d1000", "root", 1);
            insertUserAndOrgan("ceo", "노미정", "d1000");
            insertDeptAndOrgan("d1001", "경영지원", "d1001, d1000, root", "d1000, root", 2);
            insertDeptAndOrgan("d1002", "개발본부", "d1002, d1000, root", "d1000, root", 2);
                insertUserAndOrgan("devBon", "개발본부장", "d1002");
                insertDeptAndOrgan("d1003", "개발1팀", "d1003, d1002, d1000, root", "d1002, d1000, root", 3);
                    insertUserAndOrgan("oneTeamLeader", "1팀장", "d1003");
                    insertUserAndOrgan("oneTeamAdvanceDeveloper", "1팀고급개발자", "d1003");
                insertDeptAndOrgan("d1004", "개발2팀", "d1004, d1002, d1000, root", "d1002, d1000, root", 3);
                    insertUserAndOrgan("twoTeamLeader", "2팀장", "d1004");
                insertDeptAndOrgan("d1005", "개발3팀", "d1005, d1002, d1000, root", "d1002, d1000, root", 3);
                    insertUserAndOrgan("threeTeamLeader", "3팀장", "d1005");
                    insertUserAndOrgan("threeTeamBasicDeveloper", "3팀초급개발자", "d1005");
            insertDeptAndOrgan("d1006", "운영본부", "d1006, d1000, root", "d1000, root", 2);
                insertUserAndOrgan("smBon", "운영본부장", "d1006");
        System.out.println(">>> End Insert Init Data <<<");
    }

    private void deleteUserAndDeptAndOrgan() {
        userRepository.deleteAll();
        deptRepository.deleteAll();
        organRepository.deleteAll();
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
//        organ.setOrganDeptLocation(dbDept.getDeptLocation());
        organ.setOrganDeptLocation(""); //check
        organ.setOrganUpDeptCnt(dbDept.getUpDeptCnt()+1);
        organ.setOrganUpDeptLocation(dbDept.getDeptCode() + ", " + dbDept.getUpDeptLocation()); //check

        organ.setUsers(user);   // select user
        organ.setDepts(dbDept); // select dbDept
        organRepository.save(organ);
    }

    public void insertDeptAndOrgan(
            String deptCode, String deptName, String deptLocation, String upDeptLocation, int upDeptCnt) {
        // Insert into Dept
        Dept dept = new Dept();
        dept.setDeptCode(deptCode);
        dept.setDeptName(deptName);
        dept.setDeptLocation(deptLocation);
        dept.setUpDeptLocation(upDeptLocation);
        dept.setUpDeptCnt(upDeptCnt);
        deptRepository.save(dept);

        // Insert into Organ from Dept
        Dept dbDept = deptRepository.findOne(deptCode);

        Organ organ = new Organ();
        organ.setOrganDeptName(deptName);
//        organ.setOrganDeptLocation(deptLocation);
        organ.setOrganDeptLocation("");     //check
        organ.setOrganUpDeptLocation(upDeptLocation);
        organ.setOrganUpDeptCnt(upDeptCnt);
        organ.setDepts(dbDept);
        organRepository.save(organ);
    }
}
