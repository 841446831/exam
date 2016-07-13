package com.exam.entity;

public class Option {
    private Integer id;
    private Integer qid;
    private String title;
    private int isTrue;
    private String tutorial;
    private String symbol;
    
    public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
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
	public int getIsTrue() {
		return isTrue;
	}
	public void setIsTrue(int isTrue) {
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
		return "Option [id=" + id + ", qid=" + qid + ", title=" + title + ", isTrue=" + isTrue + ", tutorial="
				+ tutorial + ", symbol=" + symbol + ", question=" + question + "]";
	}

    
    
    
}
