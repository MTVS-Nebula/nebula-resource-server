package com.nebula.nebula_resource.app.dao.repository.avatar;

import com.nebula.nebula_resource.app.dao.entity.avatar.Follow;
import com.nebula.nebula_resource.app.dao.entity.avatar.embeddable.FollowId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, FollowId> {
    int countByIdFollowingId(int followingId);
    List<Follow> findByIdFollowerId(int followerId);
    int deleteByIdFollowerIdAndIdFollowingId(int followerId, int followingId);
}
