package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/*
FNO int PK 
POSTER text 
NAME text 
TYPE text 
ADDRESS text 
PHONE text 
SCORE double 
THEME text 
PRICE text 
TIME text 
SEAT text 
CONTENT text 
LINK text 
HIT int 
LIKE_COUNT int 
RDAY text 
JJIM_COUNT int
 */
@Entity(name = "food_house")
@Data
public class Food {
	@Id
	private int fno;
	private String poster,name,type,address,phone,theme,price,time,seat,content,link;
	private int hit,like_count,jjim_count;
	private double score;
}
