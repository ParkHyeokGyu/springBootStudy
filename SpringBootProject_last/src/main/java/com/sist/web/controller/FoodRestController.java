package com.sist.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.FoodDAO;
import com.sist.web.entity.Food;

@RestController
@CrossOrigin(origins = "*")
public class FoodRestController {
	@Autowired
	private FoodDAO fDao;
	
	@GetMapping("/food/list/{page}")
	public ResponseEntity<Map> food_list(@PathVariable("page") int page) {
		Map map=new HashMap();
		try {
			int rowSize=20;
			int start=(page*rowSize)-rowSize;
			List<Food> fList=fDao.foodListData(start);
			int totalcount=(int) fDao.count();
			map.put("fList", fList);
			map.put("totalcount", totalcount);
			map.put("curpage", page);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}
	@PostMapping("/food/find")
	public ResponseEntity<Map> food_find(@RequestParam int page,@RequestParam String address) {
		Map map=new HashMap();
		try {
			int rowSize=20;
			int start=(page*rowSize)-rowSize;
			List<Food> fList=fDao.foodFindData(address, start);
			int totalcount=fDao.foodFindTotalCount(address);
			map.put("fList", fList);
			map.put("totalcount", totalcount);
			map.put("curpage", page);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}
	
	
}
