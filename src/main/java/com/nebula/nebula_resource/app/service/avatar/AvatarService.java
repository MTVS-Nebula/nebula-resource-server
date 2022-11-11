package com.nebula.nebula_resource.app.service.avatar;

import com.nebula.nebula_resource.app.dto.avatar.AvatarAppearanceVO;
import com.nebula.nebula_resource.app.dto.avatar.AvatarCreateDTO;
import com.nebula.nebula_resource.app.dto.avatar.AvatarDTO;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public interface AvatarService {
    List<AvatarDTO> getMyAvatarList();
    void createAvatar(AvatarCreateDTO avatarCreateDTO);
    void saveTexture(String avatarName, Map<String , Object> textureMap);
    AvatarAppearanceVO getAvatarAppearance(String avatarName);
}
