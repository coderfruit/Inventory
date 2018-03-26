package com.stee.inventory.controller;

import com.stee.inventory.dao.LampPoleDao;
import com.stee.inventory.entity.Result;
import com.stee.sel.inventory.LampPoleEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Result getAll() {
        List<LampPoleEntity> lampPoles=lampPoleDao.findAll();
        return new Result().success(lampPoles);
    }


}
