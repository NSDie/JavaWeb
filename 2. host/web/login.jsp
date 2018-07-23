<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/8/4
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>

<html >
<head>
    <title> 用户登录 </title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap-3.3.7-distcss/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap-3.3.7-distcsscss/recordSearchResult.css">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/bootstrap-3.3.7-distcssjs/bootstrap-paginator.min.js"></script>
</head>
<body>
<br>
<br>
<h3 class="col-sm-2 control-label" for="formGroupInputLarge">请输入用户名和密码:</h3>

<div style="padding: 80px 250px 100px;">

    <form id="login" method="post" action="login" class="bs-example bs-example-form" role="form" >
        <div class="row">
        <div class="col-lg-6">
         <div class="input-group">
            <span class="input-group-addon">用户名</span>
            <input type="text" class="form-control" placeholder="Username" name="username"/>
         </div>
        </div>
        <br>
        <br>
     <div class="col-lg-6">
        <div class="input-group">
            <span class="input-group-addon">密 &nbsp;&nbsp 码</span>
            <input type="password" class="form-control" placeholder="Password" name="pass"/>
        </div>
        </div>
        </div>
        <br>
        <br>

        <!-- 输出出错提示 -->
        <label class="col-sm-2 control-label" for="formGroupInputSmall" style="color:red;font-weight:bold">
        <%
       if (request.getAttribute("err") != null)
       {
        out.println(request.getAttribute("err"));
       }
       %>
       </label>
        <br/>
        <br>
        <br>
        <br>
        <button type="submit" class="btn btn-success">登录</button>
        <a href="login_add.jsp"><button type="button" class="btn btn-primary">注册</button></a>
       <br/>
    </form>
    </div>
    </body>
</html>
