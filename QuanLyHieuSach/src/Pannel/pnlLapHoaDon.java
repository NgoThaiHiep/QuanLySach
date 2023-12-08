
package Pannel;

import DAO.ChiTietHoaDon_DAO;
import DAO.HangCho_DAO;
import DAO.HoaDon_DAO;
import DAO.KhachHang_DAO;
import DAO.Sach_DAO;
import DAO.VanPhongPham_DAO;
import Entity.ChiTietHoaDon;
import Entity.HangCho;
import Entity.HoaDon;
import Entity.KhachHang;
import Entity.NhanVien;
import Entity.Sach;
import Entity.SanPham;
import Entity.TaiKhoan;
import Entity.VanPhongPham;
import InHoaDon.FieldHoaDon;
import InHoaDon.ParameterHoaDon;
import InHoaDon.XuLyHoaDon;
import ServiceUser.ScrollBarCustom;
import ServiceUser.TableActionCellEditor;
import ServiceUser.TableActionCellRender;
import ServiceUser.TableActionEvent;
import java.awt.Font;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.format.DateTimeFormatter;
import UI.TrangChu;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.time.LocalDate;

import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import net.sf.jasperreports.engine.JRException;



/**
 *
 * @author FPTSHOP
 */
public class pnlLapHoaDon extends javax.swing.JPanel {

    /**
     * Creates new form TaoHoaDon
     */
    private TaiKhoan tk;
    private NhanVien nv;
    private HoaDon_DAO hoaDon_DAO;
    private TrangChu trangChu;
    private LookAndFeel originalLookAndFeel;
    private Sach_DAO sach_DAO;
    private VanPhongPham_DAO vanPhongPham_DAO;
    private KhachHang_DAO khachHang_DAO;
    private ChiTietHoaDon_DAO chiTietHoaDon_DAO;
    private HangCho_DAO hangCho_DAO;
    public pnlLapHoaDon(TaiKhoan tk,NhanVien nv, LookAndFeel originalLookAndFeel) {
        this.nv = nv;
        this.tk = tk;
        this.originalLookAndFeel = originalLookAndFeel;
        initComponents();
        addTableStyle(scrGioHang);
        addTableStyle(scrDanhSachSanPhamTimKiem);
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onThem(int row) {
                editThemSanPhamOnTable(row, 1);
            }

            @Override
            public void onDelete(int row) {
                if(tblGioHang.isEditing()){
                    tblGioHang.getCellEditor().stopCellEditing();
                }
             xoaSanPham();
            }
            @Override
            public void onTru(int row) {
                editTruSanPhamOnTable(row, 1);
            }
        };
        tblGioHang.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender());
        tblGioHang.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor(event));
	hoaDon_DAO = new HoaDon_DAO();
        
	String formattedDate = null;
        try {
           formattedDate = hoaDon_DAO.generateHoaDon(nv);
        } catch (SQLException ex) {
            Logger.getLogger(pnlLapHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblMaHoaDonFont.setText(formattedDate);
        lblMaHoaDonFont.setFont(new java.awt.Font("Times New Roman", Font.BOLD, 15)); 
        
        Date currentDate = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	String formattedDate2 = formatter.format(currentDate);
        
        lblNgayLapHoaDonFont.setText(formattedDate2);
        lblNgayLapHoaDonFont.setFont(new java.awt.Font("Times New Roman", Font.BOLD, 15)); 
        
        lblTenNhanVienFont.setText(nv.getHoTenNhanVien());
        lblTenNhanVienFont.setFont(new java.awt.Font("Times New Roman", Font.BOLD, 15)); // Chú ý: sử dụng Font.BOLD
        
      
       
   
        
        
       // capNhatDanhSanPham();
        duLieuSDT();
      //  kiemTraDuLieuFloat(txtTienKhachDua);
        kiemTraSoTienTraLai();
        capNhatDanhSach_TheoMa(txtTimKiemMaSanPham);
       // lblTenNhanVien.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
    }
    public void capNhatDanhSach_TheoMa(JTextField txt){
        txt.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                capNhatDanhSanPham_TheMa(txt.getText());
                DefaultTableModel model = (DefaultTableModel ) tblDanhSachSanPhamTimKiem.getModel();
                int row = model.getRowCount();
                if(row == 1){
                  
                    txtMaSanPham.setText(model.getValueAt(row - 1, 1).toString());
                    txtTenSanPham.setText(model.getValueAt(row - 1, 2).toString());
                    txtGiaBan.setText(model.getValueAt(row - 1, 3).toString());
                    txtSoLuong.setText("1");
                    themSoLuongSanPham();
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                capNhatDanhSanPham_TheMa(txt.getText());
               
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
               capNhatDanhSanPham_TheMa(txt.getText());
            }
        });
    }
    
