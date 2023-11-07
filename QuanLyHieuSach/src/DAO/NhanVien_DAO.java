
package DAO;

import ConnectDB.ConnectDB;
import Entity.CaLamViec;
import Entity.ChucVu;
import Entity.NhanVien;
import Entity.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.sql.Statement;


/**
 *
 * @author FPTSHOP
 */
public class NhanVien_DAO {
    public NhanVien_DAO(){
        
    }
    public NhanVien layThongTinNhanVien(TaiKhoan  tk){
        ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
        PreparedStatement pst = null;
        NhanVien nv;
        try{
            String sql = "select * from NhanVien where TaiKhoan = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1,tk.getTenTK());    
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                String maNV = rs.getString(1);
                String tenNV = rs.getString(2);
                String cccd = rs.getString(3);
                boolean gioiTinh = rs.getBoolean(4);
                java.sql.Date ngaySinhDate = rs.getDate(5);
                LocalDate ngaySinhLocal = ngaySinhDate.toLocalDate();
                String soDienThoai = rs.getString(6);
                String email = rs.getString(7);
                String diaChi = rs.getString(8);
                String trangThai = rs.getString(9);
                String hinhAnh = rs.getString(10);
                ChucVu chucVu = new ChucVu(rs.getString(11)) ;
                CaLamViec caLam = new CaLamViec(rs.getString(12));
                nv = new NhanVien(maNV, tenNV, cccd, ngaySinhLocal, gioiTinh, email, soDienThoai, chucVu, tk, caLam, trangThai, hinhAnh,diaChi);
                return nv;
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
        return null;
    }
    
    public ArrayList<NhanVien> layDanhSachNhanVien(){
        ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
	ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
        
        try {
        String sql = "select * from NhanVien";
        Statement state = con.createStatement();
	ResultSet rs = state.executeQuery(sql);
        while(rs.next()){
                String maNV = rs.getString(1);
                String tenNV = rs.getString(2);
                String cccd = rs.getString(3);
                boolean gioiTinh = rs.getBoolean(4);
                java.sql.Date ngaySinhDate = rs.getDate(5);
                LocalDate ngaySinhLocal = ngaySinhDate.toLocalDate();
                String soDienThoai = rs.getString(6);
                String email = rs.getString(7);
                String diaChi = rs.getString(8);
                String trangThai = rs.getString(9);
                String hinhAnh = rs.getString(10);
                ChucVu chucVu = new ChucVu(rs.getString(11)) ;
                CaLamViec caLam = new CaLamViec(rs.getString(12));
                TaiKhoan tk = new TaiKhoan(maNV);
                NhanVien nv = new NhanVien(maNV, tenNV, cccd, ngaySinhLocal, gioiTinh, email, soDienThoai, chucVu, tk, caLam, trangThai, hinhAnh,diaChi);
                dsnv.add(nv);
                
            }    
        } catch (SQLException e) {
           e.printStackTrace();
        }
        
        return dsnv; 
    }
    
    public String generateVerifyCode() throws SQLException{
            DecimalFormat df = new DecimalFormat("000000");
            Random ran = new Random();

            // Lấy hai số cuối cùng của năm
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR) % 100;

            String code;
            do {
                code = df.format(year * 10000 + ran.nextInt(10000));
            } while (checkDuplicateCode(code));
        return code;
	}
    public boolean checkDuplicateCode(String code) throws SQLException{
	boolean duplicate = false;

        // Thực hiện kết nối đến cơ sở dữ liệu ở đây
        ConnectDB.getInstance();
	Connection con = ConnectDB.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;

        
            // Thực hiện truy vấn kiểm tra code
            p = con.prepareStatement("select NhanVienID from NhanVien where NhanVienID = ?");
            p.setString(1, code);
            rs = p.executeQuery();

            if (rs.next()) {
                duplicate = true;
            }
        return duplicate;
        }
    
	public boolean InsertNhanVien(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement state = null;
		int n = 0;
		try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			state = con.prepareStatement("INSERT INTO [dbo].[NhanVien]([NhanVienID],[HoTenNhanVien],[CCCD],[GioiTinh],[NgaySinh],[SoDienThoai],[Email],[TrangThai],[HinhAnh],[ChucVu],[CaLamViec],[TaiKhoan])VALUES(?,?, ?, ?,?,?,?,?,?,?,?,?)");
			state.setString(1, nv.getMaNV());
			state.setString(2, nv.getHoTenNhanVien());
			state.setString(3, nv.getCCCD());
			state.setBoolean(4, nv.getGioiTinh());
			state.setString(5, formatter.format(nv.getNgaySinh()));
			state.setString(6, nv.getSoDienThoai());
                        state.setString(7, nv.getEmail());
			state.setString(8, nv.getTrangThai());
                        state.setString(9, nv.getHinhAnh());
                        state.setString(10, nv.getChucVu().getMaChucVu());
                        state.setString(11, nv.getCaLam().getMaCa());
                        state.setString(12, nv.getTaiKhoan().getTenTK());
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
		return n>0;
	}
        public boolean capNhatNhanVien(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement state = null;
		int n =0;
		try {
                     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			state = con.prepareStatement("UPDATE [dbo].[NhanVien]\n" +
                                                    " SET [HoTenNhanVien] = ?,[CCCD] = ? ,[GioiTinh] = ? ,[NgaySinh] = ?,[SoDienThoai] = ?,[Email] = ?,[DiaChi] = ? ,[TrangThai] = ? ,[HinhAnh] =? ,[ChucVu] = ? , [CaLamViec] = ?,[TaiKhoan] = ?\n" +
                                                    " WHERE [NhanVienID] = ? ");
			
			
			state.setString(1, nv.getHoTenNhanVien());
			state.setString(2, nv.getCCCD());
			state.setBoolean(3, nv.getGioiTinh());
			state.setString(4, formatter.format(nv.getNgaySinh()));
			state.setString(5, nv.getSoDienThoai());
                        state.setString(6, nv.getEmail());
                        state.setString(7, nv.getDiaChi());
			state.setString(8, nv.getTrangThai());
                        state.setString(9, nv.getHinhAnh());
                        state.setString(10, nv.getChucVu().getMaChucVu());
                        state.setString(11, nv.getCaLam().getMaCa());
                        state.setString(12, nv.getTaiKhoan().getTenTK());
                        state.setString(13, nv.getMaNV());
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
		return n>0;
	}


}
