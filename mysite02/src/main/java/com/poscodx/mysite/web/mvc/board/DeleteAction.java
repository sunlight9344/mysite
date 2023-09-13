package com.poscodx.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.web.mvc.Action;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		new BoardDao().DeleteByNo(no);
		response.sendRedirect("/mysite02/board?curPage="+curPage);
	}
}
