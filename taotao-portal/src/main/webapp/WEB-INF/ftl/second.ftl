<html>
<head>
	<title>${title!"tilet"}</title>
</head>
<body>
	<#include "first.ftl"/>		
	<label>id:</label>${student.id}<br>
	<label>name:</label>${student.name}<br>
	<label>address:</label>${student.address}<br>
	studentlist:
	<table border="1">
	<#list students as s>
		<#if s_index % 2 == 0>
		<tr style="color:red">
		<#else>
		<tr>
		</#if>
			<td>${s_index}</td>
			<td>${s.id}</td>
			<td>${s.name}</td>
			<td>${s.address}</td>
		</tr>
	</#list>
	</table>
	<br>
	datetime:${curdate?datetime}
</body>
</html>
