package com.poscodx.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poscodx.mysite.service.BoardService;
import com.poscodx.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	private int listPerPage = 5;
	private int showPageLength = 5;
	
	@RequestMapping(value="/{curPage}")
	public String list(@PathVariable("curPage") int curPage,
			Model model) {

		int allLength = boardService.findAllCount("");
		int totalPageLength = allLength/listPerPage + 1;
		int begin = ((curPage-1)/listPerPage)*listPerPage + 1;
		int end = (begin+showPageLength-1<=totalPageLength) ? begin+showPageLength-1:totalPageLength;
		if (curPage < 1 || curPage > totalPageLength) {
			curPage = 1;
		}
		List<BoardVo> list = boardService.getContentsList(curPage, listPerPage, "");
		
		model.addAttribute("list", list);
		model.addAttribute("pageLength", totalPageLength);
		model.addAttribute("curPage", curPage);
		model.addAttribute("listPerPage", listPerPage);
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
		model.addAttribute("allLength", allLength);
		model.addAttribute("kwd", "");
		
		return "board/list";
	}
	
	@RequestMapping(value="/view/{no}/{curPage}")
	public String view(
			@PathVariable("no") int no,
			@PathVariable("curPage") int curPage,
			Model model) {
		BoardVo vo = boardService.getBoardInfoByNo(no);
		model.addAttribute("vo", vo);
		model.addAttribute("curPage", curPage);
		return "board/view";
	}
	
	@RequestMapping(value="/write/{no}", method=RequestMethod.GET)
	public String write(
			@PathVariable("no") int no) {
		return "";
	}
	
	@RequestMapping(value="/write/{no}", method=RequestMethod.POST)
	public String write(
			@PathVariable("no") int no,
			@RequestParam(value="title", required=true, defaultValue="") String title) {
		return "";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(
			@RequestParam(value="title", required=true, defaultValue="") String title,
			@RequestParam(value="contents", required=true, defaultValue="") String contents
			) {
		boardService.insert(title, contents);
		return "";
	}
	
}
