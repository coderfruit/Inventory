package com.stee.inventory.repository;

import com.stee.sel.asm.LifetimeTrackingConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by SerryMiano on 2017/1/10.
 */
public interface LifetimeRepository extends JpaRepository<LifetimeTrackingConfig, Integer> {
    LifetimeTrackingConfig findByLuminaireId(String luminaireId);
}
