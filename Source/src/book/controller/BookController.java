/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Book;
import model.CoppyOfBook;

/**
 *
 * @author tungts
 */
public class BookController {

    private Book book = new Book();
    private static BookController bookController;
    
    private BookController() {}
    
    /**
     * Hàm này khởi tạo cho đối tượng dùng chung duy nhất của BookController
     * @return đối tượng dùng chung duy nhất của BookController
     */
    public static BookController getInstance(){
        if (bookController == null){
            bookController = new BookController();
        }
        return bookController;
    }
    
    /**
     * Hàm này lấy quyển sách từ mã sách
     * @param idBook mã quyển sách muốn lấy thông tin
     * @return trả về quyển sách cần lấy ra
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Book getBookByIdBook(String idBook) throws ClassNotFoundException, SQLException{
        return book.getBookByIdBook(idBook);
    }
    
    /**
     * Hàm này lấy quyển sách từ mã bản sao sách
     * @param idCoppyOfBook mã bản sao sách
     * @return trả về quyển sách cần lấy ra
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Book getBookByIdCoppyOfBook(String idCoppyOfBook) throws ClassNotFoundException, SQLException {
        return new CoppyOfBook().getBookByIdCoppyOfBook(idCoppyOfBook);
    }
    
    /**
     * Hàm này thực hiện chức năng thêm một bản sao của một sách
     * @param idBook mã sách của cuốn sách muốn thêm các bản sao
     * @param coppyOfBook bản sao sách muốn thêm vào
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public void addCoppyOfBookByIdBook(String idBook,CoppyOfBook coppyOfBook) throws ClassNotFoundException, SQLException{
        coppyOfBook.addCoppyOfBookByIdBook(idBook);
    }
    
    /**
     * Hàm này thực hiện chức năng thêm một sách vào cơ sở dữ liệu
     * @param book sách muốn thêm vào
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public void addBook(Book book) throws ClassNotFoundException, SQLException {
        book.addBook();
    }
    
    /**
     * Hàm này thực hiện chức năng lấy bản sao sách từ mã bản sao sách
     * @param idCoppyOfBook mã bản sao sách muốn lấy ra
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public CoppyOfBook getCoppyOfBookById(String idCoppyOfBook) throws ClassNotFoundException, SQLException {
        return new CoppyOfBook().getCoppyOfBookById(idCoppyOfBook);
    }

    /**
     * Hàm này thực hiện chức năng cập nhật thông tin bản sao sách 
     * @param b  bản sao sách muốn cập nhật thông tin
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public void updateCoppyOfBook(CoppyOfBook b) throws ClassNotFoundException, SQLException {
        b.updateCoppyOfBook();
    }

    public ArrayList<Integer> getNumberOfCoppy(String idBorrower) throws ClassNotFoundException, SQLException {
        return new Book().getNumberOfCoppy(idBorrower);
    }

    public CoppyOfBook.StatusOfCoppy getStatusByIdBook(String idBook, int numberOfCoppy) throws ClassNotFoundException, SQLException {
       return new CoppyOfBook().getStatusByIdBook(idBook,numberOfCoppy);
    }
    
    
    public ArrayList<Book> getListBook() throws ClassNotFoundException, SQLException {
        return  new Book().getListBook();
    }

    /**
     * Hàm này thực hiện chức năng tìm kiếm sách theo tên sách trong CSDL
     * @param key từ khóa tìm kiếm 
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public ArrayList<Book> searchBook(String key) throws SQLException, ClassNotFoundException {
        return  book.searchBook(key);
    }

}
