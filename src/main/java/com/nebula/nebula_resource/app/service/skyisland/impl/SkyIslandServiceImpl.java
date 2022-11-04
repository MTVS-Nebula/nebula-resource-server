package com.nebula.nebula_resource.app.service.skyisland.impl;

import com.nebula.nebula_resource.app.dao.entity.item.PlaceObject;
import com.nebula.nebula_resource.app.dao.entity.skyisland.SkyIsland;
import com.nebula.nebula_resource.app.dao.entity.skyisland.SkyIslandPlaceObject;
import com.nebula.nebula_resource.app.dao.repository.skyisland.SkyIslandRepository;
import com.nebula.nebula_resource.app.dto.skyisland.*;
import com.nebula.nebula_resource.app.service.skyisland.SkyIslandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkyIslandServiceImpl implements SkyIslandService {
    private final SkyIslandRepository skyIslandRepository;

    @Autowired
    public SkyIslandServiceImpl(SkyIslandRepository skyIslandRepository) {
        this.skyIslandRepository = skyIslandRepository;
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
            for(int index = gridList.size(); index < gridNumber; index ++){
                gridList.add(new GridPlaceObjectDTO());
            }

            // grid에 placeobject 배치를 추가
            PlaceObjectDTO placeObjectDTO = convertPlaceObjectToDto(placeObject);
            gridList.get(gridNumber - 1).getGridPlaceObjectList().add(placeObjectDTO);
        }
        result.setIslandGridList(gridList);

        return result;
    }

    private PlaceObjectDTO convertPlaceObjectToDto(SkyIslandPlaceObject skyIslandPlaceObject){
        PlaceObjectDTO result = new PlaceObjectDTO();
        // SOname 설정
        String soName = skyIslandPlaceObject.getId().getPlaceObject().getSoName();
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
}
