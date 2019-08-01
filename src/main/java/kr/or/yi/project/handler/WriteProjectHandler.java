package kr.or.yi.project.handler;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import kr.or.yi.project.controller.CommandHandler;
import kr.or.yi.project.dao.ProjectDao;
import kr.or.yi.project.model.Project;
import kr.or.yi.project.util.MySqlSessionFactory;

public class WriteProjectHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("get")) {
			return "/WEB-INF/view/projectWrite.jsp";
		}else if(req.getMethod().equalsIgnoreCase("post")) {
			String name = req.getParameter("name");
			String content = req.getParameter("content");
			
			SimpleDateFormat sp_start = new SimpleDateFormat("yyyy-MM-dd");
			Date date_start = sp_start.parse(req.getParameter("start"));
			
			SimpleDateFormat sp_end = new SimpleDateFormat("yyyy-MM-dd");
			Date date_end = sp_end.parse(req.getParameter("end"));
			
			String state = req.getParameter("state");
			
			SqlSession sqlSession = null;
			
			try {
				sqlSession = MySqlSessionFactory.openSession();
				ProjectDao dao = sqlSession.getMapper(ProjectDao.class);
				
				Project project =  new Project(name, content, date_start, date_end, state);
				
				dao.insertProject(project);
				sqlSession.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sqlSession.close();
			}
			
			res.sendRedirect("list.do");
			return null;
		}
		return null;
	}
}
