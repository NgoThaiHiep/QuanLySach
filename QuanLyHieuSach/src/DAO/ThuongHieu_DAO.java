
package DAO;

import ConnectDB.ConnectDB;
import Entity.TacGia;
import Entity.ThuongHieu;
import Entity.XuatXu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author FPTSHOP
 */
public class ThuongHieu_DAO {
    public ThuongHieu_DAO (){
        
    }
    public ArrayList<ThuongHieu> layDanhSachXuatXu(){
        ArrayList<ThuongHieu> dsth= new ArrayList<ThuongHieu>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
          PreparedStatement pst = null;
        try {
            String sql = "select * from ThuongHieu";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String maThuongHieu =  rs.getString(1);
                String tenThuongHieu = rs.getString(2);
               
              
              ThuongHieu th = new ThuongHieu(maThuongHieu, tenThuongHieu);
               dsth.add(th);
                }
            } catch (Exception e) {
        }
        return dsth;
    }
}
