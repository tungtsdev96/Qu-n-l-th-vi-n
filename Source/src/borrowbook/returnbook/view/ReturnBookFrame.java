/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borrowbook.returnbook.view;

import model.Book;
import model.User;
import model.BorrowingInformation;
import model.CoppyOfBook;
import model.Borrower;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Nguyen Thanh Tung
 */
public class ReturnBookFrame extends javax.swing.JFrame {
    public static User taikhoan=new User();
    public static Borrower nguoimuon=new Borrower();
    public static BorrowingInformation thongtinmuontrasach=new BorrowingInformation();
    public static ArrayList<String> IdInfoBorrow = new ArrayList<>();
    public static CoppyOfBook bansaosach=new CoppyOfBook();
    public static Book sach = new Book();
    public static ArrayList<Object[]> dataTable = new ArrayList<>();
    public static tableBookInfoBorrow tbib=new tableBookInfoBorrow();
    public static SearchInfoPanel siP=new SearchInfoPanel();
    
    public ReturnBookFrame() {
        initComponents();
        this.setLayout(new GridLayout(2,1));
        setLocation(60,60);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add(siP);
        this.add(tbib);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Trả sách");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
