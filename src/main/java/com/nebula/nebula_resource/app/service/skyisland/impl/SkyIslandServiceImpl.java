package com.nebula.nebula_resource.app.service.skyisland.impl;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import com.nebula.nebula_resource.app.dao.entity.inventory.AvatarBuildingBundle;
import com.nebula.nebula_resource.app.dao.entity.item.PlaceObject;
import com.nebula.nebula_resource.app.dao.entity.item.buildingbundle.BuildingBundlePlaceObject;
import com.nebula.nebula_resource.app.dao.entity.skyisland.SkyIsland;
import com.nebula.nebula_resource.app.dao.entity.skyisland.SkyIslandCoordinate;
import com.nebula.nebula_resource.app.dao.entity.skyisland.SkyIslandPlaceObject;
import com.nebula.nebula_resource.app.dao.repository.avatar.AvatarRepository;
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

    @Autowired
    public SkyIslandServiceImpl(SkyIslandRepository skyIslandRepository, AvatarRepository avatarRepository,
                                SkyIslandPlaceObjectRepository skyIslandPlaceObjectRepository,
                                PlaceObjectRepository placeObjectRepository,
                                SkyIslandCoordinateRepository skyIslandCoordinateRepository) {
        this.skyIslandRepository = skyIslandRepository;
        this.avatarRepository = avatarRepository;
        this.skyIslandPlaceObjectRepository = skyIslandPlaceObjectRepository;
        this.placeObjectRepository = placeObjectRepository;
        this.skyIslandCoordinateRepository = skyIslandCoordinateRepository;
    }

    @Override
    public void createIsland(int avatarId) {

    }

    @Override
    public SkyIslandDTO loadSkyIslandById(int skyIslandId) {
        SkyIslandDTO result = new SkyIslandDTO();

        // 하늘섬 정보 조회
        SkyIsland skyIsland = skyIslandRepository.findById(skyIslandId);
        if(skyIsland == null){
            throw new RuntimeException("해당 아이디의 하늘섬이 존재하지 않습니다.");
        }
        // 하늘섬의 placeObjectList를 DTO로 변경
        List<SkyIslandPlaceObject> placeObjectList = skyIsland.getPlaceObjects();
        IslandPlaceObjectDTO islandPlaceObjectDTO = convertSkyIslandPlaceObjectsToDto(placeObjectList);

        //결과에 데이터 세팅
        result.setId(skyIslandId);
        result.setOwner(skyIsland.getAvatar().getAvatarName());
        result.setPlaceObjects(islandPlaceObjectDTO);

        return result;
    }

    private IslandPlaceObjectDTO convertSkyIslandPlaceObjectsToDto(List<SkyIslandPlaceObject> placeObjectList){
        IslandPlaceObjectDTO result = new IslandPlaceObjectDTO();

        // grid list 데이터 셋
        List<GridPlaceObjectDTO> gridList = new ArrayList<>();
        for(SkyIslandPlaceObject placeObject : placeObjectList){
            // place object 의 grid 번호 (층수)
            int gridNumber = placeObject.getGridNumber();

            // 해당 층수의 grid가 없을경우 해당 층까지 어레이를 추가
            for(int index = gridList.size(); index <= gridNumber; index ++){
                gridList.add(new GridPlaceObjectDTO(new ArrayList<>()));
            }

            // grid에 placeobject 배치를 추가
            PlaceObjectDTO placeObjectDTO = convertPlaceObjectToDto(placeObject);
            gridList.get(gridNumber).getGridPlaceObjectList().add(placeObjectDTO);
        }
        result.setIslandGridList(gridList);

        return result;
    }

    private PlaceObjectDTO convertPlaceObjectToDto(SkyIslandPlaceObject skyIslandPlaceObject){
        PlaceObjectDTO result = new PlaceObjectDTO();
        // SOname 설정
        String soName = skyIslandPlaceObject.getPlaceObject().getSoName();
        result.setPlacedObjectTypeSOName(soName);

        // origin(좌표) 설정
        PlaceObjectOriginDTO origin = new PlaceObjectOriginDTO();
        origin.setX(skyIslandPlaceObject.getX());
        origin.setY(skyIslandPlaceObject.getY());
        result.setOrigin(origin);

        // dir(방향) 설정
        result.setDir(skyIslandPlaceObject.getDir());

        return result;
    }


    @Override
    @Transactional
    public void saveSkyIslandById(int skyislandId, IslandPlaceObjectDTO islandPlaceObjectDTO){
        // 하늘 섬 정보를 받아옴
        SkyIsland skyIsland = findSkyIslandById(skyislandId);
        // 토큰이 하늘섬에 대한 수정 권한을 가지고 있는지 확인
        checkSkyIslandAuthentication(skyIsland);
        // 기존 세이브 데이터 제거
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
            String avatarName = avatar.getAvatarName();
            String imageUrl = avatar.getImage().getSavedPath();
            SkyIslandCoordinateDTO dto = new SkyIslandCoordinateDTO(avatarName,pc1,pc2,pc3,keyword1,keyword2,imageUrl);

            result.put(id,dto);
        }
        return result;
    }

    private SkyIsland findSkyIslandById(int id){
        SkyIsland result = skyIslandRepository.findById(id);
        if(result == null){
            throw new RuntimeException("하늘섬의 아이디가 잘못되었습니다");
        }
        return result;
    }

    private void checkSkyIslandAuthentication(SkyIsland skyIsland){
        String username = getContextUsername();
        if (!skyIsland.getAvatar().getOwner().getUsername().equals(username)){
            throw new RuntimeException("하늘섬의 주인이 아닙니다");
        }
    }

    private PlaceObject findPlaceObjectBySOName(String soName){
        PlaceObject placeObject = placeObjectRepository.findBySoName(soName);
        if(placeObject == null){
            throw new RuntimeException("SOName이 잘못되었습니다");
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
            throw new RuntimeException(soName + "건물에 대한 번들을 소유하고 있지 않습니다");
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
