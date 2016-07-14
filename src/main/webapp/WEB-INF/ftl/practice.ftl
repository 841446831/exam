
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>test paper</title>
    <style>

        #time{
            display: block;
            width: 200px;
            height: 200px;
            background: darkseagreen;
            margin: 0 auto;
            border-radius: 50px;
        }
        .time-icon{
            background: url("//static.nowcoder.com/images/img/icons/ico-media.png")no-repeat;
        }
        .subject{
            width: 1000px;
            height: 1800px;
            display: block;
            margin: 0 auto;
            background: white;
        }
        .subject-content{
            display: block;
            width: 918px;
            height: 290px;
            margin-left: 20px;
            margin-top: 30px;
        }
        .subject-question{
            margin-bottom: 20px;
        }
        .answer{
            margin-bottom: 20px;
            border: 1px solid #d4d4d4;
            border-radius: 2px;
            width: 900px;
            background: #FFF;
            display: block;
            float: left;
            margin-bottom: 15px;
        }
        .answer:hover{
            background: #f3f7f9;
        }
        .answer-radio{
            float: left;
            vertical-align: middle;
            margin-top: 20px;
            margin-right: 20px;
        }
        pre{
        	display:block
            float: left;
            vertical-align: middle;
            margin-top: 19px;
            font-size: 16px;
            color: black;
        }
        .subject-submit {
            display: block;
            height: 50px;
            margin-top: 50px;
            text-align: center;
        }
        .subject-submit .submit{
            display: inline-block;
            padding: 10px 0;
            margin-right: 15px;
            margin-top: 4px;
            width: 110px;
            color: #FFF;
            font-size: 14px;
            border-radius: 3px;
            border: none 0;
            cursor: pointer;
            outline: 0;
            background: green;
        }
        
                .mask{margin:0;
            padding:0;
            border:none;
            width:100%;
            height:100%;
            background: #e0e0e0;
            opacity: 0.5;
            z-index:9999;
            position:fixed;
            top:0;left:0;
            display:none;}
         #box{
            position: relative;
            z-index:10000;
            display: none;
            width: 500px;
            padding: 15px;
            margin: 0 auto;
            top: -1000px;;

        }
        .box-title{

            padding-left: 200px;
            padding-top: 10px;
            padding-bottom: 10px;
            color: white;
            font-size: 24px;
            border-bottom: 1px dashed #2a6496;
            background: #2aabd2;

        }
        .box-list{
            background: white;
            border: 1px solid #e0e0e0;
            padding-top: 60px;
            padding-bottom: 20px;

        }
        .close_btn{
            margin-left: 160px;
            width: 30px;height: 30px;
            border-radius: 10px;
            background: #2b669a;

            color:white;
        ;
        }
        .box-list a{
            margin-left: 130px;
            font-size: 20px;
            color: blue;

        }
         .box-list a:hover{
             background-color: #2aabd2;
             color: white;
         }
        .box-list .text{
              margin-left: 50px;
        }
    </style>
</head>
<body>

<!--头部-->
<div  id="header">
    <p class="practice">练习试卷</p>
</div>
<!--头部-->
<!--秒表设计-->
<div id="time">
    <span class="time-icon"></span>
</div>
<!--秒表设计-->
<!-- 题目-->
<form action="getQuestionsAnswer" method="post" id="app" v-on:submit.prevent="onSubmit">
    <div class="subject">
        <div class="subject-content" v-for="question in examPaper.questions">
          	
          	<div v-if="question.isRadio" class="subject-title">[单选题]</div>
          	<div v-else class="subject-title">[多选题]</div>
          	
            <div class="subject-question">
            	{{{question.face}}}
            </div>
            
            <div  class="answer" v-for="option in question.options">
               <label>	
	                <input v-if="question.isRadio" type="radio" name="{{question.id}}" class="answer-radio" value="{{option.symbol }}">
	                <input v-else type="checkbox" name="{{question.id}}" class="answer-radio" value="{{option.symbol }}">
	                <pre>{{option.symbol  }} {{option.title}}</pre>
                </label>
          </div>
       </div>

        <div class="subject-submit">
            <input type="button" id="submit" class="submit" value="提交" @click.prevent="onSubmit">
        </div>
        
    </div>
    <input type="hidden" name="eid" value="{{examPaper.id}}">

 <div id="box">
       <div class="head">
       <div class="box-title">
            完成练习<a href="javascript:void(0)" title="关闭窗口" class="close_btn" id="closeBtn">×</a>
       </div>
       <div class="box-list">
           <a href="tags.html">再做一套</a>
           <a class="text" :href="answerUrl">查看正确答案</a>
       </div>
       </div>
  </div>
  
</form>

<script src="js/jquery-3.0.0.min.js"></script>
<script src="js/vue.js"></script>
<script>
	
	var tag = '${tag}';
	var diffculty = ${diffculty};
	var count = ${count};
	
	//var url = "http://192.168.3.37:8080/exam/types";
	var url = "http://localhost:8080/exam/makepaper";
	
	var app = new Vue({
	    el: '#app',
	    data: {
	   		examPaper:null
	    },
	    methods:{
	    	onSubmit:function(){
	    	
	    		$.post('getQuestionsAnswer',$('#app').serialize(),function(result){
           		   	 	$("body").append("<div id='mask'></div>");
            		    $("#mask").addClass("mask").fadeIn("slow");
              		    $("#box").fadeIn("slow");	
              		  
	    		});
	    		
	    	}
	    }
	    ,computed:{
	    	answerUrl :function(){
	    		return "answer.html?eid="+this.examPaper.id
	    	}
	    }
	})

	function callback(data){
		app.examPaper=data;
		for (var i=0;i<app.examPaper.questions.length;++i){
			question = app.examPaper.questions[i];
			question.isRadio = question.isRadio==1?true:false;
			question.options.sort(function(a,b){
				return a.symbol-b.symbol;
			});
		}
	}
	
	
	var srcipt=document.createElement("script");
	srcipt.src = url+'?tag='+tag+'&diffculty='+diffculty+'&count='+count;
	document.body.appendChild(srcipt);
	
	 //弹出登录
           $("#submit").hover(function () {
               $(this).stop().animate({
                   opacity: '1'
               }, 600);
           }, function () {
               $(this).stop().animate({
                   opacity: '0.6'
               }, 1000);
           })
           //
           //按钮的透明度
           $("#loginbtn").hover(function () {
               $(this).stop().animate({
                   opacity: '1'
               }, 600);
           }, function () {
               $(this).stop().animate({
                   opacity: '0.8'
               }, 1000);
           });
           //关闭
           $(".close_btn").hover(function () { $(this).css({ color: 'black' }) }, function () { $(this).css({ color: '#999' }) }).on('click', function () {
               $("#box").fadeOut("fast");
               $("#mask").css({ display: 'none' });
           });
   
	
	
</script>
</body>
</html>