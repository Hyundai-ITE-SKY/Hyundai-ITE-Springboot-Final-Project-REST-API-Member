package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.Event;
import com.mycompany.webapp.dto.Events;

@Mapper
public interface EventDao {
	public Event getEvent(int eid);
	public int updateEvent(Event event);
	public List<Event> getEvents();
}
