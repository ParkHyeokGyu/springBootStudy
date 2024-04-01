package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sist.web.entity.RecipeDetail;

public interface RecipeDetailDAO extends JpaRepository<RecipeDetail, Integer> {
	public RecipeDetail findByNo(int no);
}
