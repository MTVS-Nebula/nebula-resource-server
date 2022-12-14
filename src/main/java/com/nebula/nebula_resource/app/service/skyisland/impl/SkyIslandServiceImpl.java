package com.nebula.nebula_resource.app.service.skyisland.impl;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import com.nebula.nebula_resource.app.dao.entity.inventory.AvatarBuildingBundle;
import com.nebula.nebula_resource.app.dao.entity.item.PlaceObject;
import com.nebula.nebula_resource.app.dao.entity.item.buildingbundle.BuildingBundlePlaceObject;
import com.nebula.nebula_resource.app.dao.entity.skyisland.SkyIsland;
import com.nebula.nebula_resource.app.dao.entity.skyisland.SkyIslandCoordinate;
import com.nebula.nebula_resource.app.dao.entity.skyisland.SkyIslandPlaceObject;
import com.nebula.nebula_resource.app.dao.repository.avatar.AvatarRepository;
import com.nebula.nebula_resource.app.dao.repository.avatar.FollowRepository;
import com.nebula.nebula_resource.app.dao.repository.item.PlaceObjectRepository;
import com.nebula.nebula_resource.app.dao.repository.skyisland.SkyIslandCoordinateRepository;
import com.nebula.nebula_resource.app.dao.repository.skyisland.SkyIslandPlaceObjectRepository;
import com.nebula.nebula_resource.app.dao.repository.skyisland.SkyIslandRepository;
import com.nebula.nebula_resource.app.dto.skyisland.*;
import com.nebula.nebula_resource.app.service.skyisland.SkyIslandService;
import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SkyIslandServiceImpl implements SkyIslandService {
    private final SkyIslandRepository skyIslandRepository;
    private final AvatarRepository avatarRepository;
    private final SkyIslandPlaceObjectRepository skyIslandPlaceObjectRepository;
    private final PlaceObjectRepository placeObjectRepository;
    private final SkyIslandCoordinateRepository skyIslandCoordinateRepository;
    private final FollowRepository followRepository;

    @Autowired
    public SkyIslandServiceImpl(SkyIslandRepository skyIslandRepository, AvatarRepository avatarRepository,
                                SkyIslandPlaceObjectRepository skyIslandPlaceObjectRepository,
                                PlaceObjectRepository placeObjectRepository,
                                SkyIslandCoordinateRepository skyIslandCoordinateRepository, FollowRepository followRepository) {
        this.skyIslandRepository = skyIslandRepository;
        this.avatarRepository = avatarRepository;
        this.skyIslandPlaceObjectRepository = skyIslandPlaceObjectRepository;
        this.placeObjectRepository = placeObjectRepository;
        this.skyIslandCoordinateRepository = skyIslandCoordinateRepository;
        this.followRepository = followRepository;
    }

    @Override
    public void createIsland(int avatarId) {

    }

    @Override
    public SkyIslandDTO loadSkyIslandById(int skyIslandId) {
        SkyIslandDTO result = new SkyIslandDTO();

        // ????????? ?????? ??????
        SkyIsland skyIsland = skyIslandRepository.findById(skyIslandId);
        if(skyIsland == null){
            throw new RuntimeException("?????? ???????????? ???????????? ???????????? ????????????.");
        }
        // ???????????? placeObjectList??? DTO??? ??????
        List<SkyIslandPlaceObject> placeObjectList = skyIsland.getPlaceObjects();
        IslandPlaceObjectDTO islandPlaceObjectDTO = convertSkyIslandPlaceObjectsToDto(placeObjectList);

        //????????? ????????? ??????
        result.setId(skyIslandId);
        result.setOwner(skyIsland.getAvatar().getAvatarName());
        result.setPlaceObjects(islandPlaceObjectDTO);

        return result;
    }

    private IslandPlaceObjectDTO convertSkyIslandPlaceObjectsToDto(List<SkyIslandPlaceObject> placeObjectList){
        IslandPlaceObjectDTO result = new IslandPlaceObjectDTO();

        // grid list ????????? ???
        List<GridPlaceObjectDTO> gridList = new ArrayList<>();
        for(SkyIslandPlaceObject placeObject : placeObjectList){
            // place object ??? grid ?????? (??????)
            int gridNumber = placeObject.getGridNumber();

            // ?????? ????????? grid??? ???????????? ?????? ????????? ???????????? ??????
            for(int index = gridList.size(); index <= gridNumber; index ++){
                gridList.add(new GridPlaceObjectDTO(new ArrayList<>()));
            }

            // grid??? placeobject ????????? ??????
            PlaceObjectDTO placeObjectDTO = convertPlaceObjectToDto(placeObject);
            gridList.get(gridNumber).getGridPlaceObjectList().add(placeObjectDTO);
        }
        result.setIslandGridList(gridList);

        return result;
    }

    private PlaceObjectDTO convertPlaceObjectToDto(SkyIslandPlaceObject skyIslandPlaceObject){
        PlaceObjectDTO result = new PlaceObjectDTO();
        // SOname ??????
        String soName = skyIslandPlaceObject.getPlaceObject().getSoName();
        result.setPlacedObjectTypeSOName(soName);

        // origin(??????) ??????
        PlaceObjectOriginDTO origin = new PlaceObjectOriginDTO();
        origin.setX(skyIslandPlaceObject.getX());
        origin.setY(skyIslandPlaceObject.getY());
        result.setOrigin(origin);

        // dir(??????) ??????
        result.setDir(skyIslandPlaceObject.getDir());

        return result;
    }


    @Override
    @Transactional
    public void saveSkyIslandById(int skyislandId, IslandPlaceObjectDTO islandPlaceObjectDTO){
        // ?????? ??? ????????? ?????????
        SkyIsland skyIsland = findSkyIslandById(skyislandId);
        // ????????? ???????????? ?????? ?????? ????????? ????????? ????????? ??????
        checkSkyIslandAuthentication(skyIsland);
        // ?????? ????????? ????????? ??????
        skyIslandPlaceObjectRepository.deleteBySkyIslandId(skyislandId);

        List<GridPlaceObjectDTO> gridDTOList = islandPlaceObjectDTO.getIslandGridList();
        int gridNum  = 0;
        Set avatarBundleSOSet = getAvatarBundleSOSet(skyIsland);
        for (GridPlaceObjectDTO gridDTO : gridDTOList){
            for (PlaceObjectDTO placeObjectDTO : gridDTO.getGridPlaceObjectList()){
                String soName = placeObjectDTO.getPlacedObjectTypeSOName();
                checkAvatarBundle(soName, avatarBundleSOSet);
                PlaceObject placeObject = findPlaceObjectBySOName(soName);

                SkyIslandPlaceObject skyIslandPlaceObject = new SkyIslandPlaceObject(
                        0, skyIsland, placeObject,
                        gridNum, placeObjectDTO.getOrigin().getX(), placeObjectDTO.getOrigin().getY(),
                        placeObjectDTO.getDir(), new Date(new java.util.Date().getTime()), null
                );

                skyIslandPlaceObjectRepository.save(skyIslandPlaceObject);
            }
            gridNum ++;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Map<Integer, SkyIslandCoordinateDTO> getSkyIslandMap() {
        Map<Integer, SkyIslandCoordinateDTO> result = new HashMap<>();
        List<SkyIslandCoordinate> skyIslandCoordinateList = skyIslandCoordinateRepository.findAllBy();
        for (SkyIslandCoordinate skyIslandCoordinate : skyIslandCoordinateList){
            SkyIsland skyIsland = skyIslandCoordinate.getSkyIsland();
            Avatar avatar = skyIsland.getAvatar();
            int id = skyIsland.getId();
            double pc1 = skyIslandCoordinate.getPc1();
            double pc2 = skyIslandCoordinate.getPc2();
            double pc3 = skyIslandCoordinate.getPc3();
            String keyword1 = skyIslandCoordinate.getKeyword1();
            String keyword2 = skyIslandCoordinate.getKeyword2();
            String keyword3 = skyIslandCoordinate.getKeyword3();
            String keyword4 = skyIslandCoordinate.getKeyword4();
            String avatarName = avatar.getAvatarName();
            String imageUrl = avatar.getImage().getSavedPath();
            int follower = avatar.getFollowerCount();
            SkyIslandCoordinateDTO dto = new SkyIslandCoordinateDTO(avatarName,pc1,pc2,pc3,keyword1,keyword2, keyword3, keyword4, imageUrl, follower);

            result.put(id,dto);
        }
        return result;
    }

    private SkyIsland findSkyIslandById(int id){
        SkyIsland result = skyIslandRepository.findById(id);
        if(result == null){
            throw new RuntimeException("???????????? ???????????? ?????????????????????");
        }
        return result;
    }

    private void checkSkyIslandAuthentication(SkyIsland skyIsland){
        String username = getContextUsername();
        if (!skyIsland.getAvatar().getOwner().getUsername().equals(username)){
            throw new RuntimeException("???????????? ????????? ????????????");
        }
    }

    private PlaceObject findPlaceObjectBySOName(String soName){
        PlaceObject placeObject = placeObjectRepository.findBySoName(soName);
        if(placeObject == null){
            throw new RuntimeException("SOName??? ?????????????????????");
        }
        return placeObject;
    }
    private Set<String> getAvatarBundleSOSet (SkyIsland skyIsland){
        Set<String> result = new HashSet<>();
        for(AvatarBuildingBundle avatarBuildingBundle : skyIsland.getAvatar().getAvatarBuildingBundleList()){
            for (BuildingBundlePlaceObject placeObject : avatarBuildingBundle.getBuildingBundle().getBuildingBundlePlaceObjects()){
                result.add(placeObject.getId().getPlaceObject().getSoName());
            }
        }
        return result;
    }
    private void checkAvatarBundle (String soName, Set<String> soStringSet){
        if (!soStringSet.contains(soName)){
            throw new RuntimeException(soName + "????????? ?????? ????????? ???????????? ?????? ????????????");
        }
    }

    private String getContextUsername(){
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return username;
        } catch (Exception e){
            throw e;
        }
    }
}
