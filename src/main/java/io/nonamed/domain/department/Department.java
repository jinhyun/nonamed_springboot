package io.nonamed.domain.department;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name="DEPARTMENT")
public class Department {
    @Id
    private String deptCode;
    private String deptName;
    private String upDeptCode;
    private String depth;

    public String getDeptName() {
        return deptName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public String getUpDeptCode() {
        return upDeptCode;
    }

    public String getDepth() {
        return depth;
    }
}
