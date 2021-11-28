package com.mycompany.webapp.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.Event;

@Mapper
public interface EventDao {
	public Event getEvent(int eid);
}
