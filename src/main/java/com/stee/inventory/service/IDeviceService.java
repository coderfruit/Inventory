package com.stee.inventory.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.stee.inventory.dto.Result;
import com.stee.inventory.entity.DeviceInfo;
import com.stee.inventory.entity.DeviceLocationInfo;
import com.stee.inventory.entity.RequestObject;
import com.stee.inventory.entity.sel.DeviceInfoEntity;
import com.stee.inventory.entity.sel.DeviceModelEntity;
import com.stee.inventory.entity.sel.LampInfoDetail;
import com.stee.sel.common.ResultData;

public interface IDeviceService {
//    Result<DeviceInfoEntity> update(DeviceInfoEntity info);
//
//    Result delete(String id);
//
//    Result<List<DeviceInfoEntity>> getAll();
//
//    Result deleteByGgz(String geoZoneId);
//
//    Result updateGz(Map<String, Integer> map);
//
//    Result updatePolling(List<DeviceInfoEntity> list);
//
//    Result<List<DeviceInfoEntity>> getByGeoZoneId(Integer geoZoneId);
//
//    Result<Page<DeviceInfoEntity>> getAllByFilter(Integer pageNo, Integer pageSize, String type, String name);
//
//    Result<Boolean> isIdExits(String id);
//
      LampInfoDetail findLampDetailInfo(String id);
//
//    Result findLuminaireModelConfig(String id);
//
//    Result findPoleOrLuminaire(RequestObject obj);
//
//    Result batchDelete(List<String> ids);
//
//    Result updateDeviceInfo(DeviceInfo deviceInfo);
//
//    Result updateDeviceInfo(DeviceLocationInfo deviceLocationInfo);
//
//    Result findLampStatus(String deviceId);
//
//    Result getFieldConfig(String appactionName);
//
//    Result findLuminaireReport(RequestObject obj);
//
//    Result exportDevcieStatusReport(RequestObject obj);

    DeviceInfoEntity update(DeviceInfoEntity info);

    void delete(String id);

    ResultData<DeviceInfoEntity> getAll();

    String deleteByGgz(Integer geoZoneId);

    String updateGz(Map<String, Integer> map);

    void updatePolling(List<DeviceInfoEntity> list);

    List<DeviceInfoEntity> getByGeoZoneId(Integer geoZoneId);
    /**
     * 获取所有Lamp信息
     * @param pageNo
     * @param pageSize
     * @param type
     * @param name
     * @return
     */
    Page<DeviceInfoEntity> getAllByFilter(Integer pageNo, Integer pageSize, String type, String name);

    boolean isIdExits(String id);

//    LampInfoDetail findLampDetailInfo(String id);

    DeviceModelEntity findLuminaireModelConfig(String id);

    com.stee.inventory.dto.Result findPoleOrLuminaire(RequestObject obj);
    /**
     * Batch Delete Lamp
     * @param ids
     * @return
     */
    com.stee.inventory.dto.Result batchDelete(List<String> ids);
    /**
     * update Lamp Info
     * @param deviceInfo
     * @return
     */
    com.stee.inventory.dto.Result updateDeviceInfo(DeviceInfo deviceInfo);
    /**
     * update Lamp Location Info
     * @param deviceLocationInfo
     * @return
     */
    com.stee.inventory.dto.Result updateDeviceInfo(DeviceLocationInfo deviceLocationInfo);
    /**
     * find Device
     * @param deviceId
     * @return
     */
    boolean findLampStatus(String deviceId);
    /**
     * 获取表头
     * @param appactionName
     * @return
     */
    public Map<String,List<String>> getFieldConfig(String appactionName);


    com.stee.inventory.dto.Result findLuminaireReport(RequestObject obj);
    /**
     * Export device status information
     * @param obj
     * @return
     */
    Result<String> exportDevcieStatusReport(RequestObject obj);
}
