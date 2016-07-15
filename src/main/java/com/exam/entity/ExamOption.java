package com.exam.entity;

public class ExamOption {
    private int id;
    private int eid;
    private int qid;
    private int oid;
    private int isSelect;
    @Override
	public String toString() {
		return "ExamOption [id=" + id + ", eid=" + eid + ", qid=" + qid
				+ ", oid=" + oid + ", isSelect=" + isSelect + ", uid=" + uid
				+ "]";
	}
	public ExamOption() {
		super();
		// TODO Auto-generated constructor stub
		uid = 2;
	}
	public ExamOption(int id, int eid, int qid, int oid, int isSelect, int uid) {
		super();
		this.id = id;
		this.eid = eid;
		this.qid = qid;
		this.oid = oid;
		this.isSelect = isSelect;
		this.uid = uid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	private int uid;
    
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
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getIsSelect() {
		return isSelect;
	}
	public void setIsSelect(int isSelect) {
		this.isSelect = isSelect;
	}
    
}
