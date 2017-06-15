package com.pracainzynierska;

import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer{
    private Logger log = Logger.getLogger(WebAppInitializer.class);
    private static final String CONFIG_LOCATION = "com.pracainzynierska.config";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        log.info("Initializing Application\n"+servletContext.getServerInfo());

        //Create App context
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.setConfigLocation(CONFIG_LOCATION);

        //Servlet mapping - auto init
        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("mvc-dispatcher",dispatcherServlet);
        servletRegistration.addMapping("/");
        servletRegistration.setAsyncSupported(true);
        servletRegistration.setLoadOnStartup(1);
    }
}
