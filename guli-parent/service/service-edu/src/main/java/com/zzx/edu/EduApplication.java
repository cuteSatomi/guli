package com.zzx.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zzx
 * @date 2021-04-10 18:32
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.zzx")
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
