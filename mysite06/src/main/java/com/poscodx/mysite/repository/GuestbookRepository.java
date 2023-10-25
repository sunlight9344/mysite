package com.poscodx.mysite.repository;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.mysite.vo.GuestbookVo;

@Repository
public class GuestbookRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestbookVo> findAll() {
		return sqlSession.selectList("guestbook.findAll");
	}

	public Boolean insert(GuestbookVo vo) {
		return sqlSession.insert("guestbook.insert", vo) == 1;
	}

	public Boolean deleteByNo(int no, String password) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("password", password);
		
		return sqlSession.delete("guestbook.deleteByNo", map) == 1;
	}

	public List<GuestbookVo> findTop(int sno, int k) {
		Map<String, Object> map = new HashMap<>();
		map.put("sno", sno);
		map.put("k", k);
		return sqlSession.selectList("guestbook.findTop", map);
	}
}
