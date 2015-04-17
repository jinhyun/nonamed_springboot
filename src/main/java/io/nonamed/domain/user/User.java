package io.nonamed.domain.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int no;
    private String name;
    private String email;
    private String deptCode;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeptCode() {
        return deptCode;
    }
}
