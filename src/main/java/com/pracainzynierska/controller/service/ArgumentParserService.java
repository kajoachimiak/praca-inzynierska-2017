package com.pracainzynierska.controller.service;

import com.pracainzynierska.exceptions.EnvVariableExctractionException;
import com.pracainzynierska.model.entities.Template;

/**
 * Created by karol on 28.01.18.
 */
public interface ArgumentParserService {
    String parseArguments(String contentToParse, Template template) throws EnvVariableExctractionException;
}
