package com.stee.inventory.repository;


import com.stee.sel.lfm.EnergyHist;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by SerryMiano on 2017/1/17.
 */
public interface EnergyUsageHistRepository extends JpaRepository<EnergyHist, Integer> {
}
