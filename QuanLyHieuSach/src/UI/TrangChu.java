
package UI;


import DAO.NhanVien_DAO;
import DAO.TaiKhoan_DAO;
import DAO.ThoiGianHoatDong_DAO;
import Entity.NhanVien;
import Entity.TaiKhoan;
import Entity.ThoiGianHoatDong;
import Menu.MenuItem;
import Pannel.pnlBaoCao;
import Pannel.pnTraCuuNhanVien;
import Pannel.pnlDanhSachHoaDon;
import Pannel.pnlLapHoaDon;
import Pannel.pnlQuyDinh;
import Pannel.pnlTaiKhoan;
import Pannel.pnlThemKhachHang;
import Pannel.pnlThemNhanVien;
import Pannel.pnlThemSanPham;
import Pannel.pnlThongKe;
import Pannel.pnlTraCuuKhachHang;
import Pannel.pnlTraCuuSanPham;
import ServiceUser.ScrollBarCustom;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LookAndFeel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 *
 * @author FPTSHOP
 */
public class TrangChu extends javax.swing.JFrame {

    /**
     * Creates new form TrangChu
     */
    private TaiKhoan tk ;
    private NhanVien nv;
    private NhanVien_DAO nhanVien_DAO;
    private ThoiGianHoatDong_DAO thoiGianHoatDong_DAO;
    private long startTime;
    private ThoiGianHoatDong tghd;
    private JLabel lblThoiGianDaLam;
    private LocalTime thoiGianDangNhap;
    private TaiKhoan_DAO taiKhoan_DAO;
    private MenuItem menuTrangChu,menuSanPham,menuThemSanPham,menuTraCuuSanPham,menuKhachHang ,menuTraCuuKhachHang, menuThemKhachHang
                    ,menuHoaDon,menuLapHoaDon, menuDanhSachHoaDon
                    ,menuNhanVien ,menuTraCuuNhanVien, menuThemNhanVien
                   ,menuDoanhThu,menuThongKe, menuBaoCao 
                   ,menuTaiKhoan, menuQuyDinh,menuTroGiup;
    private LookAndFeel originalLookAndFeel;
//    private final pnlTrangChu pnlTrangChu;
//    private final pnlTraCuuSanPham pnlTraCuuSanPham;
//    private final pnlThemSanPham pnlThemSanPham;
//    private final pnlCapNhatSanPham pnlCapNhatSanPham;
//    private final pnlLapHoaDon pnlLapHoaDon;
//    private final pnlDanhSachHoaDon pnlDanhSachHoaDon;
//    private pnlTraCuuKhachHang pnlTraCuuKhachHang = null;
//    private final pnlThemKhachHang pnlThemKhachHang;
//    private final pnlTraCuuNhanVien pnlTraCuuNhanVien;
//    private pnlThemNhanVien pnlThemNhanVien = null;
//    private pnlCapNhatNhanVien pnlCapNhatNhanVien = null;
//    private final pnlThongKe pnlThongKe;
//    private final pnlTaiKhoan pnlTaiKhoan;
//    private final CardLayout cardLayout;
//    private final JPanel mainPanel;



