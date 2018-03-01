package com.stee.inventory;

import com.stee.inventory.dao.*;
import com.stee.inventory.entity.AlarmThreshold;
import com.stee.inventory.entity.LampPole;
import com.stee.inventory.entity.LampPoleModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
public class InventoryApplicationTests {

	@Resource
	private LampPoleDao lampPoleDao;
	@Resource
	private LampPoleModelDao lampPoleModelDao;
    @Resource
    private CalendarProfileRepository calendarProfileRepository;

	@Resource
	private AlarmThresholdRepository repository;

	@Test
	public void test1(){
		LampPoleModel lampPoleModel=new LampPoleModel();
		lampPoleModel.setLampPoleModelId("111");
		lampPoleModel.setDescription("111");
		lampPoleModel.setHeight(11.0);

		LampPole lampPole1=new LampPole("111",111);
		lampPole1.setLampPoleModel(lampPoleModel);
		LampPole lampPole2=new LampPole("222",222);
		lampPole2.setLampPoleModel(lampPoleModel);
        List<LampPole> list=new ArrayList<>();
        list.add(lampPole1);
        list.add(lampPole2);

//		lampPoleModel.setLampPoles(list);

//        LampPoleModel save = lampPoleModelDao.save(lampPoleModel);
//        lampPoleDao.save(lampPole1);
//        System.out.println(save);

    }

}
