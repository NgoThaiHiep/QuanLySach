 
package Entity;

/**
 *
 * @author ThaiHiep
 */
public class Sach extends SanPham{
    private TacGia tacGia ;
    private int namXuatBan ;
    private  int soTrang ;
    private TheLoai theLoai; 
    private NhaXuatBan nhaXuatBan ;
    private String ngonNgu;
    public Sach(TacGia tacGia, int namXuatBan, int soTrang, TheLoai theLoai, NhaXuatBan nhaXuatBan, String maSanPham, String tenSanPham, LoaiSanPham loaiSanPham, NhaCungCap nhaCungCap, int soLuongTon, double donGia, String moTa, String tinhTrang, String hinhAnh,String ngonNgu) {
        super(maSanPham, tenSanPham, loaiSanPham, nhaCungCap, soLuongTon, donGia, moTa, tinhTrang, hinhAnh);
        this.tacGia = tacGia;
        this.namXuatBan = namXuatBan;
        this.soTrang = soTrang;
        this.theLoai = theLoai;
        this.nhaXuatBan = nhaXuatBan;
        this.ngonNgu = ngonNgu;
    }

    public Sach(TacGia tacGia, int namXuatBan, int soTrang, TheLoai theLoai, NhaXuatBan nhaXuatBan, String maSanPham, String tenSanPham, LoaiSanPham loaiSanPham, NhaCungCap nhaCungCap, int soLuongTon, double donGia, String tinhTrang, String hinhAnh) {
        super(maSanPham, tenSanPham, loaiSanPham, nhaCungCap, soLuongTon, donGia, tinhTrang, hinhAnh);
        this.tacGia = tacGia;
        this.namXuatBan = namXuatBan;
        this.soTrang = soTrang;
        this.theLoai = theLoai;
        this.nhaXuatBan = nhaXuatBan;
    }
    

    public Sach(String maSanPham) {
        super(maSanPham);
    }

    public String getNgonNgu() {
        return ngonNgu;
    }

    public void setNgonNgu(String ngonNgu) {
        this.ngonNgu = ngonNgu;
    }

    
    public Sach() {
    }

    public TacGia getTacGia() {
        return tacGia;
    }

    public void setTacGia(TacGia tacGia) {
        this.tacGia = tacGia;
    }

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public int getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }

    public TheLoai getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(TheLoai theLoai) {
        this.theLoai = theLoai;
    }

    public NhaXuatBan getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(NhaXuatBan nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    @Override
    public String toString() {
        return "Sach{" + "tacGia=" + tacGia + ", namXuatBan=" + namXuatBan + ", soTrang=" + soTrang + ", theLoai=" + theLoai + ", nhaXuatBan=" + nhaXuatBan + ", ngonNgu=" + ngonNgu + '}';
    }


    
    
    
}
