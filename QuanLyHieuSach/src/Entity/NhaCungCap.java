
package Entity;

import java.util.Objects;


/**
 *
 * @author ThaiHiep
 */
public class NhaCungCap {
    private String maNCC;
    private String tenNCC;
    private String diaChiNCC;
    private String soDienThoai;
    private String email;

    public NhaCungCap(String maNCC, String tenNCC, String diaChiNCC, String soDienThoai, String email) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.diaChiNCC = diaChiNCC;
        this.soDienThoai = soDienThoai;
        this.email = email;
    }
    public NhaCungCap(String maNCC, String tenNCC) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
    }

    public NhaCungCap() {
    }
    
    public NhaCungCap(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getDiaChiNCC() {
        return diaChiNCC;
    }

    public void setDiaChiNCC(String diaChiNCC) {
        this.diaChiNCC = diaChiNCC;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.maNCC);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NhaCungCap other = (NhaCungCap) obj;
        return Objects.equals(this.maNCC, other.maNCC);
    }

    @Override
    public String toString() {
        return "NhaCungCap{" + "maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", diaChiNCC=" + diaChiNCC + ", soDienThoai=" + soDienThoai + ", email=" + email + '}';
    }

    

    
    
}
