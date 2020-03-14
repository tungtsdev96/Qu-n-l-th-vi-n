/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package card.controller;


import model.User;;
import model.Borrower;
import model.BorrowingCard;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DataAccessHelper;

/**
 *
 * @author Nguyen Thanh Tung
 */
public class CreateNewCardController extends DataAccessHelper{


    public  User getAccount(String tenTaiKhoan) throws SQLException, ClassNotFoundException {
        return new BorrowingCard().getAccount(tenTaiKhoan);
    }

    public String registerborrower(Borrower nm,BorrowingCard tm,User tk) throws SQLException, ClassNotFoundException  {
         return new BorrowingCard().registerborrower(nm, tm, tk);
    }
}
