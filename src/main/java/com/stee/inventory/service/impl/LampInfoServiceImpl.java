package com.stee.inventory.service.impl;

import com.lowagie.text.DocumentException;
import com.stee.inventory.dto.*;
import com.stee.inventory.repository.*;
import com.stee.inventory.service.ILampInfoService;
import com.stee.inventory.utils.Utils;
import com.stee.sel.asm.LifetimeTrackingConfig;
import com.stee.sel.asm.LuminaireModelConfig;
import com.stee.sel.asm.PoleModelConfig;
import com.stee.sel.common.ResultData;
import com.stee.sel.constant.ResponseCode;
import com.stee.sel.field.ResultFieldConfig;
import com.stee.sel.gzm.GZone;
import com.stee.sel.lim.LampInfo;
import com.stee.sel.lim.reading.DeviceStatusReport;
import com.stee.sel.lim.reading.LiminaireAlarmInfo;
import com.stee.sel.lim.reading.LiminaireEnergyHist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/* Copyright (C) 2016, ST Electronics Info-Comm Systems PTE. LTD
 * All rights reserved.
 *
 * This software is confidential and proprietary property of 
 * ST Electronics Info-Comm Systems PTE. LTD.
 * The user shall not disclose the contents of this software and shall
 * only use it in accordance with the terms and conditions stated in
 * the contract or license agreement with ST Electronics Info-Comm Systems PTE. LTD.
 *
 * Project Name : STL_LIM
 * File Name    : LampInfoServiceImpl.java
 * Author       : Jerry
 * Created      : 2016年10月15日 上午11:00:31
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
@Service("lampInfoServiceImpl")
public class LampInfoServiceImpl implements ILampInfoService {
	@Autowired
	LampInfoRepository repository;

	@Autowired
    LuminaireRepository luminaireRepository;

	@Autowired
    LifetimeRepository lifetimeRepository;

	@Autowired
    PoleRepository poleRepository;
	
	@Autowired
	GeoZoneRepository geoZoneRepository;
	
	@Autowired
	LimAlarmInfoRepository limAlarmInfoRepository;
	
	@Autowired
	LimEnergyUsageRepository limEnergyUsageRepository;
	
	@Autowired
	DeviceStatusRepository deviceStatusRepository;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public LampInfo update(LampInfo info) {
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
	public Result batchDelete(List<String> ids) {
		Result result = new Result();
		try {
			List<LampInfo> list = repository.findAll(ids);
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
	public ResultData<LampInfo> getAll() {
		ResultData<LampInfo> data = new ResultData<>();
		try {
			data.setData(repository.findAll());
		} catch (Exception e) {
			data.setStatus(ResponseCode.FAILED.getCode());
		}
		data.setStatus(ResponseCode.SUCCESS.getCode());
		return data;
	}

	@Override
	public String deleteByGgz(String geoZoneId) {
		try {
			List<LampInfo> findByGeoZoneId = repository.findByGeoZoneId(geoZoneId);
			if (!findByGeoZoneId.isEmpty()) {
				findByGeoZoneId.forEach(lampInfo -> {
					lampInfo.setGeoZoneId(null);
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
	public String updateGz(Map<String, String> map) {
		try {
			Map<String, String> tempMap = new HashMap<>();
			tempMap.putAll(map);
			Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
			List<LampInfo> compareLamps = new ArrayList<>();
			if (iterator.hasNext()) {
				Entry<String, String> next = iterator.next();
				String value = next.getValue();
				List<LampInfo> byGeoZoneId = this.getByGeoZoneId(value);
				if (null != byGeoZoneId) {
					if (byGeoZoneId.size() > map.size()) {
						Iterator<Entry<String, String>> iterator2 = tempMap.entrySet().iterator();
						while (iterator2.hasNext()) {
							Entry<String, String> next2 = iterator2.next();
							String key = next2.getKey();
							for (LampInfo lampInfo : byGeoZoneId) {
								String id = lampInfo.getId();

								if (key.equals(id)) {
									System.out.println(id);
									compareLamps.add(lampInfo);
								}
							}
						}
						byGeoZoneId.removeAll(compareLamps);
						System.out.println(byGeoZoneId);
						for (LampInfo lampInfo : byGeoZoneId) {
							lampInfo.setGeoZoneId(null);
						}
						repository.save(byGeoZoneId);
					} else {
						map.forEach((k, v) -> {
							LampInfo findOne = repository.findOne(k);
							findOne.setGeoZoneId(v);
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
	public void updatePolling(List<LampInfo> list) {
		if (list.isEmpty()) {
			try {
				repository.save(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<LampInfo> getByGeoZoneId(String geoZoneId) {
		List<LampInfo> lampList =  repository.findByGeoZoneId(geoZoneId);
		List<LuminaireModelConfig> LMClist = luminaireRepository.findAll();
		Map<String,String> cp = new HashMap<String,String>();
		for(LuminaireModelConfig eneity :LMClist){
			cp.put(eneity.getModelId(), eneity.getControlProtocol());
		}
		for(LampInfo lampinfo : lampList){
			lampinfo.setControlProtocol(cp.get(lampinfo.getModuleId()));
		}
		return lampList;
	}

	@Override
	public Page<LampInfo> getAllByFilter(Integer pageNo, Integer pageSize, String type, String name) {
        System.out.println("pageNo = [" + pageNo + "], pageSize = [" + pageSize + "], name = [" + name + "], type = [" + type + "]");
        Page<LampInfo> page = null;
        Pageable pageable= new PageRequest(pageNo-1, pageSize);
        try {
            page = repository.findAll(where(type,name), pageable);
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
    public LampInfoDetail findLampDetailInfo(String id) {
        LampInfoDetail infoDetail = new LampInfoDetail();
        if (null != id && !id.equals("")) {
            LampInfo info = repository.findOne(id);
            infoDetail.setLampInfo(info);
            LuminaireModelConfig modelConfig = luminaireRepository.findByModelId(info.getModuleId());
            Luminaire luminaire = new Luminaire();
            if (null != modelConfig) {
                luminaire.setDescription(modelConfig.getDescription());
                luminaire.setControlProtocol(modelConfig.getControlProtocol());
                luminaire.setLampType(modelConfig.getLampType());
                luminaire.setLightSensor(modelConfig.isLightSensor());
                luminaire.setManufacturer(modelConfig.getManufacturer());
                luminaire.setModelId(modelConfig.getModelId());
                luminaire.setMotionSensor(modelConfig.isMotionSensor());
                luminaire.setPollingMethod(modelConfig.isPollingMethod());
                luminaire.setRatedWatt(modelConfig.getRatedWatt());
            }
            LifetimeTrackingConfig lifetimeTrackingConfig = lifetimeRepository.findByLuminaireId(info.getModuleId());
            if (null != lifetimeTrackingConfig) {
                luminaire.setLifeTime(lifetimeTrackingConfig.getLifetime());
            }
            infoDetail.setLuminaire(luminaire);
            PoleModelConfig poleModelConfig = poleRepository.findByName(info.getLampPole().getModelId());
            if (null == poleModelConfig) {
                PoleModelConfig poleModelConfig1 = new PoleModelConfig();
                poleModelConfig1.setId(null);
                poleModelConfig1.setDescription("");
                poleModelConfig1.setHeight(null);
                poleModelConfig1.setName("");
                poleModelConfig1.setPicStr("");
                poleModelConfig1.setPicture(null);
                infoDetail.setPole(poleModelConfig1);
            } else {
                infoDetail.setPole(poleModelConfig);
            }
        }
        return infoDetail;
    }

    private Specification<LampInfo> where(final String type, final String name) {
        return new Specification<LampInfo>() {

            @Override
            public Predicate toPredicate(Root<LampInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (name != null && name.length()>0) {
                	
                	if(type != null && type.equals("1")){
                		predicates.add(cb.like(root.get("id").as(String.class), "%" + name.trim() + "%"));
                	}
                	if(type != null && type.equals("2")){
                		predicates.add(cb.like( root.get("name").as(String.class), "%" + name.trim() + "%"));
                	}
                	if(type != null && type.equals("3")){
                		predicates.add(cb.like(root.get("moduleId").as(String.class), "%" + name.trim() + "%"));
                	}
                	if(type != null && type.equals("4")){
                		predicates.add(cb.like(root.get("geoZoneId").as(String.class), "%" + name.trim() + "%"));
                	}
                	if(type != null && type.equals("5")){
                		predicates.add(cb.like(root.get("address").as(String.class), "%" + name.trim() + "%"));
                	}
                }
                query.where(predicates.toArray(new Predicate[predicates.size()]));
                return query.getRestriction();
            }
        };
    }

	@Override
	public LuminaireModelConfig findLuminaireModelConfig(String id) {
		LuminaireModelConfig modelConfig = luminaireRepository.findByModelId(id);
		return modelConfig;
	}

	
	public Result findPoleOrLuminaire(RequestObject obj){
		Result result = new Result();
		try {
			LampInfo lampInfo = repository.findOne(obj.getLampId());
			LuminaireModelConfig modelConfig = null;
			if(lampInfo.getModuleId()!=null){
				modelConfig = luminaireRepository.findByModelId(lampInfo.getModuleId());
			}
//			PoleModelConfig poleModelConfig = poleRepository.findByName(lampInfo.getLampPole().getModelId());
			GZone gzone = null;
			if(lampInfo.getGeoZoneId()!=null){
				 gzone = geoZoneRepository.findByName(lampInfo.getGeoZoneId());
			}
			List<LiminaireAlarmInfo> pageAlarm = getLimAlarmInfo(obj);
			 SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			 Date date = lampInfo.getLampStatus().getInstalledDate();
			 String installedDate = null;
			 if(date!=null){
				 installedDate = format.format(date);
			 }
			DeviceInfo deviceInfo = new DeviceInfo();
					deviceInfo.setDeviceInfo(lampInfo.getName(),
					lampInfo.getId(),
					lampInfo.getModuleId(), 
					lampInfo.getLampPole().getPoleId(), 
					lampInfo.getLampPole().getModelId(),
					installedDate,
					modelConfig.getPicStr());
			DeviceLocationInfo deviceLocationInfo = new DeviceLocationInfo();
					deviceLocationInfo.setDeviceLocationInfo(lampInfo.getGeoZoneId(),
					lampInfo.getLocation().getLatitude(), 
					lampInfo.getLocation().getLongitude(), 
					gzone.getCountry(), 
					gzone.getCountry(), 
					lampInfo.getAddress());
			DeviceControl dc = new DeviceControl();
					dc.setDeviceId(lampInfo.getId());
					dc.setControlMode(lampInfo.getLampControl().getControlMode());
					dc.setLampLevel(lampInfo.getLampStatus().getLampLevel());
					dc.setLampSwitch(lampInfo.getLampStatus().isLampSwitch());
					dc.setControlProtocol(modelConfig.getControlProtocol());
			List<Object> list = new ArrayList<Object>();
			list.add(deviceInfo);
			list.add(deviceLocationInfo);
			list.add(pageAlarm);
			list.add(dc);
			result.setStatusCode("000000");
			result.setData(list);
		} catch (Exception e) {
			result.setStatusCode("999999");
			result.setMessage("Query Exception");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result updateDeviceInfo(DeviceInfo deviceInfo) {
		Result result = new Result();
		try {
			LampInfo info = repository.findOne(deviceInfo.getDeviceId());
			if(info==null){
				result.setStatusCode("999999");
				result.setMessage("Device ID does not exist");
				return result;
			}
			info.setName(deviceInfo.getDeviceName());
			info.setModuleId(deviceInfo.getLuminaireModelId());
			info.getLampPole().setPoleId(deviceInfo.getLampPoleId());
			info.getLampPole().setModelId(deviceInfo.getLampPoleModelId());
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			String installedDate = deviceInfo.getInstalledDate();
			if(installedDate!=null&&installedDate.length()>0){
				info.getLampStatus().setInstalledDate(format.parse(installedDate));
			}
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
	public Result updateDeviceInfo(DeviceLocationInfo deviceLocationInfo) {
		Result result = new Result();
		try {
			LampInfo info = repository.findOne(deviceLocationInfo.getDeviceId());
			if(info==null){
				result.setStatusCode("999999");
				result.setMessage("Device ID does not exist");
				return result;
			}
			info.setAddress(deviceLocationInfo.getAddress());
			info.setGeoZoneId(deviceLocationInfo.getGeoZone());
			info.getLocation().setLatitude(deviceLocationInfo.getLatitude());
			info.getLocation().setLongitude(deviceLocationInfo.getLongitude());
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
			LampInfo lampInfo = repository.getOne(deviceId);
			if(lampInfo.getLampStatus()!=null){
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


	public List<LiminaireAlarmInfo> getLimAlarmInfo(RequestObject obj) {
		  System.out.println("showNum = [" + obj.getShowNum() + "], , lampId = [" + obj.getLampId() + "]");
	        List<LiminaireAlarmInfo> listAlarm = null;
	        Pageable pageable= new PageRequest(0, obj.getShowNum());
	        try {
	        	  listAlarm = limAlarmInfoRepository.findByDeviceId(pageable, obj.getLampId());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		return listAlarm;
	}
	
	public List<LiminaireAlarmInfo> getEnergyUsageInfo(RequestObject obj) {
		  System.out.println("showNum = [" + obj.getShowNum() + "], , lampId = [" + obj.getLampId() + "]");
	        List<LiminaireAlarmInfo> listAlarm = null;
	        Pageable pageable= new PageRequest(0, obj.getShowNum());
	        try {
	        	  listAlarm = limAlarmInfoRepository.findByDeviceId(pageable, obj.getLampId());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		return listAlarm;
	}

	@Override
	public Result findLuminaireReport(RequestObject obj) {
		if((obj.getStartDate()==null||obj.getStartDate().length()<=0)&&(obj.getEndDate()==null||obj.getEndDate().length()<=0)){
			obj.setStartDate("2016-01-01");
			obj.setEndDate("2018-12-01");
		}
		Result result = new Result();
		result.setUnit(getUnits(obj.getType()));
		if(obj.getType().equals("1")){//Energy Usage
				List<LiminaireEnergyHist> listEnergy = null;
				try {
					listEnergy = limEnergyUsageRepository.findAll(
							new Specification<LiminaireEnergyHist>() {
						        @Override
						        public Predicate toPredicate(Root<LiminaireEnergyHist> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						            List<Predicate> list = new ArrayList<Predicate>();
		
						            if (obj.getLampId()!=null&&obj.getLampId().length()>0) {
						                list.add(cb.like(root.get("luminaireId").as(String.class), "%" + obj.getLampId() + "%"));
						            }
						            if (obj.getStartDate()!=null&&obj.getStartDate().length()>0) {
						            	//大于或等于传入时间  
						                list.add(cb.greaterThanOrEqualTo(root.get("associatedTime").as(String.class), obj.getStartDate()));  
						            }
		
						            if (obj.getEndDate()!=null&&obj.getEndDate().length()>0) {
						            	 //小于或等于传入时间  
						                list.add(cb.lessThanOrEqualTo(root.get("associatedTime").as(String.class), obj.getEndDate()));  
						            }
						            Predicate[] p = new Predicate[list.size()];
						            return cb.and(list.toArray(p));
						        }
						    });
					Integer pageNumber = obj.getPageNo();
					Integer pageSize = obj.getPageSize();
					List<LiminaireEnergyHist> reList = new ArrayList<LiminaireEnergyHist>();
					Integer totalNumber = listEnergy.size();
					Integer sPage = (pageNumber-1)*pageSize;
					Integer ePage = sPage+pageSize;
					if(ePage>totalNumber){
						ePage = totalNumber;
					}
					for(int i=sPage;i<ePage;i++){
						reList.add(listEnergy.get(i));
					}
					result.setPageData(pageNumber,pageSize,totalNumber,reList);
					result.setStatusCode("000000");
					result.setMessage("Query success");
					result.setReportData(listEnergy);
				} catch (Exception e) {
					result.setStatusCode("999999");
					result.setMessage("Query failure");
					e.printStackTrace();
				}
		}else{
			List<DeviceStatusReport> deviceList = null;
			try {
				deviceList = deviceStatusRepository.findAll(
						new Specification<DeviceStatusReport>() {
					        @Override
					        public Predicate toPredicate(Root<DeviceStatusReport> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					            List<Predicate> list = new ArrayList<Predicate>();
	
					            if (obj.getLampId()!=null&&obj.getLampId().length()>0) {
					                list.add(cb.like(root.get("deviceId").as(String.class), "%" + obj.getLampId() + "%"));
					            }
					            if (obj.getStartDate()!=null&&obj.getStartDate().length()>0) {
					            	//大于或等于传入时间  
					                list.add(cb.greaterThanOrEqualTo(root.get("timestamp").as(String.class), obj.getStartDate()));  
					            }
	
					            if (obj.getEndDate()!=null&&obj.getEndDate().length()>0) {
					            	 //小于或等于传入时间  
					                list.add(cb.lessThanOrEqualTo(root.get("timestamp").as(String.class), obj.getEndDate()));  
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
				List<DeviceStatusReport> reList = new ArrayList<DeviceStatusReport>();
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
		}
		return result;
	}

	@Override
	public Result<String> exportDevcieStatusReport(RequestObject obj) {
		Result<String> result = new Result<>();
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);//日期国际化
			if(obj.getExportType().equals("csv")){
				if(obj.getType().equals("1")){///Energy Usage
					/**查询数据**/
					List<LiminaireEnergyHist> lehList = null;
					List<String> dataList = new ArrayList<>();
					Double totalUsage = 0.0;
					// 查询数据，用于创建CSV文件
					try {
						lehList = findLuminaireStatusReport(obj);
						dataList.add(obj.getName());
						dataList.add("Date Type:"+","+obj.getName());
						dataList.add("Start Date:"+","+obj.getStartDate());
						dataList.add("End Date:"+","+obj.getEndDate());
						// 根据不同的criteria加不同的列名
						dataList.add("Export Time:"+","+df.format(new Date()));
						dataList.add(",,,");
						dataList.add("Date,"+obj.getName()+"("+getUnits(obj.getType())+")");
						for (LiminaireEnergyHist e : lehList) {
							dataList.add(e.getFormatDate() +","+e.getEnergyUsage());
							totalUsage += e.getEnergyUsage();
						}
						dataList.add(",,,");
						dataList.add(",,,");
						dataList.add(",,Energy Usage Grand Total("+lehList.size()+" Records):"+totalUsage+""+getUnits(obj.getType()));
						// 创建CSV文件
						Utils.createCsv(dataList,obj.getName() +".csv");
						result.setStatusCode("000000");
						result.setMessage("success");
					} catch (Exception e) {
						result.setStatusCode("999999");
						result.setMessage("fail");
						logger.error("create csv file failed!!!  Error Message:{}", e.getMessage());
						return result;
					}
				}else{
					/**查询数据**/
					List<DeviceStatusReport> dsrList = null;
					List<String> dataList = new ArrayList<>();
					Double totalData = 0.0;
					// 查询数据，用于创建CSV文件
					try {
						dsrList = findDeviceStatusReport(obj);
						dataList.add(obj.getName());
						dataList.add("Date Type:"+","+obj.getName());
						dataList.add("Start Date:"+","+obj.getStartDate());
						dataList.add("End Date:"+","+obj.getEndDate());
						// 根据不同的criteria加不同的列名
						dataList.add("Export Time:"+","+df.format(new Date()));
						dataList.add(",,,");
						dataList.add("Date,"+obj.getName()+"("+getUnits(obj.getType())+")");
						for (DeviceStatusReport e : dsrList) {
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
						logger.error("create csv file failed!!!  Error Message:{}", e.getMessage());
						return result;
					}
				}
			}else if(obj.getExportType().equals("pdf")){
				if(obj.getType().equals("1")){
					List<LiminaireEnergyHist> lehList = null;
					Map<String, Object> freemarkerData = new HashMap<String, Object>();
					Double totalData = 0.0;
					//利用前端传的base64编码生成图片
					try {
						Utils.createPicture(obj.getBase64Info());
					} catch (IOException e) {
						logger.error("create picture failed!!  Error Message:{}", e.getMessage());
						result.setStatusCode("999999");
						result.setMessage("fail");
						return result;
					}
					//查询数据，用于渲染HTML页面
					try {
						lehList =findLuminaireStatusReport(obj);
					} catch (Exception e) {
						logger.error(">>Get Luminaire Usage Report Failed!!  Error Message:{}", e.getMessage());
						result.setStatusCode("999999");
						result.setMessage("fail");
						return result;
					}
					/***
					 * 时间国际化
					 */
						for (LiminaireEnergyHist e : lehList) {
							totalData += e.getEnergyUsage();
						}
					obj.setExportTime(df.format(new Date()));//导出时间
					freemarkerData.put("LEHCondition", obj);
					freemarkerData.put("LEHReport", lehList);
					freemarkerData.put("type", obj.getType());
					freemarkerData.put("totalItem", lehList.size());
					freemarkerData.put("totalData", totalData);
					freemarkerData.put("unit", getUnits(obj.getType()));
					String content = null;
					//freemarker渲染页面，根据不同的criteria选择不同的模板
					content = Utils.freeMarkerRender(freemarkerData, "LuminaireUsageReport.html");
					//flying saucer生成PDF
					try {
						Utils.createPdf(content);
						result.setStatusCode("000000");
						result.setMessage("success");
					} catch (DocumentException | IOException e) {
						logger.error("create Pdf failed!!  Error Message:{}", e.getMessage());
						result.setStatusCode("999999");
						result.setMessage("fail");
						return result;
					}
				}else{
					List<DeviceStatusReport> dsrList = null;
					Map<String, Object> freemarkerData = new HashMap<String, Object>();
					Double totalData = 0.0;
					//利用前端传的base64编码生成图片
					try {
						Utils.createPicture(obj.getBase64Info());
					} catch (IOException e) {
						logger.error("create picture failed!!  Error Message:{}", e.getMessage());
						result.setStatusCode("999999");
						result.setMessage("fail");
						return result;
					}
					//查询数据，用于渲染HTML页面
					try {
						dsrList = findDeviceStatusReport(obj);
					} catch (Exception e) {
						logger.error(">>Get Device Status Report Failed!!  Error Message:{}", e.getMessage());
						result.setStatusCode("999999");
						result.setMessage("fail");
						return result;
					}
					/***
					 * 时间国际化
					 */
					for (DeviceStatusReport e : dsrList) {
						totalData += e.getData();
					}
					obj.setExportTime(df.format(new Date()));//导出时间
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
						logger.error("create Pdf failed!!  Error Message:{}", e.getMessage());
						result.setStatusCode("999999");
						result.setMessage("fail");
						return result;
					}
				}
			}
		return result;
	}
	
	private List<LiminaireEnergyHist>  findLuminaireStatusReport(RequestObject obj){
		List<LiminaireEnergyHist> listEnergy = null;
		try {
			listEnergy = limEnergyUsageRepository.findAll(
					new Specification<LiminaireEnergyHist>() {
				        @Override
				        public Predicate toPredicate(Root<LiminaireEnergyHist> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				            List<Predicate> list = new ArrayList<Predicate>();

				            if (obj.getLampId()!=null&&obj.getLampId().length()>0) {
				                list.add(cb.like(root.get("luminaireId").as(String.class), "%" + obj.getLampId() + "%"));
				            }
				            if (obj.getStartDate()!=null&&obj.getStartDate().length()>0) {
				            	//大于或等于传入时间  
				                list.add(cb.greaterThanOrEqualTo(root.get("associatedTime").as(String.class), obj.getStartDate()));  
				            }

				            if (obj.getEndDate()!=null&&obj.getEndDate().length()>0) {
				            	 //小于或等于传入时间  
				                list.add(cb.lessThanOrEqualTo(root.get("associatedTime").as(String.class), obj.getEndDate()));  
				            }
				            Predicate[] p = new Predicate[list.size()];
				            return cb.and(list.toArray(p));
				        }
				    });
		}catch (Exception e) {
			logger.error(">>Method->findLuminaireStatusReport Exception:", e.getMessage());
			e.printStackTrace();
		}
		return listEnergy;
	}
	
	private List<DeviceStatusReport>  findDeviceStatusReport(RequestObject obj){
		List<DeviceStatusReport> deviceList = null;
		try {
			deviceList = deviceStatusRepository.findAll(
					new Specification<DeviceStatusReport>() {
				        @Override
				        public Predicate toPredicate(Root<DeviceStatusReport> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				            List<Predicate> list = new ArrayList<Predicate>();

				            if (obj.getLampId()!=null&&obj.getLampId().length()>0) {
				                list.add(cb.like(root.get("deviceId").as(String.class), "%" + obj.getLampId() + "%"));
				            }
				            if (obj.getStartDate()!=null&&obj.getStartDate().length()>0) {
				            	//大于或等于传入时间  
				                list.add(cb.greaterThanOrEqualTo(root.get("timestamp").as(String.class), obj.getStartDate()));  
				            }

				            if (obj.getEndDate()!=null&&obj.getEndDate().length()>0) {
				            	 //小于或等于传入时间  
				                list.add(cb.lessThanOrEqualTo(root.get("timestamp").as(String.class), obj.getEndDate()));  
				            }
				            if (obj.getType()!=null&&obj.getType().length()>0) {
				            	  list.add(cb.like(root.get("dataType").as(String.class), "%" + obj.getType() + "%"));
				            }
				            Predicate[] p = new Predicate[list.size()];
				            return cb.and(list.toArray(p));
				        }
				    });
		}catch (Exception e) {
			logger.error(">>Method->findDeviceStatusReport Exception:", e.getMessage());
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
}
