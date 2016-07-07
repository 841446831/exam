package com.exam.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.exam.entity.Question;

public interface QuestionDao {
    
	@Insert("insert into question(id,face,answer,tid,level) values(#{id},#{face},#{answer},#{tid},#{level})")
	public int insertQuestion(Question question);
	
	//@Select("select q.*,o.* from question q,`option` o where o.qid = q.id and q.level = #{level}")
	public List<Question> selectQuestionByTypeAndLevel(int level);

}
