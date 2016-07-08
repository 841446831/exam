package com.exam.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.exam.entity.Option;

public interface OptionDao {
    
	@Insert("INSERT INTO `option` (`id`, `qid`, `title`, `isTrue`, `tutorial`) VALUES (#{id},#{qid}, #{title},#{isTrue},#{tutorial})")
	public int insertOption(Option option);

	@Select("select id,title from `option` where qid = #{id}")
	public List<Option> selectByTid(Integer id);
	
}
