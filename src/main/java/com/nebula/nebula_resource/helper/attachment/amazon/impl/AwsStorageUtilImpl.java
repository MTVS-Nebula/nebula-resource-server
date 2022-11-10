package com.nebula.nebula_resource.helper.attachment.amazon.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.nebula.nebula_resource.helper.attachment.amazon.AwsStorageUtil;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class AwsStorageUtilImpl implements AwsStorageUtil{
    private final AmazonS3 amazonS3;
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Autowired
    public AwsStorageUtilImpl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Override
    public String upload(String saveName, MultipartFile file) throws IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getInputStream().available());

        objectMetadata.setContentType(file.getContentType());
        amazonS3.putObject(new PutObjectRequest(bucketName, saveName, file.getInputStream(), objectMetadata));

        return amazonS3.getUrl(bucketName, saveName).toString();
    }

}
