package com.nebula.nebula_resource.helper.permission;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import com.nebula.nebula_resource.app.dao.entity.skyisland.SkyIsland;
import com.nebula.nebula_resource.helper.exception.NoObjectException;
import com.nebula.nebula_resource.helper.exception.PermissionException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class PermissionChecker {

    public static void checkAvatarPermission(Avatar avatar){
        if (avatar == null){
            throw new NoObjectException("아바타가 존재하지 않습니다.");
        }
        String username = getContextUsername();
        if (!username.equals(avatar.getOwner().getUsername())){
            throw new PermissionException("아바타에 대한 소유권이 없습니다.");
        }
    }

    public static void checkIslandPermission(SkyIsland skyIsland){
        if (skyIsland == null){
            throw new NoObjectException("하늘섬이 존재하지 않습니다.");
        }
        String username = getContextUsername();
        if (!username.equals(skyIsland.getAvatar().getOwner().getUsername())){
            throw new PermissionException("하늘섬에 대한 소유권이 없습니다.");
        }
    }
    private static String getContextUsername(){
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return username;
        } catch (Exception e){
            throw e;
        }
    }
}
