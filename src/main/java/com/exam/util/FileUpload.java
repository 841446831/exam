package com.exam.util;

import java.io.IOException;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class FileUpload {
//	 UploadManager uploadManager = new UploadManager();
//	 String bucketname = "face";
//	 public static void main(String args[]) throws IOException{  
//	    new FileUpload().upload();
//     }

	 private String filePath;
	 private String key;
	
	 public FileUpload(String filePath,String key) {
         this.filePath = filePath;
         this.key = key;
	 }

	
	 //设置好账号的ACCESS_KEY和SECRET_KEY
	  String ACCESS_KEY = "46qeohKhCTAhjGYyy7jJtJ5JGvyDAEYU-N3Vl7x1";
	  String SECRET_KEY = "lUC5wUKPo_evHkJQ0c43vhAGIGmWK_rov9HQ-DAC";
	  //要上传的空间
	  String bucketname = "exam";
	  //上传到七牛后保存的文件名
	 // String key = "kobe4.png";
	  //上传文件的路径
	 

	  //密钥配置
	  Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	  
	  //创建上传对象
	  UploadManager uploadManager = new UploadManager();

	  //简单上传，使用默认策略，只需要设置上传的空间名就可以了
	  public String getUpToken(){
	      return auth.uploadToken(bucketname);
	  }
	  
	  public void upload() throws IOException{
		    try {
		      //调用put方法上传
		      Response res = uploadManager.put(filePath, key, getUpToken());
		      //打印返回的信息
		      System.out.println(res.bodyString()); 
		      } catch (QiniuException e) {
		          Response r = e.response;
		          // 请求失败时打印的异常的信息
		          System.out.println(r.toString());
		          try {
		              //响应的文本信息
		            System.out.println(r.bodyString());
		          } catch (QiniuException e1) {
		              //ignore
		          }
		      }       
		  }

	  
	  
}
