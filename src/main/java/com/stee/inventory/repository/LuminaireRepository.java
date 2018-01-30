package com.stee.inventory.repository;

import com.stee.sel.asm.LuminaireModelConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by SerryMiano on 2017/1/10.
 */
public interface LuminaireRepository extends JpaRepository<LuminaireModelConfig, Integer> {
    LuminaireModelConfig findByModelId(String modelId);
}
