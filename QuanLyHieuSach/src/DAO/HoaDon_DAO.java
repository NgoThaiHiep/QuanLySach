
package DAO;

import ConnectDB.ConnectDB;
import Entity.HangCho;
import Entity.HoaDon;
import Entity.KhachHang;
import Entity.NhanVien;
import Entity.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author FPTSHOP
 */
public class HoaDon_DAO {
        public HoaDon_DAO(){
        
    }
    
        public String generateHoaDon(NhanVien nv) throws SQLException{
            Date currentDate = new Date();
	SimpleDateFormat formatterDay = new SimpleDateFormat("ddMMyy");
	String formattedDate = "";
        
           
             
        for (int i = 1; i < 9999; i++) {
            // Định dạng số thành chuỗi và thêm các số 0 bổ sung để đảm bảo đủ 5 chữ số
            formattedDate =  formatterDay.format(currentDate) + nv.getMaNV().substring(2, 6)+  String.format("%04d", i);
            
            if(!kiemTraMaHoaDon(formattedDate)){
                break;
            }
            }  
        return formattedDate;
	}
    public boolean kiemTraMaHoaDon(String code) throws SQLException{
	boolean duplicate = false;

        // Thực hiện kết nối đến cơ sở dữ liệu ở đây
        ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;

        
            // Thực hiện truy vấn kiểm tra code
            p = con.prepareStatement("select HoaDonID from HoaDon where HoaDonID = ?");
            p.setString(1, code);
            rs = p.executeQuery();

            if (rs.next()) {
                duplicate = true;
            }
        return duplicate;
        }
    public boolean InsertHoaDon(HoaDon hd,NhanVien nv, KhachHang kh) {
		ConnectDB.getInstance();
    Connection con = ConnectDB.getConnection();
    PreparedStatement state = null;
    ResultSet rs = null;
    int n = 0;
     Date currentDate = new Date();
        SimpleDateFormat formatterDay = new SimpleDateFormat("yyyy-MM-dd");
	String formattedDate = formatterDay.format(currentDate);
    try {
       
        // Kiểm tra xem số điện thoại đã tồn tại trong cơ sở dữ liệu hay chưa
        String sql = "INSERT INTO [dbo].[HoaDon] ([HoaDonID],[NgayLapHD],[NhanVienID],[KhachHangID],[TongTien])\n" +
"     VALUES (?,?,?,?,?)";
        
        state = con.prepareStatement(sql);
        state.setString(1,  hd.getMaHoaDon());
        state.setString(2,formattedDate);
        state.setString(3, nv.getMaNV());
        state.setString(4,kh.getMaKhachHang());
        state.setDouble(5,hd.getTongTien());
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
    
     public HoaDon layHoaDon(String ma,String ngay){
         HoaDon hoaDon = new HoaDon();
         ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
         PreparedStatement p = null;
           
            try {
            String sql = "select * from HoaDon where HoaDonID = ? and NgayLapHD = ? ";
            p = con.prepareStatement(sql);
            p.setString(1, ma);
            p.setString(2, ngay);
            ResultSet rs = p.executeQuery();
            if(rs.next()){
                
                java.sql.Date sqlNgayLapHD = rs.getDate("NgayLapHD");
                LocalDate ngayLapHD = sqlNgayLapHD.toLocalDate();
                NhanVien nv = new NhanVien(rs.getString("NhanVienID"));
                KhachHang kh = new KhachHang(rs.getString("KhachHangID"));
                
                double thanhTien = rs.getDouble("TongTien");
                hoaDon = new HoaDon(ma, ngayLapHD, nv, kh,thanhTien);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return  hoaDon;
    }
}
