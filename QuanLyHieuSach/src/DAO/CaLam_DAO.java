
package DAO;
import ConnectDB.ConnectDB;
import Entity.CaLamViec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Thái Hiệp
 */
import java.util.ArrayList;
public class CaLam_DAO {
    public CaLam_DAO(){
        
    }
    public ArrayList<CaLamViec> layDanhSachCaLamViec(){
        ArrayList<CaLamViec> dsCaLam = new ArrayList<CaLamViec>();
         ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
        PreparedStatement pst = null;
        try {
            String sql = "select * from CaLamViec";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String maCaLam =  rs.getString(1);
                String tenCa = rs.getString(2);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String thoiGianBatDau = rs.getString("ThoiGianBatDau").substring(0, 8);
                String thoiGianKetCa= rs.getString("ThoiGianKetCa").substring(0, 8);
                
                CaLamViec caLamViec = new CaLamViec(maCaLam,tenCa,LocalTime.parse(thoiGianBatDau, formatter),LocalTime.parse(thoiGianKetCa, formatter));
                dsCaLam.add(caLamViec);
                }
            } catch (Exception e) {
        }
        return dsCaLam;
    }
}
