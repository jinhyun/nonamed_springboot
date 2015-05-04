package io.nonamed.domain.user;

import com.google.gson.annotations.Expose;
import io.nonamed.domain.organization.Organ;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="USER")
public class User {
    @Id
    @Column(name="USER_ID")
    @Expose
    private String userId;

    @Column(name="USER_NO")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    private String userNo;

    @Column(name="USER_NAME")
    @Expose
    private String userName;

    @OneToMany(mappedBy="users", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    List<Organ> organList = new ArrayList<>();

    @Expose
    private String userPassword;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Organ> getOrganList() {
        return organList;
    }

    public void setOrganList(List<Organ> organList) {
        this.organList = organList;
    }
}
