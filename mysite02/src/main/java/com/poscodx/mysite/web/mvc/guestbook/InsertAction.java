package com.poscodx.mysite.web.mvc.guestbook;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.dao.GuestBookDao;
import com.poscodx.mysite.vo.GuestBookVo;
import com.poscodx.web.mvc.Action;

public class InsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String content = request.getParameter("content");
		String reg_date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		GuestBookVo vo = new GuestBookVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setContents(content);
		vo.setReg_date(reg_date);
		
		new GuestBookDao().insert(vo);
		
		response.sendRedirect("/mysite02/guestbook");
	}
}
