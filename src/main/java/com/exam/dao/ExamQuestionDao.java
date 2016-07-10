package com.exam.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.exam.entity.ExamPaper;
import com.exam.entity.ExamQuestion;

public interface ExamQuestionDao {
	@Insert("insert into  examQuestion (id,eid,qid,mark)values(#{id},#{eid},#{qid},#{mark})")
	int insert(ExamQuestion examQuestion);
	
	//@Select("SELECT DISTINCT ep.* FROM `examQuestion` e ,exampaper ep WHERE ep.id = e.eid AND e.eid = #{eid}")
	ExamPaper selectExamPaperByEid(int eid);

	@Select("SELECT qid from examQuestion where eid = #{eid} order by qid")
	List<Integer> selectQidByEid(int eid);
	
}
