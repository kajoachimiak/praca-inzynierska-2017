package com.kjoachimiak.model.dao;

import com.kjoachimiak.model.entities.EventHistory;

/**
 * Created by karol on 30.01.18.
 */
public interface EventHistoryDao {
    void createEvent(EventHistory eventHistory);
}
