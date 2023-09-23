package com.poscodx.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poscodx.mysite.security.AuthUser;
import com.poscodx.mysite.service.BoardService;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="")
	public String list(
			@RequestParam(value="p", required=true, defaultValue="1") int curPage,
			@RequestParam(value="kwd", required=true, defaultValue="") String kwd,
			Model model) {
		
		model.addAllAttributes(boardService.getContentsList(curPage, kwd));
		
		return "board/list";
	}
	
	@RequestMapping(value="/view/{no}")
	public String view(
			@PathVariable("no") int no,
			Model model) {
		
		BoardVo vo = boardService.getBoardInfoByNo(no);
		model.addAttribute("vo", vo);
		
		return "board/view";
	}
	
	@RequestMapping(value="/modify/{no}", method=RequestMethod.GET)
	public String modify(
			@PathVariable("no") int no,
			Model model
			) {
		BoardVo vo = boardService.getBoardInfoByNo(no);
		model.addAttribute("vo", vo);
		
		return "board/modify";
	}
	
	@RequestMapping(value="/modify/{no}", method=RequestMethod.POST)
	public String modify(
			@PathVariable("no") int no,
			@RequestParam(value="p", required=true, defaultValue="1") int curPage,
			@RequestParam(value="kwd", required=true, defaultValue="") String kwd,
			BoardVo boardVo
			) {
		boardService.modify(boardVo);
		return "redirect:/board/view/" + no + "?p=" + curPage +"&kwd=" + kwd;
	}
	
	@RequestMapping(value="/delete/{no}")
	public String delete(
			HttpSession session,
			@PathVariable("no") int no,
			@RequestParam(value="p", required=true, defaultValue="1") int curPage) {
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/user/login";
		}
		
		boardService.deleteByNo(no);
		
		return "redirect:/board?p=" + curPage;
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(
			@AuthUser UserVo authUser, 
			BoardVo boardVo,
			int n
			) {
		boardVo.setUser_no(authUser.getNo());
		boardService.write(boardVo, n);
		
		return "redirect:/board";
	}
}
