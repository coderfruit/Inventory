package com.stee.inventory.dao;

import com.stee.inventory.entity.sel.DimmingGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by SerryMiano on 2017/1/10.
 */
public interface DimmingGroupRepository extends JpaRepository<DimmingGroupEntity, String> {
}
