/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javafx.scene.input.ClipboardContent;
import static model.DataAccessHelper.conn;

/**
 *
 * @author tungts
 */
public class BorrowingInformation extends DataAccessHelper {

    private String idBorrowingInformation;
    private Borrower borrower;
    private String borrowedDate;
    private int numberBookOfInfor;
    private String expectReturnDate;
    private int deposit;
    private BorrowStatus borrowStatus;

    private final String GET_LIST_BORROWINGINFORMATION_BY_BORROWER = "SELECT * FROM tkxdpm.thongtinmuontrasach where maNguoiMuon = ?";
    private final String GET_INFOR_BORROWED_BY_ID_BORROWER = "SELECT * FROM thongtinmuontrasach where maNguoiMuon = ? and (trangThai=0 or trangThai=1)";
    private final String DELETE_BORROWING_INFOR_BY_ID = "DELETE FROM thongtinmuontrasach WHERE maThongTinMuonTraSach=?";
    private final String UPDATE_LENT_BOOK = "UPDATE thongtinmuontrasach SET hanTra=?, trangThai=?, tienDatCoc=? WHERE maThongTinMuonTraSach=?";

    public BorrowingInformation() {
    }

    public BorrowingInformation(String idBorrowingInformation, Borrower borrower, String borrowedDate, String expectReturnDate, int deposit, BorrowStatus borrowStatus) {
        this.idBorrowingInformation = idBorrowingInformation;
        this.borrower = borrower;
        this.borrowedDate = borrowedDate;
        this.expectReturnDate = expectReturnDate;
        this.deposit = deposit;
        this.borrowStatus = borrowStatus;
    }

    public String getIdBorrowingInformation() {
        return idBorrowingInformation;
    }

