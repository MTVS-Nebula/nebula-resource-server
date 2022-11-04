package com.nebula.nebula_resource.app.controller.skyisland;

import com.nebula.nebula_resource.app.service.skyisland.SkyIslandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("skyisland")
public class SkyIslandController {
    private final SkyIslandService skyIslandService;

    @Autowired
    public SkyIslandController(SkyIslandService skyIslandService) {
        this.skyIslandService = skyIslandService;
    }
}
