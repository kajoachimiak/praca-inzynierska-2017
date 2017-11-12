package com.pracainzynierska;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.pracainzynierska")
public class WebAppInitializer {
    private static final Logger log = Logger.getLogger(WebAppInitializer.class);
    public static void main(String[] args) throws Exception{
        SpringApplication.run(WebAppInitializer.class,args);
    }

}
