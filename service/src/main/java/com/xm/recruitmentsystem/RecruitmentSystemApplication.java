package com.xm.recruitmentsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.xm")
public class RecruitmentSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecruitmentSystemApplication.class,args);
    }

}
