package com.poscodx.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.mysite.repository.BoardRepository;
import com.poscodx.mysite.vo.BoardVo;

@Service
public class BoardService {
	
	private final int LIST_PER_PAGE = 5;
	private final int SHOW_PAGE_LENGTH = 5;
	
	@Autowired
	private BoardRepository boardRepository;

	public Map<String, Object> getContentsList(int curPage, String kwd) {
		int allCount = boardRepository.findAllCount(kwd);
		int totalPageLength = allCount / LIST_PER_PAGE + 1;
		if (curPage < 1 || curPage > totalPageLength) {
			curPage = 1;
		}
		int begin = ((curPage-1) / LIST_PER_PAGE) * LIST_PER_PAGE + 1;
		int end = (begin + SHOW_PAGE_LENGTH - 1 <= totalPageLength) ? begin + SHOW_PAGE_LENGTH - 1 : totalPageLength;
		
		List<BoardVo> list = boardRepository.findAll(curPage, LIST_PER_PAGE, kwd);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("list", list);
		map.put("totalPageLength", totalPageLength);
		map.put("curPage", curPage);
		map.put("listPerPage", LIST_PER_PAGE);
		map.put("begin", begin);
		map.put("end", end);
		map.put("allCount", allCount);
		map.put("kwd", kwd);
		
		return map;
	}

	public BoardVo getBoardInfoByNo(int no) {
		boardRepository.UpdateHit(no);
		return boardRepository.getBoardInfoByNo(no);
	}
	
	public void modify(BoardVo boardVo) {
		boardRepository.ModifyByNo(boardVo);
	}

	public void deleteByNo(int no) {
		boardRepository.DeleteByNo(no);
	}

	public void write(BoardVo boardVo, int n) {
		if(n != -1) {
			BoardVo tmpVo = boardRepository.getBoardInfoByNo(n);
			boardVo.setG_no(tmpVo.getG_no());
			boardVo.setO_no(tmpVo.getO_no()+1);
			boardVo.setDepth(tmpVo.getDepth()+1);
			boardRepository.refreshNo(tmpVo.getG_no(), tmpVo.getO_no()+1);
		}
		boardRepository.write(boardVo);
	}
}
