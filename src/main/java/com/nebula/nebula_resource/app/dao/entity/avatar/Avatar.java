package com.nebula.nebula_resource.app.dao.entity.avatar;


import com.nebula.nebula_resource.app.dao.entity.file.Attachment;
import com.nebula.nebula_resource.app.dao.entity.inventory.AvatarBuildingBundle;
import com.nebula.nebula_resource.app.dao.entity.inventory.AvatarEquipment;
import com.nebula.nebula_resource.app.dao.entity.user.User;

import java.util.List;
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
    @Column(name = "NAME", unique = true)
    private String avatarName;
    @Column(name = "MONEY")
    private int money;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "IS_DELETED")
    private String isDeleted;
    @Column(name = "DELETED_DATE")
    private Date deletedDate;
    @OneToOne
    private Attachment image;
    @OneToMany(mappedBy = "avatar")
    private List<AvatarBuildingBundle>  avatarBuildingBundleList;
    @OneToOne(mappedBy = "avatar")
    private AvatarTexturePlane avatarTexture;
    @OneToMany(mappedBy = "avatar")
    private List<AvatarTag> avatarTags;
    @OneToMany(mappedBy = "avatar")
    private List<AvatarEquipment> avatarEquipmentList;

    public Avatar() {
    }

    public Avatar(int id, User owner, AvatarTexture texture, String avatarName, Date createdDate, String isDeleted, Date deletedDate, Attachment image, List<AvatarBuildingBundle> avatarBuildingBundleList, AvatarTexturePlane avatarTexture,
                  List<AvatarTag> avatarTags) {
        this.id = id;
        this.owner = owner;
        this.texture = texture;
        this.avatarName = avatarName;
        this.money = 0;
        this.createdDate = createdDate;
        this.isDeleted = isDeleted;
        this.deletedDate = deletedDate;
        this.image = image;
        this.avatarBuildingBundleList = avatarBuildingBundleList;
        this.avatarTexture = avatarTexture;
        this.avatarTags = avatarTags;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
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

    public Attachment getImage() {
        return image;
    }

    public void setImage(Attachment image) {
        this.image = image;
    }

    public List<AvatarBuildingBundle> getAvatarBuildingBundleList() {
        return avatarBuildingBundleList;
    }

    public void setAvatarBuildingBundleList(
            List<AvatarBuildingBundle> avatarBuildingBundleList) {
        this.avatarBuildingBundleList = avatarBuildingBundleList;
    }

    public AvatarTexturePlane getAvatarTexture() {
        return avatarTexture;
    }

    public void setAvatarTexture(AvatarTexturePlane avatarTexture) {
        this.avatarTexture = avatarTexture;
    }

    public List<AvatarTag> getAvatarTags() {
        return avatarTags;
    }

    public void setAvatarTags(List<AvatarTag> avatarTags) {
        this.avatarTags = avatarTags;
    }

    public List<AvatarEquipment> getAvatarEquipmentList() {
        return avatarEquipmentList;
    }

    public void setAvatarEquipmentList(
            List<AvatarEquipment> avatarEquipmentList) {
        this.avatarEquipmentList = avatarEquipmentList;
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
                ", image=" + image +
                ", avatarBuildingBundleList=" + avatarBuildingBundleList +
                ", avatarTexture=" + avatarTexture +
                ", avatarTags=" + avatarTags +
                ", avatarEquipments=" + avatarEquipmentList +
                '}';
    }
}
