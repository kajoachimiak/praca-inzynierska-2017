package com.pracainzynierska.controller.service;

import com.pracainzynierska.model.entities.Template;

/**
 * Created by karol on 30.01.18.
 */
public interface AccountingService {
    void logEvent(Template template, String command, String result);
    void logEvent(Template template, String command);
}
