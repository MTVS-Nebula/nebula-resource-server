package com.nebula.nebula_resource.app.service.inventory.impl;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import com.nebula.nebula_resource.app.dao.entity.inventory.AvatarBuildingBundle;
import com.nebula.nebula_resource.app.dao.entity.inventory.AvatarClothes;
import com.nebula.nebula_resource.app.dao.entity.item.buildingbundle.BuildingBundleBuff;
import com.nebula.nebula_resource.app.dao.entity.item.clothes.ClothesBuff;
import com.nebula.nebula_resource.app.dao.repository.avatar.AvatarRepository;
import com.nebula.nebula_resource.app.dao.repository.inventory.AvatarBuildingBundleRepository;
import com.nebula.nebula_resource.app.dao.repository.inventory.AvatarClothesRepository;
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
    private final AvatarClothesRepository avatarClothesRepository;
    private static final int INVENTORY_SLOT_NUMBER = 24;
    private static final List<Integer> BB_ALLOWED_ITEMS = List.of(0);
    private static final List<Integer> CL_ALLOWED_ITEMS = List.of(5,6,7,8,9,10,11,12,13,3);

    @Autowired
    public InventoryServiceImpl(AvatarRepository avatarRepository,
                                AvatarBuildingBundleRepository avatarBuildingBundleRepository,
                                AvatarClothesRepository avatarClothesRepository) {
        this.avatarRepository = avatarRepository;
        this.avatarBuildingBundleRepository = avatarBuildingBundleRepository;
        this.avatarClothesRepository = avatarClothesRepository;
    }

    @Override
    public InventoryDTO getBuildingBundleInventory(String avatarName) {
        // 아바타에 대한 권한 검사
        checkAvatarAuthentication(avatarName);

        List<AvatarBuildingBundle> avatarBuildingBundleList = avatarBuildingBundleRepository.findByAvatarAvatarName(avatarName);
        InventoryDTO result = convertAvatarBuildingBundleToDTO(avatarBuildingBundleList);

        return result;
    }

    private InventoryDTO convertAvatarBuildingBundleToDTO(List<AvatarBuildingBundle> avatarBuildingBundleList){
        InventoryDTO result = new InventoryDTO();

        List<SlotDTO> slotDTOList = new ArrayList<>();
        for (int i = 0 ; i < INVENTORY_SLOT_NUMBER; i++){
            slotDTOList.add(generateBuildindBundleEmptySlotDTO());
        }

        for(AvatarBuildingBundle avatarBuildingBundle : avatarBuildingBundleList){
            int index = avatarBuildingBundle.getSlotNumber();
            String name = avatarBuildingBundle.getBuildingBundle().getName();
            int id = avatarBuildingBundle.getBuildingBundle().getElementId();


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


    private SlotDTO generateBuildindBundleEmptySlotDTO(){
        SlotItemDTO itemDTO = new SlotItemDTO("",-1,new ArrayList<>());
        SlotDTO slotDTO = new SlotDTO(BB_ALLOWED_ITEMS,itemDTO,0);

        return slotDTO;
    }

    @Override
    public InventoryDTO getClothesInventory(String avatarName) {
        // 아바타에 대한 권한 검사
        checkAvatarAuthentication(avatarName);

        List<AvatarClothes> avatarClothesList = avatarClothesRepository.findByAvatarAvatarName(avatarName);
        InventoryDTO result = convertAvatarClothesToDTO(avatarClothesList);

        return result;
    }

    private InventoryDTO convertAvatarClothesToDTO(List<AvatarClothes> avatarClothesList){
        InventoryDTO result = new InventoryDTO();

        // 슬롯 초기화
        List<SlotDTO> slotDTOList = new ArrayList<>();
        for (int i = 0; i < INVENTORY_SLOT_NUMBER; i++){
            slotDTOList.add(generateClothesEmptySlotDTO());
        }

        //슬롯에 아이템 세팅
        for (AvatarClothes avatarClothes : avatarClothesList){
            int index = avatarClothes.getSlotNumber();
            String name = avatarClothes.getClothes().getBaseClothes().getName();
            int id = avatarClothes.getClothes().getBaseClothes().getElementId();

            SlotItemDTO slotItemDTO = new SlotItemDTO(name, id, new ArrayList<>());
            for (ClothesBuff clothesBuff : avatarClothes.getClothes().getBuffs()){
                BuffDTO buffDTO = new BuffDTO();
                buffDTO.setStat(clothesBuff.getBuffStat());
                buffDTO.setValue(clothesBuff.getBuffValue());
                buffDTO.setMax(clothesBuff.getBuffMax());
                buffDTO.setMin(clothesBuff.getBuffMin());

                slotItemDTO.getBuffs().add(buffDTO);
            }
            slotDTOList.get(index).setItem(slotItemDTO);
        }
        result.setSlots(slotDTOList);

        return result;
    }

    private SlotDTO generateClothesEmptySlotDTO(){
        SlotItemDTO itemDTO = new SlotItemDTO("",-1,new ArrayList<>());
        SlotDTO slotDTO = new SlotDTO(CL_ALLOWED_ITEMS,itemDTO,0);

        return slotDTO;
    }


    private void checkAvatarAuthentication(String avatarName){
        String username = getContextUsername();
        Avatar avatar = avatarRepository.findByAvatarName(avatarName);
        System.out.println(username +" "+ avatar.getOwner().getUsername());
        if (!username.equals(avatar.getOwner().getUsername())){
            throw new RuntimeException("토큰이 유효하지 않습니다.");
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
