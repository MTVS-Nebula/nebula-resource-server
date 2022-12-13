package com.nebula.nebula_resource.app.controller.avatar;

import com.nebula.nebula_resource.app.dto.avatar.FollowRequestDTO;
import com.nebula.nebula_resource.app.dto.avatar.FollowResponseVO;
import com.nebula.nebula_resource.app.service.avatar.FollowService;
import com.nebula.nebula_resource.helper.api.ResponseMessage;
import com.nebula.nebula_resource.helper.api.ResultResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("follow")
public class FollowController {
    private final FollowService followService;
    @Autowired
    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("{avatarName}")
    public ResponseEntity<?> follow(@PathVariable String avatarName,
                                    @RequestBody FollowRequestDTO followRequestDTO){
        try {
            followService.followAvatar(avatarName, followRequestDTO.getFollowingName());
            return ResponseEntity
                    .created(URI.create("/follow"))
                    .body(new ResponseMessage(HttpStatus.CREATED.value(), "success"));
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @PutMapping("{avatarName}")
    public ResponseEntity<?> unfollow(@PathVariable String avatarName,
                                      @RequestBody FollowRequestDTO followRequestDTO){
        try {
            followService.unfollowAvatar(avatarName, followRequestDTO.getFollowingName());
            return ResponseEntity
                    .ok()
                    .body(new ResponseMessage(HttpStatus.OK.value(), "success"));
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @GetMapping("{avatarName}")
    public ResponseEntity<?> getFollowList(@PathVariable String avatarName){
        try {
            List<FollowResponseVO> result = followService.getAvatarFollowingList(avatarName);
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
