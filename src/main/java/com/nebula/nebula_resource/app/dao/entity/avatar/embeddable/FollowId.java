package com.nebula.nebula_resource.app.dao.entity.avatar.embeddable;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class FollowId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "FOLLOWING_ID")
    private Avatar following;
    @ManyToOne
    @JoinColumn(name = "FOLLOWER_ID")
    private Avatar follower;

    public FollowId() {
    }

    public FollowId(Avatar following, Avatar follower) {
        this.following = following;
        this.follower = follower;
    }

    public Avatar getFollowing() {
        return following;
    }

    public void setFollowing(Avatar following) {
        this.following = following;
    }

    public Avatar getFollower() {
        return follower;
    }

    public void setFollower(Avatar follower) {
        this.follower = follower;
    }

    @Override
    public String toString() {
        return "FollowId{" +
                "following=" + following +
                ", follower=" + follower +
                '}';
    }
}
