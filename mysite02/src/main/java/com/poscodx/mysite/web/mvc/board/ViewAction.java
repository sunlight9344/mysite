package com.poscodx.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.web.mvc.Action;
import com.poscodx.web.utils.WebUtil;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		int curPage = Integer.parseInt(request.getParameter("curPage"));

		BoardVo vo = new BoardDao().getBoardInfoByNo(no);
		new BoardDao().UpdateHit(no);
		
		request.setAttribute("curPage", curPage);
		request.setAttribute("vo", vo);
		
		WebUtil.forward("/board/view", request, response);
	}
}
