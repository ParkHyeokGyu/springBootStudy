package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.FoodDAO;
import com.sist.web.dao.RecipeDAO;
import com.sist.web.entity.Food;
import com.sist.web.entity.Recipe;

@RestController
@CrossOrigin(origins = "*")
public class MainRestController {
	@Autowired
	private RecipeDAO rDao;
	@Autowired
	private FoodDAO fDao;
	
	@GetMapping("/main")
	public ResponseEntity<Map> main_data() {
		Map map=new HashMap();
		try {
			List<Food> fList=fDao.foodMainListData();
			Food fOne=fDao.foodMainOneData();
			List<Recipe> rList=rDao.recipeMainListData();
			map.put("fList", fList);
			map.put("fOne", fOne);
			map.put("rList", rList);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map>(map,HttpStatus.OK);
	}
}
