
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
    <p class="practice">${examPaper.title}</p>
</div>
<!--头部-->
<!--秒表设计-->
<div id="time">
    <span class="time-icon"></span>
</div>
<!--秒表设计-->
<!-- 题目-->
<form action="submitExampaper" method="post" id="app" >
    <div class="subject">
    	<#list examPaper.questions as question>
   
        <div class="subject-content">
          	<#if (question.isRadio==1)>
          	<div  class="subject-title">[单选题]</div>
          	<#else>
          	<div  class="subject-title">[多选题]</div>
          	</#if>
            <div class="subject-question">
            	${question.face}
            </div>
            <#list question.options as option>
            <div  class="answer">
               <label>	
	              	<#if (question.isRadio==1)>
	                <input  type="radio" name="${question.id}" class="answer-radio" value="${option.symbol }">
	              	<#else>
	                <input  type="checkbox" name="${question.id}" class="answer-radio" value="${option.symbol }">
	              	</#if>
	                <pre>${option.symbol  } ${option.title}</pre>
                </label>
            </div>
            </#list>
       </div>	

    	</#list>
        <div class="subject-submit">
            <input type="submit" id="submit" class="submit" value="提交" >
        </div>
        
    </div>
    <input type="hidden" name="id" value="${examPaper.id}">
	
  
</form>

<script src="js/jquery-3.0.0.min.js"></script>
<script src="js/vue.js"></script>

</body>
</html>