public void inHoaDon() throws JRException {
        XuLyHoaDon.getInstance().compileReport();
        if (tblGioHang.getRowCount() == 0) {
        } else {
            try {
                    List<FieldHoaDon> fields = new ArrayList<>();
                    for (int i = 0; i < tblGioHang.getRowCount(); i++) {
                    // Kiểm tra và chuyển đổi kiểu dữ liệu một cách an toàn
                    String tenSP = String.valueOf(tblGioHang.getValueAt(i, 2));
                        Object soLuongObj = tblGioHang.getValueAt(i, 3);
                        System.out.println(soLuongObj+" ");
                    int soLuong = Integer.parseInt(soLuongObj+"");

                    // Kiểm tra và chuyển đổi kiểu dữ liệu cho giaBan
                    Object giaBanObj = tblGioHang.getValueAt(i, 4);
                    
                        System.out.println(giaBanObj+" ");
                    double giaBan = Double.parseDouble(giaBanObj+"");

                    // Kiểm tra và chuyển đổi kiểu dữ liệu cho soLuong
                   
                    // Kiểm tra và chuyển đổi kiểu dữ liệu cho tongTienSP
                    Object tongTienSPObj = tblGioHang.getValueAt(i, 5);
                    System.out.println(tongTienSPObj+" ");
                    double tongTienSP = Double.parseDouble(tongTienSPObj+"");

                     fields.add(new FieldHoaDon(tenSP, soLuong, giaBan, tongTienSP));
                }
                String tenNhanVien = lblTenNhanVienFont.getText();
                String maHoaDon = lblMaHoaDonFont.getText();
                String tienThanhToan = lblTongTien1.getText();
                LocalDate ngay = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String ngayTao = ngay.format(formatter);
                String sdt = txtSoDienThoai.getText();
                if(sdt.length()!=0){
                    sdt = txtSoDienThoai.getText();
                }else{
                    sdt = "Khách vãng lai";
                }
                ParameterHoaDon dataprint = new ParameterHoaDon(tenNhanVien, maHoaDon, tienThanhToan, ngayTao, sdt,fields);
                XuLyHoaDon.getInstance().printReportPayment(dataprint);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void editThemSanPhamOnTable(int row, int soLuong){
                     DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
                    // Cập nhật số lượng và thành tiền
                    int currentSoLuong = Integer.parseInt(model.getValueAt(row, 3).toString());
                    double currentThanhTien = Double.parseDouble(model.getValueAt(row, 5).toString());
                    int newSoLuong = currentSoLuong + soLuong;
                   
                    int soLuongTon =  soLuongToiDaDeThem(model.getValueAt(row, 1).toString());       
                    
                    
                    if(newSoLuong > soLuongTon ){
                        if(soLuongTon == currentSoLuong)
                            
                             JOptionPane.showMessageDialog(this, "Sản phẩm đã thêm số lượng tối đa");
                        else 
                            JOptionPane.showMessageDialog(this, "Số lượng thêm lớn hơn số lượng tồn");                            
                    }else{
                    double newThanhTien = currentThanhTien + Double.parseDouble( model.getValueAt(row, 4).toString());
                    model.setValueAt(newSoLuong, row, 3);
                    model.setValueAt(newThanhTien, row, 5);
                    tblGioHang.setModel(model);
                    
                    DecimalFormat decimalFormat = new DecimalFormat("#,###");
                    String formattedTongTien = decimalFormat.format(tongTien());
                   if(tongTien() == 0 ){
                        lblTongTien1.setText("0"+formattedTongTien+" VND");
                    }else
                        lblTongTien1.setText(formattedTongTien+" VND");
                   }
    }
     public void editTruSanPhamOnTable(int row, int soLuong){
          DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
                    // Cập nhật số lượng và thành tiền
                    int currentSoLuong = Integer.parseInt(model.getValueAt(row, 3).toString());
                    double currentThanhTien = Double.parseDouble(model.getValueAt(row, 5).toString());
                    
                    int newSoLuong = currentSoLuong - soLuong;
                    if(newSoLuong == 0){
                         if(tblGioHang.isEditing()){
                            tblGioHang.getCellEditor().stopCellEditing();
                        }
                    xoaSanPham();
                    }else{ 
                    double newThanhTien = currentThanhTien - Double.parseDouble( model.getValueAt(row, 4).toString());
                    model.setValueAt(newSoLuong, row, 3);
                    model.setValueAt(newThanhTien, row, 5);
                    
                    
                     }
                    tblGioHang.setModel(model);
                    
                    DecimalFormat decimalFormat = new DecimalFormat("#,###.000");
                    String formattedTongTien = decimalFormat.format(tongTien());
                    if(tongTien() == 0.0 ){
                        lblTongTien1.setText("0"+formattedTongTien+" VND");
                    }else
                        lblTongTien1.setText(formattedTongTien+" VND");
    }
    public void kiemTraSoTienTraLai(){
        DecimalFormat df = new DecimalFormat("#,###");
        txtTienKhachDua.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            String priceWithCurrency =  lblTongTien1.getText();

	    // Loại bỏ chữ cái không cần thiết từ chuỗi số
	    String priceWithoutCurrency = priceWithCurrency.replaceAll("[^\\d.]+", "");
	    double giaBan = Double.parseDouble(priceWithoutCurrency);
            if(giaBan < Integer.parseInt(txtTienKhachDua.getText())){
                        txtTienTraLai.setText(  df.format( Double.parseDouble(txtTienKhachDua.getText())- giaBan)+" VND");
                }else{
                       txtTienTraLai.setText("0.0 VND");
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                String priceWithCurrency =  lblTongTien1.getText();

	    // Loại bỏ chữ cái không cần thiết từ chuỗi số
	    String priceWithoutCurrency = priceWithCurrency.replaceAll("[^\\d.]+", "");
	    double giaBan = Double.parseDouble(priceWithoutCurrency);
             
             if(giaBan < Integer.parseInt(txtTienKhachDua.getText())){
                        txtTienTraLai.setText(  df.format( Double.parseDouble(txtTienKhachDua.getText())- giaBan)+" VND");    
                }else{
                       txtTienTraLai.setText("0.0 VND");
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String priceWithCurrency =  lblTongTien1.getText();

	    // Loại bỏ chữ cái không cần thiết từ chuỗi số
	    String priceWithoutCurrency = priceWithCurrency.replaceAll("[^\\d.]+", "");
	    double giaBan = Double.parseDouble(priceWithoutCurrency);
             
               if(giaBan < Integer.parseInt(txtTienKhachDua.getText())){
                        txtTienTraLai.setText(  df.format( Double.parseDouble(txtTienKhachDua.getText())- giaBan)+" VND");
                        
                }else{
                       txtTienTraLai.setText("0.0 VND");
                }
            
            }
        });
    }
    public int  soLuongToiDaDeThem(String maSanPham){
        int soLuongTon = 0 ;
        ArrayList<Sach> dssach = sach_DAO.layDanhSanPhamSach();
        ArrayList<VanPhongPham> dsvpp = vanPhongPham_DAO.layDanhSanPhamVanPhongPham();
         // Tạo một ArrayList mới để chứa cả hai danh sách
        ArrayList<Object> combinedList = new ArrayList<>();

        // Thêm danh sách sách vào danh sách kết hợp
        combinedList.addAll(dssach);

        // Thêm danh sách văn phòng phẩm vào danh sách kết hợp
        combinedList.addAll(dsvpp);
        
        for (Object item : combinedList) {
               
                
             if (item instanceof Sach) {
                Sach sach = (Sach) item;
                if(sach.getMaSanPham().equals(maSanPham))
                    soLuongTon = sach.getSoLuongTon();
               
            } else if (item instanceof VanPhongPham) {
                VanPhongPham vpp = (VanPhongPham) item;
                if(vpp.getMaSanPham().equals(maSanPham))
                // Thực hiện các thao tác với đối tượng VanPhongPham
                    soLuongTon = vpp.getSoLuongTon();
            }
        }
        return soLuongTon;
    }
    
    public void capNhatDanhSanPham(){
       int count = 0;
       sach_DAO = new Sach_DAO();
       vanPhongPham_DAO = new VanPhongPham_DAO();
        
      
        String colTieuDe1[] = new String[]{"STT", "Mã sản phẩm", "Tên sản phẩm", "Giá bán", "Số lượng tồn"};
        
        DefaultTableModel model = new DefaultTableModel(colTieuDe1, 0);
        Object[] row;
        ArrayList<Sach> dssach = sach_DAO.layDanhSanPhamSach();
        ArrayList<VanPhongPham> dsvpp = vanPhongPham_DAO.layDanhSanPhamVanPhongPham();
         // Tạo một ArrayList mới để chứa cả hai danh sách
        ArrayList<Object> combinedList = new ArrayList<>();

        // Thêm danh sách sách vào danh sách kết hợp
        combinedList.addAll(dssach);

        // Thêm danh sách văn phòng phẩm vào danh sách kết hợp
        combinedList.addAll(dsvpp);
        
        for (Object item : combinedList) {
                row = new Object[5];
                count ++;
             if (item instanceof Sach) {
                
                Sach sach = (Sach) item;
                // Thực hiện các thao tác với đối tượng Sach
              row[0] = count ;
              row[1] =   sach.getMaSanPham();
              row[2] = sach.getTenSanPham();
              row[3] = sach.getDonGia();
              row[4] = sach.getSoLuongTon();
                model.addRow(row);
            } else if (item instanceof VanPhongPham) {
                VanPhongPham vpp = (VanPhongPham) item;
                // Thực hiện các thao tác với đối tượng VanPhongPham
               row[0] = count ;
                row[1] =   vpp.getMaSanPham();
                row[2] = vpp.getTenSanPham();
                row[3] = vpp.getDonGia();
               row[4] = vpp.getSoLuongTon();
               model.addRow(row);
            }
             
        }
         tblDanhSachSanPhamTimKiem.setModel(model);
       
//        for (KhachHang nhanVien : dsKhachHang) {
//              row = new Object[12];
//            // GÁN GIÁ TRỊ
//            row[0] = nhanVien.getMaKhachHang();
//            row[1] = nhanVien.getTenKhachHang();
//            row[2] = nhanVien.getSoDienThoai();
//            row[3] = nhanVien.getDiaChi();
//           model.addRow(row);
//        }
//        jTable1.setModel(model);
    }
    public void capNhatDanhSanPham_TheMa(String maSanPham){
       int count = 0;
       sach_DAO = new Sach_DAO();
       vanPhongPham_DAO = new VanPhongPham_DAO();
        
      
        String colTieuDe1[] = new String[]{"STT", "Mã sản phẩm", "Tên sản phẩm", "Giá bán", "Số lượng tồn"};
        
        DefaultTableModel model = new DefaultTableModel(colTieuDe1, 0);
        Object[] row;
        ArrayList<Sach> dssach = sach_DAO.layDanhSachTheoMaSach(maSanPham);
        ArrayList<VanPhongPham> dsvpp = vanPhongPham_DAO.layDanhSanPhamVanPhongPham_TheoMa(maSanPham);
         // Tạo một ArrayList mới để chứa cả hai danh sách
        ArrayList<Object> combinedList = new ArrayList<>();

        // Thêm danh sách sách vào danh sách kết hợp
        combinedList.addAll(dssach);

        // Thêm danh sách văn phòng phẩm vào danh sách kết hợp
        combinedList.addAll(dsvpp);
        
        for (Object item : combinedList) {
                row = new Object[5];
                count ++;
             if (item instanceof Sach) {
                
                Sach sach = (Sach) item;
                // Thực hiện các thao tác với đối tượng Sach
              row[0] = count ;
              row[1] =   sach.getMaSanPham();
              row[2] = sach.getTenSanPham();
              row[3] = sach.getDonGia();
              row[4] = sach.getSoLuongTon();
                model.addRow(row);
            } else if (item instanceof VanPhongPham) {
                VanPhongPham vpp = (VanPhongPham) item;
                // Thực hiện các thao tác với đối tượng VanPhongPham
               row[0] = count ;
                row[1] =   vpp.getMaSanPham();
                row[2] = vpp.getTenSanPham();
                row[3] = vpp.getDonGia();
               row[4] = vpp.getSoLuongTon();
               model.addRow(row);
            }
             
        }
         tblDanhSachSanPhamTimKiem.setModel(model);
       
//        for (KhachHang nhanVien : dsKhachHang) {
//              row = new Object[12];
//            // GÁN GIÁ TRỊ
//            row[0] = nhanVien.getMaKhachHang();
//            row[1] = nhanVien.getTenKhachHang();
//            row[2] = nhanVien.getSoDienThoai();
//            row[3] = nhanVien.getDiaChi();
//           model.addRow(row);
//        }
//        jTable1.setModel(model);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHoaDon = new javax.swing.JPanel();
        lblThongTinKhachHang = new javax.swing.JPanel();
        lblMaKhachHang = new javax.swing.JLabel();
        lblSoDienThoai = new javax.swing.JLabel();
        lblTenKhachHang = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        txtDiemTichLuy = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        txtTenKhachHang = new javax.swing.JTextField();
        pnlTacVu = new javax.swing.JPanel();
        btnLamMoi = new javax.swing.JButton();
        btnThemSanPham = new javax.swing.JButton();
        btnXoaSanPham = new javax.swing.JButton();
        btnThemVaoHangCho = new javax.swing.JButton();
        btnDanhSachHangCho = new javax.swing.JButton();
        pnlTimKiemSanPham = new javax.swing.JPanel();
        lblTimKiemMaSanPham = new javax.swing.JLabel();
        txtTimKiemMaSanPham = new javax.swing.JTextField();
        pnlGioHang = new javax.swing.JPanel();
        scrGioHang = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        pnlDanhSachSanPhamTimKiem = new javax.swing.JPanel();
        scrDanhSachSanPhamTimKiem = new javax.swing.JScrollPane();
        tblDanhSachSanPhamTimKiem = new javax.swing.JTable();
        pnlThongTinHoaDon = new javax.swing.JPanel();
        lblNgayLapHoaDon = new javax.swing.JLabel();
        lblMaHoaDon = new javax.swing.JLabel();
        lblTenNhanVien = new javax.swing.JLabel();
        lblNgayLapHoaDonFont = new javax.swing.JLabel();
        lblMaHoaDonFont = new javax.swing.JLabel();
        lblTenNhanVienFont = new javax.swing.JLabel();
        pnlThongTinSanPham = new javax.swing.JPanel();
        lblMaSanPham = new javax.swing.JLabel();
        lblTenSanPham = new javax.swing.JLabel();
        lblGiaBan = new javax.swing.JLabel();
        lblSoLuong = new javax.swing.JLabel();
        txtMaSanPham = new javax.swing.JTextField();
        txtTenSanPham = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        lblTongTien1 = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        lblTongTien = new javax.swing.JLabel();
        lblTienKhachDua = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        lblTienKhachDua1 = new javax.swing.JLabel();
        txtTienTraLai = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        lblTieuDeLapHoaDon = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1300, 700));

        pnlHoaDon.setPreferredSize(new java.awt.Dimension(1325, 787));
        pnlHoaDon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblThongTinKhachHang.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin khách hàng"));
        lblThongTinKhachHang.setPreferredSize(new java.awt.Dimension(528, 400));
        lblThongTinKhachHang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMaKhachHang.setText("Điểm tích lũy");
        lblThongTinKhachHang.add(lblMaKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        lblSoDienThoai.setText("Số điện thoại");
        lblThongTinKhachHang.add(lblSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        lblTenKhachHang.setText("Tên khách hàng");
        lblThongTinKhachHang.add(lblTenKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        lblDiaChi.setText("Địa chỉ");
        lblThongTinKhachHang.add(lblDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        txtDiemTichLuy.setEditable(false);
        txtDiemTichLuy.setEnabled(false);
        txtDiemTichLuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiemTichLuyActionPerformed(evt);
            }
        });
        lblThongTinKhachHang.add(txtDiemTichLuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 360, -1));

        txtDiaChi.setEditable(false);
        txtDiaChi.setEnabled(false);
        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });
        lblThongTinKhachHang.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 360, -1));

        txtSoDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoDienThoaiActionPerformed(evt);
            }
        });
        lblThongTinKhachHang.add(txtSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 360, -1));

        txtTenKhachHang.setEditable(false);
        txtTenKhachHang.setEnabled(false);
        txtTenKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKhachHangActionPerformed(evt);
            }
        });
        lblThongTinKhachHang.add(txtTenKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 360, -1));

        pnlHoaDon.add(lblThongTinKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 575, 157));

        pnlTacVu.setBorder(javax.swing.BorderFactory.createTitledBorder("Tác vụ"));
        pnlTacVu.setPreferredSize(new java.awt.Dimension(528, 400));
        pnlTacVu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        pnlTacVu.add(btnLamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 24, 118, -1));

        btnThemSanPham.setText("Thêm sản phẩm");
        btnThemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSanPhamActionPerformed(evt);
            }
        });
        pnlTacVu.add(btnThemSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 24, -1, -1));

        btnXoaSanPham.setText("Xóa sản phẩm");
        btnXoaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSanPhamActionPerformed(evt);
            }
        });
        pnlTacVu.add(btnXoaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 24, -1, -1));

        btnThemVaoHangCho.setText("Thêm vào hàng chờ");
        btnThemVaoHangCho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVaoHangChoActionPerformed(evt);
            }
        });
        pnlTacVu.add(btnThemVaoHangCho, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 24, -1, -1));

        btnDanhSachHangCho.setText("Danh sách hàng chờ");
        btnDanhSachHangCho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDanhSachHangChoActionPerformed(evt);
            }
        });
        pnlTacVu.add(btnDanhSachHangCho, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 24, 140, -1));

        pnlHoaDon.add(pnlTacVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(581, 163, 700, 63));

        pnlTimKiemSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm sản phẩm"));
        pnlTimKiemSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTimKiemMaSanPham.setText("Mã sản phẩm");
        pnlTimKiemSanPham.add(lblTimKiemMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 27, 86, -1));

        txtTimKiemMaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemMaSanPhamActionPerformed(evt);
            }
        });
        pnlTimKiemSanPham.add(txtTimKiemMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 24, 200, -1));

        pnlHoaDon.add(pnlTimKiemSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 163, 575, 63));

        pnlGioHang.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ hàng"));

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGioHang.setRowHeight(40);
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        scrGioHang.setViewportView(tblGioHang);

        javax.swing.GroupLayout pnlGioHangLayout = new javax.swing.GroupLayout(pnlGioHang);
        pnlGioHang.setLayout(pnlGioHangLayout);
        pnlGioHangLayout.setHorizontalGroup(
            pnlGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGioHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrGioHang)
                .addContainerGap())
        );
        pnlGioHangLayout.setVerticalGroup(
            pnlGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGioHangLayout.createSequentialGroup()

                .addComponent(scrGioHang, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)

                .addContainerGap()
                .addComponent(scrGioHang, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)

                .addContainerGap())
        );

        pnlHoaDon.add(pnlGioHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(581, 232, 700, 290));

        pnlDanhSachSanPhamTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm tìm kiếm"));

        tblDanhSachSanPhamTimKiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Giá bán", "Số lượng tồn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDanhSachSanPhamTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachSanPhamTimKiemMouseClicked(evt);
            }
        });
        scrDanhSachSanPhamTimKiem.setViewportView(tblDanhSachSanPhamTimKiem);

        javax.swing.GroupLayout pnlDanhSachSanPhamTimKiemLayout = new javax.swing.GroupLayout(pnlDanhSachSanPhamTimKiem);
        pnlDanhSachSanPhamTimKiem.setLayout(pnlDanhSachSanPhamTimKiemLayout);
        pnlDanhSachSanPhamTimKiemLayout.setHorizontalGroup(
            pnlDanhSachSanPhamTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrDanhSachSanPhamTimKiem, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        pnlDanhSachSanPhamTimKiemLayout.setVerticalGroup(
            pnlDanhSachSanPhamTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachSanPhamTimKiemLayout.createSequentialGroup()
                .addComponent(scrDanhSachSanPhamTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pnlHoaDon.add(pnlDanhSachSanPhamTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 232, 575, 290));

        pnlThongTinHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin hóa đơn"));
        pnlThongTinHoaDon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNgayLapHoaDon.setText("Ngày lập hóa đơn :");
        pnlThongTinHoaDon.add(lblNgayLapHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 110, 22));

        lblMaHoaDon.setText("Mã hóa đơn :");
        pnlThongTinHoaDon.add(lblMaHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 80, 22));

        lblTenNhanVien.setText("Tên nhân viên :");
        pnlThongTinHoaDon.add(lblTenNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 80, 20));
        pnlThongTinHoaDon.add(lblNgayLapHoaDonFont, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 100, 22));
        pnlThongTinHoaDon.add(lblMaHoaDonFont, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 150, 22));
        pnlThongTinHoaDon.add(lblTenNhanVienFont, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 150, 22));

        pnlHoaDon.add(pnlThongTinHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(581, 0, 700, 60));

        pnlThongTinSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin sản phẩm"));
        pnlThongTinSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMaSanPham.setText("Mã sản phẩm");
        pnlThongTinSanPham.add(lblMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 27, -1, -1));

        lblTenSanPham.setText("Tên sản phẩm");
        pnlThongTinSanPham.add(lblTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(378, 27, -1, -1));

        lblGiaBan.setText("Đơn giá");
        pnlThongTinSanPham.add(lblGiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        lblSoLuong.setText("Số lượng");
        pnlThongTinSanPham.add(lblSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 74, -1));

        txtMaSanPham.setEnabled(false);
        txtMaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSanPhamActionPerformed(evt);
            }
        });
        pnlThongTinSanPham.add(txtMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 208, -1));

        txtTenSanPham.setEditable(false);
        txtTenSanPham.setEnabled(false);
        txtTenSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSanPhamActionPerformed(evt);
            }
        });
        pnlThongTinSanPham.add(txtTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(481, 24, 208, -1));

        txtGiaBan.setEditable(false);
        txtGiaBan.setEnabled(false);
        txtGiaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaBanActionPerformed(evt);
            }
        });
        pnlThongTinSanPham.add(txtGiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 208, -1));

        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });
        pnlThongTinSanPham.add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 208, -1));

        pnlHoaDon.add(pnlThongTinSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(581, 59, 700, 98));

        lblTongTien1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblTongTien1.setText("0.0 VND");
        pnlHoaDon.add(lblTongTien1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 550, 220, 20));

        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });
        pnlHoaDon.add(btnThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 630, -1, -1));

        lblTongTien.setText("Tổng tiền          :");
        pnlHoaDon.add(lblTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 550, 100, 20));

        lblTienKhachDua.setText("Tiền khách đưa   :");
        pnlHoaDon.add(lblTienKhachDua, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 586, 100, 30));
        pnlHoaDon.add(txtTienKhachDua, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 590, 220, -1));

        lblTienKhachDua1.setText("Tiền trả lại        :");
        pnlHoaDon.add(lblTienKhachDua1, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 590, 90, 20));

        txtTienTraLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienTraLaiActionPerformed(evt);
            }
        });
        pnlHoaDon.add(txtTienTraLai, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 590, 220, -1));

        jLabel1.setText("Khuyến mãi         :");
        pnlHoaDon.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 550, 100, -1));

        pnlHoaDon.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 550, 220, -1));

        lblTieuDeLapHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblTieuDeLapHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTieuDeLapHoaDon.setText("Lập hóa đơn");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTieuDeLapHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 1300, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTieuDeLapHoaDon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtDiemTichLuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiemTichLuyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiemTichLuyActionPerformed

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void txtSoDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoDienThoaiActionPerformed

    private void txtTenKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKhachHangActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
      lamMoiDuLieu_SanPham();
    }//GEN-LAST:event_btnLamMoiActionPerformed
    
    private void txtTimKiemMaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemMaSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemMaSanPhamActionPerformed
    public void lamMoiDuLieu_KhachHang(){
        txtTenKhachHang.setText("");
        txtDiemTichLuy.setText("");
        txtDiaChi.setText("");
    }
    public void lamMoiDuLieu_SanPham(){
        txtMaSanPham.setText("");
        txtTenSanPham.setText("");
        txtGiaBan.setText("");
        txtSoLuong.setText("");
    }
    private void txtMaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSanPhamActionPerformed

    private void txtTenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSanPhamActionPerformed

    private void txtGiaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaBanActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void tblDanhSachSanPhamTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachSanPhamTimKiemMouseClicked
        // TODO add your handling code here:
        btnThemSanPham.setText("Thêm sản phẩm");
        int row = tblDanhSachSanPhamTimKiem.getSelectedRow();
        txtMaSanPham.setText(tblDanhSachSanPhamTimKiem.getValueAt(row, 1).toString());
        txtMaSanPham.setForeground(Color.red);
        txtTenSanPham.setText(tblDanhSachSanPhamTimKiem.getValueAt(row, 2).toString());
        txtGiaBan.setText(tblDanhSachSanPhamTimKiem.getValueAt(row, 3).toString());
       txtSoLuong.setText("");
      
    }//GEN-LAST:event_tblDanhSachSanPhamTimKiemMouseClicked
    private int count = 0;
    private void btnThemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSanPhamActionPerformed
        // TODO add your handling code here:
      
      if(btnThemSanPham.getText().equals("Thêm sản phẩm")){
        
            themSoLuongSanPham();
        
      }
      else{
           capNhatSoLuongSanPham();
      }
       
    }//GEN-LAST:event_btnThemSanPhamActionPerformed
    public void themSoLuongSanPham(){
        String maSanPham = txtMaSanPham.getText();
        String tenSanPham = txtTenSanPham.getText();
        String giaBan = txtGiaBan.getText();
        String soLuong = txtSoLuong.getText();
        if(!txtSoLuong.getText().equals("") ){
        
        double thanhTien = Integer.parseInt(soLuong) * Double.parseDouble(giaBan);
        
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        boolean found = false;
        int rowCount = model.getRowCount();
        // Kiểm tra nếu mã sản phẩm đã tồn tại trong bảng
        for (int i = 0; i < rowCount; i++) {
                if (model.getValueAt(i, 1).equals(maSanPham)) {
                    // Cập nhật số lượng và thành tiền
                    int currentSoLuong = Integer.parseInt(model.getValueAt(i, 3).toString());
                    double currentThanhTien = Double.parseDouble(model.getValueAt(i, 5).toString());
                    int newSoLuong = currentSoLuong + Integer.parseInt(soLuong);
                    int soLuongTon = soLuongToiDaDeThem(maSanPham);
                    if(newSoLuong > soLuongTon){
                        int soLuongMess = soLuongTon -  currentSoLuong;
                        if(soLuongMess > 0){
                        JOptionPane.showMessageDialog(this, "Số lượng sản phẩm có thể thêm là : " + (soLuongMess));
                           
                        }else{
                        JOptionPane.showMessageDialog(this, "Sản phẩm đã thêm số lượng tối đa");
       
                        }
                        found = true;
                        break;
                    }else{           
                    double newThanhTien = currentThanhTien + thanhTien;
                    model.setValueAt(newSoLuong, i, 3);
                    model.setValueAt(newThanhTien, i, 5);
                    found = true;
                    break;
                    }
                
                }
            }

    // Nếu không tìm thấy sản phẩm, thêm sản phẩm mới vào bảng
            if (!found) {
                 int soLuongTon = soLuongToiDaDeThem(maSanPham);
                    if(Integer.parseInt(soLuong) > soLuongTon){
                        
                        JOptionPane.showMessageDialog(this, "Số lượng sản phẩm có thể thêm là : " + (soLuongTon));
                    }else{               
                        count++;
                        model.addRow(new Object[]{count, maSanPham, tenSanPham, soLuong, giaBan, thanhTien});                                }
            }
            tblGioHang.setModel(model);
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String formattedTongTien = decimalFormat.format(tongTien());
           if(tongTien() == 0.0 ){
                lblTongTien1.setText("0"+formattedTongTien+" VND");
            }else
                lblTongTien1.setText(formattedTongTien+" VND");
        }
        
    }
    public void capNhatSoLuongSanPham(){
        String maSanPham = txtMaSanPham.getText();
        String tenSanPham = txtTenSanPham.getText();
        String giaBan = txtGiaBan.getText();
        String soLuong = txtSoLuong.getText();
        if(!soLuong.equals("")){
            double thanhTien = Integer.parseInt(soLuong) * Double.parseDouble(giaBan);
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        int rowCount = model.getRowCount();
        int row = tblGioHang.getSelectedRow();
        int soLuongThem = Integer.parseInt(soLuong);
        // Kiểm tra nếu mã sản phẩm đã tồn tại trong bảng
        if(Integer.parseInt(soLuong)!=0){
        boolean found = false;
        for (int i = 0; i < rowCount; i++) {
                if (model.getValueAt(i, 1).equals(maSanPham)) {
                    found = true;
                    break;
                }
        }
        if(!found){
              int soLuongTon = soLuongToiDaDeThem(maSanPham);
                    if(Integer.parseInt(soLuong) > soLuongTon){
                        JOptionPane.showMessageDialog(this, "Số lượng sản phẩm có thể thêm là : " + (soLuongTon));
                    }else{               
                        count++;
                        model.addRow(new Object[]{count, maSanPham, tenSanPham, soLuong, giaBan, thanhTien});         
                    }
            }else{
                 if(soLuongThem !=0){ 
                    // Cập nhật số lượng và thành tiền
                    int soLuongTon = soLuongToiDaDeThem(maSanPham);
                    if(Integer.parseInt(soLuong) > soLuongTon){   
                        JOptionPane.showMessageDialog(this, "Số lượng sản phẩm cập nhật lớn hơn số lượng tồn : " + (soLuongTon));  
                    }else{           
                    double newThanhTien =  thanhTien;
                    model.setValueAt(soLuongThem,row , 3);
                    model.setValueAt(newThanhTien, row , 5);
                    }
                }
            }
        }
            if(soLuongThem == 0)    
                xoaSanPham();
            tblGioHang.setModel(model);
            DecimalFormat decimalFormat = new DecimalFormat("#,###.000");
            String formattedTongTien = decimalFormat.format(tongTien());
           if(tongTien() == 0.0 ){
                lblTongTien1.setText("0"+formattedTongTien+" VND");
            }else
                lblTongTien1.setText(formattedTongTien+" VND");
        }
       
         
    }
    public double tongTien(){
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        int rowCount = model.getRowCount();
        double tongTien = 0.0;

        for (int i = 0; i < rowCount; i++) {
            double thanhTien = Double.parseDouble(model.getValueAt(i, 5).toString());
            tongTien += thanhTien;
        }

// In ra tổng tiền hoặc thực hiện các thao tác khác với giá trị này
    return tongTien;
    }
    private void btnXoaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSanPhamActionPerformed
        // TODO add your handling code here:
        // Lấy chỉ số cột đang được chọn
