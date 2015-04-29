package io.nonamed.domain.department;

import com.google.gson.annotations.Expose;
import io.nonamed.domain.organization.Organ;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name="DEPT")
public class Dept {
    @Expose
    @Id
    @Column(name="DEPT_CODE")
    private String deptCode;

    @Expose
    @Column(name="DEPT_NAME")
    private String deptName;

    @Expose
    @Column(name="DEPT_LOCATION")
    private String deptLocation;

    @Expose
    @Column(name="UP_DEPT_LOCATION")
    private String upDeptLocation;

    @Expose(serialize = false, deserialize = false)
    @OneToMany(mappedBy="depts", cascade=CascadeType.ALL)
    List<Organ> organList = new ArrayList<>();
    private int upDeptCnt;

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

    public String getUpDeptLocation() {
        return upDeptLocation;
    }

    public void setUpDeptLocation(String upDeptLocation) {
        this.upDeptLocation = upDeptLocation;
    }

    public List<Organ> getOrganList() {
        return organList;
    }

    public void setOrganList(List<Organ> organList) {
        this.organList = organList;
    }

    public void setUpDeptCnt(int upDeptCnt) {
        this.upDeptCnt = upDeptCnt;
    }

    public int getUpDeptCnt() {
        return upDeptCnt;
    }
}
