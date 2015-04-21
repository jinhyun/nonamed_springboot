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

    @Column(name = "ORGAN_DEPT_NAME")
    private String organDeptName;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User users;

    @ManyToOne
    @JoinColumn(name="DEPT_CODE")
    private Dept depts;

    @Column(name="ORGAN_DEPT_LOCATION")
    private String organDeptLocation;

    public int getOrganId() {
        return organId;
    }

    public void setOrganId(int organId) {
        this.organId = organId;
    }

    public String getOrganDeptName() {
        return organDeptName;
    }

    public void setOrganDeptName(String organDeptName) {
        this.organDeptName = organDeptName;
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

    public String getOrganDeptLocation() {
        return organDeptLocation;
    }

    public void setOrganDeptLocation(String organDeptLocation) {
        this.organDeptLocation = organDeptLocation;
    }
}
