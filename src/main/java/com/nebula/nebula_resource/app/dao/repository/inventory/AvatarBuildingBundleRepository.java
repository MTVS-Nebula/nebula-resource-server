package com.nebula.nebula_resource.app.dao.repository.inventory;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import com.nebula.nebula_resource.app.dao.entity.inventory.AvatarBuildingBundle;
import com.nebula.nebula_resource.app.dao.entity.item.buildingbundle.BuildingBundle;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarBuildingBundleRepository extends JpaRepository<AvatarBuildingBundle, Integer> {
    List<AvatarBuildingBundle> findByAvatarAvatarName(String avatarName);
    AvatarBuildingBundle findByAvatarAndBuildingBundle(Avatar avatar, BuildingBundle buildingBundle);

}
