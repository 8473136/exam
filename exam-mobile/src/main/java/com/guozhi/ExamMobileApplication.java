package com.guozhi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ExamMobileApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamMobileApplication.class, args);
        log.info("[移动端]模块启动成功");
    }

}
