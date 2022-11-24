package com.nebula.nebula_resource.app.controller.purchase;

import com.nebula.nebula_resource.app.dto.purchase.BuildingBundleNameDTO;
import com.nebula.nebula_resource.app.dto.purchase.PurchaseBuildingBundleDTO;
import com.nebula.nebula_resource.app.dto.purchase.PurchaseListDTO;
import com.nebula.nebula_resource.app.service.purchase.PurchaseService;
import com.nebula.nebula_resource.helper.api.ResponseMessage;
import com.nebula.nebula_resource.helper.api.ResultResponseMessage;
import com.nebula.nebula_resource.helper.exception.PermissionException;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("purchase")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("{avatarName}")
    public ResponseEntity<?> getPurchasableList(@PathVariable String avatarName) {
        try {
            PurchaseListDTO result = purchaseService.getPurchaseList(avatarName);
            return ResponseEntity
                    .created(URI.create("/inventory/building-bundle"))
                    .body(new ResultResponseMessage(HttpStatus.CREATED.value(), "success", result));
        } catch (PermissionException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseMessage(HttpStatus.UNAUTHORIZED.value(), e.getMessage()));
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @PostMapping("building-bundle/{avatarName}")
    public ResponseEntity<?> purchaseBuildingBundle(@PathVariable String avatarName,
                                                    @RequestBody BuildingBundleNameDTO buildingBundleNameDTO) {
        try {
            PurchaseBuildingBundleDTO result
                    = purchaseService.purchaseBundle(avatarName, buildingBundleNameDTO.getBuildingBundleName());
            return ResponseEntity
                    .created(URI.create("/inventory/building-bundle"))
                    .body(new ResultResponseMessage(HttpStatus.CREATED.value(), "success", result));
        } catch (PermissionException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseMessage(HttpStatus.UNAUTHORIZED.value(), e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

}
