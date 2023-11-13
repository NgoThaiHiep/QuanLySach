
package ServiceUser;


import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import DAO.NhaCungCap_DAO;
import DAO.NhaXuatBan_DAO;
import DAO.Sach_TheLoai_DAO;
import DAO.TacGia_DAO;
import DAO.TheLoai_DAO;
import Entity.LoaiSanPham;
import Entity.NhaCungCap;

import Entity.NhaXuatBan;
import Entity.Sach;
import Entity.TacGia;
import Entity.TheLoai;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author FPTSHOP
 */
public class CellSach extends javax.swing.JPanel {
    	private Sach sach;
	private NhaXuatBan_DAO nhaXuatBan_DAO;
	private NhaCungCap_DAO nhaCungCap_DAO;
	private TheLoai_DAO theLoai_DAO;
	private List<Object> selectedItemsTheLoai;
	private List<Object>   selectedItemsTacGia;
	private Sach_TheLoai_DAO sachTheLoai_DAO;
	private TacGia_DAO tacGia_DAO;
    /**
     * Creates new form CellSach
     */
    private String pathImage;
    private File selectedFile;
    private boolean show = false;
    public CellSach(Sach sach) throws IOException {
        this.sach = sach;
         try {
            // Set the Look and Feel for the entire application
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        initComponents();
        
         txtTinhTrang.setVisible(false);
        txtMaSanPham.setVisible(false);
        
        lblMaSanPham1.setText(sach.getMaSanPham());
        lblTenSach1.setText(sach.getTenSanPham());
        lblTacGia1.setText(sach.getTacGia());
        lblTheLoai1.setText(sach.getTheLoai().getMaTheLoai());
        lblSoTrang1.setText(sach.getSoTrang()+"");
        lblGiaBan1.setText(sach.getDonGia()+"");
        lblSoLuongTon1.setText(sach.getSoLuongTon()+"");
        lblNhaXuatBan1.setText(sach.getNhaXuatBan().getMaNhaXuatBan());
        lblTinhTrang1.setText(sach.getTinhTrang());
        lblNhaCungCap1.setText(sach.getNhaCungCap().getMaNCC());
        lblNamXuatBan1.setText(sach.getNamXuatBan()+"");
        
        nhaXuatBan_DAO = new NhaXuatBan_DAO();
        
        
        ArrayList<NhaXuatBan> dsnxb = nhaXuatBan_DAO.layDanhSachNhaXuatBan();
        
        for (NhaXuatBan nhaXuatBan : dsnxb) {
      	  cboNhaXuatBan.addItem(nhaXuatBan.getTenNhaXuatBanl());
        }
        for (NhaXuatBan nhaXuatBan : dsnxb) {
      	  if( nhaXuatBan.getMaNhaXuatBan().equals(lblNhaXuatBan1.getText())) {
      		  lblNhaXuatBan1.setText(nhaXuatBan.getTenNhaXuatBanl());
      	  }
      	 
        }
        
        nhaCungCap_DAO = new NhaCungCap_DAO();
       ArrayList<NhaCungCap> dsncc = nhaCungCap_DAO.layDanhSachNhaCungCap();
        for (NhaCungCap nhaCungCap : dsncc) {
            cboNhaCungCap.addItem(nhaCungCap.getTenNCC());
        }
       
        for (NhaCungCap nhaCungCap : dsncc) {
            if(lblNhaCungCap1.getText().equals(nhaCungCap.getMaNCC()))
                lblNhaCungCap1.setText(nhaCungCap.getTenNCC());
          
        }
		 visibelTextField(false);
        visibelLable(true);
        
        try {
            selectedFile =new File(sach.getHinhAnh());
            BufferedImage image = ImageIO.read(selectedFile); // Thay đổi đường dẫn đến ảnh
            
            // thay đổi kích thức ảnh cùng kích thước với lable 184x216
             Image scaledImage = image.getScaledInstance(148, 198, Image.SCALE_SMOOTH);

                    // Tạo ImageIcon với ảnh đã điều chỉnh kích thước
                    ImageIcon imageIcon = new ImageIcon(scaledImage);

                    // Thiết lập ImageIcon cho JLabel
                    lblAnh.setIcon(imageIcon);
           
        } catch (IOException e) {
            selectedFile =new File("src\\IMG\\anhSachMacDinh.png");
            BufferedImage image = ImageIO.read(selectedFile); // Thay đổi đường dẫn đến ảnh
            
            // thay đổi kích thức ảnh cùng kích thước với lable 184x216
             Image scaledImage = image.getScaledInstance(148, 198, Image.SCALE_SMOOTH);

                    // Tạo ImageIcon với ảnh đã điều chỉnh kích thước
                    ImageIcon imageIcon = new ImageIcon(scaledImage);

                    // Thiết lập ImageIcon cho JLabel
                    lblAnh.setIcon(imageIcon);
        }
        
        btnLuu.setVisible(false);
    }
    public void visibelLable(boolean a) {
    	// lblMaSanPham1.setVisible(a);
         lblTenSach1.setVisible(a);
         lblTacGia1.setVisible(a);
         lblTheLoai1.setVisible(a);
         lblSoTrang1.setVisible(a);
         lblGiaBan1.setVisible(a);
         lblSoLuongTon1.setVisible(a);
         lblNhaXuatBan1.setVisible(a);
       //  lblTinhTrang1.setVisible(a);
         lblNhaCungCap1.setVisible(a);
         lblNamXuatBan1.setVisible(a);
    }
 public void visibelTextField(boolean a) {
    	// txtMaSanPham.setVisible(a);
         txtTenSach.setVisible(a);
         cboTacGia.setVisible(a);
         cboTheLoai.setVisible(a);
         txtSoTrang.setVisible(a);
         txtGiaBan.setVisible(a);
         txtSoLuongTon.setVisible(a);
         cboNhaXuatBan.setVisible(a);
       //  txtTinhTrang.setVisible(a);
         cboNhaCungCap.setVisible(a);
         txtNamXuatBan.setVisible(a);
    }
  private void dataTheLoai(JComboBox combo) {
    	theLoai_DAO = new TheLoai_DAO();
    	ArrayList<TheLoai> dstl = theLoai_DAO.layDanhSachTheLoai();
    
    	String[] tenTheLoaiArray = new String[dstl.size()];
    	for (int i = 0; i < dstl.size(); i++) {
    	    tenTheLoaiArray[i] = dstl.get(i).getTenTheLoai();
    	}

    	// Đặt mô hình cho ComboBox sử dụng mảng tên thể loại
        
    	combo.setModel(new javax.swing.DefaultComboBoxModel<>(tenTheLoaiArray));
    }
    
    private void testData(JComboBox combo) {
    	tacGia_DAO = new TacGia_DAO();
    	ArrayList<TacGia> dstg = tacGia_DAO.layDanhSachTacGia();
    	String[] tacGiaArray = new String[dstg.size()];
    	for (int i = 0; i < dstg.size(); i++) {
    	   tacGiaArray[i] = dstg.get(i).getTenTacGia();
    	}

    	// Đặt mô hình cho ComboBox sử dụng mảng tên thể loại
    	combo.setModel(new javax.swing.DefaultComboBoxModel<>(tacGiaArray));
    }
    public void dataTacGia(){
        testData(cboTacGia);
        selectedItemsTacGia = new ArrayList<>();
        cboTacGia.removeSelectedItems();
        
        String inputTacGia = lblTacGia1.getText();
        String[] itemTacGia = inputTacGia.split(",");

       for (String items : itemTacGia) {
           selectedItemsTacGia.add(items);
       }
        cboTacGia.setSelectedItems( selectedItemsTacGia);
      
    }
    public void dataTheLoai(){
        
       
      dataTheLoai(cboTheLoai);
      selectedItemsTheLoai= new ArrayList<>();
      cboTheLoai.removeSelectedItems();
      
      String inputTheLoai =lblTheLoai1.getText();
      String[] itemTheLoai = inputTheLoai.split(",");
    
     for (String items : itemTheLoai) {
    	 selectedItemsTheLoai.add(items);
     }
      cboTheLoai.setSelectedItems(selectedItemsTheLoai);
    }
    public void dataTextField() {
    	
      txtMaSanPham.setText(sach.getMaSanPham());
      txtTenSach.setText(sach.getTenSanPham());
     // cboTacGia.setText(sach.getTacGia());
      dataTacGia();
      // txtTheLoai.setText(sach.getTheLoai().getMaTheLoai());
       dataTheLoai();
       
      txtSoTrang.setText(sach.getSoTrang()+"");
      txtGiaBan.setText(sach.getDonGia()+"");
      txtSoLuongTon.setText(sach.getSoLuongTon()+"");
      
      nhaXuatBan_DAO = new NhaXuatBan_DAO();
      
      cboNhaXuatBan.setSelectedItem(lblNhaXuatBan1.getText());
 
     // cboNhaXuatBan.setText(sach.getNhaXuatBan().getMaNhaXuatBan());
      
        lblTinhTrang1.setText(sach.getTinhTrang());
      
     // cboNhaCungCap.setText(sach.getNhaCungCap().getMaNCC());
      txtNamXuatBan.setText(sach.getNamXuatBan()+"");
      
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTacGia = new javax.swing.JLabel();
        lblTenSach = new javax.swing.JLabel();
        lblTheLoai = new javax.swing.JLabel();
        lblNhaXuatBan = new javax.swing.JLabel();
        txtSoLuongTon = new javax.swing.JTextField();
        txtTinhTrang = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        lblGiaBan = new javax.swing.JLabel();
        lblSoLuongTon = new javax.swing.JLabel();
        lblTinhTrang = new javax.swing.JLabel();
        btnSua = new javax.swing.JButton();
        btnNgungKinhDoanh = new javax.swing.JButton();
        txtTenSach = new javax.swing.JTextField();
        lblNamXuatBan = new javax.swing.JLabel();
        txtNamXuatBan = new javax.swing.JTextField();
        txtMaSanPham = new javax.swing.JTextField();
        txtSoTrang = new javax.swing.JTextField();
        lblMaSanPham = new javax.swing.JLabel();
        lblSoTrang = new javax.swing.JLabel();
        lblTacGia1 = new javax.swing.JLabel();
        lblTenSach1 = new javax.swing.JLabel();
        lblTheLoai1 = new javax.swing.JLabel();
        lblMaSanPham1 = new javax.swing.JLabel();
        lblNhaXuatBan1 = new javax.swing.JLabel();
        lblGiaBan1 = new javax.swing.JLabel();
        lblSoLuongTon1 = new javax.swing.JLabel();
        lblSoTrang1 = new javax.swing.JLabel();
        lblTinhTrang1 = new javax.swing.JLabel();
        lblNhaCungCap1 = new javax.swing.JLabel();
        lblNamXuatBan1 = new javax.swing.JLabel();
        lblNhaCungCap = new javax.swing.JLabel();
        cboTacGia = new ServiceUser.ComboBoxMultiSelection();
        cboTheLoai = new ServiceUser.ComboBoxMultiSelection();
        cboNhaXuatBan = new javax.swing.JComboBox<>();
        cboNhaCungCap = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        lblAnh = new javax.swing.JLabel();
        btnLuu = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        setMinimumSize(new java.awt.Dimension(1150, 230));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(700, 230));
        setRequestFocusEnabled(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTacGia.setText("Tác giả");
        add(lblTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, -1, -1));

        lblTenSach.setText("Tên sách");
        add(lblTenSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, -1));

