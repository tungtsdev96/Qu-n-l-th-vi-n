/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.view;

import card.view.SearchBorrowCardForm;
import borrowbook.view.LendBookForm;
import borrowbook.view.BorrowingInformationForm;
import book.addbook.view.AddNewBookForm;
import book.addbook.view.AddCoppyOfBookForm;
import model.Librarian;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import card.view.createnewcard.CreateNewCardFrame;
import borrowbook.returnbook.view.ReturnBookFrame;
import view.updateinfor.UpdateInfoFrame;

/**
 *
 * @author tungts
 */
public class MainFormOfLibrarian extends javax.swing.JFrame {

    private Librarian librarian;
    private LendBookForm lendBookForm;
    private BorrowingInformationForm borrowingInformationForm;
    private AddNewBookForm addNewBookForm;
    private AddCoppyOfBookForm addCoppyOfBookForm;
    private HomeForm homeForm;
    private SearchBorrowCardForm searchBorrowCardForm;

    /**
     * Creates new form Main
     */
    public MainFormOfLibrarian() {
        initComponents();
        setLocationRelativeTo(this);
    }

    public MainFormOfLibrarian(Librarian thuThu) {
        initComponents();
        setLocationRelativeTo(this);
        setTitleScreen("Thủ thư - " + thuThu.getName());
        homeForm = new HomeForm(this);
        lendBookForm = new LendBookForm(this);
        borrowingInformationForm = new BorrowingInformationForm(this);
        addNewBookForm = new AddNewBookForm(this);
        addCoppyOfBookForm = new AddCoppyOfBookForm(this);
        searchBorrowCardForm = new SearchBorrowCardForm();

        this.librarian = thuThu;
        tfNameLibrarian.setText(thuThu.getName());
        panelContent.setLayout(new GridBagLayout());

        panelContent.add(homeForm);
        homeForm.setPreferredSize(new Dimension(1050, 490));
        homeForm.setVisible(false);

        panelContent.add(lendBookForm);
        lendBookForm.setPreferredSize(new Dimension(1050, 490));
        lendBookForm.setVisible(false);

        panelContent.add(borrowingInformationForm);
        borrowingInformationForm.setPreferredSize(new Dimension(1050, 490));
        borrowingInformationForm.setVisible(false);

        panelContent.add(addCoppyOfBookForm);
        addCoppyOfBookForm.setPreferredSize(new Dimension(1050, 490));
        addCoppyOfBookForm.setVisible(false);

        panelContent.add(addNewBookForm);
        addNewBookForm.setPreferredSize(new Dimension(1050, 490));
        addNewBookForm.setVisible(false);

        panelContent.add(searchBorrowCardForm);
        searchBorrowCardForm.setPreferredSize(new Dimension(1050, 490));
        searchBorrowCardForm.setVisible(false);

        setContentsPanel(0);
    }

