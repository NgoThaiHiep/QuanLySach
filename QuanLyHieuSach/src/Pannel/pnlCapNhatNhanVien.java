
package Pannel;

import ConnectDB.ConnectDB;
import DAO.NhanVien_DAO;
import DAO.TaiKhoan_DAO;
import Entity.CaLamViec;
import Entity.ChucVu;
import Entity.NhanVien;
import Entity.TaiKhoan;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ServiceUser.SelectedDate;
import java.awt.Dialog.ModalityType;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.JDialog;
/**
 *
 * @author FPTSHOP
 */
public class pnlCapNhatNhanVien extends javax.swing.JPanel {

    /**
     * Creates new form ThemNhanVien
     */
    
    private String tp = "Tỉnh/Thành phố";
    private String q = "Quận/huyện";
    private String cboDiaChi = "";
    private ArrayList<String> cities ;
    private ArrayList<String> districts ;
    private ArrayList<String> wardsDistricts ;
    private TaiKhoan tk;
    private NhanVien_DAO nhanVien_DAO;
    private File selectedFile;
    private JFrame imageFrame;
    private NhanVien nv;
    private TaiKhoan_DAO taiKhoan_DAO;
    public pnlCapNhatNhanVien(TaiKhoan tk) throws IOException, SQLException {
        this.tk = tk;
        initComponents();
        init();
        lblCaLam.setVisible(false);
        cboCaLamViec.setVisible(false);
        
    }
        
