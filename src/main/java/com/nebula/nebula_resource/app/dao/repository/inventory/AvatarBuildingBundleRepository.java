package com.nebula.nebula_resource.app.dao.repository.inventory;

import com.nebula.nebula_resource.app.dao.entity.inventory.AvatarBuildingBundle;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarBuildingBundleRepository extends JpaRepository<AvatarBuildingBundle, Integer> {
    List<AvatarBuildingBundle> findByAvatarAvatarName(String avatarName);

}
