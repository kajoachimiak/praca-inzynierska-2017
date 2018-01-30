package com.pracainzynierska.controller.service.impl;

import com.pracainzynierska.controller.service.ArgumentParserService;
import com.pracainzynierska.exceptions.EnvVariableExctractionException;
import com.pracainzynierska.model.entities.Template;
import com.pracainzynierska.enums.Tag;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by karol on 28.01.18.
 */
@Service
public class ArgumentParserServiceImpl implements ArgumentParserService {
    private static final Logger LOG = Logger.getLogger(ArgumentParserServiceImpl.class);
    private static final String PATTERN = "%([^%]+)%";
    private static final String BRACKET_PATTERN = "\\(([^)]+)\\)";
    private static final String PERCENT_PATTERN = "%{2}";

    @Override
    public String parseArguments(String contentToParse, Template template) throws IndexOutOfBoundsException,
            SecurityException, IllegalStateException, EnvVariableExctractionException {
        Pattern pattern = Pattern.compile(PATTERN, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(contentToParse);
        while (matcher.find()) {
            String match = matcher.group();
            if (Tag.USER.getCode().equals(match)) {
                contentToParse = contentToParse.replaceAll(Tag.USER.getCode(),
                        template.getUser().getLogin());
                matcher = pattern.matcher(contentToParse);
            }else if(Tag.EDITION.getCode().equals(match)){
                contentToParse = contentToParse.replaceAll(Tag.EDITION.getCode(),
                        template.getEdition().getName());
                matcher = pattern.matcher(contentToParse);
            }else if(Tag.GROUP.getCode().equals(match)){
                contentToParse = contentToParse.replaceAll(Tag.GROUP.getCode(),
                        template.getGroup().getName());
                matcher = pattern.matcher(contentToParse);
            }else if(Tag.COURSE.getCode().equals(match)){
                contentToParse = contentToParse.replaceAll(Tag.COURSE.getCode(),
                        template.getCourse().getName());
                matcher = pattern.matcher(contentToParse);
            }else if(match.matches(Tag.ENV.getCode())){
                Pattern patternEnv = Pattern.compile(BRACKET_PATTERN, Pattern.DOTALL);
                Matcher matcherBracket = patternEnv.matcher(match);
                String extractedVar;
                while (matcherBracket.find()){
                    extractedVar = System.getenv(matcherBracket.group(1));
                    if(null != extractedVar){
                        contentToParse = contentToParse.replaceAll(Tag.ENV.getCode(), extractedVar);
                    }else {
                        LOG.error("Failed to extract system environment variable! ");
                        throw new EnvVariableExctractionException("Requested environment variable is null or does not exist.");
                    }

                }
                matcher = pattern.matcher(contentToParse);
            }else if(match.matches(Tag.DATE.getCode())){
                Pattern patternBracket = Pattern.compile(BRACKET_PATTERN, Pattern.DOTALL);
                Matcher matcherBracket = patternBracket.matcher(match);
                SimpleDateFormat dateFormat;
                while (matcherBracket.find()){
                    dateFormat = new SimpleDateFormat(matcherBracket.group(1));
                    contentToParse = contentToParse.replaceAll(Tag.DATE.getCode(),dateFormat.format(new Date()));
                }
                matcher = pattern.matcher(contentToParse);
            }
        }
        Pattern patternPercent = Pattern.compile(PERCENT_PATTERN, Pattern.DOTALL);
        Matcher matcherPercent = patternPercent.matcher(contentToParse);
        while (matcherPercent.find()){
            contentToParse = contentToParse.replaceAll(matcherPercent.group(),"%");
            matcherPercent = patternPercent.matcher(contentToParse);
        }

        return contentToParse;
    }

}
