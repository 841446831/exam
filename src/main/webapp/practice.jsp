<%@ page pageEncoding="UTF-8"%>
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
<form action="" class="" method="post" id="app">
    <div class="subject">
        <div class="subject-content" v-for="question in examPaper.questions">
          	
          	<div v-if="question.isRadio" class="subject-title">[单选题]</div>
          	<div v-else class="subject-title">[多选题]</div>
          	
            <div class="subject-question">
            	{{{question.face}}}
            </div>
            
            <div  class="answer" v-for="option in question.options">
               <label>	
	                <input v-if="question.isRadio" type="radio" name="{{question.id}}" class="answer-radio" value="{{opSymbol[$index]}}">
	                <input v-else type="checkbox" name="{{question.id}}" class="answer-radio" value="{{opSymbol[$index]}}">
	                <pre>{{ opSymbol[$index] }} {{option.title}}</pre>
                </label>
          </div>
       </div>

        <div class="subject-submit">
            <input type="submit" class="submit" value="提交">

        </div>
    </div>
    <input type="hidden" name="eid" value="{{examPaper.id}}">
</form>

<script src="js/jquery-3.0.0.min.js"></script>
<script src="js/vue.js"></script>
<script>
//	var tag = ${tag};
//	var diffculty = ${diffculty};
//	var count = ${count};
	
	var tag = '589,570';
	var diffculty = 1;
	var count = 5;
	
	//var url = "http://192.168.3.37:8080/exam/types";
	var url = "http://localhost:8080/exam/makepaper";
	
	var app = new Vue({
	    el: '#app',
	    data: {
	   		examPaper:null,
	   		opSymbol:['A','B','C','D','E','F','G','H','I','J','K']
	    }
	})

	function callback(data){
		app.examPaper=data;
		for (var i=0;i<examPaper.questions;++i){
			question = examPaper.questions[i];
			question.isRadio = question.isRadio==1?true:false;
		}
	}
	
	var srcipt=document.createElement("script");
	srcipt.src = url+'?tag='+tag+'&diffculty='+diffculty+'&count='+count;
	document.body.appendChild(srcipt);
</script>
</body>
</html>