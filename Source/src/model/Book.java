/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import book.controller.BookDetailController;
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
public class Book extends DataAccessHelper {

    private String idBook;
    private String titleBook;
    private String nameAuthor;
    private Publisher publisher;
    private String isbn;
    private ArrayList<CoppyOfBook> listCoppyOfBook;
    private ArrayList<Author> listAuthor;
    
    private final String GET_LIST_IDAUTHOR = "SELECT * FROM tkxdpm.chitiettacgia where maSach=?";
    private final String ADD_BOOK = "INSERT INTO tkxdpm.sach VALUES (?,?,?,?)";
    private final String ADD_DETAIL_AUTHOR = "INSERT INTO tkxdpm.chitiettacgia VALUES (?,?)";
    private final String SEARCH_BOOK = "select * from sach where tenSach like ?";
    
    public Book() {}

    public Book(String idBook, String titleBook, String nameAuthor, String isbn, ArrayList<CoppyOfBook> listCoppyOfBook, Publisher publisher) {
        this.idBook = idBook;
        this.titleBook = titleBook;
        this.nameAuthor = nameAuthor;
        this.isbn = isbn;
        this.listCoppyOfBook = listCoppyOfBook;
        this.publisher = publisher;
    }

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public String getTitleBook() {
        return titleBook;
    }

