package com.sist.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sist.web.vo.EmpVO;

@Mapper
public interface EmpMapper {
	/*
		<select id="empListData" resultType="EmpVO">
			SELECT *
			FROM emp
		</select>
	 */
	public List<EmpVO> empListData();
}
