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

        for (Organ organ : organList) {
            InnerOrgan innerOrgan = new InnerOrgan();
            innerOrgan.setOrganDeptName(organ.getOrganDeptName());
            innerOrgan.setOrganId(organ.getOrganId());
            innerOrgan.setOrganDeptLocation(organ.getOrganDeptLocation());
            innerOrgan.setOrganUpDeptLocation(organ.getOrganUpDeptLocation());
            innerOrgan.setOrganUpDeptCnt(organ.getOrganUpDeptCnt());
            if (organ.getDepts() != null) {
                Dept dept = organ.getDepts();

                InnerDept innerDept = new InnerDept();
                innerDept.setDeptCode(dept.getDeptCode());
                innerDept.setDeptLocation(dept.getDeptLocation());
                innerDept.setDeptName(dept.getDeptName());
                innerDept.setUpDeptCnt(dept.getUpDeptCnt());
                innerDept.setUpDeptLocation(dept.getUpDeptLocation());

                innerOrgan.setDepts(innerDept);
            }
            if (organ.getUsers() != null) {
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
        @Expose
        private int upDeptCnt;
        @Expose
        private String upDeptLocation;

        public void setDeptCode(String deptCode) {
            this.deptCode = deptCode;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public void setDeptLocation(String deptLocation) {
            this.deptLocation = deptLocation;
        }

        public void setUpDeptCnt(int upDeptCnt) {
            this.upDeptCnt = upDeptCnt;
        }

        public int getUpDeptCnt() {
            return upDeptCnt;
        }

        public void setUpDeptLocation(String upDeptLocation) {
            this.upDeptLocation = upDeptLocation;
        }

        public String getUpDeptLocation() {
            return upDeptLocation;
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
        @Expose
        private String organUpDeptLocation;
        @Expose
        private int organUpDeptCnt;

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

        public void setOrganUpDeptLocation(String organUpDeptLocation) {
            this.organUpDeptLocation = organUpDeptLocation;
        }

        public String getOrganUpDeptLocation() {
            return organUpDeptLocation;
        }

        public void setOrganUpDeptCnt(int organUpDeptCnt) {
            this.organUpDeptCnt = organUpDeptCnt;
        }

        public int getOrganUpDeptCnt() {
            return organUpDeptCnt;
        }
    }
}