    public void setIdBorrowingInformation(String idBorrowingInformation) {
        this.idBorrowingInformation = idBorrowingInformation;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public int getNumberBookOfInfor() {
        return numberBookOfInfor;
    }

    public void setNumberBookOfInfor(int numberBookOfInfor) {
        this.numberBookOfInfor = numberBookOfInfor;
    }

    public String getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(String borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public String getExpectReturnDate() {
        return expectReturnDate;
    }

    public void setExpectReturnDate(String expectReturnDate) {
        this.expectReturnDate = expectReturnDate;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public BorrowStatus getBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(BorrowStatus borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    public static enum BorrowStatus {
        BORROWED(0),
        LENDING(1),
        RETURNED(2);

        private int value;

        private BorrowStatus(int value) {
            this.value = value;
        }
    }

    /**
     * Hàm này thực hiện chức năng lấy ra danh sách các thông tin mượn trả sách
     * tương ứng với người mượn
     *
     * @param borrower đối tượng Borrower muốn lấy thông tin mượn trả sách
     * @return danh sách các thông tin mượn trả cảu đối tượng người mượn ở trên
     */
    public ArrayList<BorrowingInformation> getListBorrowingInformationByBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
        ArrayList<BorrowingInformation> arr = new ArrayList<>();
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(GET_LIST_BORROWINGINFORMATION_BY_BORROWER);
        ps.setString(1, borrower.getIdBorrower());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String idBorrowingInformation = rs.getString("maThongTinMuonTraSach");
            String borrowedDate = rs.getString("ngayDangKi");
            String expectReturnDate = rs.getString("hanTra");
            int deposit = rs.getInt("tienDatCoc");

            BorrowStatus borrowStatus = null;
            if (rs.getInt("trangThai") == 0) {
                borrowStatus = BorrowStatus.BORROWED;
            } else if (rs.getInt("trangThai") == 1) {
                borrowStatus = BorrowStatus.LENDING;
            } else if (rs.getInt("trangThai") == 2) {
                borrowStatus = BorrowStatus.RETURNED;
            };

            arr.add(new BorrowingInformation(idBorrowingInformation, borrower, borrowedDate, expectReturnDate, deposit, borrowStatus));
        }
        closeDatabase();
        return arr;
    }

    /**
     * Hàm này thực hiện chức năng xóa thông tin mượn trả sách trong  CSDL
     * @param idBorrowingInformation mã thông tin mượn trả muốn xóa
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void deleteBorrowingInfor(String idBorrowingInformation) throws ClassNotFoundException, SQLException {
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(DELETE_BORROWING_INFOR_BY_ID);
        ps.setString(1, idBorrowingInformation);
        ps.executeUpdate();
        closeDatabase();
    }

    /**
     * Hàm này thực hiện chức năng cập nhật thông tin mượn trả thành đang mượn
     * @return true Cập nhật thành công
     *         false nếu không thành công
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public boolean updateLentBook() throws ClassNotFoundException, SQLException {
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(UPDATE_LENT_BOOK);
        ps.setString(1, expectReturnDate);
        ps.setInt(2, 1);
        ps.setInt(3, deposit);
        ps.setString(4, idBorrowingInformation);
        int i = ps.executeUpdate();
        closeDatabase();
        return i == -1 ? false : true;
    }

    /**
     * Hàm này để lấy thông tin mượn theo mã người mượn
     * @param borrowerId là mã người mượn
     * @return ArrayList<BorrowingInformation> là thông tin mượn sách của người
     * mượn
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     *
     */
    public ArrayList<BorrowingInformation> getBorrowingInfo(String borrowerId) throws ClassNotFoundException, SQLException {
        ArrayList<BorrowingInformation> info = new ArrayList<>();
        connectDatabase();
        PreparedStatement st = conn.prepareStatement("SELECT  chitietmuonsach.ngayMuon, chitietmuonsach.ngayTra, bansaosach.maSach, sach.tenSach, tacgia.tenTacGia, nhaphathanh.tenNhaPhatHanh, bansaosach.soThuTu, thongtinmuontrasach.ngayDangKi, thongtinmuontrasach.hanTra, bansaosach.trangThai "
                + "FROM tkxdpm.thongtinmuontrasach,tkxdpm.bansaosach,tkxdpm.chitietmuonsach, tkxdpm.sach, tkxdpm.tacgia,tkxdpm.chitiettacgia,tkxdpm.nhaphathanh "
                + "where thongtinmuontrasach.manguoimuon = ? and thongtinmuontrasach.maThongTinMuonTraSach = chitietmuonsach.maThongTinMuonSach and chitietmuonsach.maBanSaoSach = bansaosach.maBanSao and bansaosach.maSach = sach.maSach and sach.maSach = chitiettacgia.maSach "
                + "and tacgia.maTacGia = chitiettacgia.maTacGia and sach.maNhaPhatHanh = nhaphathanh.maNhaPhatHanh and thongtinmuontrasach.trangThai = 0  group by sach.maSach");

        st.setString(1, borrowerId);

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            // String idBorrowingInformation = rs.getString("maThongTinMuonTraSach");
            //  String idBook = rs.getString("maSach");
            // int numberCoppy = rs.getInt("soThuTu");
            String borrowedDate = rs.getString("ngayDangKi");
            //  String lentDate = rs.getString("ngayMuon");
            //   String returnDate = rs.getString("ngayTra");
            String expectReturnDate = rs.getString("hanTra");

            BorrowingInformation temp = new BorrowingInformation();
            //temp.setIdBorrowingInformation(idBorrowingInformation);
            temp.setBorrowedDate(borrowedDate);
            // temp.setLentDate(lentDate);
            //  temp.setReturnDate(returnDate);
            temp.setExpectReturnDate(expectReturnDate);

            info.add(temp);
        }
        return info;
    }

    /*Hàm này để lấy về danh sách mã sách đã được mượn theo mã người mượn
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public Vector<String> getListIdBook(String maNguoiMuon) throws SQLException, ClassNotFoundException {
        connectDatabase();
        Vector<String> listIdBook = new Vector<>();
        PreparedStatement st1 = conn.prepareStatement("select bansaosach.maSach from nguoimuon, bansaosach, chitietmuonsach,thongtinmuontrasach where nguoimuon.maNguoiMuon=\"" + maNguoiMuon + "\"\n"
                + "and nguoimuon.maNguoiMuon=thongtinmuontrasach.maNguoiMuon\n" + " and thongtinmuontrasach.trangThai = 0 "
                + "and thongtinmuontrasach.maThongTinMuonTraSach= chitietmuonsach.maThongTinMuonSach\n"
                + " and chitietmuonsach.maBanSaoSach = bansaosach.maBanSao group by bansaosach.maSach");
        ResultSet rs1 = st1.executeQuery();
        while (rs1.next()) {
            listIdBook.add(rs1.getString("maSach"));
            System.out.println(rs1.getString("maSach"));
        }
        return listIdBook;
    }

    /*Hàm này để lấy về số sách đã mượn theo mã người mượn
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public int getNumberBook(String maNguoiMuon) throws SQLException, ClassNotFoundException {
        connectDatabase();
        PreparedStatement st1 = conn.prepareStatement("SELECT count(maThongTinMuonSach) FROM tkxdpm.chitietmuonsach, thongtinmuontrasach where maNguoiMuon = \"" + maNguoiMuon + "\" and thongtinmuontrasach.trangThai =0 and thongtinmuontrasach.maThongTinMuonTraSach = chitietmuonsach.maThongTinMuonSach");
        ResultSet rs1 = st1.executeQuery();
        int numberCoppy = 0;
        while (rs1.next()) {
            numberCoppy = rs1.getInt("count(maThongTinMuonSach)");
        }
        return numberCoppy;
    }

    /*Hàm này để cập nhật thông tin mượn của người dùng và trạng thái sách sau khi hủy đăng ký mượn
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public boolean updateInfomation(Borrower borrower, Book book, int numberCopy) throws ClassNotFoundException, SQLException {
        connectDatabase();

        PreparedStatement st = conn.prepareStatement("Select maBanSao from bansaosach where maSach = \"" + book.getIdBook() + "\"and soThuTu =" + numberCopy);
        ResultSet rs = st.executeQuery();
        String maBanSao = null;
        while (rs.next()) {
            maBanSao = rs.getString("maBanSao");
        }
        PreparedStatement st1 = conn.prepareStatement("Select maThongTinMuonSach from chitietmuonsach where maBanSaoSach = \"" + maBanSao + "\"and maThongTinMuonSach in (Select maThongTinMuonTraSach from thongtinmuontrasach where maNguoiMuon= \"" + borrower.getIdBorrower() + "\")");
        ResultSet rs1 = st1.executeQuery();
        while (rs1.next()) {
            PreparedStatement st2 = conn.prepareStatement("Delete from chitietmuonsach where maBanSaoSach=\"" + maBanSao + "\"");
            st2.execute();
            //  PreparedStatement st3 = conn.prepareStatement("Delete from thongtinmuontrasach where maThongTinMuonTraSach=\"" + rs1.getString("maThongTinMuonSach") + "\"");
            //  st3.execute();
            PreparedStatement st4 = conn.prepareStatement("UPDATE bansaosach SET trangThai='0' WHERE maBanSao=\"" + maBanSao + "\"");
            String s = "UPDATE bansaosach SET trangThai='0' WHERE maBanSao=\" " + maBanSao + "\"";
            st4.execute();
            //System.out.println(s);
        }
        return true;

    }

    public boolean checkRegistrationFromBorrower(String idBorrower) throws SQLException, ClassNotFoundException {
        connectDatabase();
        String sqlCommand = "Select * from thongtinmuontrasach where maNguoiMuon = ? and trangThai=0";

        PreparedStatement st = conn.prepareStatement(sqlCommand);
        st.setString(1, idBorrower);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            closeDatabase();
            return true;

        }

        closeDatabase();
        return false;
    }

    /*Hàm này để cập nhật thông tin mượn sách và trạng thái sách của người mượn sau khi đăng ký mượn
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public String addInfomation(Borrower borrower) throws ClassNotFoundException, SQLException {
        connectDatabase();
        Date date = new Date();
        String sqlCommand2 = "INSERT INTO thongtinmuontrasach (maThongTinMuonTraSach, ngayDangKi, maNguoiMuon,trangThai) VALUES (? , ? ,  ? ,0)";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        PreparedStatement st1 = conn.prepareStatement(sqlCommand2);
        st1.setString(1, "TQB" + String.valueOf(timestamp.getTime()));
        st1.setString(2, new SimpleDateFormat("dd/MM/yyyy").format(date));
        st1.setString(3, borrower.getIdBorrower());
        st1.execute();

        return "TQB" + String.valueOf(timestamp.getTime()); // return id
    }

    /**
     * Hàm này trả về mã thông tin mượn sách tìm kiếm theo mã người mượn
     *
     * @param idBorrower là mã người mượn
     * @return idBorrowingInfo là mã thông tin mượn sách
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String getIdBorrowingInfo(String idBorrower) throws ClassNotFoundException, SQLException {
        connectDatabase();
        String idBorrowingInfo = "";

        String sqlCommand = "select maThongTinMuonTraSach from thongtinmuontrasach where maNguoiMuon = ? and trangThai =0";
        PreparedStatement st = conn.prepareStatement(sqlCommand);
        st.setString(1, idBorrower);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            idBorrowingInfo = rs.getString("maThongTinMuonTraSach");
        }
        closeDatabase();
        return idBorrowingInfo;
    }

    /**
     * Hàm này trả về danh sách mã bản sao sách tìm kiếm theo mã thông tin mượn
     * sách
     *
     * @param idBorrowingInfo là mã thông tin mượn trả sách
     * @return listIdBook là danh sách mã bản sao sách
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<String> getListIdBorrowed(String idBorrowingInfo) throws ClassNotFoundException, SQLException {
        ArrayList<String> listIdBook = new ArrayList<>();
        connectDatabase();
        String sqlCommand = "select maBanSaoSach from chitietmuonsach where maThongTinMuonSach =?";
        PreparedStatement st = conn.prepareStatement(sqlCommand);
        st.setString(1, idBorrowingInfo);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            listIdBook.add(rs.getString("maBanSaoSach"));
        }

        closeDatabase();

        return listIdBook;
    }

    /**
     * Hàm này trả về danh sách sách đã đăng ký mượn
     *
     * @param listId danh sách mã bản sao sách đã đăng ký mượn
     * @return listBookBorrowed danh sách sách đã mượn
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Book> getBookByIdCoppy(ArrayList<String> listId) throws SQLException, ClassNotFoundException {
        ArrayList<Book> listBookBorrowed = new ArrayList<>();
        connectDatabase();

        String sqlCommand = "select tacgia.tenTacGia, nhaphathanh.maNhaPhatHanh, sach.tenSach from bansaosach, sach, nhaphathanh, tacgia, chitiettacgia\n"
                + "where bansaosach.maBanSao =? \n"
                + "and bansaosach.maSach = sach.maSach\n"
                + "and sach.maNhaPhatHanh = nhaphathanh.maNhaPhatHanh\n"
                + "and sach.maSach = chitiettacgia.maSach\n"
                + "and chitiettacgia.maTacGia = tacgia.maTacGia ";
        PreparedStatement st = conn.prepareStatement(sqlCommand);
        for (int i = 0; i < listId.size(); i++) {
            st.setString(1, listId.get(i));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setNameAuthor(rs.getString("tenTacGia"));
                book.setTitleBook(rs.getString("tenSach"));
                book.setPublisher(new Publisher().getPublisherByIdPublisher(rs.getInt("maNhaPhatHanh")));
                listBookBorrowed.add(book);
            }
        }
        closeDatabase();
        return listBookBorrowed;
    }

    /**
     * Hàm này trả về ngày đăng ký mượn sách
     *
     * @param idBorrowingInfo là mã thông tin đăng ký mượn
     * @return trả về ngày đăng ký mượn
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String getRegistrationDate(String idBorrowingInfo) throws ClassNotFoundException, SQLException {
        String date = "";
        connectDatabase();
        String sqlCommand = " select thongtinmuontrasach.ngayDangKi from thongtinmuontrasach where maThongTinMuonTraSach =?";
        PreparedStatement st = conn.prepareStatement(sqlCommand);
        st.setString(1, idBorrowingInfo);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            date = rs.getString("ngayDangKi");
        }

        closeDatabase();
        return date;
    }

    /**
     * Hàm này để xóa thông tin mượn sách
     *
     * @param bookNumber số sách còn đăng ký
     * @param idBorrowingInfo mã thông tin mượn sách
     * @throws ClassNotFoundException
     * @throws SQLException
     */
}
