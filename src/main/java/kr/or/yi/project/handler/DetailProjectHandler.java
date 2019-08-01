package kr.or.yi.project.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import kr.or.yi.project.controller.CommandHandler;
import kr.or.yi.project.dao.ProjectDao;
import kr.or.yi.project.model.Project;
import kr.or.yi.project.util.MySqlSessionFactory;

public class DetailProjectHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String str_no = req.getParameter("no");
		int no = Integer.parseInt(str_no);
		
		SqlSession sqlSession = null;
		
		try {
			sqlSession = MySqlSessionFactory.openSession();
			ProjectDao dao = sqlSession.getMapper(ProjectDao.class);
			
			Project project = dao.selectProjectByno(no);
			
			req.setAttribute("project", project);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return "/WEB-INF/view/projectDetail.jsp";
	}

}
