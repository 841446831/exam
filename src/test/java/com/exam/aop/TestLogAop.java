package com.exam.aop;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.exam.dao.OptionDao;
import com.exam.entity.Option;
import com.exam.service.OptionService;
import com.exam.service.TypeService;


@RunWith(SpringRunner.class) 
@ContextConfiguration("classpath:spring/applicationContext.xml") 
public class TestLogAop {
	@Resource 
	private TypeService typeService;
	@Resource OptionService	optionService;
	
	@Test
	public void log(){
		Option option = optionService.selectById(27);
	}
}
