/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Borrower;

/**
 *
 * @author tungts
 */
public class BorrowerController {
    
    Borrower borrower = new Borrower();
    private static BorrowerController borrowerController;
    
    public BorrowerController(){}
    
    /**
     * Hàm này khởi tạo cho đối tượng dùng chung duy nhất của BorrowerController
     * @return đối tượng dùng chung duy nhất của BorrowerController
     */
    public static BorrowerController getInstance(){
        if (borrowerController == null){
            borrowerController = new BorrowerController();
        }
        return borrowerController;
    }
    
    /**
     * Hàm này thực hiện chức năng lấy ra danh sách người mượn có trong hệ thống
     * @return Danh sách người mượn có trong hệ thống
     */
    public ArrayList<Borrower> getListBorrower() throws SQLException, ClassNotFoundException {
        return borrower.getListBorrower();
    }
    
    /** Hàm này để lấy đối tượng người mượn theo mã người mượn
     * @param borrowerId mã người mượn của người mượn cần lấy
     * @return đối tượng người mượn tương ứng với mã người mượn
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public Borrower getBorrowerById(String idBorrower) throws ClassNotFoundException, SQLException{
        return borrower.getBorrower(idBorrower);
    }
     
    /** Hàm này để tìm kiếm người mượn theo tên hoặc mã người mượn
     * @param key từ khóa mà có thể bao gồm trong tên hoặc mã người mượn
     * @return Danh sách các đối tượng người mượn thỏa mãn
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public ArrayList<Borrower> searchBorrower(String key) throws SQLException, ClassNotFoundException {
        return borrower.searchBorrower(key);
    }
    
}
