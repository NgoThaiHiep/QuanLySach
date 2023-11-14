package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ConnectDB.ConnectDB;
import Entity.TacGia;
import java.sql.SQLException;

public class TacGia_DAO {

public TacGia_DAO() {
		
	}

    public String generateTacGia() throws SQLException{
            String number = "";
        int n = 1;
        do{
            
        number = "TG"+ n;
            
        n++;
        }while(kiemTraMaSach(number));
        return number;
	}
    public boolean kiemTraMaSach(String code) throws SQLException{
	boolean duplicate = false;

        // Thực hiện kết nối đến cơ sở dữ liệu ở đây
        ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;

        
            // Thực hiện truy vấn kiểm tra code
            p = con.prepareStatement("select TacGiaID from TacGia where TacGiaID = ?");
            p.setString(1, code);
            rs = p.executeQuery();

            if (rs.next()) {
                duplicate = true;
            }
        return duplicate;
        }
    
	public ArrayList<TacGia> layDanhSachTacGia(){
	ArrayList<TacGia> dstg = new ArrayList<TacGia>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement pst = null;
        try {
            String sql = "select * from TacGia";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String maTacGia=  rs.getString(1);
                String tenTacGia = rs.getString(2);
               
              
                TacGia  tg = new TacGia(maTacGia, tenTacGia);
                dstg.add(tg);
                }
            } catch (Exception e) {
        }
        return dstg;
    }
        
    public boolean InsertTacGia(TacGia tacGia){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement state = null;
        int n = 0;
        try {
            String sql = "INSERT INTO [dbo].[TacGia]([TacGiaID],[TenTacGia])VALUES(?,?)";
            state = con.prepareStatement(sql);
            state.setString(1, tacGia.getMaTacGia());
            state.setString(2, tacGia.getTenTacGia());
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

