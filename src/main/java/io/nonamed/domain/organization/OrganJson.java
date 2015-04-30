package io.nonamed.domain.organization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import io.nonamed.domain.department.Dept;
import io.nonamed.domain.user.User;

import java.util.ArrayList;
import java.util.List;

public class OrganJson {
    @Expose
    private List<InnerOrgan> organJsonList;

    private void setOrganJsonList(List<InnerOrgan> organJsonList) {
        this.organJsonList = organJsonList;
    }

    public String toJsonOrganList(List<Organ> organList) {
        OrganJson organJson = new OrganJson();
        organJson.setOrganJsonList(castInnerOrganJsonFromOrgan(organList));

        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();

        return gson.toJson(organJson, OrganJson.class);
    }

    private List<InnerOrgan> castInnerOrganJsonFromOrgan(List<Organ>organList) {
        List<InnerOrgan> innerOrganList = new ArrayList<>();

        for (Organ organ : organList) {
            InnerOrgan innerOrgan = new InnerOrgan();
            innerOrgan.setOrganId(organ.getOrganId());
            innerOrgan.setOrganBelongDeptId(organ.getOrganBelongDeptId());
            innerOrgan.setOrganCode(organ.getOrganCode());
            innerOrgan.setOrganDeptIdUserNo(organ.getOrganDeptIdUserNo());
            innerOrgan.setOrganDeptNameUserName(organ.getOrganDeptNameUserName());
            innerOrgan.setOrganUpDepts(organ.getOrganUpDepts());
            innerOrgan.setOrganUpDeptsCnt(organ.getOrganUpDeptsCnt());
            if (organ.getDepts() != null) {
                Dept dept = organ.getDepts();

                InnerDept innerDept = new InnerDept();
                innerDept.setDeptId(dept.getDeptId());
                innerDept.setUpDepts(dept.getUpDepts());
                innerDept.setDeptName(dept.getDeptName());
                innerDept.setUpDeptsCnt(dept.getUpDeptsCnt());
                innerDept.setBelongDeptId(dept.getBelongDeptId());

                innerOrgan.setDepts(innerDept);
            }
            if (organ.getUsers() != null) {
                User user = organ.getUsers();

                InnerUser innerUser = new InnerUser();
                innerUser.setUserId(user.getUserId());
                innerUser.setUserName(user.getUserName());
                innerUser.setUserNo(user.getUserNo());

                innerOrgan.setUsers(innerUser);
            }
            innerOrganList.add(innerOrgan);
        }
        return innerOrganList;
    }

    private class InnerDept {
        @Expose
        private String deptId;
        @Expose
        private String deptName;
        @Expose
        private String upDepts;
        @Expose
        private int upDeptsCnt;
        @Expose
        private String belongDeptId;

        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public void setUpDepts(String upDepts) {
            this.upDepts = upDepts;
        }

        public void setUpDeptsCnt(int upDeptsCnt) {
            this.upDeptsCnt = upDeptsCnt;
        }

        public void setBelongDeptId(String belongDeptId) {
            this.belongDeptId = belongDeptId;
        }
    }

    private class InnerUser {
        @Expose
        private String userId;
        @Expose
        private String userNo;
        @Expose
        private String userName;

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setUserNo(String userNo) {
            this.userNo = userNo;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }

    private class InnerOrgan {
        @Expose
        private int organId;
        @Expose
        private String organBelongDeptId;
        @Expose
        private String organDeptIdUserNo;
        @Expose
        private String organDeptNameUserName;
        @Expose
        private String organUpDepts;
        @Expose
        private int organUpDeptsCnt;
        @Expose
        private String organCode;
        @Expose
        private InnerUser users;
        @Expose
        private InnerDept depts;

        public void setOrganId(int organId) {
            this.organId = organId;
        }

        public void setOrganBelongDeptId(String organBelongDeptId) {
            this.organBelongDeptId = organBelongDeptId;
        }

        public void setOrganDeptIdUserNo(String organDeptIdUserNo) {
            this.organDeptIdUserNo = organDeptIdUserNo;
        }

        public void setOrganDeptNameUserName(String organDeptNameUserName) {
            this.organDeptNameUserName = organDeptNameUserName;
        }

        public void setOrganUpDepts(String organUpDepts) {
            this.organUpDepts = organUpDepts;
        }

        public void setOrganUpDeptsCnt(int organUpDeptsCnt) {
            this.organUpDeptsCnt = organUpDeptsCnt;
        }

        public void setOrganCode(String organCode) {
            this.organCode = organCode;
        }

        public InnerUser getUsers() {
            return users;
        }

        public void setUsers(InnerUser users) {
            this.users = users;
        }

        public void setDepts(InnerDept depts) {
            this.depts = depts;
        }
    }
}
