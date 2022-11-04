package com.nebula.nebula_resource.app.dao.repository.skyisland;

import com.nebula.nebula_resource.app.dao.entity.skyisland.SkyIsland;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkyIslandRepository extends JpaRepository<Integer, SkyIsland> {
    public SkyIsland findById(int id);
}
