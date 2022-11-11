package com.nebula.nebula_resource.app.dto.avatar;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import com.nebula.nebula_resource.app.dao.entity.inventory.AvatarEquipment;
import com.nebula.nebula_resource.app.dao.entity.item.clothes.Clothes;
import com.nebula.nebula_resource.app.dao.entity.item.clothes.ClothesBuff;
import com.nebula.nebula_resource.app.dto.inventory.BuffDTO;
import com.nebula.nebula_resource.app.dto.inventory.InventoryDTO;
import com.nebula.nebula_resource.app.dto.inventory.SlotDTO;
import com.nebula.nebula_resource.app.dto.inventory.SlotItemDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AvatarAppearanceVO {
    private String name;
    private Map<String, Object> texture;
    private InventoryDTO equipment;

    public AvatarAppearanceVO() {
    }

    public AvatarAppearanceVO(String name, Map<String, Object> texture, InventoryDTO equipment) {
        this.name = name;
        this.texture = texture;
        this.equipment = equipment;
    }

    public AvatarAppearanceVO(Avatar avatar){
        this.name = avatar.getAvatarName();
        if(avatar.getAvatarTexture() != null){
            JSONParser jsonParser = new JSONParser();
            try {
                Object obj = jsonParser.parse(avatar.getAvatarTexture().getTexturePlane());
                this.texture = (Map<String, Object>) obj;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        this.equipment = convertAvatarEquipmentToDTO(avatar.getAvatarEquipmentList());
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

    private SlotDTO generateEmptySlotDTO(List<Integer> allowItems){
        SlotItemDTO itemDTO = new SlotItemDTO("",-1, -1, new ArrayList<>());
        SlotDTO slotDTO = new SlotDTO(allowItems,itemDTO,0);

        return slotDTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getTexture() {
        return texture;
    }

    public void setTexture(Map<String, Object> texture) {
        this.texture = texture;
    }

    public InventoryDTO getEquipment() {
        return equipment;
    }

    public void setEquipment(InventoryDTO equipment) {
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return "AvatarAppearanceDTO{" +
                "name='" + name + '\'' +
                ", texture=" + texture +
                ", equipment=" + equipment +
                '}';
    }
}
