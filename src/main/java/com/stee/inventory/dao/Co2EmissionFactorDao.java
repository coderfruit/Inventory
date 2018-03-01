package com.stee.inventory.dao;

import com.stee.inventory.entity.Co2EmissionFactor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Co2EmissionFactorDao extends JpaRepository<Co2EmissionFactor,Integer>{

        Co2EmissionFactor findByCo2EmissionFactorId(Integer id);

        @Query("select co2emissio0_ from Co2EmissionFactor co2emissio0_ where co2emissio0_.area like concat('%',?1,'%')")
        List<Co2EmissionFactor> findByAreaLike(String area);
//        Page<Co2EmissionFactor> findByAreaLike(String area, Pageable pageable);


}
