
package Pannel;

import DAO.Sach_DAO;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

/**
 *
 * @author FPTSHOP
 */
public class pnlThemSach extends javax.swing.JPanel {

    /**
     * Creates new form pnlThemSanPhamSach
     */
    private File selectedFile;
    private JFrame imageFrame;
    private final Sach_DAO sach_DAO;
    public pnlThemSach() throws SQLException {
        
     try {
            // Set the Look and Feel for the entire application
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        initComponents();
        init();
        testData(cboTacGia);
        testData(cboNhaCungCap);
        sach_DAO = new Sach_DAO();
        lblMaSachKyTu.setText(sach_DAO.generateMaSach());
        
        // Create a custom DocumentFilter
        kiemTraSo(txtSoTrang);
        kiemTraSo(txtSoLuongTon);
        kiemTraSo(txtNamXuatBan);
        kiemTraDuLieuFloat(txtDonGia);
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
    public void kiemTraSo(JTextField s){
         PlainDocument document = (PlainDocument) s.getDocument();
        document.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
                if (isValidInput(text)) {
                    super.insertString(fb, offset, text, attr);
                }
            }

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (isValidInput(text)) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
             private static boolean isValidInput(String text) {
                if (text.isEmpty()) {
                    return true;
                }
                char firstChar = text.charAt(0);
                return Character.isDigit(firstChar) && firstChar != '0' && text.matches("\\d*");
            }
        });
    }
    
    public void testData(JComboBox combo) {
        combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
            "Blueberry",
            "Kiwi",
            "Mango",
            "Pineapple",
            "Strawberry",
            "Watermelon",
            "Raspberry",
            "Peach",
            "Grapefruit",
            "Lemon",
            "Orange",
            "Apple",
            "Banana",
            "Pear",
            "Cherry",
            "Papaya",
            "Plum",
            "Apricot",
            "Blackberry",
            "Cranberry",
            "Guava",
            "Lime",
            "Lychee",
            "Coconut",
            "Fig",
            "Pomegranate",
            "Avocado",
            "Grape",
            "Cantaloupe",
            "Dragonfruit"
        }));
        
    }
    public void init(){
                // Đặt ảnh vào JLabel và thiết lập kích thước bằng với JLabel
        try {
            selectedFile =new File("src\\IMG\\anhSachMacDinh_daThayDoi.png");
            BufferedImage image = ImageIO.read(selectedFile); // Thay đổi đường dẫn đến ảnh
            
            //thay đổi kích thước ảnh phù hợp vs lable 259x137
             Image scaledImage = image.getScaledInstance(259, 137, Image.SCALE_SMOOTH);

                    // Tạo ImageIcon với ảnh đã điều chỉnh kích thước
                    ImageIcon imageIcon = new ImageIcon(scaledImage);

                    // Thiết lập ImageIcon cho JLabel
                    lblAnhSach.setIcon(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblAnhSach = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblMaSach = new javax.swing.JLabel();
        lblTenSach = new javax.swing.JLabel();
        txtTenSach = new javax.swing.JTextField();
        lblDonGia = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        lblNhaCungCap = new javax.swing.JLabel();
        cboNhaCungCap = new javax.swing.JComboBox<>();
        cboTheLoai = new javax.swing.JComboBox<>();
        lblTheLoai = new javax.swing.JLabel();
        lblTacGia = new javax.swing.JLabel();
        lblNhaXuatBan = new javax.swing.JLabel();
        lblNamXuatBan = new javax.swing.JLabel();
        txtSoLuongTon = new javax.swing.JTextField();
        lblSoTrang = new javax.swing.JLabel();
        cboNhaXuatBan = new javax.swing.JComboBox<>();
        lblSoLuongTon = new javax.swing.JLabel();
        txtNamXuatBan = new javax.swing.JTextField();
        txtSoTrang = new javax.swing.JTextField();
        cboTacGia = new ServiceUser.ComboBoxMultiSelection();
        lblMaSachKyTu = new javax.swing.JLabel();
        btnThemTheLoai = new javax.swing.JButton();
        btnThemNhaCungCap = new javax.swing.JButton();
        btnThemNhaXuatBan = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1130, 787));

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));
        jPanel1.setEnabled(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(1320, 787));

        lblAnhSach.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnhSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhSachMouseClicked(evt);
            }
        });

        jButton1.setText("Chọn ảnh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin sách"));

        lblMaSach.setBackground(new java.awt.Color(0, 0, 0));
        lblMaSach.setText("Mã sách");

        lblTenSach.setText("Tên sách");

        txtTenSach.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTenSachFocusGained(evt);
            }
        });
        txtTenSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSachActionPerformed(evt);
            }
        });

        lblDonGia.setText("Đơn giá");

        txtDonGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDonGiaActionPerformed(evt);
            }
        });

        lblNhaCungCap.setText("Nhà cung cấp");

        lblTheLoai.setText("Thể loại");

        lblTacGia.setText("Tác giả");

        lblNhaXuatBan.setText("Nhà xuất bản");

        lblNamXuatBan.setText("Năm xuất bản");

        txtSoLuongTon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongTonActionPerformed(evt);
            }
        });

        lblSoTrang.setText("Số trang");

        lblSoLuongTon.setText("Số lượng tồn");

        txtNamXuatBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamXuatBanActionPerformed(evt);
            }
        });

        txtSoTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoTrangActionPerformed(evt);
            }
        });

        cboTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTacGiaActionPerformed(evt);
            }
        });

        lblMaSachKyTu.setBackground(new java.awt.Color(204, 204, 204));
        lblMaSachKyTu.setEnabled(false);

        btnThemTheLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTheLoaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNhaCungCap)
                            .addComponent(lblTheLoai))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboNhaCungCap, 0, 381, Short.MAX_VALUE)
                            .addComponent(cboTheLoai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(lblDonGia)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblTenSach)
                                .addComponent(lblMaSach))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtTenSach, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(lblMaSachKyTu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(81, 81, 81))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblNamXuatBan)
                        .addComponent(lblNhaXuatBan))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTacGia)
                            .addComponent(lblSoLuongTon)
                            .addComponent(lblSoTrang))
                        .addGap(7, 7, 7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNamXuatBan)
                    .addComponent(txtSoLuongTon, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtSoTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cboNhaXuatBan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboTacGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThemNhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTacGia)
                            .addComponent(cboTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblNhaXuatBan)
                                .addComponent(cboNhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnThemNhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNamXuatBan)
                            .addComponent(txtNamXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoTrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSoTrang))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSoLuongTon)
                            .addComponent(txtSoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaSach)
                            .addComponent(lblMaSachKyTu, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTenSach)
                            .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDonGia)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblNhaCungCap)
                                .addComponent(cboNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnThemNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTheLoai)
                            .addComponent(cboTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jButton2.setText("Làm mới");

        jButton3.setText("Thêm sản phẩm");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblAnhSach, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(403, 403, 403))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(490, 490, 490))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(448, 448, 448)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAnhSach, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(324, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1130, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
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
                    int labelWidth = lblAnhSach.getWidth();
                    int labelHeight = lblAnhSach.getHeight();
                    
                    Image scaledImage = b.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

                    // Tạo ImageIcon với ảnh đã điều chỉnh kích thước
                    ImageIcon imageIcon = new ImageIcon(scaledImage);

                    // Thiết lập ImageIcon cho JLabel
                    lblAnhSach.setIcon(imageIcon);
                     //  lblAnhNhanVien.setIcon(new javax.swing.ImageIcon(b));

                    } catch (Exception e) {
                    }
                            
                }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTenSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSachActionPerformed

    private void txtDonGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDonGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDonGiaActionPerformed

    private void txtSoLuongTonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongTonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongTonActionPerformed

    private void txtNamXuatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamXuatBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamXuatBanActionPerformed

    private void txtSoTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoTrangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoTrangActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void lblAnhSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhSachMouseClicked
        // TODO add your handling code here:
                
    JDialog modalDialog = new JDialog(SwingUtilities.windowForComponent(this), "Fullscreen Modal", Dialog.ModalityType.APPLICATION_MODAL);
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
    }//GEN-LAST:event_lblAnhSachMouseClicked

    private void cboTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTacGiaActionPerformed
        // TODO add your handling code here:
        
     
    }//GEN-LAST:event_cboTacGiaActionPerformed

    private void txtTenSachFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenSachFocusGained
        // TODO add your handling code here:
        txtTenSach.setBackground(Color.white);
    }//GEN-LAST:event_txtTenSachFocusGained

    private void btnThemTheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTheLoaiActionPerformed
        // TODO add your handling code here:
        JDialog modalDialog = new JDialog(SwingUtilities.windowForComponent(this), "Fullscreen Modal", Dialog.ModalityType.APPLICATION_MODAL);
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
        jframThemTheLoai(true);
        modalDialog.setVisible(true);
    }//GEN-LAST:event_btnThemTheLoaiActionPerformed
    public void jframThemTheLoai(boolean a){
         JFrame frmTheTheLoai = new JFrame("Large Image");
       
            frmTheTheLoai.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frmTheTheLoai.setSize(500, 400);
            frmTheTheLoai.setAlwaysOnTop(a);
            frmTheTheLoai.setUndecorated(true);
            
        javax.swing.JPanel jPanel1;
        javax.swing.JLabel lblMaTheLoai;
        javax.swing.JLabel lblMaTheLoaiKyTu;
        javax.swing.JLabel lblTenTheLoai;
        javax.swing.JLabel lblThemTheLoai;
        javax.swing.JTextField txtTenTheLoai;
        jPanel1 = new javax.swing.JPanel();
    
        lblMaTheLoai = new javax.swing.JLabel();
        lblTenTheLoai = new javax.swing.JLabel();
        lblThemTheLoai = new javax.swing.JLabel();
        txtTenTheLoai = new javax.swing.JTextField();
        lblMaTheLoaiKyTu = new javax.swing.JLabel();

        frmTheTheLoai.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frmTheTheLoai.setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblMaTheLoai.setText("Mã thể loại");

        lblTenTheLoai.setText("Tên thể loại");

        lblThemTheLoai.setText("Thêm thể loại");
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblTenTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTenTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblMaTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblMaTheLoaiKyTu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(lblThemTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblThemTheLoai)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblMaTheLoai)
                        .addGap(23, 23, 23))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblMaTheLoaiKyTu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenTheLoai)
                    .addComponent(txtTenTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frmTheTheLoai.getContentPane());
        frmTheTheLoai.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        frmTheTheLoai.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                closeImageFrame();
            }
        });

        frmTheTheLoai.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                 closeImageFrame();
            }
        });
        
        frmTheTheLoai.pack();
        frmTheTheLoai.setLocationRelativeTo(null);
        duLieuTenTheLoai(txtTenTheLoai);
    }
    public void duLieuTenTheLoai(JTextField txtTenTheLoai){
            txtTenTheLoai.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = txtTenTheLoai.getText();
                String formattedText = vietHoaChuCaiDauTienTrongJtextField(text);
                txtTenTheLoai.setText(formattedText);
            }
        });
        
                // Tạo một DocumentFilter để kiểm tra và lọc ký tự
        AbstractDocument document = (AbstractDocument) txtTenTheLoai.getDocument();
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThemNhaCungCap;
    private javax.swing.JButton btnThemNhaXuatBan;
    private javax.swing.JButton btnThemTheLoai;
    private javax.swing.JComboBox<String> cboNhaCungCap;
    private javax.swing.JComboBox<String> cboNhaXuatBan;
    private ServiceUser.ComboBoxMultiSelection cboTacGia;
    private javax.swing.JComboBox<String> cboTheLoai;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblAnhSach;
    private javax.swing.JLabel lblDonGia;
    private javax.swing.JLabel lblMaSach;
    private javax.swing.JLabel lblMaSachKyTu;
    private javax.swing.JLabel lblNamXuatBan;
    private javax.swing.JLabel lblNhaCungCap;
    private javax.swing.JLabel lblNhaXuatBan;
    private javax.swing.JLabel lblSoLuongTon;
    private javax.swing.JLabel lblSoTrang;
    private javax.swing.JLabel lblTacGia;
    private javax.swing.JLabel lblTenSach;
    private javax.swing.JLabel lblTheLoai;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtNamXuatBan;
    private javax.swing.JTextField txtSoLuongTon;
    private javax.swing.JTextField txtSoTrang;
    private javax.swing.JTextField txtTenSach;
    // End of variables declaration//GEN-END:variables
}
