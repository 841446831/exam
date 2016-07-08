package com.exam.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.exam.entity.Question;
import com.exam.entity.Type;
import com.exam.service.QuestionService;
import com.exam.service.TypeService;

@Controller
public class TypeController {

	@Resource
 	private TypeService typeService;
	@Resource
	private QuestionService questionService;
 	
	@SuppressWarnings("unchecked")
	@RequestMapping("types")
	@ResponseBody
	public String types()
	{
	    Map<String, Object> map = null;
	    
	    List<Type> listType = new ArrayList<Type>();
	    
	    Map<String,Object> mapListType = new HashMap<>();
	    
	    listType = typeService.SelectAll();
	    
	    
	    //mapListType.put("编程语言",list);
	    for (Type type:listType){
	    	if  (!mapListType.containsKey(type.getSuperType())){
	    		//System.out.println(type.getSuperType());
	    		mapListType.put(type.getSuperType(),new ArrayList<Object>());
	    	}
	    	//System.out.println(type);
	    	JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(type));
	    	jsonObject.put("count", questionService.typeOfQuestionCount(type.getId()));
	    	((List<Object>) mapListType.get(type.getSuperType())).add(jsonObject);
	    }
	    
	    Set set = mapListType.keySet();
	    System.out.println(set.size());
	    
	    List<Map<String, Object>> list = new ArrayList<>();
	    
	    for(Object key : set)
	    {
	    	 map = new HashMap<>();
	    	 map.put("title",key);
	    
	    	 map.put("list", mapListType.get(key));
	    	 list.add(map);
	    }
	    
	    System.out.println(list);
	    
	    String json = JSON.toJSONString(list);
	    
	    System.out.println(json);
		
	    return json;
	}
	
}
