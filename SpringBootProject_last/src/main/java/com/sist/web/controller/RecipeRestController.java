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

import com.sist.web.dao.RecipeDAO;
import com.sist.web.entity.Recipe;

@RestController
@CrossOrigin(origins = "*")
public class RecipeRestController {
	@Autowired
	private RecipeDAO rDao;
	
	@GetMapping("/recipe/list/{page}")
	public ResponseEntity<Map> recipe_list(@PathVariable("page") int page) {
		Map map=new HashMap<>();
		try {
			int rowSize=20;
			int start=(page*rowSize)-rowSize;
			List<Recipe> rList=rDao.recipeListData(start);
			int totalcount=(int) rDao.count();
			map.put("rList", rList);
			map.put("totalcount", totalcount);
			map.put("curpage", page);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}
	
}
