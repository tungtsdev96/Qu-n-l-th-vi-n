/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Author;

/**
 *
 * @author tungts
 */
public class AuthorController {
    
    private static AuthorController authorController;
    Author author = new Author();
    
    /**
     * Hàm này khởi tạo cho đối tượng dùng chung duy nhất của AuthorController
     * @return đối tượng dùng chung duy nhất của AuthorController
     */
    public static AuthorController getInstance(){
        if (authorController == null){
            authorController = new AuthorController();
        }
        return authorController;
    }
    
    /**
     * Hàm này lấy ra danh sách các tác giả có trong hệ thống.
     * @return danh sách các tác giả có trong hệ thống
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<Author> getListAuthor() throws ClassNotFoundException, SQLException{
        return author.getListAuthor();
    }

    /**
     * Hàm này lấy ra mã lớn nhất của các tác giả có trong hệ thống.
     * @return mã lớn nhất của tác giả có trong hệ thống
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int getMaxIdAuthor() throws ClassNotFoundException, SQLException {
        return author.getMaxIdAuthor();
    }

    /**
     * Hàm này thêm mới một tác giả vào trong hệ thống.
     * @param author Tác giả muốn thêm vào
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void addAuthor(Author author) throws ClassNotFoundException, SQLException {
        author.addAuthor();
    }
}
