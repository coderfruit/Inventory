package com.stee.inventory.service.impl;

import com.google.common.collect.Lists;
import com.stee.inventory.Exception.ServiceException;
import com.stee.inventory.dao.DeviceModelDao;
import com.stee.inventory.entity.DeviceModel;
import com.stee.inventory.entity.LuminaireQueryBean;
import com.stee.inventory.entity.Result;
import com.stee.inventory.entity.sel.DeviceModelEntity;
import com.stee.inventory.service.IDeviceModelService;
import com.stee.sel.common.ResultData;
import com.stee.sel.constant.ResponseCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceModelServiceImpl implements IDeviceModelService{

    @Resource
    private DeviceModelDao deviceModelDao;

    @Override
    public ResultData<DeviceModelEntity> getAll() {
        ResultData<DeviceModelEntity> resultData = new ResultData<>();
        try {
            List<DeviceModelEntity> all = deviceModelDao.findAll();
            resultData.setData(all);
            resultData.setStatus(ResponseCode.SUCCESS.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setStatus(ResponseCode.FAILED.getCode());
        }
        return resultData;
    }

    @Override
    public String save(DeviceModelEntity config) {
        if (null != config) {
            try {
                deviceModelDao.save(config);
                return ResponseCode.SUCCESS.getCode();
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseCode.FAILED.getCode();
            }
        } else {
            return ResponseCode.ERROR_PARAM.getCode();
        }
    }

    @Override
    public boolean isIdExits(String id) {
        return deviceModelDao.exists(id);
    }

    @Override
    public String delete(String id) {
        try {
            deviceModelDao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseCode.FAILED.getCode();
        }
        return ResponseCode.SUCCESS.getCode();
    }

    @Override
    public ResultData<DeviceModelEntity> findByModelIdLike(LuminaireQueryBean query) {
        ResultData<DeviceModelEntity> resultData = new ResultData<>();
        List<DeviceModelEntity> list = Lists.newArrayList();
        List<DeviceModelEntity> configs = Lists.newArrayList();
        if (null == query) {
            try {
                configs =  deviceModelDao.findAll();
            } catch (Exception e) {
                e.printStackTrace();
                resultData.setStatus(ResponseCode.FAILED.getCode());
            }
        } else {
            try {
                configs = deviceModelDao.findAll(where(query.getModelId(), query.getManufacturer(), query.getLampType(), query.getControlProtocol(), query.getRatedWattStart(), query.getRatedWattEnd(), query.getDescription()));
            } catch (Exception e) {
                e.printStackTrace();
                resultData.setStatus(ResponseCode.FAILED.getCode());
            }
        }

//        if (null != configs && !configs.isEmpty()) {
//            for (DeviceModel luminaireModelConfig : configs) {
//                String modelId = luminaireModelConfig.getDeviceModelId();
//                LifetimeTrackingConfig lifetimeTrackingConfig = lifetimeRepo.findByLuminaireId(modelId);
//                if (null != lifetimeTrackingConfig) {
//                    luminaireModelConfig.setLifeTimeExits(true);
//                } else {
//                    luminaireModelConfig.setLifeTimeExits(false);
//                }
//                list.add(luminaireModelConfig);
//            }
//        }

        resultData.setData(configs);
        resultData.setStatus(ResponseCode.SUCCESS.getCode());
        return resultData;
    }

    //    @Override
//    public Result<Page<DeviceModel>> getAll(Pageable pageable) {
//        try{
//            Page<DeviceModel> pageResult=deviceModelDao.findAll(pageable);
//            return new Result<Page<DeviceModel>>().success(pageResult);
//        }catch (Exception e){
//            throw new ServiceException("get device model configuration failed, error message:"+e.getMessage());
//        }
//    }
//
//    @Override
//    public Result save(DeviceModel config) {
//        try{
//            deviceModelDao.save(config);
//            return Result.success();
//        }catch (Exception e){
//            throw new ServiceException("save device model configuration failed, error message:"+e.getMessage());
//        }
//    }
//
//    @Override
//    public Result<Boolean> isIdExits(String id) {
//        try{
//            boolean isExists=deviceModelDao.exists(id);
//            return new Result<Boolean>().success(isExists);
//        }catch (Exception e){
//            throw new ServiceException("check device model configuration by id failed, error message:"+e.getMessage());
//        }
//    }
//
//    @Override
//    public Result delete(String id) {
//        try{
//            deviceModelDao.delete(id);
//            return Result.success();
//        }catch (Exception e){
//            throw new ServiceException("delete device model configuration failed, error message:"+e.getMessage());
//        }
//    }
//
//    @Override
//    public Result<Page<DeviceModel>> findByModelIdLike(LuminaireQueryBean query,Pageable pageable) {
//        try{
//            Page<DeviceModel> pageResult=deviceModelDao.findAll(where(query.getModelId(), query.getManufacturer(), query.getLampType(), query.getControlProtocol(), query.getRatedWattStart(), query.getRatedWattEnd(), query.getDescription()),pageable);
//            return new Result<Page<DeviceModel>>().success(pageResult);
//        }catch (Exception e){
//            throw new ServiceException("get device model configuration failed, error message:"+e.getMessage());
//        }
//    }
//
    private Specification<DeviceModelEntity> where(final String modelId, final String manufacturer, final String lampType, final String controlProtocol, final Double ratedWattStart, final Double ratedWattEnd, final String desc) {
        return new Specification<DeviceModelEntity>() {

            @Override
            public Predicate toPredicate(Root<DeviceModelEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (null != modelId && !modelId.equals("")) {
                    predicates.add(cb.like(root.<String>get("deviceModelId"), "%" + modelId + "%"));
                }
                if (null != manufacturer && !manufacturer.equals("")) {
                    predicates.add(cb.like(root.<String>get("manufacturer"), "%" + manufacturer + "%"));
                }
                if (null != lampType && !lampType.equals("")) {
                    predicates.add(cb.like(root.<String>get("lampType"), "%" + lampType + "%"));
                }
                if (null != controlProtocol && !controlProtocol.equals("")) {
                    predicates.add(cb.like(root.<String>get("controlProtocol"), "%" + controlProtocol + "%"));
                }
                if (null != ratedWattStart && null!= ratedWattEnd) {
                    predicates.add(cb.between(root.<Double>get("ratedWatt"), ratedWattStart, ratedWattEnd));
                }
                if (null != desc && !desc.equals("")) {
                    predicates.add(cb.like(root.<String>get("description"), "%" + desc + "%"));
                }
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
    }
}
