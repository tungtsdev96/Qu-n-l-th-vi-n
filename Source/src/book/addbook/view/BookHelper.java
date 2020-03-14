/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.addbook.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Book;
import model.CoppyOfBook;

/**
 *
 * @author tungts
 */
public class BookHelper {
    
    public static final Pattern VALID_IDBOOK_REGEX = Pattern.compile("^[A-Z]{2}\\d{4}");
    
    /**
     * Hàm này thực hiện chức năng kiểm tra định dạng mã sách
     * @param String mã cuốn sách mà muốn thêm bản sao
     * @return String mã bản sao sách vừa được tạo mới
     */
    public static boolean checkPtternIdBook(String idBook) {
            Matcher matcher = VALID_IDBOOK_REGEX .matcher(idBook);
            return matcher.find();
    }
    
    /**
     * Hàm này thực hiện chức năng tạo tự động mã bản sao của sách khi thêm bản sao sách.
     * @param book cuốn sách mà muốn thêm bản sao
     * @return String mã bản sao sách vừa được tạo mới
     */
    public static String getNewIdCoppyOfBook(Book book,int numberOfLastCoppy) {
        ArrayList<CoppyOfBook> arr = book.getListCoppyOfBook();
        numberOfLastCoppy = (arr==null)?numberOfLastCoppy:(numberOfLastCoppy + arr.get(arr.size()-1).getNumberOfCoopy());
        if (numberOfLastCoppy < 10){
            return book.getIdBook()+ "-0" + numberOfLastCoppy; 
        }
        return book.getIdBook()+"-"+numberOfLastCoppy;
    }
    
    
    /**
     * Hàm này thực hiện chức năng tạo tự động số hiệu bản sao của sách khi thêm bản sao sách.
     * @param book cuốn sách mà muốn thêm bản sao
     * @return int số hiệu bản sao sách vừa được tạo mới
     */
    public static int getNewNumberCoppyOfBook(Book book,int numberOfLastCoppy) {
        ArrayList<CoppyOfBook> arr = book.getListCoppyOfBook();
        numberOfLastCoppy = (arr==null)?numberOfLastCoppy:(numberOfLastCoppy + arr.get(arr.size()-1).getNumberOfCoopy());
        return numberOfLastCoppy;
    }
    
    /**
     * Hàm này thực hiện chức năng  lấy mã sách từ mã bản sao sách
     * @param idCoppyOfBook cmã bản sao sách
     * @return String mã sách
     */
    public static String getIdBookByIdCoppyOfBook(String idCoppyOfBook) {
        String[] part = idCoppyOfBook.split("-");
        return part[0];
    }
    
    /**
     * Hàm này thực hiện chức năng  lấy số hiệu bản sao sách từ mã bản sao sách
     * @param idCoppyOfBook cmã bản sao sách
     * @return int số hiệu bản sao sách
     */
    public static int getNumberOfCoppyByIdCoppyOfBook(String idCoppyOfBook) {
        String[] part = idCoppyOfBook.split("-");
        return Integer.parseInt(part[1]);
    }
}
