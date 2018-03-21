package com.stee.inventory.dao;

import com.stee.sel.alarm.DeviceAlarmsEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by SerryMiano on 2017/1/10.
 */
public interface DeviceAlarmsRepository extends JpaRepository<DeviceAlarmsEntity, String> {
    List<DeviceAlarmsEntity> findBySource(Pageable pageable, String source);
}
