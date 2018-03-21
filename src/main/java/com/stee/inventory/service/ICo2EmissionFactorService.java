package com.stee.inventory.service;

import com.stee.sel.common.ResultData;
import com.stee.sel.inventory.Co2EmissionFactor;

public interface ICo2EmissionFactorService {
//    Result save(Co2EmissionFactor Co2EmissionFactor);
//
//    Result update(Co2EmissionFactor Co2EmissionFactor);
//
//    Result deleteCD(Integer id);
//
//    Result<Co2EmissionFactor> getCDById(Integer id);
//
//    Result<Page<Co2EmissionFactor>> getAllCD(Pageable pageable);
//
//    Result<Page<Co2EmissionFactor>> fuzzyQueryByName(String name, Pageable pageable);

    public ResultData save(Co2EmissionFactor carboDioxide);

    public ResultData update(Co2EmissionFactor carboDioxide);

    public ResultData deleteCD(Integer id);

    public ResultData getCDById(Integer id);

    public ResultData getAllCD();

    public ResultData fuzzyQueryByName(String name);
}
