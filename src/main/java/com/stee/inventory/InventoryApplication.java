package com.stee.inventory;

import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.security.auth.message.config.AuthConfigFactory;

@SpringBootApplication
@ServletComponentScan
@EnableScheduling
@MapperScan("com.stee.inventory.dao")
@ComponentScan(basePackages = {"com.stee.sel","com.stee.inventory"})
@EntityScan({"com.stee.sel.lim","com.stee.sel.asm","com.stee.sel.gzm","com.stee.sel.cpm","com.stee.sel.dgm","com.stee.sel.lfm"})
public class InventoryApplication {

    public static void main(String[] args) {

        if(AuthConfigFactory.getFactory() == null){
            AuthConfigFactory.setFactory(new AuthConfigFactoryImpl());
        }
        SpringApplication.run(InventoryApplication.class, args);
    }

}
