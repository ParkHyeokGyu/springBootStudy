package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.Emp;
@Repository
public interface EmpDAO extends JpaRepository<Emp, Integer> {
	public Emp findByEmpno(int empno);
}
