/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borrowbook.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import model.Borrower;
import model.BorrowingInformation;
import model.CoppyOfBook;

/**
 *
 * @author tungts
 */
public class BorrowingInformationController {
        
    private static BorrowingInformationController controller;
    
    /**
     * Hàm này khởi tạo đối tượng duy nhất đại diện cho BorrowingInformationController
     * @return đối tượng duy nhất đại diện cho BorrowingInformationController
     */
    public static BorrowingInformationController getInstance(){
        if (controller == null){
            controller = new BorrowingInformationController();
        }
        return controller;
    }

    /**
     * Hàm này thực hiện chức năng lấy ra danh sách các thông tin mượn trả sách tương ứng với người mượn
     * @param borrower đối tượng Borrower muốn lấy thông tin mượn trả sách
     * @return danh sách các thông tin mượn trả cảu đối tượng người mượn ở trên
     */
    public ArrayList<BorrowingInformation> getListBorrowingInformationByIdBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
        return new BorrowingInformation().getListBorrowingInformationByBorrower(borrower);
    }

    public void deleteBorrowingInfor(String idBorrowInfor) throws ClassNotFoundException, SQLException {
        new BorrowingInformation().deleteBorrowingInfor(idBorrowInfor);
    }

    public boolean updateLent(BorrowingInformation borrowingInformation) throws ClassNotFoundException, SQLException {
       return borrowingInformation.updateLentBook();
    }

}
