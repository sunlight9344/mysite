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
		
//		for(BoardVo vo:list) {
//			System.out.println(vo);
//		}
		return map;
	}

	public BoardVo getBoardInfoByNo(int no) {
		return boardRepository.getBoardInfoByNo(no);
	}

	public void insert(String title, String contents) {
		boardRepository.BoardInsert(null);
	}
	
	public void update(int no, String title, String content) {
		boardRepository.ModifyByNo(no, title, content);
	}

}
