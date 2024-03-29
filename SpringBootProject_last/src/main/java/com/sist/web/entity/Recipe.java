package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/*
NO int 
TITLE text 
POSTER text 
CHEF text 
HIT int
 */
@Entity(name = "recipe2")
@Data
public class Recipe {
	@Id
	private int no;
	private String title,poster,chef;
	private int hit;
}
