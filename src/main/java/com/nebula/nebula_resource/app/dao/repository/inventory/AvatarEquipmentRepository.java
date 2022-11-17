package com.nebula.nebula_resource.app.dao.repository.inventory;

import com.nebula.nebula_resource.app.dao.entity.inventory.AvatarEquipment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarEquipmentRepository extends JpaRepository<AvatarEquipment, Integer> {
    List<AvatarEquipment> findByAvatarAvatarName(String avatarName);
    int deleteByAvatarAvatarName(String avatarName);
    int countByClothesId(int uniqueId);
}
