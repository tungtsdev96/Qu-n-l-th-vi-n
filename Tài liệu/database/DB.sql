-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: tkxdpm
-- ------------------------------------------------------
-- Server version	5.7.15-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bansaosach`
--

DROP TABLE IF EXISTS `bansaosach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bansaosach` (
  `maSach` varchar(45) NOT NULL,
  `maBanSao` varchar(10) NOT NULL,
  `soThuTu` int(11) NOT NULL,
  `loaiBanSao` int(11) NOT NULL,
  `gia` int(11) NOT NULL,
  `trangThai` int(11) NOT NULL,
  `xoa` int(11) NOT NULL,
  PRIMARY KEY (`maBanSao`),
  KEY `maSach_idx` (`maSach`),
  CONSTRAINT `maSach` FOREIGN KEY (`maSach`) REFERENCES `sach` (`maSach`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bansaosach`
--

LOCK TABLES `bansaosach` WRITE;
/*!40000 ALTER TABLE `bansaosach` DISABLE KEYS */;
INSERT INTO `bansaosach` VALUES ('IT1000','IT1000-01',1,1,50000,0,0),('IT1000','IT1000-02',2,1,50000,0,0),('IT1000','IT1000-03',3,1,50000,0,0),('IT1000','IT1000-04',4,1,50000,0,0),('IT1000','IT1000-05',5,1,50000,0,0),('IT1000','IT1000-06',6,1,50000,0,0),('IT1000','IT1000-07',7,1,50000,0,0),('IT1000','IT1000-08',8,0,60000,0,0),('IT1000','IT1000-09',9,0,60000,0,0),('IT1000','IT1000-10',10,0,60000,0,0),('IT1001','IT1001-01',1,1,70000,0,0),('IT1001','IT1001-02',2,0,30000,0,0),('IT6666','IT6666-01',1,1,56000,0,0),('IT6666','IT6666-02',2,1,56000,0,0),('IT6666','IT6666-03',3,1,56000,0,0),('IT7777','IT7777-01',1,1,56789,0,0),('IT7777','IT7777-02',2,1,56789,0,0),('IT7777','IT7777-03',3,1,56789,0,0),('IT7777','IT7777-04',4,1,56789,0,0),('IT7777','IT7777-05',5,1,56789,0,0),('QS1111','QS1111-01',1,1,40000,1,0),('QS1111','QS1111-02',2,1,40000,0,0),('QS1111','QS1111-03',3,0,40000,0,0),('TH1000','TH1000-01',1,1,75000,0,0),('TS9999','TS9999-01',1,1,90000,0,0),('TS9999','TS9999-02',2,1,90000,0,0),('TS9999','TS9999-03',3,1,90000,0,0),('TS9999','TS9999-04',4,0,90000,0,0),('TS9999','TS9999-05',5,0,90000,0,0),('VH1000','VH1000-01',1,1,30000,0,0),('VH1001','VH1001-01',1,1,40000,0,0),('VN1000','VN1000-01',1,1,40000,0,0),('VN1000','VN1000-02',2,1,40000,0,0),('VN1000','VN1000-03',3,0,40000,0,0),('VN1000','VN1000-04',4,0,30000,0,0);
/*!40000 ALTER TABLE `bansaosach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitietmuonsach`
--

DROP TABLE IF EXISTS `chitietmuonsach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chitietmuonsach` (
  `maThongTinMuonSach` varchar(100) NOT NULL,
  `maBanSaoSach` varchar(10) NOT NULL,
  `trangThai` int(11) DEFAULT NULL,
  `ngayMuon` varchar(45) DEFAULT NULL,
  `ngayTra` varchar(45) DEFAULT NULL,
  `tienPhat` int(11) DEFAULT NULL,
  `lyDoPhat` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`maThongTinMuonSach`,`maBanSaoSach`),
  KEY `key_ban_sao_idx` (`maBanSaoSach`),
  CONSTRAINT `key_ban_sao` FOREIGN KEY (`maBanSaoSach`) REFERENCES `bansaosach` (`maBanSao`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `key_thong_tin_muon` FOREIGN KEY (`maThongTinMuonSach`) REFERENCES `thongtinmuontrasach` (`maThongTinMuonTraSach`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitietmuonsach`
--

LOCK TABLES `chitietmuonsach` WRITE;
/*!40000 ALTER TABLE `chitietmuonsach` DISABLE KEYS */;
INSERT INTO `chitietmuonsach` VALUES ('TQB0001','IT1000-01',2,'16/12/2017','20/12/2017',0,NULL),('TQB0001','QS1111-01',2,'16/12/2017','20/12/2017',10000,'làm rách sách'),('TQB0002','IT1000-01',1,'23/11/2017','02/12/2017',0,''),('TQB0002','IT1001-01',1,'23/11/2017','02/12/2017',0,''),('TQB1512230240510','IT1001-01',2,'02/12/2017','02/12/2017',30000,''),('TQB1512230240510','QS1111-01',2,'02/12/2017','02/12/2017',0,''),('TQB1512232807313','IT7777-01',2,'02/12/2017','02/12/2017',0,''),('TQB1512232807313','VH1001-01',2,'02/12/2017','02/12/2017',0,''),('TQB1512266767074','IT1000-01',2,'03/12/2017','03/12/2017',0,''),('TQB1512266767074','IT6666-01',2,'03/12/2017','03/12/2017',0,''),('TQB1512266767074','QS1111-01',1,'03/12/2017',NULL,NULL,NULL);
/*!40000 ALTER TABLE `chitietmuonsach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitiettacgia`
--

DROP TABLE IF EXISTS `chitiettacgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chitiettacgia` (
  `maSach` varchar(6) NOT NULL,
  `maTacGia` int(11) NOT NULL,
  PRIMARY KEY (`maSach`,`maTacGia`),
  KEY `fk_Sach_has_TacGia_TacGia1_idx` (`maTacGia`),
  KEY `fk_Sach_has_TacGia_Sach1_idx` (`maSach`),
  CONSTRAINT `fk_Sach_has_TacGia_Sach1` FOREIGN KEY (`maSach`) REFERENCES `sach` (`maSach`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Sach_has_TacGia_TacGia1` FOREIGN KEY (`maTacGia`) REFERENCES `tacgia` (`maTacGia`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitiettacgia`
--

LOCK TABLES `chitiettacgia` WRITE;
/*!40000 ALTER TABLE `chitiettacgia` DISABLE KEYS */;
INSERT INTO `chitiettacgia` VALUES ('IT1001',1),('TS9999',1),('VH1000',1),('IT7777',2),('QS1111',2),('TS9999',2),('VH1001',2),('IT6666',4),('IT1000',12),('TH1000',12),('TS9999',12),('VN1000',12),('IT6666',13),('IT7777',14);
/*!40000 ALTER TABLE `chitiettacgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nguoimuon`
--

DROP TABLE IF EXISTS `nguoimuon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nguoimuon` (
  `maNguoiMuon` varchar(10) NOT NULL,
  `tenTaiKhoan` varchar(20) NOT NULL,
  `mssv` varchar(8) DEFAULT NULL,
  `soTienDatCoc` int(11) NOT NULL,
  `giaiDoanHoc` varchar(15) DEFAULT NULL,
  `trangThai` int(11) NOT NULL,
  PRIMARY KEY (`maNguoiMuon`),
  KEY `fk_NguoiMuon_TaiKhoan1_idx` (`tenTaiKhoan`),
  CONSTRAINT `fk_NguoiMuon_TaiKhoan1` FOREIGN KEY (`tenTaiKhoan`) REFERENCES `taikhoan` (`tenTaiKhoan`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguoimuon`
--

LOCK TABLES `nguoimuon` WRITE;
/*!40000 ALTER TABLE `nguoimuon` DISABLE KEYS */;
INSERT INTO `nguoimuon` VALUES ('NM0001','test0','20141111',0,'5/2019',1),('NM0002','test1','20141122',0,'5/2019',1),('NM0003','test2','20141133',0,'5/2019',1),('NM0004','test3','20141144',0,'5/2019',1),('NM0005','test4','20141155',0,'5/2019',1),('NM0006','test5','',50000,'',1),('NM0007','test6','',50000,'',1),('NM0008','test7',NULL,50000,'15/01/2017',1),('NM0009','test8',NULL,50000,'15/01/2017',1);
/*!40000 ALTER TABLE `nguoimuon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhaphathanh`
--

DROP TABLE IF EXISTS `nhaphathanh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nhaphathanh` (
  `maNhaPhatHanh` int(11) NOT NULL AUTO_INCREMENT,
  `tenNhaPhatHanh` varchar(45) NOT NULL,
  PRIMARY KEY (`maNhaPhatHanh`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhaphathanh`
--

LOCK TABLES `nhaphathanh` WRITE;
/*!40000 ALTER TABLE `nhaphathanh` DISABLE KEYS */;
INSERT INTO `nhaphathanh` VALUES (1,'Nhà xuất bản Đại học Bách Khoa Hà Nội'),(2,'Nhà xuất bản Xây Dựng '),(3,'Nhà xuất bản giáo dục'),(4,'Nhà xuất bản Đại học KTQD'),(5,'Nhà xuất bản Kim Đồng'),(6,'Nhà xuất bản Tuổi trẻ'),(7,'Nhà xuất bản Đại học Quốc gia Hà Nội'),(8,'Nhà xuất bản Đại học Quốc gia TPHCM'),(9,'Nhà xuất bản Lao động'),(10,'Nhà xuất bản Chính trị Quốc gia - Sự thật');
/*!40000 ALTER TABLE `nhaphathanh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sach`
--

DROP TABLE IF EXISTS `sach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sach` (
  `maSach` varchar(6) NOT NULL,
  `tenSach` varchar(45) NOT NULL,
  `maNhaPhatHanh` int(11) NOT NULL,
  `isbn` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`maSach`),
  KEY `maNhaPhatHanh_idx` (`maNhaPhatHanh`),
  CONSTRAINT `maNhaPhatHanh` FOREIGN KEY (`maNhaPhatHanh`) REFERENCES `nhaphathanh` (`maNhaPhatHanh`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sach`
--

LOCK TABLES `sach` WRITE;
/*!40000 ALTER TABLE `sach` DISABLE KEYS */;
INSERT INTO `sach` VALUES ('IT1000','Toán Rời Rạc',1,'0123456789'),('IT1001','Cấu Trúc Dữ Liệu và Giải thuật',1,'1234567888'),('IT6666','test',1,'231232'),('IT7777','wqwqe',1,'21312321'),('QS1111','Test',1,'123456789'),('TH1000','Đại số tuyến tính',2,'3654546666'),('TS9999','Sách Test',1,'2752525'),('VH1000','Đắc Nhân Tâm',6,'9876543210'),('VH1001','Nhà Giả Kim',5,'7654123890'),('VN1000','ten la gi',1,'123456789');
/*!40000 ALTER TABLE `sach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tacgia`
--

DROP TABLE IF EXISTS `tacgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tacgia` (
  `maTacGia` int(11) NOT NULL AUTO_INCREMENT,
  `tenTacGia` varchar(45) NOT NULL,
  PRIMARY KEY (`maTacGia`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tacgia`
--

LOCK TABLES `tacgia` WRITE;
/*!40000 ALTER TABLE `tacgia` DISABLE KEYS */;
INSERT INTO `tacgia` VALUES (1,'Nguyễn Đức Nghĩa'),(2,'Huỳnh Quyết Thắng'),(3,'Nguyễn Viết Đông'),(4,'Đậu Thế Cấp'),(5,'Võ Khắc Thường'),(6,'Paulo Coelho'),(7,'Dale Carnegie'),(8,'Andy Andrews'),(9,'Tony Buổi Sáng'),(10,'Ngô Tất Tố'),(11,'Nguyễn Du'),(12,'Trần Sơn Tùng'),(13,'ád'),(14,'qewqe');
/*!40000 ALTER TABLE `tacgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taikhoan` (
  `tenTaiKhoan` varchar(20) NOT NULL,
  `matKhau` varchar(20) NOT NULL,
  `ten` varchar(45) NOT NULL,
  `gioiTinh` varchar(3) NOT NULL,
  `email` varchar(50) NOT NULL,
  `soDienThoai` varchar(15) NOT NULL,
  PRIMARY KEY (`tenTaiKhoan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` VALUES ('admin','1','Trần Sơn Tùng','Nam','mail@gmail.com','0123456789'),('test0','1','Phạm Hải Linh','Nam','mail1@gmail.com','0123456789'),('test1','1','Bùi Văn Nam','Nam','mail2@gmail.com','0123456789'),('test2','1','Nguyễn Văn Thanh','Nam','mail3@gmail.com','0123456789'),('test3','1','Nguyễn Văn Đức','Nam','mail4@gmail.com','0123456789'),('test4','1','Lê  Thị Tươi','Nữ','mail5@gmail.com','0123456789'),('test5','1','Đỗ Hồng Diệp','Nữ','mail6@gmail.com','0123456789'),('test6','1','Trần Thị Dung','Nữ','mail7@gmail.com','0123456789'),('test7','1','Nguyễn Văn Viết','Nữ','mail8@gmail.com','0123456789'),('test8','1','Lê Đức Anh','Nữ','mail9@gmail.com','0123456789');
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `themuon`
--

DROP TABLE IF EXISTS `themuon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `themuon` (
  `maTheMuon` varchar(10) NOT NULL,
  `ngayHetHan` varchar(15) NOT NULL,
  `trangThai` int(11) DEFAULT NULL,
  `maKichHoat` varchar(10) NOT NULL,
  `maNguoiMuon` varchar(10) NOT NULL,
  PRIMARY KEY (`maTheMuon`),
  KEY `maNguoiMuon_idx` (`maNguoiMuon`),
  CONSTRAINT `maNguoiMuon` FOREIGN KEY (`maNguoiMuon`) REFERENCES `nguoimuon` (`maNguoiMuon`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `themuon`
--

LOCK TABLES `themuon` WRITE;
/*!40000 ALTER TABLE `themuon` DISABLE KEYS */;
INSERT INTO `themuon` VALUES ('NM0008','3/11/2019',1,'679957','NM0008'),('NM0009','3/11/2019',1,'614540','NM0009'),('THE0001','11/09/2018',NULL,'87575757','NM0001'),('THE0002','11/09/2018',NULL,'12456546','NM0002'),('THE0003','11/09/2018',NULL,'56456466','NM0003'),('THE0004','11/09/2018',NULL,'13213232','NM0004'),('THE0005','11/09/2018',NULL,'adsad223','NM0005'),('THE0006','11/09/2018',NULL,'das3f3rr3','NM0006'),('THE0007','11/09/2018',NULL,'fsd32342f','NM0007');
/*!40000 ALTER TABLE `themuon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thongtinmuontrasach`
--

DROP TABLE IF EXISTS `thongtinmuontrasach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thongtinmuontrasach` (
  `maThongTinMuonTraSach` varchar(100) NOT NULL,
  `ngayDangKi` varchar(10) NOT NULL,
  `hanTra` varchar(10) DEFAULT NULL,
  `trangThai` varchar(15) DEFAULT NULL,
  `maNguoiMuon` varchar(10) NOT NULL,
  `tienDatCoc` int(11) DEFAULT NULL,
  PRIMARY KEY (`maThongTinMuonTraSach`),
  KEY `key_ma_nguoi_muon_idx` (`maNguoiMuon`),
  CONSTRAINT `key_ma_nguoi_muon` FOREIGN KEY (`maNguoiMuon`) REFERENCES `nguoimuon` (`maNguoiMuon`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thongtinmuontrasach`
--

LOCK TABLES `thongtinmuontrasach` WRITE;
/*!40000 ALTER TABLE `thongtinmuontrasach` DISABLE KEYS */;
INSERT INTO `thongtinmuontrasach` VALUES ('TQB0001','15/12/2017','21/12/2017','2','NM0001',0),('TQB0002','22/11/2017','28/11/2017','1','NM0002',120000),('TQB1512230240510','02/12/2017','16/12/2017','2','NM0007',110000),('TQB1512232807313','02/12/2017','16/12/2017','2','NM0006',96789),('TQB1512266767074','03/12/2017','17/12/2017','1','NM0009',146000);
/*!40000 ALTER TABLE `thongtinmuontrasach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thuthu`
--

DROP TABLE IF EXISTS `thuthu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thuthu` (
  `maThuThu` varchar(10) NOT NULL,
  `tenTaiKhoan` varchar(20) NOT NULL,
  `ngaySinh` varchar(10) NOT NULL,
  PRIMARY KEY (`maThuThu`),
  KEY `key_thu_thu_idx` (`tenTaiKhoan`),
  CONSTRAINT `key_thu_thu` FOREIGN KEY (`tenTaiKhoan`) REFERENCES `taikhoan` (`tenTaiKhoan`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thuthu`
--

LOCK TABLES `thuthu` WRITE;
/*!40000 ALTER TABLE `thuthu` DISABLE KEYS */;
INSERT INTO `thuthu` VALUES ('TH0001','admin','17/11/1996');
/*!40000 ALTER TABLE `thuthu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-19 12:35:38
