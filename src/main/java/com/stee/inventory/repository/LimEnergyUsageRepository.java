package com.stee.inventory.repository;

import com.stee.sel.lim.reading.LiminaireEnergyHist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LimEnergyUsageRepository extends JpaRepository<LiminaireEnergyHist, String>, JpaSpecificationExecutor<LiminaireEnergyHist>{

}
