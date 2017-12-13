package com.pracainzynierska.controller;

import com.pracainzynierska.controller.service.ShellRunnerService;
import com.pracainzynierska.model.daoservice.SzablonyService;
import com.pracainzynierska.model.entities.Szablony;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//mvn spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"


@Controller
public class MainController {
    private static final Logger LOG = Logger.getLogger(MainController.class);
    private final ShellRunnerService shellRunnerService;
    private SzablonyService szablonyService;

    @Autowired
    public MainController(ShellRunnerService shellRunnerService) {
        this.shellRunnerService = shellRunnerService;
    }

    @RequestMapping(value = "/scriptTest", method = RequestMethod.GET)
    public String homepage(){
        szablonyService = new SzablonyService();
        Szablony szablon = szablonyService.getSzablonBySzablonId(4);
        String command = szablon.getTresc();
        System.out.println(shellRunnerService.runScript(command));
        return "index";
    }
}
