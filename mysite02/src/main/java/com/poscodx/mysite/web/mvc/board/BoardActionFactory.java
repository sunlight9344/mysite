package com.poscodx.mysite.web.mvc.board;
import com.poscodx.web.mvc.Action;
import com.poscodx.web.mvc.ActionFactory;

public class BoardActionFactory implements ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("write".equals(actionName)) {
			action = new WriteAction();
		} else if("writeform".equals(actionName)) {
			action = new WriteFormAction();
		} else if("modify".equals(actionName)) {
			action = new ModifyAction();
		} else if("modifyform".equals(actionName)) {
			action = new ModifyFormAction();
		} else if("view".equals(actionName)) {
			action = new ViewAction();
		} else if("delete".equals(actionName)) {
			action = new DeleteAction();
		} else {
			action = new ListAction();
		}
		
		return action;
	}

}
