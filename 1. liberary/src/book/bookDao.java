package book;

import book.book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class bookDao {
        public List read () {
            List<book> list = new ArrayList<book>();
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
                String sql = "select * from root_book";
                psmt = con.prepareStatement(sql);
                rs = psmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("book_id");
                    String title = rs.getString("book_title");
                    String author = rs.getString("book_author");
                    String press = rs.getString("book_press");
                    int num = rs.getInt("book_num");
                    book b = new book(id, title, author, press, num);
                    list.add(b);
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
            return list;
        }

}
