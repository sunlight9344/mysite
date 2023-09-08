package com.poscodx.mysite.web.mvc.user;

import com.poscodx.mysite.web.mbc.main.MainAction;
import com.poscodx.web.mvc.Action;

public class UserActionFactory implements com.poscodx.web.mvc.ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("joinform".equals(actionName)) {
			action = new JoinFormAction();
		} else if("join".equals(actionName)) {
			action = new JoinAction();
		} else if("joinsuccess".equals(actionName)) {
			action = new JoinSuccessAction();
		} else if("loginform".equals(actionName)) {
			action = new LoginFormAction();
		} else {
			action = new MainAction();
		}
		
		return action;
	}

}
