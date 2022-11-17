package com.nebula.nebula_resource.app.service.inventory.impl;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import com.nebula.nebula_resource.app.dao.entity.inventory.AvatarClothes;
import com.nebula.nebula_resource.app.dao.entity.inventory.AvatarEquipment;
import com.nebula.nebula_resource.app.dao.entity.item.clothes.Clothes;
import com.nebula.nebula_resource.app.dao.entity.item.clothes.ClothesBuff;
import com.nebula.nebula_resource.app.dao.repository.avatar.AvatarRepository;
import com.nebula.nebula_resource.app.dao.repository.inventory.AvatarClothesRepository;
import com.nebula.nebula_resource.app.dao.repository.inventory.AvatarEquipmentRepository;
import com.nebula.nebula_resource.app.dao.repository.item.ClothesRepository;
import com.nebula.nebula_resource.app.dto.inventory.BuffDTO;
import com.nebula.nebula_resource.app.dto.inventory.InventoryDTO;
import com.nebula.nebula_resource.app.dto.inventory.InventoryEquipmentDTO;
import com.nebula.nebula_resource.app.dto.inventory.SlotDTO;
import com.nebula.nebula_resource.app.dto.inventory.SlotItemDTO;
import com.nebula.nebula_resource.app.service.inventory.EquipmentService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    private final AvatarRepository avatarRepository;
    private final AvatarEquipmentRepository avatarEquipmentRepository;
    private final AvatarClothesRepository avatarClothesRepository;
    private final ClothesRepository clothesRepository;
    @Autowired
    public EquipmentServiceImpl(AvatarRepository avatarRepository, AvatarEquipmentRepository avatarEquipmentRepository,
                                AvatarClothesRepository avatarClothesRepository, ClothesRepository clothesRepository) {
        this.avatarRepository = avatarRepository;
        this.avatarEquipmentRepository = avatarEquipmentRepository;
        this.avatarClothesRepository = avatarClothesRepository;
        this.clothesRepository = clothesRepository;
    }

    @Override
    public InventoryDTO getEquipment(String avatarName) {
        Avatar avatar = avatarRepository.findByAvatarName(avatarName);
        // 아바타에 대한 권한 검사
        checkAvatarAuthentication(avatar);

        List<AvatarEquipment> avatarEquipmentList = avatarEquipmentRepository.findByAvatarAvatarName(avatarName);
        InventoryDTO result = convertAvatarEquipmentToDTO(avatarEquipmentList);

        return result;
    }

    @Override
    @Transactional
    public void saveEquipment(String avatarName, InventoryEquipmentDTO inventoryEquipmentDTO) {
        Avatar avatar = avatarRepository.findByAvatarName(avatarName);
        // 아바타에 대한 권한 검사
        checkAvatarAuthentication(avatar);

        // 데이터 비교
        Set<Integer> newUniqueIdSet = getUniqueIdSet(inventoryEquipmentDTO);
        Set<Integer> oldUniqueIdSet = getUniqueIdSet(avatarName);

        System.out.println(newUniqueIdSet + " " + oldUniqueIdSet);
        if (!oldUniqueIdSet.equals(newUniqueIdSet)){
            throw new RuntimeException("부정한 행위가 검출되었습니다");
        }

        // 기존 세이브 데이터 제거
        avatarEquipmentRepository.deleteByAvatarAvatarName(avatarName);
        avatarClothesRepository.deleteByAvatarAvatarName(avatarName);

        // 신규 데이터 추가
        saveInventoryAndEquipment(avatar, inventoryEquipmentDTO);
    }

    private void saveInventoryAndEquipment(Avatar avatar, InventoryEquipmentDTO inventoryEquipmentDTO){
        InventoryDTO clothesInventory = inventoryEquipmentDTO.getClothesInventory();
        InventoryDTO equipment = inventoryEquipmentDTO.getEquipment();

        // Inventory save
        int slotIndex = 0;
        for (SlotDTO slotDTO : clothesInventory.getSlots()){
            SlotItemDTO slotItem = slotDTO.getItem();
            if(slotDTO.getAmount() == 0 || slotItem.getId() == -1 || slotItem.getUniqueId() == -1){
                slotIndex ++;
                continue;
            }
            Clothes clothes = clothesRepository.findById(slotItem.getUniqueId());
            if (clothes == null){
                slotIndex ++;
                continue;
            }
            AvatarClothes avatarClothes = new AvatarClothes(0, avatar, clothes, slotIndex);
            avatarClothesRepository.save(avatarClothes);
            slotIndex ++;
        }
        // Equipment save
        slotIndex = 0;
        for (SlotDTO slotDTO : equipment.getSlots()){
            SlotItemDTO slotItem = slotDTO.getItem();
            if(slotDTO.getAmount() == 0 || slotItem.getId() == -1){
                slotIndex ++;
                continue;
            }
            Clothes clothes = clothesRepository.findById(slotItem.getUniqueId());
            AvatarEquipment avatarEquipment = new AvatarEquipment(0, avatar, clothes, slotIndex);
            avatarEquipmentRepository.save(avatarEquipment);
            slotIndex ++;
        }
    }

    private Set<Integer> getUniqueIdSet(InventoryEquipmentDTO inventoryEquipmentDTO){
        Set<Integer> result = new HashSet<>();

        for (SlotDTO clothesSlotDTO : inventoryEquipmentDTO.getClothesInventory().getSlots()){
            int uniqueId = clothesSlotDTO.getItem().getUniqueId();
            if (uniqueId != -1 && result.contains(uniqueId)){
                throw new RuntimeException("아이템 복사 행위가 발견되었습니다.");
            }
            result.add(uniqueId);
        }
        for (SlotDTO equipmentSlotDTO : inventoryEquipmentDTO.getEquipment().getSlots()){
            int uniqueId = equipmentSlotDTO.getItem().getUniqueId();
            if (uniqueId != -1 && result.contains(uniqueId)){
                throw new RuntimeException("아이템 복사 행위가 발견되었습니다.");
            }
            result.add(uniqueId);
        }
        result.add(-1);

        return result;
    }

    private Set<Integer> getUniqueIdSet(String avatarName){
        Set<Integer> result = new HashSet<>();
        List<AvatarEquipment> avatarEquipmentList = avatarEquipmentRepository.findByAvatarAvatarName(avatarName);
        List<AvatarClothes> avatarClothesList = avatarClothesRepository.findByAvatarAvatarName(avatarName);

        for (AvatarEquipment avatarEquipment : avatarEquipmentList){
            int uniqueId = avatarEquipment.getClothes().getId();
            result.add(uniqueId);
        }
        for (AvatarClothes avatarClothes : avatarClothesList){
            int uniqueId = avatarClothes.getClothes().getId();
            result.add(uniqueId);
        }
        result.add(-1);

        return result;
    }

    private InventoryDTO convertAvatarEquipmentToDTO(List<AvatarEquipment> avatarEquipmentList){
        InventoryDTO result = new InventoryDTO();

        //슬롯 초기화
        List<SlotDTO> slotDTOList = generateEmptySlotList();

        if (avatarEquipmentList == null){
            result.setSlots(slotDTOList);
            return result;
        }

        //슬롯에 아이템 세팅
        for (AvatarEquipment avatarEquipment : avatarEquipmentList){
            int index = avatarEquipment.getSlotNumber();
            String name = avatarEquipment.getClothes().getBaseClothes().getName();
            int id = avatarEquipment.getClothes().getBaseClothes().getElementId();
            int uid = avatarEquipment.getClothes().getId();

            SlotItemDTO slotItemDTO = new SlotItemDTO(name, id, uid, new ArrayList<>());
            for (ClothesBuff clothesBuff : avatarEquipment.getClothes().getBuffs()){
                BuffDTO buffDTO = convertClothesBuffToDTO(clothesBuff);
                slotItemDTO.getBuffs().add(buffDTO);
            }

            slotDTOList.get(index).setItem(slotItemDTO);
            slotDTOList.get(index).setAmount(1);
        }
        result.setSlots(slotDTOList);

        return result;
    }

    private BuffDTO convertClothesBuffToDTO(ClothesBuff clothesBuff){
        BuffDTO result = new BuffDTO();

        result.setStat(clothesBuff.getBuffStat());
        result.setValue(clothesBuff.getBuffValue());
        result.setMax(clothesBuff.getBuffMax());
        result.setMin(clothesBuff.getBuffMin());

        return result;
    }

    private List<SlotDTO> generateEmptySlotList(){
        List<SlotDTO> slotDTOList = new ArrayList<>();
        slotDTOList.add(generateEmptySlotDTO(List.of(7)));
        slotDTOList.add(generateEmptySlotDTO(List.of(6)));
        slotDTOList.add(generateEmptySlotDTO(List.of(8)));
        slotDTOList.add(generateEmptySlotDTO(List.of(9)));
        slotDTOList.add(generateEmptySlotDTO(List.of(10)));
        slotDTOList.add(generateEmptySlotDTO(List.of(11)));
        slotDTOList.add(generateEmptySlotDTO(List.of(12)));

        return slotDTOList;
    }

    private void checkAvatarAuthentication(Avatar avatar){
        String username = getContextUsername();
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

    private SlotDTO generateEmptySlotDTO(List<Integer> allowItems){
        SlotItemDTO itemDTO = new SlotItemDTO("",-1, -1, new ArrayList<>());
        SlotDTO slotDTO = new SlotDTO(allowItems,itemDTO,0);

        return slotDTO;
    }

}
