package com.nebula.nebula_resource.app.dao.entity.file;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_ATC", schema = "file")
@SequenceGenerator(
        name = "SEQ_ATC_ID_GENERATOR",
        sequenceName = "SEQ_ATC_ID",
        initialValue = 1,
        allocationSize = 1
)
public class Attachment {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_ATC_ID_GENERATOR"
    )
    @Column(name = "ID")
    private int id;
    @Column(name = "ORIGINAL_NAME")
    private String originalName;
    @Column(name = "SAVED_NAME")
    private String savedName;
    @Column(name = "SAVED_PATH")
    private String savedPath;
    @Column(name = "FILE_TYPE")
    private String fileType;
    @Column(name = "UPLOAD_DATE")
    private Date uploadDate;

    public Attachment() {
    }

    public Attachment(int id, String originalName, String savedName, String savedPath, String fileType,
                      Date uploadDate) {
        this.id = id;
        this.originalName = originalName;
        this.savedName = savedName;
        this.savedPath = savedPath;
        this.fileType = fileType;
        this.uploadDate = uploadDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getSavedName() {
        return savedName;
    }

    public void setSavedName(String savedName) {
        this.savedName = savedName;
    }

    public String getSavedPath() {
        return savedPath;
    }

    public void setSavedPath(String savedPath) {
        this.savedPath = savedPath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", originalName='" + originalName + '\'' +
                ", savedName='" + savedName + '\'' +
                ", savedPath='" + savedPath + '\'' +
                ", fileType='" + fileType + '\'' +
                ", uploadDate='" + uploadDate + '\'' +
                '}';
    }
}
