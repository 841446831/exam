package com.exam.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.exam.entity.ExamOption;

public interface ExamOptionDao {
   
    @Insert("insert into examoption(eid,qid,oid,isSelect) values(#{eid},#{qid},#{oid},#{isSelect})")
	public int insertExamOption(ExamOption examOption);

    @Select("select count(*) from examoption where eid = #{eid}")
	public int countByEid(int eid);

    @Select("select count(*) from examoption where eid = #{arg0} and uid = #{arg1}")
	public int selectCountByEidAndUid(int eid, int uid);

    @Insert("insert into examoption(eid,qid,oid,isSelect,uid) values(#{eid},#{qid},#{oid},#{isSelect},#{uid})")
	public int insert(ExamOption examOption);
	
}
