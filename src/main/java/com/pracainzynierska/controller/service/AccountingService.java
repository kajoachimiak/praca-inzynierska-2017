package com.pracainzynierska.controller.service;

import com.pracainzynierska.model.entities.Template;
import com.pracainzynierska.model.entities.User;

/**
 * Created by karol on 30.01.18.
 */
public interface AccountingService {
    void logEvent(Template template, String command, String result);
    void logEvent(User user, Template template, String command);
}
