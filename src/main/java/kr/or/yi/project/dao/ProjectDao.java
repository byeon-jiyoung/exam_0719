package kr.or.yi.project.dao;

import java.util.List;

import kr.or.yi.project.model.Project;

public interface ProjectDao {
	List<Project> selectProjectList();
	int insertProject(Project project);
	Project selectProjectByno(int serial_no);
	int updateProject(Project project);
	int deleteProject(Project project);
}
