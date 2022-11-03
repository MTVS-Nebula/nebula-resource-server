package com.nebula.nebula_resource.app.dao.entity;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "TBL_AVATAR", schema = "avatar")
@SequenceGenerator(
        name = "SEQ_AVATAR_ID_GENERATOR",
        sequenceName = "SEQ_AVATAR_ID",
        initialValue = 1,
        allocationSize = 1
)
public class Avatar {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_AVATAR_ID_GENERATOR"
    )
    @Column(name = "ID")
    private int id;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User owner;
    @OneToOne(mappedBy = "avatar")
    private AvatarTexture texture;
    @Column(name = "NAME")
    private String avatarName;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "IS_DELETED")
    private String isDeleted;
    @Column(name = "DELETED_DATE")
    private Date deletedDate;

    public Avatar() {
    }

    public Avatar(int id, User owner, AvatarTexture texture, String avatarName, Date createdDate, String isDeleted, Date deletedDate) {
        this.id = id;
        this.owner = owner;
        this.texture = texture;
        this.avatarName = avatarName;
        this.createdDate = createdDate;
        this.isDeleted = isDeleted;
        this.deletedDate = deletedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public AvatarTexture getTexture() {
        return texture;
    }

    public void setTexture(AvatarTexture texture) {
        this.texture = texture;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "id=" + id +
                ", owner=" + owner +
                ", texture=" + texture +
                ", avatarName='" + avatarName + '\'' +
                ", createdDate=" + createdDate +
                ", isDeleted='" + isDeleted + '\'' +
                ", deletedDate=" + deletedDate +
                '}';
    }
}
