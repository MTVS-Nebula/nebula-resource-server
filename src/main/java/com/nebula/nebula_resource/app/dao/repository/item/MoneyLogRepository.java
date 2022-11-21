package com.nebula.nebula_resource.app.dao.repository.item;

import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import com.nebula.nebula_resource.app.dao.entity.item.coin.MoneyLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyLogRepository extends JpaRepository<MoneyLog, Integer> {
    MoneyLog findByAvatarId(int id);
}
