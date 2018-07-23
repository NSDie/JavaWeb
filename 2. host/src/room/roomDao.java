package room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class roomDao {
    public List readFirstTitle(){
        List<room> list =new ArrayList<room>();
        Connection con=null;
        PreparedStatement psmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/host","root","111");
            String sql="select * from room_inf";
            psmt=con.prepareStatement(sql);
            rs=psmt.executeQuery();

            while(rs.next())
            {
                String room=rs.getString("room");
                int num=rs.getInt("number");
                int money=rs.getInt("money");
                int is=rs.getInt("IsNull");
                room r=new room(room, num, money,is);
                list.add(r);
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