package com.mycompany.webapp.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Coupon {
	private String ccode;
	private int eid;
	private String mid;
	private String cname;
	private Date cstartdate;
	private Date cenddate;
	private int cstate;
}
