/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borrowbook.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Book;
import model.DetailLentBook;

/**
 *
 * @author tungts
 */
public class DetailLentBookController {
    
    private static DetailLentBookController controller;
    DetailLentBook detailLentBook = new DetailLentBook();
    
    /**
     * Hàm này khởi tạo đối tượng duy nhất đại diện cho DetailLentBookController
     * @return đối tượng duy nhất đại diện cho DetailLentBookController
     */
    public static DetailLentBookController getInstance(){
        if (controller == null){
            controller = new DetailLentBookController();
        }
        return controller;
    }

    /**
     * Hàm này thực hiện chức năng lấy ra tất cả chi tiết mượn sách bao gồm mã bản sao sách ngày mượn,trả tương ứng với mã thông tin mượn
     * @param idBorrowerInformation mã thông tin mượn trả
     * @return Danh sách các thông tin chi tiết mượn trả sách
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<DetailLentBook> getListDetailLentBookByIdBorrowInfor(String idBorrowerInformation) throws ClassNotFoundException, SQLException {
        return detailLentBook.getListDetailLentBookByIdBorrowInfor(idBorrowerInformation);
    }
    
    public void deleteDetailLentBook(String idBorrowingInfor,String idCoppyOfBook) throws ClassNotFoundException, SQLException {
        detailLentBook.deleteDetailLentBook(idBorrowingInfor, idCoppyOfBook);
    }
    
    public void updateDetailLentBook(String idBorrowInfor, DetailLentBook detailLentBook) throws SQLException, ClassNotFoundException {
        detailLentBook.updateDetailLentBook(idBorrowInfor);
    }
     public void updateBorrowingInfomation(Book book, int numberOfCopy, String maThongTinMuonTra) throws SQLException, ClassNotFoundException {
        detailLentBook.addInfomationDetail(book, numberOfCopy, maThongTinMuonTra);
    }
}
