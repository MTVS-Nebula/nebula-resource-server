package com.nebula.nebula_resource.app.service.avatar.impl;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import com.nebula.nebula_resource.app.dao.entity.avatar.AvatarTag;
import com.nebula.nebula_resource.app.dao.entity.avatar.AvatarTexturePlane;
import com.nebula.nebula_resource.app.dao.entity.file.Attachment;
import com.nebula.nebula_resource.app.dao.entity.inventory.AvatarBuildingBundle;
import com.nebula.nebula_resource.app.dao.entity.item.buildingbundle.BuildingBundle;
import com.nebula.nebula_resource.app.dao.entity.skyisland.SkyIsland;
import com.nebula.nebula_resource.app.dao.entity.user.User;
import com.nebula.nebula_resource.app.dao.repository.UserRepository;
import com.nebula.nebula_resource.app.dao.repository.avatar.AvatarRepository;
import com.nebula.nebula_resource.app.dao.repository.avatar.AvatarTagRepository;
import com.nebula.nebula_resource.app.dao.repository.avatar.AvatarTexturePlaneRepository;
import com.nebula.nebula_resource.app.dao.repository.inventory.AvatarBuildingBundleRepository;
import com.nebula.nebula_resource.app.dao.repository.item.BuildingBundleRepository;
import com.nebula.nebula_resource.app.dao.repository.skyisland.SkyIslandRepository;
import com.nebula.nebula_resource.app.dto.avatar.AvatarCreateDTO;
import com.nebula.nebula_resource.app.dto.avatar.AvatarDTO;
import com.nebula.nebula_resource.app.service.avatar.AvatarService;
import com.nebula.nebula_resource.helper.attachment.service.FileService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AvatarServiceImpl implements AvatarService {
    private final FileService fileService;
    private final AvatarRepository avatarRepository;
    private final SkyIslandRepository skyIslandRepository;
    private final UserRepository userRepository;
    private final AvatarBuildingBundleRepository avatarBuildingBundleRepository;
    private final BuildingBundleRepository buildingBundleRepository;
    private final AvatarTagRepository avatarTagRepository;
    private final AvatarTexturePlaneRepository avatarTexturePlaneRepository;

    @Autowired
    public AvatarServiceImpl(FileService fileService, AvatarRepository avatarRepository,
                             SkyIslandRepository skyIslandRepository, UserRepository userRepository,
                             AvatarBuildingBundleRepository avatarBuildingBundleRepository,
                             BuildingBundleRepository buildingBundleRepository, AvatarTagRepository avatarTagRepository,
                             AvatarTexturePlaneRepository avatarTexturePlaneRepository) {
        this.fileService = fileService;
        this.avatarRepository = avatarRepository;
        this.skyIslandRepository = skyIslandRepository;
        this.userRepository = userRepository;
        this.avatarBuildingBundleRepository = avatarBuildingBundleRepository;
        this.buildingBundleRepository = buildingBundleRepository;
        this.avatarTagRepository = avatarTagRepository;
        this.avatarTexturePlaneRepository = avatarTexturePlaneRepository;
    }

    @Override
    public List<AvatarDTO> getMyAvatarList() {
        List<AvatarDTO> result = new ArrayList<>();

        String username = getContextUsername();
        List<Avatar> avatarList = avatarRepository.findByOwnerUsername(username);
        for(Avatar avatar : avatarList){
            if (avatar.getIsDeleted().equals("Y")){
                continue;
            }
            SkyIsland island = skyIslandRepository.findByAvatar(avatar);
            AvatarDTO avatarDTO = new AvatarDTO(avatar);
            avatarDTO.setSkyIslandId(island.getId());
            result.add(avatarDTO);
        }

        return result;
    }

    @Override
    @Transactional
    public void createAvatar(AvatarCreateDTO avatarCreateDTO) {
        String username = getContextUsername();
        User user = userRepository.findByUsername(username);

        if(avatarRepository.countByOwnerAndIsDeleted(user, "N") >= 3){
            throw new RuntimeException("이미 3개의 아바타를 소유하고 있습니다.");
        }

        Attachment image = fileService.uploadFile("profile", avatarCreateDTO.getImage());

        Avatar avatar = new Avatar(0, user, null, avatarCreateDTO.getAvatarName(),
                new Date(new java.util.Date().getTime()),
                "N",null,image,null,null,null);
        avatarRepository.save(avatar);
        generateTagList(avatarCreateDTO, avatar);
        generateBasicBundleList(avatar);
        SkyIsland skyIsland = new SkyIsland(0, avatar, null);
        skyIslandRepository.save(skyIsland);
    }

    @Override
    public void saveTexture(String avatarName, Map<String, Object> textureMap) {
        // 아바타 소유 권한 확인
        String username = getContextUsername();
        Avatar avatar = avatarRepository.findByAvatarName(avatarName);
        if(avatar == null){
            throw new RuntimeException("존재하지 않는 아바타 입니다.");
        }
        if(!avatar.getOwner().getUsername().equals(username)){
            throw new RuntimeException(avatarName + " 에 대한 소유권이 없습니다.");
        }

        //텍스처 파싱
        JSONObject jsonObject = new JSONObject(textureMap);
        String planeTexture = jsonObject.toString();

        //엔티티 객체를 만들어 텍스처 저장
        AvatarTexturePlane avatarTexturePlane = new AvatarTexturePlane(0,planeTexture,avatar);
        avatarTexturePlaneRepository.save(avatarTexturePlane);
    }

    private void generateBasicBundleList(Avatar avatar){
        BuildingBundle bbStandard = buildingBundleRepository.findById(0);
        BuildingBundle bbSystem = buildingBundleRepository.findById(1);
        AvatarBuildingBundle abStandard = new AvatarBuildingBundle(0, avatar, bbStandard,0);
        AvatarBuildingBundle abSystem = new AvatarBuildingBundle(0, avatar, bbSystem,1);
        avatarBuildingBundleRepository.save(abStandard);
        avatarBuildingBundleRepository.save(abSystem);
    }

    private void generateTagList(AvatarCreateDTO avatarCreateDTO, Avatar avatar){
        AvatarTag avatarTag1 = new AvatarTag(0, 0, avatarCreateDTO.getTag1(),avatar);
        AvatarTag avatarTag2 = new AvatarTag(0, 1, avatarCreateDTO.getTag2(),avatar);
        AvatarTag avatarTag3 = new AvatarTag(0, 2, avatarCreateDTO.getTag3(),avatar);
        AvatarTag avatarTag4 = new AvatarTag(0, 3, avatarCreateDTO.getTag4(),avatar);

        avatarTagRepository.save(avatarTag1);
        avatarTagRepository.save(avatarTag2);
        avatarTagRepository.save(avatarTag3);
        avatarTagRepository.save(avatarTag4);
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
