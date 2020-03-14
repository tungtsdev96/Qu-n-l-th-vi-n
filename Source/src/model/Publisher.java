/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tungts
 */
public class Publisher extends DataAccessHelper {

    private int idPublisher;
    private String namePublisher;
    
    private final String GET_LIST_PUBLISHER = "SELECT * FROM tkxdpm.nhaphathanh";
    private final String GET_MAX_ID = "SELECT MAX(maNhaPhatHanh) FROM tkxdpm.nhaphathanh";
    private final String ADD_PUBLISHER = "INSERT INTO tkxdpm.nhaphathanh VALUES (?,?)";

    public Publisher() {}

    public Publisher(int idPublisher, String namePublisher) {
        this.idPublisher = idPublisher;
        this.namePublisher = namePublisher;
    }

    public int getIdPublisher() {
        return idPublisher;
    }

    public void setIdPublisher(int idPublisher) {
        this.idPublisher = idPublisher;
    }

    public String getNamePublisher() {
        return namePublisher;
    }

    public void setNamePublisher(String namePublisher) {
        this.namePublisher = namePublisher;
    }

    /**
     * Hàm này lấy ra tên nhà phát hành từ mã nhà phát hành
     * @param idPublisher mã nhà phát hành bạn muốn lấy tên
     * @return tên tác giả
     * @throws ClassNotFoundException
     * @throws SQLException
     */

    public Publisher getPublisherByIdPublisher(int idPublisher) throws SQLException, ClassNotFoundException {
        Publisher publisher = null;
        connectDatabase();
        PreparedStatement st = conn.prepareStatement("SELECT * FROM tkxdpm.nhaphathanh where maNhaPhatHanh =?");
        st.setInt(1, idPublisher);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String namePublisher = rs.getString("tenNhaPhatHanh");
            publisher = new Publisher(idPublisher, namePublisher);
        }
        closeDatabase();
        return publisher;
    }

    /**
     * Hàm này lấy ra danh sách các nhà phát hành có trong hệ thống.
     * @return danh sách các nhà phát hành có trong hệ thống
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<Publisher> getListPublishers() throws ClassNotFoundException, SQLException {
        ArrayList<Publisher> publishers = new ArrayList<>();
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(GET_LIST_PUBLISHER);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int idPublisher = rs.getInt("maNhaPhatHanh");
            String namePublisher = rs.getString("tenNhaPhatHanh");
            publishers.add(new Publisher(idPublisher, namePublisher));
        }
        closeDatabase();
        return publishers;
    }

    /**
     * Hàm này thêm mới một nhà phát hành vào trong hệ thống.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void addPublisher() throws ClassNotFoundException, SQLException {
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(ADD_PUBLISHER);
        ps.setInt(1, idPublisher);
        ps.setString(2, namePublisher);
        ps.executeUpdate();
        closeDatabase();
    }

    /**
     * Hàm này lấy ra mã lớn nhất của các nhà phát hànhcó trong hệ thống.
     * @return mã lớn nhất của nhà phát hành có trong hệ thống
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int getMaxIdPublisher() throws ClassNotFoundException, SQLException {
        int maxId = -1;
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(GET_MAX_ID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
           maxId = rs.getInt(1);
        }
        closeDatabase();
        return maxId;
    }

}
