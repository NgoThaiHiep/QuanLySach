
package DAO;

import ConnectDB.ConnectDB;
import Entity.GiamGiaSanPham;
import Entity.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author FPTSHOP
 */
public class GiamGiaSanPham_DAO {
    public GiamGiaSanPham_DAO(){
        
    }
     
    public ArrayList<GiamGiaSanPham> layDanhSachGiamGiaSanPham (){
         ArrayList<GiamGiaSanPham> dsGiamGiaSanPham = new  ArrayList<GiamGiaSanPham> ();
           ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement pst = null;
        try {
            String sql = "select * from GiamGiaSanPham";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String maGiamGiaSanPham = rs.getString("GiamGiaSanPhamID");
                String tenGiamGia = rs.getString("TenGiamGia");
                SanPham sanPham = new SanPham(rs.getString("SanPhamID"));
                float soTienGiam = rs.getFloat("SoTienGiam");
                java.sql.Date sqlNgayBatDau = rs.getDate("NgayBatDau");
                LocalDate ngayBatDau = sqlNgayBatDau.toLocalDate();
                java.sql.Date sqlNgayKetThuc = rs.getDate("NgayKetThuc");
                LocalDate ngayKetThuc = sqlNgayKetThuc.toLocalDate();
                String tinhTrang = rs.getString("TinhTrang");
                String chiTiet = rs.getString("ChiTiet");
                int loai = rs.getInt("Loai");
                GiamGiaSanPham giamGiaSanPham = new GiamGiaSanPham(maGiamGiaSanPham, tenGiamGia, sanPham, soTienGiam, ngayBatDau, ngayKetThuc, tinhTrang, chiTiet,loai);
                dsGiamGiaSanPham.add(giamGiaSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsGiamGiaSanPham;
    }
    
    public ArrayList<GiamGiaSanPham> layDanhSachGiamGiaSanPham_GiaTien (int loai){
         ArrayList<GiamGiaSanPham> dsGiamGiaSanPham = new  ArrayList<GiamGiaSanPham> ();
           ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement pst = null;
        try {
            String sql = "select * from GiamGiaSanPham where Loai = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1,loai);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String maGiamGiaSanPham = rs.getString("GiamGiaSanPhamID");
                String tenGiamGia = rs.getString("TenGiamGia");
                SanPham sanPham = new SanPham(rs.getString("SanPhamID"));
                float soTienGiam = rs.getFloat("SoTienGiam");
                java.sql.Date sqlNgayBatDau = rs.getDate("NgayBatDau");
                LocalDate ngayBatDau = sqlNgayBatDau.toLocalDate();
                java.sql.Date sqlNgayKetThuc = rs.getDate("NgayKetThuc");
                LocalDate ngayKetThuc = sqlNgayKetThuc.toLocalDate();
                String tinhTrang = rs.getString("TinhTrang");
                String chiTiet = rs.getString("ChiTiet");
                
                GiamGiaSanPham giamGiaSanPham = new GiamGiaSanPham(maGiamGiaSanPham, tenGiamGia, sanPham, soTienGiam, ngayBatDau, ngayKetThuc, tinhTrang, chiTiet,loai);
                dsGiamGiaSanPham.add(giamGiaSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsGiamGiaSanPham;
    }
    
    public ArrayList<GiamGiaSanPham> layDanhSachGiamGiaSanPham_TyLe (){
         ArrayList<GiamGiaSanPham> dsGiamGiaSanPham = new  ArrayList<GiamGiaSanPham> ();
           ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement pst = null;
        try {
            String sql = "select * from GiamGiaSanPham where Loai = 2";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String maGiamGiaSanPham = rs.getString("GiamGiaSanPhamID");
                String tenGiamGia = rs.getString("TenGiamGia");
                SanPham sanPham = new SanPham(rs.getString("SanPhamID"));
                float tyLeGiam = rs.getFloat("TyLeGiam");
                java.sql.Date sqlNgayBatDau = rs.getDate("NgayBatDau");
                LocalDate ngayBatDau = sqlNgayBatDau.toLocalDate();
                java.sql.Date sqlNgayKetThuc = rs.getDate("NgayKetThuc");
                LocalDate ngayKetThuc = sqlNgayKetThuc.toLocalDate();
                String tinhTrang = rs.getString("TinhTrang");
                String chiTiet = rs.getString("ChiTiet");
                int loai = rs.getInt("Loai");
                GiamGiaSanPham giamGiaSanPham = new GiamGiaSanPham(maGiamGiaSanPham, tenGiamGia, sanPham, ngayBatDau, ngayKetThuc, tinhTrang, chiTiet, loai, tyLeGiam);
                dsGiamGiaSanPham.add(giamGiaSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsGiamGiaSanPham;
    }
    public String generateGiamGiaSanPham_TyLe() throws SQLException{
        java.util.Date currentDate = new java.util.Date();
	SimpleDateFormat formatterDay = new SimpleDateFormat("ddMMyy");
	String formattedDate = "";
        for (int i = 1; i < 9999; i++) {
            // Định dạng số thành chuỗi và thêm các số 0 bổ sung để đảm bảo đủ 5 chữ số
            formattedDate = "GGSPTL"+ formatterDay.format(currentDate)+ String.format("%04d", i);
            if(!kiemTraGiamGiaSanPham(formattedDate)){
                break;
            }
            }  
        return formattedDate;
	}
     
    public String generateGiamGiaSanPham_GiaTien() throws SQLException{
        java.util.Date currentDate = new java.util.Date();
	SimpleDateFormat formatterDay = new SimpleDateFormat("ddMMyy");
	String formattedDate = "";
        for (int i = 1; i < 9999; i++) {
            // Định dạng số thành chuỗi và thêm các số 0 bổ sung để đảm bảo đủ 5 chữ số
            formattedDate = "GGSPGT"+ formatterDay.format(currentDate)+ String.format("%04d", i);
            if(!kiemTraGiamGiaSanPham(formattedDate)){
                break;
            }
            }  
        return formattedDate;
	}
    public boolean kiemTraGiamGiaSanPham(String code) throws SQLException{
	boolean duplicate = false;

        // Thực hiện kết nối đến cơ sở dữ liệu ở đây
        ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;
            // Thực hiện truy vấn kiểm tra code
            p = con.prepareStatement("select GiamGiaSanPhamID  from GiamGiaSanPham where GiamGiaSanPhamID = ?");
            p.setString(1, code);
            rs = p.executeQuery();

            if (rs.next()) {
                duplicate = true;
            }
        return duplicate;
        }
    
    public boolean themGiamGiaSanPham_TyLe(GiamGiaSanPham giamGiaSanPham){
        ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement state = null;
		int n = 0;
		      try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String sql ="INSERT INTO [dbo].[GiamGiaSanPham]([GiamGiaSanPhamID] ,[TenGiamGia],[SanPhamID],[NgayBatDau],[NgayKetThuc],[TinhTrang],[ChiTiet],[Loai],[TyLeGiam])\n" +
                                    "VALUES(?,?,?,?,?,?,?,?,?)";
                        state = con.prepareStatement(sql);
                        state.setString(1, giamGiaSanPham.getMaGiamGiaSanPham());
                        state.setString(2, giamGiaSanPham.getTenGiamGia());
                        state.setString(3, giamGiaSanPham.getSanPham().getMaSanPham());
                        state.setString(4,formatter.format(giamGiaSanPham.getNgayBatDau()));
                        state.setString(5,formatter.format(giamGiaSanPham.getNgayKetThuc()));   
                        state.setString(6, giamGiaSanPham.getTinhTrang());
                        state.setString(7, giamGiaSanPham.getChiTiet());
                        state.setInt(8, giamGiaSanPham.getLoai());
                        state.setFloat(9, giamGiaSanPham.getTyLeGiam());
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
    public boolean themGiamGiaSanPham_GiaTien(GiamGiaSanPham giamGiaSanPham){
                ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement state = null;
		int n = 0;
		      try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String sql ="INSERT INTO [dbo].[GiamGiaSanPham]([GiamGiaSanPhamID] ,[TenGiamGia],[SanPhamID],[SoTienGiam],[NgayBatDau],[NgayKetThuc],[TinhTrang],[ChiTiet],[Loai])\n" +
                                    "     VALUES(?,?,?,?,?,?,?,?,?)";
                        state = con.prepareStatement(sql);
                        state.setString(1, giamGiaSanPham.getMaGiamGiaSanPham());
                        state.setString(2, giamGiaSanPham.getTenGiamGia());
                        state.setString(3, giamGiaSanPham.getSanPham().getMaSanPham());
                        state.setFloat(4, giamGiaSanPham.getSoTienGiam());
                        state.setString(5,formatter.format(giamGiaSanPham.getNgayBatDau()));
                        state.setString(6,formatter.format(giamGiaSanPham.getNgayKetThuc()));   
                        state.setString(7, giamGiaSanPham.getTinhTrang());
                        state.setString(8, giamGiaSanPham.getChiTiet());
                        state.setInt(9, giamGiaSanPham.getLoai());
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
    
    public boolean updateGiamGiaSanPham_GiaTien(GiamGiaSanPham giamGiaSanPham){
        ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
	PreparedStatement state = null;
        int n = 0;
        try {
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String sql = "UPDATE [dbo].[GiamGiaSanPham]\n" +
                        "SET [TenGiamGia] = ?,[SanPhamID] = ?,[SoTienGiam] = ?,[NgayBatDau] = ?,[NgayKetThuc] = ?,[TinhTrang] = ?,[ChiTiet] = ?\n" +
                        "WHERE [GiamGiaSanPhamID] = ?";
            state = con.prepareStatement(sql);
            state.setString(1, giamGiaSanPham.getTenGiamGia());
            state.setString(2, giamGiaSanPham.getSanPham().getMaSanPham());
            state.setFloat(3, giamGiaSanPham.getSoTienGiam());
            state.setString(4, formatter.format( giamGiaSanPham.getNgayBatDau()));
            state.setString(5, formatter.format( giamGiaSanPham.getNgayKetThuc()));
            state.setString(6, giamGiaSanPham.getTinhTrang());
            state.setString(7, giamGiaSanPham.getChiTiet());
            state.setString(8, giamGiaSanPham.getMaGiamGiaSanPham());
            n = state.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
			try {
				state.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
                return n > 0;
    }
    public boolean updateGiamGiaSanPham_TyLe(GiamGiaSanPham giamGiaSanPham){
        ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
	PreparedStatement state = null;
        int n = 0;
        try {
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String sql ="UPDATE [dbo].[GiamGiaSanPham]\n" +
                        "SET [TenGiamGia] = ?,[SanPhamID] = ?,[TyLeGiam]  = ?,[NgayBatDau] = ?,[NgayKetThuc] = ?,[TinhTrang] = ?,[ChiTiet] = ?\n" +
                        "WHERE [GiamGiaSanPhamID] = ?";
            state = con.prepareStatement(sql);
            state.setString(1, giamGiaSanPham.getTenGiamGia());
            state.setString(2, giamGiaSanPham.getSanPham().getMaSanPham());
            state.setFloat(3, giamGiaSanPham.getTyLeGiam());
            state.setString(4, formatter.format( giamGiaSanPham.getNgayBatDau()));
            state.setString(5, formatter.format( giamGiaSanPham.getNgayKetThuc()));
            state.setString(6, giamGiaSanPham.getTinhTrang());
            state.setString(7, giamGiaSanPham.getChiTiet());
            state.setString(8, giamGiaSanPham.getMaGiamGiaSanPham());
            n = state.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
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
