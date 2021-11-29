package com.mycompany.webapp.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Coupon {
	private String ccode;
	private int eid;//미사용:0, 사용:1, 기한만료:2
	private String mid;
	private String cname;
	private Date cstartdate;
	private Date cenddate;
	private int cstate;
}
