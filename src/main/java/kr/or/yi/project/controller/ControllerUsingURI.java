package kr.or.yi.project.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerUsingURI extends HttpServlet {
	
	private HashMap<String, CommandHandler> commandHandlerMap = new HashMap<>();

	@Override
	public void init() throws ServletException { //서버가 알아서 호출해줌. 서버 실행될 때 딱 한번만 호출됨. 그래서 properties파일 수정하면 서버 멈췄다가 재실행해줘야 한다!!!!!!!
		String configFile = getInitParameter("configFile"); //configFile얘가 들고 있는 값을 들고오는거 => 즉, /WEB-INF/commandHandler.properties이게 호출됨
		Properties prop = new Properties(); //해쉬맵과 비슷함
		String configFilePath = getServletContext().getRealPath(configFile); //상대주소를 절대주소로 바꾸는거
		
		try(FileReader fis = new FileReader(configFilePath)) { //파일까서 읽어
			prop.load(fis); //Properties파일에 읽은 내용을 집어넣어
		}catch (Exception e) {
			throw new ServletException(e);
		}
		
		Iterator keyIter = prop.keySet().iterator(); //얘도 linked형태로 되어있음. 다음꺼 있는지 확인해보는
		
		// commandHandler.properties파일안에 /simple.do = mvc.simple.SimpleHandler
		while(keyIter.hasNext()) {
			String command = (String) keyIter.next(); //key 추출 - /simple.do
			String handlerClassName = prop.getProperty(command); //key에 해당하는 값 - mvc.simple.SimpleHandler
			Class<?> handlerClass; //String만 받아온거라서 이 String에 해당되는 클래스를 만들어야 된다
			try {
				handlerClass = Class.forName(handlerClassName); //String되어있는 클래스를  class화 시킨다.
				CommandHandler handlerInstance = (CommandHandler) handlerClass.newInstance(); //new => 즉, 메모리에 올림
				commandHandlerMap.put(command, handlerInstance);
									 // /simple.do & class가 들어감
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}//init() 함수의 역할은 map에 넣는거다.
	//이 과정을 거치면 handler가 객체가 된거라고 생각하면 됨
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	private void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String command = req.getRequestURI(); //주소가 넘어옴 : /MVCProject/simple.do
		if(command.indexOf(req.getContextPath()) == 0) { // /MVCProject 이거로 시작되는지 여부를 확인하기 위한 용도
			command = command.substring(req.getContextPath().length()); // /simple.do
			//즉, command에 내가 자른 command를 다시 집어넣는거지
		}
		
		CommandHandler handler = commandHandlerMap.get(command); 
													//command에 해당하는 클래스가 온다
		if(handler == null) {
			handler = new NullHandler();
		}
		
		String viewPage = null;
		
		try {
			viewPage = handler.process(req, res); //simpleForm.jsp 
								//command에 해당하는 클래스를 받아와서 이 클래스에 해당되는 process함수가 호출되는거다
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(viewPage != null) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
			dispatcher.forward(req, res); //viewPage로 포워드 시키기 위한 용도
		}
	}
}

//ControllerUsingURI의 doget, dopost - 커맨드 class의 process()호출 -> viewPage로 forward
