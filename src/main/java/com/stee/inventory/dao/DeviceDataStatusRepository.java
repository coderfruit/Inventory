package com.stee.inventory.dao;

import com.stee.inventory.entity.AlarmThreshold;
import com.stee.inventory.entity.sel.DeviceDataStatsEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by SerryMiano on 2017/1/10.
 */
public interface DeviceDataStatusRepository extends JpaRepository<DeviceDataStatsEntity, Integer>,JpaSpecificationExecutor<DeviceDataStatsEntity> {
    List<DeviceDataStatsEntity> findByDeviceId(Pageable pageable, String devicdId);
}
