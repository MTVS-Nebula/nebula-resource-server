package com.nebula.nebula_resource.app.dto.avatar;

import com.nebula.nebula_resource.app.dao.entity.avatar.Follow;

import java.sql.Timestamp;

public class FollowResponseVO {
    String avatarName;
    int skyIslandId;
    Timestamp followDate;

    public FollowResponseVO() {
    }

    public FollowResponseVO(Follow follow){
        this.avatarName = follow.getId().getFollowing().getAvatarName();
        this.skyIslandId = follow.getId().getFollowing().getSkyIsland().getId();
        this.followDate = follow.getTimestamp();
    }

    public FollowResponseVO(String avatarName, int skyIslandId, Timestamp followDate) {
        this.avatarName = avatarName;
        this.skyIslandId = skyIslandId;
        this.followDate = followDate;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    public int getSkyIslandId() {
        return skyIslandId;
    }

    public void setSkyIslandId(int skyIslandId) {
        this.skyIslandId = skyIslandId;
    }

    public Timestamp getFollowDate() {
        return followDate;
    }

    public void setFollowDate(Timestamp followDate) {
        this.followDate = followDate;
    }

    @Override
    public String toString() {
        return "FollowListResponseVO{" +
                "avatarName='" + avatarName + '\'' +
                ", skyIslandId=" + skyIslandId +
                ", followDate=" + followDate +
                '}';
    }
}
