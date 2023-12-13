
package Entity;

import java.util.Objects;


/**
 *
 * @author ThaiHiep
 */
public class ThuongHieu {
    private String maThuongHieu;
    private String tenThuongHieu;

    public ThuongHieu(String tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
    }

    

    public ThuongHieu(String maThuongHieu, String tenThuongHieu) {
        this.maThuongHieu = maThuongHieu;
        this.tenThuongHieu = tenThuongHieu;
    }

    public ThuongHieu() {
    }

    public String getMaThuongHieu() {
        return maThuongHieu;
    }

    public void setMaThuongHieu(String maThuongHieu) {
        this.maThuongHieu = maThuongHieu;
    }

    public String getTenThuongHieu() {
        return tenThuongHieu;
    }

    public void setTenThuongHieu(String tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.maThuongHieu);
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
        final ThuongHieu other = (ThuongHieu) obj;
        return Objects.equals(this.maThuongHieu, other.maThuongHieu);
    }

    @Override
    public String toString() {
        return "ThuongHieu{" + "maThuongHieu=" + maThuongHieu + ", tenThuongHieu=" + tenThuongHieu + '}';
    }

    
    
}
