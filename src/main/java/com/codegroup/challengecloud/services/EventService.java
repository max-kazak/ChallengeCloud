package com.codegroup.challengecloud.services;

import com.codegroup.challengecloud.dao.EventDao;
import com.codegroup.challengecloud.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Krokhin on 15.04.2015.
 */
@Service("eventService")
public class EventService {
    @Autowired
    EventDao eventDao;

    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Transactional
    public void updateEvent(Event event) {
        eventDao.update(event);
    }

    @Transactional
    public void deleteEvent(Event event) {
        eventDao.delete(event);
    }

    @Transactional
    public Event createEvent(String id, String name, String clazz) {
        Event event = new Event();
        event.setId(id);
        event.setName(name);
        event.setClazz(clazz);

        eventDao.save(event);

        return event;
    }

    @Transactional
    public Event findById(String id) {
        return eventDao.findById(id);
    }

    @Transactional
    public List<Event> findByName(String name) {
        return eventDao.findByName(name);
    }

    @Transactional
    public List<Event> findByClazz(String clazz) {
        return eventDao.findByClazz(clazz);
    }
}
