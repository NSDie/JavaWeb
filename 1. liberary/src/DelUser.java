
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;

@WebServlet(name="main2",urlPatterns = "/main2")

public class DelUser extends HttpServlet {
    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, java.io.IOException {
        RequestDispatcher rd;

        try {
            request.setCharacterEncoding("utf-8");
            String user_name = request.getParameter("name");
            DbDao dd = new DbDao("com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/test?characterEncoding=utf8", "root", "111");
            ResultSet rs = dd.query("select * from user_inf"
                    + " where name = ?", user_name);
            if(user_name!=null)
            {
                if(rs.next())
                {
                    dd.insert("DELETE FROM user_inf WHERE name='"+user_name+"';");
                    dd.insert("DROP TABLE "+user_name+"_book;");
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