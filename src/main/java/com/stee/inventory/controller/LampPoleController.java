package com.stee.inventory.controller;

import com.stee.inventory.dao.LampPoleDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
