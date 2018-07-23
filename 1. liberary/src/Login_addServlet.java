import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.PrintWriter;
import java.io.IOException;

import java.sql.*;


@WebServlet(name="add"
        , urlPatterns={"/add"})

public class Login_addServlet extends HttpServlet {
    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException,java.io.IOException{
        String errMsg = "";
        // Servlet本身并不输出响应到客户端，因此必须将请求转发到视图页面
        RequestDispatcher rd;
        // 获取请求参数
        String username = request.getParameter("username");
        String pass = request.getParameter("password");
        String pass2 = request.getParameter("password2");
        try{
          DbDao dd = new DbDao("com.mysql.jdbc.Driver",
                  "jdbc:mysql://localhost:3306/test","root","111");
            ResultSet rs = dd.query("select pass from user_inf"
                    + " where name = ?", username);
         if(rs.next())
         {
             errMsg += "您的用户已经存在,可以立即登录。";
         }
         else {
             if (username != null && !username.equals("") && pass != null && !pass.equals("")) {
                 if(pass.length()<6)
                 {
                     errMsg += "密码不能小于6位。";
                 }
                 else if(!pass.equals(pass2))
                 {
                     errMsg += "两次密码输入不一致！";
                 }
                 else {
                     dd.insert("INSERT INTO user_inf(name,pass) values('" + username + "','" + pass + "');");
                     dd.modify("create table "+username+"_book(book_id int not null auto_increment, book_title varchar(100) not null," +
                             "book_author varchar(100) not null,book_press varchar(100) not null,book_num int not null," +
                             "primary key (book_id))ENGINE=InnoDB DEFAULT CHARSET=utf8;");
                     errMsg += "注册成功！返回登录。";
                 }
             }
             else
             {
                 errMsg += "用户名和密码不能为空！";
             }
         }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
         //如果出错，重新注册；
        if (errMsg != null && !errMsg.equals(""))
        {
            rd = request.getRequestDispatcher("/login_add.jsp");
            request.setAttribute("err" , errMsg);
            rd.forward(request,response);
        }
    }
}
