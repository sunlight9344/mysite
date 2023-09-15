package com.poscodx.mysite.web.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.web.mvc.Action;
import com.poscodx.web.utils.WebUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int listPerPage = 5;
		int showPageLength = 5;
		String kwd = request.getParameter("kwd");
		if (kwd == null) {
			kwd = "";
		}
		int allLength = new BoardDao().findAllCount(kwd);
		int totalPageLength = allLength/listPerPage + 1;
		String sCurPage = request.getParameter("curPage");
		int curPage = 1;
		if(sCurPage != null) {
			curPage = Integer.parseInt(sCurPage);
		}
		
		if (curPage < 1 || curPage > totalPageLength) {
			curPage = 1;
		}
		
		int begin = ((curPage-1) / listPerPage) * listPerPage + 1;
		int end = (begin + showPageLength - 1 <= totalPageLength) ? begin + showPageLength - 1 : totalPageLength;
		
		List<BoardVo> list = new BoardDao().findAll(curPage, listPerPage, kwd);
		
		request.setAttribute("list", list);
		request.setAttribute("pageLength", totalPageLength);
		request.setAttribute("curPage", curPage);
		request.setAttribute("listPerPage", listPerPage);
		request.setAttribute("begin", begin);
		request.setAttribute("end", end);
		request.setAttribute("allLength", allLength);
		request.setAttribute("kwd", kwd);
		
		WebUtil.forward("/board/list", request, response);
	}
}
