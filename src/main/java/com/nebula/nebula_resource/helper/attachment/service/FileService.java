package com.nebula.nebula_resource.helper.attachment.service;

import com.nebula.nebula_resource.app.dao.entity.file.Attachment;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    Attachment uploadFile(String category, MultipartFile file);
}
