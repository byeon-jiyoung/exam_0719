package kr.or.yi.project.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import kr.or.yi.project.controller.CommandHandler;
import kr.or.yi.project.dao.ProjectDao;
import kr.or.yi.project.model.Project;
import kr.or.yi.project.util.MySqlSessionFactory;

public class DeleteProjectHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		int no = Integer.parseInt(req.getParameter("no"));
		
		SqlSession sqlSession = null;
		
		try {
			sqlSession = MySqlSessionFactory.openSession();
			ProjectDao dao = sqlSession.getMapper(ProjectDao.class);
			
			Project project = new Project(no);
			//System.out.println(project);

			dao.deleteProject(project);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return "list.do";
	}

}
