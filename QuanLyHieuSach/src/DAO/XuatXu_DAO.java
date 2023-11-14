
package DAO;

import ConnectDB.ConnectDB;
import Entity.XuatXu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

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
                String tenQuocGia = rs.getString(2);
               
              
               XuatXu xx = new XuatXu(maXuatXu, tenQuocGia);
               dsxx.add(xx);
                }
            } catch (Exception e) {
        }
        return dsxx;
    }
    public String generateXuatXu() throws SQLException{
            String number = "";
        int n = 1;
        do{
            
        number = "XX"+ n;
            
        n++;
        }while(kiemTraMaXuatXu(number));
        return number;
    }
    public boolean kiemTraMaXuatXu(String code) throws SQLException{
	boolean duplicate = false;

        // Thực hiện kết nối đến cơ sở dữ liệu ở đây
        ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;

        
            // Thực hiện truy vấn kiểm tra code
            p = con.prepareStatement("select XuatXuID from XuatXu where XuatXuID = ?");
            p.setString(1, code);
            rs = p.executeQuery();

            if (rs.next()) {
                duplicate = true;
            }
        return duplicate;
    }
    public boolean InsertXuatXu(XuatXu xuatXu){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement state = null;
        int n = 0;
        try {
            String sql = "INSERT INTO [dbo].[XuatXu]\n" +
"           ([XuatXuID]\n" +
"           ,[TenQuocGia])\n" +
"     VALUES\n" +
"           (?,?)";
            state = con.prepareStatement(sql);
            state.setString(1, xuatXu.getMaXuatXu());
            state.setString(2, xuatXu.getTenQuocGia());
            n = state.executeUpdate();
        } catch (Exception e) {
        }
        return n > 0;
    }
}
