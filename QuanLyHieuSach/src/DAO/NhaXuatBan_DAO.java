package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import ConnectDB.ConnectDB;
import Entity.NhaCungCap;
import Entity.NhaXuatBan;
import java.sql.SQLException;

public class NhaXuatBan_DAO {
	public NhaXuatBan_DAO() {
		
	}
         public String generateNhaXuatBan() throws SQLException{
            String number = "";
        int n = 1;
        do{
            
        number = "NXB"+ n;
            
        n++;
        }while(kiemTraNhaXuatBan(number));
        return number;
    }
    public boolean kiemTraNhaXuatBan(String code) throws SQLException{
	boolean duplicate = false;
        // Thực hiện kết nối đến cơ sở dữ liệu ở đây
        ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;

        
            // Thực hiện truy vấn kiểm tra code
            p = con.prepareStatement("select NhaXuatBan from NhaXuatBan where NhaXuatBan = ?");
            p.setString(1, code);
            rs = p.executeQuery();
            if (rs.next()) {
                duplicate = true;
            }
        return duplicate;
    }
    public boolean InsertNhaXuatBan(NhaXuatBan nhaXuatBan){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement state = null;
        int n = 0;
        try {
            String sql = "INSERT INTO [dbo].[NhaXuatBan]\n" +
"           ([NhaXuatBan]\n" +
"           ,[TenNhaXuatBan]\n" +
"           ,[Email]\n" +
"           ,[SoDienThoai]\n" +
"           ,[DiaChi])\n" +
"     VALUES\n" +
"           (?,?,?,?,?)";
            state = con.prepareStatement(sql);
            state.setString(1, nhaXuatBan.getMaNhaXuatBan());
            state.setString(2, nhaXuatBan.getTenNhaXuatBanl());
            state.setString(3, nhaXuatBan.getSoDienThoai());
            state.setString(4,nhaXuatBan.getEmail());
            state.setString(5,nhaXuatBan.getDiaChi());
            n = state.executeUpdate();
        } catch (Exception e) {
        }
        return n > 0;
    }
	public ArrayList<NhaXuatBan> layDanhSachNhaXuatBan(){
        ArrayList<NhaXuatBan> dsnxb = new ArrayList<NhaXuatBan>();
         ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
        PreparedStatement pst = null;
        try {
            String sql = "select * from NhaXuatBan";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String maNhaXuatBan =  rs.getString(1);
                String tenNhaXuatBan = rs.getString(2);
               String email = rs.getString(3);
               String sdt = rs.getString(4);
               String diaChi = rs.getString(5);
              
               NhaXuatBan nxb = new NhaXuatBan(maNhaXuatBan, tenNhaXuatBan, diaChi, sdt, email);
               dsnxb.add(nxb);
                }
            } catch (Exception e) {
        }
        return dsnxb;
    }
}
