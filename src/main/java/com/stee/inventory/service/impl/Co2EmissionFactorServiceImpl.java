package com.stee.inventory.service.impl;

import com.stee.inventory.Exception.ServiceException;
import com.stee.inventory.dao.Co2EmissionFactorDao;
import com.stee.inventory.entity.Co2EmissionFactor;
import com.stee.inventory.entity.Result;
import com.stee.inventory.service.ICo2EmissionFactorService;
import com.stee.inventory.utils.ConfigUtil;
import com.stee.inventory.utils.PropUtils;
import com.stee.sel.common.ResultData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Co2EmissionFactorServiceImpl implements ICo2EmissionFactorService {

    @Resource
    private Co2EmissionFactorDao iCarboDioxideDao;

    @Override
    public ResultData save(Co2EmissionFactor carboDioxide) {
        try{
            iCarboDioxideDao.save(carboDioxide);
            return ConfigUtil.ok();
        }catch(Exception e){
            return ConfigUtil.fail();
        }
    }

    @Override
    public ResultData update(Co2EmissionFactor carboDioxide) {
        try{
            iCarboDioxideDao.save(carboDioxide);
            return ConfigUtil.ok();
        }catch(Exception e){
            return ConfigUtil.fail();
        }
    }

    @Override
    public ResultData deleteCD(Integer id) {
        try{
            iCarboDioxideDao.delete(id);
            return ConfigUtil.ok();
        }catch(Exception e){
            return ConfigUtil.fail();
        }
    }

    @Override
    public ResultData getCDById(Integer id) {
        try{
            return ConfigUtil.okOnlyOne(iCarboDioxideDao.findByCo2EmissionFactorId(id));
        }catch(Exception e){
            return ConfigUtil.fail();
        }
    }

    @Override
    public ResultData getAllCD() {
        try{
            return ConfigUtil.okList(iCarboDioxideDao.findAll());
        }catch(Exception e){
            return ConfigUtil.fail();
        }
    }

    @Override
    public ResultData fuzzyQueryByName(String name) {
        try{
            StringBuffer sql = new StringBuffer("%").append(name).append("%");
            return ConfigUtil.okList(iCarboDioxideDao.findByAreaLike(name));
        }catch(Exception e){
            return ConfigUtil.fail();
        }
    }
//    @Override
//    public Result save(Co2EmissionFactor co2EmissionFactor) {
//        try{
//            co2EmissionFactorDao.save(co2EmissionFactor);
//            return Result.success();
//        }catch (Exception e){
//            throw new ServiceException("save co2 emission factor configuration to database failed, error message:"+e.getMessage());
//        }
//    }
//
//    @Override
//    public Result update(Co2EmissionFactor co2EmissionFactor) {
//        try{
//            co2EmissionFactorDao.save(co2EmissionFactor);
//            return Result.success();
//        }catch (Exception e){
//            throw new ServiceException("save co2 emission factor configuration to database failed, error message:"+e.getMessage());
//        }
//    }
//
//    @Override
//    public Result deleteCD(Integer id) {
//        try{
//            co2EmissionFactorDao.delete(id);
//            return Result.success();
//        }catch (Exception e){
//            throw new ServiceException("delete co2 emission factor configuration failed, error message:"+e.getMessage());
//        }
//    }
//
//    @Override
//    public Result<Co2EmissionFactor> getCDById(Integer id) {
//        try{
//            Co2EmissionFactor one = co2EmissionFactorDao.findByCo2EmissionFactorId(id);
//            return new Result<Co2EmissionFactor>().success(one);
//        }catch (Exception e){
//            throw new ServiceException("get co2 emission factor configuration failed, error message:"+e.getMessage());
//        }
//    }
//
//    @Override
//    public Result<Page<Co2EmissionFactor>> getAllCD(Pageable pageable) {
//        try{
//           Page<Co2EmissionFactor> pageResult=co2EmissionFactorDao.findAll(pageable);
//            return new Result<Page<Co2EmissionFactor>>().success(pageResult);
//        }catch (Exception e){
//            throw new ServiceException("get all co2 emission factor configuration failed, error message:"+e.getMessage());
//        }
//    }
//
//    @Override
//    public Result<Page<Co2EmissionFactor>> fuzzyQueryByName(String name,Pageable pageable) {
//        try{
//            Page<Co2EmissionFactor> all = co2EmissionFactorDao.findByAreaLike(name,pageable);
//            return new Result<Page<Co2EmissionFactor>>().success(all);
//        }catch (Exception e){
//            throw new ServiceException("get co2 emission factor configuration failed, error message:"+e.getMessage());
//        }
//    }
}
