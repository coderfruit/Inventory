package com.stee.inventory.service;

import com.stee.inventory.entity.LampPoleModel;
import com.stee.inventory.entity.PoleQueryBean;
import com.stee.inventory.entity.Result;
import com.stee.sel.common.ResultData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
    ResultData<LampPoleModel> getAll();

    String save(LampPoleModel config);

    boolean isNameExits(String name);

    String delete(String id);

    ResultData<LampPoleModel> findByQueryBean(PoleQueryBean query);
}
