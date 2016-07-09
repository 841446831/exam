package com.exam.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.exam.entity.Option;

public interface OptionDao {
    
	@Insert("INSERT INTO `option` (`id`, `qid`, `title`, `isTrue`, `tutorial`) VALUES (#{id},#{qid}, #{title},#{isTrue},#{tutorial})")
	public int insertOption(Option option);

	@Select("select id,title,isTrue from `option` where qid = #{id}")
	public List<Option> selectByTid(Integer id);

	@Select("select * from `option`")
	public List<Option> selectAll();
	
	@Select("SELECT count(*) from `option` where isTrue = 1 AND qid = #{qid}")
	public int selectTrueAnswer(int qid);
	
    
}
