package com.poscodx.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.mysite.repository.BoardRepository;
import com.poscodx.mysite.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;

	public List<BoardVo> getContentsList(int curPage, int listPerPage, String kwd) {
		return boardRepository.findAll(curPage, listPerPage, kwd);
	}

	public int findAllCount(String kwd) {
		return boardRepository.findAllCount(kwd);
	}

	public BoardVo getBoardInfoByNo(int no) {
		return boardRepository.getBoardInfoByNo(no);
	}

	public void insert(String title, String contents) {
		boardRepository.BoardInsert(null);
	}

}
