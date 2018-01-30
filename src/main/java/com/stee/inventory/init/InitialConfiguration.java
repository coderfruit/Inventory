package com.stee.inventory.init;

import com.stee.sel.field.ResultFieldConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;


/* Copyright (C) 2016, ST Electronics Info-Comm Systems PTE. LTD
 * All rights reserved.
 *
 * This software is confidential and proprietary property of 
 * ST Electronics Info-Comm Systems PTE. LTD.
 * The user shall not disclose the contents of this software and shall
 * only use it in accordance with the terms and conditions stated in
 * the contract or license agreement with ST Electronics Info-Comm Systems PTE. LTD.
 *
 * Project Name : STL_NIA
 * File Name    : InitialConfiguration.java
 * Author       : yuxiaolin
 * Created      : 2016年11月17日 下午8:58:37
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
@Component
public class InitialConfiguration implements CommandLineRunner {

    @Override
    public void run(String... arg0) throws Exception {
        // 初始化
        initProperties();
    }

    private void initProperties() {
        Properties niaProps = new Properties();
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("field-config.properties");
            niaProps.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,Map<Integer,String>> fieldMap = new HashMap<String,Map<Integer,String>>();
        niaProps.forEach((o, o2) -> {
        	String keyStr = o.toString();
        	String[] str = keyStr.split("\\.");
        	Integer showOrder = Integer.parseInt(str[2]);
        	if(fieldMap.containsKey(str[1])){
        		fieldMap.get(str[1]).put(showOrder,o2.toString());
        	}else{
        		Map<Integer,String> map = new HashMap<Integer,String>();
        		map.put(showOrder,o2.toString());
        		fieldMap.put(str[1], map);
        	}
        }); 
    	for(Map.Entry<String,Map<Integer,String>> enery : fieldMap.entrySet()){
    		Map<Integer,String> map = enery.getValue();
    		if(!ResultFieldConfig.fieldMap.containsKey(enery.getKey())){
    			List<String> fieldList = new ArrayList<String>();
    			ResultFieldConfig.fieldMap.put(enery.getKey(), fieldList);
    			for(int i=0;i<map.size();i++){
    				ResultFieldConfig.fieldMap.get(enery.getKey()).add(i,map.get(i));
        		}
    		}
    	}
        System.out.println(ResultFieldConfig.fieldMap);
      
    }


}
