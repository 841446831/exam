package com.exam.spider;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.exam.entity.Type;
import com.exam.service.TypeService;
import com.sun.javafx.collections.MappingChange.Map;
import com.sun.org.apache.xml.internal.security.keys.content.RetrievalMethod;

import static java.lang.System.out;

public class Main {
	private static ApplicationContext applicationContext;
	private static TypeService typeService; 
	private static CloseableHttpClient httpClient;
	private static String cookie[] = {"t=0A359ADC4FA2E646A64EF0DBE8A5264B;  ",
							"NOWCODERUID=B6BD906295CD912B7E390FCE6C588687; _cnzz_CV1253353781=%E6%98%AF%E5%90%A6%E7%99%BB%E5%BD%95%7C%E5%B7%B2%E7%99%BB%E5%BD%95%7C1467874191489; CNZZDATA1253353781=9734587-1467618196-null%7C1467867022; t=AA23270DBE4ED53730C39A7433304E08; NOWCODERCLINETID=61517148B9829D6B7B7716D0E5878E25; SERVERID=8b30bcebacc57c03f5f188b29f8635ad|1467867023|1467855327",
								"NOWCODERUID=354E3B1ECC1266964520C2C05CAA3400; NOWCODERCLINETID=3D162F04831701EB621FA366B2E81228; t=BEDF5AF694A0415B459987AD35765188; SERVERID=04b0d01c5f76391d48534b2801b3cad1|1467867128|1467852905; CNZZDATA1253353781=1167893929-1467768302-null%7C1467867128; _cnzz_CV1253353781=%E6%98%AF%E5%90%A6%E7%99%BB%E5%BD%95%7C%E5%B7%B2%E7%99%BB%E5%BD%95%7C1467874328522"};
	private static HttpGet httpGet;
	private static HttpPost httpPost;
	private static HttpEntity httpEntity;
	private static HttpResponse httpResponse;
	
	static{
		httpClient=HttpClients.createDefault();
		httpGet = new HttpGet();
		httpPost =new HttpPost();
		httpGet.setHeader("Cookie", cookie[0]);
		httpPost.setHeader("Cookie",cookie[0]);
		applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		typeService = (TypeService) applicationContext.getBean("typeService");
	}
	
