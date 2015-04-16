package io.nonamed.domain.organization;

import io.nonamed.domain.department.Department;
import io.nonamed.domain.user.User;
import java.util.List;

public class Organization {
    private List<User> userList;
    private List<Department> departmentList;
    private Department department;

    public Organization(String deptCode) {

    }

    public List<User> getUserList() {
        return userList;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public Department getDepartment() {
        return department;
    }
}
