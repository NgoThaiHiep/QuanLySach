
package DAO;

import ConnectDB.ConnectDB;
import Entity.TacGia;
import Entity.ThuongHieu;
import Entity.XuatXu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author FPTSHOP
 */
public class ThuongHieu_DAO {
    public ThuongHieu_DAO (){
        
    }
    public ArrayList<ThuongHieu> layDanhSachThuongHieu(){
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
    public String generateThuongHieu() throws SQLException{
            String number = "";
        int n = 1;
        do{
            
        number = "TH"+ n;
            
        n++;
        }while(kiemTraMaThuongHieu(number));
        return number;
    }
    public boolean kiemTraMaThuongHieu(String code) throws SQLException{
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
    public boolean InsertThuongHieu(ThuongHieu thuongHieu){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement state = null;
        int n = 0;
        try {
            String sql = "INSERT INTO [dbo].[ThuongHieu]\n" +
"           ([ThuongHieuID]\n" +
"           ,[TenThuongHieu])\n" +
"     VALUES\n" +
"           (?,?)";
            state = con.prepareStatement(sql);
            state.setString(1, thuongHieu.getMaThuongHieu());
            state.setString(2, thuongHieu.getTenThuongHieu());
            n = state.executeUpdate();
        } catch (Exception e) {
        }
        return n > 0;
    }
}
