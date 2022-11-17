package com.nebula.nebula_resource.app.controller.inventory;

import com.nebula.nebula_resource.app.dto.inventory.ClothesUniqueIdDTO;
import com.nebula.nebula_resource.app.dto.inventory.InventoryDTO;
import com.nebula.nebula_resource.app.service.inventory.AchieveItemService;
import com.nebula.nebula_resource.helper.api.ResponseMessage;
import com.nebula.nebula_resource.helper.api.ResultResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("achieve")
public class AchieveController {
    private final AchieveItemService achieveItemService;

    @Autowired
    public AchieveController(AchieveItemService achieveItemService) {
        this.achieveItemService = achieveItemService;
    }

    @PostMapping("pick/{avatarName}")
    public ResponseEntity<?> pickUpClothes(@PathVariable String avatarName,
                                           @RequestBody ClothesUniqueIdDTO clothesUniqueIdDTO) {
        try {
            achieveItemService.pickUpClothes(avatarName, clothesUniqueIdDTO.getUniqueId());
            return ResponseEntity
                    .ok()
                    .body(new ResponseMessage(HttpStatus.CREATED.value(), "success"));
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }
}
