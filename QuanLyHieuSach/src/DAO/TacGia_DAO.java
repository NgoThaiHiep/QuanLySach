package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ConnectDB.ConnectDB;
import Entity.TacGia;
import Entity.TheLoai;

public class TacGia_DAO {

public TacGia_DAO() {
		
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
}
