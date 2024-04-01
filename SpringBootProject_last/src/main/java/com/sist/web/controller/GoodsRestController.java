package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.GoodsDAO;
import com.sist.web.entity.GoodsAll;

@RestController
@CrossOrigin(origins = "*")
public class GoodsRestController {
	@Autowired
	private GoodsDAO gDao;
	
	@GetMapping("/goods/list/{page}")
	public ResponseEntity<Map> goods_list(@PathVariable("page") int page) {
		Map map=new HashMap();
		try {
			int rowSize=20;
			int start=(page*rowSize)-rowSize;
			List<GoodsAll> gList=gDao.goodsListData(start);
			int totalcount=(int) gDao.count();
			map.put("gList", gList);
			map.put("totalcount", totalcount);
			map.put("curpage", page);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}
	@GetMapping("/goods/find/{page}/{fd}")
	public ResponseEntity<Map> goods_find(
			@PathVariable("page") int page,
			@PathVariable("fd") String fd
	) {
		Map map=new HashMap();
		try {
			int rowSize=20;
			int start=(page*rowSize)-rowSize;
			List<GoodsAll> gList=gDao.goodsFindData(fd, start);
			int totalcount=gDao.goodsFindTotalCount(fd);
			map.put("gList", gList);
			map.put("totalcount", totalcount);
			map.put("curpage", page);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}
	@GetMapping("/goods/detail/{no}")
	public ResponseEntity<GoodsAll> goods_detail(@PathVariable("no") int no) {
		GoodsAll goods=new GoodsAll();
		try {
			goods=gDao.findByNo(no);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<GoodsAll>(goods, HttpStatus.OK);
	}
}
