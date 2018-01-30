package com.pracainzynierska.model.dao;

import com.pracainzynierska.model.entities.EventHistory;

/**
 * Created by karol on 30.01.18.
 */
public interface EventHistoryDao {
    void createEvent(EventHistory eventHistory);
}
