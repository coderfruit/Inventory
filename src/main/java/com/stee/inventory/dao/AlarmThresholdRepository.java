package com.stee.inventory.dao;

import com.stee.sel.inventory.AlarmThreshold;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by SerryMiano on 2017/1/10.
 */
public interface AlarmThresholdRepository extends JpaRepository<AlarmThreshold, Integer> {

    AlarmThreshold findByAlarmThresholdId(Integer id);
}
