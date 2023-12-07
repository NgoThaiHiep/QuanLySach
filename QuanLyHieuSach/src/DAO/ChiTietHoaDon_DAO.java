
package DAO;

import ConnectDB.ConnectDB;
import Entity.HoaDon;
import Entity.KhachHang;
import Entity.NhanVien;
import Entity.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
}
