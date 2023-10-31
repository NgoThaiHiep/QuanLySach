
package DAO;

import ConnectDB.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Random;

/**
 *
 * @author Thái Hiệp
 */
public class Sach_DAO {
    public Sach_DAO(){
        
    }
    
        public String generateMaSach() throws SQLException{
            String number = "";
             
        for (int i = 1; i < 99999; i++) {
            // Định dạng số thành chuỗi và thêm các số 0 bổ sung để đảm bảo đủ 5 chữ số
            number = "Sach"+String.format("%05d", i);
            
            if(!kiemTraMaSach(number)){
                break;
            }
            }  
        return number;
	}
    public boolean kiemTraMaSach(String code) throws SQLException{
	boolean duplicate = false;

        // Thực hiện kết nối đến cơ sở dữ liệu ở đây
        ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;

        
            // Thực hiện truy vấn kiểm tra code
            p = con.prepareStatement("select SachID from Sach where SachID = ?");
            p.setString(1, code);
            rs = p.executeQuery();

            if (rs.next()) {
                duplicate = true;
            }
        return duplicate;
        }
    
}
