package com.pracainzynierska.controller;

import com.pracainzynierska.model.Szablony;
import com.pracainzynierska.service.SzablonyService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//mvn spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"


@Controller
public class MainController {
    private static final Logger LOG = Logger.getLogger(MainController.class);
    private SzablonyService szablonyService;
    @RequestMapping(value = "/scriptTest", method = RequestMethod.GET)
    public String homepage(){
        szablonyService = new SzablonyService();
        Szablony szablon = szablonyService.getSzablonBySzablonId(3);
        String command = szablon.getTresc();
        System.out.println(runShellScript(command));
        return "index";
    }
    private StringBuffer runShellScript(String command){
       StringBuffer output = new StringBuffer();
       Process p;
       try {
           p = Runtime.getRuntime().exec(command);
           p.waitFor();
           BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
           String line = "";
           while ((line = reader.readLine())!=null){
               output.append(line).append("\n");
           }
       } catch (IOException e) {
           LOG.error("Command: " + command + " execution failed!", e);
       } catch (InterruptedException e) {
           LOG.error("Intterupted exception while execution command: "+ command,e);
       }
       return output;
    }
}
