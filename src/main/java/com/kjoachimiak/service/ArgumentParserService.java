package com.kjoachimiak.service;

import com.kjoachimiak.exceptions.EnvVariableExctractionException;
import com.kjoachimiak.model.entities.Template;
import com.kjoachimiak.model.entities.User;

/**
 * Created by karol on 28.01.18.
 */
public interface ArgumentParserService {
    String parseArguments(String contentToParse, Template template, User user) throws EnvVariableExctractionException;
}
