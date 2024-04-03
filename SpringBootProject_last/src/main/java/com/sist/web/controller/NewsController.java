package com.sist.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.NewsVO;
import com.sist.web.manager.NewsManager;

@RestController
@CrossOrigin(origins = "*")
public class NewsController {
	@Autowired
	private NewsManager nm;
	
	@GetMapping("/news/list/{fd}")
	public ResponseEntity<List<NewsVO>> newsListData(@PathVariable("fd") String fd) {
		List<NewsVO> nList=new ArrayList<>();
		try {
			nList=nm.newsFind(fd);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<NewsVO>>(nList, HttpStatus.OK);
	}
	
}
