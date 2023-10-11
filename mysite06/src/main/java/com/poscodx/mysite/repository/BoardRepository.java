package com.poscodx.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void UpdateHit(int no) {
		sqlSession.update("board.updateHit", no);
	}
	
	public int findAllCount(String tempKwd) {
		String kwd = "%" + tempKwd + "%";
		return sqlSession.selectOne("board.count", kwd);
	}

	public void ModifyByNo(BoardVo boardVo) {
		sqlSession.update("board.modify", boardVo);
	}
	
	public List<BoardVo> findAll(int curPage, int listPerPage, String kwd) {
		Map<String, Object> map = new HashMap<>();
		int tempCurPage = (curPage-1)*listPerPage;
		map.put("curPage", tempCurPage);
		map.put("listPerPage", listPerPage);
		map.put("kwd", "%" + kwd + "%");
		return sqlSession.selectList("board.findAll", map);
	}
	
	public BoardVo getBoardInfoByNo(int no) {
		return sqlSession.selectOne("board.getBoardInfoByNo", no);
	}
	
	public void write(BoardVo boardVo) {
		sqlSession.insert("board.insert", boardVo);
	}

	public void refreshNo(int g_no, int o_no) {
		Map<String, Object> map = new HashMap<>();
		map.put("g_no", g_no);
		map.put("o_no", o_no);
		sqlSession.update("board.refresh",map);
	}

	public void DeleteByNo(int no) {
		sqlSession.delete("board.delete", no);
	}

}
