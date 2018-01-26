package com.pracainzynierska.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.pracainzynierska.controller.service.ShellRunnerService;
import com.pracainzynierska.controller.service.impl.JsonBuilderService;
import com.pracainzynierska.controller.service.impl.TemplateService;
import com.pracainzynierska.controller.service.TemplateListGenerator;
import com.pracainzynierska.controller.service.impl.UserService;
import com.pracainzynierska.model.dto.RunScriptResponseDTO;
import com.pracainzynierska.model.entities.Template;
import com.pracainzynierska.model.entities.User;
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
    private TemplateService templateService;
    private UserService userService;
    private TemplateListGenerator templateListGenerator;
    private JsonBuilderService jsonBuilderService;

    @Autowired
    public MainController(ShellRunnerService shellRunnerService, UserService userService,
                          TemplateListGenerator templateListGenerator, JsonBuilderService jsonBuilderService,
                          TemplateService templateService) {
        this.shellRunnerService = shellRunnerService;
        this.userService = userService;
        this.templateListGenerator = templateListGenerator;
        this.jsonBuilderService = jsonBuilderService;
        this.templateService = templateService;
    }

    @RequestMapping(value = "/runScript", method = RequestMethod.GET)
    @ResponseBody
    public RunScriptResponseDTO runScript(@RequestParam("templateId") Integer templateId,
                                          @RequestParam("templateName") String templateName) {
        Template template = templateService.getTemplateByIdAndName(templateId, templateName);
        String command = template.getContent();
        RunScriptResponseDTO runScriptResponseDTO = new RunScriptResponseDTO();
        try {
            runScriptResponseDTO.setExecutionSuccess(true);
            LOG.info("Executing command "+ command +". Result is: " +
                    shellRunnerService.runScript(command));
        } catch (IOException e) {
            runScriptResponseDTO.setExecutionSuccess(false);
            LOG.error("Command: " + command + " execution failed!", e);
        } catch (InterruptedException e) {
            runScriptResponseDTO.setExecutionSuccess(false);
            LOG.error("Intterupted exception while execution command: "+ command,e);
        }
        return runScriptResponseDTO;
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
        String username = userService.getCurrentUserUsername(principal);
        jsonObject.addProperty("username", username);
        return new Gson().toJson(jsonObject);
    }

    @RequestMapping(value = "/userRelations", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getCurrentUserRelations(Principal principal) {
        String username = userService.getCurrentUserUsername(principal);
        User user = userService.getUserByLogin(username);
        return jsonBuilderService.buildUserRelationsResponse(user);
    }

    @RequestMapping(value = "/treeNodeTemplates", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getTreeNodeTemplates(Principal principal, @RequestParam("nodeType") String nodeType) {
        String username = userService.getCurrentUserUsername(principal);
        User user = userService.getUserByLogin(username);

        Pair<String, List<Template>> templateGeneratorResult =
                templateListGenerator.generateTemplateList(NodeType.valueOf(nodeType.toUpperCase(Locale.ENGLISH)), user);
        String responsJson =
                jsonBuilderService.buildTemplateListResponse(templateGeneratorResult.getKey(),
                        templateGeneratorResult.getValue());

        return responsJson;
    }
}
