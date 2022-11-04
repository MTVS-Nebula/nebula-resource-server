package com.nebula.nebula_resource.app.service.skyisland;

import com.nebula.nebula_resource.app.dto.skyisland.IslandPlaceObjectDTO;
import org.springframework.stereotype.Service;

public interface SkyIslandService {
    public void createIsland(int avatarId);
    public IslandPlaceObjectDTO loadSkyIslandPlaceObjectById(int skyIslandId);
}
