package io.nonamed.service.organization;

import io.nonamed.dao.department.DeptRepository;
import io.nonamed.domain.department.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganService {
//    @Autowired
//    UserRepository userRepository;
    @Autowired
DeptRepository deptRepository;

    /**
     * 현재단계와 한단계 하위까지의 결과를 조회한다.
     * @param deptCode
     * @return Organization
     */
//    public Organization getOrganizationDept(String deptCode) {
//        List<User> userList = userRepository.findByDeptCode(deptCode);
//        List<Department> departmentList =
//                departmentRepository.findByUpDeptCode(deptCode);
//        Department department = departmentRepository.findOne(deptCode);
//
//        Organization organization = new Organization();
//        organization.setUserList(userList);
//        organization.setDepartmentList(departmentList);
//        organization.setDepartment(department);
//
//        return organization;
//    }

    /**
     * 최상위까지 조직도를 조회한다.
     * @param deptCode
     * @return
     */
//    public Organization getOrganizationToRoot(String deptCode) {
//        boolean isAddDeptCode = true;
//        List<String> upDeptCodeList = getUpDeptCodeList(deptCode, isAddDeptCode);
//        List<User> userList = userRepository.findByDeptCodeIn(upDeptCodeList);
//
//        List<Department> departmentList =
//                departmentRepository.
//                        findByUpDeptCodeInOrderByDepthAscDeptCodeAsc(upDeptCodeList);
//
//        Department department = departmentRepository.findOne(deptCode);
//
//        Organization organization = new Organization();
//        organization.setUserList(userList);
//        organization.setDepartmentList(departmentList);
//        organization.setDepartment(department);
//
//        return organization;
//    }

    /**
     * 최상위까지 부서코드만 조회한다.
     * @param deptCode
     * @param isAddDeptCode
     * @return
     */
//    public List<String> getUpDeptCodeList(String deptCode
//            , boolean isAddDeptCode) {
//        List<Department> departmentList = departmentRepository.findAll();
//        Department department = departmentRepository.findOne(deptCode);
//
//        List<String> upDeptCodeList = new ArrayList<>();
//        String upDeptCode = department.getDeptLocation();
//        boolean isRoot = false;
//
//        int i = 0;
//        if (isAddDeptCode){
//            upDeptCodeList.add(department.getDeptCode());
//        }
//        while(i < departmentList.size()){
//            if (isRoot) break;
//            if (departmentList.get(i).getDeptCode().equals(upDeptCode)) {
//                upDeptCodeList.add(departmentList.get(i).getDeptCode());
//                upDeptCode = departmentList.get(i).getDeptLocation();
//                i = 0;
//                if (upDeptCode.equals("")){
//                    upDeptCodeList.add("");
//                    isRoot = true;
//                }
//            } else {
//                i++;
//            }
//        }
//        return upDeptCodeList;
//    }

//    public List<Department> getTree() {
//        return departmentRepository.findAll();
//    }

    public Dept getTree() {
        Dept dept = deptRepository.findOne("d0000");
        return dept;
    }
}
