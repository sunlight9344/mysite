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
		int listLenght = new BoardDao().findCount();
		String sCurPage = request.getParameter("curPage");
		int curPage = 1;
		if(sCurPage != null) {
			curPage = Integer.parseInt(sCurPage);
		}
		
		//System.out.println(curPage);
		
		if (curPage < 1) {
			curPage = 1;
		}else if(curPage > listLenght/listPerPage + 1) {
			curPage = listLenght/listPerPage + 1;
		}
		List<BoardVo> list = new BoardDao().findAll((curPage-1) * 5, listPerPage);
		
		request.setAttribute("list", list);
		request.setAttribute("pageLength", listLenght/listPerPage + 1);
		request.setAttribute("curPage", curPage);
		
		WebUtil.forward("/board/list", request, response);
	}
}
