/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tungts
 */
public class CoppyOfBook extends DataAccessHelper {

    private String idCoppyOfBook;
    private int numberOfCoopy;
    private int price;
    private TypeOfCoppy typeOfCoppy;
    private StatusOfCoppy statusOfCoppy;

    public final String GET_BOOK_BY_ID_COPPY_OF_BOOK = "SELECT * FROM tkxdpm.bansaosach,sach where  maBanSao=? and bansaosach.maSach = sach.maSach";
    public final String GET_LIST_COPPY_OF_BOOK_BY_ID_BOOK = "SELECT * FROM tkxdpm.bansaosach where maSach =?";
    public final String ADD_COPPY_OF_BOOK_BY_ID_BOOK = "INSERT INTO bansaosach VALUES (?,?,?,?,?,?,?)";
    public final String GET_COPPY_OF_BOOK_BY_ID = "SELECT * FROM tkxdpm.bansaosach where maBanSao =?";
    public final String UPDATE_COPPY_OF_BOOK = "UPDATE bansaosach SET trangThai=?,gia=? WHERE maBanSao=?";

    public CoppyOfBook() {
    }

    public CoppyOfBook(String idCoppyOfBook, int numberOfCoopy, int price, TypeOfCoppy typeOfCoppy, StatusOfCoppy statusOfCoppy) {
        this.idCoppyOfBook = idCoppyOfBook;
        this.numberOfCoopy = numberOfCoopy;
        this.price = price;
        this.typeOfCoppy = typeOfCoppy;
        this.statusOfCoppy = statusOfCoppy;
    }

    public String getIdCoppyOfBook() {
        return idCoppyOfBook;
    }

    public void setIdCoppyOfBook(String idCoppyOfBook) {
        this.idCoppyOfBook = idCoppyOfBook;
    }

    public int getNumberOfCoopy() {
        return numberOfCoopy;
    }

    public void setNumberOfCoopy(int numberOfCoopy) {
        this.numberOfCoopy = numberOfCoopy;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public TypeOfCoppy getTypeOfCoppy() {
        return typeOfCoppy;
    }

    public void setTypeOfCoppy(TypeOfCoppy typeOfCoppy) {
        this.typeOfCoppy = typeOfCoppy;
    }

    public StatusOfCoppy getStatusOfCoppy() {
        return statusOfCoppy;
    }

    public void setStatusOfCoppy(StatusOfCoppy statusOfCoppy) {
        this.statusOfCoppy = statusOfCoppy;
    }

    public static enum StatusOfCoppy {
        AVAILABLE(0),
        LENT(1),
        BORROWED(2);

        private int value;

        private StatusOfCoppy(int value) {
            this.value = value;
        }
    }

    public static enum TypeOfCoppy {
        REFERENCE(0),
        BORROWABLE(1);

        private int value;

        private TypeOfCoppy(int value) {
            this.value = value;
        }
    }

    public Book getBookByIdCoppyOfBook(String idCoppyOfBook) throws ClassNotFoundException, SQLException {
        Book b = null;
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(GET_BOOK_BY_ID_COPPY_OF_BOOK);
        ps.setString(1, idCoppyOfBook);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            b = new Book();
            String idBook = rs.getString(1);
            String titleBook = rs.getString("tenSach");
            b.setIdBook(idBook);
            b.setTitleBook(titleBook);
        }
        closeDatabase();
        return b;
    }

