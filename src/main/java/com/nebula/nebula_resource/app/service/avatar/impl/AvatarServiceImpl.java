package com.nebula.nebula_resource.app.service.avatar.impl;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import com.nebula.nebula_resource.app.dao.entity.skyisland.SkyIsland;
import com.nebula.nebula_resource.app.dao.repository.avatar.AvatarRepository;
import com.nebula.nebula_resource.app.dao.repository.skyisland.SkyIslandRepository;
import com.nebula.nebula_resource.app.dto.avatar.AvatarDTO;
import com.nebula.nebula_resource.app.service.avatar.AvatarService;
import com.nebula.nebula_resource.helper.attachment.service.FileService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AvatarServiceImpl implements AvatarService {
    private final FileService fileService;
    private final AvatarRepository avatarRepository;
    private final SkyIslandRepository skyIslandRepository;

    @Autowired
    public AvatarServiceImpl(FileService fileService, AvatarRepository avatarRepository,
                             SkyIslandRepository skyIslandRepository) {
        this.fileService = fileService;
        this.avatarRepository = avatarRepository;
        this.skyIslandRepository = skyIslandRepository;
    }

    @Override
    public List<AvatarDTO> getMyAvatarList() {
        List<AvatarDTO> result = new ArrayList<>();

        String username = getContextUsername();
        List<Avatar> avatarList = avatarRepository.findByOwnerUsername(username);
        for(Avatar avatar : avatarList){
            SkyIsland island = skyIslandRepository.findByAvatar(avatar);
            AvatarDTO avatarDTO = new AvatarDTO(avatar);
            avatarDTO.setSkyIslandId(island.getId());
            result.add(avatarDTO);
        }

        return result;
    }

    @Override
    public void createAvatar(MultipartFile file) {
        fileService.uploadFile("test",file);
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
