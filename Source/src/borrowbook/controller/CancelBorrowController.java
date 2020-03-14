/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borrowbook.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Book;
import model.Borrower;
import model.BorrowingInformation;
import model.CoppyOfBook;
import model.DetailLentBook;

/**
 *
 * @author 2&G
 */
public class CancelBorrowController {

    BorrowingInformation info;
    DetailLentBook detail;
    CoppyOfBook copy;

    public CancelBorrowController() {
        info = new BorrowingInformation();
        detail = new DetailLentBook();
        copy = new CoppyOfBook();
    }

    public ArrayList<BorrowingInformation> getBorrowingInfo(String borrowerId) throws ClassNotFoundException, SQLException {
        return info.getBorrowingInfo(borrowerId);

    }

    public void cancelRegisTration(Borrower borrower, Book book, int numberOfBook) {
        try {
            info.updateInfomation(borrower, book, numberOfBook);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CancelBorrowController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Hàm này tra về danh sách mã bản sao sách đăng ký mượn
     *
     * @param idBorrower là mã người mượn
     * @return danh sách mã bản sao
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<String> getListIdCoppyBook(String idBorrower) throws ClassNotFoundException, SQLException {
        return info.getListIdBorrowed(info.getIdBorrowingInfo(idBorrower));
    }

    public ArrayList<Book> getListBookBorrowed(String idBorrower) throws ClassNotFoundException, SQLException {
        return info.getBookByIdCoppy(getListIdCoppyBook(idBorrower));

    }

    public String getIdBorrowingInfo(String idBorrower) throws ClassNotFoundException, SQLException {
        return info.getIdBorrowingInfo(idBorrower);
    }

    public String getDateRegistration(String idBorrower) throws ClassNotFoundException, SQLException {
        return info.getRegistrationDate(info.getIdBorrowingInfo(idBorrower));
    }

    public int getNumberBook(String idBorrower) throws ClassNotFoundException, SQLException {
        return detail.getNumberBook(info.getIdBorrowingInfo(idBorrower));

    }

    public void updateBorrowingInfo(String idBorrower) throws ClassNotFoundException, SQLException {
        info.deleteBorrowingInfor(info.getIdBorrowingInfo(idBorrower));
    }

    public void updateDetail(String idBorrower, String idCopyBook) throws ClassNotFoundException, SQLException {
        detail.deleteDetailLentBook(info.getIdBorrowingInfo(idBorrower), idCopyBook);
    }

    public void updateCopyBook(String idCopyBook) throws ClassNotFoundException, SQLException {
        copy.updateStateofBook(idCopyBook);
    }
}
