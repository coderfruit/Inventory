package com.stee.inventory.dao;

import com.stee.inventory.entity.LampPole;
import com.stee.inventory.entity.LampPoleModel;
import com.stee.inventory.entity.sel.LampPoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface LampPoleDao extends JpaRepository<LampPoleEntity,String>{
        LampPoleEntity findByLampPoleId(String id);
}
