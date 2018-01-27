package com.pracainzynierska.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.pracainzynierska.controller.service.FileService;
import com.pracainzynierska.controller.service.JsonBuilderService;
import com.pracainzynierska.controller.service.ShellRunnerService;
import com.pracainzynierska.controller.service.TemplateListGenerator;
import com.pracainzynierska.controller.service.impl.JsonBuilderServiceImpl;
import com.pracainzynierska.controller.service.impl.TemplateService;
import com.pracainzynierska.controller.service.impl.UserService;
import com.pracainzynierska.model.dto.FileDTO;
import com.pracainzynierska.model.dto.SaveFileResponseDTO;
import com.pracainzynierska.model.dto.ScriptRunnerResponseDTO;
import com.pracainzynierska.model.entities.Template;
import com.pracainzynierska.model.entities.User;
import com.pracainzynierska.model.enums.NodeType;
import javafx.util.Pair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Locale;


@Controller
public class MainController {
    private static final Logger LOG = Logger.getLogger(MainController.class);
    private final ShellRunnerService shellRunnerService;
    private TemplateService templateService;
    private UserService userService;
    private TemplateListGenerator templateListGenerator;
    private JsonBuilderService jsonBuilderService;
    private FileService fileService;

    @Autowired
    public MainController(ShellRunnerService shellRunnerService, UserService userService,
                          TemplateListGenerator templateListGenerator, JsonBuilderService jsonBuilderService,
                          TemplateService templateService, FileService fileService) {
        this.shellRunnerService = shellRunnerService;
        this.userService = userService;
        this.templateListGenerator = templateListGenerator;
        this.jsonBuilderService = jsonBuilderService;
        this.templateService = templateService;
        this.fileService = fileService;
    }

    @RequestMapping(value = "/runScript", method = RequestMethod.GET)
    @ResponseBody
    public ScriptRunnerResponseDTO runScript(@RequestParam("templateId") Integer templateId,
                                             @RequestParam("templateName") String templateName) {
        Template template = templateService.getTemplateByIdAndName(templateId, templateName);
        String command = template.getContent();
        ScriptRunnerResponseDTO scriptRunnerResponse = new ScriptRunnerResponseDTO();
        try {
            scriptRunnerResponse.setExecutionSuccess(true);
            LOG.info("Executing command " + command + ". Result is: " +
                    shellRunnerService.runScript(command));
        } catch (IOException e) {
            scriptRunnerResponse.setExecutionSuccess(false);
            LOG.error("Command: " + command + " execution failed!", e);
        } catch (InterruptedException e) {
            scriptRunnerResponse.setExecutionSuccess(false);
            LOG.error("Intterupted exception while execution command: " + command, e);
        }
        return scriptRunnerResponse;
    }

    @RequestMapping(value = "/getFileContent", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getFileContent(@RequestParam("templateId") Integer templateId,
                                 @RequestParam("templateName") String templateName) {
        String result = "";
        try {
            Template template = templateService.getTemplateByIdAndName(templateId, templateName);
            result = fileService.getFileContent(template.getContent());
        } catch (IOException e) {
            LOG.error("Error reading file from path: ");
            e.printStackTrace();
        }
        return jsonBuilderService.buildFileResponse(result);
    }

    @RequestMapping(value = "/saveFile", method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public SaveFileResponseDTO saveFile(@RequestParam("templateId") Integer templateId,
                                        @RequestParam("templateName") String templateName,
                                        @RequestBody FileDTO file){
        SaveFileResponseDTO saveFileResponse = new SaveFileResponseDTO();
        Template template = templateService.getTemplateByIdAndName(templateId, templateName);
        try {
            fileService.writeToFile(template.getContent(),file.getFileContent());
            saveFileResponse.setWritingSuccess(true);
        } catch (IOException e) {
            saveFileResponse.setWritingSuccess(false);
        }
        return saveFileResponse;
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
        return jsonBuilderService.buildTemplateListResponse(templateGeneratorResult.getKey(),
                        templateGeneratorResult.getValue());
    }
}
