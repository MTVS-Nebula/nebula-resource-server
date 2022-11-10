package com.nebula.nebula_resource.app.service.avatar;

import com.nebula.nebula_resource.app.dto.avatar.AvatarCreateDTO;
import com.nebula.nebula_resource.app.dto.avatar.AvatarDTO;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AvatarService {
    List<AvatarDTO> getMyAvatarList();
    void createAvatar(AvatarCreateDTO avatarCreateDTO);
}
