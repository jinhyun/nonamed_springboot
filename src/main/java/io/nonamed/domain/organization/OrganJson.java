package io.nonamed.domain.organization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import io.nonamed.domain.department.Dept;
import io.nonamed.domain.user.User;

import java.util.ArrayList;
import java.util.List;

public class OrganJson {
    public String toJsonOrganList(List<Organ> organList) {
        OrganJson organJson = new OrganJson();
        organJson.setOrganJsonList(castInnerOrganJsonFromOrgan(organList));

        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();

        return gson.toJson(organJson, OrganJson.class);
    }

    @Expose
    private List<InnerOrgan> organJsonList;

    private void setOrganJsonList(List<InnerOrgan> organJsonList) {
        this.organJsonList = organJsonList;
    }

    private List<InnerOrgan> castInnerOrganJsonFromOrgan(List<Organ>
                                                                 organList) {
        List<InnerOrgan> innerOrganList = new ArrayList<>();

        for(Organ organ : organList){
            InnerOrgan innerOrgan = new InnerOrgan();
            innerOrgan.setOrganDeptName(organ.getOrganDeptName());
            innerOrgan.setOrganId(organ.getOrganId());
            innerOrgan.setOrganDeptLocation(organ.getOrganDeptLocation());
            if (organ.getDepts() != null){
                Dept dept = organ.getDepts();

                InnerDept innerDept = new InnerDept();
                innerDept.setDeptCode(dept.getDeptCode());
                innerDept.setDeptLocation(dept.getDeptLocation());
                innerDept.setDeptName(dept.getDeptName());

                innerOrgan.setDepts(innerDept);
            }
            if (organ.getUsers() != null){
                User user = organ.getUsers();

                InnerUser innerUser = new InnerUser();
                innerUser.setUserId(user.getUserId());
                innerUser.setUserName(user.getUserName());

                innerOrgan.setUsers(innerUser);
            }
            innerOrganList.add(innerOrgan);
        }
        return innerOrganList;
    }

    private class InnerDept {
        @Expose
        private String deptCode;
        @Expose
        private String deptName;
        @Expose
        private String deptLocation;

        public void setDeptCode(String deptCode) {
            this.deptCode = deptCode;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public void setDeptLocation(String deptLocation) {
            this.deptLocation = deptLocation;
        }
    }

    private class InnerUser {
        @Expose
        private String userId;
        @Expose
        private String userName;

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }

    private class InnerOrgan {
        @Expose
        private int organId;
        @Expose
        private String organDeptName;
        @Expose
        private InnerUser users;
        @Expose
        private InnerDept depts;
        @Expose
        private String organDeptLocation;

        public void setOrganId(int organId) {
            this.organId = organId;
        }

        public void setOrganDeptName(String organDeptName) {
            this.organDeptName = organDeptName;
        }

        public void setUsers(InnerUser users) {
            this.users = users;
        }

        public void setDepts(InnerDept depts) {
            this.depts = depts;
        }

        public void setOrganDeptLocation(String organDeptLocation) {
            this.organDeptLocation = organDeptLocation;
        }
    }
}
