package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.model.Event;

import java.util.List;

/**
 * Created by Krokhin on 15.04.2015.
 */
public interface EventDao {
    public void save(Event event);

    public void update(Event event);

    public void delete(Event event);

    public Event findById(String id);

    public List<Event> findByName(String name);

    public List<Event> findByClazz(String clazz);
}
