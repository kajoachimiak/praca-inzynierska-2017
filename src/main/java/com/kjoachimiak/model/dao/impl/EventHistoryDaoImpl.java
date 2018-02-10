package com.kjoachimiak.model.dao.impl;

import com.kjoachimiak.model.dao.EventHistoryDao;
import com.kjoachimiak.model.entities.EventHistory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created by karol on 30.01.18.
 */
@Transactional
@Repository
public class EventHistoryDaoImpl implements EventHistoryDao {
    @PersistenceContext
    private EntityManager em;

    public EventHistoryDaoImpl() {
    }

    @Override
    public void createEvent(EventHistory eventHistory) {
        em.persist(eventHistory);
    }
}
