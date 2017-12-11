package com.pracainzynierska.controller;

import com.pracainzynierska.model.Szablony;
import com.pracainzynierska.service.SzablonyService;
import com.pracainzynierska.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//mvn spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"


@Controller
public class MainController {
    private SzablonyService szablonyService;
    @RequestMapping(value = "/scriptTest", method = RequestMethod.GET)
    public String homepage(){
        szablonyService = new SzablonyService();
        Szablony szablon = szablonyService.getSzablonBySzablonId(2);
        System.out.println(szablon.getTresc());
        return "index";
    }
}
