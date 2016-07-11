package com.exam.dao;

import org.apache.ibatis.annotations.Insert;

import com.exam.entity.ExamOption;

public interface ExamOptionDao {
   
    @Insert("insert into examoption(eid,qid,oid,isSelect) values(#{eid},#{qid},#{oid},#{isSelect})")
	public int insertExamOption(ExamOption examOption);
	
}
