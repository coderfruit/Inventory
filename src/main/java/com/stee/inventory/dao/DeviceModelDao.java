package com.stee.inventory.dao;

import com.stee.inventory.entity.DeviceModel;
import com.stee.inventory.entity.sel.DeviceModelEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeviceModelDao extends JpaRepository<DeviceModelEntity,String>,JpaSpecificationExecutor<DeviceModelEntity>{
        Page<DeviceModel> findAll(Specification specification, Pageable pageable);
        DeviceModelEntity findByDeviceModelId(String id);

}
