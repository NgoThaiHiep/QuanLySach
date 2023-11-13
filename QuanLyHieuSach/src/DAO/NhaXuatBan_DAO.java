package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import ConnectDB.ConnectDB;
import Entity.CaLamViec;
import Entity.NhaXuatBan;

public class NhaXuatBan_DAO {
	public NhaXuatBan_DAO() {
		
	}
	public ArrayList<NhaXuatBan> layDanhSachNhaXuatBan(){
        ArrayList<NhaXuatBan> dsnxb = new ArrayList<NhaXuatBan>();
         ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
        PreparedStatement pst = null;
        try {
            String sql = "select * from NhaXuatBan";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String maNhaXuatBan =  rs.getString(1);
                String tenNhaXuatBan = rs.getString(2);
               String email = rs.getString(3);
               String sdt = rs.getString(4);
               String diaChi = rs.getString(5);
              
               NhaXuatBan nxb = new NhaXuatBan(maNhaXuatBan, tenNhaXuatBan, diaChi, sdt, email);
               dsnxb.add(nxb);
                }
            } catch (Exception e) {
        }
        return dsnxb;
    }
}
