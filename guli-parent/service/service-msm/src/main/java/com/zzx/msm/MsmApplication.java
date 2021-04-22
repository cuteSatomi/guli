package com.zzx.msm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zzx
 * @date 2021-04-21 16:41:15
 */
@SpringBootApplication
@ComponentScan("com.zzx")
@MapperScan("com.zzx.msm.mapper")
public class MsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class, args);
    }
}
