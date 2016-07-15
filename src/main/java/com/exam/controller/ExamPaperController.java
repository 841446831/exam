package com.exam.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.qos.logback.classic.Logger;

import com.alibaba.fastjson.JSON;
import com.exam.dao.OptionDao;
import com.exam.entity.ExamOption;
import com.exam.entity.ExamPaper;
import com.exam.entity.Option;
import com.exam.entity.Question;
import com.exam.entity.User;
import com.exam.service.ExamOptionService;
import com.exam.service.ExamPaperService;
import com.exam.service.ExamQuestionService;
import com.exam.service.QuestionService;

@Controller
public class ExamPaperController {
	 private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Resource  
	QuestionService questionService;
	@Resource
	ExamPaperService exampaperService;
	@Resource
	ExamQuestionService examQuestionService;
	@Resource 
	OptionDao optionDao;
	
	@Resource
	ExamOptionService examOptionService;
	
	@RequestMapping(value="makepaper",produces="application/json;charset=utf-8")
	@ResponseBody
	public String getExamPaper(String tag,@RequestParam(defaultValue="1")int diffculty,@RequestParam(defaultValue="5")int count,HttpServletRequest request){
		List<Integer> tags = new ArrayList<>();
		if (tag!=null && !tag.equals(""))
			if (tag.indexOf(',')>=0){
				tags = new ArrayList<>();
				for (String t:tag.split(",")){
					tags.add(Integer.valueOf(t));
				}
			}else{
				tags.add(Integer.valueOf(tag));
			}
		List<Question> questions = questionService.selectQuestions(tags, diffculty, count);
		ExamPaper examPaper = new ExamPaper();
		examPaper.setPractice(1);
		examPaper.setTitle("练习");
		examPaper.setQuestions(questions);
		if (request.getSession().getAttribute("user")!=null){
			User  user = (User) request.getSession().getAttribute("user");
			examPaper.setUid(user.getId());
		}else{
			examPaper.setUid(2);
		}
		exampaperService.insert(examPaper);
		return  "callback("+JSON.toJSONString(examPaper) +");";
	}
	
	
	
	@RequestMapping("practice")
	public String getExamPaperRedirect(String tag,int diffculty,int count,Model model){
		model.addAttribute("tag", tag);
		model.addAttribute("diffculty", diffculty);
		model.addAttribute("count", count);
		return "practice";
	}
	
	@RequestMapping("exam")
	public String checkInExam(String eid,HttpServletRequest request){
		if (request.getSession().getAttribute("user")==null){
			logger.info("进入exam，没有登陆");
		}else{
			//验证密码
		}
		return "exampaper";
	}
	
	@RequestMapping("insertPaper")
	@ResponseBody
	public String insertPaper(@RequestParam("qidString") String qidString,ExamPaper examPaper,HttpSession session)
	{
		examPaper.setUid(((User)session.getAttribute("user")).getId());
		
	    exampaperService.insert(qidString,examPaper);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code",0);
		return JSON.toJSONString(map);
	}
	
	@RequestMapping("selectNowExam")
	@ResponseBody
	public String selectNowExam(ExamPaper examPaper){
		return JSON.toJSONString(exampaperService.selectByCurrentTime(examPaper));
	}
	
	@RequestMapping("checkInExam")
	@ResponseBody
	public String checkInExam(ExamPaper examPaper,HttpServletRequest request){
		ExamPaper temp = exampaperService.selectById(examPaper.getId());
		Map<String,Object> map = new HashMap<>();
		Object user = request.getSession().getAttribute("user");
		if (user == null){
			map.put("code", 1);
			map.put("msg", "需要登陆后操作");
			return JSON.toJSONString(map);
		}else{
			user = (User)user;
		}
		
		if (temp.getPassword()!=null  && examPaper.getPassword()!=null   && !temp.getPassword().equals(examPaper.getPassword())){
			map.put("code", 2);
			map.put("msg", "密码错误");
			return JSON.toJSONString(map);
		}
		map.put("code",0);
		request.getSession().setAttribute(""+examPaper.getId(),true);
		return JSON.toJSONString(map);
	}
	
	@RequestMapping("exampaper")
	public String examd(int id,HttpServletRequest request,Model model){
		ExamPaper examPaper = exampaperService.selectById(id);
		
		if (examPaper != null && request.getSession().getAttribute(""+examPaper.getId())!=null){
			model.addAttribute("examPaper", examPaper);
			return "exam";	
		}else {
			return "examError";
		}
	}
	
	@RequestMapping("submitExampaper")
	public String submitExampaper(int id,HttpServletRequest request,Model model){
		ExamPaper examPaper = exampaperService.selectById(id);
		List<Integer> qids = examQuestionService.selectQidByEid(id);
		User user = (User) request.getSession().getAttribute("user");
		
		if (examOptionService.selectCountByEidAndUid(id,user.getId())>0){
			model.addAttribute("code", 1);
			model.addAttribute("msg", "你已经提交过这份试卷的答案");
			return "hint";
		}else{
		
			for(Integer qid : qids)
			{			
				List<Option> options =  optionDao.selectByQid(qid);
				String[] selects = request.getParameterValues(qid.toString());
				int j = 0;
				for (int i=0;i<options.size();++i){
					Option option = options.get(i);
					ExamOption examOption = new ExamOption();
					examOption.setEid(id);
					examOption.setQid(qid);
					examOption.setOid(option.getId());
					examOption.setUid(user.getId());
					if (selects!=null && j<selects.length && option.getSymbol().equals(selects[j])){
						j++;
						examOption.setOid(option.getId());
						examOption.setIsSelect(1);
					}
					examOptionService.insert(examOption);
				}
			}
			model.addAttribute("examPaper", examPaper);
			return "finishExam";	
		}
		
	}
}
