package com.stee.inventory.service;

import com.stee.inventory.entity.Result;
import com.stee.sel.inventory.AlarmThreshold;

public interface IAlarmThresholdService {
    Result<AlarmThreshold> save(AlarmThreshold alarmThreshold);

    Result deleteById(Integer id);


    Result<AlarmThreshold> findAlarmThresholdById(Integer id);
}
