package com.pracainzynierska.controller.service;

import com.pracainzynierska.exceptions.EnvVariableExctractionException;
import com.pracainzynierska.model.entities.Template;
import com.pracainzynierska.model.entities.User;

/**
 * Created by karol on 28.01.18.
 */
public interface ArgumentParserService {
    String parseArguments(String contentToParse, Template template, User user) throws EnvVariableExctractionException;
}
