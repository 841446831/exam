package com.exam.entity;

import java.util.ArrayList;
import java.util.List;


public class ExamPaper {
	private int id;
	private String title;
	private long startTime;
	private long endTime;
	private String password;
	private int practice;
	private List<ExamQuestion> examQuestions;
	private List<Question> questions;
	
	public ExamPaper() {
		super();
		examQuestions = new ArrayList<ExamQuestion>();
		questions = new ArrayList<>();
	}
	
	

	public List<ExamQuestion> getExamQuestions() {
		return examQuestions;
	}



	public void setExamQuestions(List<ExamQuestion> examQuestions) {
		this.examQuestions = examQuestions;
	}



	public List<Question> getQuestions() {
		return questions;
	}



	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPractice() {
		return practice;
	}
	public void setPractice(int i) {
		this.practice = i;
	}



	@Override
	public String toString() {
		return "ExamPaper [id=" + id + ", title=" + title + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", password=" + password + ", practice=" + practice + ", questions=" + questions + "]";
	}

}
