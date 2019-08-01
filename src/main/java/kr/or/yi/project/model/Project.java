package kr.or.yi.project.model;

import java.util.Date;

public class Project {
	private int serialNum;
	private String projectName;
	private String projectContent;
	private Date startDate;
	private Date endDate;
	private String projectProgress;
	
	public Project() {
		super();
	}
	
	public Project(int serialNum) {
		super();
		this.serialNum = serialNum;
	}

	public Project(String projectName, String projectContent, Date startDate, Date endDate, String projectProgress) {
		super();
		this.projectName = projectName;
		this.projectContent = projectContent;
		this.startDate = startDate;
		this.endDate = endDate;
		this.projectProgress = projectProgress;
	}
	
	public Project(int serialNum, String projectName, String projectContent, Date startDate, Date endDate,
			String projectProgress) {
		super();
		this.serialNum = serialNum;
		this.projectName = projectName;
		this.projectContent = projectContent;
		this.startDate = startDate;
		this.endDate = endDate;
		this.projectProgress = projectProgress;
	}

	public int getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectContent() {
		return projectContent;
	}
	public void setProjectContent(String projectContent) {
		this.projectContent = projectContent;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getProjectProgress() {
		return projectProgress;
	}
	public void setProjectProgress(String projectProgress) {
		this.projectProgress = projectProgress;
	}
	
	@Override
	public String toString() {
		return "Project [serialNum=" + serialNum + ", projectName=" + projectName + ", projectContent=" + projectContent
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", projectProgress=" + projectProgress + "]";
	}
	
}
