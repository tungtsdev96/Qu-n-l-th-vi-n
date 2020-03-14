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
public class Borrower extends User {

    private String idBorrower;
    private BorrowingCard borrowingCard;
    private int deposit;
    private String idStudent;
    private String studyPeriod;
    private String username;
    private int status; /// 0 : chua co the  ; 1: co the
    
    private final String GET_BORROWER_BY_USERNAME = "SELECT * FROM tkxdpm.taikhoan,tkxdpm.nguoimuon where taikhoan.tenTaiKhoan = ? and taikhoan.tenTaiKhoan = nguoimuon.tenTaiKhoan";
    private final String GET_LIST_BORROWER = "SELECT * FROM tkxdpm.nguoimuon,themuon,taikhoan where nguoimuon.tenTaiKhoan = taikhoan.tenTaiKhoan and nguoimuon.maNguoiMuon = themuon.maNguoiMuon  ";
    private final String SEARCH_BORROWER = "SELECT nguoimuon.maNguoiMuon,taikhoan.ten, taikhoan.matKhau,taikhoan.tenTaiKhoan,"
                                            + "taikhoan.email,taikhoan.gioiTinh,taikhoan.soDienThoai, nguoimuon.soTienDatCoc, "
                                            + "nguoimuon.mssv, nguoimuon.giaiDoanHoc,nguoimuon.trangThai,themuon.maTheMuon, themuon.maKichHoat,themuon.maKichHoat,themuon.ngayHetHan  "
                                            + "FROM tkxdpm.nguoimuon,taikhoan,themuon "
                                            + "where (nguoimuon.tenTaiKhoan = taikhoan.tenTaiKhoan) and "
                                            + "(nguoimuon.maNguoiMuon = themuon.maNguoiMuon) and "
                                            + "((nguoimuon.maNguoiMuon like ?) or (taikhoan.ten like ?))";
    
    public Borrower() {}

    public Borrower(String idBorrower,  String idStudent,int deposit, String studyPeriod,String username) {
        super();
        this.idBorrower = idBorrower;
        this.deposit = deposit;
        this.idStudent = idStudent;
        this.studyPeriod = studyPeriod;
        this.username = username;
    }
    
     public Borrower(String idBorrower,int deposit,String idStudent,String studyPeriod, String name, String username, String password, String email, boolean isMale, String phone) {
        super(name, username, password, email, isMale, phone);
        this.idBorrower = idBorrower;
        this.deposit = deposit;
        this.idStudent = idStudent;
        this.studyPeriod = studyPeriod;
    }
    
    public Borrower(String idBorrower,int deposit,String idStudent,String studyPeriod,BorrowingCard borrowingCard, String name, String username, String password, String email, boolean isMale, String phone) {
        super(name, username, password, email, isMale, phone);
        this.borrowingCard = borrowingCard;
        this.idBorrower = idBorrower;
        this.deposit = deposit;
        this.idStudent = idStudent;
        this.studyPeriod = studyPeriod;
    }

    
    public String getIdBorrower() {
        return idBorrower;
    }

    public void setIdBorrower(String idBorrower) {
        this.idBorrower = idBorrower;
    }

    public BorrowingCard getBorrowingCard() {
        return borrowingCard;
    }

