package com.pracainzynierska.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.pracainzynierska.controller.service.ShellRunnerService;
import com.pracainzynierska.model.daoservice.SzablonyService;
import com.pracainzynierska.model.dto.UczestnikDTO;
import com.pracainzynierska.model.entities.Szablony;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.stream.Collectors;


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
    @RequestMapping(value = "/getLog", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getLog(){
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get("/home/karol/Pulpit/test-file"));
            br.lines().collect(Collectors.toList()).forEach(result::append);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("log", result.toString());
        return new Gson().toJson(jsonObject);
    }

    @RequestMapping(value = "/userDetails", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getCurrentUserDetails(Principal principal){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", principal.getName());
        return new Gson().toJson(jsonObject);
    }
}
