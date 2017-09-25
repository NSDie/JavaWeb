<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/8/15
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap-3.3.7-distcss/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap-3.3.7-distcsscss/recordSearchResult.css">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/bootstrap-3.3.7-distcssjs/bootstrap-paginator.min.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<form id="tt" name="tt" method="post" action="tt" >
    <div class="input-group">
        <span class="input-group-addon">房间号</span>
        <input type="text" class="form-control" placeholder="room" name="room" style="width: 200px;">
    </div>
    <button type="submit" class="btn btn-info">提交</button>
</form>
    <!-- 输出出错提示 -->
    <label class="col-sm-2 control-label" for="formGroupInputSmall" style="color:red;font-weight:bold">
            <%
       if (request.getAttribute("err") != null)
       {
        out.println(request.getAttribute("err"));
       }
       %>

</body>
</html>
