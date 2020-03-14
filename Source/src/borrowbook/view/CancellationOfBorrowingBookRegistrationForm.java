/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borrowbook.view;

import borrowbook.controller.CancelBorrowController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Book;
import model.Borrower;
import common.view.LoginForm;

/**
 *
 * @author 2&G
 */
public class CancellationOfBorrowingBookRegistrationForm extends javax.swing.JFrame {

    /**
     * Creates new form HuyDangKyMuonSachForm
     */
    CancelBorrowController info;
    Borrower borrower;
    Book book;

    public CancellationOfBorrowingBookRegistrationForm(Borrower borrower) {
        initComponents();
        setLocationRelativeTo(this);
        info = new CancelBorrowController();
        this.borrower = borrower;
        book = new Book();
        try {
            putIntoTable(info.getListIdCoppyBook(borrower.getIdBorrower()), info.getListBookBorrowed(borrower.getIdBorrower()), info.getDateRegistration(borrower.getIdBorrower()), tableBorrowingBookRegistrationList);    //get ArrayList<Book> by idBorrower
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CancellationOfBorrowingBookRegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Hàm này để đẩy dữ liệu vào bảng thông tin sách đã mượn
     *
     * @param listId là danh sách mã bản sao
     * @param date là ngày đăng ký mượn
     * @param book là danh sách sách mượn ứng với thông tin mượn
     * @param table là bảng hiển thị dữ liệu
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     *
     */
    public void putIntoTable(ArrayList<String> listId, ArrayList<Book> book, String date, JTable table) throws ClassNotFoundException, SQLException {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnCount(0);
        model.setRowCount(0);
        model.addColumn("Mã bản sao sách");
        model.addColumn("Tiêu đề");
        model.addColumn("Tác giả");
        model.addColumn("Nhà xuất bản");
        model.addColumn("Ngày đăng ký mượn");
        // model.addColumn("Ngay muon");
        // model.addColumn("Han tra");
        // model.addColumn("Ngay tra");
        //  model.addColumn("Trạng thái");
        for (int i = 0; i < listId.size(); i++) {
            Vector vec = new Vector();
            vec.add(listId.get(i)); // truyen them 1 arraySach bang cach truy van lay sach tu maNguoiMuon
            vec.add(book.get(i).getTitleBook());
            vec.add(book.get(i).getNameAuthor());
            vec.add(book.get(i).getPublisher().getNamePublisher());

            vec.add(date);
//          vec.add(theMuon.get(i).getLentDate());                         // cần đẩy thêm ngày mượn và ngày trả lấy từ DetailLentBook

            //System.out.println(new CoppyOfBook().getStatusByIdBook("IT1000", 1).toString());
            model.addRow(vec);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableBorrowingBookRegistrationList = new javax.swing.JTable();
        btnSendRequest = new javax.swing.JButton();
        lbBorrowingBookRegistrationList = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuBack = new javax.swing.JMenu();
        mnuBorrowingBookRegistration = new javax.swing.JMenu();
        mnuBorrowingBookRegistrationList = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableBorrowingBookRegistrationList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableBorrowingBookRegistrationList);

        btnSendRequest.setText("Send Request");
        btnSendRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendRequestActionPerformed(evt);
            }
        });

        lbBorrowingBookRegistrationList.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbBorrowingBookRegistrationList.setText("Danh sách đăng kí mượn sách");
        lbBorrowingBookRegistrationList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbBorrowingBookRegistrationListMouseClicked(evt);
            }
        });

        mnuBack.setText("Đăng kí mượn sách");
        mnuBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuBackMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuBack);

        mnuBorrowingBookRegistration.setText("Hủy đăng kí mượn sách");
        mnuBorrowingBookRegistration.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuBorrowingBookRegistrationMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuBorrowingBookRegistration);

        mnuBorrowingBookRegistrationList.setText("Đăng xuất");
        mnuBorrowingBookRegistrationList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuBorrowingBookRegistrationListMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuBorrowingBookRegistrationList);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(237, 237, 237)
                .addComponent(lbBorrowingBookRegistrationList)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSendRequest)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lbBorrowingBookRegistrationList)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(btnSendRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendRequestActionPerformed
        try {
            checkCondition();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CancellationOfBorrowingBookRegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSendRequestActionPerformed

    private void lbBorrowingBookRegistrationListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbBorrowingBookRegistrationListMouseClicked
        // TODO add your handling code here:
        RegistrationOfBorrowingBookForm bookForm;
        try {
            bookForm = new RegistrationOfBorrowingBookForm(borrower);
             bookForm.setVisible(true);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CancellationOfBorrowingBookRegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        this.dispose();
    }//GEN-LAST:event_lbBorrowingBookRegistrationListMouseClicked

    private void mnuBorrowingBookRegistrationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuBorrowingBookRegistrationMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_mnuBorrowingBookRegistrationMouseClicked

    private void mnuBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuBackMouseClicked
        // TODO add your handling code here:
        RegistrationOfBorrowingBookForm bookForm;
        try {
            bookForm = new RegistrationOfBorrowingBookForm(borrower);
             bookForm.setVisible(true);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CancellationOfBorrowingBookRegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        this.dispose();
    }//GEN-LAST:event_mnuBackMouseClicked

    private void mnuBorrowingBookRegistrationListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuBorrowingBookRegistrationListMouseClicked
        // TODO add your handling code here:
        if (chooseDialog("Bạn có chắc chắn muốn đăng xuất") == JOptionPane.YES_OPTION) {
            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_mnuBorrowingBookRegistrationListMouseClicked
    
    private int chooseDialog(String title) {
        int i = JOptionPane.showConfirmDialog(null, title, "Thông báo", JOptionPane.YES_NO_OPTION);
        return i;
    }
    
    /**
     * Hàm này để để kiểm tra dữ liệu chọn từ bảng có hợp lệ để gưi yêu cầu
     * không
     *
     *
     */
    private void checkCondition() throws ClassNotFoundException, SQLException {
        int i = tableBorrowingBookRegistrationList.getSelectedRow();
        String idCopyBook = tableBorrowingBookRegistrationList.getValueAt(i, tableBorrowingBookRegistrationList.getColumn("Mã bản sao sách").getModelIndex()).toString();
        if (i != -1) {
            info.updateDetail(borrower.getIdBorrower(), idCopyBook);
            if (info.getNumberBook(borrower.getIdBorrower()) == 0) {
                info.updateBorrowingInfo(borrower.getIdBorrower());
            }
            info.updateCopyBook(idCopyBook);
            pushNotice("Thành công");
  putIntoTable(info.getListIdCoppyBook(borrower.getIdBorrower()), info.getListBookBorrowed(borrower.getIdBorrower()), info.getDateRegistration(borrower.getIdBorrower()), tableBorrowingBookRegistrationList);    //get ArrayList<Book> by idBorrower

        } else {
            pushNotice("Hãy chọn một cuốn sách");
        }
    }

    private void pushNotice(String content) {
        JOptionPane.showMessageDialog(null, content);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSendRequest;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbBorrowingBookRegistrationList;
    private javax.swing.JMenu mnuBack;
    private javax.swing.JMenu mnuBorrowingBookRegistration;
    private javax.swing.JMenu mnuBorrowingBookRegistrationList;
    private javax.swing.JTable tableBorrowingBookRegistrationList;
    // End of variables declaration//GEN-END:variables

}
