
package DAO;

import ConnectDB.ConnectDB;
import Entity.KhuyenMaiThanhToan;
import Entity.NhanVien;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author FPTSHOP
 */
public class KhuyenMaiThanhToan_DAO {
    public KhuyenMaiThanhToan_DAO(){
        
    }
    
    public ArrayList<KhuyenMaiThanhToan> layDanhSachKhuyenMai_TyLe(){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement pst = null;
        ArrayList<KhuyenMaiThanhToan> dsKhuyenMai = new ArrayList<KhuyenMaiThanhToan> ();
        
        try {
        String sql = "select * from KhuyenMaiThanhToan where loai = 1";    
        pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String maKhuyenMai = rs.getString("MaKhuyenMai");
                String tenKhuyenMai = rs.getString("TenKhuyenMai");
                float phanTramGiam = rs.getFloat("PhanTramGiam");
                float giaTriDonHangToiThieu = rs.getFloat("GiaTriToiThieuDonHang");
                float giamToiDa = rs.getFloat("GiamToiDa");
                Date sqlNgayBatDau = rs.getDate("NgayBatDau");
                LocalDate ngayBatDau = sqlNgayBatDau.toLocalDate();
                Date sqlNgayKetThuc = rs.getDate("NgayKetThuc");
                LocalDate ngayKetThuc = sqlNgayKetThuc.toLocalDate();
                String tinhTrang = rs.getString("TinhTrang");
                int loai = rs.getInt("Loai");
                String chiTiet = rs.getString("ChiTiet");
                int soLuong = rs.getInt("SoLuong");
                KhuyenMaiThanhToan khuyenMaiThanhToan = new KhuyenMaiThanhToan(maKhuyenMai, tenKhuyenMai, phanTramGiam, giaTriDonHangToiThieu, giamToiDa, ngayBatDau, ngayKetThuc, tinhTrang, loai,chiTiet,soLuong);
                dsKhuyenMai.add(khuyenMaiThanhToan);
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
        return dsKhuyenMai;
    }
    
    
      public String generateMaKhuyenMai_GiaTri() throws SQLException{
        java.util.Date currentDate = new java.util.Date();
	SimpleDateFormat formatterDay = new SimpleDateFormat("ddMMyy");
	String formattedDate = "";
        for (int i = 1; i < 99; i++) {
            // Định dạng số thành chuỗi và thêm các số 0 bổ sung để đảm bảo đủ 5 chữ số
            formattedDate = "KMGT"+ formatterDay.format(currentDate)+ String.format("%02d", i);
            if(!kiemTraMaKhuyenMai_GiaTri(formattedDate)){
                break;
            }
            }  
        return formattedDate;
	}
       public String generateMaKhuyenMai_TyLe() throws SQLException{
        java.util.Date currentDate = new java.util.Date();
	SimpleDateFormat formatterDay = new SimpleDateFormat("ddMMyy");
	String formattedDate = "";
        for (int i = 1; i < 99; i++) {
            // Định dạng số thành chuỗi và thêm các số 0 bổ sung để đảm bảo đủ 5 chữ số
            formattedDate = "KMTL"+ formatterDay.format(currentDate)+ String.format("%02d", i);
            if(!kiemTraMaKhuyenMai_GiaTri(formattedDate)){
                break;
            }
            }  
        return formattedDate;
	}
    public boolean kiemTraMaKhuyenMai_GiaTri(String code) throws SQLException{
	boolean duplicate = false;

        // Thực hiện kết nối đến cơ sở dữ liệu ở đây
        ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;

        
            // Thực hiện truy vấn kiểm tra code
            p = con.prepareStatement("select MaKhuyenMai from KhuyenMaiThanhToan where MaKhuyenMai = ?");
            p.setString(1, code);
            rs = p.executeQuery();

            if (rs.next()) {
                duplicate = true;
            }
        return duplicate;
        }
    
        public ArrayList<KhuyenMaiThanhToan> layDanhSachKhuyenMai_GiaTien(){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement pst = null;
        ArrayList<KhuyenMaiThanhToan> dsKhuyenMai = new ArrayList<KhuyenMaiThanhToan> ();
        try {
        String sql = "select * from KhuyenMaiThanhToan where loai = 2";    
        pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String maKhuyenMai = rs.getString("MaKhuyenMai");
                String tenKhuyenMai = rs.getString("TenKhuyenMai");
               
                float giaTriDonHangToiThieu = rs.getFloat("GiaTriToiThieuDonHang");
                float soTienGiam = rs.getFloat("SoTienGiam");
                
                Date sqlNgayBatDau = rs.getDate("NgayBatDau");
                LocalDate ngayBatDau = sqlNgayBatDau.toLocalDate();
                Date sqlNgayKetThuc = rs.getDate("NgayKetThuc");
                LocalDate ngayKetThuc = sqlNgayKetThuc.toLocalDate();
                String tinhTrang = rs.getString("TinhTrang");
                int loai = rs.getInt("Loai");
                String chiTiet = rs.getString("ChiTiet");
                int soLuong = rs.getInt("SoLuong");
                KhuyenMaiThanhToan khuyenMaiThanhToan = new KhuyenMaiThanhToan(maKhuyenMai, tenKhuyenMai, giaTriDonHangToiThieu, soTienGiam, ngayBatDau, ngayKetThuc, tinhTrang, loai, chiTiet, soLuong);
                dsKhuyenMai.add(khuyenMaiThanhToan);
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
        return dsKhuyenMai;
    }
}

