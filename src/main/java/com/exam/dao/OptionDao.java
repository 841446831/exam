package com.exam.dao;

import org.apache.ibatis.annotations.Insert;

import com.exam.entity.Option;

public interface OptionDao {
    
	@Insert("INSERT INTO `option` (`id`, `qid`, `title`, `isTrue`, `tutorial`) VALUES (#{id},#{qid}, #{title},#{isTrue},#{tutorial})")
	public int insertOption(Option option);
	
}
