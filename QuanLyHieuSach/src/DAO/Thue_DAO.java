
package DAO;

import ConnectDB.ConnectDB;
import Entity.QuyDinh;
import Entity.Thue;
import Entity.Sach;
import Entity.TheLoai;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Thue_DAO {
    public Thue_DAO(){
        
    }
    public ArrayList<Thue> layDuLieuThue(){
            ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        ArrayList<Thue> dsthue = new ArrayList<Thue>();
        try {
            String sql = "Select * from Thue";
            Statement state = con.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Thue thue = new Thue(rs.getString(1),rs.getFloat(2));
                dsthue.add(thue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsthue;
    }
    public boolean updateThue(Thue thue){
        ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
	PreparedStatement state = null;
        int n = 0;
        try {
            String sql = "UPDATE [dbo].[Thue]SET [MucThue] = ? WHERE [ThueID] = ?";
            state = con.prepareStatement(sql);
            state.setFloat(1, thue.getThue());
            state.setString(2, thue.getId());
            n = state.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
			try {
				state.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
                return n > 0;
    }
    public boolean insertThue(Thue thue){
          ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement state = null;
        int n = 0;
        try {
            String sql = "INSERT INTO [dbo].[Thue]([ThueID],[MucThue])VALUES(?,?)";
            state = con.prepareStatement(sql);
             state.setString(1, thue.getId());
            state.setFloat(2, thue.getThue());
            n = state.executeUpdate();
        } catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				state.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
        return n > 0;
    }
}
