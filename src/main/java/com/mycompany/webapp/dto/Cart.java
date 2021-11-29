package com.mycompany.webapp.dto;

import lombok.Data;

@Data
public class Cart {
	private String mid;
	private String pid;
	private String psize;
	private String pcolor;
	private String aftercolor; //추가
	private String aftersize; //추가
	private int pamount;
}
