<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/8/15
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page import="room.room" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.*" %>
<html>
<head>
    <title>搜索房间</title>
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
<form name="ser" method="post" >
    <div class="input-group">
        <select name="s" class="form-control" style="width: 100px;">
            <option value="room">房间号</option>
            <option value="name">客户姓名</option>
            <option value="id_card">客户身份证</option>
            <option value="date">入住日期</option>
        </select>
        <input type="text" class="form-control" placeholder="please input something." name="text" style="width: 200px;">
        <button class="btn btn-default" onclick="sel()">Go!</button>
    </div>
</form>
<div style="overflow:auto;">
    <table class="table">
        <thead>
        <tr>
            <th>room/房间号</th>
            <th>num/人数</th>
            <th>price/价格</th>
        </tr>
        </thead>
        <%  List<room> list =new ArrayList<room>();
            Connection con = null;
            PreparedStatement psmt = null;
            ResultSet rs = null;
            String Value=request.getParameter("s");
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            String sql="";
            try{
                request.setCharacterEncoding("utf8");
                String text= request.getParameter("text");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/host?characterEncoding=utf8", "root", "111");
                System.out.println(Value);
                System.out.println(text);
                if(Value.equals("room")) {
                     sql = "select * from room_inf WHERE room like'%" + text + "%';";
                }else if(Value.equals("name")){
                   String ntext=new String(text.getBytes("ISO-8859-1"),"utf-8");
                   System.out.println(ntext);
                     sql = "select * from room_inf WHERE username like'%" + ntext + "%';";
                }else if(Value.equals("id_card")){
                     sql = "select * from room_inf WHERE id_card like'%" + text + "%';";
                }else if(Value.equals("date")){
                     sql = "select * from room_inf WHERE date like'%" + text + "%';";
                }
                psmt = con.prepareStatement(sql);
                rs = psmt.executeQuery();
                while (rs.next()) {
                    String room=rs.getString("room");
                    int num=rs.getInt("number");
                    int money=rs.getInt("money");
                    int is=rs.getInt("IsNull");
                    room r = new room(room,num,money,is);
                    list.add(r);
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (psmt != null) {
                        psmt.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(!sql.equals("")){
            for(room r:list) { if(r.getIs()==1){%>
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
        <%}}}%>
    </table>
</div>

<br>
<script>
    function sel() {
        document.getElementById("").options["s"].value();
        document.ser.submit();
    }
</script>
</body>
</html>
