package io.nonamed.domain.department;

import io.nonamed.domain.organization.Organ;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name="DEPT")
public class Dept {
    @Id
    @Column(name="DEPT_CODE")
    private String deptCode;

    @Column(name="DEPT_NAME")
    private String deptName;

    @Column(name="DEPT_LOCATION")
    private String deptLocation;

    @OneToMany(mappedBy="depts", cascade=CascadeType.ALL)
    List<Organ> organList = new ArrayList<>();

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptLocation() {
        return deptLocation;
    }

    public void setDeptLocation(String deptLocation) {
        this.deptLocation = deptLocation;
    }

    public List<Organ> getOrganList() {
        return organList;
    }

    public void setOrganList(List<Organ> organList) {
        this.organList = organList;
    }
}
