<table>
	<tr>
		<th>id</th> <th>题目标题</th> <th>是否公开</th> 
	</tr>
	<#list questions as question>
	<tr>
		<th>${question.id}</th><th>${question.face}</th><th>${question.isPublic}</th>
	</tr>
	</#list>
</table>