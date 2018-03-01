package com.stee.inventory.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.stee.inventory.entity.LampPoleModel;

public interface LampPoleModelDao extends JpaRepository<LampPoleModel,String>,JpaSpecificationExecutor<LampPoleModel> {
    List<LampPoleModel> findByLampPoleModelIdLike(String id);

    Page<LampPoleModel> findAll(Specification<LampPoleModel> var1,Pageable pageable);

    LampPoleModel findByLampPoleModelId(String id);
}
