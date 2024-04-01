package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.RecipeEntity;

public interface RecipeChefDAO extends JpaRepository<RecipeEntity, Integer> {
	@Query(value = "SELECT * "
			+ "FROM recipe "
			+ "WHERE chef=:chef "
			+ "ORDER BY no "
			+ "LIMIT :start,20",nativeQuery = true)
	public List<RecipeEntity> chefRecipeData(@Param("chef") String chef,@Param("start") int start);
	@Query(value = "SELECT COUNT(*) "
			+ "FROM recipe "
			+ "WHERE chef=:chef",nativeQuery = true)
	public int chefRecipeTotalCount(@Param("chef") String chef);
}
