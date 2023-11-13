package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ConnectDB.ConnectDB;
import Entity.TheLoai;

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
}
