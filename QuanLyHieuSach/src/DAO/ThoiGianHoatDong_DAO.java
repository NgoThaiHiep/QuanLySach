
package DAO;

import ConnectDB.ConnectDB;
import Entity.ThoiGianHoatDong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author FPTSHOP
 */
public class ThoiGianHoatDong_DAO {
        public ThoiGianHoatDong_DAO(){
            
        }
        
        public boolean kiemTraDangNhapTrongNgay(ThoiGianHoatDong tghd,String thoiGianBatDauCa, String thoiGianKetThucCa){
                ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pst = null;
		try {
                      // Thực hiện truy vấn SQL để lưu trữ giá trị vào cơ sở dữ liệu
                pst = con.prepareStatement("select ThoiGianDangNhap,ThoiGianDaLam,ThoiGianHoatDongID  from ThoiGianHoatDong where NhanVienID = ? and NgayDangNhap = ? and ThoiGianDangNhap >= ? and ThoiGianDangNhap <= ?");
                pst.setString(1,tghd.getNhanVien().getMaNV());
                LocalDate localDate = LocalDate.now();
                pst.setString(1, tghd.getNhanVien().getMaNV());
                pst.setDate(2, Date.valueOf(localDate));
                pst.setString(3,thoiGianBatDauCa);
                pst.setString(4,thoiGianKetThucCa);
                ResultSet rs = pst.executeQuery();  
                if(rs.next()){
                    
                   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                   String thoiGianDangNhap = rs.getString("ThoiGianDangNhap").substring(0, 8);
                   String thoiGianDaLam= rs.getString("ThoiGianDaLam").substring(0, 8);
                   tghd.setThoiGianDangNhap(LocalTime.parse(thoiGianDangNhap, formatter));
                   tghd.setThoiGianDaLam(LocalTime.parse(thoiGianDaLam, formatter));
                   tghd.setMaThoiGian(rs.getString("ThoiGianHoatDongID"));
                    return true;
                }
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
                        
		}
		finally {
			try {
				pst.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
               return false;
        }
        
        public boolean capNhatThoiGianLam(ThoiGianHoatDong tghd){
                ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pst = null;
                int n = 0;
                try {
                pst = con.prepareStatement("UPDATE [dbo].[ThoiGianHoatDong] SET [ThoiGianDaLam] = ?,[ThoiGianDangXuat] = ? WHERE [ThoiGianHoatDongID] = ?");
                pst.setString(1, tghd.getThoiGianDaLam()+"");
                pst.setString(2, tghd.getThoiGianDangXuat()+"");
                pst.setString(3, tghd.getMaThoiGian());
                n = pst.executeUpdate();
                } catch (SQLException e) {
            }finally {
			try {
				pst.close();
			} catch (SQLException e2) {
                            // TODO: handle exception
			}
		}
                return n > 0;
        }
    public boolean insertThoiGianLam(ThoiGianHoatDong tghd){
         ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pst = null;
                int n = 0;
                try {
                pst = con.prepareStatement("INSERT INTO [dbo].[ThoiGianHoatDong]([ThoiGianHoatDongID] ,[NhanVienID],[NgayDangNhap],[ThoiGianDangNhap] ) VALUES(?,?,?,?)");
                pst.setString(1, tghd.getMaThoiGian());
                pst.setString(2, tghd.getNhanVien().getMaNV());
                pst.setString(3, tghd.getNgayDangNhap()+"");
                pst.setString(4, tghd.getThoiGianDangNhap()+"");
                n = pst.executeUpdate();
                } catch (SQLException e) {
            }finally {
			try {
				pst.close();
			} catch (SQLException e2) {
                            // TODO: handle exception
			}
		}
                return n > 0;
    }
}
