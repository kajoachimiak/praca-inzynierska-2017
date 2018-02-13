package com.kjoachimiak.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kjoachimiak.helpers.enums.HttpCodes;
import com.kjoachimiak.service.*;
import com.kjoachimiak.service.impl.TemplateService;
import com.kjoachimiak.service.impl.UserService;
import com.kjoachimiak.exceptions.EnvVariableExctractionException;
import com.kjoachimiak.exceptions.ForbiddenException;
import com.kjoachimiak.dto.FileDTO;
import com.kjoachimiak.dto.SaveFileResponseDTO;
import com.kjoachimiak.dto.ScriptRunnerResponseDTO;
import com.kjoachimiak.dto.EventHistoryResponseDTO;
import com.kjoachimiak.model.entities.Template;
import com.kjoachimiak.model.entities.User;
import com.kjoachimiak.helpers.enums.NodeType;
import javafx.util.Pair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


@Controller
public class MainController {
    private static final Logger LOG = Logger.getLogger(MainController.class);
    private final ShellRunnerService shellRunnerService;
    private TemplateService templateService;
    private UserService userService;
    private TemplateListGenerator templateListGenerator;
    private ResponseBuilderService responseBuilderService;
    private FileService fileService;
    private ArgumentParserService parserService;
    private AccountingService accountingService;

    @Autowired
    public MainController(ShellRunnerService shellRunnerService, UserService userService,
                          TemplateListGenerator templateListGenerator, ResponseBuilderService responseBuilderService,
                          TemplateService templateService, FileService fileService, ArgumentParserService parserService, AccountingService accountingService) {
        this.shellRunnerService = shellRunnerService;
        this.userService = userService;
        this.templateListGenerator = templateListGenerator;
        this.responseBuilderService = responseBuilderService;
        this.templateService = templateService;
        this.fileService = fileService;
        this.parserService = parserService;
        this.accountingService = accountingService;
    }

    @RequestMapping(value = "/runScript", method = RequestMethod.GET)
    @ResponseBody
    public ScriptRunnerResponseDTO runScript(@RequestParam("templateId") Integer templateId,
                                             @RequestParam("templateName") String templateName,
                                             Principal principal, HttpSession session) {
        Template template = templateService.getTemplateByIdAndName(templateId, templateName);
        User user = loadUser(principal);
        if (!templateService.isUserAuthorizedToUseTemplate(user, template)){
            LOG.error("User: " + user.getLogin() + " is not authorized to use template with id: "+ templateId);
            throw new ForbiddenException();
        }
        String command = "";
        ScriptRunnerResponseDTO scriptRunnerResponse = new ScriptRunnerResponseDTO();

        try {
            command = parserService.parseArguments(template.getContent(), template, user);
            Future<Long> processStatus = shellRunnerService.runScript(command);
            String processId = new Date().toString();
            session.setAttribute(processId,processStatus);
            accountingService.logEvent(user,template, command);

            scriptRunnerResponse.setExecutionSuccess(true);
            scriptRunnerResponse.setMessage(processId);

            LOG.info("Executing command " + command);
        } catch (IOException e) {
            scriptRunnerResponse.setExecutionSuccess(false);
            LOG.error("Command: " + command + " execution failed with exception:", e);
        } catch (InterruptedException e) {
            scriptRunnerResponse.setExecutionSuccess(false);
            LOG.error("Command: " + command + " execution interrupted with exception: ", e);
        } catch (EnvVariableExctractionException e) {
            scriptRunnerResponse.setExecutionSuccess(false);
            LOG.error("Command: " + command + " execution failed with exception: ", e);
        }
        return scriptRunnerResponse;
    }

