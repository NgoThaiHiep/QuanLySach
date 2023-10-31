
package Entity;

/**
 *
 * @author ThaiHiep
 */
public class VanPhongPham extends SanPham{
    private XuatXu xuatXu;
    private ThuongHieu thuongHieu;
    private LoaiVanPhongPham loaiVanPhongPham;
    private String chatLieu;
    private int namSanXuat;

    public VanPhongPham(String maSanPham, String tenSanPham, LoaiSanPham loaiSanPham, NhaCungCap nhaCungCap, int soLuongTon, double donGia, String moTa, String tinhTrang, String hinhAnh) {
        super(maSanPham, tenSanPham, loaiSanPham, nhaCungCap, soLuongTon, donGia, moTa, tinhTrang, hinhAnh);
    }

    public VanPhongPham(String maSanPham) {
        super(maSanPham);
    }

    public VanPhongPham() {
    }

    public XuatXu getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(XuatXu xuatXu) {
        this.xuatXu = xuatXu;
    }

    public ThuongHieu getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(ThuongHieu thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public LoaiVanPhongPham getLoaiVanPhongPham() {
        return loaiVanPhongPham;
    }

    public void setLoaiVanPhongPham(LoaiVanPhongPham loaiVanPhongPham) {
        this.loaiVanPhongPham = loaiVanPhongPham;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public int getNamSanXuat() {
        return namSanXuat;
    }

    public void setNamSanXuat(int namSanXuat) {
        this.namSanXuat = namSanXuat;
    }

    @Override
    public String toString() {
        return "VanPhongPham{" + "xuatXu=" + xuatXu + ", thuongHieu=" + thuongHieu + ", loaiVanPhongPham=" + loaiVanPhongPham + ", chatLieu=" + chatLieu + ", namSanXuat=" + namSanXuat + '}';
    }
    
    
}
