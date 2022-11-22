package com.nebula.nebula_resource.app.service.avatar.impl;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import com.nebula.nebula_resource.app.dao.entity.avatar.Follow;
import com.nebula.nebula_resource.app.dao.entity.avatar.embeddable.FollowId;
import com.nebula.nebula_resource.app.dao.repository.avatar.AvatarRepository;
import com.nebula.nebula_resource.app.dao.repository.avatar.FollowRepository;
import com.nebula.nebula_resource.app.service.avatar.FollowService;
import com.nebula.nebula_resource.helper.permission.PermissionChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {
    private final AvatarRepository avatarRepository;
    private final FollowRepository followRepository;

    @Autowired
    public FollowServiceImpl(AvatarRepository avatarRepository, FollowRepository followRepository) {
        this.avatarRepository = avatarRepository;
        this.followRepository = followRepository;
    }

    @Override
    @Transactional
    public void followAvatar(String followerName, String followingName) {
        if (followerName == followingName){
            throw new RuntimeException("자기 자신을 팔로우 할 수 없습니다.");
        }
        Avatar follower = findAvatarByAvatarName(followerName);
        PermissionChecker.checkAvatarPermission(follower);
        Avatar following = findAvatarByAvatarName(followingName);

        generateFollow(following, follower);
    }

    private void generateFollow(Avatar following, Avatar follower){
        FollowId followId = new FollowId(following,follower);
        Follow follow = new Follow(followId,new Timestamp(new Date().getTime()));
        followRepository.save(follow);
    }

    @Override
    public void unfollowAvatar(String followerName, String followingName) {

    }

    @Override
    public List<Follow> getMyFollowingList(String follower) {
        return null;
    }

    private Avatar findAvatarByAvatarName(String avatarName){
        Avatar avatar = avatarRepository.findByAvatarName(avatarName);
        if (avatar == null){
            throw new RuntimeException("해당 이름의 아바타가 존재하지 않습니다.");
        }
        return avatar;
    }
}
