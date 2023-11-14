
package DAO;

import ConnectDB.ConnectDB;
import Entity.LoaiSanPham;
import Entity.TheLoai;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author FPTSHOP
 */
public class LoaiSanPham_DAO {
    public LoaiSanPham_DAO(){
        
    }
    public ArrayList<LoaiSanPham> layDanhLoaiSanPham(){
        ArrayList<LoaiSanPham> dslsp = new ArrayList<LoaiSanPham>();
         ConnectDB.getInstance();
         Connection con = ConnectDB.getConnection();
        PreparedStatement pst = null;
        try {
            String sql = "select * from LoaiSanPham";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String maLoaiSanPham =  rs.getString(1);
                String tenTloaiSanPham = rs.getString(2);
               
              
             LoaiSanPham lsp = new LoaiSanPham(maLoaiSanPham, tenTloaiSanPham);
             dslsp.add(lsp);
                }
            } catch (Exception e) {
        }
        return dslsp;
    }
}
