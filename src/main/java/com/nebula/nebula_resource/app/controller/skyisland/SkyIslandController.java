package com.nebula.nebula_resource.app.controller.skyisland;

import com.nebula.nebula_resource.app.dto.skyisland.IslandPlaceObjectDTO;
import com.nebula.nebula_resource.app.dto.skyisland.SkyIslandDTO;
import com.nebula.nebula_resource.app.service.skyisland.SkyIslandService;
import com.nebula.nebula_resource.helper.api.ResponseMessage;
import com.nebula.nebula_resource.helper.api.ResultResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("skyisland")
public class SkyIslandController {
    private final SkyIslandService skyIslandService;

    @Autowired
    public SkyIslandController(SkyIslandService skyIslandService) {
        this.skyIslandService = skyIslandService;
    }

    @GetMapping("{islandId}")
    public ResponseEntity<?> getSkyIslandObjects(@PathVariable int islandId){
        try {
            SkyIslandDTO result = skyIslandService.loadSkyIslandById(islandId);
            return ResponseEntity
                    .ok()
                    .body(new ResultResponseMessage(HttpStatus.OK.value(), "success", result));
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }
}
