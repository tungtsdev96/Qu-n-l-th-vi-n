/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import common.view.LoginForm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.DataAccessHelper.conn;

/**
 *
 * @author tungts
 */
public class User extends DataAccessHelper {

    private String name;
    private String username;
    private String password;
    private String email;
    private boolean isMale;
    private String phone;

    public User() {
    }

    public User(String name, String username, String password, String email, boolean isMale, String phone) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.isMale = isMale;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIsMale() {
        return isMale;
    }

    public void setIsMale(boolean isMale) {
        this.isMale = isMale;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Hàm này lấy ra tài khoản người dùng khi đăng nhập thành công
     * @param username tên tài khoản của người dùng
     * @param type kiểu người dùng thủ thư hay là người mượn
     * @return User trả về một tài khoản cuảngười dùng
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public User login(String username, int type) throws SQLException, ClassNotFoundException {
        User user = null;
        if (type == LoginForm.BORROWER) {
            user = new Borrower().getBorrowerByUsername(username);
        } else if (type == LoginForm.LIBRARIAN) {
            user = new Librarian().getLibrarianByUserName(username);
        }
        return user;
    }

    /**
     * Hàm này kiểm tra thông tin đăng nhập của người dùng
     * @param username tên tài khoản của người dùng
     * @param password mật khẩu của người dùng
     * @return 
     *      true nếu username và pass thỏa mãn
     *      false nếu một trong 2 thông tin usernam và pass không đúng
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public boolean checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        String mk = null;
        connectDatabase();
        PreparedStatement st = conn.prepareStatement("SELECT matKhau FROM tkxdpm.taikhoan where tenTaiKhoan =?");
        st.setString(1, username);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            mk = rs.getString("matKhau");
        }
        if (mk == null) {
            return false;
        }
        closeDatabase();
        return mk.equals(password);
    }
    
    /**
 * Thay đổi thông tin tài khoản và gia hạn thẻ
 * @param ten 
 * @param sdt
 * @param gtinh
 * @param nhhan
 * @param email
 * @param maNguoiMuon
 * @return 
 * @throws ClassNotFoundException
 * @throws SQLException 
 */
    public String updateInfoUser(String ten, String sdt, String gtinh, String nhhan, String email, String maNguoiMuon) throws ClassNotFoundException, SQLException {
        try {
            connectDatabase();
            String sqlQuery = "update taikhoan,nguoimuon,themuon Set taikhoan.ten=?,taikhoan.soDienThoai=?,taikhoan.gioiTinh=?,themuon.ngayHetHan=?,taikhoan.email=? where taikhoan.tenTaiKhoan= nguoimuon.tenTaiKhoan and nguoimuon.maNguoiMuon= themuon.maNguoiMuon and nguoimuon.maNguoiMuon=?";
            PreparedStatement prepareStatement;
            prepareStatement = conn.prepareStatement(sqlQuery);
            prepareStatement.setString(1, ten);
            prepareStatement.setString(2, sdt);
            prepareStatement.setString(3, gtinh);
            prepareStatement.setString(4, nhhan);
            prepareStatement.setString(5, email);
            prepareStatement.setString(6, maNguoiMuon);
            prepareStatement.executeUpdate();
            return "THANHCONG";
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);

            return "THATBAI";
        }

    }

    public Object[] getAccount(String x) throws ClassNotFoundException, SQLException {
        connectDatabase();
        Object[] account = new Object[3];
        User userSearch;
        Borrower borrowerSearch;
        BorrowingCard borrowCardSearch;
        String sqlQuery = "select taikhoan.tenTaiKhoan,taikhoan.matKhau,taikhoan.ten,taikhoan.gioiTinh,taikhoan.email,taikhoan.soDienThoai,\n"
                + "nguoimuon.maNguoiMuon,nguoimuon.mssv,nguoimuon.soTienDatCoc,nguoimuon.giaiDoanHoc,themuon.ngayHetHan\n"
                + "from taikhoan ,nguoimuon ,themuon where taikhoan.tenTaiKhoan=nguoimuon.tenTaiKhoan and nguoimuon.maNguoiMuon= \"" + x + "\" and nguoimuon.maNguoiMuon = themuon.maNguoiMuon\n";
        ResultSet rs = null;
        Statement statement;
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(sqlQuery);
            rs.next();
            userSearch = new User(rs.getString(3), rs.getString(1), rs.getString(2), rs.getString(5), rs.getString(4).equalsIgnoreCase("Nam") ? true : false, rs.getString(6));
            borrowerSearch = new Borrower(rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10), rs.getString(1));
            borrowCardSearch = new BorrowingCard();
            borrowCardSearch.setExpriedDate(rs.getString(11));
            account[0] = userSearch;
            account[1] = borrowerSearch;
            account[2] = borrowCardSearch;
            closeDatabase();
            return account;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, "Không tìm thấy", ex);
        }
        closeDatabase();
        return account;
    }

}
