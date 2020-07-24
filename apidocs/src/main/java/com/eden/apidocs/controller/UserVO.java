package com.eden.apidocs.controller;

import io.github.yedaxia.apidocs.RapMock;

public class UserVO extends SimpleUser {
    @RapMock(limit = "1-10")
    private SimpleUser[] friends; //好友


    private Boolean isFollow; //是否关注

    public SimpleUser[] getFriends() {
        return friends;
    }

    public void setFriends(UserVO[] friends) {
        this.friends = friends;
    }

    public Boolean getFollow() {
        return isFollow;
    }

    public void setFollow(Boolean follow) {
        isFollow = follow;
    }

    public void setFriends(SimpleUser[] friends) {
        this.friends = friends;
    }
}
