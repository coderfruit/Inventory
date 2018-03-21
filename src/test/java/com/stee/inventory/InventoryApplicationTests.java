package com.stee.inventory;

import com.stee.inventory.dao.AlarmThresholdRepository;
import com.stee.inventory.dao.CalendarProfileRepository;
import com.stee.inventory.dao.LampPoleDao;
import com.stee.inventory.dao.LampPoleModelDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;

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


//		lampPoleModel.setLampPoles(list);

//        LampPoleModel save = lampPoleModelDao.save(lampPoleModel);
//        lampPoleDao.save(lampPole1);
//        System.out.println(save);

    }


}
