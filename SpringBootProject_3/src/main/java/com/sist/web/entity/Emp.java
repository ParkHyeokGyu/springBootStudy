package com.sist.web.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Emp {
	@Id
	private int empno;
	private int sal,deptno,mgr,comm;
	private String ename,job,hiredate;
//	private Date hiredate;
}
