package com.exam.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import com.exam.entity.ExamQuestion;

public interface ExamQuestionDao {
	@Insert("insert into  examQuestion (id,eid,qid,mark)values(#{id},#{eid},#{qid},#{mark})")
	int insert(ExamQuestion examQuestion);
}
