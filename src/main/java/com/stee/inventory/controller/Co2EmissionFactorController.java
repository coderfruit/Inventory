package com.stee.inventory.controller;

import com.stee.inventory.entity.Co2EmissionFactor;
import com.stee.inventory.entity.Result;
import com.stee.inventory.service.ICo2EmissionFactorService;
import com.stee.sel.common.ResultData;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.jws.WebParam;

@RestController
@RequestMapping("/co2")
public class Co2EmissionFactorController {
    @Resource
    private ICo2EmissionFactorService iSvcCarboDioxide;

//    @Log(moduleName="save",desc="保存二氧化碳设置信息")
//    @RequestMapping(path="/save",method= RequestMethod.POST)
//    public Result save(@RequestBody Co2EmissionFactor factor){
//            return service.save(factor);
//    }
//
//    @Log(moduleName="update",desc="修改二氧化碳设置信息")
//    @RequestMapping(path="/update",method=RequestMethod.PUT)
//    public Result update(@RequestBody Co2EmissionFactor factor){
//        return service.update(factor);
//    }
//
//    @Log(moduleName="deleteCD",desc="删除二氧化碳设置信息")
//    @RequestMapping(path="/delete/{id}",method=RequestMethod.GET)
//    public Result deleteCD(@PathVariable Integer id){
//        return service.deleteCD(id);
//    }
//
//    @RequestMapping(path="/get",method=RequestMethod.GET)
//    public Result getCDByName(@WebParam Integer id){
//        return service.getCDById(id);
//    }
//
//    @RequestMapping(path="/getall",method=RequestMethod.GET)
//    public Result getAllCD(@PageableDefault Pageable pageable){
//
//        return service.getAllCD(pageable);
//    }
//
//    @RequestMapping(path="/fuzzy/get",method=RequestMethod.GET)
//    public Result fuzzyQueryByName(@WebParam String name,@PageableDefault Pageable pageable){
//        return service.fuzzyQueryByName(name,pageable);
//    }


//    @Log(moduleName="save",desc="保存二氧化碳设置信息")
    @ResponseBody
    @RequestMapping(path="/co2/save",method=RequestMethod.POST)
    public ResultData save(@RequestBody Co2EmissionFactor carboDioxide){
        return iSvcCarboDioxide.save(carboDioxide);
    }

//    @Log(moduleName="update",desc="修改二氧化碳设置信息")
    @ResponseBody
    @RequestMapping(path="/co2/update",method=RequestMethod.PUT)
    public ResultData update(@RequestBody Co2EmissionFactor carboDioxide){
        return iSvcCarboDioxide.update(carboDioxide);
    }

//    @Log(moduleName="deleteCD",desc="删除二氧化碳设置信息")
    @ResponseBody
    @RequestMapping(path="/co2/delete/{id}",method=RequestMethod.DELETE)
    public ResultData deleteCD(@PathVariable Integer id){
        return iSvcCarboDioxide.deleteCD(id);
    }

    @ResponseBody
    @RequestMapping(path="/co2/get",method=RequestMethod.GET)
    public ResultData getCDByName(@WebParam Integer id){
        return iSvcCarboDioxide.getCDById(id);
    }

    @ResponseBody
    @RequestMapping(path="/co2/getall",method=RequestMethod.GET)
    public ResultData getAllCD(){
        return iSvcCarboDioxide.getAllCD();
    }

    @ResponseBody
    @RequestMapping(path="/co2/fuzzy/get",method=RequestMethod.GET)
    public ResultData fuzzyQueryByName(@WebParam String name){
        return iSvcCarboDioxide.fuzzyQueryByName(name);
    }
}
