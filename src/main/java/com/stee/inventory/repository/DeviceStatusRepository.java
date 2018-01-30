package com.stee.inventory.repository;

import com.stee.sel.lim.reading.DeviceStatusReport;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface DeviceStatusRepository  extends JpaRepository<DeviceStatusReport, String>,JpaSpecificationExecutor<DeviceStatusReport>,PagingAndSortingRepository<DeviceStatusReport, String>{
	List<DeviceStatusReport> findByDeviceId(Pageable pageable, String devicdId);
}
