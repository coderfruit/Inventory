package com.stee.inventory.service.impl;

import com.stee.inventory.Exception.ServiceException;
import com.stee.inventory.dao.AlarmThresholdRepository;
import com.stee.inventory.entity.AlarmThreshold;
import com.stee.inventory.entity.Result;
import com.stee.inventory.service.IAlarmThresholdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AlarmThresholdServiceImpl implements IAlarmThresholdService{

    @Resource
    private AlarmThresholdRepository repository;

    @Override
    public Result<AlarmThreshold> save(AlarmThreshold alarmThreshold) {
        try{
            AlarmThreshold threshold = repository.save(alarmThreshold);
            return new Result<AlarmThreshold>().success(threshold);
        } catch (Exception e){
            throw new ServiceException("save alarmThreshold failed !!! error message:"+e.getMessage());
        }
    }

    @Override
    public Result deleteById(Integer id) {
        try{
            repository.delete(id);
            return new Result().success();
        } catch (Exception e){
            throw new ServiceException("delete alarmThreshold failed !!! error message:"+e.getMessage());
        }
    }

    @Override
    public Result<AlarmThreshold> findAlarmThresholdById(Integer id) {
        try{
            AlarmThreshold byAlarmThresholdId = repository.findByAlarmThresholdId(id);
            return new Result<AlarmThreshold>().success(byAlarmThresholdId);
        } catch (Exception e){
            throw new ServiceException("find alarmThreshold by id failed !!! error message:"+e.getMessage());
        }
    }
}
