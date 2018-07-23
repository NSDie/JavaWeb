<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/8/6
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  errorPage="" import="java.util.*"%>
<%@ page import="book.book,book.bookDao"%>
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

</head>
<body>
<div style="overflow:auto;">
<table class="table">
    <thead>
    <tr>
        <th>ID</th>
        <th>Title/书名</th>
        <th>Author/作家</th>
        <th>Press/出版社</th>
        <th>Num/数量</th>
    </tr>
    </thead>
    <%
        bookDao dao=new bookDao();
        List<book> list =dao.read();
        for(book b:list)
        {%>
    <tr>
        <td><%=b.getId() %></td>
        <td><%=b.getTitle() %></td>
        <td><%=b.getAuthor() %></td>
        <td><%=b.getPress() %></td>
        <td><%=b.getNum() %></td>
    </tr>
    <%}
    %>
</table>
</div>
</body>
</html>


