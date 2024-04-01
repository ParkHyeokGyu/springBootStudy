package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.GoodsAll;

public interface GoodsDAO extends JpaRepository<GoodsAll, Integer> {
	@Query(value = "SELECT * "
			+ "FROM goods_all "
			+ "ORDER BY no "
			+ "LIMIT :start,20",nativeQuery = true)
	public List<GoodsAll> goodsListData(@Param("start") int start);
	@Query(value = "SELECT * "
			+ "FROM goods_all "
			+ "WHERE goods_name LIKE CONCAT('%',:fd,'%') "
			+ "ORDER BY no "
			+ "LIMIT :start,20",nativeQuery = true)
	public List<GoodsAll> goodsFindData(@Param("fd") String fd,@Param("start") int start);
	@Query(value = "SELECT COUNT(*) "
			+ "FROM goods_all "
			+ "WHERE goods_name LIKE CONCAT('%',:fd,'%')",nativeQuery = true)
	public int goodsFindTotalCount(@Param("fd") String fd);
	public GoodsAll findByNo(int no);
}
