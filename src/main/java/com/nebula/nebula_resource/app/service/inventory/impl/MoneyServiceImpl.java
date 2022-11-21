package com.nebula.nebula_resource.app.service.inventory.impl;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import com.nebula.nebula_resource.app.dao.entity.item.coin.MoneyLog;
import com.nebula.nebula_resource.app.dao.repository.avatar.AvatarRepository;
import com.nebula.nebula_resource.app.dao.repository.item.MoneyLogRepository;
import com.nebula.nebula_resource.app.dao.repository.skyisland.SkyIslandRepository;
import com.nebula.nebula_resource.app.service.inventory.MoneyService;
import com.nebula.nebula_resource.helper.permission.PermissionChecker;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MoneyServiceImpl implements MoneyService {
    private final AvatarRepository avatarRepository;
    private final SkyIslandRepository skyIslandRepository;
    private final MoneyLogRepository moneyLogRepository;

    @Autowired
    public MoneyServiceImpl(AvatarRepository avatarRepository,
                            SkyIslandRepository skyIslandRepository,
                            MoneyLogRepository moneyLogRepository) {
        this.avatarRepository = avatarRepository;
        this.skyIslandRepository = skyIslandRepository;
        this.moneyLogRepository = moneyLogRepository;
    }

    @Override
    @Transactional
    public void mineMoney(String avatarName, int skyIslandId) {
        Avatar miner = avatarRepository.findByAvatarName(avatarName);
        PermissionChecker.checkAvatarPermission(miner);
        MoneyLog moneyLog = moneyLogRepository.findByAvatarId(miner.getId());
        if (moneyLog == null){
            moneyLogRepository.save(new MoneyLog(0,
                    new java.sql.Date(new Date().getTime()), miner));
        } else {
            checkLastMiningTime(moneyLog);
        }
        Avatar beneficiary = skyIslandRepository.findById(skyIslandId).getAvatar();
        beneficiary.setMoney(beneficiary.getMoney() + 1);
    }
    private void checkLastMiningTime(MoneyLog moneyLog){
        Long timeDifference = new Date().getTime() - moneyLog.getLastMiningTime().getTime();
        if (timeDifference/1000 < 5){
            throw new RuntimeException("채굴 대기 시간입니다.");
        }
        moneyLog.setLastMiningTime(new Date());
        moneyLogRepository.save(moneyLog);
    }
}
