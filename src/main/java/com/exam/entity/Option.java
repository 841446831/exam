package com.exam.entity;

public class Option {
    private Integer id;
    private Integer qid;
    private String title;
    private boolean isTrue;
    private String tutorial;
    
    private Question question;
   
    
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer oid) {
		this.id = oid;
	}
	public Integer getQid() {
		return qid;
	}
	public void setQid(Integer qid) {
		this.qid = qid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean getIsTrue() {
		return isTrue;
	}
	public void setIsTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}
	public String getTutorial() {
		return tutorial;
	}
	public void setTutorial(String tutorial) {
		this.tutorial = tutorial;
	}
	@Override
	public String toString() {
		return "Option [id=" + id + ", qid=" + qid + ", title=" + title
				+ ", isTrue=" + isTrue + ", tutorial=" + tutorial + "]";
	}
    
    
    
}
