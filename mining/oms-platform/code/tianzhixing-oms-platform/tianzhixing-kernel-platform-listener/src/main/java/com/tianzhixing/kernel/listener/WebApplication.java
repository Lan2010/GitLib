package com.tianzhixing.kernel.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApplication {

    /**
     * init spring boot
     * @param args
     */
    public static void main(String[] args) {
        try {
            SpringApplication.run(WebApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
