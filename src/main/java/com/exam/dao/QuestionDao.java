package com.exam.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.exam.entity.Question;

public interface QuestionDao {
    
	@Insert("insert into question(id,face,answer,tid,level,isRadio,isPublic) values(#{id},#{face},#{answer},#{tid},#{level},#{isRadio},#{isPublic})")
	@Options(useGeneratedKeys=true, keyProperty="id")//添加该行，product中的id将被自动添加
	public int insertQuestion(Question question);
	
	//@Select("select q.*,o.* from question q,`option` o where o.qid = q.id and q.level = #{level}")
	public List<Question> selectQuestionByTypeAndLevel(int level);
	
	@Select("select * from question where face = #{face}")
	public List<Question> selectQuestionsByFace(String face);

	@Select("select * from question where id = #{id}")
	public Question selectById(int id);

	public List<Question> selectQuestions(List<Integer> tags, int diffculty, int count);
	
	public int typeOfQuestionCount(int tid);

	public List<Question> selectByTypeId(int typeId);

	@Select("select * from question")
	public List<Question> selectAll();
	
	@Update("update question set isRadio = #{isRadio} , isPublic = #{isPublic},"
			+ " face = #{face},answer=#{answer},tid=#{tid},level=#{level},uid=#{user.id} where id = #{id}")
	int update(Question question);
	
	public List<Question> selectQuestionByEid(int eid);
	
	@Select("select id from question")
	public List<Integer> selectQuesionId();
	
}