    @RequestMapping(value = "/processStatus", method = RequestMethod.GET)
    @ResponseBody
    public HttpCodes getProcessStatus(@RequestParam("processId") String processId, HttpSession session) {
        Future<Long> process = (Future<Long>) session.getAttribute(processId);
        if (process.isDone()) {
            try {
                if (process.get() == 0){
                    return HttpCodes.HTTP_SUCCESS;
                }else {
                    return HttpCodes.HTTP_FAILED;
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return HttpCodes.HTTP_FAILED;
            }
        }else {
            return HttpCodes.HTTP_PROCESSING;
        }
    }

    @RequestMapping(value = "/getFileContent", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getFileContent(@RequestParam("templateId") Integer templateId,
                                 @RequestParam("templateName") String templateName,
                                 Principal principal) {
        String result = "";
        User user = loadUser(principal);
        Template template = templateService.getTemplateByIdAndName(templateId, templateName);
        if (!templateService.isUserAuthorizedToUseTemplate(user, template)){
            LOG.error("User: " + user.getLogin() + " is not authorized to use template with id: "+ templateId);
            throw new ForbiddenException();
        }
        Boolean status = false;
        try {
            String parsedContent = parserService.parseArguments(template.getContent(), template, user);
            result = fileService.getFileContent(parsedContent);
            status = true;
            accountingService.logEvent(user, template, parsedContent);
        } catch (IOException e) {
            LOG.error("Error reading file from path: ", e);
            status = false;
        } catch (EnvVariableExctractionException e) {
            e.printStackTrace();
            status = false;
        }
        return responseBuilderService.buildFileResponse(result, status);
    }

    @RequestMapping(value = "/saveFile", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public SaveFileResponseDTO saveFile(@RequestParam("templateId") Integer templateId,
                                        @RequestParam("templateName") String templateName,
                                        @RequestBody FileDTO file,
                                        Principal principal) {
        SaveFileResponseDTO saveFileResponse = new SaveFileResponseDTO();
        Template template = templateService.getTemplateByIdAndName(templateId, templateName);
        User user = loadUser(principal);
        if (!templateService.isUserAuthorizedToUseTemplate(user, template)){
            LOG.error("User: " + user.getLogin() + " is not authorized to use template with id: "+ templateId);
            throw new ForbiddenException();
        }
        try {
            String parsedContent = parserService.parseArguments(template.getContent(), template, user);
            fileService.writeToFile(parsedContent, file.getFileContent());
            saveFileResponse.setWritingSuccess(true);
            accountingService.logEvent(user, template, parsedContent);
        } catch (IOException e) {
            saveFileResponse.setWritingSuccess(false);
        } catch (EnvVariableExctractionException e) {
            e.printStackTrace();
        }
        return saveFileResponse;
    }

    @RequestMapping(value = "/getUrl", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getUrl(@RequestParam("templateId") Integer templateId,
                         @RequestParam("templateName") String templateName,
                         Principal principal) {
        Template template = templateService.getTemplateByIdAndName(templateId, templateName);
        String parsedContent = null;
        User user = loadUser(principal);
        if (!templateService.isUserAuthorizedToUseTemplate(user, template)){
            LOG.error("User: " + user.getLogin() + " is not authorized to use template with id: "+ templateId);
            throw new ForbiddenException();
        }

        try {
            parsedContent = parserService.parseArguments(template.getContent(), template, user);
            accountingService.logEvent(user, template, parsedContent);
        } catch (EnvVariableExctractionException e) {
            e.printStackTrace();
        }
        return responseBuilderService.buildUrlResponse(parsedContent);
    }

    @RequestMapping(value = "/loadTemplateHistory", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public EventHistoryResponseDTO getTemplateHistory(@RequestParam("templateId") Integer templateId,
                                                      @RequestParam("templateName") String templateName) {
        Template template = templateService.getTemplateByIdAndName(templateId, templateName);
        return responseBuilderService.buildEventHistoryResponse(template,template.getEventHistoryList());
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
        User user = loadUser(principal);
        return responseBuilderService.buildUserRelationsResponse(user);
    }

    @RequestMapping(value = "/treeNodeTemplates", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getTreeNodeTemplates(Principal principal, @RequestParam("nodeType") String nodeType) {
        String username = userService.getCurrentUserUsername(principal);
        User user = userService.getUserByLogin(username);

        Pair<String, List<Template>> templateGeneratorResult =
                templateListGenerator.generateTemplateList(NodeType.valueOf(nodeType.toUpperCase(Locale.ENGLISH)), user);
        return responseBuilderService.buildTemplateListResponse(templateGeneratorResult.getKey(),
                templateGeneratorResult.getValue());
    }
    private User loadUser(Principal principal){
        String username = userService.getCurrentUserUsername(principal);
        return userService.getUserByLogin(username);
    }


}
