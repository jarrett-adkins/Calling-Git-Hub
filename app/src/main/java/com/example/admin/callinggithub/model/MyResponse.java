package com.example.admin.callinggithub.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyResponse {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("watchers")
    @Expose
    private int watchers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated() {
        return created_at;
    }

    public void setCreated(Integer age) {
        this.created_at = created_at;
    }

    public Integer getWatchers() {
        return watchers;
    }

    public void setWatchers(Integer age) {
        this.watchers = watchers;
    }

}
