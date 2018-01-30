package com.stee.inventory.repository;

import com.stee.sel.lim.reading.LiminaireAlarmInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface LimAlarmInfoRepository extends  JpaRepository<LiminaireAlarmInfo, String>,JpaSpecificationExecutor<LiminaireAlarmInfo>,PagingAndSortingRepository<LiminaireAlarmInfo, String>  {
	List<LiminaireAlarmInfo> findByDeviceId(Pageable pageable, String devicdId);
}
