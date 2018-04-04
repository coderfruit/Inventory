package com.stee.inventory.service.impl;

import com.lowagie.text.DocumentException;
import com.stee.inventory.dao.*;
import com.stee.inventory.dto.Result;
import com.stee.inventory.entity.DeviceControl;
import com.stee.inventory.entity.DeviceInfo;
import com.stee.inventory.entity.DeviceLocationInfo;
import com.stee.inventory.entity.RequestObject;
import com.stee.inventory.entity.sel.LampInfoDetail;
import com.stee.inventory.service.IDeviceService;
import com.stee.inventory.utils.Utils;
import com.stee.sel.alarm.DeviceAlarmsEntity;
import com.stee.sel.common.ResultData;
import com.stee.sel.constant.ResponseCode;
import com.stee.sel.field.ResultFieldConfig;
import com.stee.sel.gis.GeoZoneEntity;
import com.stee.sel.inventory.DeviceInfoEntity;
import com.stee.sel.inventory.DeviceModelEntity;
import com.stee.sel.inventory.LampPoleEntity;
import com.stee.sel.inventory.LampPoleModelEntity;
import com.stee.sel.report.DeviceDataStatsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DeviceServiceImpl implements IDeviceService{

    @Resource
    private DeviceDao repository;
    @Resource
    private DeviceModelDao deviceModelDao;
    @Resource
    private LampPoleDao lampPoleDao;
    @Resource
    private GeoZoneRepository geoZoneRepository;
    @Resource
    private DeviceDataStatusRepository deviceStatusRepository;
    @Resource
    private LampPoleModelDao lampPoleModelDao;
    @Resource
    private DeviceAlarmsRepository deviceAlarmsRepository;


    @Override
    public DeviceInfoEntity update(DeviceInfoEntity info) {
        try {
            repository.save(info);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return info;
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public ResultData<DeviceInfoEntity> getAll() {
        ResultData<DeviceInfoEntity> data = new ResultData<>();
        try {
            data.setData(repository.findAll());
        } catch (Exception e) {
            data.setStatus(ResponseCode.FAILED.getCode());
        }
        data.setStatus(ResponseCode.SUCCESS.getCode());
        return data;
    }

    @Override
    public String deleteByGgz(Integer geoZoneId) {
        try {
            List<DeviceInfoEntity> findByGeoZoneId = repository.findByGeozoneId(geoZoneId);
            if (!findByGeoZoneId.isEmpty()) {
                findByGeoZoneId.forEach(lampInfo -> {
                    lampInfo.setGeozoneId(null);
                    repository.save(lampInfo);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseCode.FAILED.getCode();
        }
        return ResponseCode.SUCCESS.getCode();
    }

    @Override
    public String updateGz(Map<String, Integer> map) {
        try {
            Map<String, Integer> tempMap = new HashMap<>();
            tempMap.putAll(map);
            Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
            List<DeviceInfoEntity> compareLamps = new ArrayList<>();
            if (iterator.hasNext()) {
                Map.Entry<String, Integer> next = iterator.next();
                Integer value = next.getValue();
                List<DeviceInfoEntity> byGeoZoneId = this.getByGeoZoneId(value);
                if (null != byGeoZoneId) {
                    if (byGeoZoneId.size() > map.size()) {
                        Iterator<Map.Entry<String, Integer>> iterator2 = tempMap.entrySet().iterator();
                        while (iterator2.hasNext()) {
                            Map.Entry<String, Integer> next2 = iterator2.next();
                            String key = next2.getKey();
                            for (DeviceInfoEntity lampInfo : byGeoZoneId) {
                                String id = lampInfo.getDeviceId();

                                if (key.equals(id)) {
                                    System.out.println(id);
                                    compareLamps.add(lampInfo);
                                }
                            }
                        }
                        byGeoZoneId.removeAll(compareLamps);
                        System.out.println(byGeoZoneId);
                        for (DeviceInfoEntity lampInfo : byGeoZoneId) {
                            lampInfo.setGeozoneId(null);
                        }
                        repository.save(byGeoZoneId);
                    } else {
                        map.forEach((k, v) -> {
                            DeviceInfoEntity findOne = repository.findOne(k);
                            findOne.setGeozoneId(v);
                            repository.save(findOne);
                        });
                    }
                }
            } else {
                return ResponseCode.ERROR_PARAM.getCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseCode.FAILED.getCode();
        }
        return ResponseCode.SUCCESS.getCode();
    }

    @Override
    public void updatePolling(List<DeviceInfoEntity> list) {
        if (!list.isEmpty()) {
            try {
                repository.save(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<DeviceInfoEntity> getByGeoZoneId(Integer geoZoneId) {
        List<DeviceInfoEntity> lampList =  repository.findByGeozoneId(geoZoneId);
        lampList.forEach((lamp)->{
            DeviceModelEntity deviceModel = deviceModelDao.findByDeviceModelId(lamp.getDeviceModelId());
            lamp.setControlProtocol(deviceModel.getControlProtocol());
        });
        return lampList;
    }

    @Override
    public Page<DeviceInfoEntity> getAllByFilter(Integer pageNo, Integer pageSize, String type, String name) {
        Page<DeviceInfoEntity> page = null;
         Pageable pageable= new PageRequest(pageNo-1, pageSize);
        try {
            page = repository.findAll(where(type,name), pageable);
            List<String> ids = new ArrayList<String>();
            page.getContent().forEach(deviceInfoEntity -> {
            	ids.add(deviceInfoEntity.getLampPoleId());
            });
            List<LampPoleEntity> lampPoleList = lampPoleDao.findByPoleList(ids);
            Map<String,String> lampMap = new HashMap<String,String>();
            for(LampPoleEntity lp : lampPoleList){
            	lampMap.put(lp.getLampPoleId(), lp.getLampPoleModelId());
            }
            page.getContent().forEach(deviceInfoEntity -> {
                deviceInfoEntity.setLampPoleModelId(lampMap.get(deviceInfoEntity.getLampPoleId()));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return page;
    }

    @Override
    public boolean isIdExits(String id) {
        if (null == id || id.equals("")) {
            return false;
        }else {
            return repository.exists(id);
        }
    }

    @Override
    public DeviceModelEntity findLuminaireModelConfig(String id) {
        DeviceModelEntity DeviceModel = deviceModelDao.findByDeviceModelId(id);
        return DeviceModel;
    }

    @Override
    public Result findPoleOrLuminaire(RequestObject obj) {
        Result result = new Result();
        try {
            System.out.println(obj);

            DeviceInfoEntity lampInfo = repository.findByDeviceId(obj.getLampId());
            LampPoleEntity lampPole = lampPoleDao.findByLampPoleId(lampInfo.getLampPoleId());
            lampInfo.setLampPoleModelId(lampPole.getLampPoleModelId());
            DeviceModelEntity modelConfig = null;
            if(lampInfo.getDeviceModelId()!=null){
                modelConfig = deviceModelDao.findByDeviceModelId(lampInfo.getDeviceModelId());
            }
//			PoleModelConfig poleModelConfig = poleRepository.findByName(lampInfo.getLampPole().getModelId());
            GeoZoneEntity gzone = null;
            if(lampInfo.getGeozoneId()!=null){
                gzone = geoZoneRepository.findByGeozoneId(lampInfo.getGeozoneId());
            }
            List<DeviceAlarmsEntity> pageAlarm = getLimAlarmInfo(obj);
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date date = lampInfo.getInstallationDate();
            String installedDate = null;
            if(date!=null){
                installedDate = format.format(date);
            }
            String picture = "";
            if(modelConfig!=null){
            	picture = modelConfig.getPicture();
            }
            DeviceInfo deviceInfo = new DeviceInfo();
            deviceInfo.setDeviceInfo(lampInfo.getName(),
                    lampInfo.getDeviceId(),
                    lampInfo.getDeviceModelId(),
                    lampInfo.getLampPoleId(),
//                    lampInfo.getLampPole().getModelId(),
                    lampPole.getLampPoleModelId(),
                    installedDate,
                    picture);
            DeviceLocationInfo deviceLocationInfo = new DeviceLocationInfo();
            deviceLocationInfo.setDeviceLocationInfo(lampInfo.getGeozoneId(),
                    lampInfo.getLatitude(),
                    lampInfo.getLongitude(),
                    lampInfo.getCountry(),
                    lampInfo.getState(),
//                    gzone.getCountry(),
//                    gzone.getCountry(),
//                    lampInfo.getAddress()
                    //这个地方有问题，暂时这样处理
                    lampInfo.getStreet()
            );
            DeviceControl dc = new DeviceControl();
            dc.setDeviceId(lampInfo.getDeviceId());
            dc.setControlMode(lampInfo.getControlMode());
            dc.setLampLevel(lampInfo.getLevelFeedback());
            dc.setLampSwitch(lampInfo.isSwitchFeedback());
            dc.setControlProtocol(modelConfig.getControlProtocol());
            List<Object> list = new ArrayList<Object>();
            list.add(deviceInfo);
            list.add(deviceLocationInfo);
            list.add(pageAlarm);
            list.add(dc);
            result.setStatusCode("000000");
            result.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatusCode("999999");
            result.setMessage("Query Exception");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Result batchDelete(List<String> ids) {
        Result result = new Result();
        try {
            List<DeviceInfoEntity> list = repository.findAll(ids);
            repository.deleteInBatch(list);
            result.setStatusCode("000000");
            result.setMessage("Batch deletion success");
        } catch (Exception e) {
            result.setStatusCode("000000");
            result.setMessage("Batch deletion failed");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Result updateDeviceInfo(DeviceInfo deviceInfo) {
        Result result = new Result();
        try {
            DeviceInfoEntity info = repository.findOne(deviceInfo.getDeviceId());
           
            if(info==null){
                result.setStatusCode("999999");
                result.setMessage("Device ID does not exist");
                return result;
            }
            info.setName(deviceInfo.getDeviceName());
            info.setDeviceModelId(deviceInfo.getLuminaireModelId());
            info.setLampPoleId(deviceInfo.getLampPoleId());
//            info.getLampPole().setModelId(deviceInfo.getLampPoleModelId());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String installedDate = deviceInfo.getInstalledDate();
            if(installedDate!=null&&installedDate.length()>0){
                info.setInstallationDate(format.parse(installedDate));
            }
            repository.save(info);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatusCode("999999");
            result.setMessage("Save exception");
            return result;
        }
        try {
        	 LampPoleEntity lampPole = lampPoleDao.findByLampPoleId(deviceInfo.getLampPoleId());
        	 lampPole.setLampPoleModelId(deviceInfo.getLampPoleModelId());
        	 lampPoleDao.save(lampPole);
		} catch (Exception e) {
            e.printStackTrace();
            result.setStatusCode("999999");
            result.setMessage("Lamp Pole Model ID OR Lamp Pole ID Not Exist");
            return result;
		}
        result.setStatusCode("000000");
        result.setMessage("Save success");
        return result;
    }

    @Override
    public Result updateDeviceInfo(DeviceLocationInfo deviceLocationInfo) {
        Result result = new Result();
        try {
            DeviceInfoEntity info = repository.findOne(deviceLocationInfo.getDeviceId());
            if(info==null){
                result.setStatusCode("999999");
                result.setMessage("Device ID does not exist");
                return result;
            }
//            info.setAddress(deviceLocationInfo.getAddress());
            info.setGeozoneId(deviceLocationInfo.getGeoZone());
            info.setLatitude(deviceLocationInfo.getLatitude());
            info.setLongitude(deviceLocationInfo.getLongitude());
            info.setCountry(deviceLocationInfo.getCountry());
            info.setState(deviceLocationInfo.getCity());
            info.setStreet(deviceLocationInfo.getAddress());
            repository.save(info);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatusCode("999999");
            result.setMessage("Save exception");
            return result;
        }
        result.setStatusCode("000000");
        result.setMessage("Save success");
        return result;
    }

    @Override
    public boolean findLampStatus(String deviceId) {
        boolean flag = false;
        try {
            DeviceInfoEntity lampInfo = repository.getOne(deviceId);
            //这个地方不知道怎么处理，暂时这样
            if(lampInfo.getLevelFeedback()!=null){
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }

        return flag;
    }

    @Override
    public Map<String, List<String>> getFieldConfig(String appactionName) {
        Map<String, List<String>> mapfield = new HashMap<String, List<String>>();
        mapfield.put(appactionName, ResultFieldConfig.fieldMap.get(appactionName));
        return mapfield;
    }

    @Override
    public Result findLuminaireReport(RequestObject obj) {
        if((obj.getStartDate()==null||obj.getStartDate().length()<=0)&&(obj.getEndDate()==null||obj.getEndDate().length()<=0)){
            obj.setStartDate("2016-01-01");
            obj.setEndDate("2018-12-01");
        }
        Result result = new Result();
        result.setUnit(getUnits(obj.getType()));

            List<DeviceDataStatsEntity> deviceList = null;
            try {
                deviceList = deviceStatusRepository.findAll(
                        new Specification<DeviceDataStatsEntity>() {
                            @Override
                            public Predicate toPredicate(Root<DeviceDataStatsEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                                List<Predicate> list = new ArrayList<Predicate>();

                                if (obj.getLampId()!=null&&obj.getLampId().length()>0) {
                                    list.add(cb.like(root.get("deviceId").as(String.class), "%" + obj.getLampId() + "%"));
                                }
                                if (obj.getStartDate()!=null&&obj.getStartDate().length()>0) {
                                    //大于或等于传入时间
                                    list.add(cb.greaterThanOrEqualTo(root.get("createTime").as(String.class), obj.getStartDate()));
                                }

                                if (obj.getEndDate()!=null&&obj.getEndDate().length()>0) {
                                    //小于或等于传入时间
                                    list.add(cb.lessThanOrEqualTo(root.get("createTime").as(String.class), obj.getEndDate()));
                                }
                                if (obj.getType()!=null&&obj.getType().length()>0) {
                                    list.add(cb.like(root.get("dataType").as(String.class), "%" + obj.getType() + "%"));
                                }
                                Predicate[] p = new Predicate[list.size()];
                                return cb.and(list.toArray(p));
                            }
                        });
                Integer pageNumber = obj.getPageNo();
                Integer pageSize = obj.getPageSize();
                List<DeviceDataStatsEntity> reList = new ArrayList<DeviceDataStatsEntity>();
                Integer totalNumber = deviceList.size();
                Integer sPage = (pageNumber-1)*pageSize;
                Integer ePage = sPage+pageSize;
                if(ePage>totalNumber){
                    ePage = totalNumber;
                }
                for(int i=sPage;i<ePage;i++){
                    reList.add(deviceList.get(i));
                }
                result.setPageData(pageNumber,pageSize,totalNumber,reList);
                result.setStatusCode("000000");
                result.setMessage("Query success");
                result.setReportData(deviceList);
            } catch (Exception e) {
                result.setStatusCode("999999");
                result.setMessage("Query failure");
                e.printStackTrace();
            }

        return result;
    }

    @Override
    public Result<String> exportDevcieStatusReport(RequestObject obj) {
        Result<String> result = new Result<>();
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);//日期国际化
        if(obj.getExportType().equals("csv")){
                /**查询数据**/
                List<DeviceDataStatsEntity> dsrList = null;
                List<String> dataList = new ArrayList<>();
                Double totalData = 0.0;
                // 查询数据，用于创建CSV文件
                try {
                    dsrList = findDeviceStatusReport(obj);
                    dataList.add(","+obj.getName());
                    dataList.add(","+",Date Type:"+","+obj.getName());
                    dataList.add(","+",Start Date:"+","+obj.getStartDate());
                    dataList.add(","+",End Date:"+","+obj.getEndDate());
                    // 根据不同的criteria加不同的列名
                    dataList.add(","+",Export Time:"+","+obj.getExportTime());

                    dataList.add(",,,");
                    dataList.add("Date,"+obj.getName()+"("+getUnits(obj.getType())+")");
                    for (DeviceDataStatsEntity e : dsrList) {
                        dataList.add(e.getFormatDate() +","+e.getData());
                        totalData += e.getData();
                    }
                    dataList.add(",,,");
                    dataList.add(",,,");
                    dataList.add(",,"+obj.getName()+" Grand Total("+dsrList.size()+" Records):"+totalData+""+getUnits(obj.getType()));
                    // 创建CSV文件
                    Utils.createCsv(dataList,obj.getName() +".csv");
                    result.setStatusCode("000000");
                    result.setMessage("success");
                } catch (Exception e) {
                    result.setStatusCode("999999");
                    result.setMessage("fail");
//                    logger.error("create csv file failed!!!  Error Message:{}", e.getMessage());
                    return result;
                }

        }else if(obj.getExportType().equals("pdf")){

                List<DeviceDataStatsEntity> dsrList = null;
                Map<String, Object> freemarkerData = new HashMap<String, Object>();
                Double totalData = 0.0;
                //利用前端传的base64编码生成图片
                try {
                    Utils.createPicture(obj.getBase64Info());
                } catch (IOException e) {
//                    logger.error("create picture failed!!  Error Message:{}", e.getMessage());
                    result.setStatusCode("999999");
                    result.setMessage("fail");
                    return result;
                }
                //查询数据，用于渲染HTML页面
                try {
                    dsrList = findDeviceStatusReport(obj);
                } catch (Exception e) {
//                    logger.error(">>Get Device Status Report Failed!!  Error Message:{}", e.getMessage());
                    result.setStatusCode("999999");
                    result.setMessage("fail");
                    return result;
                }
                /***
                 * 时间国际化
                 */
                for (DeviceDataStatsEntity e : dsrList) {
                    totalData += e.getData();
                }
//                obj.setExportTime(df.format(new Date()));//导出时间
                freemarkerData.put("DSRCondition", obj);
                freemarkerData.put("DSRReport", dsrList);
                freemarkerData.put("type", obj.getType());
                freemarkerData.put("totalItem", dsrList.size());
                freemarkerData.put("totalData", totalData);
                freemarkerData.put("unit", getUnits(obj.getType()));
                String content = null;
                //freemarker渲染页面，根据不同的criteria选择不同的模板
                content = Utils.freeMarkerRender(freemarkerData, "DeviceStatusReport.html");
                //flying saucer生成PDF
                try {
                    Utils.createPdf(content);
                    result.setStatusCode("000000");
                    result.setMessage("success");
                } catch (DocumentException | IOException e) {
//                    logger.error("create Pdf failed!!  Error Message:{}", e.getMessage());
                    result.setStatusCode("999999");
                    result.setMessage("fail");
                    return result;
                }
            }

        return result;
    }

    private List<DeviceDataStatsEntity>  findDeviceStatusReport(RequestObject obj){
        List<DeviceDataStatsEntity> deviceList = null;
        try {
            deviceList = deviceStatusRepository.findAll(
                    new Specification<DeviceDataStatsEntity>() {
                        @Override
                        public Predicate toPredicate(Root<DeviceDataStatsEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                            List<Predicate> list = new ArrayList<Predicate>();

                            if (obj.getLampId()!=null&&obj.getLampId().length()>0) {
                                list.add(cb.like(root.get("deviceId").as(String.class), "%" + obj.getLampId() + "%"));
                            }
                            if (obj.getStartDate()!=null&&obj.getStartDate().length()>0) {
                                //大于或等于传入时间
                                list.add(cb.greaterThanOrEqualTo(root.get("createTime").as(String.class), obj.getStartDate()));
                            }

                            if (obj.getEndDate()!=null&&obj.getEndDate().length()>0) {
                                //小于或等于传入时间
                                list.add(cb.lessThanOrEqualTo(root.get("createTime").as(String.class), obj.getEndDate()));
                            }
                            if (obj.getType()!=null&&obj.getType().length()>0) {
                                list.add(cb.like(root.get("dataType").as(String.class), "%" + obj.getType() + "%"));
                            }
                            Predicate[] p = new Predicate[list.size()];
                            return cb.and(list.toArray(p));
                        }
                    });
        }catch (Exception e) {
//            logger.error(">>Method->findDeviceStatusReport Exception:", e.getMessage());
            e.printStackTrace();
        }
        return deviceList;
    }
    //	public static void main(String[] args) {
//		System.out.println(getUnits("8"));
//	}
    private static String getUnits(String type){
        String unit = "";
        switch (type) {
            case "1": unit = "KWH"; break;
            case "2": unit = "H"; break;
            case "3": unit = "mA"; break;
            case "4": unit = "V"; break;
            case "5": unit = "W"; break;
            case "6": unit = "Var"; break;
            case "7": unit = ""; break;
            case "8": unit = "℃"; break;
            case "9": unit = "VA"; break;
            default: unit = "";break;
        }
        return unit;
    }

//    @Override
//    public Result<DeviceInfoEntity> update(DeviceInfoEntity info) {
//        try{
//            DeviceInfoEntity device = deviceDao.save(info);
//            return new Result<DeviceInfoEntity>().success(device);
//        }catch (Exception e){
//            throw new ServiceException("update device failed, error message:"+e.getMessage());
//        }
//    }
//
//    @Override
//    public Result delete(String id) {
//        try{
//            deviceDao.delete(id);
//            return Result.success();
//        }catch (Exception e){
//            throw new ServiceException("delete device failed, error message:"+e.getMessage());
//        }
//    }
//
//    @Override
//    public Result<List<DeviceInfoEntity>> getAll() {
//        try{
//            List<DeviceInfoEntity> all = deviceDao.findAll();
//            return new Result<List<DeviceInfoEntity>>().success(all);
//        }catch (Exception e){
//            throw new ServiceException("get all device failed, error message:"+e.getMessage());
//        }
//    }
//
//    @Override
//    public Result deleteByGgz(String geoZoneId) {
//        try{
//            List<DeviceInfoEntity> findByGeoZoneId = deviceDao.findByGeoZoneId(geoZoneId);
//            if (!findByGeoZoneId.isEmpty()) {
//                findByGeoZoneId.forEach(device -> {
//                    device.setGeozoneId(null);
//                    deviceDao.save(device);
//                });
//            }
//            return Result.success();
//        }catch (Exception e){
//            throw new ServiceException("delete by Ggz failed, error message:"+e.getMessage());
//        }
//    }
//
//    @Override
//    public Result updateGz(Map<String, Integer> map) {
//        try {
//            Map<String, Integer> tempMap = new HashMap<>();
//            tempMap.putAll(map);
//            Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
//            List<DeviceInfoEntity> compareLamps = new ArrayList<>();
//            if (iterator.hasNext()) {
//                Map.Entry<String, Integer> next = iterator.next();
//                Integer value = next.getValue();
//                List<DeviceInfoEntity> byGeoZoneId = this.getByGeoZoneId(value).getData();
//                if (null != byGeoZoneId) {
//                    if (byGeoZoneId.size() > map.size()) {
//                        Iterator<Map.Entry<String, Integer>> iterator2 = tempMap.entrySet().iterator();
//                        while (iterator2.hasNext()) {
//                            Map.Entry<String, Integer> next2 = iterator2.next();
//                            String key = next2.getKey();
//                            for (DeviceInfoEntity device : byGeoZoneId) {
//                                String id = device.getDeviceId();
//
//                                if (key.equals(id)) {
//                                    compareLamps.add(device);
//                                }
//                            }
//                        }
//                        byGeoZoneId.removeAll(compareLamps);
//                        for (DeviceInfoEntity device : byGeoZoneId) {
//                            device.setGeozoneId(null);
//                        }
//                        deviceDao.save(byGeoZoneId);
//                    } else {
//                        map.forEach((k, v) -> {
//                            DeviceInfoEntity findOne = deviceDao.findOne(k);
//                            findOne.setGeozoneId(v);
//                            deviceDao.save(findOne);
//                        });
//                    }
//                }
//            }
//            return Result.success();
//        } catch (Exception e) {
//            throw new ServiceException("delete by Ggz failed, error message:"+e.getMessage());
//        }
//    }
//
//    @Override
//    public Result updatePolling(List<DeviceInfoEntity> list) {
//            if (list.isEmpty()) {
//                try {
//                    deviceDao.save(list);
//                } catch (Exception e) {
//                    throw new ServiceException("update polling failed, error message:"+e.getMessage());
//                }
//            }
//        return Result.success();
//    }
//
//    @Override
//    public Result<List<DeviceInfoEntity>> getByGeoZoneId(Integer geoZoneId) {
//        try {
//            List<DeviceInfoEntity> lampList =  deviceDao.findByGeoZoneId(geoZoneId);
//            return new Result<List<DeviceInfoEntity>>().success(lampList);
//        } catch (Exception e) {
//            throw new ServiceException("get device by geoZoneId failed, error message:"+e.getMessage());
//        }
////        List<DeviceModel> LMClist = deviceModelDao.findAll();
////        Map<String,String> cp = new HashMap<String,String>();
////        for(DeviceModel eneity :LMClist){
////            cp.put(eneity.getDeviceModelId(), eneity.getControlProtocol());
////        }
////        for(Device device : lampList){
////            device.setControlProtocol(cp.get(device.getDeviceModelId()));
////        }
//    }
//
//    @Override
//    public Result<Page<DeviceInfoEntity>> getAllByFilter(Integer pageNo, Integer pageSize, String type, String name) {
//        Pageable pageable= new PageRequest(pageNo-1, pageSize);
//        try {
//            Page<DeviceInfoEntity> page = deviceDao.findAll(where(type,name), pageable);
//            return new Result<Page<DeviceInfoEntity>>().success(page);
//        } catch (Exception e) {
//            throw new ServiceException("get all by filter failed, error message:"+e.getMessage());
//        }
//    }
//
//    @Override
//    public Result<Boolean> isIdExits(String id) {
//        if (null == id || id.equals("")) {
//            return new Result<Boolean>().success(false);
//        }else {
//            try {
//                boolean isExists = deviceDao.exists(id);
//                return new Result<Boolean>().success(isExists);
//            } catch (Exception e){
//                throw new ServiceException("check id failed, error message:"+e.getMessage());
//            }
//        }
//    }
//
    @Override
    public LampInfoDetail findLampDetailInfo(String id) {
        LampInfoDetail infoDetail = new LampInfoDetail();
        if (null != id && !id.equals("")) {
            //因为前段传入的是name,所以根据名字查询,注意名字不能重复
            //按道理来讲应该传id进来，由于前端遗留问题，所以传入的是name
            DeviceInfoEntity info = repository.findByName(id);
            infoDetail.setLampInfo(info);
            DeviceModelEntity modelConfig = deviceModelDao.findByDeviceModelId(info.getDeviceModelId());
            DeviceModelEntity luminaire = new DeviceModelEntity();
            if (null != modelConfig) {
                luminaire.setDescription(modelConfig.getDescription());
                luminaire.setControlProtocol(modelConfig.getControlProtocol());
                luminaire.setLampType(modelConfig.getLampType());
                luminaire.setBuiltInLightSensor(modelConfig.isBuiltInLightSensor());
                luminaire.setManufacturer(modelConfig.getManufacturer());
                luminaire.setDeviceModelId(modelConfig.getDeviceModelId());
                luminaire.setBuiltInMotionSensor(modelConfig.isBuiltInMotionSensor());
                luminaire.setPollingMethod(modelConfig.isPollingMethod());
                luminaire.setRatedWatt(modelConfig.getRatedWatt());
            }
//            LifetimeTrackingConfig lifetimeTrackingConfig = lifetimeRepository.findByLuminaireId(info.getModuleId());
//            if (null != lifetimeTrackingConfig) {
//                luminaire.setLifeTime(lifetimeTrackingConfig.getLifetime());
//            }
            infoDetail.setLuminaire(luminaire);
            LampPoleEntity poleModelConfig = lampPoleDao.findByLampPoleId(info.getLampPoleId());
            LampPoleModelEntity lampPoleModel = lampPoleModelDao.findByLampPoleModelId(poleModelConfig.getLampPoleModelId());
//            if (null == poleModelConfig) {
//                LampPoleEntity poleModelConfig1 = new LampPoleEntity();
//                poleModelConfig1.set
//                poleModelConfig1.setDescription("");
//                poleModelConfig1.setHeight(null);
//                poleModelConfig1.setName("");
//                poleModelConfig1.setPicStr("");
//                poleModelConfig1.setPicture(null);
//                infoDetail.setPole(poleModelConfig1);
//            } else {
                infoDetail.setPole(poleModelConfig);
                infoDetail.setPoleModel(lampPoleModel);
//            }
        }
        return infoDetail;
    }
//
//    @Override
//    public Result findLuminaireModelConfig(String id) {
//        return null;
//    }
//
//    @Override
//    public Result findPoleOrLuminaire(RequestObject obj) {
//        return null;
//    }
//
//    @Override
//    public Result batchDelete(List<String> ids) {
//        try{
//            //先查询
//            List<DeviceInfoEntity> all = deviceDao.findAll(ids);
//            //再删除
//            deviceDao.deleteInBatch(all);
//            return Result.success();
//        }catch (Exception e){
//            throw new ServiceException("batch delete device failed, error message:"+e.getMessage());
//        }
//    }
//
//    @Override
//    public Result updateDeviceInfo(DeviceInfo deviceInfo) {
//        return null;
//    }
//
//    @Override
//    public Result updateDeviceInfo(DeviceLocationInfo deviceLocationInfo) {
//        return null;
//    }
//
//    @Override
//    public Result findLampStatus(String deviceId) {
//        return null;
//    }
//
//    @Override
//    public Result getFieldConfig(String appactionName) {
//        return null;
//    }
//
//    @Override
//    public Result findLuminaireReport(RequestObject obj) {
//        return null;
//    }
//
//    @Override
//    public Result exportDevcieStatusReport(RequestObject obj) {
//        return null;
//    }
//
    //动态拼接查询条件
    private Specification<DeviceInfoEntity> where(final String type, final String name) {
        return new Specification<DeviceInfoEntity>() {

            @Override
            public Predicate toPredicate(Root<DeviceInfoEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (name != null && name.length()>0) {

                    if(type != null && type.equals("1")){
                        predicates.add(cb.like(root.get("deviceId").as(String.class), "%" + name.trim() + "%"));
                    }
                    if(type != null && type.equals("2")){
                        predicates.add(cb.like( root.get("name").as(String.class), "%" + name.trim() + "%"));
                    }
                    if(type != null && type.equals("3")){
                        predicates.add(cb.like(root.get("deviceModelId").as(String.class), "%" + name.trim() + "%"));
                    }
                    if(type != null && type.equals("4")){
                        predicates.add(cb.like(root.get("geoZoneId").as(String.class), "%" + name.trim() + "%"));
                    }
//                    if(type != null && type.equals("5")){
//                        predicates.add(cb.like(root.get("address").as(String.class), "%" + name.trim() + "%"));
//                    }
                }
                query.where(predicates.toArray(new Predicate[predicates.size()]));
                return query.getRestriction();
            }
        };
    }

    public List<DeviceAlarmsEntity> getLimAlarmInfo(RequestObject obj) {
        System.out.println("showNum = [" + obj.getShowNum() + "], , lampId = [" + obj.getLampId() + "]");
        List<DeviceAlarmsEntity> listAlarm = null;
        Pageable pageable= new PageRequest(0, obj.getShowNum(),Sort.Direction.DESC,"createTime");
        try {
            listAlarm = deviceAlarmsRepository.findBySource(pageable, obj.getLampId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listAlarm;
    }
}
