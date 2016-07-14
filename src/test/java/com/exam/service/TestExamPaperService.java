package com.exam.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.exam.entity.ExamPaper;
import com.exam.entity.Question;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml") 
public class TestExamPaperService {
	@Resource
	private ExamPaperService examPaperService;
	@Test
	public void testInsertExampaper(){
		long startTime = System.currentTimeMillis();
		for (int i=0;i<20;++i){
			ExamPaper examPaper = new ExamPaper();
			examPaper.setStartTime(startTime);
			examPaper.setEndTime(System.currentTimeMillis()+100000000);
			examPaper.setTitle("wfc 的考试之路 "+i);
			examPaper.setPassword(""+i);
			examPaper.setPractice(i%2);
			examPaper.setUid(1);
			List<Question> list = new ArrayList<>();
			
			Question question = new Question();
			question.setId(22);
			list.add(question);
			
			Question question2 = new Question();
			question2.setId(24);
			list.add(question2);
			
			examPaper.setQuestions(list);
			System.out.println(list);
			examPaperService.insert(examPaper);
		}
	}
}
