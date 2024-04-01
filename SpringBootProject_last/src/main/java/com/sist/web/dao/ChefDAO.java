package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.Chef;

public interface ChefDAO extends JpaRepository<Chef, Integer> {
	@Query(value = "SELECT * "
			+ "FROM chef "
			+ "ORDER BY cno "
			+ "LIMIT :start,20",nativeQuery = true)
	public List<Chef> chefListData(@Param("start") int start);
}
