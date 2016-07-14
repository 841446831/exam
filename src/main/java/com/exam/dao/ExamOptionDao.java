package com.exam.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.exam.entity.ExamOption;

public interface ExamOptionDao {
   
    @Insert("insert into examoption(eid,qid,oid,isSelect) values(#{eid},#{qid},#{oid},#{isSelect})")
	public int insertExamOption(ExamOption examOption);

    @Select("select count(*) from examoption")
	public int countByEid(int eid);
	
}
