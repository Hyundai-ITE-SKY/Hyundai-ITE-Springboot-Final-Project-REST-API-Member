package com.mycompany.webapp.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.webapp.dto.Event;
import com.mycompany.webapp.service.EventService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/event")
@Slf4j
public class EventController {
	@Resource
	private EventService eventService;
	
	@GetMapping("/{eid}")
	public Event getEvent(@PathVariable int eid) {
		log.info(eid+"hello");
		return eventService.getEvent(eid);
	}
	
	//수량 감소
	@PostMapping("/updateevent")
	public int updateEvent(Event event) {
		log.info("실행");
		return eventService.updateEvent(event);
	}
	
}
