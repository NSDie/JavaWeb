<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/8/6
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注销</title>
</head>
<body>

<%
    response.setHeader("refresh","2;url=login.jsp");//定时跳转
    session.invalidate();//注销
%>
<h3>你好,你已经退出本系统,两秒后跳会首页</h3>
<h3>如没有跳转,请按<a href="login.jsp">这里</a>
</body>
</html>
