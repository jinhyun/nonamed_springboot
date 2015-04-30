package io.nonamed.domain.organization;

import io.nonamed.domain.department.Dept;
import io.nonamed.domain.user.User;

import javax.persistence.*;

@Entity
@Table(name = "ORGAN")
public class Organ {
    @Id
    @Column(name = "ORGAN_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int organId;

    @Column(name = "ORGAN_BELONG_DEPT_ID")
    private String organBelongDeptId;

    @Column(name="ORGAN_DEPT_ID_USER_NO")
    private String organDeptIdUserNo;

    @Column(name="ORGAN_DEPT_NAME_USER_NAME")
    private String organDeptNameUserName;

    @Column(name="ORGAN_UP_DEPTS")
    private String organUpDepts;

    @Column(name="ORGAN_UP_DEPTS_CNT")
    private int organUpDeptsCnt;

    @Column(name="ORGAN_CODE")
    private String organCode;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User users;

    @ManyToOne
    @JoinColumn(name="DEPT_CODE")
    private Dept depts;

    public int getOrganId() {
        return organId;
    }

    public void setOrganId(int organId) {
        this.organId = organId;
    }

    public String getOrganBelongDeptId() {
        return organBelongDeptId;
    }

    public void setOrganBelongDeptId(String organBelongDeptId) {
        this.organBelongDeptId = organBelongDeptId;
    }

    public String getOrganDeptIdUserNo() {
        return organDeptIdUserNo;
    }

    public void setOrganDeptIdUserNo(String organDeptIdUserNo) {
        this.organDeptIdUserNo = organDeptIdUserNo;
    }

    public String getOrganDeptNameUserName() {
        return organDeptNameUserName;
    }

    public void setOrganDeptNameUserName(String organDeptNameUserName) {
        this.organDeptNameUserName = organDeptNameUserName;
    }

    public String getOrganUpDepts() {
        return organUpDepts;
    }

    public void setOrganUpDepts(String organUpDepts) {
        this.organUpDepts = organUpDepts;
    }

    public int getOrganUpDeptsCnt() {
        return organUpDeptsCnt;
    }

    public void setOrganUpDeptsCnt(int organUpDeptsCnt) {
        this.organUpDeptsCnt = organUpDeptsCnt;
    }

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Dept getDepts() {
        return depts;
    }

    public void setDepts(Dept depts) {
        this.depts = depts;
    }
}
