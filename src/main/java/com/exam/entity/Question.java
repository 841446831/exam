package com.exam.entity;

import java.util.ArrayList;
import java.util.List;

import com.exam.util.PageHelper;

public class Question extends PageHelper<Question>{
    private Integer id;
    private String face;
    private String answer;
    private Integer level;
    private int tid;
    private int isRadio;
    private int isPublic;
    private User user;

	public int getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(int isPublic) {
		this.isPublic = isPublic;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private List<Option> options;
    
    public Question(){
    	options = new ArrayList<Option>();
    }
    
	public int getIsRadio() {
		return isRadio;
	}

	public void setIsRadio(int isRadio) {
		this.isRadio = isRadio;
	}

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
		return "Question [id=" + id + ", face=" + face + ", answer=" + answer + ", level=" + level + ", tid=" + tid
				+ ", isRadio=" + isRadio + ", isPublic=" + isPublic + ", user=" + user + ", options=" + options + "]";
	}


	
}
