
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
              String soLuongTon = rs.getString(10);
              int soTrang =rs.getInt("SoTrang");
              Double donGia = rs.getDouble("DonGia");
              TheLoai tl = new TheLoai(rs.getString("TheLoai"));
              int namXuatban = rs.getInt("NamXuatBan");
              String tinhTrang = rs.getString("TinhTrang");
              NhaXuatBan nhaXuatBan = new NhaXuatBan(rs.getString("NhaXuatBan"));
              NhaCungCap nhaCungCap = new NhaCungCap(rs.getString("NhaCungCap"));
               //Sach sach = new Sach();
               LoaiSanPham loaiSanPham = new LoaiSanPham(rs.getString("LoaiSanPham"));
               loaiSanPham.setMaLoaiSanPham("");
               Sach sach = new Sach(tacGia, namXuatban , soTrang, tl, nhaXuatBan, maSanPham, tenSanPham,loaiSanPham, nhaCungCap, Integer.parseInt(soLuongTon),donGia, null, tinhTrang, tinhTrang);  
             dssps.add(sach);
                
            }    
        } catch (SQLException e) {
           e.printStackTrace();
        }
        
        return dssps; 
    }
}
