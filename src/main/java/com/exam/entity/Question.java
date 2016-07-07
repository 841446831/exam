package com.exam.entity;

import java.util.List;

public class Question {
    private Integer id;
    private String face;
    private String answer;
    private Integer level;
    private int tid;
    private List<Option> options;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	
	
	public List<Option> getOptions() {
		return options;
	}
	public void setOptions(List<Option> options) {
		this.options = options;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	@Override
	public String toString() {
		return "Question [id=" + id + ", face=" + face + ", answer=" + answer
				+ ", level=" + level + ", tid=" + tid + ", options=" + options
				+ "]";
	}
	
}
