
package DAO;

import ConnectDB.ConnectDB;
import Entity.ChucVu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Thái Hiệp
 */
public class ChucVu_DAO {
        public ChucVu_DAO(){
            
        }
        public ArrayList<ChucVu> layDanhSachChucVu(){
            ArrayList<ChucVu> dsChucVu = new ArrayList<ChucVu>();
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
             PreparedStatement pst = null;
             try {
                String sql = "select * from ChucVu";
                pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    String maChucVu = rs.getString(1).trim();
                    String tenChucVu = rs.getString(2).trim();
                    ChucVu chucVu = new ChucVu(maChucVu, tenChucVu);
                    dsChucVu.add(chucVu);
                }
            } catch (Exception e) {
            }
            return dsChucVu;
        }
}