    /**
     * Hàm này thực hiện chức năng lấy danh sách bản sao sách của một sách
     *
     * @param idBook mã sách của cuốn sách muốn lấy ra các bản sao
     * @return danh sách bản sao của sách.
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public ArrayList<CoppyOfBook> getListCoppyOfBookByIdBook(String idBook) throws ClassNotFoundException, SQLException {
        ArrayList<CoppyOfBook> listCoppyOfBooks = new ArrayList<>();
        connectDatabase();
        PreparedStatement st = conn.prepareStatement(GET_LIST_COPPY_OF_BOOK_BY_ID_BOOK);
        st.setString(1, idBook);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String idCoppyOfBook = rs.getString("maBanSao");
            int numberOfCoopy = rs.getInt("soThuTu");
            int price = rs.getInt("gia");

            //trang thai ban sao
            StatusOfCoppy statusOfCoppy = null;
            if (rs.getInt("trangThai") == 0) {
                statusOfCoppy = StatusOfCoppy.AVAILABLE;
            } else if (rs.getInt("trangThai") == 1) {
                statusOfCoppy = StatusOfCoppy.LENT;
            } else if (rs.getInt("trangThai") == 2) {
                statusOfCoppy = StatusOfCoppy.BORROWED;
            }

            //loai ban sao
            TypeOfCoppy typeOfCoppy = null;
            if (rs.getInt("loaiBanSao") == 0) {
                typeOfCoppy = TypeOfCoppy.REFERENCE;
            } else if (rs.getInt("loaiBanSao") == 1) {
                typeOfCoppy = TypeOfCoppy.BORROWABLE;
            }
            listCoppyOfBooks.add(new CoppyOfBook(idCoppyOfBook, numberOfCoopy, price, typeOfCoppy, statusOfCoppy));
        }
        closeDatabase();
        return listCoppyOfBooks;
    }

    /**
     * Hàm này thực hiện chức năng thêm một bản sao của một sách
     * @param idBook mã sách của cuốn sách muốn thêm các bản sao
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public boolean addCoppyOfBookByIdBook(String idBook) throws ClassNotFoundException, SQLException {
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(ADD_COPPY_OF_BOOK_BY_ID_BOOK);
        ps.setString(1, idBook);
        ps.setString(2, idCoppyOfBook);
        ps.setInt(3, numberOfCoopy);
        ps.setInt(4, typeOfCoppy == TypeOfCoppy.REFERENCE ? 0 : 1);
        ps.setInt(5, price);
        ps.setInt(6, 0);    //status : available
        ps.setInt(7, 0);    //delete : 0
        int isuccess = ps.executeUpdate();
        closeDatabase();
        return (isuccess == -1) ? false : true;
    }

    /**
     * Hàm này thực hiện chức năng lấy bản sao sách từ mã bản sao sách
     * @param idCoppyOfBook mã bản sao sách muốn lấy ra
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public CoppyOfBook getCoppyOfBookById(String idCoppyOfBook) throws ClassNotFoundException, SQLException {
        CoppyOfBook b = null;
        connectDatabase();
        PreparedStatement st = conn.prepareStatement(GET_COPPY_OF_BOOK_BY_ID);
        st.setString(1, idCoppyOfBook);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            if (rs.getInt("xoa") != 0) {
                return null;
            }

            int numberOfCoopy = rs.getInt("soThuTu");
            int price = rs.getInt("gia");

            //trang thai ban sao
            StatusOfCoppy statusOfCoppy = null;
            if (rs.getInt("trangThai") == 0) {
                statusOfCoppy = StatusOfCoppy.AVAILABLE;
            } else if (rs.getInt("trangThai") == 1) {
                statusOfCoppy = StatusOfCoppy.LENT;
            } else if (rs.getInt("trangThai") == 2) {
                statusOfCoppy = StatusOfCoppy.BORROWED;
            }

            //loai ban sao
            TypeOfCoppy typeOfCoppy = null;
            if (rs.getInt("loaiBanSao") == 0) {
                typeOfCoppy = TypeOfCoppy.REFERENCE;
            } else if (rs.getInt("loaiBanSao") == 1) {
                typeOfCoppy = TypeOfCoppy.BORROWABLE;
            }

            b = new CoppyOfBook(idCoppyOfBook, numberOfCoopy, price, typeOfCoppy, statusOfCoppy);
        }
        closeDatabase();
        return b;
    }

    /**
     * Hàm này thực hiện chức năng cập nhật thông tin bản sao sách
     *
     * @param b bản sao sách muốn cập nhật thông tin
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public boolean updateCoppyOfBook() throws ClassNotFoundException, SQLException {
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(UPDATE_COPPY_OF_BOOK);
        if (statusOfCoppy == StatusOfCoppy.AVAILABLE) {
            ps.setInt(1, 0);
        } else if (statusOfCoppy == StatusOfCoppy.LENT) {
            ps.setInt(1, 1);
        } else if (statusOfCoppy == StatusOfCoppy.BORROWED) {
            ps.setInt(1, 2);
        }
        ps.setInt(2, price);
        ps.setString(3, idCoppyOfBook);
        int isSuccess = ps.executeUpdate();
        closeDatabase();
        return (isSuccess == -1) ? false : true;
    }

    /**
     * Hàm này để lấy trạng thái của bản sao theo mã sách và số hiệu bản sao
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public StatusOfCoppy getStatusByIdBook(String idBook, Integer soThuTu) throws ClassNotFoundException, SQLException {
        connectDatabase();
        String command = "SELECT bansaosach.trangThai FROM tkxdpm.bansaosach where maSach =\"" + idBook + "\" and soThuTu =" + soThuTu;
        //   System.out.println(command);
        PreparedStatement st = conn.prepareStatement(command);
        ResultSet rs = st.executeQuery();
        String status = "";
        while (rs.next()) {
            status = rs.getString("trangThai");
        }
        //System.out.println(status);
        if (status.equals("0")) {
            return StatusOfCoppy.AVAILABLE;
        }
        if (status.equals("1")) {
            return StatusOfCoppy.LENT;
        }
        if (status.equals("2")) {
            return StatusOfCoppy.BORROWED;
        }

        return null;
    }

    /**
     * Lấy các cuốn sách đã mượn từ maNguoiMuon
     *
     * @param maNguoiMuon
     * @return 1 Object[] các quyển sách đã mượn
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Object[] getBookIsBorrowed(String maNguoiMuon) throws SQLException, ClassNotFoundException, ParseException {
        connectDatabase();
        boolean haveData = true;
        ResultSet rs = null;
        Object[] ReturnBookInfo = new Object[4];
        Object[] ReturnBookInfo1 = new Object[4];
        ArrayList<Object[]> dataTable = new ArrayList<>();
        ArrayList<String> IdInfoBorrow = new ArrayList<>();
        User taikhoan = new User();
        Borrower nguoimuon = new Borrower();
        PreparedStatement preparedStatement;
        String sql = "SELECT taikhoan.tenTaiKhoan,taiKhoan.ten,taikhoan.soDienThoai,nguoimuon.maNguoiMuon,thongtinmuontrasach.maThongTinMuonTraSach, chitietmuonsach.ngayMuon,thongtinmuontrasach.hanTra,bansaosach.maBanSao,bansaosach.trangThai,sach.maSach,sach.tenSach\n"
                + "                from taikhoan,nguoimuon,thongtinmuontrasach,bansaosach,sach,chitietmuonsach\n"
                + "                where taikhoan.tenTaiKhoan= nguoimuon.tenTaiKhoan \n"
                + "                and nguoimuon.maNguoiMuon=?\n"
                + "                and thongtinmuontrasach.maNguoiMuon= nguoimuon.maNguoiMuon \n"
                + "                and thongtinmuontrasach.maThongTinMuonTraSach= chitietmuonsach.maThongTinMuonSach \n"
                + "                and chitietmuonsach.maBanSaoSach= bansaosach.maBanSao \n"
                + "                and bansaosach.maSach= sach.maSach \n"
                + "                and chitietmuonsach.trangThai=\"1\"";
        preparedStatement = conn.prepareStatement(sql);

        preparedStatement.setString(1, maNguoiMuon);
        rs = preparedStatement.executeQuery();
        try {
            rs.next();
            System.out.println("tenTenKhoan:" + rs.getString(1) + "  ten :" + rs.getString(2) + "  phone:" + rs.getString(3) + "   maNguoiMuon:" + rs.getString(4));
            taikhoan.setUsername(rs.getString(1));
            taikhoan.setName(rs.getString(2));
            taikhoan.setPhone(rs.getString(3));
            nguoimuon.setIdBorrower(rs.getString(4));
            ReturnBookInfo[0] = taikhoan;
            ReturnBookInfo[1] = nguoimuon;
            rs.previous();
            Date currentDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            while (rs.next()) {

                Date ExperiedDate = sdf.parse(rs.getString(7));
                dataTable.add(new Object[]{rs.getString(5), rs.getString(8), rs.getString(11), rs.getString(6), rs.getString(7), currentDate.before(ExperiedDate) ? "đúng hạn" : "hết hạn", "", "0", false});
                IdInfoBorrow.add(rs.getString(5));
            }
            ReturnBookInfo[2] = dataTable;
            ReturnBookInfo[3] = IdInfoBorrow;
            closeDatabase();
//            return ReturnBookInfo;
            haveData = true;
        } catch (SQLException ex) {
            Logger.getLogger(CoppyOfBook.class.getName()).log(Level.SEVERE, "Không mượn sách nhé,ahihi", "");

            User taikhoan1 = new User();
            String a = "KOMUON";
            taikhoan1.setName(a);
            ReturnBookInfo1[0] = taikhoan1;
//            return ReturnBookInfo1;
            haveData = false;
        }
        if (haveData) {
            return ReturnBookInfo;
        } else {
            return ReturnBookInfo1;
        }
    }

    /**
     * Trả những cuốn sách đã chọn
     *
     * @param dataTable
     * @param id
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void ReturnBook(ArrayList<Object[]> dataTable, boolean[] id) throws SQLException, ClassNotFoundException {
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String a = sdf.format(currentDate);
        System.out.println(a);
        for (int i = id.length - 1; i >= 0; i--) {
            if (id[i] == true) {
                connectDatabase();
                PreparedStatement preparedStatement;
                String sql = "UPDATE bansaosach,chitietmuonsach,thongtinmuontrasach SET bansaosach.trangThai=\"0\",chitietmuonsach.trangThai= \"2\",chitietmuonsach.ngayTra=?,chitietmuonsach.tienPhat=?,chitietmuonsach.lyDoPhat=? \n"
                        + "WHERE bansaosach.maBanSao= ? \n"
                        + "and bansaosach.maBanSao= chitietmuonsach.maBanSaoSach \n"
                        + "and chitietmuonsach.maThongTinMuonSach= thongtinmuontrasach.maThongTinMuonTraSach \n"
                        + "and thongtinmuontrasach.maThongTinMuonTraSach=?";
                preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(1, a);
                preparedStatement.setString(2, dataTable.get(i)[7].toString());
                preparedStatement.setString(3, dataTable.get(i)[6].toString());
                preparedStatement.setString(4, dataTable.get(i)[1].toString());
                preparedStatement.setString(5, dataTable.get(i)[0].toString());
                System.out.println(i);
                preparedStatement.executeUpdate();
                closeDatabase();
            }
        }
    }

    /**
     * Hàm này cập nhật trạng thái sách về có sẵn
     *
     * @param idCopyBook là mã bản sao sách
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void updateStateofBook(String idCopyBook) throws ClassNotFoundException, SQLException {
        connectDatabase();
        String sqlCommand = "UPDATE bansaosach SET trangThai=0 WHERE maBanSao=?";
        PreparedStatement st = conn.prepareStatement(sqlCommand);
        st.setString(1, idCopyBook);
        st.execute();
        closeDatabase();
    }

    public void UpdateInfoBorrow(ArrayList<String> IdBorrower) throws ClassNotFoundException, SQLException {
        int result;
        for (int i = 0; i < IdBorrower.size(); i++) {
            result=1;
            connectDatabase();
            String sql = "select count(chitietmuonsach.maThongTinMuonSach) \n"
                    + "from thongtinmuontrasach,chitietmuonsach\n"
                    + "where thongtinmuontrasach.maThongTinMuonTraSach=? \n"
                    + "and thongtinmuontrasach.maThongTinMuonTraSach = chitietmuonsach.maThongTinMuonSach\n"
                    + "and chitietmuonsach.trangThai=\"1\" ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, IdBorrower.get(i));
            ResultSet rs = null;
            rs = ps.executeQuery();
            rs.next();
            result = rs.getInt(1);
            closeDatabase();

            if (result == 0) {
                connectDatabase();
                sql = "UPDATE thongtinmuontrasach SET trangThai='2' WHERE maThongTinMuonTraSach = ? ;";
                PreparedStatement ps1 = conn.prepareStatement(sql);
                ps1.setString(1, IdBorrower.get(i));
                ps1.executeUpdate();
                closeDatabase();
            }
        }

    }
}
