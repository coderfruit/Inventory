package com.stee.inventory.dao;

import com.stee.inventory.entity.LampPoleModel;
import com.stee.sel.asm.PoleModelConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface LampPoleModelDao extends JpaRepository<LampPoleModel,String>,JpaSpecificationExecutor<LampPoleModel> {
    List<LampPoleModel> findByLampPoleModelIdLike(String id);

    Page<LampPoleModel> findAll(Specification<LampPoleModel> var1,Pageable pageable);

    LampPoleModel findByLampPoleModelId(String id);
}
