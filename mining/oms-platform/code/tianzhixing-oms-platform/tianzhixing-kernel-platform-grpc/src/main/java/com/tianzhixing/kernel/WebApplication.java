package com.tianzhixing.kernel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

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
