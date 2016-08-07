<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>试卷列表</title>
   <style >
   
   #userlistContainer{
    width: 1000px;
    padding: 10px;
    background: #f7f7f7;
}
.userlistHead{
    padding: 10px 10px 0px 10px;
    font-size: 26px;
    font-weight: bold;
   /* border-bottom: 2px solid #f7f7f7;*/
}
.userOr{
    padding:0px 10px 15px 10px;
    border-bottom: 2px solid #aaaaaa;
    color: #379be7;
}
.editBtn{
     padding: 10px;
    margin-top: 30px;
}
.editBtn .delete{
    background: #3755ad;

}
.editBtn .delete:hover{
    background: rebeccapurple;
}
.editBtn a{
    padding: 10px;
    background: #404d5b;
    color: white;
    font-size: 18px;
    text-decoration: none;
    border-radius: 5px;
}
.editBtn a:hover{
    background: black;
}
.userlist{
    padding: 10px;
    margin-top: 40px;
}
.userlist table{
    width: 900px;
    border-collapse: collapse;
}
.userlist th{

    background:rgb(73,165,222);
    padding: 15px;
    opacity: 0.9;

}
.userlist td{
    
    padding: 11px;
    border-bottom: 1px dashed #2f3238;
}
.userlist a{
    color: #0000CC;
    cursor: pointer;
}
.userlist a:hover{
    color: red;
}

.nowTestcontainerTable td{
    border:none;
    padding: 15px;
    text-align: center;
    background: white;
}
.userlistPage {
    padding: 20px;
    margin-top: 30px;
    margin-bottom: 50px;
}
.userlistPage li{
    list-style: none;
    float: left;

}
.userlistPage li a{
    padding: 10px;
    margin-right: 10px;
    background: white;
    text-decoration: none;
    border: 1px solid #aaaaaa;
}
.userlistPage li a:hover{
    color: red;
    background: #aaaaaa;

}
   
</style>
</head>
<body id="app">
  <div id="userlistContainer">
      <div class="userlistHead">
         <p> 试卷管理</p>

      </div>
      <div class="userOr">
          学员/工程师
      </div>
      <div class="editBtn">
         <a href="">添加试卷</a>
          
      </div>
      <div class="userlist">
          <table >

              <tr>
                  
                  <th>编号</th>
                  <th>标题</th>
                  <th>密码</th>
                  <th>编辑</th>
              </tr>
                <#list examPapers as paper>
              <tr>
                  <td> ${paper.id}</td>
                  <td> ${paper.title}</td>
                  <td>${paper.password} </td>
                 
                  <td><a>删除</a>/<a>修改</a></td>
              </tr>
              </#list>
              </table>
          <div class="userlistPage">
              <ul class="page">
                  <li><a href="#">&laquo;</a></li>
                  <li><a href="#">1</a></li>
                  <li><a href="#">2</a></li>
                  <li><a href="#">3</a></li>
                  <li><a href="#">4</a></li>
                  <li><a href="#">5</a></li>
                  <li><a href="#">&raquo;</a></li>
              </ul>
          </div>
      </div>
  </div>
</body>
</html>






