/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borrowbook.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Borrower;
import model.CoppyOfBook;
import model.DataAccessHelper;
import model.User;

/**
 *
 * @author Nguyen Thanh Tung
 */
public class ReturnBookController extends DataAccessHelper {

    public Object[] getBookIsBorrowed(String maNguoiMuon) throws SQLException, ClassNotFoundException, ParseException {
        return new CoppyOfBook().getBookIsBorrowed(maNguoiMuon);
    }

    public void ReturnBook(ArrayList<Object[]> dataTable, boolean[] id) throws SQLException, ClassNotFoundException {
        new CoppyOfBook().ReturnBook(dataTable, id);
    }
   
    public void UpdateInfoBorrowTable(ArrayList<String> InfoBorrow) throws ClassNotFoundException, SQLException{
        new CoppyOfBook().UpdateInfoBorrow(InfoBorrow);
    }

}
