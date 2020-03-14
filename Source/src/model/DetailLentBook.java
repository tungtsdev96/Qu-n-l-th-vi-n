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

/**
 *
 * @author tungts
 */
public class DetailLentBook extends DataAccessHelper {

    private String idCoppyOfBook;
    private int status;  // 0: da dki ; 1: Dang muon ; 2 : da tra 
    private String lentDate; // ngay muon
    private String returnDate; // ngay tra
    private int fineBorrower; //tien phat
    private String reason; //lý do phạt

    private final String GET_DETAIL_LENT_BOOK = "SELECT * FROM tkxdpm.chitietmuonsach where maThongTinMuonSach=?";
    private final String DELETE_COPPYOFBOOK_OF_DETAIL_BORROWER = "DELETE FROM chitietmuonsach WHERE maThongTinMuonSach = ? and maBanSaoSach=?";
    private final String UPDATE_DETAIL_LENT_BOOK = "UPDATE chitietmuonsach SET trangThai='1',ngayMuon=? WHERE maThongTinMuonSach=? and maBanSaoSach=? ";

    public DetailLentBook() {
    }

    public DetailLentBook(String idCoppyOfBook, int status, String lentDate, String returnDate, int fineBorrower, String reason) {
        this.idCoppyOfBook = idCoppyOfBook;
        this.status = status;
        this.lentDate = lentDate;
        this.returnDate = returnDate;
        this.fineBorrower = fineBorrower;
        this.reason = reason;
    }

    public String getIdCoppyOfBook() {
        return idCoppyOfBook;
    }

    public void setIdCoppyOfBook(String idCoppyOfBook) {
        this.idCoppyOfBook = idCoppyOfBook;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLentDate() {
        return lentDate;
    }

    public void setLentDate(String lentDate) {
        this.lentDate = lentDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getFineBorrower() {
        return fineBorrower;
    }

    public void setFineBorrower(int fineBorrower) {
        this.fineBorrower = fineBorrower;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * Hàm này thực hiện chức năng lấy ra tất cả chi tiết mượn sách bao gồm mã
     * bản sao sách ngày mượn,trả tương ứng với mã thông tin mượn
     *
     * @param idBorrowerInformation mã thông tin mượn trả
     * @return Danh sách các thông tin chi tiết mượn trả sách
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<DetailLentBook> getListDetailLentBookByIdBorrowInfor(String idBorrowerInformation) throws ClassNotFoundException, SQLException {
        ArrayList<DetailLentBook> arr = new ArrayList<>();
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(GET_DETAIL_LENT_BOOK);
        ps.setString(1, idBorrowerInformation);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String idCoppyOfBook = rs.getString("maBanSaoSach");
            int status = rs.getInt("trangThai");
            String lentDate = rs.getString("ngayMuon");
            String returnDate = rs.getString("ngayTra");
            int fineBorrower = rs.getInt("tienPhat");
            String reason = rs.getString("lyDoPhat");
            arr.add(new DetailLentBook(idCoppyOfBook, status, lentDate, returnDate, fineBorrower, reason));
        }
        closeDatabase();
        return arr;
    }

    public void deleteDetailLentBook(String idBorrowingInfor, String idCoppyOfBook) throws ClassNotFoundException, SQLException {
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(DELETE_COPPYOFBOOK_OF_DETAIL_BORROWER);
        ps.setString(1, idBorrowingInfor);
        ps.setString(2, idCoppyOfBook);
        ps.executeUpdate();
        closeDatabase();
    }

//    UPDATE chitietmuonsach SET trangThai='1',ngayMuon=? WHERE maThongTinMuonSach=? and maBanSaoSach=? 
    public void updateDetailLentBook(String idBorrowInfor) throws SQLException, ClassNotFoundException {
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(UPDATE_DETAIL_LENT_BOOK);
        ps.setString(1, lentDate);
        ps.setString(2, idBorrowInfor);
        ps.setString(3, idCoppyOfBook);
        ps.executeUpdate();
        closeDatabase();
    }

    public void addInfomationDetail(Book book, int numberOfCopy, String maThongTinMuonTra) throws SQLException, ClassNotFoundException {
        connectDatabase();
        String sqlCommand = "INSERT INTO chitietmuonsach (maThongTinMuonSach, maBanSaoSach,trangThai) VALUES (?, ?, 0)";
        PreparedStatement st = conn.prepareStatement(sqlCommand);
        st.setString(1, maThongTinMuonTra);
        //  System.out.println( new CoppyOfBook().getListCoppyOfBookByIdBook(book.getIdBook()).get(numberOfCopy - 1).getIdCoppyOfBook());
        st.setString(2, new CoppyOfBook().getListCoppyOfBookByIdBook(book.getIdBook()).get(numberOfCopy - 1).getIdCoppyOfBook());
        st.executeUpdate();

    }

    /**
     * Hàm này trả về số sách còn đăng ký
     *
     * @param idBorrowingInfo mã thông tin đăng ký mượn
     * @return số sách còn đăng ký
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int getNumberBook(String idBorrowingInfo) throws ClassNotFoundException, SQLException {
        connectDatabase();
        int numberBook = 0;
        String sqlCommand = "select count(maThongTinMuonSach) from chitietmuonsach where maThongTinMuonSach =?";
        PreparedStatement st = conn.prepareStatement(sqlCommand);
        st.setString(1, idBorrowingInfo);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {

            numberBook = rs.getInt("count(maThongTinMuonSach)");
        }

        closeDatabase();
        return numberBook;

    }
}
