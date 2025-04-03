create database if not exists quan_li_thu_vien;
use quan_li_thu_vien;

CREATE TABLE sach (
    ma_sach VARCHAR(10) PRIMARY KEY,
    ten_sach VARCHAR(255),
    tac_gia VARCHAR(255),
    mo_ta TEXT,
    so_luong INT
);

CREATE TABLE hoc_sinh (
    ma_hs VARCHAR(10) PRIMARY KEY,
    ho_ten VARCHAR(255),
    lop VARCHAR(10)
);

CREATE TABLE the_muon (
    ma_muon VARCHAR(10) PRIMARY KEY,
    ma_sach VARCHAR(10),
    ma_hs VARCHAR(10),
    ngay_muon DATE,
    ngay_tra DATE,
    trang_thai BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (ma_sach) REFERENCES sach(ma_sach),
    FOREIGN KEY (ma_hs) REFERENCES hoc_sinh(ma_hs)
);

INSERT INTO sach (ma_sach, ten_sach, tac_gia, mo_ta, so_luong) VALUES
('MS-0001', 'Lập trình Java cơ bản', 'Nguyễn Văn A', 'Sách học lập trình Java cho người mới bắt đầu.', 10),
('MS-0002', 'Lập trình Web với JavaScript', 'Trần Thị B', 'Hướng dẫn lập trình web với JavaScript cho người mới.', 15),
('MS-0003', 'Cơ sở dữ liệu SQL', 'Lê Văn C', 'Hướng dẫn sử dụng SQL để quản lý cơ sở dữ liệu.', 5),
('MS-0004', 'Python cho người mới bắt đầu', 'Nguyễn Thị D', 'Giới thiệu Python, ngôn ngữ lập trình phổ biến.', 8),
('MS-0005', 'Kỹ thuật lập trình C++', 'Phạm Minh E', 'Tìm hiểu về lập trình C++ từ cơ bản đến nâng cao.', 12);

INSERT INTO hoc_sinh (ma_hs, ho_ten, lop) VALUES
('HS-0001', 'Nguyễn Thị Mai', '10A1'),
('HS-0002', 'Trần Văn An', '10A2'),
('HS-0003', 'Lê Thị Lan', '11B1'),
('HS-0004', 'Phạm Minh Tân', '12C1'),
('HS-0005', 'Trương Thị Lan', '11A2');

INSERT INTO the_muon (ma_muon, ma_sach, ma_hs, ngay_muon, ngay_tra, trang_thai) VALUES
('MM-0001', 'MS-0001', 'HS-0001', '2025-04-01', '2025-04-10', TRUE),
('MM-0002', 'MS-0002', 'HS-0002', '2025-03-25', '2025-04-05', TRUE),
('MM-0003', 'MS-0003', 'HS-0003', '2025-03-20', '2025-03-30', TRUE),
('MM-0004', 'MS-0004', 'HS-0004', '2025-03-15', '2025-03-25', TRUE),
('MM-0005', 'MS-0005', 'HS-0005', '2025-02-28', '2025-03-10', FALSE);
