package com.stee.inventory.repository;

import com.stee.sel.asm.PoleModelConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by SerryMiano on 2017/1/10.
 */
public interface PoleRepository extends JpaRepository<PoleModelConfig, Integer> {
    PoleModelConfig findByName(String name);
}