    public void setBorrowingCard(BorrowingCard borrowingCard) {
        this.borrowingCard = borrowingCard;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public String getStudyPeriod() {
        return studyPeriod;
    }

    public void setStudyPeriod(String studyPeriod) {
        this.studyPeriod = studyPeriod;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    /**
     * Hàm này thực hiện chức năng lấy người mượn từ username
     * @param username tên tài khoản của người mượn
     * @return người mượn tương ứng với tên tài khoản của người mượn
     */
    public Borrower getBorrowerByUsername(String username) throws SQLException, ClassNotFoundException {
        Borrower borrower = null;
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(GET_BORROWER_BY_USERNAME);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String idBorrower = rs.getString("maNguoiMuon");
            String name = rs.getString("ten");
            String password = rs.getString("matKhau");
            String email = rs.getString("email");
            boolean isMale = rs.getString("gioiTinh").equalsIgnoreCase("nam");
            String phone = rs.getString("soDienThoai");
            int deposit = rs.getInt("soTienDatCoc");
            String idStudent = rs.getString("mssv");
            String studyPeriod = rs.getString("giaiDoanHoc");
            int status = rs.getInt("trangThai");
            borrower = new Borrower(idBorrower, deposit, idStudent, studyPeriod, name, username, password, email, isMale, phone);
            borrower.setStatus(status);
        }
        closeDatabase();
        return borrower;
    }
    
    /**
     * Hàm này thực hiện chức năng lấy ra danh sách người mượn có trong hệ thống
     * @return Danh sách người mượn có trong hệ thống
     */
    public ArrayList<Borrower> getListBorrower() throws SQLException, ClassNotFoundException {
        ArrayList<Borrower> arr = new ArrayList<>();
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(GET_LIST_BORROWER);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            BorrowingCard borrowingCard = new BorrowingCard(rs.getString("maTheMuon"), rs.getString("maKichHoat"), rs.getString("ngayHetHan"));
            String idBorrower = rs.getString("maNguoiMuon");
            String name = rs.getString("ten");
            String password = rs.getString("matKhau");
            String email = rs.getString("email");
            boolean isMale = rs.getString("gioiTinh").equalsIgnoreCase("nam");
            String phone = rs.getString("soDienThoai");
            int deposit = rs.getInt("soTienDatCoc");
            String idStudent = rs.getString("mssv");
            String studyPeriod = rs.getString("giaiDoanHoc");
            int status = rs.getInt("trangThai");
            if (status != 0){
                Borrower borrower = new Borrower(idBorrower, deposit, idStudent, studyPeriod, borrowingCard, name, username, password, email, isMale, phone);
                arr.add(borrower);
            } else {
                continue;
            }
        }
        closeDatabase();
        return arr;
    }

    /** Hàm này để lấy đối tượng người mượn theo mã người mượn
     * @param borrowerId mã người mượn của người mượn cần lấy
     * @return đối tượng người mượn tương ứng với mã người mượn
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public Borrower getBorrower(String borrowerId) throws ClassNotFoundException, SQLException{
        Borrower borrower = null;
        connectDatabase();
        PreparedStatement st = conn.prepareStatement("SELECT * FROM tkxdpm.themuon,tkxdpm.nguoimuon,tkxdpm.taikhoan where themuon.maNguoiMuon = ? and themuon.maNguoiMuon = nguoimuon.maNguoiMuon and nguoiMuon.tenTaiKhoan = taikhoan.tenTaiKhoan");
        st.setString(1, borrowerId);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String idBorrower = rs.getString("maNguoiMuon");
            String name = rs.getString("ten");
            String password = rs.getString("matKhau");
            String email = rs.getString("email");
            boolean isMale = rs.getString("gioiTinh").equalsIgnoreCase("nam");
            String phone = rs.getString("soDienThoai");
            int deposit = rs.getInt("soTienDatCoc");
            String idStudent = rs.getString("mssv");
            String studyPeriod = rs.getString("giaiDoanHoc");
            int status = rs.getInt("trangThai");
            String username = rs.getString("tenTaiKhoan");
            borrower = new Borrower(idBorrower, deposit, idStudent, studyPeriod, name, username, password, email, isMale, phone);
        }
        return borrower;
    }
    
    /** Hàm này để tìm kiếm người mượn theo tên hoặc mã người mượn
     * @param key từ khóa mà có thể bao gồm trong tên hoặc mã người mượn
     * @return Danh sách các đối tượng người mượn thỏa mãn
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public ArrayList<Borrower> searchBorrower(String key) throws SQLException, ClassNotFoundException {
        ArrayList<Borrower> arr = new ArrayList<>();
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(SEARCH_BORROWER);
        ps.setString(1, "%"+key+"%");
        ps.setString(2, "%"+key+"%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {            
            BorrowingCard borrowingCard = new BorrowingCard(rs.getString("maTheMuon"), rs.getString("maKichHoat"), rs.getString("ngayHetHan"));
            String idBorrower = rs.getString("maNguoiMuon");
            String name = rs.getString("ten");
            String username = rs.getString("tenTaiKhoan");
            String password = rs.getString("matKhau");
            String email = rs.getString("email");
            boolean isMale = rs.getString("gioiTinh").equalsIgnoreCase("nam");
            String phone = rs.getString("soDienThoai");
            int deposit = rs.getInt("soTienDatCoc");
            String idStudent = rs.getString("mssv");
            String studyPeriod = rs.getString("giaiDoanHoc");
            int status = rs.getInt("trangThai");
            if (status != 0){
                Borrower borrower = new Borrower(idBorrower, deposit, idStudent, studyPeriod, borrowingCard, name, username, password, email, isMale, phone);
                arr.add(borrower);
            } else {
                continue;
            }
        }
        closeDatabase();
        return  arr;
    }
    
}
