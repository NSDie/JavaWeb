import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;

@WebServlet(name="main4",urlPatterns = "/main4")

public class Del_book extends HttpServlet{
    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, java.io.IOException {
        RequestDispatcher rd;
        try {
            request.setCharacterEncoding("utf-8");
            String id = request.getParameter("id");
            String name = request.getSession().getAttribute("user_name").toString();
            int book_id = 0;
            if (id != null && !id.equals("")) {
                book_id = Integer.parseInt(id);
            }
            int num = 0;
            DbDao dd = new DbDao("com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/test?characterEncoding=utf8", "root", "111");
            ResultSet rs=dd.query("select * from "+name+"_book"
                    + " where book_id = ?", book_id);
            if(rs.next())
            {
                if(rs.getInt("book_num")>1) {
                    num=rs.getInt("book_num");
                    num--;
                    dd.modify("UPDATE " + name + "_book SET book_num=" + num + " WHERE book_id=" + book_id + ";");
                    String title=rs.getString("book_title");
                    String author=rs.getString("book_author");
                    String press=rs.getString("book_press");
                    ResultSet rs2=dd.query("select * from root_book"
                            + " where book_title = '"+title+"' and book_author='"+author+"' and book_press='"+press+"';");
                    if(rs2.next())
                    {
                        num=rs2.getInt("book_num");
                        book_id=rs2.getInt("book_id");
                        num++;
                       dd.modify("UPDATE root_book SET book_num=" + num + " WHERE book_id=" + book_id + ";");
                    }
                    else
                    {
                       dd.insert("INSERT INTO " + name + "_book(book_title,book_author,book_press,book_num) VALUES('" + title + "','" + author
                               + "','" + press + "',1);");
                    }
                    // 获取session对象
                    HttpSession session = request.getSession(true);
                    // 获取转发对象
                    rd = request.getRequestDispatcher("/main.jsp");
                    // 转发请求
                    rd.forward(request, response);
                }
                else
                {
                    dd.modify("DELETE FROM "+name+"_book WHERE book_id="+book_id+";");

                    // 获取session对象
                    HttpSession session = request.getSession(true);
                    // 获取转发对象
                    rd = request.getRequestDispatcher("/main.jsp");
                    // 转发请求
                    rd.forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
