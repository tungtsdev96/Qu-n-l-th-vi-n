/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Publisher;

/**
 *
 * @author tungts
 */
public class PublisherController {
    
    private static PublisherController publisherController;
    Publisher publisher = new Publisher();
    
    public static PublisherController getInstance(){
        if (publisherController == null){
            publisherController = new PublisherController();
        }
        return publisherController;
    }
    
    /**
     * Hàm này lấy ra danh sách các nhà phát hành có trong hệ thống.
     * @return danh sách các nhà phát hành có trong hệ thống
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<Publisher> getListPublishers() throws ClassNotFoundException, SQLException{
        return publisher.getListPublishers();
    }

    /**
     * Hàm này thêm mới một nhà phát hành vào trong hệ thống.
     * @param publisher nhà phát hành muốn thêm vào
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void addPublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
        publisher.addPublisher();
    }
    
    /**
     * Hàm này lấy ra mã lớn nhất của các nhà phát hànhcó trong hệ thống.
     * @return mã lớn nhất của nhà phát hành có trong hệ thống
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int getMaxIdPublisher() throws ClassNotFoundException, SQLException {
        return publisher.getMaxIdPublisher();
    }
    
}
