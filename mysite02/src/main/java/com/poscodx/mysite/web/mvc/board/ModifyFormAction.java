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
import com.poscodx.web.utils.WebUtil;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user?a=loginform");
			return;
		}
		
		int no = Integer.parseInt(request.getParameter("no"));
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		
		BoardVo vo = new BoardDao().getBoardInfoByNo(no);
		
		request.setAttribute("vo", vo);
		request.setAttribute("curPage", curPage);
		
		WebUtil.forward("/board/modify", request, response);
	}
}