//int columnToRemove = tblGioHang.getSelectedColumn();
//
//// Kiểm tra xem có cột nào được chọn không
//if (columnToRemove != -1) {
//    // Lặp qua từng hàng và xóa dữ liệu trong cột đó
//    DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
//    for (int i = 0; i < model.getRowCount(); i++) {
//        model.setValueAt(null, i, columnToRemove);
//    }
//    
//    // Xóa cột khỏi mô hình của bảng
//    TableColumnModel tcm = tblGioHang.getColumnModel();
//    TableColumn col = tcm.getColumn(columnToRemove);
//    
//    tcm.removeColumn(row);

xoaSanPham();

    }//GEN-LAST:event_btnXoaSanPhamActionPerformed
    public void xoaSanPham(){
        // Lấy chỉ số hàng đang được chọn
       int rowToRemove = tblGioHang.getSelectedRow();
        if (rowToRemove != -1) {
            DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
            model.removeRow(rowToRemove);

            // Cập nhật lại STT sau khi xóa hàng
            for (int i = rowToRemove; i < model.getRowCount(); i++) {
                model.setValueAt(i + 1, i, 0); // Cột STT ở đây là cột đầu tiên (0)
            }
            count--;
        }
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String formattedTongTien = decimalFormat.format(tongTien());
           
         if(tongTien() == 0.0 ){
                lblTongTien1.setText("0"+formattedTongTien+" VND");
            }else
                lblTongTien1.setText(formattedTongTien+" VND");
    }
