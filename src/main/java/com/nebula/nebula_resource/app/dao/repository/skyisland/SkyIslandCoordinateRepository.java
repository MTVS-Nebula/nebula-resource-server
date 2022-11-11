package com.nebula.nebula_resource.app.dao.repository.skyisland;

import com.nebula.nebula_resource.app.dao.entity.skyisland.SkyIslandCoordinate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkyIslandCoordinateRepository extends JpaRepository<SkyIslandCoordinate, Integer> {
    List<SkyIslandCoordinate> findAllBy();
}
