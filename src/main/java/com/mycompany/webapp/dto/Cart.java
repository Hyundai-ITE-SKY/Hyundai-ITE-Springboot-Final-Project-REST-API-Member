package com.mycompany.webapp.dto;

import lombok.Data;

@Data
public class Cart {
	private String mid;
	private String pid;
	private String psize;
	private String pcolor;
	private int pamount;
}
