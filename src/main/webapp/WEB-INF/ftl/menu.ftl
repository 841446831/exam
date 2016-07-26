<link rel="stylesheet" href="css/menu.css" />
<div id="menu">
 <ul>
    	<li data-url="user" >个人信息</li>
		
	
		<#if user.group == 1>
			<li data-url="add-question.html">添加题目</li>
			<li data-url="questions">题目管理</li>
		  	<li data-url="add-exam.html">添加试卷</li>
		  	<li data-url="examPapers">试卷管理</li>
			<li>申请成为管理员</li>
		</#if>
		
		<#if user.group == 2>
			<li data-url="users">用户管理</li>
			<li data-url="questions">题目管理</li>
			<li data-url="examPapers">考试管理</li>  
		</#if>
</ul>
</div>