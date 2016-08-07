package com.exam.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.exam.entity.ExamPaper;

public interface ExamPaperDao {
	
	@Select("select * from exampaper")
	List<ExamPaper> selectAll();
	
	@Insert("insert into exampaper (id,title,startTime,endTime,password,practice,uid)values(#{id},#{title},#{startTime},#{endTime},#{password},#{practice},#{uid})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	int insert(ExamPaper examPaper);	
	
	@Select("select * from exampaper where practice = 0 limit #{start},#{limit}")
    List<ExamPaper> selectByPractice(ExamPaper examPaper);
	
	@Select("select count(*) from exampaper where starttime < #{currentTime} and endtime > #{currentTime} and practice = 0")
	int selectCountByCurrentTime(long currentTime);
	
	@Select("select * from exampaper where starttime < #{arg0} and endtime > #{arg0} and practice = 0 limit #{arg1.start},#{arg1.limit}")
	List<ExamPaper> selectByCurrentTime(long currentTime,ExamPaper examPaper);

	@Select("select * from exampaper where id=#{id}")
	ExamPaper selectById(int id);
	
	@Select("select * from exampaper where practice = 0 limit 0,5")
    List<ExamPaper> selectPaper();

	
}
