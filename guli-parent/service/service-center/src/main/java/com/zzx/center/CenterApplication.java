package com.zzx.center;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zzx
 * @date 2021-04-21 20:44
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.zzx")
@MapperScan("com.zzx.center.mapper")
public class CenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(CenterApplication.class, args);
    }
}
