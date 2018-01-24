package com.pracainzynierska.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.pracainzynierska.controller.service.ShellRunnerService;
import com.pracainzynierska.controller.service.impl.JsonBuilderService;
import com.pracainzynierska.controller.service.impl.SzablonyService;
import com.pracainzynierska.controller.service.TemplateListGenerator;
import com.pracainzynierska.controller.service.impl.UczestnikService;
import com.pracainzynierska.model.entities.Szablony;
import com.pracainzynierska.model.entities.Uczestnik;
import com.pracainzynierska.model.enums.NodeType;
import javafx.util.Pair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@Controller
public class MainController {
    private static final Logger LOG = Logger.getLogger(MainController.class);
    private final ShellRunnerService shellRunnerService;
    private SzablonyService szablonyService;
    private UczestnikService uczestnikService;
    private TemplateListGenerator templateListGenerator;
    private JsonBuilderService jsonBuilderService;

    @Autowired
    public MainController(ShellRunnerService shellRunnerService, UczestnikService uczestnikService,
                          TemplateListGenerator templateListGenerator, JsonBuilderService jsonBuilderService) {
        this.shellRunnerService = shellRunnerService;
        this.uczestnikService = uczestnikService;
        this.templateListGenerator = templateListGenerator;
        this.jsonBuilderService = jsonBuilderService;
    }

    @RequestMapping(value = "/scriptTest", method = RequestMethod.GET)
    public String homepage() {
        szablonyService = new SzablonyService();
        Szablony szablon = szablonyService.getSzablonBySzablonId(4);
        String command = szablon.getTresc();
        System.out.println(shellRunnerService.runScript(command));
        return "index";
    }

    @RequestMapping(value = "/getLog", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getLog() {
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
    public String getCurrentUserDetails(Principal principal) {
        JsonObject jsonObject = new JsonObject();
        String username = uczestnikService.getCurrentUserUsername(principal);
        jsonObject.addProperty("username", username);
        return new Gson().toJson(jsonObject);
    }

    @RequestMapping(value = "/userRelations", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getCurrentUserRelations(Principal principal) {
        String username = uczestnikService.getCurrentUserUsername(principal);
        Uczestnik user = uczestnikService.getUserByLogin(username);
        return jsonBuilderService.buildUserRelationsResponse(user);
    }

    @RequestMapping(value = "/treeNodeTemplates", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getTreeNodeTemplates(Principal principal, @RequestParam("nodeType") String nodeType) {
        String username = uczestnikService.getCurrentUserUsername(principal);
        Uczestnik user = uczestnikService.getUserByLogin(username);

        Pair<String, List<Szablony>> templateGeneratorResult =
                templateListGenerator.generateTemplateList(NodeType.valueOf(nodeType.toUpperCase(Locale.ENGLISH)), user);
        String responsJson =
                jsonBuilderService.buildTemplateListResponse(templateGeneratorResult.getKey(),
                        templateGeneratorResult.getValue());

        return responsJson;
    }
}