	public static void insertTags() {
		try {
			httpGet.setURI(new URI("http://www.nowcoder.com/intelligentTest"));
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try{
        	CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null){
                String html = EntityUtils.toString(httpEntity);
                Document document = Jsoup.parse(html);
                Element script = document.select("script").last();
                html = script.html();
                String jsonString = html.substring(html.indexOf('['),html.lastIndexOf(']')+1);
                JSONArray json = (JSONArray) JSON.parse(jsonString);
                for (int i=0;i<json.size();++i){
                	JSONObject jsonObject = json.getJSONObject(i);
                	String title = jsonObject.getString("title");
                	JSONArray items = jsonObject.getJSONArray("items");
                	for (int j=0;j<items.size();++j){
                		JSONObject item = items.getJSONObject(j);
                		Type type = new Type();
                		type.setSuperType(title);
                		type.setType(item.getString("title"));
                		type.setId(item.getInteger("id"));
                		typeService.insertType(type);
                	}
                }
            }
        }catch (Exception e){
            e.printStackTrace(System.out);
        }
	}
	
/**
 * 
 * @param source [0,4)
 * @param tag  题目标签
 * @param difficulty [1,5]
 * @return null or 一个表示题目的字符串
 */
	@SuppressWarnings("resource")
	private static String getQuestions(int source,int tag,int difficulty) {
	  try {
		  Random random = new Random();
		  httpPost.setURI(new URI("http://www.nowcoder.com//makePaper"));
		  List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	      nvps.add(new BasicNameValuePair("source",""+source));
	      nvps.add(new BasicNameValuePair("tagIds", ""+tag));
	      nvps.add(new BasicNameValuePair("difficulty", ""+difficulty));
	      nvps.add(new BasicNameValuePair("questionCount", "5"));
	      httpPost.setEntity(new UrlEncodedFormEntity(nvps));
	      String fakeIp = rndIp();  
	      System.out.println(rndIp());
	      httpPost.addHeader("X-Forwarded-For", fakeIp);  
	      HttpResponse response = httpClient.execute(httpPost);
	      if (response.getFirstHeader("Location")==null) {
	    	  httpEntity = response.getEntity();
	    	  String htmlString = EntityUtils.toString(httpEntity);
	    	  if (htmlString.indexOf("操作太快")>=0){
	    		  out.println("操作太快");
	    	  }else if(htmlString.indexOf("没有足够")>=0){
	    		  out.println("没有足够题目");
	    	  }else {
	    	 
	    		  out.println(htmlString);
	    	  }
	    	  return null;
	      }
	      
	      String url = response.getFirstHeader("Location").getValue();
	      httpGet.setURI(new URI(url));
	      response = httpClient.execute(httpGet);
	      
	      java.util.Map<Integer,JSONObject> questionsMap = new HashMap<Integer,JSONObject>();
	      
	      int id = 0;
	      do {
	    	  id++;
	    	  httpEntity = response.getEntity();
	    	  if (httpEntity!=null){
	    		  String html = EntityUtils.toString(httpEntity);
	    		  if (html.indexOf("单选")<0 && html.indexOf("不定项选择题")<0 ){
	    			  out.println("不是选择");
	    			  continue;
	    		  }else if(html.indexOf("no right")>=0){
	    			  out.println("没有权限");
	    			  continue;
	    		  }
	          
	    		  Document document = Jsoup.parse(html);	          
	    		  //题面,获取题目
	    		  JSONObject question = new JSONObject();
			      Elements faceElements = document.select(".subject-question"); 
		          String face = faceElements.html();
		          face = face.substring(0,face.lastIndexOf("<script"));
		          question.put("face", face);
		          JSONArray optionsJsonArray = new JSONArray();
		          Elements options = document.select("input[type=radio]");{
		        	  out.println("单选");
		          }	
		          if (options == null || options.size()==0){
		        	   options = document.select("input[type=checkbox]");
				        out.println("多选");
		          }
		        	char c = 'A';
		          for (Element option:options){
		        	  JSONObject optionJsonObject = new JSONObject();
		        	  optionJsonObject.put("option", option.nextElementSibling().html());
		        	  optionJsonObject.put("title", c++);
		        	  optionsJsonArray.add(optionJsonObject);
		          }
		          if (optionsJsonArray.size() ==0) continue;
		          question.put("options", optionsJsonArray);
		          question.put("type", tag);
		          question.put("diffculty", difficulty);
		          questionsMap.put(id, question);
		          String finishUrl = "http://www.nowcoder.com"+document.select("#submitForm").attr("action");
		          httpPost.setURI(new URI(finishUrl));
		          response = httpClient.execute(httpPost);
	    	  }
		}while (response.getStatusLine().getStatusCode() == 200);
	    if (questionsMap.size() == 0) return null;
	      //全部做完，并且提交，以下获取答案
		url = response.getFirstHeader("Location").getValue();
		httpGet.setURI(new URI(url));
	    response = httpClient.execute(httpGet);
	    httpEntity = response.getEntity();
	    if (httpEntity!=null){
	    	Document document = Jsoup.parse(EntityUtils.toString(httpEntity));
	    	Elements as = document.select(".analytic-page a");
	    	String solutionUrlString =  "http://www.nowcoder.com"+as.get(1).attr("href");
	    	httpGet.setURI(new URI(solutionUrlString));
	    	httpEntity = httpClient.execute(httpGet).getEntity();
	    	if (httpEntity!=null){
	    		Document analizyDocument = Jsoup.parse(EntityUtils.toString(httpEntity));
	    		Elements list = analizyDocument.select(".subject-num-list a");
	    		for (int i=0;i<list.size();++i){
	    			JSONArray options = new JSONArray();
	    			if ((questionsMap.get(i+1)) != null){
	    				options = questionsMap.get(i+1).getJSONArray("options");
	    				String tempUrlString = "http://www.nowcoder.com"+ list.get(i).attr("href");
	    				httpGet.setURI(new URI(tempUrlString));
	    				response = httpClient.execute(httpGet);
	    				httpEntity = response.getEntity();
	    				if (httpEntity != null){
	    					Element element=Jsoup.parse(EntityUtils.toString(httpEntity)).select(".result-subject-answer h1").get(0);
	    					String ansString = element.text();
	    		
	    					boolean flag = true;
	    					for (int j=0;j<options.size();++j){
	    						JSONObject option = options.getJSONObject(j);
	    						if (ansString.indexOf(option.getString("title"))>=0){
	    							option.put("isTrue", true);
	    							flag = false;
	    						}
	    					}
	    					if (flag) {
	    						System.out.println("第"+i+1+"题找不到正确答案");
	    						questionsMap.remove(questionsMap.get(i+1));
	    					}
	    					
	    				}
	    			}
	    		}
	    	}
	    }
	     
	    return JSON.toJSONString(questionsMap).toString();
	  }catch(Exception e){
		  e.printStackTrace();
	  }
	  return null;
	}
	
	public static void main(String[] args) {
//		insertTags();
		insertQuestions();
	}


	private static void insertQuestions() {
		// TODO Auto-generated method stub
		List<Type> types = typeService.SelectAll();
		Random random = new Random();
		String s = getQuestions(3, types.get(random.nextInt(types.size())).getId(), random.nextInt(5)+1);
		out.println(s);
	}
	
	 /**  
     * 伪造IP  
     *   
     * @return  
     */  
    private static String rndIp() {  
        return rndInt(255) + "." + rndInt(255) + "." + rndInt(255) + "."  
                + rndInt(255);  
    }  
    private static int rndInt(int max) {  
        Random rnd = new Random();  
        return rnd.nextInt(max);  
    }  
}
