package com.nebula.nebula_resource.app.dao.repository.avatar;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepository extends JpaRepository<Avatar, Integer> {
    Avatar findByAvatarName(String avatarName);
    List<Avatar> findByOwnerUsername(String userName);
}
