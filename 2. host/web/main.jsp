<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/8/14
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>酒店管理系统</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap-3.3.7-distcss/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap-3.3.7-distcsscss/recordSearchResult.css">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/bootstrap-3.3.7-distcssjs/bootstrap-paginator.min.js"></script>
</head>

<body style="background: url(images/1.jpg)" >
<nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" >酒店管理系统</a>
    </div>
    <ul class="nav navbar-nav navbar-right">
      <li>
          <% if(session.getAttribute( "name")!=null){
              session.setAttribute("name",session.getAttribute("name"));
          %>
          <h4 class="text-success text-left">
              你好，管理员
          </h4>
          <a href="logout.jsp"><span class="glyphicon glyphicon-log-in"></span> 注销</a>
          <%}else{%>
          <a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> 登录</a>
          <%}%>
      </li>
    </ul>
  </div>
</nav>  
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="main.jsp">房间管理系统</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <% if(session.getAttribute( "name")!=null) {%>
                <li class="active"><a href="add.jsp">添加房间</a></li>
                <li class="active"><a href="search.jsp">房间搜索</a></li>
                <li class="active"><a href="count.jsp">下单结算</a></li>
                <li class="active"><a href="quit.jsp">退房处理</a></li>
                <li class="active"><a href="order.jsp">预定房间</a></li>
                <%}else {%>
                <li class="active"><a href="main.jsp">添加房间</a></li>
                <li class="active"><a href="main.jsp">房间搜索</a></li>
                <li class="active"><a href="main.jsp">下单结算</a></li>
                <li class="active"><a href="main.jsp">退房处理</a></li>
                <li class="active"><a href="main.jsp">预定房间</a></li>
                <%}%>
            </ul>
        </div>
    </div>
</nav>



</body>

</html>
