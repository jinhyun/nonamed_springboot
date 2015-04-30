package io.nonamed.domain.department;

import io.nonamed.domain.organization.Organ;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name="DEPT")
public class Dept {
    @Id
    @Column(name="DEPT_ID")
    private String deptId;

    @Column(name="DEPT_NAME")
    private String deptName;

    @Column(name="UP_DEPTS")
    private String upDepts;

    @Column(name="UP_DEPTS_CNT")
    private int upDeptsCnt;

    @Column(name="BELONG_DEPT_ID")
    private String belongDeptId;

    @OneToMany(mappedBy="depts", cascade=CascadeType.ALL)
    List<Organ> organList = new ArrayList<>();

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUpDepts() {
        return upDepts;
    }

    public void setUpDepts(String upDepts) {
        this.upDepts = upDepts;
    }

    public int getUpDeptsCnt() {
        return upDeptsCnt;
    }

    public void setUpDeptsCnt(int upDeptsCnt) {
        this.upDeptsCnt = upDeptsCnt;
    }

    public String getBelongDeptId() {
        return belongDeptId;
    }

    public void setBelongDeptId(String belongDeptId) {
        this.belongDeptId = belongDeptId;
    }

    public List<Organ> getOrganList() {
        return organList;
    }

    public void setOrganList(List<Organ> organList) {
        this.organList = organList;
    }
}
