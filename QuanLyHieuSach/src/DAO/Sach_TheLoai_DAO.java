package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import ConnectDB.ConnectDB;

public class Sach_TheLoai_DAO {
	public Sach_TheLoai_DAO() {
		
	}
	
	public List<Object> KiemTraTheLoai(String maSach, List<Object> s) {
		 ConnectDB.getInstance();
		 Connection con = ConnectDB.getConnection();
		 PreparedStatement pst = null;
		 try{
	            String sql = "select * from Sach_TheLoai where SachID = ?";
	            pst = con.prepareStatement(sql);
	            pst.setString(1,maSach);    
	            ResultSet rs = pst.executeQuery();
	            while(rs.next()){
	                
	            	String sql1 = "select * from TheLoai where TheLoaiID = ?";
	   	            pst = con.prepareStatement(sql1);
	   	            pst.setString(1,rs.getString(2));    
	   	            ResultSet rs1 = pst.executeQuery();
	   	            if(rs1.next()) {
	   	             s.add(rs1.getString(2));
	   	            }
	              
	               
	            }    
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        finally {
		try {
			pst.close();
			} catch (SQLException e2) {
			// TODO: handle exceptione2 
			e2.printStackTrace();
			
				}
	        }
		 return s;
	}
}
