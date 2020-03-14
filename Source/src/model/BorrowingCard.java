/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.DataAccessHelper.conn;

/**
 *
 * @author tungts
 */
public class BorrowingCard extends DataAccessHelper {

    
    private Borrower borrower;
    private String idBorrowingCard;
    private String activedCode;
    private String expriedDate;
    private String status;
    private String idBorrower;
    
    public BorrowingCard() {
    }

    public BorrowingCard(String idBorrowingCard, String activedCode, String expriedDate) {
        this.idBorrowingCard = idBorrowingCard;
        this.activedCode = activedCode;
        this.expriedDate = expriedDate;
    }

    public String getIdBorrowingCard() {
        return idBorrowingCard;
    }

    public void setIdBorrowingCard(String idBorrowingCard) {
        this.idBorrowingCard = idBorrowingCard;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public String getActivedCode() {
        return activedCode;
    }

    public void setActivedCode(String activedCode) {
        this.activedCode = activedCode;
    }

    public String getExpriedDate() {
        return expriedDate;
    }

    public void setExpriedDate(String expriedDate) {
        this.expriedDate = expriedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdBorrower() {
        return idBorrower;
    }

    public void setIdBorrower(String idBorrower) {
        this.idBorrower = idBorrower;
    }
    
   
    /*Hàm này để lấy về tất cả đối tượng thẻ mượn có trong hệ thống
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public ArrayList<BorrowingCard> getAllCard() throws ClassNotFoundException, SQLException {
        ArrayList<BorrowingCard> allCard = new ArrayList<>();
        connectDatabase();
        PreparedStatement st = conn.prepareStatement("SELECT * FROM tkxdpm.themuon");
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String idBorrowingCard = rs.getString("maTheMuon");
            String borrowerId = rs.getString("maNguoiMuon");
            Borrower borrower = new Borrower().getBorrower(borrowerId);
            String activedCode = rs.getString("maKichHoat");
            String expriedDate = rs.getString("ngayHetHan");
            BorrowingCard card = new BorrowingCard(idBorrowingCard, activedCode, expriedDate);
            card.setBorrower(borrower);
            allCard.add(card);

        }
        return allCard;

    }

    /*Hàm này để lấy các đối tượng thẻ mượn theo mã thẻ mượn được nhập
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public ArrayList<BorrowingCard> getCardById(String id) throws ClassNotFoundException, SQLException {
        ArrayList<BorrowingCard> allCard = new ArrayList<>();
        connectDatabase();
        PreparedStatement st = conn.prepareStatement("SELECT * FROM tkxdpm.themuon where themuon.maTheMuon like \'%" + id + "%\'");
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String idBorrowingCard = rs.getString("maTheMuon");
            String idBorrower = rs.getString("maNguoiMuon");
            Borrower borrower = new Borrower().getBorrower(idBorrower);
            String activedCode = rs.getString("maKichHoat");
            String expriedDate = rs.getString("ngayHetHan");
            BorrowingCard card = new BorrowingCard(idBorrowingCard, activedCode, expriedDate);
            card.setBorrower(borrower);
            allCard.add(card);

        }
        return allCard;
    }

    /*Hàm này để lấy về thẻ mượn theo mã người mượn
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public BorrowingCard getCardByIdBorrower(String idBorrower) throws SQLException, ClassNotFoundException {
        BorrowingCard card = new BorrowingCard();
        connectDatabase();
        PreparedStatement st = conn.prepareStatement("SELECT * FROM tkxdpm.themuon where themuon.maNguoiMuon = \"" + idBorrower + "\"");
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String idBorrowingCard = rs.getString("maTheMuon");
            //  Date sqlDate= rs.getDate("ngayHetHan");
            String expriedDate = rs.getString("ngayHetHan");
            //System.out.println(expriedDate);
            card = new BorrowingCard(idBorrowingCard, null, expriedDate);
        }
        conn.close();
        return card;

    }

    /**
     * Lấy tài khoản đã đăng kí online,để phát hành thẻ mượn
     * @param tenTaiKhoan
     * @return tài khoản đã tìm
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public User getAccount(String tenTaiKhoan) throws SQLException, ClassNotFoundException {
        connectDatabase();
        User userSearch = new User();
        ResultSet rs = null;
        PreparedStatement preparedStatement;
        String sql = "SELECT taikhoan.tenTaiKhoan,taiKhoan.ten,taikhoan.gioiTinh,taikhoan.email,taikhoan.soDienThoai\n"
                + "                FROM taikhoan\n"
                + "                where taikhoan.tenTaiKhoan=?;";
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, tenTaiKhoan);
        rs = preparedStatement.executeQuery();
        try {

            rs.next();
            userSearch.setUsername(rs.getString(1));
            userSearch.setName(rs.getString(2));
            userSearch.setIsMale(rs.getString(3).equalsIgnoreCase("Nam"));
            userSearch.setEmail(rs.getString(4));
            userSearch.setPhone(rs.getString(5));

//            CreateNewCardFrame.ici.getAccountInfoUi().setInfo();
            closeDatabase();
            return userSearch;
        } catch (SQLException ex) {
            Logger.getLogger(BorrowingCard.class.getName()).log(Level.SEVERE, "em chưa đăng kí nhé,mệt mỏi!!!", "");
        }
        return userSearch;
    }

        /**
         * Cấp hành thẻ mượn
         * @param nm
         * @param tm
         * @param tk
         * @return Mã thẻ mượn
         * @throws SQLException
         * @throws ClassNotFoundException 
         */
    public String registerborrower(Borrower nm, BorrowingCard tm, User tk) throws SQLException, ClassNotFoundException {
        connectDatabase();
        String BorrowerID;
        ResultSet rs = null;
        PreparedStatement  preparedStatementGetMaNguoiMuon;
        String sqlGetMaNguoiMuon = "select nguoimuon.maNguoiMuon from nguoimuon where nguoimuon.tenTaiKhoan=?;";
        preparedStatementGetMaNguoiMuon = conn.prepareStatement(sqlGetMaNguoiMuon);
        preparedStatementGetMaNguoiMuon.setString(1, tk.getUsername());
        rs = preparedStatementGetMaNguoiMuon.executeQuery();
        rs.next();
        BorrowerID=rs.getString(1);
        closeDatabase();
//       tạo người mượn mới
        connectDatabase();
        String sql = "UPDATE nguoimuon SET  mssv=?, soTienDatCoc=?, giaiDoanHoc=?, trangThai=? WHERE maNguoiMuon=?;";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, nm.getIdStudent());
        preparedStatement.setString(2, String.valueOf(nm.getDeposit()));
        preparedStatement.setString(3, nm.getStudyPeriod());
        preparedStatement.setString(4, "1");
        preparedStatement.setString(5, BorrowerID);
        System.out.println(tk.getUsername());
        preparedStatement.executeUpdate();
        closeDatabase();
// tạo thẻ mới        
        connectDatabase();
        String sql1 = "INSERT INTO themuon (maTheMuon, ngayHetHan, trangThai, maKichHoat, maNguoiMuon) VALUES (?,?,?,?,?);";
        PreparedStatement preparredStatementCreateNewCard = conn.prepareStatement(sql1);
        preparredStatementCreateNewCard.setString(1, BorrowerID);
        preparredStatementCreateNewCard.setString(2, tm.getExpriedDate());
        preparredStatementCreateNewCard.setString(3, "1");
        preparredStatementCreateNewCard.setString(4, tm.getActivedCode());
        preparredStatementCreateNewCard.setString(5, BorrowerID);
        preparredStatementCreateNewCard.executeUpdate();
        closeDatabase();

        return BorrowerID;
    }
    
}
