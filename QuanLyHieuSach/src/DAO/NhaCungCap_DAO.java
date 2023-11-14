package DAO;

import ConnectDB.ConnectDB;
import Entity.NhaCungCap;
import Entity.ThuongHieu;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class NhaCungCap_DAO {
	public NhaCungCap_DAO() {
		
	}
        public String generateNhaCungCap() throws SQLException{
            String number = "";
        int n = 1;
        do{
            
        number = "NCC"+ n;
            
        n++;
        }while(kiemTraMaNhaCungCap(number));
        return number;
    }
    public boolean kiemTraMaNhaCungCap(String code) throws SQLException{
	boolean duplicate = false;
        // Thực hiện kết nối đến cơ sở dữ liệu ở đây
        ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;

        
            // Thực hiện truy vấn kiểm tra code
            p = con.prepareStatement("select ThuongHieuID from ThuongHieu where ThuongHieuID = ?");
            p.setString(1, code);
            rs = p.executeQuery();
            if (rs.next()) {
                duplicate = true;
            }
        return duplicate;
    }
    public boolean InsertNhaCungCap(NhaCungCap nhaCungCap){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement state = null;
        int n = 0;
        try {
            String sql = "INSERT INTO [dbo].[NhaCungCap]\n" +
"           ([NhaCungCapID]\n" +
"           ,[TenNhaCungCap]\n" +
"           ,[SanPhamCungCap]\n" +
"           ,[SoDienThoai]\n" +
"           ,[Email]\n" +
"           ,[DiaChi])\n" +
"     VALUES\n" +
"           (?,?,?,?,?,?)";
            state = con.prepareStatement(sql);
            state.setString(1, nhaCungCap.getMaNCC());
            state.setString(2, nhaCungCap.getTenNCC());
            state.setString(3,nhaCungCap.getSanPhamCungCap());
            state.setString(4, nhaCungCap.getSoDienThoai());
            state.setString(5,nhaCungCap.getEmail());
            state.setString(6,nhaCungCap.getDiaChiNCC());
            n = state.executeUpdate();
        } catch (Exception e) {
        }
        return n > 0;
    }
    
	public ArrayList<NhaCungCap> layDanhSachNhaCungCap(){
            ArrayList<NhaCungCap> dsncc = new ArrayList<NhaCungCap> ();
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            PreparedStatement pst = null;
               try {
            String sql = "select * from NhaCungCap";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
                while(rs.next()){
                String maNhaCungCap  =  rs.getString(1);
                    String tenNhaCungCap = rs.getString(2);
                   String sanPhamCungCap = rs.getString(3);
                   String sdt = rs.getString(4);
                   String email = rs.getString(5);
                   String diaChi = rs.getString(6);

                 NhaCungCap ncc = new NhaCungCap(maNhaCungCap, tenNhaCungCap, sanPhamCungCap, diaChi, sdt, email);
                 dsncc.add(ncc);
                 }
             } catch (SQLException e) {
                 
            }
            return dsncc;
        }
}
