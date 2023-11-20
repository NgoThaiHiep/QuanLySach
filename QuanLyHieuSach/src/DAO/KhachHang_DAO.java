
package DAO;
import ConnectDB.ConnectDB;
import Entity.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
    public KhachHang layThongTinKhachHang(String sdt){
        ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
        PreparedStatement pst = null;
        KhachHang kh;
        try{
            String sql = "select * from KhachHang where SoDienThoai = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1,sdt);    
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
    public ArrayList<KhachHang> layDanhSachTheoMaSach_TheoSoDienThoai(String sdt) throws SQLException{
        ArrayList<KhachHang> dsKhachHang = new ArrayList<KhachHang>();
        ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
         try{
            String sql = "Select * from KhachHang where  SoDienThoai LIKE '%"+sdt+"%'";
            Statement state = con.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                String maKH = rs.getString(1);
                String tenKH = rs.getString(2);
                String soDT = rs.getString(3);
                String diaChi = rs.getString(4);
                KhachHang kh = new KhachHang(maKH, tenKH, soDT, diaChi);
                dsKhachHang.add(kh);
            }
             }catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
         return dsKhachHang;
    }
     public KhachHang layThongTinKhachHang_TheoMa(String maKhachHang){
        ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
        PreparedStatement pst = null;
        KhachHang kh;
        try{
            String sql = "select * from KhachHang where KhachHangID = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1,maKhachHang);    
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
    
    public String generateVerifyCode_KhachHangLe() throws SQLException {
    DecimalFormat df = new DecimalFormat("0000000000");
    Random ran = new Random();

    // Lấy hai số cuối cùng của năm
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR) % 100; // Lấy 2 số cuối của năm
    // Lấy 2 số của tháng
    int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, cần cộng thêm 1
    String code;
    do {
        code = "KHL"+df.format(month * 100000000 + year * 1000000 + ran.nextInt(100000));
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
    ResultSet rs = null;
    int n = 0;
    try {
        // Kiểm tra xem số điện thoại đã tồn tại trong cơ sở dữ liệu hay chưa
        String checkPhoneNumberQuery = "SELECT COUNT(*) FROM [dbo].[KhachHang] WHERE [SoDienThoai] = ?";
        state = con.prepareStatement(checkPhoneNumberQuery);
        state.setString(1, kh.getSoDienThoai());
        rs = state.executeQuery();
        if (rs.next() && rs.getInt(1) > 0) {
            // Số điện thoại đã tồn tại, không thực hiện thêm mới và thông báo cho người dùng
            return n<0;
        } else {
            // Số điện thoại không tồn tại, thực hiện thêm mới
            String insertQuery = "INSERT INTO [dbo].[KhachHang]([KhachHangID],[TenKhachHang],[SoDienThoai],[DiaChi]) VALUES(?,?,?,?)";
            state = con.prepareStatement(insertQuery);
            state.setString(1, kh.getMaKhachHang());
            state.setString(2, kh.getTenKhachHang());
            state.setString(3, kh.getSoDienThoai());
            state.setString(4, kh.getDiaChi());
            n = state.executeUpdate();
        }
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
        
}
