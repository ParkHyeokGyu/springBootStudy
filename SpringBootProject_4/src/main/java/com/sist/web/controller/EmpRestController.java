package com.sist.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.EmpDAO;
import com.sist.web.entity.Emp;

@RestController
@CrossOrigin(origins = "*")
public class EmpRestController {
	private EmpDAO dao;
	
	public EmpRestController(EmpDAO dao) {
		this.dao=dao;
	}
	
	@GetMapping("/emp/list")
	public ResponseEntity<List<Emp>> emp_list() {
		List<Emp> list=new ArrayList<>();
		try {
			list=dao.findAll();
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Emp>>(list, HttpStatus.OK);
	}
	@GetMapping("/emp/detail/{empno}")
	public ResponseEntity<Emp> empDetailData(@PathVariable("empno") int empno) {
		Emp emp=new Emp();
		try {
			emp=dao.findByEmpno(empno);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Emp>(emp, HttpStatus.OK);
	}
}