    public void setTitleBook(String titleBook) {
        this.titleBook = titleBook;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public ArrayList<CoppyOfBook> getListCoppyOfBook() {
        return listCoppyOfBook;
    }

    public void setListCoppyOfBook(ArrayList<CoppyOfBook> listCoppyOfBook) {
        this.listCoppyOfBook = listCoppyOfBook;
    }

    public ArrayList<Author> getListAuthor() {
        return listAuthor;
    }

    public void setListAuthor(ArrayList<Author> listAuthor) {
        this.listAuthor = listAuthor;
    }
    
    /**
     * Hàm này lấy Tên tác giả của sách
     * @param idBook mã quyển sách muốn lấy thông tin
     * @return trả về tên các tác giả của sách
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private String getNameAuthorByIdBook(String idBook) throws ClassNotFoundException, SQLException {
        String nameOfListAuthor = "";
        connectDatabase();
        PreparedStatement st = conn.prepareStatement(GET_LIST_IDAUTHOR);
        st.setString(1, idBook);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            int maTacGia = rs.getInt("maTacGia");
            nameOfListAuthor = (nameOfListAuthor.isEmpty())?nameOfListAuthor = new Author().getNameAuthorByIdAuthor(maTacGia):nameOfListAuthor + ", " + new Author().getNameAuthorByIdAuthor(maTacGia);
        }
        closeDatabase();
        return nameOfListAuthor;
    }
    
    /**
     * Hàm này lấy quyển sách từ mã sách
     * @param idBook mã quyển sách muốn lấy thông tin
     * @return trả về quyển sách cần lấy ra
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Book getBookByIdBook(String idBook) throws ClassNotFoundException, SQLException {
        Book book = new Book();
        connectDatabase();
        PreparedStatement st = conn.prepareStatement("SELECT * FROM tkxdpm.sach where maSach = ?");
        st.setString(1, idBook);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String titleBook = rs.getString("tenSach");
            Publisher publisher = new Publisher().getPublisherByIdPublisher(rs.getInt("maNhaPhatHanh"));
            String isbn = rs.getString("isbn");
            String nameAuthor = getNameAuthorByIdBook(idBook);
            ArrayList<CoppyOfBook> listBanSao = new CoppyOfBook().getListCoppyOfBookByIdBook(idBook);
            book = new Book(idBook, titleBook, nameAuthor, isbn, listBanSao, publisher);
        }
        closeDatabase();
        return book;
    }
    
    /**
     * Hàm này thực hiện chức năng thêm một sách vào cơ sở dữ liệu
     * @param book sách muốn thêm vào
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public void addBook() throws ClassNotFoundException, SQLException{
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(ADD_BOOK);
        ps.setString(1, idBook);
        ps.setString(2, titleBook);
        ps.setInt(3, publisher.getIdPublisher());
        ps.setString(4, isbn);
        ps.executeUpdate();

        for (int i = 0; i <listAuthor.size(); i++){
            ps = conn.prepareStatement(ADD_DETAIL_AUTHOR);
            ps.setString(1, idBook);
            ps.setInt(2, listAuthor.get(i).getIdAuthor());
            ps.executeUpdate();
        }
        closeDatabase();
    }
    
    /**
     * Hàm này thực hiện chức năng tìm kiếm sách theo tên sách trong CSDL
     * @param key từ khóa tìm kiếm 
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public ArrayList<Book> searchBook(String key) throws SQLException, ClassNotFoundException {
        ArrayList<Book> arr = new ArrayList<>();
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(SEARCH_BOOK);
        ps.setString(1, "%"+  key +"%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Book b = new Book();
            b.setIdBook(rs.getString("maSach"));
            b.setTitleBook(rs.getString("tenSach"));
            Publisher publisher = new Publisher().getPublisherByIdPublisher(rs.getInt("maNhaPhatHanh"));
            b.setPublisher(publisher);
            String nameAuthor = getNameAuthorByIdBook(b.getIdBook()); 
            b.setNameAuthor(nameAuthor);
            arr.add(b);
        }
        closeDatabase();
        return  arr;
    }
    
    /*Hàm này để lấy tất cả các quyển sách người mượn đã đăng kí theo mã người dùng
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public ArrayList<Book> getBooksByIdBorrower(String idBorrower) throws ClassNotFoundException, SQLException {
        Book book = new Book();
        ArrayList<Book> books = new ArrayList<>();
        connectDatabase();
        PreparedStatement st = conn.prepareStatement("SELECT sach.tenSach, sach.maSach, nhaphathanh.maNhaPhatHanh from bansaosach,sach,thongtinmuontrasach,nguoimuon,chitietmuonsach,nhaphathanh where nguoimuon.maNguoiMuon = ? \n"
                + "and nguoimuon.maNguoiMuon = thongtinmuontrasach.maNguoiMuon\n"
                + "and thongtinmuontrasach.maThongTinMuonTraSach = chitietmuonsach.maThongTinMuonSach\n"
                + "and chitietmuonsach.maBanSaoSach = bansaosach.maBanSao and thongtinmuontrasach.trangThai =0\n"
                + "and sach.maSach=bansaosach.maSach\n"
                + "and nhaphathanh.maNhaPhatHanh = sach.maNhaPhatHanh");
        st.setString(1, idBorrower);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String maSach = rs.getString("maSach");
            Publisher tenNhaPhatHanh = new Publisher().getPublisherByIdPublisher(rs.getInt("maNhaPhatHanh"));
            String tenSach = rs.getString("tenSach");
            String tenTacGia = book.getNameAuthorByIdBook(maSach);
            book = new Book(maSach, tenSach, tenTacGia, null, null, tenNhaPhatHanh);

            //book.set
            books.add(book);
        }

        return books;
    }

    /*Hàm này để lấy lần lượt số hiệu bản sao theo các quyển sách đã mượn
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public ArrayList<Integer> getNumberOfCoppy(String idBorrower) throws ClassNotFoundException, SQLException {
        ArrayList<Integer> numberOfCoppy = new ArrayList<>();
        connectDatabase();
        PreparedStatement st = conn.prepareStatement("SELECT bansaoSach.soThuTu from bansaosach,sach,thongtinmuontrasach,nguoimuon,chitietmuonsach,nhaphathanh where nguoimuon.maNguoiMuon = ? \n"
                + "and nguoimuon.maNguoiMuon = thongtinmuontrasach.maNguoiMuon\n"
                + "and thongtinmuontrasach.maThongTinMuonTraSach = chitietmuonsach.maThongTinMuonSach\n"
                + "and chitietmuonsach.maBanSaoSach = bansaosach.maBanSao\n"
                + "and sach.maSach=bansaosach.maSach\n"
                + "and nhaphathanh.maNhaPhatHanh = sach.maNhaPhatHanh");
        st.setString(1, idBorrower);

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            numberOfCoppy.add(rs.getInt("soThuTu"));
        }
        return numberOfCoppy;
    }

    /*Hàm này để lấy danh sách sách theo thông tin từ các trường mã sách, tên sách, tác giả hay tên nhà phát hành
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public ArrayList<Book> getBookByInfo(String info) throws ClassNotFoundException, SQLException {
        ArrayList<Book> books = new ArrayList<>();
        connectDatabase();
        Book book;
        String sqlCommand = "SELECT sach.maSach,tenSach,tenNhaPhatHanh,tenTacGia,sach.isbn,nhaphathanh.maNhaPhatHanh FROM sach, tacgia,nhaphathanh,chitiettacgia where sach.maSach like '%" + info + "%' \n"
                + "and sach.maSach = chitiettacgia.maSach and chitiettacgia.maTacGia =tacgia.maTacGia\n"
                + "and sach.maNhaPhatHanh = nhaphathanh.maNhaPhatHanh group by sach.maSach\n"
                + "union\n"
                + "(SELECT sach.maSach,tenSach,tenNhaPhatHanh,tenTacGia,sach.isbn,nhaphathanh.maNhaPhatHanh  FROM sach, tacgia,nhaphathanh,chitiettacgia where sach.tenSach like '%" + info + "%' \n"
                + "and sach.maSach = chitiettacgia.maSach and chitiettacgia.maTacGia =tacgia.maTacGia\n"
                + "and sach.maNhaPhatHanh = nhaphathanh.maNhaPhatHanh group by sach.maSach\n"
                + ")\n"
                + "union\n"
                + "(SELECT sach.maSach,tenSach,tenNhaPhatHanh,tenTacGia,sach.isbn,nhaphathanh.maNhaPhatHanh  FROM sach, tacgia,nhaphathanh,chitiettacgia where nhaphathanh.tenNhaPhatHanh like '%" + info + "%' \n"
                + "and sach.maSach = chitiettacgia.maSach and chitiettacgia.maTacGia =tacgia.maTacGia\n"
                + "and sach.maNhaPhatHanh = nhaphathanh.maNhaPhatHanh group by sach.maSach\n"
                + ")\n"
                + "union\n"
                + "(SELECT sach.maSach,tenSach,tenNhaPhatHanh,tenTacGia,sach.isbn,nhaphathanh.maNhaPhatHanh  FROM sach, tacgia,nhaphathanh,chitiettacgia where tacgia.tenTacGia like '%" + info + "%' \n"
                + "and sach.maSach = chitiettacgia.maSach and chitiettacgia.maTacGia =tacgia.maTacGia\n"
                + "and sach.maNhaPhatHanh = nhaphathanh.maNhaPhatHanh group by sach.maSach\n"
                + ")";
        PreparedStatement st = conn.prepareStatement(sqlCommand);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String titleBook = rs.getString("tenSach");
            String idBook = rs.getString("maSach");
            Publisher namePublisher = new Publisher().getPublisherByIdPublisher(rs.getInt("maNhaPhatHanh"));
            String isbn = rs.getString("isbn");
            String nameAuthor = getNameAuthorByIdBook(idBook);
            ArrayList<CoppyOfBook> listBanSao = new CoppyOfBook().getListCoppyOfBookByIdBook(idBook);
            book = new Book(idBook, titleBook, nameAuthor, isbn, listBanSao, namePublisher);
            books.add(book);
        }
        return books;
    }

    /*Hàm này để cập nhật trang thái của sách sau khi đăng ký mượn sách
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public void updateBookInfo(String maBanSao) throws ClassNotFoundException, SQLException {
        connectDatabase();
        String sqlCommand = "UPDATE bansaosach SET trangThai='2' WHERE maBanSao= ?";
        PreparedStatement st = conn.prepareStatement(sqlCommand);
        st.setString(1, maBanSao);
        st.execute();
    }
    
    public ArrayList<Object[]> handleBookCopy(String maSach) throws SQLException, ClassNotFoundException {
        Object[] a = new Object[5];
        a = searchAuthor(maSach);
        ArrayList<Object[]> dataTable = new ArrayList<>();
        connectDatabase();
        ResultSet rs = null;
        PreparedStatement preparedStatement;
        String sql1 = "select sach.maSach,sach.tenSach,bansaosach.maBanSao,bansaosach.trangThai,bansaosach.gia from  sach,bansaosach\n"
                + "where   sach.maSach=? and bansaosach.maSach= sach.maSach;";
        preparedStatement = conn.prepareStatement(sql1);
        preparedStatement.setString(1, maSach);
        rs = preparedStatement.executeQuery();
        try {
            while (rs.next()) {
                String s;
                if (Integer.valueOf(rs.getString(4).toString()) == 0) {
                    s = "chưa mượn";
                } else {
                    s = "đang mượn";
                }
                dataTable.add(new Object[]{a[0], a[1], a[2], a[3], a[4], rs.getString(3), s, rs.getString(5)});
            }
            closeDatabase();
            return dataTable;

        } catch (SQLException ex) {
            Logger.getLogger(BookDetailController.class.getName()).log(Level.SEVERE, "Không có bản sao sách nhé,ahihi", "");
        }
        return dataTable;
    }
    
    public Object[] searchAuthor(String maSach) throws SQLException, ClassNotFoundException {
        connectDatabase();
        String Author = "";
        Object[] a = new Object[5];
        ResultSet rs = null;
        PreparedStatement preparedStatement;
        String sql = "select sach.maSach,sach.tenSach,nhaphathanh.tenNhaPhatHanh,tacgia.tenTacGia,sach.isbn from  sach,tacgia,nhaphathanh,chitiettacgia \n"
                + "where sach.maSach= chitiettacgia.maSach and chitiettacgia.maTacGia= tacgia.maTacGia\n"
                + "and sach.maNhaPhatHanh = nhaphathanh.maNhaPhatHanh  and sach.maSach=?;";
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, maSach);
        rs = preparedStatement.executeQuery();

        try {
            rs.next();
            Author = Author + rs.getString(4);
            a[0] = rs.getString(1);
            a[1] = rs.getString(2);
            a[2] = rs.getString(3);
            a[4] = rs.getString(5);
            while (rs.next()) {
                Author += "\n   " + rs.getString(4);
            }
            System.out.println(Author);
            a[3] = Author;
            closeDatabase();
        } catch (SQLException ex) {
            Logger.getLogger(BookDetailController.class.getName()).log(Level.SEVERE, "Không có sách nhé,ahihi", "");
        }
        return a;
    }

    public ArrayList<Book> getListBook() throws ClassNotFoundException, SQLException {
        ArrayList<Book> arr = new ArrayList<>();
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement("Select * from sach");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {            
            Book b = new Book();
            b.setIdBook(rs.getString("maSach"));
            b.setTitleBook(rs.getString("tenSach"));
            Publisher publisher = new Publisher().getPublisherByIdPublisher(rs.getInt("maNhaPhatHanh"));
            b.setPublisher(publisher);
            String nameAuthor = getNameAuthorByIdBook(b.getIdBook()); 
            b.setNameAuthor(nameAuthor);
            arr.add(b);
        }
        closeDatabase();
        return arr;
    }

}
