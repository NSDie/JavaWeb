<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/8/6
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" import="java.util.*" %>
<%@page import="user.userDao,user.user,book.book,book.bookDao"%>
<%@ page import="java.sql.*" %>

<html>
<head>
    <title>图书管理系统 beta 1.0</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap-3.3.7-distcss/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap-3.3.7-distcsscss/recordSearchResult.css">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/bootstrap-3.3.7-distcssjs/bootstrap-paginator.min.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-center text-info">
                <div>
                    欢迎使用图书管理系统 Beta 1.0
                </div>
            </h3>
            <br>
            <br>
            <div class="row clearfix">
                <div class="col-md-4 column">
                    <h2>
                        使用须知：
                    </h2>
                    <p>
                        还在开发中，目前就三种功能，<font color="#ffd700" >管理员</font>可以进行加入书籍，<font color="#7cfc00">用户</font>只能借书/还书/检索，<font color="orange">游客</font>请先登录 -》<a href="login.jsp">传送门</a>
                    </p>
                </div>
                <div class="col-md-4 column">
                    <%
                        if(session.getAttribute("name")!=null){
                            session.setAttribute("user_name",session.getAttribute("name"));
                            if(session.getAttribute("name").equals("root"))
                            {
                    %>
                    <button class="btn btn-default btn-danger" data-toggle="modal" data-target="#myModal">书籍管理</button>
                    <!-- 模态框（Modal） -->
                    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-hidden="true">×
                                    </button>
                                    <h4 class="modal-title" id="myModalLabel">
                                        添加书籍
                                    </h4>
                                </div>
                                <form id="main" method="post" action="main"  >
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control" placeholder="书名/title" name="title">
                                        </div>
                                        <br>
                                        <br>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control" placeholder="作者/author" name="author">
                                        </div>
                                        <br>
                                        <br>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control" placeholder="出版时间 出版社/press" name="press">
                                        </div>
                                        <br>
                                        <br>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control" placeholder="数目/num" name="num">
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
                                    <button type="button" class="btn btn-default"
                                            data-dismiss="modal">关闭
                                    </button>
                                    <br>
                                    <br>
                                </div>
                                </form>
                            </div><!-- /.modal-content -->
                        </div><!-- /.modal-dialog -->
                    </div><!-- /.modal -->
                    <button class="btn btn-default btn-danger" data-toggle="modal" data-target="#myModa2">用户管理</button>
                <!-- 模态框（Modal） -->
                <div class="modal fade" id="myModa2" tabindex="-1" role="dialog" aria-labelledby="myModalLabe2" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-hidden="true">×
                                </button>
                                <h4 class="modal-title" id="myModalLabe2">
                                    用户管理
                                </h4>
                            </div>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>name</th>
                                    <th>pass</th>
                                </tr>
                                </thead>
                                <%
                                    userDao dao=new userDao();
                                    List<user> list =dao.readFirstTitle();
                                    for(user u:list)
                                    {%>
                                <tr>
                                    <td><%=u.getId() %></td>
                                    <td><%=u.getName() %></td>
                                    <td><%=u.getPass() %></td>
                                </tr>
                                <%}
                                %>
                            </table>
                            <form id="main2" method="post" action="main2"  >
                            <br>
                            <input type="text" class="form-control"  placeholder="用户名/name" name="name">
                            <br>
                            <br>
                            <button type="submit" class="btn btn-primary">
                                确认删除
                            </button>
                            </form>
                                    <br>
                                    <button type="button" class="btn btn-default"
                                            data-dismiss="modal">关闭
                                    </button>
                                    <br>

                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->
                    <%}else{%>
                    <%}%>
                    <button class="btn btn-default btn-success" data-toggle="modal" data-target="#myModa3">书籍目录</button>
                <!-- 模态框（Modal） -->
                <div class="modal fade" id="myModa3" tabindex="-1" role="dialog" aria-labelledby="myModalLabe3" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-hidden="true">×
                                </button>
                                <h4 class="modal-title" id="myModalLabe3">
                                    书籍目录
                                </h4>
                            </div>
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
                            <br>
                            <form id="main3" method="post" action="main3"  >
                                <br>
                                <input type="text" class="form-control"  placeholder="不是书名！！书前的ID" name="id">
                                <br>
                                <br>
                                <button type="submit" class="btn btn-primary">
                                    确认借书
                                </button>
                            </form>
                            <br>
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">关闭
                            </button>
                            <br>

                            <br>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->
                    <button class="btn btn-default btn-primary" data-toggle="modal" data-target="#myModa4">书籍借还</button>
                    <!-- 模态框（Modal） -->
                    <div class="modal fade" id="myModa4" tabindex="-1" role="dialog" aria-labelledby="myModalLabe4" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-hidden="true">×
                                    </button>
                                    <h4 class="modal-title" id="myModalLabe4">
                                        书籍目录
                                    </h4>
                                </div>
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
                                            List<book> list1 = new ArrayList<book>();
                                            Connection con = null;
                                            PreparedStatement psmt = null;
                                            ResultSet rs = null;
                                            try {
                                                Class.forName("com.mysql.jdbc.Driver");
                                            } catch (ClassNotFoundException e) {
                                                e.printStackTrace();
                                            }

                                            try {
                                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?characterEncoding=utf8", "root", "111");
                                                String sql = "select * from "+session.getAttribute("name")+"_book";
                                                psmt = con.prepareStatement(sql);
                                                rs = psmt.executeQuery();

                                                while (rs.next()) {
                                                    int id = rs.getInt("book_id");
                                                    String title = rs.getString("book_title");
                                                    String author = rs.getString("book_author");
                                                    String press = rs.getString("book_press");
                                                    int num = rs.getInt("book_num");
                                                    book b = new book(id, title, author, press, num);
                                                    list1.add(b);
                                                }

                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            } finally {
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
                                            for(book b:list1)
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
                                <br>
                                <form id="main4" method="post" action="main4"  >
                                    <br>
                                    <input type="text" class="form-control"  placeholder="不是书名！！书前的ID" name="id">
                                    <br>
                                    <br>
                                    <button type="submit" class="btn btn-primary">
                                        确认还书
                                    </button>
                                </form>
                                <br>
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <br>

                                <br>
                            </div><!-- /.modal-content -->
                        </div><!-- /.modal-dialog -->
                    </div><!-- /.modal -->
                    <%}else{%>
                    <button class="btn btn-default btn-success" type="button" disabled="disabled">书籍目录</button>
                    <button class="btn btn-default btn-primary" type="button" disabled="disabled">书籍借还</button>
                    <%}%>
                    <br>
                    <br>
                    <div class="progress progress-striped active">
                        <div class="progress-bar progress-success"aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
                             style="width: 100%;">
                            <span class="sr-only">100% 完成（成功）</span>
                        </div>
                    </div>
                    <br>
                    <br>
                    <form name="ser" method="post" >
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="please input title." name="title">
                            <span class="input-group-btn">
						                <button class="btn btn-default" onclick="sel()">Go!</button>
                            </span>
                        </div>
                    </form>
                    <br>
                    <button class="btn btn-default btn-primary" data-toggle="modal" data-target="#myModa5">书籍检索</button>
                    <!-- 模态框（Modal） -->
                    <div class="modal fade" id="myModa5" tabindex="-1" role="dialog" aria-labelledby="myModalLabe5" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-hidden="true">×
                                    </button>
                                    <h4 class="modal-title" id="myModalLabe5">
                                        书籍目录
                                    </h4>
                                </div>
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
                                            List<book> list1 = new ArrayList<book>();
                                            Connection con = null;
                                            PreparedStatement psmt = null;
                                            ResultSet rs = null;
                                            try {
                                                Class.forName("com.mysql.jdbc.Driver");
                                            } catch (ClassNotFoundException e) {
                                                e.printStackTrace();
                                            }

                                            try {
                                                request.setCharacterEncoding("utf-8");
                                                String book_title= request.getParameter("title");
                                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?characterEncoding=utf8", "root", "111");
                                                String sql = "select * from root_book WHERE book_title like'%" +book_title+"%';";
                                                psmt = con.prepareStatement(sql);
                                                rs = psmt.executeQuery();

                                                while (rs.next()) {
                                                    int id = rs.getInt("book_id");
                                                    String title = rs.getString("book_title");
                                                    String author = rs.getString("book_author");
                                                    String press = rs.getString("book_press");
                                                    int num = rs.getInt("book_num");
                                                    book b = new book(id, title, author, press, num);
                                                    list1.add(b);
                                                }

                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            } finally {
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
                                            for(book b:list1)
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
                                <br>


                                <form id="main5" method="post" action="main5"  >
                                    <br>
                                    <input type="text" class="form-control"  placeholder="不是书名！！书前的ID" name="id">
                                    <br>
                                    <br>
                                    <button type="submit" class="btn btn-primary">
                                        确认借书
                                    </button>
                                </form>
                                <br>
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <br>
                                <br>
                            </div><!-- /.modal-content -->
                        </div><!-- /.modal-dialog -->
                    </div><!-- /.modal -->

                </div>

                <div class="col-md-4 column">
                    <%
                        if(session.getAttribute("name")!=null){
                            if(session.getAttribute("name").equals("root"))
                            {
                    %>
                    <h3 class="text-success text-left">
                        你好，管理员
                    </h3><a href="logout.jsp"><button class="btn btn-default btn-warning" type="button">注销</button></a>
                    <%}else {%>
                    <h3>你好，<%=session.getAttribute("name")%></h3><a href="logout.jsp"><button class="btn btn-default btn-warning" type="button">注销</button></a>
                    <%}%>
                    <%
                    }else{
                    %>
                    <h3>你好，游客</h3><a href="logout.jsp"><button class="btn btn-default btn-warning" type="button">登录</button></a>
                    <%
                        }
                    %>

                </div>
            </div>
        </div>
    </div>
    <br>
    <br>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="row">
                <div class="col-md-4">
                    <div class="thumbnail">
                        <img alt="300x300" src="img/tb.jpg" />
                        <div class="caption">
                            <h3>
                                《三体》系列
                            </h3>
                            <p>
                                &nbsp;&nbsp&nbsp;&nbsp文化大革命如火如荼地进行，天文学家叶文洁在期间历经劫难，被带到军方绝秘计划“红岸工程”。叶文洁以太阳为天线，向宇宙发出地球文明的第一声啼鸣，
                                取得了探寻外星文明的突破性进展。三颗无规则运行的太阳主导下，四光年外的“三体文明”百余次毁灭与重生，正被逼迫不得不逃离母星，而恰在此时，他们
                                接收到了地球发来的信息。对人性绝望的叶文洁向三体人暴露了地球的坐标，彻底改变了人类的命运。
                            </p>
                            <p>
                                &nbsp;&nbsp&nbsp;&nbsp地球的基础科学出现了异常的扰动，纳米科学家汪淼进入神秘的网络游戏《三体》，开始逐步逼近这个世界的真相。汪淼参加一次玩家聚会时，接触到了地球上应
                                对三体人到来而形成的一个秘密组织（ETO）。地球防卫组织中国区作战中心通过“古筝计划”，一定程度上挫败了拯救派和降临派扰乱人类科学界和其他领域思想
                                的图谋，获悉处于困境之中的三体人为了得到一个能够稳定生存的世界决定入侵地球。
                            </p>
                            <p>....</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="thumbnail">
                        <img alt="300x200" src="img/giants.jpg" />
                        <div class="caption">
                            <h3>
                                巨人的陨落
                            </h3>
                            <p>
                                &nbsp;&nbsp&nbsp;&nbsp“福莱特再次创造了一个既熟悉又美妙的世界，以及一种纯粹的阅读乐趣，让你根本放不下这本书。帝国衰落，英雄崛起，真爱无敌。
                                你会忍不住和书里的角色在战火硝烟中同呼吸、共命运，并希望福莱特的下一本大部头赶快砸过来。”——《Time Out纽约》
                            </p>
                            <p>
                                &nbsp;&nbsp&nbsp;&nbsp在第一次世界大战的硝烟中，每一个迈向死亡的生命都在热烈地生长……从充满灰尘和危险的煤矿到闪闪发光的皇室宫殿，从代表着权
                                力的走廊到爱恨纠缠的卧室，五个家族迥然不同又纠葛不断的命运逐渐揭晓，波澜壮阔地展现了一个我们自认为了解，但从未如此真切感受过的20世纪。
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="thumbnail">
                        <img alt="300x200" src="img/day.jpg" />
                        <div class="caption">
                            <h3>
                                白夜行
                            </h3>
                            <p>
                                &nbsp;&nbsp&nbsp;&nbsp由于母亲与自己家当铺雇的人在家里偷情，桐原亮司跑到废弃大楼的通风道里玩耍，却看到了父亲对自己的好友雪穗实施侵害的不堪一幕，
                                扭曲的惊惧与愤怒使得11岁的他用长剪刀刺死了自己的父亲，之后西本雪穗的母亲及“母亲的情人”也“意外死亡”，随后她被优雅独居的唐泽礼子收养。没有了完
                                整家庭的少男和少女，在惨剧发生后度过了平静的七年，然而，桐原亮司发现当年的案子还是有人在查，而且，已经开始怀疑到自己和唐泽雪穗身上了。没有家庭温
                                暖的二人，为了不让自己的罪行被发现，用尽各种手段把自己身边的亲人、朋友一一除掉。
                            </p>
                            <p>
                                &nbsp;&nbsp&nbsp;&nbsp这一切的起因竟是少女的母亲由于家庭窘迫，为了钱硬逼着自己的女儿出卖肉体，幼年不幸的经历让雪穗的心灵从此失去了阳光，而亮司基于
                                各种复杂的情愫一直暗中帮助雪穗报复迫害她的人，同时也帮她一步步铲除一切妨碍她成功障碍。最终桐原亮司为了让警察不追查到雪穗，用剪刀自尽，而雪穗面对桐原
                                亮司的尸体，一次也没有回头。
                            </p>
                            <p>.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
 </div>
</div>

<script>
function sel() {
    document.ser.submit();
}


    $(function () { $('#myModal').modal({
        show: false
        keyboard: true
    })});
</script>
</body>
</html>
