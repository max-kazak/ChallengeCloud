package com.codegroup.challengecloud.dao.impl;

import com.codegroup.challengecloud.dao.EventDao;
import com.codegroup.challengecloud.model.Event;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Krokhin on 15.04.2015.
 */
@Repository("eventDao")
public class EventDaoMySQL extends HibernateDao implements EventDao {

    private static final Logger log = Logger.getLogger(EventDaoMySQL.class);

    @Override
    public void save(Event event) {
        log.debug("saving event " + event.getId());
        getSession().save(event);
    }

    @Override
    public void update(Event event) {
        log.debug("updating event " + event.getId());
        getSession().update(event);
    }

    @Override
    public void delete(Event event) {
        log.debug("removing event " + event.getId());
        getSession().delete(event);
    }

    @Override
    public Event findById(String id) {
        log.debug("looking for event by id = " + id);
        List list = find("from Event where event_id = ?", id);
        return (Event) list.get(0);
    }

    @Override
    public List<Event> findByName(String name) {
        log.debug("looking for event by name = " + name);
        List list = find("from Event where name = ?", name);
        return (List<Event>) (List<?>) list;
    }

    @Override
    public List<Event> findByClazz(String clazz) {
        log.debug("looking for event by class = " + clazz);
        List list = find("from Event where class = ?", clazz);
        return (List<Event>) (List<?>) list;
    }
}
