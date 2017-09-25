package user;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class userDao {
    public List readFirstTitle(){
        List<user> list =new ArrayList<user>();
        Connection con=null;
        PreparedStatement psmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","111");
            String sql="select * from user_inf";
            psmt=con.prepareStatement(sql);
            rs=psmt.executeQuery();

            while(rs.next())
            {
                int id=rs.getInt("id");
                String name=rs.getString("name");
                String pass=rs.getString("pass");

                user u=new user(id, name, pass);
                list.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally
        {
            try {
                if(rs!=null)
                {
                    rs.close();
                }
                if(psmt!=null)
                {
                    psmt.close();
                }
                if(con!=null)
                {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
