package com.nebula.nebula_resource.app.service.avatar;

import com.nebula.nebula_resource.app.dao.entity.avatar.Follow;

import java.util.List;

public interface FollowService {
    void followAvatar(String followerName, String followingName);
    void unfollowAvatar(String followerName, String followingName);
    List<Follow> getMyFollowingList(String follower);
}
