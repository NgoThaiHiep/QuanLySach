
package DAO;

import ConnectDB.ConnectDB;
import Entity.LoaiSanPham;
import Entity.LoaiVanPhongPham;
import Entity.TheLoai;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author FPTSHOP
 */
public class LoaiVanPhongPham_DAO {
    public LoaiVanPhongPham_DAO(){
        
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
