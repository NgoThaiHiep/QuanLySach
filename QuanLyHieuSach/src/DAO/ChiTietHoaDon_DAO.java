
package DAO;

import ConnectDB.ConnectDB;
import Entity.ChiTietHoaDon;
import Entity.HoaDon;
import Entity.KhachHang;
import Entity.NhanVien;
import Entity.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author FPTSHOP
 */
public class ChiTietHoaDon_DAO {
    public ChiTietHoaDon_DAO(){
        
    }
    public boolean InsertCTHoaDon(HoaDon hd,int soLuong, double giaBan,SanPham sanPham) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement state = null;
		int n = 0;
		try { 
			state = con.prepareStatement("INSERT INTO [dbo].[ChiTietHoaDon]([HoaDonID],[SanPhamID],[SoLuong],[DonGia])VALUES(?,?,?,?)");
			state.setString(1,hd.getMaHoaDon());
                        state.setString(2, sanPham.getMaSanPham());
                        state.setInt(3, soLuong);
                        state.setDouble(4, giaBan);
                        
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
		return n>0;
	}
    public ArrayList<ChiTietHoaDon> layDanhSachGiamGiaSanPham_GiaTien (String ma){
        ArrayList<ChiTietHoaDon> dsGiamGiaSanPham = new  ArrayList<ChiTietHoaDon> ();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement pst = null;
        try {
            String sql = "select * from ChiTietHoaDon where  HoaDonID = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1,ma);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(new HoaDon(ma),new SanPham(rs.getString("SanPhamID")),rs.getInt("SoLuong"), rs.getFloat("DonGia"));
                dsGiamGiaSanPham.add(chiTietHoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsGiamGiaSanPham;
    }
    
}