        lblTheLoai.setText("Thể loại");
        add(lblTheLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, -1, -1));

        lblNhaXuatBan.setText("Nhà xuất bản");
        add(lblNhaXuatBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 160, 70, -1));
        add(txtSoLuongTon, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 120, 210, -1));
        add(txtTinhTrang, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 50, 180, -1));

        txtGiaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaBanActionPerformed(evt);
            }
        });
        add(txtGiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 210, -1));

        lblGiaBan.setText("Giá bán");
        add(lblGiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, 70, -1));

        lblSoLuongTon.setText("Số lượng tồn");
        add(lblSoLuongTon, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 120, 80, -1));

        lblTinhTrang.setText("Tình trạng");
        add(lblTinhTrang, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 50, 71, -1));

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 160, 62, -1));

        btnNgungKinhDoanh.setText("Ngừng kinh doanh");
        btnNgungKinhDoanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgungKinhDoanhActionPerformed(evt);
            }
        });
        add(btnNgungKinhDoanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 160, -1, -1));
        add(txtTenSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 260, -1));

        lblNamXuatBan.setText("Năm xuất bản");
        add(lblNamXuatBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 130, 84, -1));
        add(txtNamXuatBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 130, 180, -1));
        add(txtMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 147, -1));
        add(txtSoTrang, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, 210, -1));

        lblMaSanPham.setText("Mã sách");
        add(lblMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, -1, -1));

        lblSoTrang.setText("Số  trang");
        add(lblSoTrang, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 50, -1));
        add(lblTacGia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 260, 30));
        add(lblTenSach1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 260, 20));
        add(lblTheLoai1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 260, 30));
        add(lblMaSanPham1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 140, 20));
        add(lblNhaXuatBan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 150, 210, 30));
        add(lblGiaBan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 210, 20));
        add(lblSoLuongTon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 120, 210, 20));
        add(lblSoTrang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, 210, 20));
        add(lblTinhTrang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 50, 180, 20));
        add(lblNhaCungCap1, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 80, 180, 30));
        add(lblNamXuatBan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 130, 180, 20));

        lblNhaCungCap.setText("Nhà cung cấp");
        add(lblNhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 90, 84, -1));
        add(cboTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 260, 30));
        add(cboTheLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 260, 30));

        add(cboNhaXuatBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 150, 210, 30));

        add(cboNhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 80, 180, 30));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        lblAnh.setBackground(new java.awt.Color(255, 255, 255));
        lblAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 160, 210));

        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 160, 62, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
         visibelTextField(true);
         visibelLable(false);
         dataTextField() ;
         btnSua.setVisible(false);
         btnLuu.setVisible(true);
         show = true;
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnNgungKinhDoanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgungKinhDoanhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNgungKinhDoanhActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        visibelTextField(false);
        visibelLable(true);
        btnLuu.setVisible(false);
        btnSua.setVisible(true);
        show = false;
        
       String maSach = lblMaSanPham1.getText();
       String tenSach = txtTenSach.getText();
       
        List<Object> ItemTacGia = cboTacGia.getSelectedItems();
       String tacGia = "";
        int count = 0;
        for (Object item : ItemTacGia) {
                 count++;
         }
        int countCong = 0;
        for (Object item : ItemTacGia) {
          //  System.out.println(item.toString());
            if(count-1 == countCong)
            	tacGia+=item.toString();
            else
            	tacGia+=item.toString()+",";
            countCong++;
        }
        
        String theLoai= "";
        
         List<Object> ItemTheLoai = cboTheLoai.getSelectedItems();
        int countTheLoai = 0;
        for (Object item : ItemTheLoai) {
                 countTheLoai++;
         }
        int countCongTheLoai = 0;
        for (Object item : ItemTheLoai) {
          //  System.out.println(item.toString());
            if(countTheLoai-1 == countCongTheLoai)
            	theLoai+=item.toString();
            else
            	theLoai+=item.toString()+",";
            countCongTheLoai++;
        }
        
        TheLoai tl = new TheLoai(theLoai);
        String soTrang = txtSoTrang.getText();
        String giaBan = txtGiaBan.getText();
        String soLuongTon = txtSoLuongTon.getText();
        String nhaXuatBan = cboNhaXuatBan.getSelectedItem()+"";
        String tinhTrang = lblTinhTrang1.getText();
        
        
        nhaXuatBan_DAO = new NhaXuatBan_DAO();
        ArrayList<NhaXuatBan> dsnxb = nhaXuatBan_DAO.layDanhSachNhaXuatBan();
        String nhaXuatBan_Sua = "";
        for (NhaXuatBan nhaXuatBan1 : dsnxb) {
            if( nhaXuatBan1.getTenNhaXuatBanl().equals(nhaXuatBan)) {
      		 nhaXuatBan = nhaXuatBan1.getMaNhaXuatBan();
      	  }
        }
        
         NhaXuatBan nxb = new NhaXuatBan(nhaXuatBan);
        
        
        nhaCungCap_DAO = new NhaCungCap_DAO();
       ArrayList<NhaCungCap> dsncc = nhaCungCap_DAO.layDanhSachNhaCungCap();
       String nhaCungCap_Sua = "";
        for (NhaCungCap nhaCungCap : dsncc) {
            if(cboNhaCungCap.getSelectedItem().equals(nhaCungCap.getTenNCC())){
                nhaCungCap_Sua = nhaCungCap.getMaNCC()+"";
            }
        }
        LoaiSanPham loaiSanPham = new LoaiSanPham(sach.getLoaiSanPham().getMaLoaiSanPham());
        NhaCungCap ncc = new NhaCungCap(nhaCungCap_Sua);
        String namXuatBan = txtNamXuatBan.getText();
        String hinhAnh = selectedFile.getAbsolutePath();
        Sach s = new Sach(tacGia,  Integer.parseInt(namXuatBan), countCong, tl, nxb, maSach, tenSach, loaiSanPham, ncc, Integer.parseInt(soLuongTon), Float.parseFloat(giaBan), "", tinhTrang, hinhAnh);
        //System.out.println(s.getHinhAnh());
        
        lblTenSach1.setText(tenSach);
        lblTacGia1.setText(tacGia);
        lblTheLoai1.setText(theLoai);
        lblSoTrang1.setText(soTrang);
        lblGiaBan1.setText(giaBan);
        lblSoLuongTon1.setText(soLuongTon);
        cboNhaXuatBan.setSelectedItem(nhaXuatBan);
        
    }//GEN-LAST:event_btnLuuActionPerformed

    private void txtGiaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaBanActionPerformed

    private void lblAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhMouseClicked
        // TODO add your handling code here:
       if(show){
          
       }else{
               
        }
    }//GEN-LAST:event_lblAnhMouseClicked
    private void clickChonAnh(){
         JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "jpeg", "png", "gif"));

                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    // Lấy tệp được chọn
                    selectedFile = fileChooser.getSelectedFile();
                  //  String filePath = selectedFile.getAbsolutePath().replace("\\", "/");
                  //  filePath = "/"+filePath;
                   // System.out.println("Đường dẫn tệp: " + filePath);
                   // lblAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource(filePath)));
                    BufferedImage b;
                    try {
                        b = ImageIO.read(selectedFile);
                        // Thiết lập kích thước ảnh bằng với kích thước của JLabel
                    int labelWidth = lblAnh.getWidth();
                    int labelHeight = lblAnh.getHeight();
                    
                    Image scaledImage = b.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

                    // Tạo ImageIcon với ảnh đã điều chỉnh kích thước
                    ImageIcon imageIcon = new ImageIcon(scaledImage);

                    // Thiết lập ImageIcon cho JLabel
                    lblAnh.setIcon(imageIcon);
                     //  lblAnh.setIcon(new javax.swing.ImageIcon(b));

                    } catch (Exception e) {
                    }
                            
                }
    }
    




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnNgungKinhDoanh;
    private javax.swing.JButton btnSua;
    private javax.swing.JComboBox<String> cboNhaCungCap;
    private javax.swing.JComboBox<String> cboNhaXuatBan;
    private ServiceUser.ComboBoxMultiSelection cboTacGia;
    private ServiceUser.ComboBoxMultiSelection cboTheLoai;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblGiaBan;
    private javax.swing.JLabel lblGiaBan1;
    private javax.swing.JLabel lblMaSanPham;
    private javax.swing.JLabel lblMaSanPham1;
    private javax.swing.JLabel lblNamXuatBan;
    private javax.swing.JLabel lblNamXuatBan1;
    private javax.swing.JLabel lblNhaCungCap;
    private javax.swing.JLabel lblNhaCungCap1;
    private javax.swing.JLabel lblNhaXuatBan;
    private javax.swing.JLabel lblNhaXuatBan1;
    private javax.swing.JLabel lblSoLuongTon;
    private javax.swing.JLabel lblSoLuongTon1;
    private javax.swing.JLabel lblSoTrang;
    private javax.swing.JLabel lblSoTrang1;
    private javax.swing.JLabel lblTacGia;
    private javax.swing.JLabel lblTacGia1;
    private javax.swing.JLabel lblTenSach;
    private javax.swing.JLabel lblTenSach1;
    private javax.swing.JLabel lblTheLoai;
    private javax.swing.JLabel lblTheLoai1;
    private javax.swing.JLabel lblTinhTrang;
    private javax.swing.JLabel lblTinhTrang1;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtNamXuatBan;
    private javax.swing.JTextField txtSoLuongTon;
    private javax.swing.JTextField txtSoTrang;
    private javax.swing.JTextField txtTenSach;
    private javax.swing.JTextField txtTinhTrang;
    // End of variables declaration//GEN-END:variables
}
