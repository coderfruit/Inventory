package com.stee.inventory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stee.inventory.entity.sel.LampPoleEntity;

public interface LampPoleDao extends JpaRepository<LampPoleEntity,String>{
        LampPoleEntity findByLampPoleId(String id);
        @Query("select t from LampPoleEntity t where t.lampPoleId in(:poleIds)")
        List<LampPoleEntity> findByPoleList(@Param(value = "poleIds")List<String> poleIds);
}
