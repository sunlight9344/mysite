package com.poscodx.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.web.mvc.Action;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		BoardVo vo = new BoardDao().getBoardInfoByNo(no);
		
		request.setAttribute("vo", vo);
		
		request.getRequestDispatcher("/WEB-INF/views/board/view.jsp").forward(request, response);
	}

}
