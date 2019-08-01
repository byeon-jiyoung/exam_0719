package kr.or.yi.project.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import kr.or.yi.project.controller.CommandHandler;
import kr.or.yi.project.dao.ProjectDao;
import kr.or.yi.project.model.Project;
import kr.or.yi.project.util.MySqlSessionFactory;

public class ListProjectHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		SqlSession sqlSession = null;
		
		try {
			sqlSession = MySqlSessionFactory.openSession();
			ProjectDao dao = sqlSession.getMapper(ProjectDao.class);
			
			List<Project> list = dao.selectProjectList();
			req.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return "/WEB-INF/view/projectList.jsp";
	}

}
