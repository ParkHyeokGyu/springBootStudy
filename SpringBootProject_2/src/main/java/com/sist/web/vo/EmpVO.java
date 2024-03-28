package com.sist.web.vo;

import java.util.Date;

import lombok.Data;

@Data
public class EmpVO {
	private int empno,sal,comm,deptno,mgr;
	private String ename,job;
	private Date hiredate;
}
