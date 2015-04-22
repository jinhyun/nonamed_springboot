package io.nonamed.domain.organization;


import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class OrganJson {
    @Expose
    List<Organ> organList = new ArrayList<>();

    public void setOrganList(List<Organ> organList) {
        this.organList = organList;
    }
}
