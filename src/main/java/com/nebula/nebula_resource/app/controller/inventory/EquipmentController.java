package com.nebula.nebula_resource.app.controller.inventory;

import com.nebula.nebula_resource.app.dto.inventory.InventoryDTO;
import com.nebula.nebula_resource.app.dto.inventory.InventoryEquipmentDTO;
import com.nebula.nebula_resource.app.service.inventory.EquipmentService;
import com.nebula.nebula_resource.helper.api.ResponseMessage;
import com.nebula.nebula_resource.helper.api.ResultResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("equipment")
public class EquipmentController {
    private final EquipmentService equipmentService;
    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping("{avatarName}")
    public ResponseEntity<?> saveEquipment(@PathVariable String avatarName){
        try {
            InventoryDTO result = equipmentService.getEquipment(avatarName);
            return ResponseEntity
                    .ok()
                    .body(new ResultResponseMessage(HttpStatus.CREATED.value(), "success",result));
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @PostMapping("{avatarName}")
    public ResponseEntity<?> saveEquipment(@PathVariable String avatarName, @RequestBody InventoryEquipmentDTO inventoryEquipmentDTO){
        try {
            equipmentService.saveEquipment(avatarName, inventoryEquipmentDTO);
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
