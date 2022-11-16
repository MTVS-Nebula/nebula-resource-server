package com.nebula.nebula_resource.app.dao.entity.item.coin;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_COIN_LOG")
@SequenceGenerator(
        name = "SEQ_COIN_LOG_ID_GENERATOR",
        sequenceName = "SEQ_COIN_LOG_ID",
        initialValue = 1,
        allocationSize = 1
)
public class MoneyLog {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_COIN_LOG_ID_GENERATOR"
    )
    private int id;
    @Column(name = "LAST_MINING_TIME")
    private Date lastMiningTime;
    @OneToOne
    @JoinColumn(name = "AVATAR_ID")
    private Avatar avatar;

    public MoneyLog() {
    }

    public MoneyLog(int id, Date lastMiningTime, Avatar avatar) {
        this.id = id;
        this.lastMiningTime = lastMiningTime;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getLastMiningTime() {
        return lastMiningTime;
    }

    public void setLastMiningTime(Date lastMiningTime) {
        this.lastMiningTime = lastMiningTime;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "CoinLog{" +
                "id=" + id +
                ", lastMiningTime=" + lastMiningTime +
                ", avatar=" + avatar +
                '}';
    }
}