    public void init() throws IOException, SQLException{
        try {
            ConnectDB.getInstance().connect();
	}catch (SQLException e) {
	// TODO: handle exception
            e.printStackTrace();
	}
          
        cities = readExcel_City();
          for (String city : cities) {
            cboTinhThanhPho.addItem(city);
        }
          txtMaNhanVien.setEnabled(false);
//          nhanVien_DAO = new NhanVien_DAO();
//          txtMaNhanVien.setText(nhanVien_DAO.generateVerifyCode());
//          System.out.println("mã nhân viên"+nhanVien_DAO.generateVerifyCode());
          
            txtTenNhanVien.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = txtTenNhanVien.getText();
                String formattedText = vietHoaChuCaiDauTienTrongJtextField(text);
                txtTenNhanVien.setText(formattedText);
            }
        });
        
                // Tạo một DocumentFilter để kiểm tra và lọc ký tự
        AbstractDocument document = (AbstractDocument) txtTenNhanVien.getDocument();
        document.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                // Chỉ cho phép chữ cái, mã UTF-8, và có đúng một dấu cách giữa các từ
                if (string == null)
                    return;

                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = currentText.substring(0, offset) + string + currentText.substring(offset);
                if (isValidText(newText)) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                // Chỉ cho phép chữ cái, mã UTF-8, và có đúng một dấu cách giữa các từ
                if (text == null)
                    return;

                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = currentText.substring(0, offset) + text + currentText.substring(offset + length);
                if (isValidText(newText)) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

            private boolean isValidText(String text) {
                if (text.isEmpty() || text.startsWith(" ")) {
                    return false; // Ký tự đầu tiên không được là dấu cách
                }

                String[] words = text.split(" ");
                if (words.length < 1) {
                    return false; // Phải có ít nhất một từ
                }

                for (String word : words) {
                    if (!Pattern.matches("^[\\p{L} ]*$", word)) {
                        return false; // Chỉ mã UTF-8 và dấu cách được chấp nhận
                    }
                }

                return true;
            }
        });
        
        duLieuCCCD();
        DuLieuEmail();
        duLieuSDT();
        
        
        // Đặt ảnh vào JLabel và thiết lập kích thước bằng với JLabel
        try {
            selectedFile =new File("src\\IMG\\anhMacDinhNhanVien.png");
            BufferedImage image = ImageIO.read(selectedFile); // Thay đổi đường dẫn đến ảnh
            
            // thay đổi kích thức ảnh cùng kích thước với lable 184x216
             Image scaledImage = image.getScaledInstance(184, 216, Image.SCALE_SMOOTH);

                    // Tạo ImageIcon với ảnh đã điều chỉnh kích thước
                    ImageIcon imageIcon = new ImageIcon(scaledImage);

                    // Thiết lập ImageIcon cho JLabel
                    lblAnhNhanVien.setIcon(imageIcon);
           
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        date.toDay();
        SelectedDate day = date.getSelectedDate();
        
       
        
        date.setSelectedDate(new SelectedDate(day.getDay(),day.getMonth(),day.getYear() - 18) );
                        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        date = new ServiceUser.DateChooser();
        jPanel2 = new javax.swing.JPanel();
        lblTimKiem = new javax.swing.JLabel();
        lblChucVuTimKiem = new javax.swing.JLabel();
        cboChucVuTimKiem = new javax.swing.JComboBox<>();
        lblTrangThaiTimKiem = new javax.swing.JLabel();
        cboTrangThaiTimKiem = new javax.swing.JComboBox<>();
        txtTimKiem = new ServiceUser.TextFieldSuggestion();
        jPanel1 = new javax.swing.JPanel();
        cboTinhThanhPho = new javax.swing.JComboBox<>();
        cboQuanHuyen = new javax.swing.JComboBox<>();
        cboPhuongXa = new javax.swing.JComboBox<>();
        lblMaNhanVien = new javax.swing.JLabel();
        lblTenNhanVien = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        lblSoDienThoai = new javax.swing.JLabel();
        lblCCCD = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        lblNgaySinh = new javax.swing.JLabel();
        lblChucVu = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtCCCD = new javax.swing.JTextField();
        btnChonNgaySinh = new javax.swing.JButton();
        cboChucVu = new javax.swing.JComboBox<>();
        cboGioiTinh = new javax.swing.JComboBox<>();
        txtEmail = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        txtTenNhanVien = new javax.swing.JTextField();
        txtMaNhanVien = new javax.swing.JTextField();
        lblTrangThai = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        cboTrangThai = new javax.swing.JComboBox<>();
        lblAnhNhanVien = new javax.swing.JLabel();
        btnChonAnh = new javax.swing.JButton();
        lblCaLam = new javax.swing.JLabel();
        cboCaLamViec = new javax.swing.JComboBox<>();
        lblThemNhanVien = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnLamMoiThongTinNhanVien = new javax.swing.JButton();
        btnCapNhatThongTinNhanVien = new javax.swing.JButton();
        btnLamMoiBang = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        date.setTextRefernce(txtNgaySinh);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N

        lblTimKiem.setText("Tìm kiếm");

        lblChucVuTimKiem.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblChucVuTimKiem.setText("Chức vụ");

        cboChucVuTimKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản lý", "Nhân viên" }));

        lblTrangThaiTimKiem.setText("Trạng thái");

        cboTrangThaiTimKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang làm", "Tạm nghỉ", "Đã nghỉ" }));

        txtTimKiem.setText("Tìm kiếm theo mã nhân viên, tên nhân viên");
        txtTimKiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimKiemFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimKiemFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblChucVuTimKiem)
                .addGap(18, 18, 18)
                .addComponent(cboChucVuTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(lblTrangThaiTimKiem)
                .addGap(18, 18, 18)
                .addComponent(cboTrangThaiTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTimKiem)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblChucVuTimKiem)
                    .addComponent(cboChucVuTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTrangThaiTimKiem)
                    .addComponent(cboTrangThaiTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12))); // NOI18N

        cboTinhThanhPho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTinhThanhPhoActionPerformed(evt);
            }
        });

        cboQuanHuyen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboQuanHuyenActionPerformed(evt);
            }
        });

        lblMaNhanVien.setText("Mã nhân viên");

        lblTenNhanVien.setText("Tên nhân viên");

        lblGioiTinh.setText("Giới tính");

        lblSoDienThoai.setText("Số điện thoại");

        lblCCCD.setText("CCCD");

        lblDiaChi.setText("Địa chỉ");

        lblNgaySinh.setText("Ngày sinh");

        lblChucVu.setText("Chức vụ");

        lblEmail.setText("Email");

        txtCCCD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtCCCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCCCDActionPerformed(evt);
            }
        });

        btnChonNgaySinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/anhLich.png"))); // NOI18N
        btnChonNgaySinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonNgaySinhActionPerformed(evt);
            }
        });

        cboChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản lý", "Nhân viên" }));
        cboChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChucVuActionPerformed(evt);
            }
        });

        cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        cboGioiTinh.setMinimumSize(new java.awt.Dimension(64, 18));
        cboGioiTinh.setPreferredSize(new java.awt.Dimension(64, 18));
        cboGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGioiTinhActionPerformed(evt);
            }
        });

        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtSoDienThoai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtTenNhanVien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtMaNhanVien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtMaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNhanVienActionPerformed(evt);
            }
        });

        lblTrangThai.setText("Trạng thái");

        txtNgaySinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgaySinhActionPerformed(evt);
            }
        });

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang làm", "Tạm nghỉ", "Đã nghỉ" }));
        cboTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiActionPerformed(evt);
            }
        });

        lblAnhNhanVien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblAnhNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhNhanVienMouseClicked(evt);
            }
        });

        btnChonAnh.setText("Chọn ảnh");
        btnChonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhActionPerformed(evt);
            }
        });

        lblCaLam.setText("Ca làm");

        cboCaLamViec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ca 1", "Ca 2" }));
        cboCaLamViec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCaLamViecActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(btnChonAnh))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lblAnhNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSoDienThoai)
                    .addComponent(lblMaNhanVien)
                    .addComponent(lblCCCD)
                    .addComponent(lblEmail)
                    .addComponent(lblDiaChi))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cboTinhThanhPho, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(cboQuanHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCCCD, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                            .addComponent(txtMaNhanVien)
                            .addComponent(txtSoDienThoai))
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTenNhanVien)
                            .addComponent(lblGioiTinh)
                            .addComponent(lblNgaySinh)
                            .addComponent(lblTrangThai))
                        .addGap(18, 20, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnChonNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblChucVu))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblCaLam)))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboCaLamViec, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(23, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(cboPhuongXa, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblMaNhanVien)
                                    .addComponent(lblTenNhanVien)
                                    .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNgaySinh)
                                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCCCD)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnChonNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblSoDienThoai)
                                .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cboGioiTinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblGioiTinh)
                                    .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblChucVu))))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTrangThai)
                            .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmail)
                            .addComponent(lblCaLam)
                            .addComponent(cboCaLamViec, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblAnhNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChonAnh)
                    .addComponent(lblDiaChi)
                    .addComponent(cboTinhThanhPho, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboQuanHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboPhuongXa, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblThemNhanVien.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblThemNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThemNhanVien.setText("Cập nhật thông tin nhân viên");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tác vụ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N

        btnLamMoiThongTinNhanVien.setText("Làm mới thông tin");
        btnLamMoiThongTinNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiThongTinNhanVienActionPerformed(evt);
            }
        });

        btnCapNhatThongTinNhanVien.setText("Thêm nhân viên");
        btnCapNhatThongTinNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatThongTinNhanVienActionPerformed(evt);
            }
        });

        btnLamMoiBang.setText("Làm mới bảng");
        btnLamMoiBang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiBangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCapNhatThongTinNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiThongTinNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiBang, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btnCapNhatThongTinNhanVien)
                .addGap(37, 37, 37)
                .addComponent(btnLamMoiThongTinNhanVien)
                .addGap(38, 38, 38)
                .addComponent(btnLamMoiBang)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "CCCD", "Ngày sinh", "Giới tính", "Số điện thoại", "Email", "Địa chỉ", "Trạng thái", "Hình ảnh", "Chức vụ", "Ca làm"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblThemNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblThemNhanVien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboTinhThanhPhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTinhThanhPhoActionPerformed
        // TODO add your handling code here:
         // TODO add your handling code here:
        if(!tp.equals(cboTinhThanhPho.getSelectedItem()+"")||cboTinhThanhPho.getSelectedItem().equals("Tỉnh/Thành phố")){
            q = cboTinhThanhPho.getSelectedItem()+"";
            //System.out.println(provinceComboBox.getSelectedItem());
            if(cboTinhThanhPho.getSelectedItem().equals("Tỉnh/Thành phố")){

                cboQuanHuyen.removeAllItems();
                cboQuanHuyen.addItem("Quận/huyện");
                cboPhuongXa.removeAllItems();
                cboPhuongXa.addItem("Phường/xã");
                cboQuanHuyen.setEnabled(false);
                cboPhuongXa.setEnabled(false);

                System.out.println("1223 "+"Tỉnh/Thành phố");
            }else{

                try {
                    districts = readExcel_districts(cboTinhThanhPho,cboQuanHuyen,cboPhuongXa,tp);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                cboQuanHuyen.removeAllItems();
                cboQuanHuyen.addItem("Quận/huyện");
                for (String district : districts) {
                    cboQuanHuyen.addItem(district);
                }
                cboPhuongXa.setEnabled(true);
                try {
                    wardsDistricts = readExcel_wardsDistrict(cboQuanHuyen,cboPhuongXa);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                cboPhuongXa.removeAllItems();
                cboPhuongXa.addItem("Phường/xã");
                for (String wad : wardsDistricts) {
                    cboPhuongXa.addItem(wad);
                }
            }
        }
        tp = cboTinhThanhPho.getSelectedItem()+"";
    }//GEN-LAST:event_cboTinhThanhPhoActionPerformed

    private void cboQuanHuyenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboQuanHuyenActionPerformed
        // TODO add your handling code here:
        String t = cboQuanHuyen.getSelectedItem()+"";

        cboQuanHuyen.addItem(q);
        // System.out.println(districtComboBox.getSelectedItem()+"");
        if( q == "Tỉnh/Thành phố"){

            //wardComboBox.removeAllItems();
            //wardComboBox.addItem("Phường/xã");
            // wardComboBox.setEnabled(true);
            // updateWardComboBox();
            // q = districtComboBox.getSelectedItem()+"";
            cboPhuongXa.setEnabled(false);
            cboPhuongXa.removeAllItems();
            cboPhuongXa.addItem("Phường/xã");

        }else{
            cboPhuongXa.setEnabled(true);
            try {
                wardsDistricts = readExcel_wardsDistrict(cboQuanHuyen,cboPhuongXa);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(!cboDiaChi.equals(cboQuanHuyen.getSelectedItem())){
                cboPhuongXa.removeAllItems();
                cboPhuongXa.addItem("Phường/xã");
                for (String wad : wardsDistricts) {
                    cboPhuongXa.addItem(wad);
                }
            }
        }
        cboDiaChi = cboQuanHuyen.getSelectedItem()+"";
        cboQuanHuyen.removeItem(q);
        
    }//GEN-LAST:event_cboQuanHuyenActionPerformed

    private void btnCapNhatThongTinNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatThongTinNhanVienActionPerformed
        // TODO add your handling code here:
        if(cboTinhThanhPho.getSelectedItem().equals("Tỉnh/Thành phố")){
           
                if(cboPhuongXa.getSelectedItem().equals("Phường/xã") ){
                    
                }
              if(cboPhuongXa.getSelectedItem().equals("Phường/xã") ){

            }
        }
        
        // NhanVien(String maNV, String hoTenNhanVien, LocalDate ngaySinh, String soDienThoai, boolean gioiTinh, String email, ChucVu chucVu, TaiKhoan taiKhoan, CaLamViec caLam, String trangThai, String hinhAnh);
         
        String maNhanVien = txtMaNhanVien.getText();
        String tenNhanVieninput = txtTenNhanVien.getText().trim();
        String tenNhanVien = tenNhanVieninput.replaceAll("\\s+", " ");
        String cccd = txtCCCD.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        String ngay = date.getSelectedDate().getYear() +"-"+date.getSelectedDate().getMonth()+"-"+ date.getSelectedDate().getDay();
        LocalDate ngaySinh = LocalDate.parse(ngay, formatter);
       
        boolean gioiTinh = true;
        if(!cboGioiTinh.getSelectedItem().equals("Nam")){
            gioiTinh = false;
        }
        String email = txtEmail.getText();
        String soDienThoai = txtSoDienThoai.getText();
        String diaChi = cboTinhThanhPho.getSelectedItem() +"-"+cboQuanHuyen.getSelectedItem()+"-"+cboPhuongXa.getSelectedItem();
        String chucVu = "QL";
        if(!cboChucVu.getSelectedItem().equals("Quản lý")){
            chucVu = "NV";
        }
        ChucVu cv = new ChucVu(chucVu);
        CaLamViec clv = new CaLamViec("");
        String trangThai = "Đang làm";
        String hinhAnh = selectedFile.getAbsolutePath();

        nhanVien_DAO = new NhanVien_DAO();
        taiKhoan_DAO = new TaiKhoan_DAO();
        TaiKhoan taiKhoan = new TaiKhoan(maNhanVien, "123456");
        nv = new NhanVien(maNhanVien, tenNhanVien, cccd, ngaySinh, gioiTinh, email, soDienThoai, cv,  taiKhoan, clv, trangThai, hinhAnh,diaChi);
        try {
            
                if(nhanVien_DAO.InsertNhanVien(nv)){
                    if(taiKhoan_DAO.inserTaiKhoan(taiKhoan)){
                    JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công");
                }
                    
            }
               
            
        } catch (SQLException ex) {
            Logger.getLogger(pnlCapNhatNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
       // System.out.println(tenNhanVien);
       // System.out.println(tenNhanVien);
    }//GEN-LAST:event_btnCapNhatThongTinNhanVienActionPerformed

    private void cboGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGioiTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboGioiTinhActionPerformed

    private void btnChonNgaySinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonNgaySinhActionPerformed
        // TODO add your handling code here:
        date.showPopup();
    }//GEN-LAST:event_btnChonNgaySinhActionPerformed

    private void btnChonAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAnhActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "jpeg", "png", "gif"));

                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    // Lấy tệp được chọn
                    selectedFile = fileChooser.getSelectedFile();
                  //  String filePath = selectedFile.getAbsolutePath().replace("\\", "/");
                  //  filePath = "/"+filePath;
                   // System.out.println("Đường dẫn tệp: " + filePath);
                   // lblAnhNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource(filePath)));
                    BufferedImage b;
                    try {
                        b = ImageIO.read(selectedFile);
                        // Thiết lập kích thước ảnh bằng với kích thước của JLabel
                    int labelWidth = lblAnhNhanVien.getWidth();
                    int labelHeight = lblAnhNhanVien.getHeight();
                    
                    Image scaledImage = b.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

                    // Tạo ImageIcon với ảnh đã điều chỉnh kích thước
                    ImageIcon imageIcon = new ImageIcon(scaledImage);

                    // Thiết lập ImageIcon cho JLabel
                    lblAnhNhanVien.setIcon(imageIcon);
                     //  lblAnhNhanVien.setIcon(new javax.swing.ImageIcon(b));

                    } catch (Exception e) {
                    }
                            
                }
    }//GEN-LAST:event_btnChonAnhActionPerformed

    private void lblAnhNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhNhanVienMouseClicked
        
    JDialog modalDialog = new JDialog(SwingUtilities.windowForComponent(this), "Fullscreen Modal", ModalityType.APPLICATION_MODAL);
        modalDialog.setUndecorated(true);

        // Đặt kích thước cửa sổ modal dựa trên kích thước màn hình
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        modalDialog.setSize(screenSize.width, screenSize.height);

        // Đặt độ trong suốt cho modal
        float modalOpacity = 0.7f;
        AlphaComposite modalAlpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, modalOpacity);
        modalDialog.setOpacity(modalOpacity);

        // Đóng cửa sổ modal khi bất kỳ sự kiện click chuột nào xảy ra
        modalDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                modalDialog.dispose();
               closeImageFrame();
            }
        });

        // Hiển thị cửa sổ modal
        modalDialog.setLocation(0, 0);
        jframAnh(true);
        modalDialog.setVisible(true);
    }//GEN-LAST:event_lblAnhNhanVienMouseClicked

    private void btnLamMoiThongTinNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiThongTinNhanVienActionPerformed
        // TODO add your handling code here:
        txtCCCD.setText("");
        txtEmail.setText("");
        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtCCCD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        
        txtSoDienThoai.setText("");
        txtSoDienThoai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        
        Calendar calendar1 = Calendar.getInstance();
        int year1 = calendar1.get(Calendar.YEAR) % 100;
        
        
        date.toDay();
        SelectedDate day = date.getSelectedDate();
        date.setSelectedDate(new SelectedDate(day.getDay(),day.getMonth(),day.getYear() - 18) );
        
        cboTinhThanhPho.setSelectedItem("Tỉnh/Thành phố");
        cboGioiTinh.setSelectedItem("Nam");
        cboChucVu.setSelectedItem("Quản lý");
                    
    }//GEN-LAST:event_btnLamMoiThongTinNhanVienActionPerformed

    private void txtNgaySinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgaySinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgaySinhActionPerformed

    private void txtCCCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCCCDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCCCDActionPerformed

    private void btnLamMoiBangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiBangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLamMoiBangActionPerformed

    private void txtTimKiemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemFocusGained
        // TODO add your handling code here:
        if(txtTimKiem.getText().equals("Tìm kiếm theo mã nhân viên, tên nhân viên")){
            txtTimKiem.setText("");
            txtTimKiem.requestFocus();
            removePlaceholderStyle(txtTimKiem);
        }
    }//GEN-LAST:event_txtTimKiemFocusGained

    private void txtTimKiemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemFocusLost
        // TODO add your handling code here:
        if(txtTimKiem.getText().length()==0){
            addPlaceholderStyle(txtTimKiem);
            txtTimKiem.setText("Tìm kiếm theo mã nhân viên, tên nhân viên");
        }
    }//GEN-LAST:event_txtTimKiemFocusLost

    private void cboCaLamViecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCaLamViecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboCaLamViecActionPerformed

    private void cboChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChucVuActionPerformed
        // TODO add your handling code here:
        
        
        if(cboChucVu.getSelectedItem().equals("Quản lý")){
            lblCaLam.setVisible(false);
            cboCaLamViec.setVisible(false);
        }else{
         lblCaLam.setVisible(true);
         cboCaLamViec.setVisible(true);   
        }
        
    }//GEN-LAST:event_cboChucVuActionPerformed

    private void cboTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTrangThaiActionPerformed

    private void txtMaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNhanVienActionPerformed
     
    public void addPlaceholderStyle(JTextField textFile){
        Font  font = textFile.getFont();
        font = font.deriveFont(Font.ITALIC);
        textFile.setFont(font);
        textFile.setForeground(Color.GRAY);
    }
    public void removePlaceholderStyle(JTextField textFile){
         Font  font = textFile.getFont();
        font = font.deriveFont(Font.PLAIN|Font.BOLD);
        textFile.setFont(font);
        textFile.setForeground(Color.black);
    }

    public void jframAnh(boolean a){
                    // TODO add your handling code here:
           // Tạo một JFrame mới để hiển thị ảnh phóng to
            imageFrame = new JFrame("Large Image");
            imageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            imageFrame.setSize(500, 400);
            imageFrame.setAlwaysOnTop(a);
            imageFrame.setUndecorated(true);
            JPanel imagePanel = new JPanel();
            imagePanel.setLayout(new BorderLayout());
            GroupLayout layout = new GroupLayout(imagePanel);
            imagePanel.setLayout(layout);

            JLabel jLabel1 = new JLabel();
            layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            );

        // Đọc ảnh từ tệp (chắc chắn rằng bạn đã đặt selectedFile)
        BufferedImage b;
        try {
            b = ImageIO.read(selectedFile);
            // Scale ảnh để khớp kích thước của JLabel
            Image scaledImage = b.getScaledInstance(imageFrame.getWidth(), imageFrame.getHeight(), Image.SCALE_SMOOTH);

            // Tạo ImageIcon từ ảnh đã điều chỉnh kích thước
            ImageIcon imageIcon = new ImageIcon(scaledImage);

            // Đặt ImageIcon cho JLabel
            jLabel1.setIcon(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageFrame.add(imagePanel);

         // Đóng cửa sổ ảnh khi bất kỳ sự kiện nào xảy ra
        imageFrame.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                closeImageFrame();
            }
        });

        imageFrame.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                 closeImageFrame();
            }
        });

        // Hiển thị JFrame
        imageFrame.setLocationRelativeTo(null);
        imageFrame.setVisible(a);
  
    }
   private void closeImageFrame() {
        if (imageFrame != null) {
            imageFrame.dispose();
            imageFrame = null;
        }
    }
    public static ArrayList<String> readExcel_City() throws IOException {
		//Đọc dữ liệu từ file Diachi.xlsx
                ArrayList<String> cities = new ArrayList<>();
		FileInputStream file = new FileInputStream("src\\Li\\Tinh_2023.xlsx");
		//Nạp file input stream đưa về dạng excel
		XSSFWorkbook wb = new XSSFWorkbook(file);
		//Đọc file từ Sheet 1 (bắt đầu từ số 0)
		XSSFSheet sheet = wb.getSheetAt(0);
		//Lấy các giá trị trong các cột
		FormulaEvaluator formula = wb.getCreationHelper().createFormulaEvaluator();
		int v = 0;
		for(Row row:sheet) {
			
				if(row.getCell(1) !=null ) {
					cities.add(row.getCell(1)+"");
					v++;
				}
			
		}
		System.out.println(v);
		wb.close();
		file.close();
		return cities;
}   

    public static String readExcel_City_Id(String ip) throws IOException {
                    //Đọc dữ liệu từ file Diachi.xlsx
                    String cities = "";
                    FileInputStream file = new FileInputStream("src\\Li\\Tinh_2023.xlsx");
                    //Nạp file input stream đưa về dạng excel
                    XSSFWorkbook wb = new XSSFWorkbook(file);
                    //Đọc file từ Sheet 1 (bắt đầu từ số 0)
                    XSSFSheet sheet = wb.getSheetAt(0);
                    //Lấy các giá trị trong các cột
                    FormulaEvaluator formula = wb.getCreationHelper().createFormulaEvaluator();
                    int v = 0;
                    for(Row row:sheet) {
                            
                        if(row.getCell(1) !=null ) {
                           if(row.getCell(1) != null && ip.equals(row.getCell(0)+"") ) {
	
				cities = row.getCell(1)+"";
                                break;
				}
                            }
                        }                     
                    wb.close();
                    file.close();
        return cities;
    }   
    public static ArrayList<String> readExcel_districts(JComboBox<String> cbo,JComboBox<String> cbo1,JComboBox<String> cbo2,String tb) throws IOException {
	//Đọc dữ liệu từ file Diachi.xlsx
            ArrayList<String> districts = new ArrayList<>();
		FileInputStream file = new FileInputStream("src\\Li\\Book1.xlsx");
		//Nạp file input stream đưa về dạng excel
		XSSFWorkbook wb = new XSSFWorkbook(file);
		//Đọc file từ Sheet 1 (bắt đầu từ số 0)
		XSSFSheet sheet = wb.getSheetAt(0);
		//Lấy các giá trị trong các cột
		FormulaEvaluator formula = wb.getCreationHelper().createFormulaEvaluator();
		//Duyệt các row
                
		int v = 0;
                if(cbo.getSelectedItem().equals("Tỉnh/Thành phố")){
                    cbo1.setEnabled(false);
                    cbo2.setEnabled(false);
                }else{
                     cbo1.setEnabled(true);
                    cbo1.setEditable(false);
                    cbo2.setEnabled(true);
                }
		for(Row row:sheet) {
			if(row.getCell(0) != null && cbo.getSelectedItem().equals(row.getCell(0)+"") ) {
				if(!districts.contains(row.getCell(2)+"" )) {
					districts.add(row.getCell(2)+"");
					v++;
				}
			}
		}
                
		System.out.println(v);
		wb.close();
		file.close();
		return districts;
	}    
     public static ArrayList<String> readExcel_wardsDistrict(JComboBox<String> cbo,JComboBox<String> cbo1) throws IOException {
		//Đọc dữ liệu từ file Diachi.xlsx
    	 ArrayList<String> wardsDistrict = new ArrayList<>();
		FileInputStream file = new FileInputStream("src\\Li\\Book1.xlsx");
		//Nạp file input stream đưa về dạng excel
		XSSFWorkbook wb = new XSSFWorkbook(file); 
		//Đọc file từ Sheet 1 (bắt đầu từ số 0)
		XSSFSheet sheet = wb.getSheetAt(0);
		//Lấy các giá trị trong các cột
		FormulaEvaluator formula = wb.getCreationHelper().createFormulaEvaluator();
		//Duyệt các row
		int v = 0;
               
                if(cbo.getSelectedItem().equals("Quận/huyện")){
                    cbo1.setEnabled(false);
                }else{
                cbo.setEnabled(true);   
                }
		for(Row row:sheet) {
			if(row.getCell(2) != null && cbo.getSelectedItem().equals(row.getCell(2)+"") ) {
				if(!wardsDistrict.contains(row.getCell(4)+"" )) {
					wardsDistrict.add(row.getCell(4)+"");
					
				}
			}
		}
		System.out.println(v);
		wb.close();
		file.close();
		return wardsDistrict;
	}
    private static String vietHoaChuCaiDauTienTrongJtextField(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder formattedText = new StringBuilder();
        boolean capitalizeNext = true;

        for (char c : input.toCharArray()) {
            if (Character.isWhitespace(c)) {
                capitalizeNext = true;
                formattedText.append(c);
            } else {
                if (capitalizeNext) {
                    formattedText.append(Character.toUpperCase(c));
                } else {
                    formattedText.append(Character.toLowerCase(c));
                }
                capitalizeNext = false;
            }
        }

        return formattedText.toString();
    }
    private static boolean validateData(String input) {
         String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
         java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
         java.util.regex.Matcher matcher = pattern.matcher(input);
        if (matcher.matches() && input.endsWith("@gmail.com")&& input.length()>0)  {
            
         return true;  
        } else {     
            return false;
        }
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
                 @Override
                 public void insertUpdate(DocumentEvent e) {
                      if ( txtSoDienThoai.getText().length() < 10) {
                    // Đặt màu đỏ đậm cho đường viền
                    LineBorder redLineBorder = new LineBorder(Color.RED, 2);
                     txtSoDienThoai.setBorder(redLineBorder);
                    } else {
                     if(!txtSoDienThoai.getText().substring(0, 1).equals("0")){
                             LineBorder redLineBorder = new LineBorder(Color.RED, 2);
                             txtSoDienThoai.setBorder(redLineBorder);
                            }else{
                         txtSoDienThoai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
                     }
                    }
                 }

                 @Override
                 public void removeUpdate(DocumentEvent e) {
                      LineBorder redLineBorder = new LineBorder(Color.RED, 2);
                     txtSoDienThoai.setBorder(redLineBorder);
                 }
                 
                 @Override
                 public void changedUpdate(DocumentEvent e) {
                 }
               });
                
    }
    private void duLieuCCCD(){
        
             ((AbstractDocument) txtCCCD.getDocument()).setDocumentFilter(new DocumentFilter() {
                   @Override
                   public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr)
                           throws BadLocationException {
                       String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                       text += string;
                       if (text.length() <= 12 && text.matches("\\d*")) {
                           super.insertString(fb, offset, string, attr);
                       } else if (text.matches("\\d*")) {
                           super.replace(fb, 11, 1, string, attr);
                       }
                   }

                   @Override
                   public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                           throws BadLocationException {
                       String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                       if (newText.length() <= 12 && newText.matches("\\d*")) {
                           super.replace(fb, offset, length, text, attrs);
                       } else if (newText.matches("\\d*")) {
                           super.replace(fb, 11, 1, text, attrs);
                       }
                   }
               });

               txtCCCD.addFocusListener(new FocusAdapter() {
                   @Override
                   public void focusLost(FocusEvent e) {
                       String text = txtCCCD.getText();
                       if (text.length() > 12) {
                           txtCCCD.setText(text.substring(text.length() - 10));
                       }
                   }
               });
               
            txtCCCD.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
              
        if ( txtCCCD.getText().length() < 12) {
                // Đặt màu đỏ đậm cho đường viền
                LineBorder redLineBorder = new LineBorder(Color.RED, 2);
                     txtCCCD.setBorder(redLineBorder);
                 
        
            
     
            } else {
             if(!txtCCCD.getText().substring(0, 1).equals("0")){
                     LineBorder redLineBorder = new LineBorder(Color.RED, 2);
                     txtCCCD.setBorder(redLineBorder);
                    }else{
                 txtCCCD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
             }
                 
                   
            }
                if( txtCCCD.getText().length() == 3 ){
                    String part2 = txtCCCD.getText().substring(1, 3);  // Phần 2: "52"
                    try {
                        if(!readExcel_City_Id(part2).equals("")){
                            System.out.println(readExcel_City_Id(part2));
                            cboTinhThanhPho.setSelectedItem(readExcel_City_Id(part2));
                        }else{
                            cboTinhThanhPho.setSelectedItem("Tỉnh/Thành phố");
                        }
                        
                    } catch (IOException ex) {
                        Logger.getLogger(pnlCapNhatNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(txtCCCD.getText().length() == 4){
                     String part3 = txtCCCD.getText().substring(3, 4);
                     if(part3.equals("2")||part3.equals("0")){
                         cboGioiTinh.setSelectedItem("Nam");
                     }else if(part3.equals("1")||part3.equals("3")){
                            cboGioiTinh.setSelectedItem("Nữ");
                     }
                }
                if(txtCCCD.getText().length()==6){
                    Calendar calendar1 = Calendar.getInstance();
                    int year1 = calendar1.get(Calendar.YEAR) % 100;
                    String part4 = txtCCCD.getText().substring(4, 6);
                    if( year1 - Integer.parseInt(part4) >= 18 ){
                        
                        String s = "20"+ part4;
                        date.setSelectedDate(new SelectedDate(1,1,Integer.parseInt(s)) );
                        
                     }else if(Integer.parseInt(part4) > year1){
                          String s = "19"+ part4;
                          date.setSelectedDate(new SelectedDate(1,1,Integer.parseInt(s)) );
                     }
                }
                
            }
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
               if( !txtCCCD.getText().equals("") ){
                    LineBorder redLineBorder = new LineBorder(Color.RED, 2);
                     txtCCCD.setBorder(redLineBorder);
                    
                }
            }
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                if( !txtCCCD.getText().equals("") ){
                    
                    System.out.println(".changedUpdate()");
                }
            }
        });
    }
    
    private void DuLieuEmail(){
        txtEmail.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if( validateData(txtEmail.getText())){
                      // Tạo Border tùy chỉnh (ví dụ: viền đậm màu đỏ)
                txtEmail.setBorder(null);
                }else{
                Border customBorder = BorderFactory.createLineBorder(Color.RED, 2);
                txtEmail.setBorder(customBorder); 
                }
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatThongTinNhanVien;
    private javax.swing.JButton btnChonAnh;
    private javax.swing.JButton btnChonNgaySinh;
    private javax.swing.JButton btnLamMoiBang;
    private javax.swing.JButton btnLamMoiThongTinNhanVien;
    private javax.swing.JComboBox<String> cboCaLamViec;
    private javax.swing.JComboBox<String> cboChucVu;
    private javax.swing.JComboBox<String> cboChucVuTimKiem;
    private javax.swing.JComboBox<String> cboGioiTinh;
    private javax.swing.JComboBox<String> cboPhuongXa;
    private javax.swing.JComboBox<String> cboQuanHuyen;
    private javax.swing.JComboBox<String> cboTinhThanhPho;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JComboBox<String> cboTrangThaiTimKiem;
    private ServiceUser.DateChooser date;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblAnhNhanVien;
    private javax.swing.JLabel lblCCCD;
    private javax.swing.JLabel lblCaLam;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblChucVuTimKiem;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblMaNhanVien;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JLabel lblTenNhanVien;
    private javax.swing.JLabel lblThemNhanVien;
    private javax.swing.JLabel lblTimKiem;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JLabel lblTrangThaiTimKiem;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTenNhanVien;
    private ServiceUser.TextFieldSuggestion txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
