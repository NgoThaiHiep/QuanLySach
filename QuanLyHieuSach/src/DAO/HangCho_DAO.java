
package DAO;

import ConnectDB.ConnectDB;
import Entity.HangCho;
import Entity.KhachHang;
import Entity.Sach;
import Entity.SanPham;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

/**
 *
 * @author Thái Hiệp
 */
public class HangCho_DAO {
    public HangCho_DAO(){
        
    }

    public ArrayList<HangCho> layDanhSachHangCho(){
        ArrayList<HangCho> dsHangCho = new ArrayList<HangCho>();
        
         ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "select * from HangCho";
                    
            Statement state = con.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                KhachHang khachHang = new KhachHang(rs.getString("KhachHangID"));
                SanPham sanPham = new SanPham(rs.getString("SanPhamID"));
                int soLuong = rs.getInt("SoLuong");
                Date sqlDate = rs.getDate("NgayMua");
                LocalDate localDate = sqlDate.toLocalDate();
                HangCho hangCho = new HangCho(khachHang, sanPham, soLuong,localDate );
                dsHangCho.add(hangCho);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return  dsHangCho;
    }
    public ArrayList<HangCho> layDanhSachHangChoTheoMaKhachHang(String maKhachHang){
        ArrayList<HangCho> dsHangCho = new ArrayList<HangCho>();
        
         ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
         PreparedStatement p = null;
           ResultSet rs = null;
        try {
            String sql = "select * from HangCho where KhachHangID =?" ;
            p = con.prepareStatement(sql);
            p.setString(1, maKhachHang);
            rs = p.executeQuery();
            while(rs.next()){
                KhachHang khachHang = new KhachHang(rs.getString("KhachHangID"));
                SanPham sanPham = new SanPham(rs.getString("SanPhamID"));
                int soLuong = rs.getInt("SoLuong");
                Date sqlDate = rs.getDate("NgayMua");
                LocalDate localDate = sqlDate.toLocalDate();
                HangCho hangCho = new HangCho(khachHang, sanPham, soLuong,localDate );
                dsHangCho.add(hangCho);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return  dsHangCho;
    }
    public boolean InsertHangCho(HangCho hangCho) {
		ConnectDB.getInstance();
    Connection con = ConnectDB.getConnection();
    PreparedStatement state = null;
    ResultSet rs = null;
    int n = 0;
    try {
        // Kiểm tra xem số điện thoại đã tồn tại trong cơ sở dữ liệu hay chưa
        String sql = "INSERT INTO [dbo].[HangCho] ([KhachHangID],[SanPhamID],[SoLuong],[NgayMua])VALUES(?,?,?,?)";
        state = con.prepareStatement(sql);
            // Số điện thoại không tồn tại, thực hiện thêm mới
            state.setString(1, hangCho.getKhachHang().getMaKhachHang());
            state.setString(2, hangCho.getSanPham().getMaSanPham());
            state.setInt(3, hangCho.getSoLuong());
            state.setString(4,hangCho.getNgayMua()+"");
            n = state.executeUpdate();
    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (state != null) {
                state.close();
            }
        } catch (SQLException e2) {
            // TODO: handle exception
            e2.printStackTrace();
        }
    }
    return n > 0;
	}
        
     public void DeleteDanhSachHangCho(String maKhachHang) {
			ConnectDB.getInstance();
			PreparedStatement pst = null;
			Connection con = ConnectDB.getConnection();
			String sql ="DELETE FROM [dbo].[HangCho]WHERE KhachHangID = ?";
			try {
				 pst = con.prepareStatement(sql);
				pst.setString(1, maKhachHang);
				 pst.executeUpdate() ;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				try {
					pst.close();
				} catch (SQLException e2) {
					// TODO: handle exceptione2 
					e2.printStackTrace();
				}
			}
		}

public void DeleteHangChoQuaNgay(){
         LocalDate localDate = LocalDate.now();
         ConnectDB.getInstance();
		PreparedStatement pst = null;
		 Connection con = ConnectDB.getConnection();
		
		 String sql ="DELETE FROM HangCho WHERE CONVERT(DATE, ngayMua) <> CONVERT(DATE, GETDATE())";
                  try {
			 pst = con.prepareStatement(sql);
			
			 pst.executeUpdate() ;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				pst.close();
			} catch (SQLException e2) {
				// TODO: handle exceptione2 
				e2.printStackTrace();
			}
		}
    }
}
