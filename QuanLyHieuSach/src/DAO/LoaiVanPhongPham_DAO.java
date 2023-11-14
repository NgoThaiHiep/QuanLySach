
package DAO;

import ConnectDB.ConnectDB;
import Entity.LoaiVanPhongPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
/**
 *
 * @author FPTSHOP
 */
public class LoaiVanPhongPham_DAO {
    public LoaiVanPhongPham_DAO(){
        
    }
     public String generatMaLoaiVanPhongPham() throws SQLException{
            String number = "";
        int n = 1;
        do{       
        number = "LVPP"+ n;
        n++;
        }while(kiemTraMaLoaiVanPhongPham(number));
        return number;
    }
    public boolean kiemTraMaLoaiVanPhongPham(String code) throws SQLException{
	boolean duplicate = false;
        // Thực hiện kết nối đến cơ sở dữ liệu ở đây
        ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;

        
            // Thực hiện truy vấn kiểm tra code
            p = con.prepareStatement("select LoaiVanPhongPhamID from LoaiVanPhongPham where  LoaiVanPhongPhamID = ?");
            p.setString(1, code);
            rs = p.executeQuery();

            if (rs.next()) {
                duplicate = true;
            }
        return duplicate;
    }
    public boolean InsertLoaiVanPhongPham(LoaiVanPhongPham loaiVanPhongPham){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement state = null;
        int n = 0;
        try {
            String sql = "INSERT INTO [dbo].[LoaiVanPhongPham]([LoaiVanPhongPhamID],[TenVanPhongPham])\n" +
                           "VALUES (?,?)";
            state = con.prepareStatement(sql);
            state.setString(1, loaiVanPhongPham.getMaLoaiVanPhongPham());
            state.setString(2, loaiVanPhongPham.getTenLoaiVanPhongPham());
            n = state.executeUpdate();
        } catch (Exception e) {
        }
        return n > 0;
    }
    
    public ArrayList<LoaiVanPhongPham> layDanhLoaiVanPhongPham(){
        ArrayList<LoaiVanPhongPham> dslsp = new ArrayList<LoaiVanPhongPham>();
         ConnectDB.getInstance();
         Connection con = ConnectDB.getConnection();
        PreparedStatement pst = null;
        try {
            String sql = "select * from LoaiVanPhongPham";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String maLoaiSanPham =  rs.getString(1);
                String tenTloaiSanPham = rs.getString(2);
               
              
            LoaiVanPhongPham loaiVanPhongPham = new LoaiVanPhongPham(maLoaiSanPham, tenTloaiSanPham);
             dslsp.add(loaiVanPhongPham);
                }
            } catch (Exception e) {
        }
        return dslsp;
    }
}
