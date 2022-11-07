package com.nebula.nebula_resource.app.service.inventory.impl;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import com.nebula.nebula_resource.app.dao.entity.inventory.AvatarBuildingBundle;
import com.nebula.nebula_resource.app.dao.entity.item.buildingbundle.BuildingBundleBuff;
import com.nebula.nebula_resource.app.dao.repository.avatar.AvatarRepository;
import com.nebula.nebula_resource.app.dao.repository.inventory.AvatarBuildingBundleRepository;
import com.nebula.nebula_resource.app.dto.inventory.BuffDTO;
import com.nebula.nebula_resource.app.dto.inventory.InventoryDTO;
import com.nebula.nebula_resource.app.dto.inventory.SlotDTO;
import com.nebula.nebula_resource.app.dto.inventory.SlotItemDTO;
import com.nebula.nebula_resource.app.service.inventory.InventoryService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final AvatarRepository avatarRepository;
    private final AvatarBuildingBundleRepository avatarBuildingBundleRepository;
    private static final int INVENTORY_SLOT_NUMBER = 24;

    @Autowired
    public InventoryServiceImpl(AvatarRepository avatarRepository,
                                AvatarBuildingBundleRepository avatarBuildingBundleRepository) {
        this.avatarRepository = avatarRepository;
        this.avatarBuildingBundleRepository = avatarBuildingBundleRepository;
    }

    @Override
    public InventoryDTO getBuildingBundleInventory(String avatarName) {
        // 아바타에 대한 권한 검사
        String username = getContextUsername();
        Avatar avatar = avatarRepository.findByAvatarName(avatarName);
        System.out.println(username +" "+ avatar.getOwner().getUsername());
        if (!username.equals(avatar.getOwner().getUsername())){
            throw new RuntimeException("토큰이 유효하지 않습니다.");
        }

        List<AvatarBuildingBundle> avatarBuildingBundleList = avatarBuildingBundleRepository.findByAvatarAvatarName(avatarName);
        InventoryDTO result = convertAvatarBuildingBundleToDTO(avatarBuildingBundleList);

        return result;
    }

    private InventoryDTO convertAvatarBuildingBundleToDTO(List<AvatarBuildingBundle> avatarBuildingBundleList){
        InventoryDTO result = new InventoryDTO();

        List<SlotDTO> slotDTOList = new ArrayList<>();
        for (int i = 0 ; i < INVENTORY_SLOT_NUMBER; i++){
            slotDTOList.add(generateEmptySlotDTO());
        }

        for(AvatarBuildingBundle avatarBuildingBundle : avatarBuildingBundleList){
            int index = avatarBuildingBundle.getSlotNumber();
            String name = avatarBuildingBundle.getBuildingBundle().getName();
            int id = avatarBuildingBundle.getBuildingBundle().getId();


            SlotItemDTO slotItemDTO = new SlotItemDTO(name, id, new ArrayList<>());
            for (BuildingBundleBuff buildingBundleBuff : avatarBuildingBundle.getBuildingBundle().getBuildingBundleBuffs()){
                BuffDTO buffDTO = new BuffDTO();
                buffDTO.setStat(buildingBundleBuff.getBuffStat());
                buffDTO.setValue(buildingBundleBuff.getBuffValue());
                buffDTO.setMax(buildingBundleBuff.getBuffMax());
                buffDTO.setMin(buildingBundleBuff.getBuffMin());

                slotItemDTO.getBuffs().add(buffDTO);
            }
            slotDTOList.get(index).setItem(slotItemDTO);
        }

        result.setSlots(slotDTOList);

        return result;
    }

    private SlotDTO generateEmptySlotDTO(){
        SlotItemDTO itemDTO = new SlotItemDTO("",-1,new ArrayList<>());
        SlotDTO slotDTO = new SlotDTO(List.of(0),itemDTO,0);

        return slotDTO;
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
