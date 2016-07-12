<table>
	<tr>
		<th>id</th> <th>用户名</th> <th>密码</th> <th>邮箱</th> 
	</tr>
	<#list users as user>
	<tr>
		<th>${user.id}</th><th>${user.username}</th><th>${user.password}</th><th>${user.email}</th>
	</tr>
	</#list>
</table>