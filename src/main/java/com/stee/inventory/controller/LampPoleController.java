package com.stee.inventory.controller;

import com.stee.inventory.dao.LampPoleDao;
import com.stee.inventory.entity.LampPole;
import com.stee.inventory.entity.LampPoleModel;
import com.stee.inventory.entity.PoleQueryBean;
import com.stee.inventory.entity.Result;
import com.stee.inventory.service.ILampPoleModelService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * lamp model configuration
 *
 * @Authon luffy
 */
@RestController
@RequestMapping(value = "/pole")
public class LampPoleController {
    @Resource
    private LampPoleDao lampPoleDao;

//    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
//    public Result<LampPoleModel> getAll(String id) {
//        LampPole lampPole=lampPoleDao.findByLampPoleId(id);
//        return new Result().success(lampPole);
//    }


}