//    
    
//    private void addToPendingPayment() {
//    // Lấy chỉ số hàng đang được chọn trong bảng sản phẩm
//    int[] selectedRows = tblGioHang.getSelectedRows();
//    
//    if (selectedRows.length > 0) { // Kiểm tra xem có hàng nào được chọn không
//        DefaultTableModel modelSanPham = (DefaultTableModel) tblGioHang.getModel();
//        DefaultTableModel modelHangChoThanhToan = (DefaultTableModel)tblGioHang1.getModel();
//        
//        // Duyệt qua tất cả các hàng đã chọn trong bảng sản phẩm
//        for (int selectedRow : selectedRows) {
//            // Lấy dữ liệu từ hàng đã chọn
//            Object[] rowData = new Object[modelSanPham.getColumnCount()];
//            for (int i = 0; i < modelSanPham.getColumnCount(); i++) {
//                rowData[i] = modelSanPham.getValueAt(selectedRow, i);
//            }
//            
//            // Thêm dữ liệu từ hàng đã chọn vào bảng hàng chờ thanh toán
//            modelHangChoThanhToan.addRow(rowData);
//        }
//    }
//}

    public class frmHangCho extends javax.swing.JFrame {

    private KhachHang_DAO khachHang_DAO;
    private HangCho_DAO hangCho_DAO;

    /**
     * Creates new form frmHangCho
     */
    public frmHangCho() {
        initComponents();
        
        capNhatDanhSachHangCho();
        capNhatDanhSachTimKiemTheoSoDienThoai(txtTimKiemSoDienThoai);
    }
    private  int count = 0;
    public void capNhatDanhSachHangCho(){
        String colTieuDe1[] = new String[]{"STT", "Mã Khách Hàng", "Tên khách hàng", "Số điện thoại"};
        DefaultTableModel model = new DefaultTableModel(colTieuDe1, 0);
        hangCho_DAO = new HangCho_DAO();
        khachHang_DAO = new KhachHang_DAO();
        hangCho_DAO.DeleteHangChoQuaNgay();
        ArrayList<HangCho> dsHangCho = hangCho_DAO.layDanhSachHangCho();
        Object[] row;

        Set<String> maKhachHangSet = new HashSet<>(); // Sử dụng Set để lưu trữ giá trị mã khách hàng đã xuất hiện
        for (HangCho hangCho : dsHangCho) {
            
                row = new Object[12];
                String khachHang = hangCho.getKhachHang().getMaKhachHang();
                if (!maKhachHangSet.contains(khachHang)) { // Kiểm tra xem mã khách hàng đã xuất hiện chưa
                    maKhachHangSet.add(khachHang); // Thêm mã khách hàng vào Set
                    count++;
                    row[0] = count;
                    row[1] = khachHang;
                    KhachHang dsKhachHang = khachHang_DAO.layThongTinKhachHang_TheoMa(khachHang);
                    row[2] = dsKhachHang.getTenKhachHang();
                    row[3] = dsKhachHang.getSoDienThoai();
                    model.addRow(row);
                }
            }
        
    tblHangCho.setModel(model);
    }
    
    public void capNhatDanhSachKhachHangTheoMa_HangCho(String sdt){
    String colTieuDe1[] = new String[]{"STT", "Mã Khách Hàng", "Tên khách hàng", "Số điện thoại"};
    DefaultTableModel model = new DefaultTableModel(colTieuDe1, 0);
    hangCho_DAO = new HangCho_DAO();
    khachHang_DAO = new KhachHang_DAO();
    ArrayList<HangCho> dsHangCho = hangCho_DAO.layDanhSachHangCho();
    Object[] row;
    int dem = 0;
    Set<String> maKhachHangSet = new HashSet<>(); // Sử dụng Set để lưu trữ giá trị mã khách hàng đã xuất hiện
    for (HangCho hangCho : dsHangCho) {
    row = new Object[12];
    String khachHang = hangCho.getKhachHang().getMaKhachHang();
        if (!maKhachHangSet.contains(khachHang)) { // Kiểm tra xem mã khách hàng đã xuất hiện chưa
        try {
            ArrayList<KhachHang> dsKhachHangSoDienThoai = khachHang_DAO.layDanhSachTheoMaSach_TheoSoDienThoai(sdt);
            for (KhachHang khachHang1 : dsKhachHangSoDienThoai) {
                if(khachHang1.getMaKhachHang().equals(hangCho.getKhachHang().getMaKhachHang())){
                    maKhachHangSet.add(khachHang); // Thêm mã khách hàng vào Set
                    dem++;
                    row[0] = dem;
                    row[1] = khachHang;
                    row[2] = khachHang1.getTenKhachHang();
                    row[3] = khachHang1.getSoDienThoai();
                    model.addRow(row);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmHangCho.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    tblHangCho.setModel(model);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lblHangCho = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHangCho = new javax.swing.JTable();
        
        btnXoa = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        pnlTimKiemKhachHang = new javax.swing.JPanel();
        lblTimKiemSoDienThoai = new javax.swing.JLabel();
        txtTimKiemSoDienThoai = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblHangCho.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblHangCho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHangCho.setText("Hàng chờ");

        tblHangCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Khách Hàng", "Tên khách hàng", "Số điện thoại"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblHangCho);

       

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanHangDoiActionPerformed(evt);
            }
        });
        pnlTimKiemKhachHang.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12))); // NOI18N
        pnlTimKiemKhachHang.setToolTipText("");

        lblTimKiemSoDienThoai.setText("Số điện thoại");

        javax.swing.GroupLayout pnlTimKiemKhachHangLayout = new javax.swing.GroupLayout(pnlTimKiemKhachHang);
        pnlTimKiemKhachHang.setLayout(pnlTimKiemKhachHangLayout);
        pnlTimKiemKhachHangLayout.setHorizontalGroup(
            pnlTimKiemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemKhachHangLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblTimKiemSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiemSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTimKiemKhachHangLayout.setVerticalGroup(
            pnlTimKiemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTimKiemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTimKiemSoDienThoai)
                    .addComponent(txtTimKiemSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHangCho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTimKiemKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        
                        .addGap(26, 26, 26)
                        .addComponent(btnXoa)
                        .addGap(27, 27, 27)
                        .addComponent(btnThanhToan)
                        .addGap(38, 38, 38)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblHangCho, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(pnlTimKiemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    
                    .addComponent(btnXoa)
                    .addComponent(btnThanhToan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
        int rowToRemove = tblHangCho.getSelectedRow();
        if (rowToRemove != -1) {
            DefaultTableModel model = (DefaultTableModel) tblHangCho.getModel();
            hangCho_DAO = new HangCho_DAO();
            hangCho_DAO.DeleteDanhSachHangCho(model.getValueAt(rowToRemove, 1)+"");
        
            model.removeRow(rowToRemove);
            
            // Cập nhật lại STT sau khi xóa hàng
            for (int i = rowToRemove; i < model.getRowCount(); i++) {
                model.setValueAt(i + 1, i, 0); // Cột STT ở đây là cột đầu tiên (0)
            }
            count--;

            
        }
        
        
        
    }                                      
    
    public void capNhatDanhSachTimKiemTheoSoDienThoai(JTextField txt){
        txt.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            capNhatDanhSachKhachHangTheoMa_HangCho(txt.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                capNhatDanhSachKhachHangTheoMa_HangCho(txt.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }
    private void btnThanhToanHangDoiActionPerformed(java.awt.event.ActionEvent evt) { 
        count = 1;
        sach_DAO = new Sach_DAO();
        vanPhongPham_DAO = new VanPhongPham_DAO();
        int row = tblHangCho.getSelectedRow();
        String maKhachHang = tblHangCho.getValueAt(row, 1).toString();
        hangCho_DAO = new HangCho_DAO();
        ArrayList<HangCho> dsHangCho = hangCho_DAO.layDanhSachHangChoTheoMaKhachHang(maKhachHang);
        DefaultTableModel model =  (DefaultTableModel) tblGioHang.getModel();
        model.setRowCount(0);
        Object[] rowTable;
        txtSoDienThoai.setText("");
        txtSoDienThoai.setText(tblHangCho.getValueAt(row, 3).toString());
        ArrayList<Sach> dssach = sach_DAO.layDanhSanPhamSach();
        
        ArrayList<VanPhongPham> dsvpp = vanPhongPham_DAO.layDanhSanPhamVanPhongPham();
         // Tạo một ArrayList mới để chứa cả hai danh sách
        ArrayList<Object> combinedList = new ArrayList<>();

        // Thêm danh sách sách vào danh sách kết hợp
        combinedList.addAll(dssach);

        // Thêm danh sách văn phòng phẩm vào danh sách kết hợp
        combinedList.addAll(dsvpp);
        
        for (HangCho hangCho : dsHangCho) {
             rowTable = new Object[12];
             //rowTable[3] = '
               for (Object item : combinedList) {
                     if (item instanceof Sach) {
                        Sach sach = (Sach) item;
                        if(hangCho.getSanPham().getMaSanPham().equals(sach.getMaSanPham())){
                        rowTable[0] =  count++;
                        rowTable[1] =  hangCho.getSanPham().getMaSanPham();
                        rowTable[2] = sach.getTenSanPham();
                        rowTable[3] = hangCho.getSoLuong();
                        rowTable[4] = sach.getDonGia();
                        double thanhTien = hangCho.getSoLuong() *  sach.getDonGia();
                        rowTable[5] = thanhTien;
                        model.addRow(rowTable);
                        }
                     } if (item instanceof VanPhongPham) {
                        VanPhongPham vanPhongPham = (VanPhongPham) item;
                        if(hangCho.getSanPham().getMaSanPham().equals(vanPhongPham.getMaSanPham())){
                        rowTable[0] =  count++;
                        rowTable[1] =  hangCho.getSanPham().getMaSanPham();
                        rowTable[2] = vanPhongPham.getTenSanPham();
                        rowTable[3] = hangCho.getSoLuong();
                        rowTable[4] = vanPhongPham.getDonGia();
                        double thanhTien = hangCho.getSoLuong() *  vanPhongPham.getDonGia();
                        rowTable[5] = thanhTien;
                        model.addRow(rowTable);
                        }
                     }
               }
           
        }
        
        tblGioHang.setModel(model);
  
                    DecimalFormat decimalFormat = new DecimalFormat("#,###");
                    String formattedTongTien = decimalFormat.format(tongTien());
                    if(tongTien() == 0.0 ){
                        lblTongTien1.setText("0"+formattedTongTien+" VND");
                    }else
                        lblTongTien1.setText(formattedTongTien+" VND");
        
    }
    // Variables declaration - do not modify                     
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXoa;
    
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHangCho;
    private javax.swing.JLabel lblTimKiemSoDienThoai;
    private javax.swing.JPanel pnlTimKiemKhachHang;
    private javax.swing.JTable tblHangCho;
    private javax.swing.JTextField txtTimKiemSoDienThoai;
    // End of variables declaration                   
}

    private void btnThemVaoHangChoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVaoHangChoActionPerformed
        // TODO add your handling code here:
     //  addToPendingPayment();
     
   // new frmHangCho().setVisible(true);
    KhachHang kh = new KhachHang();
        khachHang_DAO = new KhachHang_DAO();
        try {
            if(txtSoDienThoai.getText().equals("")) {
               kh.setMaKhachHang(khachHang_DAO.generateVerifyCode_KhachHangLe());
            }else{
              kh =   khachHang_DAO.layThongTinKhachHang(txtSoDienThoai.getText());
            }
        } catch (SQLException ex) {
            Logger.getLogger(pnlLapHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        LocalDate localDate = LocalDate.now();
        System.out.println(kh);
    DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
    int rowCount = model.getRowCount();
    int columnCount = model.getColumnCount();
   
    SanPham sanPham = null ;
        int soLuong = 0;
        double donGia = 0;
// Duyệt qua từng hàng và cột để lấy dữ liệu\

    String maKhachHang = "";
    if(kh != null){
          maKhachHang = kh.getMaKhachHang().substring(0,3); 
    }
   
    if(kh != null && !maKhachHang.equals("KHL")){
        if(rowCount!=0){
            hangCho_DAO = new HangCho_DAO();
            hangCho_DAO.DeleteDanhSachHangCho(kh.getMaKhachHang());
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < columnCount; col++) {
                    if(col == 1){
                        Object cellValue = model.getValueAt(row, col);
                         // System.out.print("sách " + cellValue + "\t");
                               sanPham = new SanPham((String) cellValue);
                    }
                    //số lượng
                    if(col == 3){
                        Object cellValue = model.getValueAt(row, col).toString();
                         // System.out.print("sách " + cellValue + "\t");
                          soLuong = Integer.parseInt((String) cellValue);
                    }    
                    //đơn giá
                    if(col == 4){
                        Object cellValue = model.getValueAt(row, col).toString();
            //              System.out.print("sách " + cellValue + "\t");
                        donGia = Double.parseDouble((String) cellValue);
                    }
                    //thành tiền
                    if(col == 5){
                        Object cellValue = model.getValueAt(row, col);
                          //System.out.print("sách " + cellValue + "\t");
                            HoaDon hoaDon = new HoaDon(lblMaHoaDonFont.getText(), localDate, nv, kh);
                            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(hoaDon, sanPham, soLuong,donGia);
                            hoaDon_DAO = new HoaDon_DAO();
                            hoaDon_DAO.InsertHoaDon(hoaDon, nv, kh);
                            HangCho hangCho = new HangCho(kh, sanPham, soLuong, localDate);
                            System.out.println(hangCho ); // Xuống dòng sau khi duyệt qua một hàng
                            System.out.println("\n");
                            hangCho_DAO = new HangCho_DAO();
                            chiTietHoaDon_DAO = new ChiTietHoaDon_DAO();
                            chiTietHoaDon_DAO.InsertCTHoaDon(hoaDon, soLuong, donGia, sanPham);
                            if(hangCho_DAO.InsertHangCho(hangCho)){ 
                            }
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "Đã thêm vào hàng chờ");
            model.setRowCount(0);
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String formattedTongTien = decimalFormat.format(tongTien());
            lamMoiDuLieu_KhachHang();
            lamMoiDuLieu_SanPham();
            AbstractDocument document = (AbstractDocument) txtSoDienThoai.getDocument();
             
            DocumentFilter oldFilter;
            oldFilter = document.getDocumentFilter();
            document.setDocumentFilter(null);

    // Đặt lại giá trị
        txtSoDienThoai.setText("");

        // Đặt lại DocumentFilter
        document.setDocumentFilter(oldFilter);
        txtSoDienThoai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            if(tongTien() == 0.0 ){
                lblTongTien1.setText("0"+formattedTongTien+" VND");
            }else
                lblTongTien1.setText(formattedTongTien+" VND");
        }else{
            JOptionPane.showMessageDialog(this, "Không có sản phẩm để thêm vào hàng chờ");
        }
    }else{
        JOptionPane.showMessageDialog(this, "Nhập số điện thoại để thêm vào hàng chờ");
        }
  
    }//GEN-LAST:event_btnThemVaoHangChoActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
         DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
         
         SanPham sanPham = null ;
        int soLuong = 0;
        double donGia = 0;
// Duyệt qua từng hàng và cột để lấy dữ liệu\
   
        KhachHang kh = new KhachHang();
        khachHang_DAO = new KhachHang_DAO();
        try {
            if(txtSoDienThoai.getText().equals("") ||  khachHang_DAO.layThongTinKhachHang(txtSoDienThoai.getText()) == null) {
               kh.setMaKhachHang(khachHang_DAO.generateVerifyCode_KhachHangLe());
               kh.setTenKhachHang("No name");
            }else{
                kh = khachHang_DAO.layThongTinKhachHang(txtSoDienThoai.getText());
            }
        } catch (SQLException ex) {
            Logger.getLogger(pnlLapHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        LocalDate localDate = LocalDate.now();
        
       int columnCount = model.getColumnCount();
   
        int rowCount = model.getRowCount();
        if(rowCount!=0){
            
            if(txtSoDienThoai.getText().equals("")) {
                int diemTichLuy = kh.getDiemTL();
                 // Loại bỏ chữ cái không cần thiết từ chuỗi số
                String priceWithoutCurrency = lblTongTien1.getText().replaceAll("[^\\d.]+", "");
                double parsedNumber = Double.parseDouble(priceWithoutCurrency);
                diemTichLuy += (int) parsedNumber / 100000 ;
                kh.setDiemTL(diemTichLuy);
                khachHang_DAO.InsertKhachHang(kh);
            }
            HoaDon hoaDon = new HoaDon(lblMaHoaDonFont.getText(), localDate, nv, kh);
            hoaDon_DAO = new HoaDon_DAO();
            hoaDon_DAO.InsertHoaDon(hoaDon, nv, kh);
            
            hangCho_DAO = new HangCho_DAO();
            hangCho_DAO.DeleteDanhSachHangCho(kh.getMaKhachHang());
            
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < columnCount; col++) {
                    if(col == 1){
                        Object cellValue = model.getValueAt(row, col);
                         // System.out.print("sách " + cellValue + "\t");
                               sanPham = new SanPham((String) cellValue);
                    }
                    //số lượng
                    if(col == 3){
                        Object cellValue = model.getValueAt(row, col).toString();
                         // System.out.print("sách " + cellValue + "\t");
                          soLuong = Integer.parseInt((String) cellValue);
                    }    
                    //đơn giá
                    if(col == 4){
                        Object cellValue = model.getValueAt(row, col).toString();
            //              System.out.print("sách " + cellValue + "\t");
                        donGia = Double.parseDouble((String) cellValue);
                    }
                    //thành tiền
                    if(col == 5){
                        Object cellValue = model.getValueAt(row, col);
                        //System.out.print("sách " + cellValue + "\t");
                        ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(hoaDon, sanPham, soLuong,donGia);
                        chiTietHoaDon_DAO = new ChiTietHoaDon_DAO();
                        chiTietHoaDon_DAO.InsertCTHoaDon(hoaDon, soLuong, donGia, sanPham);
                    }
                }
            }
        String formattedDate = null;
        try {
           formattedDate = hoaDon_DAO.generateHoaDon(nv);
        } catch (SQLException ex) {
            Logger.getLogger(pnlLapHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblMaHoaDonFont.setText(formattedDate);
        lblMaHoaDonFont.setFont(new java.awt.Font("Times New Roman", Font.BOLD, 15)); 
        
       
        }else{
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm để thanh toán");
        }
        try {
            inHoaDon();
        } catch (JRException ex) {
            Logger.getLogger(pnlLapHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtTienTraLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienTraLaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienTraLaiActionPerformed

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        // TODO add your handling code here:
       
        btnThemSanPham.setText("Sửa số lượng");
        int row = tblGioHang.getSelectedRow();
        txtMaSanPham.setText(tblGioHang.getValueAt(row, 1).toString());
        txtTenSanPham.setText(tblGioHang.getValueAt(row, 2).toString());
        txtGiaBan.setText(tblGioHang.getValueAt(row, 4).toString());
        txtSoLuong.setText(tblGioHang.getValueAt(row, 3).toString());
        
    }//GEN-LAST:event_tblGioHangMouseClicked

    private void btnDanhSachHangChoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDanhSachHangChoActionPerformed
        // TODO add your handling code here:
        new frmHangCho().setVisible(true);
    }//GEN-LAST:event_btnDanhSachHangChoActionPerformed
    public void kiemTraDuLieuFloat(JTextField textField){
        DecimalFormat df = new DecimalFormat("#,###");
        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String text = textField.getText().replaceAll(",", "");
                int dotIndex = text.indexOf('.');
                if ((c < '0' || c > '9') && c != '.') { // Chỉ cho phép nhập số và dấu chấm
                    e.consume();
                } else if (c == '0' && text.isEmpty()) { // Số đầu tiên không được là 0
                    e.consume();
                } else if (c == '.' && (text.isEmpty() || dotIndex != -1)) { // Dấu chấm không được là ký tự đầu tiên và chỉ được nhập một lần
                    e.consume();
                } else if (dotIndex != -1 && text.substring(dotIndex).length() > 3 && textField.getCaretPosition() > dotIndex) { // Sau dấu chấm chỉ cho phép nhập tối đa 3 số
                    e.consume();
                }
            }
            public void keyReleased(KeyEvent e) {
                String text = textField.getText().replaceAll(",", "");
                if (!text.isEmpty() && !text.contains(".")) {
                    try {
                        long number = Long.parseLong(text);
                        textField.setText(df.format(number));
                    } catch (NumberFormatException ex) {
                        // Không xử lý ngoại lệ này vì nó sẽ không xảy ra do kiểm tra nhập liệu ở trên
                    }
                }
            }
        });
    }

//Để xóa hết hàng trong bảng, bạn có thể sử dụng phương thức setRowCount(0) của DefaultTableModel. Điều này sẽ xóa tất cả các hàng khỏi bảng.
    private void clearTable(DefaultTableModel model) {
        model.setRowCount(0);
        count = 0; // Đặt lại giá trị count về 0 nếu cần thiết
        //Sau đó, để sử dụng hàm này, bạn chỉ cần gọi nó với mô hình của bảng:
//clearTable((DefaultTableModel) tblGioHang.getModel());
//Hàm clearTable sẽ đặt số hàng về 0 và cũng có thể đặt lại giá trị count nếu bạn sử dụng count để theo dõi số lượng hàng.
}
private void duLieuSDT(){
             ((AbstractDocument) txtSoDienThoai.getDocument()).setDocumentFilter(new DocumentFilter() {
                   @Override
                   public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr)
                           throws BadLocationException {
                        String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                        text += string;
                        if (text.length() <= 10 && text.matches("\\d*")) {
                            super.insertString(fb, offset, string, attr);
                        } else if (text.matches("\\d*")) {
                            super.replace(fb, 9, 1, string, attr);
                        }
                   }

                   @Override
                   public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                           throws BadLocationException {
                       String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                       if (newText.length() <= 10 && newText.matches("\\d*")) {
                           super.replace(fb, offset, length, text, attrs);
                       } else if (newText.matches("\\d*")) {
                           super.replace(fb, 9, 1, text, attrs);
                       }
                   }
               });

               txtSoDienThoai.addFocusListener(new FocusAdapter() {
                   @Override
                   public void focusLost(FocusEvent e) {
                       String text = txtSoDienThoai.getText();
                       if (text.length() > 10) {
                           txtSoDienThoai.setText(text.substring(text.length() - 10));
                       }
                   }
               });
               txtSoDienThoai.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                 private KhachHang_DAO khachHang_DAO;
                 @Override
                 public void insertUpdate(DocumentEvent e) {
                      if ( txtSoDienThoai.getText().length() < 10) {
                    // Đặt màu đỏ đậm cho đường viền
                    LineBorder redLineBorder = new LineBorder(Color.RED, 2);
                     txtSoDienThoai.setBorder(redLineBorder);
                     lamMoiDuLieu_KhachHang();
                    } else {
                     if(!txtSoDienThoai.getText().substring(0, 1).equals("0")){
                            lamMoiDuLieu_KhachHang();
                             LineBorder redLineBorder = new LineBorder(Color.RED, 2);
                             txtSoDienThoai.setBorder(redLineBorder);
                            }else{
                            txtSoDienThoai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
                            khachHang_DAO = new KhachHang_DAO();
                            KhachHang kh = khachHang_DAO.layThongTinKhachHang(txtSoDienThoai.getText());            
                            if(kh != null){
                            txtTenKhachHang.setText(kh.getTenKhachHang());
                            txtDiaChi.setText(kh.getDiaChi());
                            txtDiemTichLuy.setText(kh.getDiemTL()+""); 
                            }    
                        }
                    }
                 }

                 @Override
                 public void removeUpdate(DocumentEvent e) {
                      LineBorder redLineBorder = new LineBorder(Color.RED, 2);
                     txtSoDienThoai.setBorder(redLineBorder);
                     lamMoiDuLieu_KhachHang();
                 }
                 
                 @Override
                 public void changedUpdate(DocumentEvent e) {
                     lamMoiDuLieu_KhachHang();
                 }
               });
                
    }
 public void addTableStyle(JScrollPane scroll) {
        scroll.getViewport().setOpaque(false);
        scroll.setViewportBorder(null); 
        scroll.setBorder(null);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        JPanel panel = new JPanel();
        panel.setBackground(new Color(60, 60, 60));
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, panel);
        scroll.setBackground(Color.red);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDanhSachHangCho;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemSanPham;
    private javax.swing.JButton btnThemVaoHangCho;
    private javax.swing.JButton btnXoaSanPham;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblGiaBan;
    private javax.swing.JLabel lblMaHoaDon;
    private javax.swing.JLabel lblMaHoaDonFont;
    private javax.swing.JLabel lblMaKhachHang;
    private javax.swing.JLabel lblMaSanPham;
    private javax.swing.JLabel lblNgayLapHoaDon;
    private javax.swing.JLabel lblNgayLapHoaDonFont;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblTenKhachHang;
    private javax.swing.JLabel lblTenNhanVien;
    private javax.swing.JLabel lblTenNhanVienFont;
    private javax.swing.JLabel lblTenSanPham;
    private javax.swing.JPanel lblThongTinKhachHang;
    private javax.swing.JLabel lblTienKhachDua;
    private javax.swing.JLabel lblTienKhachDua1;
    private javax.swing.JLabel lblTieuDeLapHoaDon;
    private javax.swing.JLabel lblTimKiemMaSanPham;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTongTien1;
    private javax.swing.JPanel pnlDanhSachSanPhamTimKiem;
    private javax.swing.JPanel pnlGioHang;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlTacVu;
    private javax.swing.JPanel pnlThongTinHoaDon;
    private javax.swing.JPanel pnlThongTinSanPham;
    private javax.swing.JPanel pnlTimKiemSanPham;
    private javax.swing.JScrollPane scrDanhSachSanPhamTimKiem;
    private javax.swing.JScrollPane scrGioHang;
    private javax.swing.JTable tblDanhSachSanPhamTimKiem;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDiemTichLuy;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienTraLai;
    private javax.swing.JTextField txtTimKiemMaSanPham;
    // End of variables declaration//GEN-END:variables
}
