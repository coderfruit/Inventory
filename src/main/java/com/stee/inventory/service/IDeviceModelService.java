package com.stee.inventory.service;

import com.stee.inventory.entity.Co2EmissionFactor;
import com.stee.inventory.entity.DeviceModel;
import com.stee.inventory.entity.LuminaireQueryBean;
import com.stee.inventory.entity.Result;
import com.stee.inventory.entity.sel.DeviceModelEntity;
import com.stee.sel.common.ResultData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDeviceModelService {
//    Result<Page<DeviceModel>> getAll(Pageable pageable);
//
//    Result save(DeviceModel config);
//
//    Result<Boolean> isIdExits(String id);
//
//    Result delete(String id);
//
//    Result<Page<DeviceModel>> findByModelIdLike(LuminaireQueryBean query, Pageable pageable);

    ResultData<DeviceModelEntity> getAll();

    String save(DeviceModelEntity config);

    boolean isIdExits(String id);

    String delete(String id);

    ResultData<DeviceModelEntity> findByModelIdLike(LuminaireQueryBean query);
}
