
package DAO;

import ConnectDB.ConnectDB;
import Entity.CaLamViec;
import Entity.ChucVu;
import Entity.NhanVien;
import Entity.Sach;
import Entity.TaiKhoan;
import Entity.TheLoai;
import Entity.NhaCungCap;
import Entity.NhaXuatBan;
import Entity.LoaiSanPham;
import Entity.SanPham;
import Entity.TacGia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

/**
 *
 * @author Thái Hiệp
 */
public class Sach_DAO {
    public Sach_DAO(){
        
    }
    
        public String generateMaSach() throws SQLException{
            String number = "";
             
        for (int i = 1; i < 99999; i++) {
            // Định dạng số thành chuỗi và thêm các số 0 bổ sung để đảm bảo đủ 5 chữ số
            number = "Sach"+String.format("%05d", i);
            
            if(!kiemTraMaSach(number)){
                break;
            }
            }  
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
            p = con.prepareStatement("select SachID from Sach where SachID = ?");
            p.setString(1, code);
            rs = p.executeQuery();

            if (rs.next()) {
                duplicate = true;
            }
        return duplicate;
        }
    public ArrayList<Sach> layDanhSanPhamSach(){
        ArrayList<Sach> dssps = new ArrayList<Sach>();
	ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
        
        try {
        String sql = "select * from Sach";
        Statement state = con.createStatement();
        ResultSet rs = state.executeQuery(sql);
        while(rs.next()){
        	
                String maSanPham = rs.getString(1);
                String tenSanPham = rs.getString(2);
                String tacGia = rs.getString(3);
                TacGia tacGias = new TacGia(tacGia);
                String soLuongTon = rs.getString(10);
                int soTrang =rs.getInt("SoTrang");
                Double donGia = rs.getDouble("DonGia");
                TheLoai tl = new TheLoai(rs.getString("TheLoai"));
                int namXuatban = rs.getInt("NamXuatBan");
                String tinhTrang = rs.getString("TinhTrang");
                NhaXuatBan nhaXuatBan = new NhaXuatBan(rs.getString("NhaXuatBan"));
                NhaCungCap nhaCungCap = new NhaCungCap(rs.getString("NhaCungCap"));
                 //Sach sach = new Sach();
                 String hinhAnh = rs.getString("HinhAnh");
                LoaiSanPham loaiSanPham = new LoaiSanPham(rs.getString("LoaiSanPham"));
                Sach sach = new Sach(tacGias, namXuatban , soTrang, tl, nhaXuatBan, maSanPham, tenSanPham,loaiSanPham, nhaCungCap, Integer.parseInt(soLuongTon),donGia, null, tinhTrang, hinhAnh );  
                dssps.add(sach);
                
            }    
        } catch (SQLException e) {
           e.printStackTrace();
        }
        
        return dssps; 
    }
    public boolean InsertSach(Sach sach){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement state = null;
        int n = 0;
        try {
            String sql = "INSERT INTO Sach (SachID, TenSach, TacGia, NhaXuatBan, NamXuatBan, SoTrang, TheLoai, "
                    + "LoaiSanPham, NhaCungCap, SoLuongTon, DonGia, MoTa, TinhTrang, HinhAnh)"
                    +"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            state = con.prepareStatement(sql);
            state.setString(1, sach.getMaSanPham());
            state.setString(2, sach.getTenSanPham());
            state.setString(3, sach.getTacGia().getMaTacGia());
            state.setString(4, sach.getNhaXuatBan().getMaNhaXuatBan());
            state.setInt(5, sach.getNamXuatBan());
            state.setInt(6, sach.getSoTrang());
            state.setString(7, sach.getTheLoai().getMaTheLoai());
            state.setString(8, sach.getLoaiSanPham().getMaLoaiSanPham());
            state.setString(9, sach.getNhaCungCap().getMaNCC());
            state.setInt(10, sach.getSoLuongTon());
            state.setDouble(11, sach.getDonGia());
            state.setString(12, sach.getMoTa());
            state.setString(13, sach.getTinhTrang());
            state.setString(14,sach.getHinhAnh());
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
        return n > 0;
    }
    public boolean updateTinhTrang(String maSach, String tinhTrang){
       ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement state = null;
                int n = 0;
              try {
            String sql = "UPDATE [dbo].[Sach]\n" +
                            "   SET\n" 
                                + " TinhTrang= ?"+
                            "	WHERE [SachID] = ?";
            state = con.prepareStatement(sql);
            state.setString(1, tinhTrang);
            state.setString(2, maSach);
            n = state.executeUpdate();
        } catch (Exception e) {
        } finally {
                  try {
				state.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
        }
              return n > 0;
    }
    public boolean updateSach(Sach sach){
       ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement state = null;
                int n = 0;
                try {
                String sql = "UPDATE [dbo].[Sach]\n" +
                            "   SET\n" +
                            "   TenSach = ?, TacGia = ? , NhaXuatBan = ?, NamXuatBan= ?, SoTrang= ?, TheLoai= ?, "
                    + "LoaiSanPham= ?, NhaCungCap= ?, SoLuongTon= ?, DonGia= ?, MoTa= ?, TinhTrang= ?, HinhAnh= ?"+
                            "	WHERE [SachID] = ?";
                state = con.prepareStatement(sql);
                state.setString(1, sach.getTenSanPham());
                state.setString(2, sach.getTacGia().getMaTacGia());
                state.setString(3, sach.getNhaXuatBan().getMaNhaXuatBan());
                state.setInt(4, sach.getNamXuatBan());
                state.setInt(5, sach.getSoTrang());
                state.setString(6, sach.getTheLoai().getMaTheLoai());
                state.setString(7, sach.getLoaiSanPham().getMaLoaiSanPham());
                state.setString(8, sach.getNhaCungCap().getMaNCC());
                state.setInt(9, sach.getSoLuongTon());
                state.setDouble(10, sach.getDonGia());
                state.setString(11, sach.getMoTa());
                state.setString(12, sach.getTinhTrang());
                state.setString(13,sach.getHinhAnh());
                state.setString(14, sach.getMaSanPham());
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
                return n > 0;
    }
    public boolean updateSoLuongSach(SanPham sanPham, int soLuong){
        ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
	PreparedStatement state = null;
        int n = 0;
        try {
            String sql = "UPDATE [dbo].[Sach]SET [SoLuongTon] =  [SoLuongTon]  - ? WHERE [SachID] = ? ";
           state = con.prepareStatement(sql);
           state.setInt(1,soLuong);
           state.setString(2,sanPham.getMaSanPham());
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
                return n > 0;
    }
}
