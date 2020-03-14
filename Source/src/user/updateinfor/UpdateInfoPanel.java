/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.updateinfor;

import model.User;;
import model.Borrower;
import model.BorrowingCard;
import java.awt.Color;
import javax.swing.BorderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Thanh Tung
 */
public class UpdateInfoPanel extends javax.swing.JPanel {
    
    public static UserInfo tt = new UserInfo();
    User taikhoanKhoiTao=new User();
    Borrower nguoimuonKhoiTao=new Borrower();
    BorrowingCard themuonKhoiTao=new BorrowingCard();
    public UpdateInfoPanel() {
        this.setLayout(null);
        SearchBorrower search = new SearchBorrower();
        tt.setBorder(BorderFactory.createLineBorder(Color.black));
        search.setBounds(0, 0, 800, 80);
        tt.setBounds(0, 80, 800, 141);
        taikhoanKhoiTao.setIsMale(true);
        tt.setThongTin(taikhoanKhoiTao, nguoimuonKhoiTao, themuonKhoiTao);
        tt.jLabel13.setText("");
        
        /**
         * Khởi tạo màn hình trống ban đầu
         */
        search.getTextField1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    search.getTaiKhoan(search.jTextField1.getText());
                    UpdateInfoPanel.tt.setThongTin(UpdateInfoFrame.taikhoan,UpdateInfoFrame.nguoimuon,UpdateInfoFrame.themuon);
                    UpdateInfoFrame.fixinfo.setThongTin(UpdateInfoFrame.taikhoan,UpdateInfoFrame.nguoimuon,UpdateInfoFrame.themuon);
                    System.out.println();
                } catch (SQLException ex) {
                    Logger.getLogger(UpdateInfoPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UpdateInfoPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
        );
        this.add(search);
        this.add(tt);
          
              


}


}
