package com.zzx.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zzx
 * @date 2021-04-20 21:59
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.zzx")
@MapperScan("com.zzx.cms.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
