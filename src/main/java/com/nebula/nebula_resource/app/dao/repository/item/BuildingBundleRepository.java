package com.nebula.nebula_resource.app.dao.repository.item;

import com.nebula.nebula_resource.app.dao.entity.item.buildingbundle.BuildingBundle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingBundleRepository extends JpaRepository<BuildingBundle, Integer> {
    BuildingBundle findById(int id);
}
