package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.BoardDAO;
import com.sist.web.entity.Board;

@RestController
@CrossOrigin(origins = "*")
public class BoardRestController {
	@Autowired
	private BoardDAO bDao;
	
	@GetMapping("/board/list/{page}")
	public ResponseEntity<Map> board_list(@PathVariable("page") int page) {
		Map map=new HashMap();
		try {
			int rowSize=10;
			int start=(page*rowSize)-rowSize;
			List<Board> bList=bDao.boardListData(start);
			int totalcount=(int) bDao.count();
			int totalpage=(int) (Math.ceil(totalcount/(double)rowSize));
			map.put("bList", bList);
			map.put("totalpage", totalpage);
			map.put("curpage", page);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}
	@GetMapping("/board/detail/{no}")
	public ResponseEntity<Board> board_detail(@PathVariable("no") int no) {
		Board board=new Board();
		try {
			board=bDao.findByNo(no);
			board.setHit(board.getHit()+1);
			bDao.save(board);
			board=bDao.findByNo(no);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}
	@PostMapping("/board/insert")
	public ResponseEntity<Map> board_insert(@RequestBody Board board) {
		Map map=new HashMap();
		try {
			Board _board=bDao.save(board);
			map.put("board", _board);
			map.put("msg", "YES");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map>(map, HttpStatus.CREATED);
	}
	@GetMapping("/board/update/{no}")
	public ResponseEntity<Board> board_update_data(@PathVariable("no") int no) {
		Board board=new Board();
		try {
			board=bDao.findByNo(no);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}
	@PutMapping("/board/update_ok/{no}")
	public ResponseEntity<Map> board_update_ok(@PathVariable("no") int no,@RequestBody Board board) {
		Map map=new HashMap();
		try {
			Board db_data=bDao.findByNo(no);
			if(db_data.getPwd().equals(board.getPwd())) {
				board.setNo(no);
				board.setHit(db_data.getHit());
				Board b=bDao.save(board);
				map.put("board", b);
				map.put("msg", "YES");
			}else {
				map.put("msg", "NO");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}
	@DeleteMapping("/board/delete/{no}/{pwd}")
	public ResponseEntity<Map> board_delete_ok(@PathVariable("no") int no,@PathVariable("pwd") String pwd) {
		Map map=new HashMap();
		try {
			Board db_data=bDao.findByNo(no);
			if(db_data.getPwd().equals(pwd)) {
				bDao.delete(db_data);
				map.put("msg", "YES");
			}else {
				map.put("msg", "NO");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}
}
