
package Pannel;

import Entity.NhanVien;
import Entity.TaiKhoan;
import java.awt.Color;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class pnlTraCuuSanPham extends javax.swing.JPanel {

    private TaiKhoan tk;
    private NhanVien nv;
    
    public pnlTraCuuSanPham(TaiKhoan tk) throws IOException {
        this.tk = tk;
        this.nv = nv;
        initComponents();
        pnlSouth.add(new Pannel.pnlTraCuuSach());
        pnlSouth.repaint();
        pnlSouth.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSach = new javax.swing.JButton();
        btnVanPhongPham = new javax.swing.JButton();
        pnlSouth = new javax.swing.JPanel();

        btnSach.setText("Sách");
        btnSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSachActionPerformed(evt);
            }
        });

        btnVanPhongPham.setText("Văn Phòng Phẩm");
        btnVanPhongPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVanPhongPhamActionPerformed(evt);
            }
        });

        pnlSouth.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSouth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnSach)
                .addGap(18, 18, 18)
                .addComponent(btnVanPhongPham)
                .addContainerGap(171, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSach)
                    .addComponent(btnVanPhongPham))
                .addGap(18, 18, 18)
                .addComponent(pnlSouth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSachActionPerformed
        // TODO add your handling code here:
        pnlSouth.removeAll();
        btnSach.setBackground(Color.red);
        btnVanPhongPham.setBackground(Color.white);
        try {
            pnlSouth.add(new Pannel.pnlTraCuuSach());
        } catch (IOException ex) {
            Logger.getLogger(pnlTraCuuSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(tk.getTenTK());
        pnlSouth.repaint();
        pnlSouth.revalidate();
    }//GEN-LAST:event_btnSachActionPerformed

    private void btnVanPhongPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVanPhongPhamActionPerformed
        // TODO add your handling code here:
        pnlSouth.removeAll();
        btnVanPhongPham.setBackground(Color.red);
        btnSach.setBackground(Color.white);

        try {
            pnlSouth.add(new Pannel.pnlThemVanPhongPham());
        } catch (SQLException ex) {
            Logger.getLogger(pnlTraCuuSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            pnlSouth.add(new Pannel.pnlTraCuuVanPhongPham());
        } catch (IOException ex) {
            Logger.getLogger(pnlTraCuuSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(tk.getTenTK());
        pnlSouth.repaint();
        pnlSouth.revalidate();
    }//GEN-LAST:event_btnVanPhongPhamActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSach;
    private javax.swing.JButton btnVanPhongPham;
    private javax.swing.JPanel pnlSouth;
    // End of variables declaration//GEN-END:variables
}
