package kr.or.yi.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NullHandler implements CommandHandler { //없는 커맨드가 들어오면 이 클래스가 실행되도록 하기 위해 만듦.

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		res.sendError(HttpServletResponse.SC_NOT_FOUND); //404 error 발생시킴
		return null;
	}

}
