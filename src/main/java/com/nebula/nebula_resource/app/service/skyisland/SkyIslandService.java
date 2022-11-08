package com.nebula.nebula_resource.app.service.skyisland;

import com.nebula.nebula_resource.app.dto.skyisland.IslandPlaceObjectDTO;
import com.nebula.nebula_resource.app.dto.skyisland.SkyIslandDTO;
import org.springframework.stereotype.Service;

public interface SkyIslandService {
    public void createIsland(int avatarId);
    public SkyIslandDTO loadSkyIslandById(int skyIslandId);
    public void saveSkyIslandById(int skyIsland, IslandPlaceObjectDTO islandPlaceObjectDTO);
}
