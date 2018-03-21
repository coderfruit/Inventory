package com.stee.inventory.service;

import com.stee.inventory.entity.LuminaireQueryBean;
import com.stee.sel.common.ResultData;
import com.stee.sel.inventory.DeviceModelEntity;

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
