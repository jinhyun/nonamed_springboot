package io.nonamed.domain.organization;

import com.google.gson.annotations.Expose;
import io.nonamed.domain.department.Dept;
import io.nonamed.domain.user.User;

import javax.persistence.*;

@Entity
@Table(name = "ORGAN")
public class Organ {
    @Expose
    @Id
    @Column(name = "ORGAN_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int organId;

    @Expose
    @Column(name = "ORGAN_DEPT_NAME")
    private String organDeptName;

    @Expose
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User users;

    @Expose
    @ManyToOne
    @JoinColumn(name="DEPT_CODE")
    private Dept depts;

    @Expose
    @Column(name="ORGAN_DEPT_LOCATION")
    private String organDeptLocation;

    //TODO - Need Code: User or Dept

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
