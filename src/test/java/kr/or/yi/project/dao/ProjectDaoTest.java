package kr.or.yi.project.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import kr.or.yi.project.model.Project;
import kr.or.yi.project.util.MySqlSessionFactory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectDaoTest {
	
	@Test
	public void test01SelectProjectList() {
		SqlSession sqlSession = null;
		
		try {
			sqlSession = MySqlSessionFactory.openSession();
			ProjectDao dao = sqlSession.getMapper(ProjectDao.class);
			
			List<Project> list = dao.selectProjectList();
			
			for(Project p : list) {
				System.out.println(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void test02InsertProject() {
		SqlSession sqlSession = null;
		
		try {
			sqlSession = MySqlSessionFactory.openSession();
			ProjectDao dao = sqlSession.getMapper(ProjectDao.class);
			
			Project p = new Project("project", "projectprojectproject", new Date(), new Date(), "보류");
			
			int res = dao.insertProject(p);
			System.out.println("insert: " + res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void test03SelectProjectByno() {
		SqlSession sqlSession = null;
		
		try {
			sqlSession = MySqlSessionFactory.openSession();
			ProjectDao dao = sqlSession.getMapper(ProjectDao.class);
			
			Project p = dao.selectProjectByno(3);
			
			System.out.println(p);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void test04UpdateProject() {
		SqlSession sqlSession = null;
		
		try {
			sqlSession = MySqlSessionFactory.openSession();
			ProjectDao dao = sqlSession.getMapper(ProjectDao.class);
			
			Project p = new Project(10, "몽실", "몽실몽실몽실몽실", new Date(), new Date(), "진행중");
			
			int res = dao.updateProject(p);
			
			System.out.println("update: " + res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void test05DeleteProject() {
		SqlSession sqlSession = null;
		
		try {
			sqlSession = MySqlSessionFactory.openSession();
			ProjectDao dao = sqlSession.getMapper(ProjectDao.class);
			
			Project p = new Project(10);
			
			int res = dao.deleteProject(p);
			
			System.out.println("delete: " + res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
}
