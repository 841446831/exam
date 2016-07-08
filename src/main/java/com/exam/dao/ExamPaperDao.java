package com.exam.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.exam.entity.ExamPaper;

public interface ExamPaperDao {
	
	@Select("select * from exampaper")
	List<ExamPaper> selectAll();
	
	@Insert("insert into exampaper (id,title,startTime,endTime,password,practice)values(#{id},#{title},#{startTime},#{endTime},#{password},#{practice}) ")
	@Options(useGeneratedKeys=true, keyProperty="id")
	int insert(ExamPaper examPaper);
}
