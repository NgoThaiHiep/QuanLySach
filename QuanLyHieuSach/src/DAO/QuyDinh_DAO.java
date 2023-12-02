
package DAO;

import ConnectDB.ConnectDB;
import Entity.KhachHang;
import Entity.QuyDinh;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import  java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author FPTSHOP
 */
public class QuyDinh_DAO {
        public QuyDinh_DAO(){
            
        }
        
 
    public QuyDinh layDuLieuQuyDinh(){
            ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        QuyDinh quyDinhData = new QuyDinh();
        try {
            String sql = "Select * from QuyDinh";
            Statement state = con.createStatement();
            ResultSet rs = state.executeQuery(sql);
            if (rs.next()) {
                quyDinhData.setSoLuongToiDa(rs.getInt("SoLuongNhapToiDa"));
                quyDinhData.setSoLuongToiThieu(rs.getInt("SoLuongNhapToiThieu"));
                quyDinhData.setVAT(rs.getFloat("VAT"));
                return quyDinhData;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean updateQuyDinh(QuyDinh quyDinh){
       ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement state = null;
                int n = 0;
                try {
              String sql = "UPDATE [dbo].[QUyDinh]\n" +
                       "   SET\n" +
                       "   SoLuongNhapToiDa = ?, SoLuongNhapToiThieu = ? , VAT= ?";
                      
                state = con.prepareStatement(sql);
                state.setInt(1, quyDinh.getSoLuongToiDa());
                state.setInt(2, quyDinh.getSoLuongToiThieu());
                state.setFloat(3, quyDinh.getVAT());
                n = state.executeUpdate();
        }  catch (Exception e) {
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
    public boolean InsertQuyDinh(int soLuongToiThieu, int soLuongToiDa, float VAT) {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            PreparedStatement state = null;
            ResultSet rs = null;
            int n = 0;
            try {
            String sql = "INSERT INTO [dbo].[QuyDinh]([SoLuongNhapToiThieu],[SoLuongNhapToiDa],[VAT])VALUES(?,?,?)";
            state = con.prepareStatement(sql);
            state.setInt(1,soLuongToiThieu);
            state.setInt(2,soLuongToiDa);
            state.setFloat(3, VAT);
            n = state.executeUpdate();
        }  catch (Exception e) {
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
}

