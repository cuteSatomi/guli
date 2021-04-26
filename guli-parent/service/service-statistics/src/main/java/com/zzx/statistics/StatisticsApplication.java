package com.zzx.statistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zzx
 * @date 2021-04-26 16:34:48
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan("com.zzx")
@MapperScan("com.zzx.statistics.mapper")
public class StatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class, args);
    }
}
