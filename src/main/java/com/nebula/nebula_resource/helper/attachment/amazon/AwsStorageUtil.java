package com.nebula.nebula_resource.helper.attachment.amazon;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface AwsStorageUtil {
    String upload(String saveName, MultipartFile file) throws IOException;
}
