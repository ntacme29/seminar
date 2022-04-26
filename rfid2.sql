-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 26, 2022 lúc 07:47 PM
-- Phiên bản máy phục vụ: 10.4.18-MariaDB
-- Phiên bản PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `rfid2`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phanquyen`
--

CREATE TABLE `phanquyen` (
  `IDPhanQuyen` varchar(10) NOT NULL,
  `TenQuyen` varchar(100) NOT NULL,
  `MoTaQuyen` text NOT NULL,
  `TrangThai` varchar(10) NOT NULL DEFAULT 'Hiện'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `phanquyen`
--

INSERT INTO `phanquyen` (`IDPhanQuyen`, `TenQuyen`, `MoTaQuyen`, `TrangThai`) VALUES
('PQ0', 'admin', 'QLBanHangQLNhapHangQLMonAnQLNguyenLieuQLCongThucQLHoaDonQLHDNhapQLKhuyenMaiQLKhachHangQLNhanVienQLNhaCungCapQLTaiKhoanQLPhanQuyenQLThongKe', 'Hiện'),
('PQ1', 'Quản lý', 'QLBanHangQLNhapHangQLMonAnQLNguyenLieuQLCongThucQLHoaDonQLHDNhapQLKhuyenMaiQLKhachHangQLNhanVienQLNhaCungCapQLThongKe', 'Hiện'),
('PQ2', 'Bán hàng', 'QLBanHangQLHoaDonQLKhachHang', 'Hiện'),
('PQ3', 'Nhập hàng', 'QLNhapHangQLHDNhap', 'Hiện'),
('PQ4', 'Bán và nhập hàng', 'QLBanHangQLNhapHangQLHoaDonQLHDNhapQLKhachHang', 'Hiện'),
('PQ5', 'jbskughfwiebgiu', 'QLBanHangQLMonAn', 'null'),
('PQ6', 'se;ouigrfiwehgbhjwe', 'QLBanHangQLMonAn', 'null');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `productinstance`
--

CREATE TABLE `productinstance` (
  `product_instance_id` varchar(30) NOT NULL,
  `product_line_id` varchar(30) NOT NULL,
  `product_mount` int(1) NOT NULL,
  `is_check` int(1) NOT NULL,
  `TrangThai` varchar(10) NOT NULL DEFAULT 'Hiện'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `productinstance`
--

INSERT INTO `productinstance` (`product_instance_id`, `product_line_id`, `product_mount`, `is_check`, `TrangThai`) VALUES
('12345', 'SP01', 5, 1, 'Hiện'),
('123456', 'SP01', 1110, 1, 'Hiện'),
('22222', 'SP01', 222, 1, 'Hiện'),
('23456', 'SP02', 3, 1, 'Hiện');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `productline`
--

CREATE TABLE `productline` (
  `product_line_id` varchar(30) NOT NULL,
  `name` varchar(300) NOT NULL,
  `price` double(10,0) NOT NULL DEFAULT 0,
  `stock` int(11) NOT NULL DEFAULT 0,
  `TrangThai` varchar(10) NOT NULL DEFAULT 'Hiện'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `productline`
--

INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`, `TrangThai`) VALUES
('SP01', 'Smart Tivi', 4999000, 100, 'Hiện'),
('SP02', 'Laptop Asus', 22999999, 5, 'Hiện'),
('SP03', 'Smart Watch', 3000000, 20, 'Hiện'),
('SP04', 'Unknown', 999999, 9, 'Hiện');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoannhanvien`
--

CREATE TABLE `taikhoannhanvien` (
  `TaiKhoan` varchar(30) NOT NULL,
  `IDNhanVien` varchar(10) NOT NULL,
  `IDPhanQuyen` varchar(10) NOT NULL,
  `MatKhau` varchar(50) NOT NULL,
  `TrangThai` varchar(10) NOT NULL DEFAULT 'Hiện'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `taikhoannhanvien`
--

INSERT INTO `taikhoannhanvien` (`TaiKhoan`, `IDNhanVien`, `IDPhanQuyen`, `MatKhau`, `TrangThai`) VALUES
('admin', 'NV01', 'PQ0', 'admin', 'Hiện'),
('MaiDangKhoi', 'NV10', 'PQ2', '123456', 'Hiện'),
('QuocHung', 'NV06', 'PQ2', '123456', 'Hiện'),
('QuocTuan', 'NV02', 'PQ2', '123456', 'Hiện'),
('TanPhat', 'NV04', 'PQ4', '123456', 'Hiện'),
('Thinh', 'NV05', 'PQ2', '123456', 'Hiện'),
('TriNhan', 'NV03', 'PQ1', '123456', 'Hiện'),
('xamqua', 'NV11', 'PQ1', '123456', 'Hiện');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `productinstance`
--
ALTER TABLE `productinstance`
  ADD PRIMARY KEY (`product_instance_id`),
  ADD KEY `product_instance_id` (`product_instance_id`,`product_line_id`),
  ADD KEY `product_line_id` (`product_line_id`);

--
-- Chỉ mục cho bảng `productline`
--
ALTER TABLE `productline`
  ADD PRIMARY KEY (`product_line_id`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `productinstance`
--
ALTER TABLE `productinstance`
  ADD CONSTRAINT `productinstance_ibfk_1` FOREIGN KEY (`product_line_id`) REFERENCES `productline` (`product_line_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
