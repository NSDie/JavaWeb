
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;

@WebServlet(name="list",urlPatterns = {"/list"})
public class cal extends HttpServlet{
    // 响应客户端请求的方法
    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException,java.io.IOException{

        RequestDispatcher rd;
        // 获取请求参数
        request.setCharacterEncoding("utf8");
        String name = request.getParameter("name");
        String room =request.getParameter("room");
        String id = request.getParameter("id_card");
        String sprice=request.getParameter("price");
       /* String sdate =request.getParameter("date");
        System.out.println(sdate);*/
        int price=0;
        if(sprice!=null&&!sprice.equals(""))
        {
            price=Integer.parseInt(sprice);
        }
        try{
            // Servlet本身并不执行任何的业务逻辑处理，它调用JavaBean处理用户请求
            DbDao dd = new DbDao("com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/host","root","111");
            // 查询结果集
            ResultSet rs = dd.query("select * from room_inf"
                    + " where room = ?", room);
        if(rs.next()) {
            if (rs.getInt("IsNull") == 0) {
                if (price >= rs.getInt("money")) {
                    dd.modify("UPDATE room_inf SET username = '" + name + "' WHERE room = '" + room + "';");
                    dd.modify("UPDATE room_inf SET id_card = '" + id + "' WHERE room = '" + room + "';");
                    //dd.modify("UPDATE room_inf SET date = '"+sdate+"' WHERE room = '" + room + "';");
                    dd.modify("UPDATE room_inf SET IsNull = 1 WHERE room = '" + room + "';");
                    // 获取session对象
                    HttpSession session = request.getSession(true);
                    // 设置session属性，跟踪用户会话状态
                    session.setAttribute("message", "付款成功！");
                    // 获取转发对象
                    response.sendRedirect("count.jsp");
                } else {
                    // 获取session对象
                    HttpSession session = request.getSession(true);
                    // 设置session属性，跟踪用户会话状态
                    session.setAttribute("message", "价格不足！");
                    // 获取转发对象
                    response.sendRedirect("count.jsp");
                }
            }else{
                // 获取session对象
                HttpSession session = request.getSession(true);
                // 设置session属性，跟踪用户会话状态
                session.setAttribute("message", "已经有人入住!");
                // 获取转发对象
                response.sendRedirect("count.jsp");
            }
        }
        else
        {
            // 获取session对象
            HttpSession session = request.getSession(true);
            // 设置session属性，跟踪用户会话状态
            session.setAttribute("message" , "房间不存在!");
            // 获取转发对象
            response.sendRedirect("count.jsp");
        }
        }catch (Exception e){
          e.printStackTrace();
        }
    }
}
