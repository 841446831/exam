<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>user</title>
	<style>
	body {
		text-align: center;
	}
		*{
			box-sizing: border-box;
			font-family: "微软雅黑";
		}
		.box {
			padding: 40px;
			display: inline-flex;
			flex-wrap: wrap;
			flex-flow: column;
			outline: 2px solid rgba(0,0,0,0.3);
			justify-content: space-around;
			align-items: center;
		}
		.face img{
			border-radius: 50%;
			width: 250px;
		}
		.train-info {
			display: flex;
			margin: 15px 0;
			justify-content: space-between;
			font-size: 20px;
		}
		.train-info h3{
			margin: 0;
		}
		.train-info .half{
			display: flex;
			flex-flow: column;
			align-items: center;
			padding: 0 15px;
		}
		.train-info .left{
			border-right: 2px solid rgba(0,0,0,0.3);
		}
		.name {
			font-size: 26px;
		}
		.info{
			text-align: left;
		}
	</style>
</head>
<body>
	<section class="box">
		<div class="face">
			<img src="http://oa17fj4lp.bkt.clouddn.com/${user.id}.jpg" alt="">
		</div>
		<div class="name"> ${user.username} </div>
		<div class="train-info">
			<div class="half left">
				<span>124</span>
				<h3>题目</h3>
			</div>
			<div class="half">
				<span>24</span>
				<h3>考试</h3>
			</div>
		</div>
		<dl class="info">
			<dt>Email:</dt>
			<dd>${user.email}</dd>
			<dt>爱好:</dt>
			<dd>fuck</dd>
		</dl>
	</section>
</body>
</html>