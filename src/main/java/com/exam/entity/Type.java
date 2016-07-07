package com.exam.entity;

public class Type {
    private Integer id;
    private String type;
    private String superType;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSuperType() {
		return superType;
	}
	public void setSuperType(String superType) {
		this.superType = superType;
	}
	@Override
	public String toString() {
		return "Type [id=" + id + ", type=" + type + ", superType=" + superType
				+ "]";
	}
	
    
    
    
    
}
