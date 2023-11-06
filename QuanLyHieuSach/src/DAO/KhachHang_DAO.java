/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import ConnectDB.ConnectDB;
import Entity.KhachHang;
import Entity.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.sql.Statement;
/**
 *
 * @author hongh
 */
public class KhachHang_DAO {
    public KhachHang_DAO(){
        
    }
    public KhachHang layThongTinKhachHang(TaiKhoan  tk){
        ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
        PreparedStatement pst = null;
        KhachHang kh;
        try{
            String sql = "select * from KhachHang where TaiKhoan = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1,tk.getTenTK());    
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                String maKH = rs.getString(1);
                String tenKH = rs.getString(2);
                String soDT = rs.getString(3);
                String diaChi = rs.getString(4);
                kh = new KhachHang(maKH, tenKH, soDT, diaChi);
                return kh;
            }    
        }catch (Exception e){
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
        return null;
    }
    public ArrayList<KhachHang> layDanhSachKhachHang(){
        ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
	ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
        
        try {
        String sql = "select * from KhachHang";
        Statement state = con.createStatement();
	ResultSet rs = state.executeQuery(sql);
        while(rs.next()){
                String maKH = rs.getString(1);
                String tenKH = rs.getString(2);
                String soDT = rs.getString(3);
                String diaChi = rs.getString(4);
                KhachHang kh = new KhachHang(maKH, tenKH, soDT, diaChi);
                dskh.add(kh);
            }    
        } catch (SQLException e) {
           e.printStackTrace();
        }
        
        return dskh; 
    }
    
   public String generateVerifyCode() throws SQLException {
    DecimalFormat df = new DecimalFormat("0000000000");
    Random ran = new Random();

    // Lấy hai số cuối cùng của năm
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR) % 100; // Lấy 2 số cuối của năm
    // Lấy 2 số của tháng
    int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, cần cộng thêm 1
    String code;
    do {
        code = df.format(month * 100000000 + year * 1000000 + ran.nextInt(100000));
    } while (checkDuplicateCode(code));
    return code;
}
    public boolean checkDuplicateCode(String code) throws SQLException{
	boolean duplicate = false;

        // Thực hiện kết nối đến cơ sở dữ liệu ở đây
        ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;

        
            // Thực hiện truy vấn kiểm tra code
            p = con.prepareStatement("select KhachHangID from KhachHang where KhachHangID = ?");
            p.setString(1, code);
            rs = p.executeQuery();

            if (rs.next()) {
                duplicate = true;
            }
        return duplicate;
        }
	public boolean InsertKhachHang(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement state = null;
		int n = 0;
		try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        
			state = con.prepareStatement("INSERT INTO [dbo].[KhachHang]([KhachHangID],[TenKhachHang],[SoDienThoai],[DiaChi])VALUES(?,?,?,?)");
			state.setString(1, kh.getMaKhachHang());
			state.setString(2, kh.getTenKhachHang());
			state.setString(3, kh.getSoDienThoai());
			state.setString(4, kh.getDiaChi());
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
