package com.poscodx.mysite.web.mvc.board;

import java.io.IOException;
import java.util.ArrayList;
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
		List<BoardVo> list = new BoardDao().findAll();
		
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		int listLength = list.size();
		int pageLength = listLength/5 + 1;
		int startIndex = (curPage-1) *5;
		int endIndex = (startIndex+5 >= listLength) ? listLength : startIndex+5;
		List<BoardVo> subList = new ArrayList<>(list.subList(startIndex, endIndex));
		
		request.setAttribute("list", subList);
		request.setAttribute("pageLength", pageLength);
		request.setAttribute("curPage", curPage);
		
		WebUtil.forward("/board/list", request, response);
	}
}
