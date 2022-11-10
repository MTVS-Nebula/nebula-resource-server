package com.nebula.nebula_resource.helper.attachment.service.impl;

import com.nebula.nebula_resource.app.dao.entity.file.Attachment;
import com.nebula.nebula_resource.app.dao.repository.file.AttachmentRepository;
import com.nebula.nebula_resource.helper.attachment.amazon.AwsStorageUtil;
import com.nebula.nebula_resource.helper.attachment.service.FileService;
import java.io.IOException;
import java.sql.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
    private final AwsStorageUtil awsStorageUtil;
    private final AttachmentRepository attachmentRepository;

    @Autowired
    public FileServiceImpl(AwsStorageUtil awsStorageUtil, AttachmentRepository attachmentRepository) {
        this.awsStorageUtil = awsStorageUtil;
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    @Transactional
    public Attachment uploadFile(String category, MultipartFile file) {
        validateFileExists(file);

        String originalFileName = file.getOriginalFilename();
        String ext = originalFileName.substring(originalFileName.lastIndexOf(".")+1);
        String saveName = category + "/" + UUID.randomUUID() + "." + ext;

        try {
            String url = awsStorageUtil.upload(saveName, file);
            System.out.println(url);
            Attachment result = saveAttachment(originalFileName, saveName, ext, url);

            return result;
        } catch (RuntimeException e){
            throw e;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateFileExists(MultipartFile multipartFile){
        if(multipartFile.isEmpty()){
            throw new RuntimeException("파일이 비어있습니다.");
        }
    }

    private Attachment saveAttachment(String originalName, String saveName, String ext, String url){
        Attachment attachment = new Attachment(0,originalName,saveName,url,ext,new Date(new java.util.Date().getTime()));
        attachmentRepository.save(attachment);

        return attachment;
    }
}
