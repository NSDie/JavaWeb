import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;

@WebServlet(name="main",urlPatterns = "/main")

public class MainServlet extends HttpServlet {

    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, java.io.IOException {
        RequestDispatcher rd;
        String errMsg="";

        try {
            request.setCharacterEncoding("utf-8");
            String book_title = request.getParameter("title");
            String book_author = request.getParameter("author");
            String book_press = request.getParameter("press");
            String book_num = request.getParameter("num");
            int num = 0;
            if (book_num != null && !book_num.equals("")) {
                num = Integer.parseInt(book_num);
                System.out.println(num);
            }
            // Servlet本身并不执行任何的业务逻辑处理，它调用JavaBean处理用户请求
            DbDao dd = new DbDao("com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/test?characterEncoding=utf8", "root", "111");
            // 查询结果集
            ResultSet rs = dd.query("select * from root_book"
                    + " where book_title ='"+book_title+"';");

              if(book_title!=null&&book_author!=null&&book_press!=null&&book_num!=null)
              {
                  if (rs.next()) {
                      if (book_press.equals(rs.getString("book_press"))) {
                          num += rs.getInt("book_num");
                          dd.modify("UPDATE root_book SET book_num=" + num + " WHERE book_title='" + book_title + "';");
                          // 获取session对象
                          HttpSession session = request.getSession(true);
                          // 获取转发对象
                          rd = request.getRequestDispatcher("/main.jsp");
                          // 转发请求
                          rd.forward(request, response);
                          System.out.println(num);
                          System.out.println(book_title);
                      } else {
                          dd.insert("INSERT INTO root_book(book_title,book_author,book_press,book_num) values('" + book_title + "','"
                                  + book_author + "','" + book_press + "'," + num + ");");
                          // 获取session对象
                          HttpSession session = request.getSession(true);

                          // 获取转发对象
                          rd = request.getRequestDispatcher("/main.jsp");
                          // 转发请求
                          rd.forward(request, response);
                      }
                  } else {
                      dd.insert("INSERT INTO root_book(book_title,book_author,book_press,book_num) values('" + book_title + "','"
                              + book_author + "','" + book_press + "'," + num + ");");
                      // 获取session对象
                      HttpSession session = request.getSession(true);
                      // 获取转发对象
                      rd = request.getRequestDispatcher("/main.jsp");
                      // 转发请求
                      rd.forward(request, response);

                  }
              }
              else
              {
                  System.out.println("Wrong！！！");
              }
            } catch(Exception e){
                e.printStackTrace();
            }

        if (errMsg != null && !errMsg.equals(""))
        {
            rd = request.getRequestDispatcher("/main.jsp");
            request.setAttribute("err" , errMsg);
            rd.forward(request,response);
        }
        }
}
