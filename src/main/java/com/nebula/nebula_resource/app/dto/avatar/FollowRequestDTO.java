package com.nebula.nebula_resource.app.dto.avatar;

public class FollowRequestDTO {
    private String followingName;

    public FollowRequestDTO() {
    }

    public FollowRequestDTO(String followingName) {
        this.followingName = followingName;
    }

    public String getFollowingName() {
        return followingName;
    }

    public void setFollowingName(String followingName) {
        this.followingName = followingName;
    }

    @Override
    public String toString() {
        return "FollowRequestDTO{" +
                "following='" + followingName + '\'' +
                '}';
    }
}
