package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.Recipe;

public interface RecipeDAO extends JpaRepository<Recipe, Integer> {
	@Query(value = "SELECT * "
			+ "FROM recipe2 "
			+ "ORDER BY no "
			+ "LIMIT 0,5",nativeQuery = true)
	public List<Recipe> recipeMainListData();
	@Query(value = "SELECT * "
			+ "FROM recipe2 "
			+ "ORDER BY no "
			+ "LIMIT :start,20",nativeQuery = true)
	public List<Recipe> recipeListData(@Param("start") int start);
	public Recipe findByNo(int no);
	public List<Recipe> findByTitleContaining(String title);
}
