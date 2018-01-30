package com.pracainzynierska.controller.service.impl;

import com.pracainzynierska.controller.service.AccountingService;
import com.pracainzynierska.model.dao.EventHistoryDao;
import com.pracainzynierska.model.entities.EventHistory;
import com.pracainzynierska.model.entities.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by karol on 30.01.18.
 */
@Service
public class AccountingServiceImpl implements AccountingService {
    @Autowired
    private EventHistoryDao eventHistoryDao;

    @Override
    public void logEvent(Template template, String command, String result) {
        EventHistory eventHistory = new EventHistory();
        eventHistory.setTemplate(template);
        eventHistory.setContent(command);
        eventHistory.setResult(result);
        eventHistory.setExecutionTime(new Date());
        eventHistoryDao.createEvent(eventHistory);
    }

    @Override
    public void logEvent(Template template, String command) {
        EventHistory eventHistory = new EventHistory();
        eventHistory.setTemplate(template);
        eventHistory.setContent(command);
        eventHistory.setExecutionTime(new Date());
        eventHistoryDao.createEvent(eventHistory);
    }
}
