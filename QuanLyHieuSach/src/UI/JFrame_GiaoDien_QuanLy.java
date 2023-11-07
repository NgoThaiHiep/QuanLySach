/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import Entity.NhanVien;
import Entity.TaiKhoan;
import Menu.MenuItem;
import Pannel.pnlCapNhatNhanVien;
import Pannel.pnlCapNhatSach;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author PC
 */
public class JFrame_GiaoDien_QuanLy extends javax.swing.JFrame {

	private MenuItem menuHomePage,
            menuDanhMuc, menuNhanVien, menuKhachHang,menuPhong, menuDichVu,
            menuXuLy,menuDatPhong, menuHoaDon,
            menuTraCuu, menuTraCuuNhanVien, menuTraCuuKhachHang, menuTraCuuPhong, menuTraCuuDichVu, menuTraCuuHoaDon,
            menuThongKe,menuDoanhThu, menuDichVuUaThich, menuKhachHangTiemNang, menuDoanhThuTheoNgay, menuDoanhThuTheoThang, menuTroGiup;
	
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private NhanVien nv;
    private pnlCapNhatNhanVien capNhatNhanVien = null;
    private final pnlCapNhatSach sach;
    private pnlCapNhatNhanVien themNhanVien = null;

//	private JPanel_ThanhToanHoaDon thanhToanHoaDon;
//
//	private JPanel_TraCuuHoaDon traCuuHoaDon;
    /**
     * Creates new form JFrame_GiaoDienChinh
     */
    public JFrame_GiaoDien_QuanLy(NhanVien nv) throws SQLException {
    	this.nv = nv;
        initComponents(nv);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
            try {
                capNhatNhanVien = new pnlCapNhatNhanVien(tk);
            } catch (IOException ex) {
                Logger.getLogger(JFrame_GiaoDien_QuanLy.class.getName()).log(Level.SEVERE, null, ex);
            }
        sach = new pnlCapNhatSach();
            try {
                themNhanVien = new pnlCapNhatNhanVien(tk);
//        thanhToanHoaDon = new JPanel_ThanhToanHoaDon();
//        traCuuHoaDon = new JPanel_TraCuuHoaDon();
            } catch (IOException ex) {
                Logger.getLogger(JFrame_GiaoDien_QuanLy.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        mainPanel.add(capNhatNhanVien, "Trang chủ");
        mainPanel.add(sach, "Danh mục phòng hát");
        mainPanel.add(themNhanVien,"Thêm nhân viên");
//        mainPanel.add(thanhToanHoaDon, "Thanh toán hóa đơn");
//        mainPanel.add(traCuuHoaDon, "Tra cứu hóa đơn");
        execute();
        jPanelbody.add(mainPanel, BorderLayout.CENTER);
    }
    private void execute(){
        //icon
        ImageIcon iconHomePage = new ImageIcon("item/home-30.png");
        ImageIcon iconDanhMuc = new ImageIcon("item/medium-priority-30.png");
        ImageIcon iconNhanVien = new ImageIcon("item/management-30.png");
        ImageIcon iconKhachHang = new ImageIcon("item/client-30.png");
        ImageIcon iconPhong = new ImageIcon("item/room-30.png");
        ImageIcon iconDichVu = new ImageIcon("item/food-30.png");
        ImageIcon iconXuLy = new ImageIcon("item/service-30.png");
        ImageIcon iconTraCuu = new ImageIcon("item/search-30.png");
        ImageIcon iconThongKe = new ImageIcon("item/analytics-30.png");
        ImageIcon iconDoanhThu = new ImageIcon("item/refund-30.png");
        ImageIcon iconTroGiup = new ImageIcon("item/help-30.png");
        ImageIcon iconRong = new ImageIcon("");
          //menu trang chủ          
        menuHomePage = new MenuItem(iconHomePage, "Trang chủ", new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  // xét background cho menu cha
            	  menuHomePage.setBackground(new Color(162, 74, 74));
            	  menuDanhMuc.setBackground(menus.getBackground());
            	  menuXuLy.setBackground(menus.getBackground());
            	  menuTraCuu.setBackground(menus.getBackground());
            	  menuThongKe.setBackground(menus.getBackground());
            	  menuTroGiup.setBackground(menus.getBackground());
            	  // xét background cho menu con của menuDanhMuc
            	  menuNhanVien.setBackground(Color.white);
            	  menuKhachHang.setBackground(Color.white);
            	  menuPhong.setBackground(Color.white);
            	  menuDichVu.setBackground(Color.white);
            	  // xét background cho menu con của menuXuLy
            	  menuDatPhong.setBackground(Color.white);
            	  menuHoaDon.setBackground(Color.white);
            	  // xét background cho menu con của menuTraCuu
            	  menuTraCuuNhanVien.setBackground(Color.white);
            	  menuTraCuuKhachHang.setBackground(Color.white);
            	  menuTraCuuPhong.setBackground(Color.white);
            	  menuTraCuuDichVu.setBackground(Color.white);
            	  menuTraCuuHoaDon.setBackground(Color.white);
            	  // xét background cho menu con của menuThongKe
            	  menuDoanhThu.setBackground(Color.white);
            	  menuDichVuUaThich.setBackground(Color.white);
            	  menuKhachHangTiemNang.setBackground(Color.white);
            	  menuDoanhThuTheoNgay.setBackground(Color.white);
            	  menuDoanhThuTheoThang.setBackground(Color.white);
            	  
            	  cardLayout.show(mainPanel, "Trang chủ");
              }
          });

          // menu danh mục
        menuNhanVien = new MenuItem(iconNhanVien, "Nhân viên", new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  menuNhanVien.setBackground(new Color(51, 245, 65));
            	  // xét background cho menu cha
            	  menuDanhMuc.setBackground(new Color(162, 74, 74));
            	  menuHomePage.setBackground(menus.getBackground());
            	  menuXuLy.setBackground(menus.getBackground());
            	  menuTraCuu.setBackground(menus.getBackground());
            	  menuThongKe.setBackground(menus.getBackground());
            	  menuTroGiup.setBackground(menus.getBackground());
            	  // xét background cho menu con của menuDanhMuc
            	  menuKhachHang.setBackground(Color.white);
            	  menuPhong.setBackground(Color.white);
            	  menuDichVu.setBackground(Color.white);
            	  // xét background cho menu con của menuXuLy
            	  menuDatPhong.setBackground(Color.white);
            	  menuHoaDon.setBackground(Color.white);
            	  // xét background cho menu con của menuTraCuu
            	  menuTraCuuNhanVien.setBackground(Color.white);
            	  menuTraCuuKhachHang.setBackground(Color.white);
            	  menuTraCuuPhong.setBackground(Color.white);
            	  menuTraCuuDichVu.setBackground(Color.white);
            	  menuTraCuuHoaDon.setBackground(Color.white);
            	  // xét background cho menu con của menuThongKe
            	  menuDoanhThu.setBackground(Color.white);
            	  menuDichVuUaThich.setBackground(Color.white);
            	  menuKhachHangTiemNang.setBackground(Color.white);
            	  menuDoanhThuTheoNgay.setBackground(Color.white);
            	  menuDoanhThuTheoThang.setBackground(Color.white);
              }
          });
        menuKhachHang = new MenuItem(iconKhachHang, "Khách hàng", new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  menuKhachHang.setBackground(new Color(51, 245, 65));
            	  // xét background cho menu cha
            	  menuDanhMuc.setBackground(new Color(162, 74, 74));
            	  menuHomePage.setBackground(menus.getBackground());
            	  menuXuLy.setBackground(menus.getBackground());
            	  menuTraCuu.setBackground(menus.getBackground());
            	  menuThongKe.setBackground(menus.getBackground());
            	  menuTroGiup.setBackground(menus.getBackground());
            	  // xét background cho menu con của menuDanhMuc
            	  menuNhanVien.setBackground(Color.white);
            	  menuPhong.setBackground(Color.white);
            	  menuDichVu.setBackground(Color.white);
            	  // xét background cho menu con của menuXuLy
            	  menuDatPhong.setBackground(Color.white);
            	  menuHoaDon.setBackground(Color.white);
            	  // xét background cho menu con của menuTraCuu
            	  menuTraCuuNhanVien.setBackground(Color.white);
            	  menuTraCuuKhachHang.setBackground(Color.white);
            	  menuTraCuuPhong.setBackground(Color.white);
            	  menuTraCuuDichVu.setBackground(Color.white);
            	  menuTraCuuHoaDon.setBackground(Color.white);
            	  // xét background cho menu con của menuThongKe
            	  menuDoanhThu.setBackground(Color.white);
            	  menuDichVuUaThich.setBackground(Color.white);
            	  menuKhachHangTiemNang.setBackground(Color.white);
            	  menuDoanhThuTheoNgay.setBackground(Color.white);
            	  menuDoanhThuTheoThang.setBackground(Color.white);
                  
                   cardLayout.show(mainPanel, "Thêm nhân viên");
              }
          });
        menuPhong = new MenuItem(iconPhong, "Phòng hát", new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  menuPhong.setBackground(new Color(51, 245, 65));
            	// xét background cho menu cha
            	  menuHomePage.setBackground(menus.getBackground());
            	  menuDanhMuc.setBackground(new Color(162, 74, 74));
            	  menuXuLy.setBackground(menus.getBackground());
            	  menuTraCuu.setBackground(menus.getBackground());
            	  menuThongKe.setBackground(menus.getBackground());
            	  menuTroGiup.setBackground(menus.getBackground());
            	  // xét background cho menu con của menuDanhMuc
            	  menuNhanVien.setBackground(Color.white);
            	  menuKhachHang.setBackground(Color.white);
            	  menuDichVu.setBackground(Color.white);
            	// xét background cho menu con của menuXuLy
            	  menuDatPhong.setBackground(Color.white);
            	  menuHoaDon.setBackground(Color.white);
            	// xét background cho menu con của menuTraCuu
            	  menuTraCuuNhanVien.setBackground(Color.white);
            	  menuTraCuuKhachHang.setBackground(Color.white);
            	  menuTraCuuPhong.setBackground(Color.white);
            	  menuTraCuuDichVu.setBackground(Color.white);
            	  menuTraCuuHoaDon.setBackground(Color.white);
            	// xét background cho menu con của menuThongKe
            	  menuDoanhThu.setBackground(Color.white);
            	  menuDichVuUaThich.setBackground(Color.white);
            	  menuKhachHangTiemNang.setBackground(Color.white);
            	  menuDoanhThuTheoNgay.setBackground(Color.white);
            	  menuDoanhThuTheoThang.setBackground(Color.white);
            	  
            	  
            	  cardLayout.show(mainPanel, "Danh mục phòng hát");
              }
          });
        menuDichVu = new MenuItem(iconDichVu, "Dịch vụ", new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  menuDichVu.setBackground(new Color(51, 245, 65));
            	  // xét background cho menu cha
            	  menuDanhMuc.setBackground(new Color(162, 74, 74));
            	  menuHomePage.setBackground(menus.getBackground());
            	  menuXuLy.setBackground(menus.getBackground());
            	  menuTraCuu.setBackground(menus.getBackground());
            	  menuThongKe.setBackground(menus.getBackground());
            	  menuTroGiup.setBackground(menus.getBackground());
            	  // xét background cho menu con của menuDanhMuc
            	  menuNhanVien.setBackground(Color.white);
            	  menuPhong.setBackground(Color.white);
            	  menuKhachHang.setBackground(Color.white);
            	  // xét background cho menu con của menuXuLy
            	  menuDatPhong.setBackground(Color.white);
            	  menuHoaDon.setBackground(Color.white);
            	  // xét background cho menu con của menuTraCuu
            	  menuTraCuuNhanVien.setBackground(Color.white);
            	  menuTraCuuKhachHang.setBackground(Color.white);
            	  menuTraCuuPhong.setBackground(Color.white);
            	  menuTraCuuDichVu.setBackground(Color.white);
            	  menuTraCuuHoaDon.setBackground(Color.white);
            	  // xét background cho menu con của menuThongKe
            	  menuDoanhThu.setBackground(Color.white);
            	  menuDichVuUaThich.setBackground(Color.white);
            	  menuKhachHangTiemNang.setBackground(Color.white);
            	  menuDoanhThuTheoNgay.setBackground(Color.white);
            	  menuDoanhThuTheoThang.setBackground(Color.white);
              }
          });
        menuDanhMuc = new MenuItem(iconDanhMuc, "Danh mục", new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  menuDanhMuc.setBackground(new Color(162, 74, 74));
            	// xét background cho menu cha
            	  menuHomePage.setBackground(menus.getBackground());
            	  menuXuLy.setBackground(menus.getBackground());
            	  menuTraCuu.setBackground(menus.getBackground());
            	  menuThongKe.setBackground(menus.getBackground());
            	  menuTroGiup.setBackground(menus.getBackground());
            	  // xét background cho menu con của menuDanhMuc
            	  menuNhanVien.setBackground(Color.white);
            	  menuKhachHang.setBackground(Color.white);
            	  menuPhong.setBackground(Color.white);
            	  menuDichVu.setBackground(Color.white);
            	// xét background cho menu con của menuXuLy
            	  menuDatPhong.setBackground(Color.white);
            	  menuHoaDon.setBackground(Color.white);
            	// xét background cho menu con của menuTraCuu
            	  menuTraCuuNhanVien.setBackground(Color.white);
            	  menuTraCuuKhachHang.setBackground(Color.white);
            	  menuTraCuuPhong.setBackground(Color.white);
            	  menuTraCuuDichVu.setBackground(Color.white);
            	  menuTraCuuHoaDon.setBackground(Color.white);
            	// xét background cho menu con của menuThongKe
            	  menuDoanhThu.setBackground(Color.white);
            	  menuDichVuUaThich.setBackground(Color.white);
            	  menuKhachHangTiemNang.setBackground(Color.white);
            	  menuDoanhThuTheoNgay.setBackground(Color.white);
            	  menuDoanhThuTheoThang.setBackground(Color.white);
            	  
              }
          }, menuNhanVien, menuKhachHang,menuPhong, menuDichVu);

        // menu xử lý
        menuDatPhong = new MenuItem(iconNhanVien, "Lập phiếu đặt phòng", new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  menuDatPhong.setBackground(new Color(51, 245, 65));
            	// xét background cho menu cha
            	  menuXuLy.setBackground(new Color(162, 74, 74));
            	  menuDanhMuc.setBackground(menus.getBackground());
            	  menuHomePage.setBackground(menus.getBackground());
            	  menuTraCuu.setBackground(menus.getBackground());
            	  menuThongKe.setBackground(menus.getBackground());
            	  menuTroGiup.setBackground(menus.getBackground());
            	// xét background cho menu con của menuDanhMuc
            	  menuNhanVien.setBackground(Color.white);
            	  menuKhachHang.setBackground(Color.white);
            	  menuPhong.setBackground(Color.white);
            	  menuDichVu.setBackground(Color.white);
            	// xét background cho menu con của menuXuLy
            	  menuHoaDon.setBackground(Color.white);
            	// xét background cho menu con của menuTraCuu
            	  menuTraCuuNhanVien.setBackground(Color.white);
            	  menuTraCuuKhachHang.setBackground(Color.white);
            	  menuTraCuuPhong.setBackground(Color.white);
            	  menuTraCuuDichVu.setBackground(Color.white);
            	  menuTraCuuHoaDon.setBackground(Color.white);
            	// xét background cho menu con của menuThongKe
            	  menuDoanhThu.setBackground(Color.white);
            	  menuDichVuUaThich.setBackground(Color.white);
            	  menuKhachHangTiemNang.setBackground(Color.white);
            	  menuDoanhThuTheoNgay.setBackground(Color.white);
            	  menuDoanhThuTheoThang.setBackground(Color.white);
              }
          });
        menuHoaDon = new MenuItem(iconKhachHang, "Lập hóa đơn", new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  menuHoaDon.setBackground(new Color(51, 245, 65));
              	// xét background cho menu cha
              	  menuXuLy.setBackground(new Color(162, 74, 74));
              	  menuDanhMuc.setBackground(menus.getBackground());
              	  menuHomePage.setBackground(menus.getBackground());
              	  menuTraCuu.setBackground(menus.getBackground());
              	  menuThongKe.setBackground(menus.getBackground());
              	  menuTroGiup.setBackground(menus.getBackground());
              	// xét background cho menu con của menuDanhMuc
              	  menuNhanVien.setBackground(Color.white);
              	  menuKhachHang.setBackground(Color.white);
              	  menuPhong.setBackground(Color.white);
              	  menuDichVu.setBackground(Color.white);
              	// xét background cho menu con của menuXuLy
              	  menuDatPhong.setBackground(Color.white);
              	// xét background cho menu con của menuTraCuu
              	  menuTraCuuNhanVien.setBackground(Color.white);
              	  menuTraCuuKhachHang.setBackground(Color.white);
              	  menuTraCuuPhong.setBackground(Color.white);
              	  menuTraCuuDichVu.setBackground(Color.white);
              	  menuTraCuuHoaDon.setBackground(Color.white);
              	// xét background cho menu con của menuThongKe
              	  menuDoanhThu.setBackground(Color.white);
              	  menuDichVuUaThich.setBackground(Color.white);
              	  menuKhachHangTiemNang.setBackground(Color.white);
              	  menuDoanhThuTheoNgay.setBackground(Color.white);
              	  menuDoanhThuTheoThang.setBackground(Color.white);
              	  
              	  cardLayout.show(mainPanel, "Thanh toán hóa đơn");
              }
          });
        menuXuLy = new MenuItem(iconXuLy, "Xử lý", new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  menuXuLy.setBackground(new Color(162, 74, 74));
            	// xét background cho menu cha
            	  menuDanhMuc.setBackground(menus.getBackground());
            	  menuHomePage.setBackground(menus.getBackground());
            	  menuTraCuu.setBackground(menus.getBackground());
            	  menuThongKe.setBackground(menus.getBackground());
            	  menuTroGiup.setBackground(menus.getBackground());
            	// xét background cho menu con của menuDanhMuc
            	  menuNhanVien.setBackground(Color.white);
            	  menuKhachHang.setBackground(Color.white);
            	  menuPhong.setBackground(Color.white);
            	  menuDichVu.setBackground(Color.white);
            	// xét background cho menu con của menuXuLy
            	  menuDatPhong.setBackground(Color.white);
            	  menuHoaDon.setBackground(Color.white);
            	// xét background cho menu con của menuTraCuu
            	  menuTraCuuNhanVien.setBackground(Color.white);
            	  menuTraCuuKhachHang.setBackground(Color.white);
            	  menuTraCuuPhong.setBackground(Color.white);
            	  menuTraCuuDichVu.setBackground(Color.white);
            	  menuTraCuuHoaDon.setBackground(Color.white);
            	// xét background cho menu con của menuThongKe
            	  menuDoanhThu.setBackground(Color.white);
            	  menuDichVuUaThich.setBackground(Color.white);
            	  menuKhachHangTiemNang.setBackground(Color.white);
            	  menuDoanhThuTheoNgay.setBackground(Color.white);
            	  menuDoanhThuTheoThang.setBackground(Color.white);
              }
          }, menuDatPhong, menuHoaDon);

        // menu tra cứu
        menuTraCuuNhanVien = new MenuItem(iconNhanVien, "Nhân viên", new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  menuTraCuuNhanVien.setBackground(new Color(51, 245, 65));
            	// xét background cho menu cha
            	  menuHomePage.setBackground(menus.getBackground());
            	  menuDanhMuc.setBackground(menus.getBackground());
            	  menuXuLy.setBackground(menus.getBackground());
            	  menuTraCuu.setBackground(new Color(162, 74, 74));
            	  menuThongKe.setBackground(menus.getBackground());
            	  menuTroGiup.setBackground(menus.getBackground());
            	// xét background cho menu con của menuDanhMuc
            	  menuNhanVien.setBackground(Color.white);
            	  menuKhachHang.setBackground(Color.white);
            	  menuDichVu.setBackground(Color.white);
            	  menuPhong.setBackground(Color.white);
            	// xét background cho menu con của menuXuLy
            	  menuDatPhong.setBackground(Color.white);
            	  menuHoaDon.setBackground(Color.white);
            	// xét background cho menu con của menuTraCuu
            	  menuTraCuuPhong.setBackground(Color.white);
            	  menuTraCuuKhachHang.setBackground(Color.white);
            	  menuTraCuuDichVu.setBackground(Color.white);
            	  menuTraCuuHoaDon.setBackground(Color.white);
            	// xét background cho menu con của menuThongKe
            	  menuDoanhThu.setBackground(Color.white);
            	  menuDichVuUaThich.setBackground(Color.white);
            	  menuKhachHangTiemNang.setBackground(Color.white);
            	  menuDoanhThuTheoNgay.setBackground(Color.white);
            	  menuDoanhThuTheoThang.setBackground(Color.white);
              }
          });
        menuTraCuuKhachHang = new MenuItem(iconKhachHang, "Khách hàng", new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  menuTraCuuKhachHang.setBackground(new Color(51, 245, 65));
              	// xét background cho menu cha
              	  menuHomePage.setBackground(menus.getBackground());
              	  menuDanhMuc.setBackground(menus.getBackground());
              	  menuXuLy.setBackground(menus.getBackground());
              	  menuTraCuu.setBackground(new Color(162, 74, 74));
              	  menuThongKe.setBackground(menus.getBackground());
              	  menuTroGiup.setBackground(menus.getBackground());
              	// xét background cho menu con của menuDanhMuc
              	  menuNhanVien.setBackground(Color.white);
              	  menuKhachHang.setBackground(Color.white);
              	  menuDichVu.setBackground(Color.white);
              	  menuPhong.setBackground(Color.white);
              	// xét background cho menu con của menuXuLy
              	  menuDatPhong.setBackground(Color.white);
              	  menuHoaDon.setBackground(Color.white);
              	// xét background cho menu con của menuTraCuu
              	  menuTraCuuPhong.setBackground(Color.white);
              	  menuTraCuuNhanVien.setBackground(Color.white);
              	  menuTraCuuDichVu.setBackground(Color.white);
              	  menuTraCuuHoaDon.setBackground(Color.white);
              	// xét background cho menu con của menuThongKe
              	  menuDoanhThu.setBackground(Color.white);
              	  menuDichVuUaThich.setBackground(Color.white);
              	  menuKhachHangTiemNang.setBackground(Color.white);
              	  menuDoanhThuTheoNgay.setBackground(Color.white);
              	  menuDoanhThuTheoThang.setBackground(Color.white);
              }
          });
        menuTraCuuPhong = new MenuItem(iconPhong, "Phòng", new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  menuTraCuuPhong.setBackground(new Color(51, 245, 65));
            	// xét background cho menu cha
            	  menuHomePage.setBackground(menus.getBackground());
            	  menuDanhMuc.setBackground(menus.getBackground());
            	  menuXuLy.setBackground(menus.getBackground());
            	  menuTraCuu.setBackground(new Color(162, 74, 74));
            	  menuThongKe.setBackground(menus.getBackground());
            	  menuTroGiup.setBackground(menus.getBackground());
            	// xét background cho menu con của menuDanhMuc
            	  menuNhanVien.setBackground(Color.white);
            	  menuKhachHang.setBackground(Color.white);
            	  menuDichVu.setBackground(Color.white);
            	  menuPhong.setBackground(Color.white);
            	// xét background cho menu con của menuXuLy
            	  menuDatPhong.setBackground(Color.white);
            	  menuHoaDon.setBackground(Color.white);
            	// xét background cho menu con của menuTraCuu
            	  menuTraCuuNhanVien.setBackground(Color.white);
            	  menuTraCuuKhachHang.setBackground(Color.white);
            	  menuTraCuuDichVu.setBackground(Color.white);
            	  menuTraCuuHoaDon.setBackground(Color.white);
            	// xét background cho menu con của menuThongKe
            	  menuDoanhThu.setBackground(Color.white);
            	  menuDichVuUaThich.setBackground(Color.white);
            	  menuKhachHangTiemNang.setBackground(Color.white);
            	  menuDoanhThuTheoNgay.setBackground(Color.white);
            	  menuDoanhThuTheoThang.setBackground(Color.white);
            	  
            	  
            	  cardLayout.show(mainPanel, "Tra cứu phòng hát");
              }
          });
        menuTraCuuDichVu = new MenuItem(iconDichVu, "Dịch vụ", new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  menuTraCuuDichVu.setBackground(new Color(51, 245, 65));
            	// xét background cho menu cha
            	  menuHomePage.setBackground(menus.getBackground());
            	  menuDanhMuc.setBackground(menus.getBackground());
            	  menuXuLy.setBackground(menus.getBackground());
            	  menuTraCuu.setBackground(new Color(162, 74, 74));
            	  menuThongKe.setBackground(menus.getBackground());
            	  menuTroGiup.setBackground(menus.getBackground());
            	// xét background cho menu con của menuDanhMuc
            	  menuNhanVien.setBackground(Color.white);
            	  menuKhachHang.setBackground(Color.white);
            	  menuDichVu.setBackground(Color.white);
            	  menuPhong.setBackground(Color.white);
            	// xét background cho menu con của menuXuLy
            	  menuDatPhong.setBackground(Color.white);
            	  menuHoaDon.setBackground(Color.white);
            	// xét background cho menu con của menuTraCuu
            	  menuTraCuuNhanVien.setBackground(Color.white);
            	  menuTraCuuKhachHang.setBackground(Color.white);
            	  menuTraCuuPhong.setBackground(Color.white);
            	  menuTraCuuHoaDon.setBackground(Color.white);
            	// xét background cho menu con của menuThongKe
            	  menuDoanhThu.setBackground(Color.white);
            	  menuDichVuUaThich.setBackground(Color.white);
            	  menuKhachHangTiemNang.setBackground(Color.white);
            	  menuDoanhThuTheoNgay.setBackground(Color.white);
            	  menuDoanhThuTheoThang.setBackground(Color.white);
            	  
            	  
            	  cardLayout.show(mainPanel, "Tra cứu dịch vụ");
              }
          });
        menuTraCuuHoaDon = new MenuItem(iconKhachHang, "Hóa đơn", new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  menuTraCuuHoaDon.setBackground(new Color(51, 245, 65));
              	// xét background cho menu cha
              	  menuHomePage.setBackground(menus.getBackground());
              	  menuDanhMuc.setBackground(menus.getBackground());
              	  menuXuLy.setBackground(menus.getBackground());
              	  menuTraCuu.setBackground(new Color(162, 74, 74));
              	  menuThongKe.setBackground(menus.getBackground());
              	  menuTroGiup.setBackground(menus.getBackground());
              	// xét background cho menu con của menuDanhMuc
              	  menuNhanVien.setBackground(Color.white);
              	  menuKhachHang.setBackground(Color.white);
              	  menuDichVu.setBackground(Color.white);
              	  menuPhong.setBackground(Color.white);
              	// xét background cho menu con của menuXuLy
              	  menuDatPhong.setBackground(Color.white);
              	  menuHoaDon.setBackground(Color.white);
              	// xét background cho menu con của menuTraCuu
              	  menuTraCuuNhanVien.setBackground(Color.white);
              	  menuTraCuuKhachHang.setBackground(Color.white);
              	  menuTraCuuPhong.setBackground(Color.white);
              	  menuTraCuuDichVu.setBackground(Color.white);
              	// xét background cho menu con của menuThongKe
              	  menuDoanhThu.setBackground(Color.white);
              	  menuDichVuUaThich.setBackground(Color.white);
              	  menuKhachHangTiemNang.setBackground(Color.white);
              	  menuDoanhThuTheoNgay.setBackground(Color.white);
              	  menuDoanhThuTheoThang.setBackground(Color.white);
              	  
              	  cardLayout.show(mainPanel, "Tra cứu hóa đơn");
              }
          });
        menuTraCuu = new MenuItem(iconTraCuu, "Tra cứu", new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  menuTraCuu.setBackground(new Color(162, 74, 74));
            	// xét background cho menu cha
            	  menuDanhMuc.setBackground(menus.getBackground());
            	  menuXuLy.setBackground(menus.getBackground());
            	  menuHomePage.setBackground(menus.getBackground());
            	  menuThongKe.setBackground(menus.getBackground());
            	  menuTroGiup.setBackground(menus.getBackground());
            	// xét background cho menu con của menuDanhMuc
            	  menuNhanVien.setBackground(Color.white);
            	  menuKhachHang.setBackground(Color.white);
            	  menuPhong.setBackground(Color.white);
            	  menuDichVu.setBackground(Color.white);
            	// xét background cho menu con của menuXuLy
            	  menuDatPhong.setBackground(Color.white);
            	  menuHoaDon.setBackground(Color.white);
            	// xét background cho menu con của menuTraCuu
            	  menuTraCuuNhanVien.setBackground(Color.white);
            	  menuTraCuuKhachHang.setBackground(Color.white);
            	  menuTraCuuPhong.setBackground(Color.white);
            	  menuTraCuuDichVu.setBackground(Color.white);
            	  menuTraCuuHoaDon.setBackground(Color.white);
            	// xét background cho menu con của menuThongKe
            	  menuDoanhThu.setBackground(Color.white);
            	  menuDichVuUaThich.setBackground(Color.white);
            	  menuKhachHangTiemNang.setBackground(Color.white);
            	  menuDoanhThuTheoNgay.setBackground(Color.white);
            	  menuDoanhThuTheoThang.setBackground(Color.white);
              }
          }, menuTraCuuNhanVien, menuTraCuuKhachHang, menuTraCuuPhong, menuTraCuuDichVu, menuTraCuuHoaDon);
        // menu thống kê
        menuDoanhThuTheoNgay = new MenuItem(iconRong,"Doanh thu theo ngày", new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  menuDoanhThu.setBackground(new Color(51, 245, 65));
            	  // xét background cho menu cha
            	  menuThongKe.setBackground(new Color(162, 74, 74));
              	  menuDanhMuc.setBackground(menus.getBackground());
              	  menuXuLy.setBackground(menus.getBackground());
              	  menuTraCuu.setBackground(menus.getBackground());
              	  menuHomePage.setBackground(menus.getBackground());
              	  menuTroGiup.setBackground(menus.getBackground());
              	// xét background cho menu con của menuDanhMuc
              	  menuNhanVien.setBackground(Color.white);
              	  menuKhachHang.setBackground(Color.white);
              	  menuPhong.setBackground(Color.white);
              	  menuDichVu.setBackground(Color.white);
              	// xét background cho menu con của menuXuLy
              	  menuDatPhong.setBackground(Color.white);
              	  menuHoaDon.setBackground(Color.white);
              	// xét background cho menu con của menuTraCuu
              	  menuTraCuuNhanVien.setBackground(Color.white);
              	  menuTraCuuKhachHang.setBackground(Color.white);
              	  menuTraCuuPhong.setBackground(Color.white);
              	  menuTraCuuDichVu.setBackground(Color.white);
              	  menuTraCuuHoaDon.setBackground(Color.white);
              	// xét background cho menu con của menuThongKe
              	  menuDichVuUaThich.setBackground(Color.white);
              	  menuKhachHangTiemNang.setBackground(Color.white);
              	  menuDoanhThuTheoNgay.setBackground(Color.CYAN);
              	  menuDoanhThuTheoThang.setBackground(Color.white);
              }
          });
        menuDoanhThuTheoThang = new MenuItem(iconRong, "Doanh thu theo tháng", new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  menuDoanhThu.setBackground(new Color(51, 245, 65));
            	  // xét background cho menu cha
            	  menuThongKe.setBackground(new Color(162, 74, 74));
              	  menuDanhMuc.setBackground(menus.getBackground());
              	  menuXuLy.setBackground(menus.getBackground());
              	  menuTraCuu.setBackground(menus.getBackground());
              	  menuHomePage.setBackground(menus.getBackground());
              	  menuTroGiup.setBackground(menus.getBackground());
              	// xét background cho menu con của menuDanhMuc
              	  menuNhanVien.setBackground(Color.white);
              	  menuKhachHang.setBackground(Color.white);
              	  menuPhong.setBackground(Color.white);
              	  menuDichVu.setBackground(Color.white);
              	// xét background cho menu con của menuXuLy
              	  menuDatPhong.setBackground(Color.white);
              	  menuHoaDon.setBackground(Color.white);
              	// xét background cho menu con của menuTraCuu
              	  menuTraCuuNhanVien.setBackground(Color.white);
              	  menuTraCuuKhachHang.setBackground(Color.white);
              	  menuTraCuuPhong.setBackground(Color.white);
              	  menuTraCuuDichVu.setBackground(Color.white);
              	  menuTraCuuHoaDon.setBackground(Color.white);
              	// xét background cho menu con của menuThongKe
              	  menuDichVuUaThich.setBackground(Color.white);
              	  menuKhachHangTiemNang.setBackground(Color.white);
              	  menuDoanhThuTheoNgay.setBackground(Color.white);
              	  menuDoanhThuTheoThang.setBackground(Color.cyan);
              }
          });
        menuDoanhThu = new MenuItem(iconDoanhThu, "Doanh thu", new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  menuDoanhThu.setBackground(new Color(51, 245, 65));
            	  // xét background cho menu cha
            	  menuThongKe.setBackground(new Color(162, 74, 74));
              	  menuDanhMuc.setBackground(menus.getBackground());
              	  menuXuLy.setBackground(menus.getBackground());
              	  menuTraCuu.setBackground(menus.getBackground());
              	  menuHomePage.setBackground(menus.getBackground());
              	  menuTroGiup.setBackground(menus.getBackground());
              	// xét background cho menu con của menuDanhMuc
              	  menuNhanVien.setBackground(Color.white);
              	  menuKhachHang.setBackground(Color.white);
              	  menuPhong.setBackground(Color.white);
              	  menuDichVu.setBackground(Color.white);
              	// xét background cho menu con của menuXuLy
              	  menuDatPhong.setBackground(Color.white);
              	  menuHoaDon.setBackground(Color.white);
              	// xét background cho menu con của menuTraCuu
              	  menuTraCuuNhanVien.setBackground(Color.white);
              	  menuTraCuuKhachHang.setBackground(Color.white);
              	  menuTraCuuPhong.setBackground(Color.white);
              	  menuTraCuuDichVu.setBackground(Color.white);
              	  menuTraCuuHoaDon.setBackground(Color.white);
              	// xét background cho menu con của menuThongKe
              	  menuDichVuUaThich.setBackground(Color.white);
              	  menuKhachHangTiemNang.setBackground(Color.white);
              	  menuDoanhThuTheoNgay.setBackground(Color.white);
              	  menuDoanhThuTheoThang.setBackground(Color.white);
              }
          }, menuDoanhThuTheoNgay, menuDoanhThuTheoThang);
        menuDichVuUaThich = new MenuItem(iconDichVu, "Dịch vụ ưu thích", new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  menuDichVuUaThich.setBackground(new Color(51, 245, 65));
            	  // xét background cho menu cha
            	  menuThongKe.setBackground(new Color(162, 74, 74));
              	  menuDanhMuc.setBackground(menus.getBackground());
              	  menuXuLy.setBackground(menus.getBackground());
              	  menuTraCuu.setBackground(menus.getBackground());
              	  menuHomePage.setBackground(menus.getBackground());
              	  menuTroGiup.setBackground(menus.getBackground());
              	// xét background cho menu con của menuDanhMuc
              	  menuNhanVien.setBackground(Color.white);
              	  menuKhachHang.setBackground(Color.white);
              	  menuPhong.setBackground(Color.white);
              	  menuDichVu.setBackground(Color.white);
              	// xét background cho menu con của menuXuLy
              	  menuDatPhong.setBackground(Color.white);
              	  menuHoaDon.setBackground(Color.white);
              	// xét background cho menu con của menuTraCuu
              	  menuTraCuuNhanVien.setBackground(Color.white);
              	  menuTraCuuKhachHang.setBackground(Color.white);
              	  menuTraCuuPhong.setBackground(Color.white);
              	  menuTraCuuDichVu.setBackground(Color.white);
              	  menuTraCuuHoaDon.setBackground(Color.white);
              	// xét background cho menu con của menuThongKe
              	  menuDoanhThu.setBackground(Color.white);
              	  menuKhachHangTiemNang.setBackground(Color.white);
              	  menuDoanhThuTheoNgay.setBackground(Color.white);
              	  menuDoanhThuTheoThang.setBackground(Color.white);
              }
          });
        menuKhachHangTiemNang = new MenuItem(iconKhachHang, "Khách hàng tiềm năng", new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  menuKhachHangTiemNang.setBackground(new Color(51, 245, 65));
            	  // xét background cho menu cha
            	  menuThongKe.setBackground(new Color(162, 74, 74));
              	  menuDanhMuc.setBackground(menus.getBackground());
              	  menuXuLy.setBackground(menus.getBackground());
              	  menuTraCuu.setBackground(menus.getBackground());
              	  menuHomePage.setBackground(menus.getBackground());
              	  menuTroGiup.setBackground(menus.getBackground());
              	// xét background cho menu con của menuDanhMuc
              	  menuNhanVien.setBackground(Color.white);
              	  menuKhachHang.setBackground(Color.white);
              	  menuPhong.setBackground(Color.white);
              	  menuDichVu.setBackground(Color.white);
              	// xét background cho menu con của menuXuLy
              	  menuDatPhong.setBackground(Color.white);
              	  menuHoaDon.setBackground(Color.white);
              	// xét background cho menu con của menuTraCuu
              	  menuTraCuuNhanVien.setBackground(Color.white);
              	  menuTraCuuKhachHang.setBackground(Color.white);
              	  menuTraCuuPhong.setBackground(Color.white);
              	  menuTraCuuDichVu.setBackground(Color.white);
              	  menuTraCuuHoaDon.setBackground(Color.white);
              	// xét background cho menu con của menuThongKe
              	  menuDoanhThu.setBackground(Color.white);
              	  menuDichVuUaThich.setBackground(Color.white);
              	  menuDoanhThuTheoNgay.setBackground(Color.white);
              	  menuDoanhThuTheoThang.setBackground(Color.white);
              }
          });
        menuThongKe = new MenuItem(iconThongKe, "Thống kê", new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  menuThongKe.setBackground(new Color(162, 74, 74));
            	// xét background cho menu cha
            	  menuDanhMuc.setBackground(menus.getBackground());
            	  menuXuLy.setBackground(menus.getBackground());
            	  menuTraCuu.setBackground(menus.getBackground());
            	  menuHomePage.setBackground(menus.getBackground());
            	  menuTroGiup.setBackground(menus.getBackground());
            	// xét background cho menu con của menuDanhMuc
            	  menuNhanVien.setBackground(Color.white);
            	  menuKhachHang.setBackground(Color.white);
            	  menuPhong.setBackground(Color.white);
            	  menuDichVu.setBackground(Color.white);
            	// xét background cho menu con của menuXuLy
            	  menuDatPhong.setBackground(Color.white);
            	  menuHoaDon.setBackground(Color.white);
            	// xét background cho menu con của menuTraCuu
            	  menuTraCuuNhanVien.setBackground(Color.white);
            	  menuTraCuuKhachHang.setBackground(Color.white);
            	  menuTraCuuPhong.setBackground(Color.white);
            	  menuTraCuuDichVu.setBackground(Color.white);
            	  menuTraCuuHoaDon.setBackground(Color.white);
            	// xét background cho menu con của menuThongKe
            	  menuDoanhThu.setBackground(Color.white);
            	  menuDichVuUaThich.setBackground(Color.white);
            	  menuKhachHangTiemNang.setBackground(Color.white);
            	  menuDoanhThuTheoNgay.setBackground(Color.white);
            	  menuDoanhThuTheoThang.setBackground(Color.white);
              }
          }, menuDoanhThu, menuDichVuUaThich, menuKhachHangTiemNang);
        // trợ giúp
        menuTroGiup = new MenuItem(iconTroGiup, "Trợ giúp", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	menuTroGiup.setBackground(new Color(162, 74, 74));
          	// xét background cho menu cha
          	  	menuDanhMuc.setBackground(menus.getBackground());
          	  	menuXuLy.setBackground(menus.getBackground());
          	  	menuTraCuu.setBackground(menus.getBackground());
          	  	menuHomePage.setBackground(menus.getBackground());
          	  	menuThongKe.setBackground(menus.getBackground());
          	// xét background cho menu con của menuDanhMuc
          	  	menuNhanVien.setBackground(Color.white);
          	  	menuKhachHang.setBackground(Color.white);
          	  	menuPhong.setBackground(Color.white);
          	  	menuDichVu.setBackground(Color.white);
          	// xét background cho menu con của menuXuLy
          	  	menuDatPhong.setBackground(Color.white);
          	  	menuHoaDon.setBackground(Color.white);
          	// xét background cho menu con của menuTraCuu
          	  	menuTraCuuNhanVien.setBackground(Color.white);
          	  	menuTraCuuKhachHang.setBackground(Color.white);
          	  	menuTraCuuPhong.setBackground(Color.white);
          	  	menuTraCuuDichVu.setBackground(Color.white);
          	  	menuTraCuuHoaDon.setBackground(Color.white);
          	// xét background cho menu con của menuThongKe
          	  	menuDoanhThu.setBackground(Color.white);
          	  	menuDichVuUaThich.setBackground(Color.white);
          	  	menuKhachHangTiemNang.setBackground(Color.white);
          	  	menuDoanhThuTheoNgay.setBackground(Color.white);
          	  	menuDoanhThuTheoThang.setBackground(Color.white);
            }
        });
        
        // xét background cho menu
        menuHomePage.setBackground(menus.getBackground());
        menuDanhMuc.setBackground(menus.getBackground());
        menuXuLy.setBackground(menus.getBackground());
        menuTraCuu.setBackground(menus.getBackground());
        menuThongKe.setBackground(menus.getBackground());
        menuTroGiup.setBackground(menus.getBackground());
        menuDoanhThuTheoNgay.setBackground(Color.LIGHT_GRAY);
        menuDoanhThuTheoThang.setBackground(Color.LIGHT_GRAY);
        
        addMenu(menuHomePage, menuDanhMuc, menuXuLy, menuTraCuu, menuThongKe, menuTroGiup);
    }
        //add tất cả menu
    private void addMenu(MenuItem...menu){
        for (MenuItem menu1 : menu) {
            menus.add(menu1);
            ArrayList<MenuItem> subMenu = menu1.getSubMenu();
            for(MenuItem m : subMenu){
                addMenu(m);
            }
        }
        menus.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents(NhanVien nv) {

        jPanelHeader = new javax.swing.JPanel();
        Logo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanelMenu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menus = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabelAnh = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonDoiMatKhau = new javax.swing.JButton();
        jButtonDangXuat = new javax.swing.JButton();
        jPanelbody = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Giao diện chính");
        setBounds(new java.awt.Rectangle(0, 0, 1550, 800));

        jPanelHeader.setBackground(new java.awt.Color(162, 74, 74));
        jPanelHeader.setPreferredSize(new java.awt.Dimension(1550, 110));
        jPanelHeader.setLayout(new java.awt.BorderLayout());

        Logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Logo.setIcon(new javax.swing.ImageIcon("item/logo75.png")); // NOI18N
        Logo.setToolTipText("");
        Logo.setPreferredSize(new java.awt.Dimension(110, 50));
        jPanelHeader.add(Logo, java.awt.BorderLayout.WEST);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Chương trình quản lý Karaoke");
        jPanelHeader.add(jLabel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanelHeader, java.awt.BorderLayout.PAGE_START);

        jPanelMenu.setPreferredSize(new java.awt.Dimension(300, 755));
        jPanelMenu.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(300, 600));

        menus.setLayout(new javax.swing.BoxLayout(menus, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(menus);

        jPanelMenu.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(162, 74, 74)));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 250));
        jPanel1.setRequestFocusEnabled(false);

        jLabelAnh.setIcon(new javax.swing.ImageIcon("item/programmer-50.png")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(nv.getHoTenNhanVien().toString());

        jButtonDoiMatKhau.setBackground(new java.awt.Color(162, 74, 74));
        jButtonDoiMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonDoiMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDoiMatKhau.setIcon(new javax.swing.ImageIcon("item/key-25.png")); // NOI18N
        jButtonDoiMatKhau.setText("Đổi mật khẩu");
        // đổi mật khẩu
        jButtonDoiMatKhau.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//JButtonDoiMatKhau(e);
			}
		});
        
        jButtonDangXuat.setBackground(new java.awt.Color(162, 74, 74));
        jButtonDangXuat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonDangXuat.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDangXuat.setIcon(new javax.swing.ImageIcon("item/logout-25.png")); // NOI18N
        jButtonDangXuat.setText("Đăng xuất");
        // đăng xuất
        jButtonDangXuat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//JButtonDangXuat(e);
			}
		});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonDoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(48, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabelAnh)
                .addGap(123, 123, 123))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabelAnh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonDoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanelMenu.add(jPanel1, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanelMenu, java.awt.BorderLayout.WEST);

        
        jPanelbody.setBackground(new java.awt.Color(255, 255, 255));
        jPanelbody.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBodyMousePressed(evt);
            }
        });
        jPanelbody.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jPanelbody, java.awt.BorderLayout.CENTER);
        setBounds(0, 0, 1250, 755);
        pack();
        
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logo;
    private javax.swing.JButton jButtonDangXuat;
    private javax.swing.JButton jButtonDoiMatKhau;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelAnh;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelbody;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel menus;

	private TaiKhoan tk;
    // End of variables declaration//GEN-END:variables
    
   
    
    
    private void panelBodyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBodyMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_panelBodyMousePressed
    
}
