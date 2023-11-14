package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ConnectDB.ConnectDB;
import Entity.TacGia;
import Entity.TheLoai;
import java.sql.SQLException;

public class TheLoai_DAO {
	public TheLoai_DAO() {
		
	}
	public ArrayList<TheLoai> layDanhSachTheLoai(){
        ArrayList<TheLoai> dstl = new ArrayList<TheLoai>();
         ConnectDB.getInstance();
         Connection con = ConnectDB.getConnection();
        PreparedStatement pst = null;
        try {
            String sql = "select * from TheLoai";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String maTheLoai =  rs.getString(1);
                String tenTheloai = rs.getString(2);
               
              
             TheLoai tl = new TheLoai(maTheLoai, tenTheloai);
             dstl.add(tl);
                }
            } catch (Exception e) {
        }
        return dstl;
    }
        
    public String generateTheLoai() throws SQLException{
        String number = "";
        int n = 1;
        do{
        number = "TL"+ n;
        n++;
        }while(kiemTraTheLoai(number));
        return number;
	}
    public boolean kiemTraTheLoai(String code) throws SQLException{
	boolean duplicate = false;
        // Thực hiện kết nối đến cơ sở dữ liệu ở đây
        ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;

        
            // Thực hiện truy vấn kiểm tra code
            p = con.prepareStatement("select TheLoaiID from TheLoai where TheLoaiID = ?");
            p.setString(1, code);
            rs = p.executeQuery();

            if (rs.next()) {
                duplicate = true;
            }
        return duplicate;
        }
    
    
     public boolean InsertTacGia(TheLoai theLoai){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement state = null;
        int n = 0;
        try {
            String sql = "INSERT INTO [dbo].[TheLoai]([TheLoaiID],[TenTheLoai])VALUES(?,?)";
            state = con.prepareStatement(sql);
            state.setString(1, theLoai.getMaTheLoai());
            state.setString(2, theLoai.getTenTheLoai());
            n = state.executeUpdate();
        }  catch (Exception e) {
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
