package com.stee.inventory.service.impl;

import com.google.common.collect.Lists;
import com.stee.inventory.entity.LuminaireQueryBean;
import com.stee.inventory.repository.LifetimeTrackingRepository;
import com.stee.inventory.repository.LuminaireModelRepository;
import com.stee.inventory.service.ILuminaireModelService;
import com.stee.sel.asm.LifetimeTrackingConfig;
import com.stee.sel.asm.LuminaireModelConfig;
import com.stee.sel.common.ResultData;
import com.stee.sel.constant.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/* Copyright (C) 2016, ST Electronics Info-Comm Systems PTE. LTD
 * All rights reserved.
 *
 * This software is confidential and proprietary property of 
 * ST Electronics Info-Comm Systems PTE. LTD.
 * The user shall not disclose the contents of this software and shall
 * only use it in accordance with the terms and conditions stated in
 * the contract or license agreement with ST Electronics Info-Comm Systems PTE. LTD.
 *
 * Project Name : STL_ASM
 * File Name    : LuminaireModelServiceImpl.java
 * Author       : Jerry
 * Created      : 2016年11月15日 下午1:47:25
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
@Service("luminaireModelServiceImpl")
public class LuminaireModelServiceImpl implements ILuminaireModelService {
	@Autowired
	LuminaireModelRepository repository;

	@Autowired
	LifetimeTrackingRepository lifetimeRepo;

	@Override
	public ResultData<LuminaireModelConfig> getAll() {
		ResultData<LuminaireModelConfig> resultData = new ResultData<>();
		List<LuminaireModelConfig> list = Lists.newArrayList();
        try {
			List<LuminaireModelConfig> findAll = repository.findAll();
            if (null != findAll && !findAll.isEmpty()) {
                for (LuminaireModelConfig luminaireModelConfig : findAll) {
                    String modelId = luminaireModelConfig.getModelId();
                    LifetimeTrackingConfig lifetimeTrackingConfig = lifetimeRepo.findByLuminaireId(modelId);
                    if (null != lifetimeTrackingConfig) {
                        luminaireModelConfig.setLifeTimeExits(true);
                    } else {
                        luminaireModelConfig.setLifeTimeExits(false);
                    }
                    list.add(luminaireModelConfig);
                }
            }
            resultData.setData(list);
			resultData.setStatus(ResponseCode.SUCCESS.getCode());
		} catch (Exception e) {
			e.printStackTrace();
			resultData.setStatus(ResponseCode.FAILED.getCode());
		}
		return resultData;
	}

	@Override
	public String save(LuminaireModelConfig config) {
		if (null != config) {
			try {
				repository.save(config);
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
	public boolean isIdExits(Integer id) {
		return repository.exists(id);
	}

	@Override
	public String delete(Integer id) {
		try {
			LuminaireModelConfig findOne = repository.findOne(id);
			String modelId = findOne.getModelId();
			if (null != modelId && !modelId.equals("")) {
                LifetimeTrackingConfig config = lifetimeRepo.findByLuminaireId(modelId);
				if (null != config) {
					lifetimeRepo.delete(config.getId());
				}
			}
			repository.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCode.FAILED.getCode();
		}
		return ResponseCode.SUCCESS.getCode();
	}

	@Override
	public ResultData<LuminaireModelConfig> findByModelIdLike(LuminaireQueryBean query) {
		ResultData<LuminaireModelConfig> resultData = new ResultData<>();
		List<LuminaireModelConfig> list = Lists.newArrayList();
		List<LuminaireModelConfig> configs = Lists.newArrayList();
		if (null == query) {
            try {
                configs =  repository.findAll();
            } catch (Exception e) {
                e.printStackTrace();
                resultData.setStatus(ResponseCode.FAILED.getCode());
            }
		} else {
            try {
               configs = repository.findAll(where(query.getModelId(), query.getManufacturer(), query.getLampType(), query.getControlProtocol(), query.getRatedWattStart(), query.getRatedWattEnd(), query.getDescription()));
            } catch (Exception e) {
                e.printStackTrace();
                resultData.setStatus(ResponseCode.FAILED.getCode());
            }
        }

        if (null != configs && !configs.isEmpty()) {
            for (LuminaireModelConfig luminaireModelConfig : configs) {
                String modelId = luminaireModelConfig.getModelId();
                LifetimeTrackingConfig lifetimeTrackingConfig = lifetimeRepo.findByLuminaireId(modelId);
                if (null != lifetimeTrackingConfig) {
                    luminaireModelConfig.setLifeTimeExits(true);
                } else {
                    luminaireModelConfig.setLifeTimeExits(false);
                }
                list.add(luminaireModelConfig);
            }
        }

        resultData.setData(list);
		resultData.setStatus(ResponseCode.SUCCESS.getCode());
		return resultData;
	}

    private Specification<LuminaireModelConfig> where(final String modelId, final String manufacturer, final String lampType, final String controlProtocol, final Double ratedWattStart, final Double ratedWattEnd, final String desc) {
        return new Specification<LuminaireModelConfig>() {

            @Override
            public Predicate toPredicate(Root<LuminaireModelConfig> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (null != modelId && !modelId.equals("")) {
                    predicates.add(cb.like(root.<String>get("modelId"), "%" + modelId + "%"));
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
