package com.poscodx.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.UserVo;
import com.poscodx.web.mvc.Action;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser == null) {
			response.sendRedirect("/mysite02/board");
			return;
		}
		BoardVo vo = new BoardVo();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		int user_no = (authUser.getNo()).intValue();
		
		vo.setTitle(title);
		vo.setContents(content);
		vo.setUser_no(user_no);
		
		String no = request.getParameter("no");
		
		// list 에서 글쓰기 할 때
		if ("".equals(no)){
			vo.setG_no(new BoardDao().FindMaxGno()+1);
			vo.setO_no(1);
			vo.setDepth(1);
		} else {
			// view 에서 글쓰기 할 때
			BoardVo boardVo = new BoardDao().getBoardInfoByNo(Integer.parseInt(no));
			vo.setG_no(boardVo.getG_no());
			vo.setO_no(boardVo.getO_no()+1);
			vo.setDepth(boardVo.getDepth()+1);
			
			new BoardDao().Update(boardVo.getG_no(),boardVo.getO_no()+1);
		}
		
		new BoardDao().BoardInsert(vo);
		response.sendRedirect("/mysite02/board");
	}

}
