package kr.or.yi.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//chapter16 SimpleController.java에서 너무 if문이 많아서 각 자바파일을 만들어서 호출하도록 하려고 한다.
public interface CommandHandler { //부모클래스 하나 만들어서 각 클래스들이 상속받게 하려고 이렇게 만들었다.
								  //부모클래스는 그냥 형식만 구현하는 거라서 메모리에 올릴필요없는거라 대부분 인터페이스로 작성한다.
								  //그냥 각각 클래스를 만들면 똑같이 if문을 써야되서 이렇게 하는 의미가 없다. 그래서 부모클래스를 만들어 상속을 받게하면 이거만 호출하면 자식들은 자동으로 호출되기 때문에 이 방법으로 진행한다.
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception;
	
	//.do를 치면 jsp연결되도록 하기 위해 만들었음. 이거도 web.xml가서 <servlet>,<servlet-mapping> 추가해줘야됨.
}
