package com.kjoachimiak.service;

import com.kjoachimiak.model.entities.Template;
import com.kjoachimiak.model.entities.User;

/**
 * Created by karol on 30.01.18.
 */
public interface AccountingService {
    void logEvent(User user, Template template, String command);
}
