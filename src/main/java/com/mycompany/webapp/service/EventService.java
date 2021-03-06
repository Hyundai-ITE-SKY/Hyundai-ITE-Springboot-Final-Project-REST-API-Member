package com.mycompany.webapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.mycompany.webapp.dao.EventDao;
import com.mycompany.webapp.dto.Event;
import com.mycompany.webapp.dto.Events;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EventService {
	@Resource
	EventDao eventDao;
	
	public Event getEvent(int eid) {
		return eventDao.getEvent(eid);
	}
	
	public int updateEvent(Event event) {
		return eventDao.updateEvent(event);
	}
	
	public List<Event> getEvents() {
		return eventDao.getEvents();
	}
}
