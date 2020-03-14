/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borrowbook.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Book;
import model.Borrower;
import model.BorrowingCard;
import model.BorrowingInformation;

/**
 *
 * @author 2&G
 */
public class RegistrationBookController {

    Book book;
    BorrowingCard card;
    Borrower borrower;
    BorrowingInformation information;

    public RegistrationBookController() {
        book = new Book();
        card = new BorrowingCard();
        borrower = new Borrower();
        information = new BorrowingInformation();

    }

    public ArrayList<Book> searchBooks(String info) {
        try {
            return book.getBookByInfo(info);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RegistrationBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String addInfoBorrowing(Borrower borrower) throws ClassNotFoundException, SQLException {
        return information.addInfomation(borrower);
    }

    public void updateBookInfo(Book book, int numberOfCoppy) throws ClassNotFoundException, SQLException {
        book.updateBookInfo(book.getListCoppyOfBook().get(numberOfCoppy - 1).getIdCoppyOfBook());

    }

    public boolean checkNumberBook(String idBorrower, int number) throws SQLException, ClassNotFoundException {
        return information.getNumberBook(idBorrower) + number <= 5;

    }

    public boolean checkIdBook(String idBorrower, String idBook) throws SQLException, ClassNotFoundException {
        return !information.getListIdBook(idBorrower).contains(idBook);

    }

    /**
     * Hàm này để kiểm tra thẻ mượn còn hạn sử dụng hay không
     *
     * @param borrower là người mượn
     * @return true nếu thẻ còn sử dụng được or false nếu thẻ đã hết hạn
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws ParseException
     *
     *
     */

    public boolean checkBorrowingCard(Borrower borrower) throws SQLException, ClassNotFoundException, ParseException {
        BorrowingCard checkCard = card.getCardByIdBorrower(borrower.getIdBorrower());
        String expriedDate = checkCard.getExpriedDate();
        Date date = new Date();
        //   System.out.println(expriedDate.toString());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date endDate = df.parse(expriedDate);
        // System.out.println(endDate.toString());
        return date.before(endDate);

    }

    public Borrower getBorrwer(String idBorrower) throws ClassNotFoundException, SQLException {
        return borrower.getBorrower(idBorrower);
    }

    public boolean checkRegistrationFromBorrower(String idBorrower) throws SQLException, ClassNotFoundException {
        return information.checkRegistrationFromBorrower(idBorrower);
    }

}
