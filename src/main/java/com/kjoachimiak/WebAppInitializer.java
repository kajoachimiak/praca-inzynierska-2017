package com.kjoachimiak;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.kjoachimiak")
public class WebAppInitializer {
    private static final Logger LOG = Logger.getLogger(WebAppInitializer.class);
    public static void main(String[] args) throws Exception{
        SpringApplication.run(WebAppInitializer.class,args);
    }

}
