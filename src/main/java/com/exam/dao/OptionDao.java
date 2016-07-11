package com.exam.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.exam.entity.Option;

public interface OptionDao {
    
	@Insert("INSERT INTO `option` (`id`, `qid`, `title`, `isTrue`, `tutorial`,symbol) VALUES (#{id},#{qid}, #{title},#{isTrue},#{tutorial},#{symbol})")
	public int insertOption(Option option);


	@Select("select * from `option`")
	public List<Option> selectAll();
	
	@Select("SELECT count(*) from `option` where isTrue = 1 AND qid = #{qid}")
	public int selectTrueAnswer(int qid);

	@Update("update `option` set qid=#{qid},title=#{title},isTrue=#{isTrue},symbol=#{symbol},tutorial=#{tutorial} where id = #{id}")
	public int update(Option option);

	@Select("select * from `option` where id = #{i}")
	public Option selectById(int i);

	@Select("select symbol,title from `option` where qid = #{i}")
	public List<Option> selectByTidWithOutAnswer(Integer id);
	
	@Select("SELECT * FROM `option` where qid = #{qid}")
	public List<Option> selectByQid(Integer qid);
	
    
}
