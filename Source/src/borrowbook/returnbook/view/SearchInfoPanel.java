/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borrowbook.returnbook.view;


import model.User;
import model.Borrower;
import model.BorrowingCard;
import java.awt.Color;
import javax.swing.BorderFactory;

/**
 *
 * @author Nguyen Thanh Tung
 */
public class SearchInfoPanel extends javax.swing.JPanel {
    public static InfoUserPanel iuP = new InfoUserPanel();
    public SearchBorrowerP search = new SearchBorrowerP();
    public SearchInfoPanel() {
        initComponents();
        this.setLayout(null);
        
        search.setBounds(0, 0, 800, 80);
        iuP.setBorder(BorderFactory.createLineBorder(Color.black));
        iuP.setBounds(0, 80, 800, 100);
        iuP.setThongTin(ReturnBookFrame.taikhoan, ReturnBookFrame.nguoimuon); 
        ReturnBookFrame.tbib.updateTable();
        this.add(search);
        this.add(iuP);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    public SearchBorrowerP getSearchBorrowerP(){
        return search;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
