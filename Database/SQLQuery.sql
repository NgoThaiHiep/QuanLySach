select ThoiGianDangNhap,ThoiGianDaLam,ThoiGianHoatDongID  from ThoiGianHoatDong where NhanVienID = '232108' and ThoiGianDangNhap >= '00:00:00' and ThoiGianDangNhap <= '23:00:00'

SELECT SUM(DATEPART(HOUR, ThoiGianDaLam) * 3600 + DATEPART(MINUTE, ThoiGianDaLam) * 60 + DATEPART(SECOND, ThoiGianDaLam)) AS total_seconds,
SUM(DATEPART(HOUR, ThoiGianDaLam) * 3600 + DATEPART(MINUTE, ThoiGianDaLam) * 60 + DATEPART(SECOND, ThoiGianDaLam)) / 3600 as h  , 
(SUM(DATEPART(HOUR, ThoiGianDaLam) * 3600 + DATEPART(MINUTE, ThoiGianDaLam) * 60 + DATEPART(SECOND, ThoiGianDaLam)) % 3600) /60 as m , 
((SUM(DATEPART(HOUR, ThoiGianDaLam) * 3600 + DATEPART(MINUTE, ThoiGianDaLam) * 60 + DATEPART(SECOND, ThoiGianDaLam)) % 3600) % 60) as s
FROM ThoiGianHoatDong where NhanVienID = '234397'

select * from NhanVien where  HoTenNhanVien like N'%ngô thái hiệ%'
update TaiKhoan 
set TrangThai = N'Đã đăng xuất'
where TenDangNhap = '232108'

select * from ThoiGianHoatDong
select * from HangCho


SELECT * FROM KhachHang WHERE CONVERT(NVARCHAR(MAX), SoDienThoai) LIKE '%0123%'

exec sp_helpconstraint HangCho


