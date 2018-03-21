package com.stee.inventory.service;

import com.stee.inventory.entity.PoleQueryBean;
import com.stee.sel.common.ResultData;
import com.stee.sel.inventory.LampPoleModelEntity;

public interface ILampPoleModelService {
//    Result<Page<LampPoleModel>> getAll(Pageable pageable);
//
//    Result save(LampPoleModel lampPoleModel);
//
//    Result<Boolean> isNameExits(String name);
//
//    Result delete(String id);
//
//    Result<Page<LampPoleModel>> findByQueryBean(PoleQueryBean name,Pageable pageable);
    ResultData<LampPoleModelEntity> getAll();

    String save(LampPoleModelEntity config);

    boolean isNameExits(String name);

    String delete(String id);

    ResultData<LampPoleModelEntity> findByQueryBean(PoleQueryBean query);
}
