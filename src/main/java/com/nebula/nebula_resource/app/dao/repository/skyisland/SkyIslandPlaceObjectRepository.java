package com.nebula.nebula_resource.app.dao.repository.skyisland;

import com.nebula.nebula_resource.app.dao.entity.skyisland.SkyIslandPlaceObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkyIslandPlaceObjectRepository extends JpaRepository<SkyIslandPlaceObject, Integer> {
    int deleteBySkyIslandId(int id);
}
