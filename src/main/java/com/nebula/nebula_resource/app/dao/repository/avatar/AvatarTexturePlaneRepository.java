package com.nebula.nebula_resource.app.dao.repository.avatar;

import com.nebula.nebula_resource.app.dao.entity.avatar.AvatarTexturePlane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarTexturePlaneRepository extends JpaRepository<AvatarTexturePlane, Integer> {
    AvatarTexturePlane findById(int id);
    int deleteByAvatarId(int avatarId);
}
