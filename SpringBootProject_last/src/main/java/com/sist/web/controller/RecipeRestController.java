package com.sist.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.ChefDAO;
import com.sist.web.dao.RecipeChefDAO;
import com.sist.web.dao.RecipeDAO;
import com.sist.web.dao.RecipeDetailDAO;
import com.sist.web.entity.Chef;
import com.sist.web.entity.Recipe;
import com.sist.web.entity.RecipeDetail;
import com.sist.web.entity.RecipeEntity;

@RestController
@CrossOrigin(origins = "*")
public class RecipeRestController {
	@Autowired
	private RecipeDAO rDao;
	@Autowired
	private RecipeDetailDAO rdDao;
	@Autowired
	private ChefDAO cDao;
	@Autowired
	private RecipeChefDAO rcDao;
	
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
	@GetMapping("/recipe/find/{page}/{fd}")
	public ResponseEntity<Map> recipe_find(@PathVariable("page") int page,@PathVariable("fd") String fd) {
		Map map=new HashMap();
		try {
			int rowSize=20;
			int start=(page*rowSize)-rowSize;
			List<Recipe> rList=rDao.recipeFindData(fd, start);
			int totalcount=rDao.recipeFindTotalCount(fd);
			map.put("rList", rList);
			map.put("totalcount", totalcount);
			map.put("curpage", page);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}
	@GetMapping("/recipe/detail/{no}")
	public ResponseEntity<Map> recipe_detail(@PathVariable("no") int no) {
		Map map=new HashMap();
		try {
			RecipeDetail rd=rdDao.findByNo(no);
			List<String> pList=new ArrayList<>();
			List<String> mList=new ArrayList<>();
			List<String> sList=new ArrayList<>();
			
			StringTokenizer st=new StringTokenizer(rd.getStuff(), ",");
			while(st.hasMoreTokens()) {
				String stuff=st.nextToken();
				stuff=stuff.replace(" 구매", "");
				sList.add(stuff);
			}
			
			String[] foodmake=rd.getFoodmake().split("\n");
			for(String fm:foodmake) {
				st=new StringTokenizer(fm, "^");
				mList.add(st.nextToken());
				pList.add(st.nextToken());
			}
			map.put("rd", rd);
			map.put("sList", sList);
			map.put("mList", mList);
			map.put("pList", pList);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}
	@GetMapping("/chef/list/{page}")
	public ResponseEntity<Map> chef_list(@PathVariable("page") int page) {
		Map map=new HashMap();
		try {
			int rowSize=20;
			int start=(page*rowSize)-rowSize;
			List<Chef> cList=cDao.chefListData(start);
			int totalcount=(int) cDao.count();
			map.put("cList", cList);
			map.put("totalcount", totalcount);
			map.put("curpage", page);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}
	@GetMapping("/chef/detail/{page}/{chef}")
	public ResponseEntity<Map> chef_recipe_data(
			@PathVariable("page") int page,
			@PathVariable("chef") String chef
	) {
		Map map=new HashMap();
		try {
			int rowSize=20;
			int start=(page*rowSize)-rowSize;
			List<RecipeEntity> cList=rcDao.chefRecipeData(chef, start);
			int totalcount=(int) rcDao.count();
			map.put("cList", cList);
			map.put("totalcount", totalcount);
			map.put("curpage", page);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}
	
}