    public void setContentsPanel(int position) {
        switch (position) {
            case 0: {
                try {
                    homeForm.setVisible(true);
                    homeForm.loadBook();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainFormOfLibrarian.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MainFormOfLibrarian.class.getName()).log(Level.SEVERE, null, ex);
                }
                lendBookForm.setVisible(false);
                borrowingInformationForm.setVisible(false);
                addCoppyOfBookForm.setVisible(false);
                addNewBookForm.setVisible(false);
                searchBorrowCardForm.setVisible(false);
                setTitleScreen("Trang chủ");
                break;
            }
            case 1: {
                homeForm.setVisible(false);
                lendBookForm.setVisible(true);
                borrowingInformationForm.setVisible(false);
                addCoppyOfBookForm.setVisible(false);
                addNewBookForm.setVisible(false);
                searchBorrowCardForm.setVisible(false);
                setTitleScreen("Cho mượn sách");
                break;
            }
            case 2: {
                homeForm.setVisible(false);
                lendBookForm.setVisible(false);
                borrowingInformationForm.setVisible(true);
                addCoppyOfBookForm.setVisible(false);
                addNewBookForm.setVisible(false);
                searchBorrowCardForm.setVisible(false);
                setTitleScreen("Tìm kiếm thông tin mượn sách");
                break;
            }
            case 3: {
                homeForm.setVisible(false);
                lendBookForm.setVisible(false);
                borrowingInformationForm.setVisible(false);
                addCoppyOfBookForm.setVisible(true);
                addNewBookForm.setVisible(false);
                searchBorrowCardForm.setVisible(false);
                setTitleScreen("Thêm bản sao sách đã có");
                break;
            }
            case 4: {
                homeForm.setVisible(false);
                lendBookForm.setVisible(false);
                borrowingInformationForm.setVisible(false);
                addCoppyOfBookForm.setVisible(false);
                addNewBookForm.setVisible(true);
                searchBorrowCardForm.setVisible(false);
                setTitleScreen("Thêm mới sách");
                break;
            }
            case 5: {
                homeForm.setVisible(false);
                lendBookForm.setVisible(false);
                borrowingInformationForm.setVisible(false);
                addCoppyOfBookForm.setVisible(false);
                addNewBookForm.setVisible(false);
                searchBorrowCardForm.setVisible(true);
                setTitleScreen("Tìm kiếm thẻ mượn");
            }
        }
    }

    private void setTitleScreen(String title) {
        this.setTitle(title);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu7 = new javax.swing.JMenu();
        panelContent = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfNameLibrarian = new javax.swing.JTextField();
        btnLogout = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuHome = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        menuAddNewBook = new javax.swing.JMenuItem();
        menuAddCoppyOfBook = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        mnuCancel = new javax.swing.JMenu();

        jMenu7.setText("jMenu7");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelContent.setBackground(new java.awt.Color(0, 102, 255));
        panelContent.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1061, Short.MAX_VALUE)
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );

        jLabel1.setText("Thủ Thư : ");

        tfNameLibrarian.setEditable(false);

        btnLogout.setText("Đăng xuất");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        mnuHome.setText("Trang chủ");
        mnuHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuHomeMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuHome);

        jMenu1.setText("Quản lý sách");

        menuAddNewBook.setText("Thêm mới sách");
        menuAddNewBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAddNewBookActionPerformed(evt);
            }
        });
        jMenu1.add(menuAddNewBook);

        menuAddCoppyOfBook.setText("Thêm bản sao sách đã có");
        menuAddCoppyOfBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAddCoppyOfBookActionPerformed(evt);
            }
        });
        jMenu1.add(menuAddCoppyOfBook);

        jMenuBar1.add(jMenu1);

        jMenu8.setText("Quản lý thẻ");

        jMenuItem4.setText("Tìm kiếm thẻ");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem4);

        jMenuItem5.setText("Cập nhật thông tin thẻ");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem5);

        jMenuItem3.setText("Phát hành thẻ");
        jMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem3MouseClicked(evt);
            }
        });
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem3);

        jMenuBar1.add(jMenu8);

        jMenu5.setText("Quản lý mượn sách");

        jMenuItem1.setText("Tìm kiếm thông tin mượn sách");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem1);

        jMenuItem2.setText("Cho mượn sách");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem2);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("Trả sách");
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu6MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu6);

        mnuCancel.setText("Thoát");
        mnuCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuCancelMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuCancel);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNameLibrarian, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(719, 719, 719)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfNameLibrarian, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(panelContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:  
        if (chooseDialog("Bạn có chắc chắn muốn đăng xuất") == JOptionPane.YES_OPTION) {
            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void mnuCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuCancelMouseClicked
        // TODO add your handling code here:
        if (chooseDialog("Bạn có chắc chắn muốn thoát") == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_mnuCancelMouseClicked

    private void menuAddNewBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAddNewBookActionPerformed
        // TODO add your handling code here:
        setContentsPanel(4);
    }//GEN-LAST:event_menuAddNewBookActionPerformed

    private void menuAddCoppyOfBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAddCoppyOfBookActionPerformed
        // TODO add your handling code here:
        setContentsPanel(3);
    }//GEN-LAST:event_menuAddCoppyOfBookActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        setContentsPanel(2);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        setContentsPanel(1);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem3MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenuItem3MouseClicked

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
        // TODO add your handling code here:
        ReturnBookFrame frame = new ReturnBookFrame();
        frame.setSize(1100, 600);
        frame.setVisible(true);
    }//GEN-LAST:event_jMenu6MouseClicked

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        CreateNewCardFrame frame = new CreateNewCardFrame();
        frame.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void mnuHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuHomeMouseClicked
        // TODO add your handling code here:
        setContentsPanel(0);
    }//GEN-LAST:event_mnuHomeMouseClicked

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        setContentsPanel(5);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        UpdateInfoFrame frame = new UpdateInfoFrame();
        frame.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private int chooseDialog(String title) {
        int i = JOptionPane.showConfirmDialog(null, title, "Thông báo", JOptionPane.YES_NO_OPTION);
        return i;
    }

    private void notify(String title) {
        JOptionPane.showMessageDialog(null, title);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem menuAddCoppyOfBook;
    private javax.swing.JMenuItem menuAddNewBook;
    private javax.swing.JMenu mnuCancel;
    private javax.swing.JMenu mnuHome;
    private javax.swing.JPanel panelContent;
    private javax.swing.JTextField tfNameLibrarian;
    // End of variables declaration//GEN-END:variables
}
