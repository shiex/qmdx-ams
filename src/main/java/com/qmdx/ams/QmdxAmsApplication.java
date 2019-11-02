package com.qmdx.ams;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qmdx.ams.mapper")
public class QmdxAmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(QmdxAmsApplication.class, args);
    }

}
