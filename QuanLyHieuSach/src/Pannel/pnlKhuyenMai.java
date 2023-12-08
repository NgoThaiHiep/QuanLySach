
package Pannel;
import DAO.GiamGiaSanPham_DAO;
import DAO.KhuyenMaiThanhToan_DAO;
import DAO.Sach_DAO;
import DAO.VanPhongPham_DAO;
import Entity.GiamGiaSanPham;
import Entity.KhuyenMaiThanhToan;
import Entity.Sach;
import Entity.VanPhongPham;
import ServiceUser.SelectedDate;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

/**
 *
 * @author FPTSHOP
 */
public class pnlKhuyenMai extends javax.swing.JPanel {

    private Sach_DAO sach_DAO;
    private VanPhongPham_DAO vanPhongPham_DAO;
    private KhuyenMaiThanhToan_DAO khuyenMaiThanhToan_DAO;
    private GiamGiaSanPham_DAO giamGiaSanPham_DAO;

    /**
     * Creates new form pnlKhuyenMai
     */
    public pnlKhuyenMai() {
        initComponents();
        giaTienRad();
        kiemTraDuLieuFloat(txtGiaTien1);
        kiemTraDuLieuFloat(txtGiaTien2);
        
        kiemTraDuLieuFloat(txtTyLe2);
        kiemTraDuLieuFloat(txtTyLe3);
        
        maHoaDon_GiaTri(lblMaKhuyenMaiKyTu);
        duLieu();
        kiemTraDuLieuFloat1(txtNgayBatDau);
    }
   public void duLieu(){
         sach_DAO = new Sach_DAO();
        vanPhongPham_DAO = new VanPhongPham_DAO();
        
        ArrayList<Sach> dssach = sach_DAO.layDanhSanPhamSach();
        for (Sach sach : dssach) {
           txtSanPham1.addItemSuggestion(sach.getTenSanPham());
       }
        ArrayList<VanPhongPham> dsvpp = vanPhongPham_DAO.layDanhSanPhamVanPhongPham();
         // Tạo một ArrayList mới để chứa cả hai danh sách
         for (VanPhongPham vanPhongPham : dsvpp) {
            txtSanPham1.addItemSuggestion(vanPhongPham.getTenSanPham());
       }
      
   }
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
public void kiemTraDuLieuFloat1(JTextField textField){
       
        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String text = textField.getText().replaceAll(",", "");
                System.out.println(".keyTyped()");
//                if ((c < '0' || c > '9') && c != '.') { // Chỉ cho phép nhập số và dấu chấm
//                   
//                } else if (c == '0' && text.isEmpty()) { // Số đầu tiên không được là 0
//                   
//                } else if (c == '.' && (text.isEmpty() || dotIndex != -1)) { // Dấu chấm không được là ký tự đầu tiên và chỉ được nhập một lần
//                   
//                } else if (dotIndex != -1 && text.substring(dotIndex).length() > 3 && textField.getCaretPosition() > dotIndex) { // Sau dấu chấm chỉ cho phép nhập tối đa 3 số
//                   
//                }
            }
            public void keyReleased(KeyEvent e) {
                System.out.println(".keyReleased()");
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btg1 = new javax.swing.ButtonGroup();
        dateNgayBatDau = new ServiceUser.DateChooser();
        btg2 = new javax.swing.ButtonGroup();
        dateNgayKetThuc = new ServiceUser.DateChooser();
        jLabel1 = new javax.swing.JLabel();
        lblmaKhuyenMai = new javax.swing.JLabel();
        lblTenKhuyenMai = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNgayBatDau = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNgayKetThuc = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txtTyLe3 = new javax.swing.JTextField();
        lblViTri2 = new javax.swing.JLabel();
        radTyLe = new javax.swing.JRadioButton();
        radGiaTien = new javax.swing.JRadioButton();
        lblViTri1 = new javax.swing.JLabel();
        radSanPham = new javax.swing.JRadioButton();
        lblViTri3 = new javax.swing.JLabel();
        txtGiaTien1 = new javax.swing.JTextField();
        txtGiaTien2 = new javax.swing.JTextField();
        txtTyLe1 = new javax.swing.JTextField();
        txtSanPham1 = new ServiceUser.TextFieldSuggestion();
        lblViTri4 = new javax.swing.JLabel();
        txtTyLe4 = new javax.swing.JTextField();
        txtGiaTien3 = new javax.swing.JTextField();
        radGiaTienSanPham = new javax.swing.JRadioButton();
        radTyLeSanPham = new javax.swing.JRadioButton();
        txtSanPham3 = new javax.swing.JTextField();
        txtSanPham4 = new javax.swing.JTextField();
        txtTyLe2 = new javax.swing.JTextField();
        lblMaKhuyenMaiKyTu = new javax.swing.JLabel();
        txtTenKhuyenMai = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        scrDanhSachSanPhamTimKiem = new javax.swing.JScrollPane();
        tblDanhSachKhuyenMai = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnNgayBatDau = new javax.swing.JButton();
        btnNgayKetThuc = new javax.swing.JButton();

        dateNgayBatDau.setTextRefernce(txtNgayBatDau);
        dateNgayBatDau.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dateNgayBatDauFocusGained(evt);
            }
        });
        dateNgayBatDau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dateNgayBatDauMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dateNgayBatDauMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dateNgayBatDauMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dateNgayBatDauMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                dateNgayBatDauMouseReleased(evt);
            }
        });

        dateNgayKetThuc.setTextRefernce(txtNgayKetThuc);
        dateNgayKetThuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dateNgayKetThucMouseClicked(evt);
            }
        });

        setPreferredSize(new java.awt.Dimension(1300, 982));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jLabel1.setText("Khuyến mãi");

        lblmaKhuyenMai.setText("Mã khuyến mãi");

        lblTenKhuyenMai.setText("Tên khuyến mãi");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1132, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 235, Short.MAX_VALUE)
        );

        jLabel7.setText("Tình trạng");

        jLabel11.setText("Ngày bắt đầu");

        jLabel12.setText("Ngày kết thúc");

        txtNgayBatDau.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNgayBatDauFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNgayBatDauFocusLost(evt);
            }
        });
        txtNgayBatDau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNgayBatDauMouseClicked(evt);
            }
        });

        jLabel6.setText("Chi tiết");

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(txtTyLe3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 160, -1));

        lblViTri2.setText("Giá trị tối thiểu đơn hàng");
        jPanel2.add(lblViTri2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, -1, -1));

        btg1.add(radTyLe);
        radTyLe.setText("Tỷ lệ");
        radTyLe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radTyLeActionPerformed(evt);
            }
        });
        jPanel2.add(radTyLe, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        btg1.add(radGiaTien);
        radGiaTien.setSelected(true);
        radGiaTien.setText("Giá tiền");
        radGiaTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radGiaTienActionPerformed(evt);
            }
        });
        jPanel2.add(radGiaTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        lblViTri1.setText("Số tiền giảm");
        jPanel2.add(lblViTri1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, -1));

        btg1.add(radSanPham);
        radSanPham.setText("Sản phẩm");
        radSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radSanPhamActionPerformed(evt);
            }
        });
        jPanel2.add(radSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        lblViTri3.setText("jLabel4");
        jPanel2.add(lblViTri3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 140, -1));

        txtGiaTien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaTien1ActionPerformed(evt);
            }
        });
        jPanel2.add(txtGiaTien1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 160, -1));
        jPanel2.add(txtGiaTien2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 160, -1));
        jPanel2.add(txtTyLe1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 160, -1));

        txtSanPham1.setText("textFieldSuggestion2");
        jPanel2.add(txtSanPham1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 16, 350, 50));

        lblViTri4.setText("jLabel3");
        jPanel2.add(lblViTri4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 140, -1));
        jPanel2.add(txtTyLe4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, 160, -1));
        jPanel2.add(txtGiaTien3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 160, -1));

        btg2.add(radGiaTienSanPham);
        radGiaTienSanPham.setSelected(true);
        radGiaTienSanPham.setText("Giá tiền");
        radGiaTienSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radGiaTienSanPhamActionPerformed(evt);
            }
        });
        jPanel2.add(radGiaTienSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, -1, -1));

        btg2.add(radTyLeSanPham);
        radTyLeSanPham.setText("Tỷ lệ");
        radTyLeSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radTyLeSanPhamActionPerformed(evt);
            }
        });
        jPanel2.add(radTyLeSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, -1, -1));
        jPanel2.add(txtSanPham3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 160, -1));
        jPanel2.add(txtSanPham4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, 160, -1));
        jPanel2.add(txtTyLe2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 160, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        scrDanhSachSanPhamTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scrDanhSachSanPhamTimKiemMouseClicked(evt);
            }
        });

        tblDanhSachKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Khuyến mãi", "Tên Khuyến mãi", "Ngày bắt đầu", "Ngày kết thúc", "Tình trạng", "Số lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDanhSachKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachKhuyenMaiMouseClicked(evt);
            }
        });
        scrDanhSachSanPhamTimKiem.setViewportView(tblDanhSachKhuyenMai);

        jButton1.setText("Thêm");

        jButton2.setText("Cập nhật ");

        jButton3.setText("Làm mới");

        btnNgayBatDau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/iconAnhLich.png"))); // NOI18N
        btnNgayBatDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgayBatDauActionPerformed(evt);
            }
        });

        btnNgayKetThuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/iconAnhLich.png"))); // NOI18N
        btnNgayKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgayKetThucActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblmaKhuyenMai)
                            .addComponent(lblTenKhuyenMai))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaKhuyenMaiKyTu, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(525, 525, 525)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrDanhSachSanPhamTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 1381, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addGap(71, 71, 71)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addGap(55, 55, 55)
                                        .addComponent(jButton2)
                                        .addGap(56, 56, 56)
                                        .addComponent(jButton3))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(50, 50, 50)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaKhuyenMai)
                    .addComponent(lblMaKhuyenMaiKyTu, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnNgayBatDau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11)
                                        .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnNgayKetThuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3))))
                .addGap(18, 18, 18)
                .addComponent(scrDanhSachSanPhamTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblDanhSachKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachKhuyenMaiMouseClicked
        // TODO add your handling code here:
         int row = tblDanhSachKhuyenMai.getSelectedRow();
        if(radGiaTien.isSelected()){
           
           
            khuyenMaiThanhToan_DAO = new KhuyenMaiThanhToan_DAO();
            ArrayList<KhuyenMaiThanhToan> dsKhuyenMaiThanhToan = khuyenMaiThanhToan_DAO.layDanhSachKhuyenMai_GiaTien();
            String ma = tblDanhSachKhuyenMai.getValueAt(row, 0).toString();
            for (KhuyenMaiThanhToan khuyenMaiThanhToan : dsKhuyenMaiThanhToan) {
                if(khuyenMaiThanhToan.getMaKhuyenMai().equals(ma)){
                    lblMaKhuyenMaiKyTu.setText(ma);
                    txtTenKhuyenMai.setText(khuyenMaiThanhToan.getTenKhuyenMai());
                    txtGiaTien1.setText(khuyenMaiThanhToan.getSoTienGiam()+"");
                    txtGiaTien2.setText(khuyenMaiThanhToan.getGiaTriToiThieuDonHang()+"");
                    txtGiaTien3.setText(khuyenMaiThanhToan.getSoLuong()+"");
                    int namBatDau= Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 2).toString().substring(0,4));
                    int thangBatDau = Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 2).toString().substring(5,7));
                    int ngayBatDau = Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 2).toString().substring(8,10));
                    dateNgayBatDau.setSelectedDate(new SelectedDate(ngayBatDau,thangBatDau,namBatDau));
                    
                    int namKetThuc = Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 3).toString().substring(0,4));
                    int thangKetThuc = Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 3).toString().substring(5,7));
                    int ngayKetThuc = Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 3).toString().substring(8,10));
                    dateNgayKetThuc.setSelectedDate(new SelectedDate(ngayKetThuc,thangKetThuc,namKetThuc));
                    
                }
            }
        }else if(radTyLe.isSelected()){
           
            
            khuyenMaiThanhToan_DAO = new KhuyenMaiThanhToan_DAO();
            ArrayList<KhuyenMaiThanhToan> dsKhuyenMaiThanhToan = khuyenMaiThanhToan_DAO.layDanhSachKhuyenMai_TyLe();
            String ma = tblDanhSachKhuyenMai.getValueAt(row, 0).toString();
            for (KhuyenMaiThanhToan khuyenMaiThanhToan : dsKhuyenMaiThanhToan) {
                 if(khuyenMaiThanhToan.getMaKhuyenMai().equals(ma)){
                     lblMaKhuyenMaiKyTu.setText(ma);
                     txtTenKhuyenMai.setText(khuyenMaiThanhToan.getTenKhuyenMai());
                     txtTyLe1.setText(khuyenMaiThanhToan.getPhanTramGiam()+" %");
                     txtTyLe2.setText(khuyenMaiThanhToan.getGiaTriToiThieuDonHang()+"");
                     if(khuyenMaiThanhToan.getGiamToiDa() == 0.0){
                         txtTyLe3.setText("Không giới hạn");
                     }
                     txtTyLe4.setText(khuyenMaiThanhToan.getSoLuong()+"");
                 }
             }
        }else{
            giamGiaSanPham_DAO = new GiamGiaSanPham_DAO();
            ArrayList<GiamGiaSanPham> dsGiamGia = giamGiaSanPham_DAO.layDanhSachGiamGiaSanPham();
            String ma = tblDanhSachKhuyenMai.getValueAt(row, 0).toString();
            for (GiamGiaSanPham giamGiaSanPham : dsGiamGia) {
                lblMaKhuyenMaiKyTu.setText(ma);
                txtTenKhuyenMai.setText(giamGiaSanPham.getTenGiamGia());
                txtSanPham1.setText(tblDanhSachKhuyenMai.getValueAt(row, 2)+"");
                if(giamGiaSanPham.getLoai() == 1){
                    radGiaTienSanPham.setSelected(true);
                    txtSanPham3.setText(giamGiaSanPham.getSoTienGiam()+"");
                     isSelected(txtSanPham3,txtSanPham4);
                }else{
                    radTyLeSanPham.setSelected(true);
                    txtSanPham4.setText(giamGiaSanPham.getTyLeGiam()+"");
                     isSelected(txtSanPham4,txtSanPham3);
                }
            }
            
        }
    }//GEN-LAST:event_tblDanhSachKhuyenMaiMouseClicked

    private void radTyLeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radTyLeActionPerformed
        // TODO add your handling code here:
        lblViTri1.setText("Phần trăm giảm");
        lblViTri2.setText("Giá trị tối thiểu đơn hàng");
        
        txtGiaTien1.setVisible(false);
        txtGiaTien2.setVisible(false);
        
        txtSanPham1.setVisible(false);
       
        txtSanPham3.setVisible(false);
        txtSanPham4.setVisible(false);
         radGiaTienSanPham.setVisible(false);
        radTyLeSanPham.setVisible(false);
       
        
        txtTyLe1.setVisible(true);
        txtTyLe2.setVisible(true);
        txtTyLe3.setVisible(true);
        txtTyLe4.setVisible(true);
        
        lblViTri2.setVisible(true);
        lblViTri3.setVisible(true);
        lblViTri4.setVisible(true);
        lblViTri3.setText("Giảm tối đa");
        lblViTri4.setText("Số lượng");
        
        danhSachKhuyenMai_TyLe();
        lamMoiDuLieu_TyLe();
    }//GEN-LAST:event_radTyLeActionPerformed
    public void lamMoiDuLieu_TyLe(){
        maHoaDon_TyLe(lblMaKhuyenMaiKyTu);
        txtTenKhuyenMai.setText("");
        txtTyLe1.setText("");
        txtTyLe2.setText("");
        txtTyLe3.setText("");
        txtTyLe4.setText("");

    }
    public void maHoaDon_TyLe(JLabel lbl){
        khuyenMaiThanhToan_DAO = new KhuyenMaiThanhToan_DAO();
        try {
            lbl.setText( khuyenMaiThanhToan_DAO.generateMaKhuyenMai_TyLe());
            
        } catch (SQLException ex) {
            Logger.getLogger(pnlKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void radGiaTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radGiaTienActionPerformed
        // TODO add your handling code here:
        giaTienRad();
        lamMoiDuLieu_GiaTri();
    }//GEN-LAST:event_radGiaTienActionPerformed
    public void lamMoiDuLieu_GiaTri(){
         txtTenKhuyenMai.setText("");
        maHoaDon_GiaTri(lblMaKhuyenMaiKyTu);
        txtGiaTien1.setText("");
        txtGiaTien2.setText("");                        
        txtGiaTien3.setText("");
    }
    public void maHoaDon_GiaTri(JLabel lbl){
        khuyenMaiThanhToan_DAO = new KhuyenMaiThanhToan_DAO();
        try {
           lbl.setText( khuyenMaiThanhToan_DAO.generateMaKhuyenMai_GiaTri());
            
        } catch (SQLException ex) {
            Logger.getLogger(pnlKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void giaTienRad(){
        lblViTri1.setText("Số tiền giảm");
        lblViTri2.setText("Giá trị tối thiểu đơn hàng");
        
        lblViTri2.setVisible(true);
        lblViTri3.setVisible(true);
        lblViTri3.setText("Số lượng");
        
        lblViTri4.setVisible(false);
        
        txtSanPham1.setVisible(false);
      
        txtSanPham3.setVisible(false);
        txtSanPham4.setVisible(false);
        radGiaTienSanPham.setVisible(false);
        radTyLeSanPham.setVisible(false);
        
        txtTyLe1.setVisible(false);
        txtTyLe2.setVisible(false);
        txtTyLe3.setVisible(false);
        txtTyLe4.setVisible(false);
         
        txtGiaTien1.setVisible(true);
        txtGiaTien2.setVisible(true);
        txtGiaTien3.setVisible(true);
        
        
        danhSachKhuyenMai_GiaTien();
    }
    private void radSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radSanPhamActionPerformed
        // TODO add your handling code here:
       
        lblViTri1.setText("Tên sản phẩm");
        
        lblViTri2.setVisible(false);
        lblViTri3.setVisible(false);
        lblViTri4.setVisible(false);
        
        txtSanPham1.setVisible(true);
       
        
        txtSanPham3.setVisible(true);
        txtSanPham4.setVisible(true);
        txtSanPham4.setEnabled(false);
        
        radGiaTienSanPham.setVisible(true);
        radTyLeSanPham.setVisible(true);
        
        txtTyLe1.setVisible(false);
        txtTyLe2.setVisible(false);
        txtTyLe3.setVisible(false);
        txtTyLe4.setVisible(false);
        
        txtGiaTien1.setVisible(false);
        txtGiaTien2.setVisible(false);
        txtGiaTien3.setVisible(false);
        danhSachGiamGia();
        txtTenKhuyenMai.setText("");
        lamMoi_sanPham();
        
    }//GEN-LAST:event_radSanPhamActionPerformed
    public void lamMoi_sanPham(){
        maHoaDon_SanPham(lblMaKhuyenMaiKyTu);
        
        txtSanPham1.setText("");
        txtSanPham3.setText("");
        txtSanPham4.setText("");
    }
    public void maHoaDon_SanPham(JLabel lbl){
        giamGiaSanPham_DAO = new GiamGiaSanPham_DAO();
        try {
            if(radGiaTienSanPham.isSelected()){
                lbl.setText( giamGiaSanPham_DAO.generateGiamGiaSanPham_GiaTien());
            }else{
                lbl.setText( giamGiaSanPham_DAO.generateGiamGiaSanPham_TyLe());
            }
          
            
        } catch (SQLException ex) {
            Logger.getLogger(pnlKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void txtGiaTien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaTien1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaTien1ActionPerformed

    private void scrDanhSachSanPhamTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scrDanhSachSanPhamTimKiemMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_scrDanhSachSanPhamTimKiemMouseClicked

    private void radGiaTienSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radGiaTienSanPhamActionPerformed
        // TODO add your handling code here:
    isSelected(txtSanPham3,txtSanPham4);
        lamMoi_sanPham();
    }//GEN-LAST:event_radGiaTienSanPhamActionPerformed
    public void isSelected(JTextField txt1, JTextField txt2){
        txt1.setVisible(true);
        txt1.setEditable(true);
        txt1.setEnabled(true);
        
        txt2.setEnabled(false);
        txt2.setText("");
    }
    private void radTyLeSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radTyLeSanPhamActionPerformed
        // TODO add your handling code here:
        isSelected(txtSanPham4,txtSanPham3);
        lamMoi_sanPham();
    }//GEN-LAST:event_radTyLeSanPhamActionPerformed
    
    private void btnNgayBatDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgayBatDauActionPerformed
        // TODO add your handling code here:
        dateNgayBatDau.showPopup();
    }//GEN-LAST:event_btnNgayBatDauActionPerformed

    private void btnNgayKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgayKetThucActionPerformed
        // TODO add your handling code here:
        dateNgayKetThuc.showPopup();
    }//GEN-LAST:event_btnNgayKetThucActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_formMouseClicked

    private void dateNgayKetThucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateNgayKetThucMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_dateNgayKetThucMouseClicked
    
    private void dateNgayBatDauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateNgayBatDauMouseClicked
        // TODO add your handling code here:
        System.out.println("Pannel.pnlKhuyenMai.dateNgayBatDauMouseClicked()");
    }//GEN-LAST:event_dateNgayBatDauMouseClicked

    private void txtNgayBatDauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNgayBatDauMouseClicked
        // TODO add your handling code here:
        System.out.println("Pannel.pnlKhuyenMai.txtNgayBatDauMouseClicked()");
    }//GEN-LAST:event_txtNgayBatDauMouseClicked

    private void txtNgayBatDauFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNgayBatDauFocusLost
        // TODO add your handling code here:
        System.out.println("Pannel.pnlKhuyenMai.txtNgayBatDauFocusLost()");
    }//GEN-LAST:event_txtNgayBatDauFocusLost

    private void txtNgayBatDauFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNgayBatDauFocusGained
        // TODO add your handling code here:
        System.out.println("Pannel.pnlKhuyenMai.txtNgayBatDauFocusGained()");
    }//GEN-LAST:event_txtNgayBatDauFocusGained

    private void dateNgayBatDauMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateNgayBatDauMouseExited
        // TODO add your handling code here:
        System.out.println("Pannel.pnlKhuyenMai.dateNgayBatDauMouseExited()");
    }//GEN-LAST:event_dateNgayBatDauMouseExited

    private void dateNgayBatDauMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateNgayBatDauMouseEntered
        // TODO add your handling code here:
        //System.out.println("Pannel.pnlKhuyenMai.dateNgayBatDauMouseEntered()");
    }//GEN-LAST:event_dateNgayBatDauMouseEntered

    private void dateNgayBatDauMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateNgayBatDauMousePressed
        // TODO add your handling code here:
        System.out.println("Pannel.pnlKhuyenMai.dateNgayBatDauMousePressed()");
    }//GEN-LAST:event_dateNgayBatDauMousePressed

    private void dateNgayBatDauFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateNgayBatDauFocusGained
        // TODO add your handling code here:
        System.out.println("Pannel.pnlKhuyenMai.dateNgayBatDauFocusGained()");
    }//GEN-LAST:event_dateNgayBatDauFocusGained

    private void dateNgayBatDauMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateNgayBatDauMouseReleased
        // TODO add your handling code here:
        System.out.println("Pannel.pnlKhuyenMai.dateNgayBatDauMouseReleased()");
    }//GEN-LAST:event_dateNgayBatDauMouseReleased
    
    public void danhSachGiamGia(){
        String colTieuDe[] = new String [] {"Mã Khuyến mãi", "Tên Khuyến mãi","Tên sản phẩm", "Ngày bắt đầu", "Ngày kết thúc", "Tình trạng"};
        DefaultTableModel model =  new DefaultTableModel(colTieuDe,0);
        Object[] row;
        giamGiaSanPham_DAO = new GiamGiaSanPham_DAO();
        
        
        ArrayList<Sach> dssach = sach_DAO.layDanhSanPhamSach();
        ArrayList<VanPhongPham> dsvpp = vanPhongPham_DAO.layDanhSanPhamVanPhongPham();
         // Tạo một ArrayList mới để chứa cả hai danh sách
        ArrayList<Object> combinedList = new ArrayList<>();

        // Thêm danh sách sách vào danh sách kết hợp
        combinedList.addAll(dssach);

        // Thêm danh sách văn phòng phẩm vào danh sách kết hợp
        combinedList.addAll(dsvpp);
        
       
        ArrayList<GiamGiaSanPham> dsGiamGiaSanPham = giamGiaSanPham_DAO.layDanhSachGiamGiaSanPham();
        for (GiamGiaSanPham giamGiaSanPham : dsGiamGiaSanPham) {
           row = new Object[12];
              row[0] =     giamGiaSanPham.getMaGiamGiaSanPham();
              row[1] =  giamGiaSanPham.getTenGiamGia();
              
                for (Object item : combinedList) {
               
                
                if (item instanceof Sach) {
                       Sach sach = (Sach) item;
                       if(sach.getMaSanPham().equals(giamGiaSanPham.getMaGiamGiaSanPham()));
                       row[2] = sach.getTenSanPham();
                   } else if (item instanceof VanPhongPham) {
                       VanPhongPham vpp = (VanPhongPham) item;
                       if(vpp.getMaSanPham().equals(giamGiaSanPham.getMaGiamGiaSanPham()));
                       row[2] = vpp.getTenSanPham();
                       // Thực hiện các thao tác với đối tượng VanPhongPham
                   }
               }
              
              row[3] =  giamGiaSanPham.getNgayBatDau();
              row[4] =  giamGiaSanPham.getNgayKetThuc();
              row[5] =  giamGiaSanPham.getTinhTrang();
              model.addRow(row);
        }
        tblDanhSachKhuyenMai.setModel(model);
    }
    public void danhSachKhuyenMai_TyLe(){
        
        String colTieuDe[] = new String [] {"Mã Khuyến mãi", "Tên Khuyến mãi", "Ngày bắt đầu", "Ngày kết thúc", "Tình trạng", "Số lượng"};
        DefaultTableModel model =  new DefaultTableModel(colTieuDe,0);
        Object[] row;
        khuyenMaiThanhToan_DAO = new KhuyenMaiThanhToan_DAO();
        ArrayList<KhuyenMaiThanhToan> dsKhuyenMaiThanhToan = khuyenMaiThanhToan_DAO.layDanhSachKhuyenMai_TyLe();
        for (KhuyenMaiThanhToan khuyenMaiThanhToan : dsKhuyenMaiThanhToan) {
              row = new Object[12];
              row[0] = khuyenMaiThanhToan.getMaKhuyenMai();
              row[1] = khuyenMaiThanhToan.getTenKhuyenMai();
              row[2] = khuyenMaiThanhToan.getNgayBatDau();
              row[3] = khuyenMaiThanhToan.getNgayKetThuc();
              row[4] = khuyenMaiThanhToan.getTinhTrang();
              row[5] = khuyenMaiThanhToan.getSoLuong();
              model.addRow(row);
              
        }
        tblDanhSachKhuyenMai.setModel(model);
    }
    public void danhSachKhuyenMai_GiaTien(){
        
        String colTieuDe[] = new String [] {"Mã Khuyến mãi", "Tên Khuyến mãi", "Ngày bắt đầu", "Ngày kết thúc", "Tình trạng", "Số lượng"};
        DefaultTableModel model =  new DefaultTableModel(colTieuDe,0);
        Object[] row;
        khuyenMaiThanhToan_DAO = new KhuyenMaiThanhToan_DAO();
        ArrayList<KhuyenMaiThanhToan> dsKhuyenMaiThanhToan = khuyenMaiThanhToan_DAO.layDanhSachKhuyenMai_GiaTien();
        for (KhuyenMaiThanhToan khuyenMaiThanhToan : dsKhuyenMaiThanhToan) {
              row = new Object[12];
              row[0] = khuyenMaiThanhToan.getMaKhuyenMai();
              row[1] = khuyenMaiThanhToan.getTenKhuyenMai();
              row[2] = khuyenMaiThanhToan.getNgayBatDau();
              row[3] = khuyenMaiThanhToan.getNgayKetThuc();
              row[4] = khuyenMaiThanhToan.getTinhTrang();
              row[5] = khuyenMaiThanhToan.getSoLuong();
              model.addRow(row);
              
        }
        tblDanhSachKhuyenMai.setModel(model);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btg1;
    private javax.swing.ButtonGroup btg2;
    private javax.swing.JButton btnNgayBatDau;
    private javax.swing.JButton btnNgayKetThuc;
    private ServiceUser.DateChooser dateNgayBatDau;
    private ServiceUser.DateChooser dateNgayKetThuc;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblMaKhuyenMaiKyTu;
    private javax.swing.JLabel lblTenKhuyenMai;
    private javax.swing.JLabel lblViTri1;
    private javax.swing.JLabel lblViTri2;
    private javax.swing.JLabel lblViTri3;
    private javax.swing.JLabel lblViTri4;
    private javax.swing.JLabel lblmaKhuyenMai;
    private javax.swing.JRadioButton radGiaTien;
    private javax.swing.JRadioButton radGiaTienSanPham;
    private javax.swing.JRadioButton radSanPham;
    private javax.swing.JRadioButton radTyLe;
    private javax.swing.JRadioButton radTyLeSanPham;
    private javax.swing.JScrollPane scrDanhSachSanPhamTimKiem;
    private javax.swing.JTable tblDanhSachKhuyenMai;
    private javax.swing.JTextField txtGiaTien1;
    private javax.swing.JTextField txtGiaTien2;
    private javax.swing.JTextField txtGiaTien3;
    private javax.swing.JTextField txtNgayBatDau;
    private javax.swing.JTextField txtNgayKetThuc;
    private ServiceUser.TextFieldSuggestion txtSanPham1;
    private javax.swing.JTextField txtSanPham3;
    private javax.swing.JTextField txtSanPham4;
    private javax.swing.JTextField txtTenKhuyenMai;
    private javax.swing.JTextField txtTyLe1;
    private javax.swing.JTextField txtTyLe2;
    private javax.swing.JTextField txtTyLe3;
    private javax.swing.JTextField txtTyLe4;
    // End of variables declaration//GEN-END:variables
}
