
<style>
/*导航栏*/
#header{
    width: 100%;
    background:rgb(73,165,222);
    border-radius: 5px;
    margin-bottom: 10px;
    opacity: 0.9;
    display:flex;
    font-size:20px;
    height: 90px;
	align-items:center;
	color:#fff;
}
#header a{
	color:white;
	
}
.nav-left{
	width:15%;
	text-align:center;
}
.nav-center{
	width:70%;
}
.nav-right{
	width:15%;
}
#header  ul,#header li{
	display:inline-block;
	margin:0;
	padding: 0;
	align-items:center;
	display:flex;
}

#header li{
	padding:0 15px;
	height:90px;
}

#header li:hover{
	 background:rgb(80,172,229);
	 cursor:pointer; 
}
</style>

<div id="header">
    <div class="nav-left logo">考试系统</div>
    <div class="nav-center">
        <ul >
            <li> <a href="tags.html">首页</a> </li>
            <li> <a href="tags.html">专项练习</a></li>
            <li> <a href="exams.html">考试系统</a></li>
        </ul>
    </div>
    
    <div class="nav-right"> 
		${user.username}
    </div>
</div>