package com.shn.gmall.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan("com.shn.gmall.mapper")
public class GmallUserApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(GmallUserApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

}
