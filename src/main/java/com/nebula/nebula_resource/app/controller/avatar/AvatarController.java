package com.nebula.nebula_resource.app.controller.avatar;

import com.nebula.nebula_resource.app.dto.avatar.AvatarCreateDTO;
import com.nebula.nebula_resource.app.dto.avatar.AvatarDTO;
import com.nebula.nebula_resource.app.service.avatar.AvatarService;
import com.nebula.nebula_resource.helper.api.ResponseMessage;
import com.nebula.nebula_resource.helper.api.ResultResponseMessage;
import java.net.URI;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("avatar")
public class AvatarController {
    private final AvatarService avatarService;

    @Autowired
    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @GetMapping
    public ResponseEntity<?> getAvatarList(){
        try {
            List<AvatarDTO> result = avatarService.getMyAvatarList();
            return ResponseEntity
                    .ok()
                    .body(new ResultResponseMessage(HttpStatus.OK.value(), "success", result));
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> createAvatar(AvatarCreateDTO createInfo){
        try {
            avatarService.createAvatar(createInfo);
            return ResponseEntity
                    .created(URI.create("/avatar"))
                    .body(new ResultResponseMessage(HttpStatus.CREATED.value(), "success",createInfo.getAvatarName()));
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @GetMapping("duplication/{avatarName}")
    public ResponseEntity<?> checkAvatarNameDuplicated(@PathVariable String avatarName){
        try {

            return ResponseEntity
                    .created(URI.create("/avatar/texture"))
                    .body(new ResponseMessage(HttpStatus.CREATED.value(), "available"));
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @PostMapping("texture/{avatarName}")
    public ResponseEntity<?> saveAvatarTexture(@PathVariable String avatarName, @RequestBody Map<String , Object> textureData){
        try {
            avatarService.saveTexture(avatarName, textureData);
            return ResponseEntity
                    .created(URI.create("/avatar/texture"))
                    .body(new ResponseMessage(HttpStatus.CREATED.value(), "success"));
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }
}
