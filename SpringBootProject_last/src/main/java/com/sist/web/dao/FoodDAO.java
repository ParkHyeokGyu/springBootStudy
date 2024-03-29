package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.Food;

public interface FoodDAO extends JpaRepository<Food, Integer> {
	@Query(value = "SELECT * "
			+ "FROM food_house "
			+ "ORDER BY fno "
			+ "LIMIT 0,8",nativeQuery = true)
	public List<Food> foodMainListData();
	@Query(value = "SELECT * "
			+ "FROM food_house "
			+ "ORDER BY fno "
			+ "LIMIT 0,1",nativeQuery = true)
	public Food foodMainOneData();
	@Query(value = "SELECT * "
			+ "FROM food_house "
			+ "ORDER BY fno "
			+ "LIMIT :start,20",nativeQuery = true)
	public List<Food> foodListData(@Param("start") int start);
	public Food findByFno(int fno);
	@Query(value = "SELECT * "
			+ "FROM food_house "
			+ "WHERE address LIKE CONCAT('%',:address,'%') "
			+ "ORDER BY fno "
			+ "LIMIT :start,20",nativeQuery = true)
	public List<Food> foodFindData(@Param("address") String address,@Param("start") int start);
	@Query(value = "SELECT COUNT(*) "
			+ "FROM food_house "
			+ "WHERE address LIKE CONCAT('%',:address,'%')",nativeQuery = true)
	public int foodFindTotalCount(@Param("address") String address);
}
