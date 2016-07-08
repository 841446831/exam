package com.exam.entity;

public class ExamQuestion {
	@Override
	public String toString() {
		return "ExamQuestion [id=" + id + ", eid=" + eid + ", qid=" + qid
				+ ", mark=" + mark + "]";
	}
	private int id;
	private int eid;
	private int qid;
	private double mark;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public double getMark() {
		return mark;
	}
	public void setMark(double mark) {
		this.mark = mark;
	}
	
	
}
