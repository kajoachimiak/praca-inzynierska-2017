package com.kjoachimiak.service.impl;

import com.kjoachimiak.service.AccountingService;
import com.kjoachimiak.model.dao.EventHistoryDao;
import com.kjoachimiak.model.entities.EventHistory;
import com.kjoachimiak.model.entities.Template;
import com.kjoachimiak.model.entities.User;
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
    public void logEvent(User user, Template template, String command) {
        String userName = user.getLogin();
        String groupName = "";
        String editionName = "";
        String courseName = "";
        if (null != user.getGroup()){
            groupName = user.getGroup().getName();
            if (null != user.getGroup().getEdition()){
                editionName = user.getGroup().getEdition().getName();
                if(null != user.getGroup().getEdition().getCourse()){
                    courseName = user.getGroup().getEdition().getCourse().getName();
                }
            }
        }
        EventHistory eventHistory = new EventHistory();
        eventHistory.setTemplate(template);
        eventHistory.setUserContext(userName);
        eventHistory.setGroupContext(groupName);
        eventHistory.setEditionContext(editionName);
        eventHistory.setCourseContext(courseName);
        eventHistory.setContent(command);
        eventHistory.setExecutionTime(new Date());
        eventHistoryDao.createEvent(eventHistory);
    }
}
