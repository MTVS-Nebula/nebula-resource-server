package com.nebula.nebula_resource.app.dao.repository.avatar;

import com.nebula.nebula_resource.app.dao.entity.avatar.AvatarTag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarTagRepository extends JpaRepository<AvatarTag, Integer> {
    List<AvatarTag> findByAvatarAvatarName(String avatarName);
}
