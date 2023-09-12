package com.poscodx.mysite.web.mvc.board;
import com.poscodx.web.mvc.Action;
import com.poscodx.web.mvc.ActionFactory;

public class BoardActionFactory implements ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("write".equals(actionName)) {
			action = new BoardWriteAction();
		} else if("writeform".equals(actionName)) {
			action = new BoardWriteFormAction();
		} else if("modify".equals(actionName)) {
			action = new BoardModifyAction();
		} else if("view".equals(actionName)) {
			action = new BoardViewAction();
		} else {
			action = new BoardListAction();
		}
		
		return action;
	}

}
