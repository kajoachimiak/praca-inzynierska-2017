package com.pracainzynierska.controller.service;

import com.pracainzynierska.model.entities.EventHistory;
import com.pracainzynierska.model.entities.Template;

import java.util.List;

/**
 * Created by karol on 30.01.18.
 */
public interface AccountingService {
    void logEvent(Template template, String command, String result);
    void logEvent(Template template, String command);
    List<EventHistory> getEventHistoryByTemplate(Template template);
}
