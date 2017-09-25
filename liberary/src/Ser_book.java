import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;

@WebServlet(name="main5",urlPatterns = "/main5")

public class Ser_book extends HttpServlet {
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
            ResultSet rs2 = dd.query("select * from root_book"
                    + " where book_id = ?", book_id);
            if (rs2.next()) {
                String book_title = rs2.getString("book_title");
                ResultSet rs = dd.query("select * from " + name + "_book"
                        + " where book_title = ?", book_title);
                if (rs2.getInt("book_num") > 0) {
                    if (rs.next()) {
                        num = rs.getInt("book_num");
                        num++;
                        dd.modify("UPDATE " + name + "_book SET book_num=" + num + " WHERE book_title='" + book_title + "';");
                        num = rs2.getInt("book_num");
                        num--;
                        dd.modify("UPDATE root_book SET book_num=" + num + " WHERE book_title='" + book_title + "';");
                        HttpSession session = request.getSession(true);
                        // 获取转发对象
                        rd = request.getRequestDispatcher("/main.jsp");
                        // 转发请求
                        rd.forward(request, response);
                    } else {
                        dd.insert("INSERT INTO " + name + "_book(book_title,book_author,book_press,book_num) VALUES('" + book_title + "','" + rs2.getString("book_author")
                                + "','" + rs2.getString("book_press") + "',1);");
                        HttpSession session = request.getSession(true);
                        // 获取转发对象
                        rd = request.getRequestDispatcher("/main.jsp");
                        // 转发请求
                        rd.forward(request, response);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

