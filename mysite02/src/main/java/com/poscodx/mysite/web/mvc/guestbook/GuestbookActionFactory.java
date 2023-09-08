package com.poscodx.mysite.web.mvc.guestbook;
import com.poscodx.web.mvc.Action;

public class GuestbookActionFactory implements com.poscodx.web.mvc.ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("insert".equals(actionName)) {
			action = new InsertAction();
		} else if("deleteform".equals(actionName)) {
			action = new deleteFormAction();
		} else if("delete".equals(actionName)) {
			action = new deleteAction();
		} else {
			action = new guestBookListAction();
		}
		
		return action;
	}

}