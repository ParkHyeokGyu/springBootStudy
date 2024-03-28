package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sist.web.entity.Emp;

public interface EmpDAO extends JpaRepository<Emp, Integer> {
	
}
