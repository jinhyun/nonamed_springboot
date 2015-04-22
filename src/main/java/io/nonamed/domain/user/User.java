package io.nonamed.domain.user;

import com.google.gson.annotations.Expose;
import io.nonamed.domain.organization.Organ;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="USER")
public class User {
    @Expose
    @Id
    @Column(name="USER_ID")
    private String userId;

    @Expose
    @Column(name="USER_NAME")
    private String userName;

    @Expose(serialize = false, deserialize = false)
    @OneToMany(mappedBy="users", cascade=CascadeType.ALL)
    List<Organ> organList = new ArrayList<>();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
