package com.nebula.nebula_resource.app.controller.inventory;

import com.nebula.nebula_resource.app.dto.skyisland.SkyIslandIdDTO;
import com.nebula.nebula_resource.app.service.inventory.MoneyService;
import com.nebula.nebula_resource.helper.api.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("money")
public class MoneyController {
    private final MoneyService moneyService;
    @Autowired
    public MoneyController(MoneyService moneyService) {
        this.moneyService = moneyService;
    }

    @PostMapping("{avatarName}")
    public ResponseEntity<?> miningMoney(@PathVariable String avatarName,
                                         @RequestBody SkyIslandIdDTO skyIslandIdDTO){
        try {
            moneyService.mineMoney(avatarName, skyIslandIdDTO.getId());
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
