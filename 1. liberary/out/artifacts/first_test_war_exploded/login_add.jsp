<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/8/4
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title> 用户注册 </title>
    <meta name="website" content="http://www.crazyit.org" />
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
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form role="form" id="add" method="post" action="add">
                <div class="form-group">
                    <label >用户名</label><input type="text" class="form-control" placeholder="Username" name="username" oninput="this.value=this.value.replace(/[\u4e00-\u9fa5\d]/g,'');" />
                </div>
                <div class="form-group">
                    <label >密    码(不小于6位）</label><input type="password" class="form-control" placeholder="Password" name="password" oninput="this.value=this.value.replace(/[\u4e00-\u9fa5\d]/g,'');" />
                </div>
                <div class="form-group">
                    <label >确认密码</label><input type="password" class="form-control" placeholder="Password" name="password2" oninput="this.value=this.value.replace(/[\u4e00-\u9fa5\d]/g,'');" />
                </div>
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
                <button class="btn btn-default" type="submit">确 认</button>
                <button class="btn btn-default" type="button"><a href="login.jsp">返 回</a></button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