    public TrangChu(TaiKhoan tk,NhanVien nv,LookAndFeel originalLookAndFeel) {     
        super("Trang chủ");
        this.tk = tk;
        this.nv = nv;
        this.originalLookAndFeel = originalLookAndFeel;
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        lblThoiGianDaLam = new JLabel();
        setTitle("");
        try {
            UIManager.setLookAndFeel(originalLookAndFeel);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        thoiGianLamViec();
        thoiGianHeader();
        gioDaLam();
        
//        pnlTrangChu = new pnlTrangChu(tk, lblThoiGianDaLam);
//        pnlTraCuuSanPham = new pnlTraCuuSanPham(tk);
//        pnlThemSanPham = new pnlThemSanPham(tk);
//        pnlCapNhatSanPham = new pnlCapNhatSanPham(tk);
//        pnlLapHoaDon = new pnlLapHoaDon(tk, nv,originalLookAndFeel);
//        pnlDanhSachHoaDon = new pnlDanhSachHoaDon(tk, nv);
//        try {
//            pnlTraCuuKhachHang = new pnlTraCuuKhachHang(tk);
//        } catch (IOException ex) {
//            Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        pnlThemKhachHang = new pnlThemKhachHang(tk);
//        pnlTraCuuNhanVien = new pnlTraCuuNhanVien(tk);
//        try {
//            pnlThemNhanVien = new pnlThemNhanVien(tk);
//        } catch (IOException ex) {
//            Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            pnlCapNhatNhanVien = new pnlCapNhatNhanVien(tk);
//        } catch (IOException ex) {
//            Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        pnlThongKe = new pnlThongKe(tk);
//        pnlTaiKhoan = new pnlTaiKhoan(tk, nv);
//       
//        cardLayout = new CardLayout();
//        mainPanel = new JPanel(cardLayout);
//        mainPanel.add(pnlTrangChu,"Trang chủ");
//        mainPanel.add(pnlTraCuuSanPham,"Tra cứu sản phẩm");
//        mainPanel.add(pnlThemSanPham,"Thêm sản phẩm");
//        mainPanel.add(pnlCapNhatSanPham,"Cập nhật sản phẩm");
//        mainPanel.add(pnlLapHoaDon,"Lập hóa đơn");
//        mainPanel.add(pnlDanhSachHoaDon,"Danh sách hóa đơn");
//        mainPanel.add(pnlTraCuuKhachHang,"Tra cứu khách hàng");
//        mainPanel.add(pnlThemKhachHang,"Thêm khách hàng");
//        mainPanel.add(pnlThongKe,"Thống kê");
//        mainPanel.add(pnlTaiKhoan,"Tài khoản");
//        mainPanel.add(pnlTraCuuNhanVien,"Tra cứu nhân viên");
//        mainPanel.add(pnlThemNhanVien,"Thêm nhân viên");
//        mainPanel.add(pnlCapNhatNhanVien,"Cập nhật nhân viên");
//       // mainPanel.add(pnlTrangChu,"Trang chủ");

        menuChucVu();
//        pnlBody.add(mainPanel,BorderLayout.CENTER);4

addTableStyle(jScrollPane1);
       
    }
    private void thoiGianLamViec(){
        thoiGianHoatDong_DAO = new ThoiGianHoatDong_DAO();
        
        //Lấy thời gian hiện tại 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime loCalTime = LocalTime.now();
        String catChuoi = loCalTime+"";
        catChuoi = catChuoi.substring(0, 8); 
        thoiGianDangNhap = LocalTime.parse(catChuoi, formatter);
        
        // lấy ngày hiện tại 
        LocalDate localDate = LocalDate.now();
        //Khai báo entity
        tghd = new ThoiGianHoatDong(null, nv, localDate, null, null);
        //nếu tồn tại thời gian lấy ra  không thì set thời thời gian làm ngày đó là 00:00:00
        if(nv.getChucVu().getMaChucVu().equals("QL")){
            LocalTime startTime = LocalTime.of(0, 0, 0);
            LocalTime endTime = LocalTime.of(23, 59, 59);
            
            thoiGianThietLap(localDate, loCalTime, formatter,startTime+"",endTime+"");
        }else if(nv.getChucVu().getMaChucVu().equals("NV")){
            //thời gian hiện tại
            LocalTime now = LocalTime.now();
            //thời gian ca 1
            LocalTime startTimeCa1 = LocalTime.of(6, 0, 0);
            LocalTime endTimeCa1 = LocalTime.of(13, 59, 59);
            //thời gian ca 2
            LocalTime endTimeCa2 = LocalTime.of(14, 0, 0);
            LocalTime startTimeCa2 = LocalTime.of(21, 59, 59);
            //thời gian tăng ca
            LocalTime endTimeCa3 = LocalTime.of(22, 0, 0);
            LocalTime startTimeCa3 = LocalTime.of(5, 59, 59);
            
            if (isBetween(now, startTimeCa1, endTimeCa1)){
                thoiGianThietLap(localDate, loCalTime, formatter,startTimeCa1+"",endTimeCa1+"");
            }else if(isBetween(now, startTimeCa2, endTimeCa2)){
                thoiGianThietLap(localDate, loCalTime, formatter,startTimeCa2+"",endTimeCa2+"");
            }else{
                thoiGianThietLap(localDate, loCalTime, formatter,startTimeCa3+"",endTimeCa3+"");
                System.out.println("3");
            }
                     
            
        }
        
    }
    public void thoiGianThietLap(LocalDate localDate,LocalTime loCalTime,DateTimeFormatter formatter,String startTimeCa1,String endTimeCa1  ){
            if(thoiGianHoatDong_DAO.kiemTraDangNhapTrongNgay(tghd,startTimeCa1,endTimeCa1)){
                lblThoiGianDaLam.setText(tghd.getThoiGianDaLam()+"");
            }else{
                //mã thời gian 221003 0600 232108 (22103 ngày tháng) (0600 thời gian đăng nhập 06:00 (hh:mm)) (232108 mã nhân viên)
                LocalDate localDateNam = LocalDate.now();
                String formattedTime = loCalTime.format(formatter);
                String nam = localDate.getYear()+"";
               
                Date currentDate = new Date();
                SimpleDateFormat formatterDay = new SimpleDateFormat("ddMMyy");
                String formattedDate = formatterDay.format(currentDate);
        
                String maLamViec = formattedDate+ formattedTime.substring(0,2) +formattedTime.substring(3,5) + nv.getMaNV();
                
                tghd = new ThoiGianHoatDong(maLamViec, nv, localDateNam, thoiGianDangNhap);
                
                thoiGianHoatDong_DAO.insertThoiGianLam(tghd);
                
                lblThoiGianDaLam.setText("00:00:00");
        }
    }
    public static boolean isBetween(LocalTime time, LocalTime startTime, LocalTime endTime) {
        return !time.isBefore(startTime) && !time.isAfter(endTime);
    }
    private void thoiGianHeader(){
        lblChucVu.setText(nv.getChucVu().getMaChucVu()+" :");
        lblTenNhanVien.setText(nv.getHoTenNhanVien());
        System.out.println("Họ tên nhân viên : "+nv.getHoTenNhanVien());
        System.out.println("Chức vụ "+nv.getChucVu().getMaChucVu());
        
        pnlBody.add(new Pannel.pnlTrangChu(tk,lblThoiGianDaLam));
        pnlBody.repaint();
        pnlBody.revalidate();
        
       
        // Đặt ngôn ngữ và khu vực sang tiếng Việt
	        Locale vietnameseLocale = new Locale("vi", "VN");
	        // Sử dụng ngôn ngữ và khu vực đã đặt để định dạng lại thứ
                Date currentDate = new Date();
	        SimpleDateFormat formatterDayVN = new SimpleDateFormat("EEEE", vietnameseLocale);
	        String dayOfWeekVN = formatterDayVN.format(currentDate);
                
         lblThu.setText(dayOfWeekVN);
         
         //Ngày tháng năm hiện tại
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-yy");
	String formattedDate = formatter.format(currentDate);
        
        
        // Tạo một đối tượng Timer với khoảng thời gian 1000ms (1 giây)
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy thời gian hiện tại
                LocalDateTime now = LocalDateTime.now();

                // Định dạng thời gian
                DateTimeFormatter formatterSecond = DateTimeFormatter.ofPattern("HH:mm:ss");
                String formattedTime = now.format(formatterSecond);

                // Hiển thị thời gian trên label
                lblNgayThangNam.setText(formattedDate +" "+ formattedTime);
            }
        });

        // Bắt đầu đồng hồ
        timer.start();
        
        pnlHeader.repaint();
        pnlHeader.revalidate();   
        

        
        
    }
    private void setbackground(MenuItem menuItem1,MenuItem menuItem2,MenuItem menuItem3,MenuItem menuItem4,MenuItem menuItem5,MenuItem menuItem6,MenuItem menuItem7,MenuItem menuItem8,MenuItem menuItem9,MenuItem menuItem10,MenuItem menuItem11
            ,MenuItem menuItem12,MenuItem menuItem13,MenuItem menuItem14,MenuItem menuItem15,MenuItem menuItem16,MenuItem menuItem17,MenuItem menuItem18,MenuItem menuItem19,MenuItem menuItem20){
        menuItem1.setBackground(Color.red);
        menuItem2.setBackground(Color.red);
        menuItem3.setBackground(pnlMenus.getBackground());
        menuItem4.setBackground(pnlMenus.getBackground());
        menuItem5.setBackground(pnlMenus.getBackground());
        menuItem6.setBackground(pnlMenus.getBackground());
        menuItem7.setBackground(pnlMenus.getBackground());
        menuItem8.setBackground(pnlMenus.getBackground());
        menuItem9.setBackground(pnlMenus.getBackground());
        menuItem10.setBackground(pnlMenus.getBackground());
        menuItem11.setBackground(pnlMenus.getBackground());
        menuItem12.setBackground(pnlMenus.getBackground());
        menuItem13.setBackground(pnlMenus.getBackground());
        menuItem14.setBackground(pnlMenus.getBackground());
        menuItem15.setBackground(pnlMenus.getBackground());
        menuItem16.setBackground(pnlMenus.getBackground());
        menuItem17.setBackground(pnlMenus.getBackground());
        menuItem18.setBackground(pnlMenus.getBackground());
        menuItem19.setBackground(pnlMenus.getBackground());
        menuItem20.setBackground(pnlMenus.getBackground()); 
    }
    private void menuChucVu(){
        //Trang chủ
        javax.swing.ImageIcon iconTrangChu = new javax.swing.ImageIcon(getClass().getResource("/IMG/iconTrangChu.png"));
        
        //Tạo icon cho sản phẩm
        javax.swing.ImageIcon iconSanPham = new javax.swing.ImageIcon(getClass().getResource("/IMG/iconSanPham.png"));
        javax.swing.ImageIcon iconTraCuuSanPham = new javax.swing.ImageIcon(getClass().getResource("/IMG/iconTraCuuSanPham.png"));
        javax.swing.ImageIcon iconThemSanPham = new javax.swing.ImageIcon(getClass().getResource("/IMG/iconSanPham.png"));
        
        //Hóa đơn
        javax.swing.ImageIcon iconHoaDon = new javax.swing.ImageIcon(getClass().getResource("/IMG/iconHoaDon.png"));
        javax.swing.ImageIcon iconLapHoaDon = new javax.swing.ImageIcon(getClass().getResource("/IMG/iconHoaDon.png"));
        javax.swing.ImageIcon iconDanhSachHoaDon = new javax.swing.ImageIcon(getClass().getResource("/IMG/iconHoaDon.png"));
        
        //Khách hàng
        javax.swing.ImageIcon iconKhachHang = new javax.swing.ImageIcon(getClass().getResource("/IMG/iconKhachHang.png"));
        javax.swing.ImageIcon iconTraCuuKhachHang = new javax.swing.ImageIcon(getClass().getResource("/IMG/iconTraCuuKhachHang.png"));
        javax.swing.ImageIcon iconThemKhachHang = new javax.swing.ImageIcon(getClass().getResource("/IMG/iconKhachHang.png"));

        //Nhân viên
        javax.swing.ImageIcon iconNhanVien = new javax.swing.ImageIcon(getClass().getResource("/IMG/iconNhanVien.png"));
        javax.swing.ImageIcon iconTraCuuNhanVien = new javax.swing.ImageIcon(getClass().getResource("/IMG/iconNhanVien.png"));
        javax.swing.ImageIcon iconThemNhanVien = new javax.swing.ImageIcon(getClass().getResource("/IMG/iconNhanVien.png"));
        
        //Thống kê
        javax.swing.ImageIcon iconDoanhThu = new javax.swing.ImageIcon(getClass().getResource("/IMG/iconThongKe.png"));
        javax.swing.ImageIcon iconThongKe = new javax.swing.ImageIcon(getClass().getResource("/IMG/iconThongKe.png"));
        javax.swing.ImageIcon iconBaoCao = new javax.swing.ImageIcon(getClass().getResource("/IMG/iconThongKe.png"));
       
        // Tài khoản
        javax.swing.ImageIcon iconTaiKhoan = new javax.swing.ImageIcon(getClass().getResource("/IMG/iconTaiKhoan.png"));
        
        //Quy định 
        javax.swing.ImageIcon iconQuyDinh = new javax.swing.ImageIcon(getClass().getResource("/IMG/iconHoaDon.png"));

        //Trợ giúp
        javax.swing.ImageIcon iconTroGiup = new javax.swing.ImageIcon(getClass().getResource("/IMG/iconTroGiup.png"));
        
        
        
        //tạo submenu cho trang chủ
        menuTrangChu = new MenuItem(iconTrangChu, "Trang chủ",new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent e) {                 
                try {            UIManager.setLookAndFeel(originalLookAndFeel);        
                } catch (UnsupportedLookAndFeelException ex) {            
                    Logger.getLogger(pnTraCuuNhanVien.class.getName()).log(Level.SEVERE, null, ex);        
                }
            pnlBody.removeAll();
            pnlBody.add(new Pannel.pnlTrangChu(tk,lblThoiGianDaLam ));
            System.out.println(tk.getTenTK());
            pnlBody.repaint();
            pnlBody.revalidate();
                 System.out.println();
//        cardLayout.show(mainPanel, "Trang chủ");

//        setbackground(menuTrangChu,menuNhanVien,menuCapNhatNhanVien,menuSanPham, menuHoaDon, menuKhachHang,menuThongKe,menuTaiKhoan,menuTroGiup, menuTraCuuSanPham,
//                        menuThemSanPham,menuLapHoaDon, menuCapNhatSanPham, menuDanhSachHoaDon,menuTraCuuKhachHang,menuThemKhachHang,menuTraCuuNhanVien, menuThemNhanVien, menuDSThongKe,menuDSTaiKhoan);
//                        menuNhanVien.setBackground(pnlMenus.getBackground());
            }
        });
        
        
        //---------------------------------------------------------------------------------------------------//
        
        //tạo submenu cho sản phẩm
        menuTraCuuSanPham = new MenuItem(iconTraCuuSanPham, "Tra cứu sản phẩm",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                 try {            UIManager.setLookAndFeel(originalLookAndFeel);        } catch (UnsupportedLookAndFeelException ex) {            Logger.getLogger(pnTraCuuNhanVien.class.getName()).log(Level.SEVERE, null, ex);        }
                 try {
            UIManager.setLookAndFeel(originalLookAndFeel);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(pnTraCuuNhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }
                pnlBody.removeAll();
                try {
                    pnlBody.add(new pnlTraCuuSanPham(tk));
                } catch (IOException ex) {
                    Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(tk.getTenTK());
                pnlBody.repaint();
                pnlBody.revalidate();
//              cardLayout.show(mainPanel, "Tra cứu sản phẩm");
//                setbackground(menuTraCuuSanPham,menuSanPham,menuTrangChu,menuHoaDon, menuKhachHang, menuNhanVien,menuThongKe,menuTaiKhoan,menuTroGiup, menuThemSanPham,
//                        menuCapNhatSanPham,menuLapHoaDon, menuDanhSachHoaDon, menuTraCuuKhachHang,menuThemKhachHang,menuTraCuuNhanVien,menuThemNhanVien, menuCapNhatNhanVien, menuDSThongKe,menuDSTaiKhoan);
//               
            }
        });
        
        menuThemSanPham= new MenuItem(iconThemSanPham, "Thêm sản phẩm", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             
                 try {
            UIManager.setLookAndFeel(originalLookAndFeel);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(pnTraCuuNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
                pnlBody.removeAll();
                pnlBody.add(new pnlThemSanPham(tk));
                pnlBody.repaint();
                pnlBody.revalidate();
//                setbackground(menuThemSanPham,menuSanPham,menuTrangChu,menuHoaDon, menuKhachHang, menuNhanVien,menuThongKe,menuTaiKhoan,menuTroGiup, menuTraCuuSanPham,
//                        menuCapNhatSanPham,menuLapHoaDon, menuDanhSachHoaDon, menuTraCuuKhachHang,menuThemKhachHang,menuTraCuuNhanVien,menuThemNhanVien, menuCapNhatNhanVien, menuDSThongKe,menuDSTaiKhoan);

//            cardLayout.show(mainPanel, "Thêm sản phẩm");
            }
        });
        
        //Menu cha
         menuSanPham = new MenuItem(iconSanPham, "Sản phẩm",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                  setbackground(menuSanPham,menuThongKe,menuCapNhatNhanVien,menuDSThongKe, menuHoaDon, menuKhachHang,menuTaiKhoan,menuNhanVien,menuTroGiup, menuThongKe,
//                        menuThongKe,menuLapHoaDon, menuThongKe, menuDanhSachHoaDon,menuTraCuuKhachHang,menuThemKhachHang,menuTraCuuNhanVien, menuThemNhanVien, menuDSTaiKhoan,menuTrangChu);
//                  menuThongKe.setBackground(pnlMenus.getBackground());
            }
        },menuTraCuuSanPham,menuThemSanPham);
        
         
        //------------------------------------------------------------------------------------------------//
        //tạo submenu cho hóa đơn
        menuLapHoaDon = new MenuItem(iconLapHoaDon, "Lập hóa đơn",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                 try {            UIManager.setLookAndFeel(originalLookAndFeel);        } catch (UnsupportedLookAndFeelException ex) {            Logger.getLogger(pnTraCuuNhanVien.class.getName()).log(Level.SEVERE, null, ex);        }
                pnlBody.removeAll();
                pnlBody.add(new pnlLapHoaDon(tk,nv,originalLookAndFeel));
                pnlBody.repaint();
                pnlBody.revalidate(); 
//                     cardLayout.show(mainPanel, "Lập hóa đơn");
            
//            setbackground(menuLapHoaDon,menuHoaDon,menuTrangChu,menuSanPham, menuKhachHang, menuNhanVien,menuThongKe,menuTaiKhoan,menuTroGiup, menuTraCuuSanPham,
//               menuThemSanPham,menuCapNhatSanPham, menuDanhSachHoaDon, menuTraCuuKhachHang,menuThemKhachHang,menuTraCuuNhanVien,menuThemNhanVien, menuCapNhatNhanVien, menuDSThongKe,menuDSTaiKhoan);                                
            }
        });
       menuDanhSachHoaDon =  new MenuItem(iconDanhSachHoaDon, "Danh sách hóa đơn",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                 try {            UIManager.setLookAndFeel(originalLookAndFeel);        } catch (UnsupportedLookAndFeelException ex) {            Logger.getLogger(pnTraCuuNhanVien.class.getName()).log(Level.SEVERE, null, ex);        }
                pnlBody.removeAll();
                pnlBody.add(new pnlDanhSachHoaDon(tk,nv));
               // System.out.println(tk.getTenTK());
                pnlBody.repaint();
                pnlBody.revalidate(); 
            //                            cardLayout.show(mainPanel,"Danh sách hóa đơn");
//            setbackground(menuDanhSachHoaDon,menuHoaDon,menuTrangChu,menuSanPham, menuKhachHang, menuNhanVien,menuThongKe,menuTaiKhoan,menuTroGiup, menuTraCuuSanPham,
//                                    menuThemSanPham,menuLapHoaDon, menuCapNhatSanPham, menuTraCuuKhachHang,menuThemKhachHang,menuTraCuuNhanVien,menuThemNhanVien, menuCapNhatNhanVien, menuDSThongKe,menuDSTaiKhoan);                 
            }
        });
        
       //Menu HoaDon cha
       menuHoaDon = new MenuItem(iconHoaDon, "Hóa đơn",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                 setbackground(menuHoaDon,menuThongKe,menuCapNhatNhanVien,menuDSThongKe, menuSanPham, menuKhachHang,menuTaiKhoan,menuNhanVien,menuTroGiup, menuTraCuuSanPham,
//                        menuThemSanPham,menuThongKe, menuCapNhatSanPham, menuThongKe,menuTraCuuKhachHang,menuThemKhachHang,menuTraCuuNhanVien, menuThemNhanVien, menuDSTaiKhoan,menuTrangChu);
//                  menuThongKe.setBackground(pnlMenus.getBackground());
            }
        },menuLapHoaDon,menuDanhSachHoaDon);
       
        //------------------------------------------------------------------------------------------------// 
        //tạo submenu cho khachHang
        menuTraCuuKhachHang = new MenuItem(iconTraCuuKhachHang, "Tra cứu khách hàng", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                 try {            UIManager.setLookAndFeel(originalLookAndFeel);        } catch (UnsupportedLookAndFeelException ex) {            Logger.getLogger(pnTraCuuNhanVien.class.getName()).log(Level.SEVERE, null, ex);        }
                pnlBody.removeAll();
                try {
                    pnlBody.add(new pnlTraCuuKhachHang(tk));
                } catch (IOException ex) {
                    Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(tk.getTenTK());
                pnlBody.repaint();
                pnlBody.revalidate(); 
//                  cardLayout.show(mainPanel,"Tra cứu khách hàng");
//        setbackground(menuTraCuuKhachHang,menuKhachHang,menuTrangChu,menuSanPham, menuHoaDon, menuNhanVien,menuThongKe,menuTaiKhoan,menuTroGiup, menuTraCuuSanPham,
//                        menuThemSanPham,menuLapHoaDon, menuCapNhatSanPham, menuDanhSachHoaDon,menuThemKhachHang,menuTraCuuNhanVien,menuThemNhanVien, menuCapNhatNhanVien, menuDSThongKe,menuDSTaiKhoan);  
            }
        });
       
        menuThemKhachHang = new MenuItem(iconThemKhachHang, "Thêm khách hàng", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                 
                try {            
                    UIManager.setLookAndFeel(originalLookAndFeel);        } catch (UnsupportedLookAndFeelException ex) {            
                    Logger.getLogger(pnTraCuuNhanVien.class.getName()).log(Level.SEVERE, null, ex);        }
                    pnlBody.removeAll();
                try {
                    pnlBody.add(new pnlThemKhachHang(tk));
                } catch (IOException ex) {
                    Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
                }
               // System.out.println(tk.getTenTK());
                pnlBody.repaint();
                pnlBody.revalidate(); 
//                cardLayout.show(mainPanel,"Thêm khách hàng");
//        setbackground(menuThemKhachHang,menuKhachHang,menuTrangChu,menuSanPham, menuHoaDon, menuNhanVien,menuThongKe,menuTaiKhoan,menuTroGiup, menuTraCuuSanPham,
//                        menuThemSanPham,menuLapHoaDon, menuCapNhatSanPham, menuDanhSachHoaDon,menuTraCuuKhachHang,menuTraCuuNhanVien,menuThemNhanVien, menuCapNhatNhanVien, menuDSThongKe,menuDSTaiKhoan);
            }
        });
       //menuKhachHang cha
        menuKhachHang = new MenuItem(iconKhachHang, "Khách hàng",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                setbackground(menuKhachHang,menuThongKe,menuCapNhatNhanVien,menuDSThongKe, menuSanPham, menuHoaDon,menuTaiKhoan,menuNhanVien,menuTroGiup, menuTraCuuSanPham,
//                        menuThemSanPham,menuLapHoaDon, menuCapNhatSanPham, menuDanhSachHoaDon,menuThongKe,menuThongKe,menuTraCuuNhanVien, menuThemNhanVien, menuDSTaiKhoan,menuTrangChu);
//                  menuThongKe.setBackground(pnlMenus.getBackground());
            }
        },menuTraCuuKhachHang,menuThemKhachHang);
      
        //------------------------------------------------------------------------------------------------//    
        //tạo submenu cho nhân viên
        menuTraCuuNhanVien = new MenuItem(iconTraCuuNhanVien, "Tra cứu nhân viên", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                 
                try {           
                    UIManager.setLookAndFeel(originalLookAndFeel);        
                } catch (UnsupportedLookAndFeelException ex) {            
                    Logger.getLogger(pnTraCuuNhanVien.class.getName()).log(Level.SEVERE, null, ex);        }
                pnlBody.removeAll();
                try {
                    pnlBody.add(new pnTraCuuNhanVien(tk));
                } catch (IOException ex) {
                    Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
                }
               //pnlBody.add(new pnlThongKeTheoNam());
                System.out.println(tk.getTenTK());
                pnlBody.repaint();
                pnlBody.revalidate(); 
//                cardLayout.show(mainPanel,"Tra cứu nhân viên");
//        setbackground(menuTraCuuNhanVien,menuNhanVien,menuTrangChu,menuSanPham, menuHoaDon, menuKhachHang,menuThongKe,menuTaiKhoan,menuTroGiup, menuTraCuuSanPham,
//                        menuThemSanPham,menuLapHoaDon, menuCapNhatSanPham, menuDanhSachHoaDon,menuTraCuuKhachHang,menuThemKhachHang,menuThemNhanVien, menuCapNhatNhanVien, menuDSThongKe,menuDSTaiKhoan);
            }
        });
        menuThemNhanVien =  new MenuItem(iconThemNhanVien, "Thêm nhân viên", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                 
                try {            UIManager.setLookAndFeel(originalLookAndFeel);        
                } catch (UnsupportedLookAndFeelException ex) {            
                    Logger.getLogger(pnTraCuuNhanVien.class.getName()).log(Level.SEVERE, null, ex);        
                }
                    pnlBody.removeAll();
                try {
                    try {
                        pnlBody.add(new pnlThemNhanVien(tk));
                    } catch (SQLException ex) {
                        Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(tk.getTenTK());
                pnlBody.repaint();
                pnlBody.revalidate();
                
//        cardLayout.show(mainPanel,"Thêm nhân viên");
//        setbackground(menuThemNhanVien,menuNhanVien,menuTrangChu,menuSanPham, menuHoaDon, menuKhachHang,menuThongKe,menuTaiKhoan,menuTroGiup, menuTraCuuSanPham,
//                        menuThemSanPham,menuLapHoaDon, menuCapNhatSanPham, menuDanhSachHoaDon,menuTraCuuKhachHang,menuThemKhachHang,menuTraCuuNhanVien, menuCapNhatNhanVien, menuDSThongKe,menuDSTaiKhoan);
            }
        });
        
        //MenuNhanVien cha
        menuNhanVien = new MenuItem(iconNhanVien, "Nhân viên",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                setbackground(menuNhanVien,menuThongKe,menuCapNhatNhanVien,menuDSThongKe, menuSanPham, menuHoaDon,menuTaiKhoan,menuKhachHang,menuTroGiup, menuTraCuuSanPham,
//                        menuThemSanPham,menuLapHoaDon, menuCapNhatSanPham, menuDanhSachHoaDon,menuTraCuuKhachHang,menuThemKhachHang,menuTraCuuNhanVien, menuThemNhanVien, menuDSTaiKhoan,menuTrangChu);
//                  menuThongKe.setBackground(pnlMenus.getBackground());
            }
        },menuTraCuuNhanVien,menuThemNhanVien);
        
        //------------------------------------------------------------------------------------------------//
        //tạo submenu cho Doanh thu
        menuThongKe = new MenuItem(iconThongKe, "Thông kê",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 try {
                    UIManager.setLookAndFeel(originalLookAndFeel);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(pnTraCuuNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }
                pnlBody.removeAll();
                pnlBody.add(new pnlThongKe(tk));
                pnlBody.repaint();
                pnlBody.revalidate(); 
//               setbackground(menuThongKe,menuKhachHang,menuCapNhatNhanVien,menuCapNhatNhanVien, menuSanPham, menuHoaDon,menuTaiKhoan,menuNhanVien,menuTroGiup, menuTraCuuSanPham,
//                        menuThemSanPham,menuLapHoaDon, menuCapNhatSanPham, menuDanhSachHoaDon,menuTraCuuKhachHang,menuThemKhachHang,menuTraCuuNhanVien, menuThemNhanVien, menuDSTaiKhoan,menuTrangChu);
//                  menuKhachHang.setBackground(pnlMenus.getBackground());
            }
        });
        menuBaoCao = new  MenuItem(iconSanPham, "Báo cáo",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 try {
                    UIManager.setLookAndFeel(originalLookAndFeel);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(pnTraCuuNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
                pnlBody.removeAll();
                pnlBody.add(new pnlBaoCao());
                System.out.println(tk.getTenTK());
                pnlBody.repaint();
                pnlBody.revalidate(); 
  //        cardLayout.show(mainPanel,"Thống kê");
//              setbackground(menuDSThongKe,menuThongKe,menuCapNhatNhanVien,menuSanPham, menuHoaDon, menuKhachHang,menuTaiKhoan,menuNhanVien,menuTroGiup, menuTraCuuSanPham,
//                          menuThemSanPham,menuLapHoaDon, menuCapNhatSanPham, menuDanhSachHoaDon,menuTraCuuKhachHang,menuThemKhachHang,menuTraCuuNhanVien, menuThemNhanVien, menuDSTaiKhoan,menuTrangChu);
//              
            }
        });
        
        //MenuDoanhThu cha
        menuDoanhThu = new MenuItem(iconThongKe, "Doanh thu",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        },menuThongKe, menuBaoCao);
      
        
      
        
        //menuTaiKhoan cha
        menuTaiKhoan = new MenuItem(iconTaiKhoan, "Tài khoản",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                 try {
                UIManager.setLookAndFeel(originalLookAndFeel);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(pnTraCuuNhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }
                pnlBody.removeAll();
                pnlBody.add(new pnlTaiKhoan(tk,nv));
                System.out.println(tk.getTenTK());
                pnlBody.repaint();
                pnlBody.revalidate(); 
//        cardLayout.show(mainPanel,"Tài khoản");
//                 setbackground(menuTaiKhoan,menuThongKe,menuCapNhatNhanVien,menuDSThongKe, menuSanPham, menuHoaDon,menuKhachHang,menuNhanVien,menuTroGiup, menuTraCuuSanPham,
//                        menuThemSanPham,menuLapHoaDon, menuCapNhatSanPham, menuDanhSachHoaDon,menuTraCuuKhachHang,menuThemKhachHang,menuTraCuuNhanVien, menuThemNhanVien, menuThongKe,menuTrangChu);
//                  menuThongKe.setBackground(pnlMenus.getBackground());
            }
        });
        
        
        //menuQuyDinh cha
        menuQuyDinh = new MenuItem(iconQuyDinh,"Quy định", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {            UIManager.setLookAndFeel(originalLookAndFeel);        
                } catch (UnsupportedLookAndFeelException ex) {            
                    Logger.getLogger(pnTraCuuNhanVien.class.getName()).log(Level.SEVERE, null, ex);        
                }
                    pnlBody.removeAll();
                    pnlBody.add(new pnlQuyDinh());
                    System.out.println(tk.getTenTK());
                    pnlBody.repaint();
                    pnlBody.revalidate();
                    System.out.println();
            }
        });
        
        //menuTroGiup cha
        menuTroGiup = new MenuItem(iconTroGiup, "Trợ Giúp",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//               setbackground(menuTroGiup,menuThongKe,menuCapNhatNhanVien,menuDSThongKe, menuSanPham, menuHoaDon,menuTaiKhoan,menuNhanVien,menuKhachHang, menuTraCuuSanPham,
//                        menuThemSanPham,menuLapHoaDon, menuCapNhatSanPham, menuDanhSachHoaDon,menuTraCuuKhachHang,menuThemKhachHang,menuTraCuuNhanVien, menuThemNhanVien, menuDSTaiKhoan,menuTrangChu);
//                  menuThongKe.setBackground(pnlMenus.getBackground());
            }
        });
        
        if(nv.getChucVu().getMaChucVu().equals("QL")){
            addMenu(menuTrangChu,menuSanPham,menuHoaDon,menuKhachHang,menuNhanVien,menuDoanhThu,menuTaiKhoan,menuQuyDinh ,menuTroGiup);
        }else{
            addMenu(menuTrangChu,menuSanPham,menuHoaDon,menuKhachHang,menuThongKe,menuTroGiup);
        }
       // addMenu(menuTrangChu,menuSanPham,menuHoaDon,menuKhachHang,menuNhanVien,menuThongKe,menuTaiKhoan,menuTroGiup);
       
         
    }
    private  void gioDaLam(){
        //String timeString = "01:00:00"; // Thời gian 01:00:00
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        
        int thoiGianLam = 0;
        try {
            Date parsedTime = format.parse(lblThoiGianDaLam.getText()); // Chuyển đổi chuỗi thời gian thành đối tượng Date
            int hours = parsedTime.getHours(); // Lấy giờ
            int minutes = parsedTime.getMinutes(); // Lấy phút
            int seconds = parsedTime.getSeconds(); // Lấy giây

            thoiGianLam = hours * 60 * 60 * 1000 + minutes * 60 * 1000 + seconds * 1000;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        startTime = System.currentTimeMillis() - thoiGianLam; // 3600000 milliseconds = 1 giờ
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startTime;
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                format.setTimeZone(TimeZone.getTimeZone("UTC"));
                String timeText = format.format(new Date(elapsedTime));
                lblThoiGianDaLam.setText( timeText);
                
            }
        });

        timer.start();
    }
    private void addMenu(MenuItem...menu){
        for(int i = 0; i < menu.length;i++){
            pnlMenus.add(menu[i]);
            ArrayList<MenuItem> subMenu = menu[i].getSubMenu();
            for(MenuItem m:subMenu){
                addMenu(m);
            }
        }
        pnlMenus.revalidate();
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        lblChucVu = new javax.swing.JLabel();
        lblTenNhanVien = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblThu = new javax.swing.JLabel();
        lblNgayThangNam = new javax.swing.JLabel();
        pnlMenu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlMenus = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnDangXuat = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        pnlBody = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlHeader.setBackground(new java.awt.Color(255, 0, 153));
        pnlHeader.setPreferredSize(new java.awt.Dimension(802, 50));

        lblChucVu.setText("Chức vụ");

        lblTenNhanVien.setText("Tên nhân viên");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Hiệu Sách");

        lblThu.setText("Thứ");

        lblNgayThangNam.setText("jLabel3");

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeaderLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlHeaderLayout.createSequentialGroup()
                        .addComponent(lblChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(pnlHeaderLayout.createSequentialGroup()
                        .addComponent(lblThu, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNgayThangNam, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenNhanVien)
                    .addComponent(lblChucVu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThu)
                    .addComponent(lblNgayThangNam))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pnlHeader, java.awt.BorderLayout.PAGE_START);

        pnlMenu.setBackground(new java.awt.Color(255, 0, 255));
        pnlMenu.setPreferredSize(new java.awt.Dimension(250, 425));
        pnlMenu.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(300, 602));

        pnlMenus.setBackground(new java.awt.Color(255, 255, 255));
        pnlMenus.setPreferredSize(new java.awt.Dimension(300, 400));
        pnlMenus.setRequestFocusEnabled(false);
        pnlMenus.setLayout(new javax.swing.BoxLayout(pnlMenus, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(pnlMenus);

        pnlMenu.add(jScrollPane1, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(50, 50));

        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });

        jButton2.setText("Đổi mật khẩu");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(208, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDangXuat)
                .addGap(18, 18, 18))
        );

        pnlMenu.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlMenu, java.awt.BorderLayout.LINE_START);

        pnlBody.setBackground(new java.awt.Color(246, 246, 246));
        pnlBody.setLayout(new java.awt.BorderLayout());
        getContentPane().add(pnlBody, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(677, 943));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        // TODO add your handling code here:
        try {
            UIManager.setLookAndFeel(originalLookAndFeel);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(pnTraCuuNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
       int hoiDeDangXuat =   JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất không?","Thông báo",JOptionPane.YES_NO_OPTION);
       if(hoiDeDangXuat == JOptionPane.YES_OPTION){
           thoiGianHoatDong_DAO = new ThoiGianHoatDong_DAO();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            tghd.setThoiGianDaLam(LocalTime.parse( lblThoiGianDaLam.getText(), formatter));
            LocalTime localTime = LocalTime.now();
            String loCalTime =  localTime.format(formatter);
            tghd.setThoiGianDangXuat(LocalTime.parse(loCalTime, formatter));
            thoiGianHoatDong_DAO.capNhatThoiGianLam(tghd);
            taiKhoan_DAO = new TaiKhoan_DAO();
           try {
               taiKhoan_DAO.updataTinhTrangDangNhap(tk.getTenTK(),"Đã đăng xuất");
           } catch (SQLException ex) {
               Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
           }
            DangNhap1 dangNhap = new DangNhap1 ();
            this.setVisible(false);
            dangNhap.setVisible(true);
       }
    }//GEN-LAST:event_btnDangXuatActionPerformed
    public void addTableStyle(JScrollPane scroll) {
        scroll.getViewport().setOpaque(false);
        scroll.setViewportBorder(null); 
        scroll.setBorder(null);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        JPanel panel = new JPanel();
        panel.setBackground(new Color(60, 60, 60));
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, panel);
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            UIManager.setLookAndFeel(originalLookAndFeel);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(pnTraCuuNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        DoiMatKhau doiMatKhau = new DoiMatKhau (tk);
        doiMatKhau.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    public void showThemKhachHang(){
        
     
     
       
   
             
 
       
    }
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        taiKhoan_DAO = new TaiKhoan_DAO();
        thoiGianHoatDong_DAO = new ThoiGianHoatDong_DAO();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        tghd.setThoiGianDaLam(LocalTime.parse( lblThoiGianDaLam.getText(), formatter));
       
        LocalTime localTime = LocalTime.now();
        String loCalTime =  localTime.format(formatter);
        
        tghd.setThoiGianDangXuat(LocalTime.parse(loCalTime, formatter));
        thoiGianHoatDong_DAO.capNhatThoiGianLam(tghd);
       
        try {
            taiKhoan_DAO.updataTinhTrangDangNhap(tk.getTenTK(), "Đã đăng xuất");
        } catch (SQLException ex) {
            
        }
    }//GEN-LAST:event_formWindowClosing
    
    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblNgayThangNam;
    private javax.swing.JLabel lblTenNhanVien;
    private javax.swing.JLabel lblThu;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlMenus;
    // End of variables declaration//GEN-END:variables
}
