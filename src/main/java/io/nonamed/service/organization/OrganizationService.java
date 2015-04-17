package io.nonamed.service.organization;

import io.nonamed.dao.department.DepartmentRepository;
import io.nonamed.dao.user.UserRepository;
import io.nonamed.domain.department.Department;
import io.nonamed.domain.organization.Organization;
import io.nonamed.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    /**
     * 현재단계와 한단계 하위까지의 결과를 조회한다.
     * @param deptCode
     * @return Organization
     */
    public Organization getOrganization(String deptCode) {
        List<User> userList = userRepository.findByDeptCode(deptCode);
        List<Department> departmentList =
                departmentRepository.findByUpDeptCode(deptCode);
        Department department = departmentRepository.findOne(deptCode);

        Organization organization = new Organization();
        organization.setUserList(userList);
        organization.setDepartmentList(departmentList);
        organization.setDepartment(department);

        return organization;
    }
}
