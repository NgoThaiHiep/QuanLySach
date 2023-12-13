
package Entity;

import java.util.Objects;

/**
 *
 * @author ThaiHiep
 */
public class LoaiSanPham {
    private String maLoaiSanPham ;
    private String tenLoaiSanPham ;

    public LoaiSanPham(String maLoaiSanPham) {
        this.maLoaiSanPham = maLoaiSanPham;
    }

    

    

    public LoaiSanPham(String maLoaiSanPham, String tenLoaiSanPham) {
        this.maLoaiSanPham = maLoaiSanPham;
        this.tenLoaiSanPham = tenLoaiSanPham;
    }
    public LoaiSanPham() {
    }

    public String getMaLoaiSanPham() {
        return maLoaiSanPham;
    }

    public void setMaLoaiSanPham(String maLoaiSanPham) {
        this.maLoaiSanPham = maLoaiSanPham;
    }

    public String getTenLoaiSanPham() {
        return tenLoaiSanPham;
    }

    public void setTenLoaiSanPham(String tenLoaiSanPham) {
        this.tenLoaiSanPham = tenLoaiSanPham;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.maLoaiSanPham);
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
        final LoaiSanPham other = (LoaiSanPham) obj;
        return Objects.equals(this.maLoaiSanPham, other.maLoaiSanPham);
    }

    @Override
    public String toString() {
        return "LoaiSanPham{" + "maLoaiSanPham=" + maLoaiSanPham + ", tenLoaiSanPham=" + tenLoaiSanPham + '}';
    }
    
}
