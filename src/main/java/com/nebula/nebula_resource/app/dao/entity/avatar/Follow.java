package com.nebula.nebula_resource.app.dao.entity.avatar;

import com.nebula.nebula_resource.app.dao.entity.avatar.embeddable.FollowId;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "TBL_FOLLOW", schema = "avatar")
public class Follow {
    @EmbeddedId
    private FollowId id;
    @Column(name = "FOLLOW_TS")
    private Timestamp timestamp;

    public Follow() {
    }

    public Follow(FollowId id, Timestamp timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public FollowId getId() {
        return id;
    }

    public void setId(FollowId id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                '}';
    }
}
