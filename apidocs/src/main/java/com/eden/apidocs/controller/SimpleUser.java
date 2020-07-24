package com.eden.apidocs.controller;

import io.github.yedaxia.apidocs.RapMock;

public class SimpleUser {
    @RapMock(value = "@ID")
    private String userId; //用户id

    @RapMock("@NAME")
    private String userName; //用户名

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
}
