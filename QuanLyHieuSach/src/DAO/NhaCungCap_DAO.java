package DAO;

import ConnectDB.ConnectDB;
import Entity.NhaCungCap;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class NhaCungCap_DAO {
	public NhaCungCap_DAO() {
		
	}
	public ArrayList<NhaCungCap> layDanhSachNhaCungCap(){
            ArrayList<NhaCungCap> dsncc = new ArrayList<NhaCungCap> ();
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            PreparedStatement pst = null;
               try {
            String sql = "select * from NhaCungCap";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
                while(rs.next()){
                String maNhaCungCap  =  rs.getString(1);
                    String tenNhaCungCap = rs.getString(2);
                   String sanPhamCungCap = rs.getString(3);
                   String sdt = rs.getString(4);
                   String email = rs.getString(5);
                   String diaChi = rs.getString(6);

                 NhaCungCap ncc = new NhaCungCap(maNhaCungCap, tenNhaCungCap, sanPhamCungCap, diaChi, sdt, email);
                 dsncc.add(ncc);
                 }
             } catch (SQLException e) {
                 
            }
            return dsncc;
        }
}
