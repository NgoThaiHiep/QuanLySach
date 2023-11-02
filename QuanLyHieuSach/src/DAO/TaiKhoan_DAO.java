package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import java.util.Random;

import ConnectDB.ConnectDB;
import Entity.NhanVien;
import Entity.TaiKhoan;


public class TaiKhoan_DAO {
	
	
	public TaiKhoan_DAO() {
		
	}
	public boolean login(TaiKhoan tk){
        ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
        PreparedStatement pst = null;
        try{
            String sql = "select * from TaiKhoan where TenDangNhap = ? and MatKhau = ? and TrangThai = N'Đã đăng xuất'";
            pst = con.prepareStatement(sql);
            pst.setString(1,tk.getTenTK());
            pst.setString(2, tk.getMatKhau());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                
                return true;
            }
            
                    
        }catch (SQLException e){
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
        return false;
    }
       
	public boolean login_DaDangNhap(TaiKhoan tk){
        ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
        PreparedStatement pst = null;
        try{
            String sql = "select * from TaiKhoan where TenDangNhap = ? and MatKhau = ? ";
            pst = con.prepareStatement(sql);
            pst.setString(1,tk.getTenTK());
            pst.setString(2, tk.getMatKhau());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                
                return true;
            }
            
                    
        }catch (SQLException e){
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
        return false;
    }
        
        
        
	public boolean inserTaiKhoan(TaiKhoan tk) throws SQLException{
		ConnectDB.getInstance();
                int n = 0;
                Connection con = ConnectDB.getConnection();
                 PreparedStatement pst = null;
                try {
                pst = con.prepareStatement("INSERT INTO [dbo].[TaiKhoan]([TenDangNhap],[MatKhau],[MaXacNhan],[TrangThai])VALUES(?,?,?,?)");
		String code = generateVerifyCode();
		pst.setString(1, tk.getTenTK());
		pst.setString(2, tk.getMatKhau());
		pst.setString(3, "");
		pst.setString(4, "Đã đăng xuất");
		n = pst.executeUpdate();
             } catch (SQLException e) {
                 
            }
            
             finally {
                    try {
                        pst.close();
                    } catch (SQLException e) {
                    }
                }
		
	return n > 0;
		
	}
	
	public String generateVerifyCode() throws SQLException{
		DecimalFormat df= new DecimalFormat("000000");
		Random ran = new Random();
		String code = df.format(ran.nextInt(1000000));
		while(checkDuplicateCode(code)) {
			code = df.format(ran.nextInt(1000000));
		}
		return code;
		
	}
	public boolean checkDuplicateCode(String code) throws SQLException{
		  boolean duplicate = false;
		 	ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
	        PreparedStatement p = con.prepareStatement("select TenDangNhap  from TaiKhoan where MaXacNhan = ?");
	        p.setString(1, code);
	        
	        ResultSet rs = p.executeQuery();
	        if (rs.next()) {
	            duplicate = true;
	        }
	        rs.close();
	        p.close();
	        return duplicate;
	}
	public boolean checkDuplicateEmail(String Email) throws SQLException {
		boolean duplicate = false;
		ConnectDB.getConnection();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pst  =con.prepareStatement("select TaiKhoan from NhanVien where Email  = ?  and TrangThai = N'Đang làm'");
		pst.setString(1, Email);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			duplicate = true;
		}
		rs.close();
		pst.close();
		return duplicate;
	}
        
	public boolean CheckEmailLost(NhanVien nv,TaiKhoan tk) throws SQLException {
		boolean duplicate = false;
		ConnectDB.getInstance();
		//String code = generateVerifyCode();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pst  =con.prepareStatement("select TaiKhoan from NhanVien where Email  = ?  and TrangThai = N'Đang làm'");
		pst.setString(1, nv.getEmail());
		ResultSet rs = pst.executeQuery();  
		if(rs.next()) {
			tk.setTenTK(rs.getString(1));
			//user.setVertifyCode(code);
			duplicate = true;
		}
		rs.close();
		pst.close();
		return duplicate;
	}
        public boolean updateMaXacNhanLost(TaiKhoan tk) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement state = null;
                String code = generateVerifyCode();
		int n = 0;
		try {
                    state = con.prepareStatement("update TaiKhoan set MaXacNhan = ? where TenDangNhap = ?");
	            state.setString(1,code);
	            state.setString(2, tk.getTenTK());
	            n = state.executeUpdate();
                    tk.setMaXacNhan(code);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
                        
		}
		finally {
			try {
				state.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
               return n > 0;
        }
        
        public boolean upDateMatKhau(String tk, String matKhauCu, String matKhauMoi) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement state = null;
                String code = generateVerifyCode();
		int n =0;
		try {
                    state = con.prepareStatement("update TaiKhoan set MatKhau = ? where TenDangNhap = ? and MatKhau = ?");
	            state.setString(1,matKhauMoi);
	            state.setString(2, tk);
                    state.setString(3, matKhauCu);
	            n = state.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
                        
		}
		finally {
			try {
				state.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
               return n > 0;
        }
        
//	public boolean checkDuplicateUser(String user) throws SQLException {
//		boolean duplicate = false;
//		ConnectDB.getInstace();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement pst  =con.prepareStatement("select  UserID from userLogin where UserName  = ?  and Status = 'Verifield'");
//		pst.setString(1, user);
//		ResultSet rs = pst.executeQuery();
//		if(rs.next()) {
//			duplicate = true;
//		}
//		rs.close();
//		pst.close();
//		return duplicate;
//	}
	
//	public void doneVerify(int userID)throws SQLException {
//		ConnectDB.getInstace();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement pst =con.prepareStatement("update userLogin set VertifyCode = '', Status  = 'Verifield' where UserID = ?");
//		pst.setInt(1, userID);
//		pst.execute();
//		pst.close();
//	}
//	public boolean  VertifyCodeWithUser(int user, String code) throws SQLException {
//		boolean vertify = false;
//		ConnectDB.getInstace();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement pst = con.prepareStatement("Select UserID from userLogin where UserID = ? and  VertifyCode = ?");
//		pst.setInt(1, user);
//		pst.setString(2, code);
//		
//		ResultSet rs = pst.executeQuery();
//		if(rs.next()) {
//			vertify = true;
//		}
//		rs.close();
//		pst.close();
//		return vertify;
//	}
	public boolean updataPasswordLost(String id,String pass)throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement state = null;
		int n =0;
		try {
                    state = con.prepareStatement("update TaiKhoan set MatKhau = ? , MaXacNhan = '' where TenDangNhap = ? ");
	            state.setString(1,pass);
	            state.setString(2, id);
	            n = state.executeUpdate();
		} catch ( SQLException e) {
			// TODO: handle exception
			
		}
		finally {
			try {
				state.close();
			} catch (SQLException e2) {
                            // TODO: handle exception

			}
		}
		return n>0;	
	}
        public boolean updataTinhTrangDangNhap(String id,String trangThai)throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement state = null;
		int n =0;
		try {
                    state = con.prepareStatement("update TaiKhoan set TrangThai = ? where TenDangNhap = ? ");
	            state.setString(1,trangThai);
	            state.setString(2, id);
	            n = state.executeUpdate();
		} catch ( SQLException e) {
			// TODO: handle exception
			
		}
		finally {
			try {
				state.close();
			} catch (SQLException e2) {
                            // TODO: handle exception

			}
		}
		return n>0;	
	}
        
}
