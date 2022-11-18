package com.nebula.nebula_resource.app.dao.repository.inventory;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import com.nebula.nebula_resource.app.dao.entity.inventory.AvatarClothes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarClothesRepository extends JpaRepository<AvatarClothes, Integer> {
    List<AvatarClothes> findByAvatarAvatarName(String avatarName);
    List<AvatarClothes> findByAvatar(Avatar avatar);
    int deleteByAvatarAvatarName(String avatarName);
    int countByClothesId(int uniqueId);
}
