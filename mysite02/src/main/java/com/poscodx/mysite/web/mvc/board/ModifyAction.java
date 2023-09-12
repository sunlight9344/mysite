package com.poscodx.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.web.mvc.Action;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		int no = Integer.parseInt(request.getParameter("no"));
		
		new BoardDao().ModifyByNo(no, title, contents);
		
		response.sendRedirect(request.getContextPath()+ "/board?a=view&no="+no);
	}

}
