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

public class UpdateProjectHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("get")) {
			SqlSession sqlSession = null;
			
			try {
				sqlSession = MySqlSessionFactory.openSession();
				ProjectDao dao = sqlSession.getMapper(ProjectDao.class);
				
				int no = Integer.parseInt(req.getParameter("no"));
				
				Project project = dao.selectProjectByno(no);
				
				req.setAttribute("no", no);
				req.setAttribute("name", project.getProjectName());
				req.setAttribute("content", project.getProjectContent());
				
				SimpleDateFormat sp_start = new SimpleDateFormat("yyyy-MM-dd");
				String str_start = sp_start.format(project.getStartDate());
				req.setAttribute("start", str_start);
				
				SimpleDateFormat sp_end = new SimpleDateFormat("yyyy-MM-dd");
				String str_end = sp_end.format(project.getEndDate());
				req.setAttribute("end", str_end);
				
				req.setAttribute("state", project.getProjectProgress());
				
				//System.out.println(project.getProjectProgress());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sqlSession.close();
			}
			return "/WEB-INF/view/projectUpdate.jsp";
		}else if(req.getMethod().equalsIgnoreCase("post")) {
			int no = Integer.parseInt(req.getParameter("no"));
			//System.out.println(no);
			
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
				
				Project project = new Project(no, name, content, date_start, date_end, state);
				
				//System.out.println(project);
				dao.updateProject(project);
				sqlSession.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sqlSession.close();
			}
			return "detail.do";
		}
		return null;
	}

}
