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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tungts
 */
public class Librarian extends User {

    private String idLibrarian;
    
    public Librarian() {
    }

    public Librarian(String idLibrarian, String name, String username, String password, String email, boolean isMale, String phone) {
        super(name, username, password, email, isMale, phone);
        this.idLibrarian = idLibrarian;
    }

    public String getIdLibrarian() {
        return idLibrarian;
    }

    public void setIdLibrarian(String idLibrarian) {
        this.idLibrarian = idLibrarian;
    }
    
    public Librarian getLibrarianByUserName(String username) throws SQLException, ClassNotFoundException {
        Librarian thuThu = null;
        connectDatabase();
        PreparedStatement st = conn.prepareStatement("SELECT * FROM tkxdpm.taikhoan,tkxdpm.thuthu where taikhoan.tenTaiKhoan = ? and taikhoan.tenTaiKhoan = thuthu.tenTaiKhoan");
        st.setString(1, username);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String idLibrarian = rs.getString("maThuThu");
            String name = rs.getString("ten");
            String password = rs.getString("matKhau");
            String email = rs.getString("email");
            boolean isMale = rs.getString("gioiTinh").equalsIgnoreCase("nam");
            String phone = rs.getString("soDienThoai");
            thuThu = new Librarian(idLibrarian, name, username, password, email, isMale, phone);
        }
        closeDatabase();
        return thuThu;
    }

}
