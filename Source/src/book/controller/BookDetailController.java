/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Book;
import model.DataAccessHelper;

/**
 *
 * @author Nguyen Thanh Tung
 */
public class BookDetailController extends DataAccessHelper {

    public ArrayList<Object[]> HandleBookCopy(String maSach) throws SQLException, ClassNotFoundException {
        return new Book().handleBookCopy(maSach);
    }

}
