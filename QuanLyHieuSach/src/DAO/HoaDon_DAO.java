/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectDB.ConnectDB;
import Entity.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author FPTSHOP
 */
public class HoaDon_DAO {
        public HoaDon_DAO(){
        
    }
    
        public String generateHoaDon(NhanVien nv) throws SQLException{
            Date currentDate = new Date();
	SimpleDateFormat formatterDay = new SimpleDateFormat("ddMMyy");
	String formattedDate = formatterDay.format(currentDate);
        formattedDate +=nv.getMaNV().substring(2, 6);
           
             
        for (int i = 1; i < 9999; i++) {
            // Định dạng số thành chuỗi và thêm các số 0 bổ sung để đảm bảo đủ 5 chữ số
            formattedDate +=String.format("%04d", i);
            
            if(!kiemTraMaHoaDon(formattedDate)){
                break;
            }
            }  
        return formattedDate;
	}
    public boolean kiemTraMaHoaDon(String code) throws SQLException{
	boolean duplicate = false;

        // Thực hiện kết nối đến cơ sở dữ liệu ở đây
        ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;

        
            // Thực hiện truy vấn kiểm tra code
            p = con.prepareStatement("select HoaDonID from HoaDon where HoaDonID = ?");
            p.setString(1, code);
            rs = p.executeQuery();

            if (rs.next()) {
                duplicate = true;
            }
        return duplicate;
        }
}
