<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/8/15
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="room.roomDao,room.room" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>房间</title>
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
<%
    String mess=(String)session.getAttribute("message");
    if("".equals(mess)&& mess==null) {
    }
    else{%>
    <script type="text/javascript">
    alert("<%=mess%>");
       </script>
   <% mess=null;}%>
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
                <li class="active"><a href="main.jsp">房间列表</a></li>
                <li class="active"><a href="main.jsp">退房处理</a></li>
                <li class="active"><a href="main.jsp">预定房间</a></li>
                <%}%>
            </ul>
        </div>
    </div>
</nav>
<br>

<button class="btn btn-default btn-success" data-toggle="modal" data-target="#myModal">下单</button>
<h5><font color="green">可入住</font>;<font color="red"> 不可入住</font></h5>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    订单信息
                </h4>
            </div>
            <form id="list" method="post" action="list">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-lg-6">
                            <input type="text" class="form-control" placeholder="姓名/name" name="name">
                        </div>
                        <br>
                        <br>
                        <div class="col-lg-6">
                            <input type="text" class="form-control" placeholder="身份证/id_card" name="id_card">
                        </div>
                        <br>
                        <br>
                        <div class="col-lg-6">
                            <input type="text" class="form-control" placeholder="房间/room" name="room">
                        </div>
                        <br>
                        <br>
                        <div class="col-lg-6">
                            <input type="text" class="form-control" placeholder="价格/price" name="price">
                        </div>
                        <br>
                        <br>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">
                        提交更改
                    </button>
                    <br>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <br>
                    <br>
                </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<br>
<div style="overflow:auto;">
    <table class="table">
        <thead>
        <tr>
            <th>room/房间号</th>
            <th>num/人数</th>
            <th>price/价格</th>
        </tr>
        </thead>
        <% roomDao dao=new roomDao(); List<room> list =dao.readFirstTitle(); for(room r:list) { if(r.getIs()==1){%>
        <tr class="danger">
            <td>
                <%=r.getRoom() %>
            </td>
            <td>
                <%=r.getNum() %>
            </td>
            <td>
                <%=r.getMoney() %>
            </td>
        </tr>
        <%}else if(r.getIs()==0){ %>
        <tr class="success">
            <td>
                <%=r.getRoom() %>
            </td>
            <td>
                <%=r.getNum() %>
            </td>
            <td>
                <%=r.getMoney() %>
            </td>
        </tr>
           <%}}%>
    </table>
</div>

</body>
</html>
