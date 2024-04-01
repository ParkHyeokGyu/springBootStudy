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
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.FoodDAO;
import com.sist.web.entity.Food;
/*
	Rest API -> 클라이언트와 서버간의 통신에 필요한 정보를 제공
	-> 유지보수
	   -> 상태코드(정상 수행,에러 발생)를 클라이언트에게 전송
	   -> 기존의 코딩 방식(public Map food_list...)
	   -> 상태코드 전송 방식(public ResponseEntity(Map) food_list...)
	   -> 값과 에러코드를 함계 보낼 수 있다
	   -> get방식 -> ?키=값
	   -> /값/값... -> PathVariable -> 값인지 폴더명인지 구분이 어렵다
 */
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
	@GetMapping("/food/find/{page}/{fd}")
	public ResponseEntity<Map> food_find(@PathVariable("page") int page,@PathVariable("fd") String fd) {
		Map map=new HashMap();
		try {
			int rowSize=20;
			int start=(page*rowSize)-rowSize;
			List<Food> fList=fDao.foodFindData(fd, start);
			int totalcount=fDao.foodFindTotalCount(fd);
			map.put("fList", fList);
			map.put("totalcount", totalcount);
			map.put("curpage", page);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}
	@GetMapping("/food/detail/{fno}")
	public ResponseEntity<Food> food_detail(@PathVariable("fno") int fno) {
		Food food=new Food();
		try {
			food=fDao.findByFno(fno);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Food>(food, HttpStatus.OK);
	}
	
}
