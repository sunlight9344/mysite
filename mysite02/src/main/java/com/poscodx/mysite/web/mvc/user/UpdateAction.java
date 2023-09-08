package com.poscodx.mysite.web.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.dao.UserDao;
import com.poscodx.mysite.vo.UserVo;
import com.poscodx.web.mvc.Action;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		Long no = Long.parseLong(request.getParameter("no"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		if(password == "") {
			new UserDao().updateWithOutPassword(no,name,email,gender);
		}else {
			new UserDao().updateWithPassword(no,name,email,gender,password);
		}
		
		// redirect
		response.sendRedirect(request.getContextPath()+ "/user?a=updateform");
	}

}
