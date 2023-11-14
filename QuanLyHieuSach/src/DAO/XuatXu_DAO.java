
package DAO;

import ConnectDB.ConnectDB;
import Entity.TacGia;
import Entity.XuatXu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author FPTSHOP
 */
public class XuatXu_DAO {
    public XuatXu_DAO (){
        
    }
    public ArrayList<XuatXu> layDanhSachXuatXu(){
        ArrayList<XuatXu> dsxx= new ArrayList<XuatXu>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
          PreparedStatement pst = null;
        try {
            String sql = "select * from XuatXu";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String maXuatXu =  rs.getString(1);
                String tenXuatXu = rs.getString(2);
               
              
               XuatXu xx = new XuatXu(maXuatXu, tenXuatXu);
               dsxx.add(xx);
                }
            } catch (Exception e) {
        }
        return dsxx;
    }
}
