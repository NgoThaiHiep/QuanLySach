
package Entity;

import java.util.Objects;

/**
 *
 * @author ThaiHiep
 */
public class SanPham {
    private String maSanPham;
    private String tenSanPham ;
    private LoaiSanPham loaiSanPham ;
    private NhaCungCap nhaCungCap ;
    private int soLuongTon ;
    private double donGia ;
    private String moTa ;
    private String tinhTrang ;
    private String hinhAnh ;

    public SanPham(String maSanPham, String tenSanPham, LoaiSanPham loaiSanPham, NhaCungCap nhaCungCap, int soLuongTon, double donGia, String moTa, String tinhTrang, String hinhAnh) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.loaiSanPham = loaiSanPham;
        this.nhaCungCap = nhaCungCap;
        this.soLuongTon = soLuongTon;
        this.donGia = donGia;
        this.moTa = moTa;
        this.tinhTrang = tinhTrang;
        this.hinhAnh = hinhAnh;
    }

  public SanPham(String maSanPham, String tenSanPham, LoaiSanPham loaiSanPham, NhaCungCap nhaCungCap, int soLuongTon, double donGia, String tinhTrang, String hinhAnh) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.loaiSanPham = loaiSanPham;
        this.nhaCungCap = nhaCungCap;
        this.soLuongTon = soLuongTon;
        this.donGia = donGia;
        this.tinhTrang = tinhTrang;
        this.hinhAnh = hinhAnh;
    }
    public SanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }
    public SanPham() {
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public LoaiSanPham getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(LoaiSanPham loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.maSanPham);
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
        final SanPham other = (SanPham) obj;
        return Objects.equals(this.maSanPham, other.maSanPham);
    }

    @Override
    public String toString() {
        return "SanPham{" + "maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", loaiSanPham=" + loaiSanPham + ", nhaCungCap=" + nhaCungCap + ", soLuongTon=" + soLuongTon + ", donGia=" + donGia + ", moTa=" + moTa + ", tinhTrang=" + tinhTrang + ", hinhAnh=" + hinhAnh + '}';
    }
    
    
    
}
