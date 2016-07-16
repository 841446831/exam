<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>Manage</title>
	<link rel="stylesheet" href=" css/manage.css" />
	<link rel="stylesheet" href="css/bootstrap.css" />
</head>
<body>
 	<nav>
 		<#include "nav.ftl">
 	</nav>
 	<section class="main">
	 	<aside>
	 		<#include "menu.ftl">  
	 	</aside>
	 	<section class="content">
	 		<#include "add-question.html">  
	 	</section>
 	</section>
 	<script src="js/jquery-3.0.0.min.js"></script>
 	<script src="js/manage.js"></script>
 	</body>
</html>