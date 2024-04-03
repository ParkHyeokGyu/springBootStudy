package com.sist.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.MemberDAO;
import com.sist.web.entity.Member;

@RestController
@CrossOrigin(origins = "*")
public class MemberRestController {
	@Autowired
	private MemberDAO mDao;
	
	@PostMapping("/member/login")
	public ResponseEntity<Map> member_login(@RequestBody Member member) {
		Map map=new HashMap();
		try {
			int idCount=mDao.idCount(member.getId());
			if(idCount>0) {
				Member db_member=mDao.findById(member.getId());
				if(db_member.getPwd().equals(member.getPwd())) {
					map.put("member", db_member);
					map.put("msg", "OK");
				}else {
					map.put("msg", "NOPWD");
				}
			}else {
				map.put("msg", "NOID");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}
	
}
