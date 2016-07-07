package com.exam.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.exam.entity.Question;

public interface QuestionDao {
    
	@Insert("insert into question(id,face,answer,tid,level) values(#{id},#{face},#{answer},#{tid},#{level})")
	@Options(useGeneratedKeys=true, keyProperty="id")//添加该行，product中的id将被自动添加
	public int insertQuestion(Question question);
	
	//@Select("select q.*,o.* from question q,`option` o where o.qid = q.id and q.level = #{level}")
	public List<Question> selectQuestionByTypeAndLevel(int level);
	
	@Select("select * from question where face = #{face}")
	public List<Question> selectQuestionsByFace(String face);

	@Select("select * from question where id = #{id}")
	public Question selectById(int id);

}
