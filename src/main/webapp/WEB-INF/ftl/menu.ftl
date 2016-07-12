<ul class="menu">
	<li data-url="user">个人信息</li>
	<li>申请成为工程师</li>
	<#if user.group == 1>
		<li data-url="add-question.html">添加题目</li>
		<li>题目管理</li>
	  	<li>试卷管理</li>
		<li>申请成为管理员</li>
	</#if>
	<#if user.group == 2>
		<li data-url="users">用户管理</li>
		<li data-url="questions">题目管理</li>
		<li data-url="exams">考试管理</li>  
	</#if>
</ul>