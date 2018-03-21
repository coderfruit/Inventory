package com.stee.inventory.dao;

import com.stee.sel.inventory.LampPoleModelEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface LampPoleModelDao extends JpaRepository<LampPoleModelEntity,String>,JpaSpecificationExecutor<LampPoleModelEntity> {
    List<LampPoleModelEntity> findByLampPoleModelIdLike(String id);

    Page<LampPoleModelEntity> findAll(Specification<LampPoleModelEntity> var1,Pageable pageable);

    LampPoleModelEntity findByLampPoleModelId(String id);
}
