package io.nonamed.domain.organization;

import io.nonamed.domain.department.Department;
import io.nonamed.domain.user.User;
import java.util.List;

public class Organization {
    private List<User> userList;
    private List<Department> departmentList;
    private Department department;

    public List<User> getUserList() {
        return userList;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public Department getDepartment() {
        return department;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
