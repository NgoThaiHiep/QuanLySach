select ThoiGianDangNhap,ThoiGianDaLam,ThoiGianHoatDongID  from ThoiGianHoatDong where NhanVienID = '232108' and NgayDangNhap = '2023-10-30' and ThoiGianDangNhap >= '00:00:00' and ThoiGianDangNhap <= '23:00:00'

SELECT SUM(DATEPART(HOUR, ThoiGianDaLam) * 3600 + DATEPART(MINUTE, ThoiGianDaLam) * 60 + DATEPART(SECOND, ThoiGianDaLam)) AS total_seconds,
SUM(DATEPART(HOUR, ThoiGianDaLam) * 3600 + DATEPART(MINUTE, ThoiGianDaLam) * 60 + DATEPART(SECOND, ThoiGianDaLam)) / 3600 as h  , 
(SUM(DATEPART(HOUR, ThoiGianDaLam) * 3600 + DATEPART(MINUTE, ThoiGianDaLam) * 60 + DATEPART(SECOND, ThoiGianDaLam)) % 3600) /60 as m , 
((SUM(DATEPART(HOUR, ThoiGianDaLam) * 3600 + DATEPART(MINUTE, ThoiGianDaLam) * 60 + DATEPART(SECOND, ThoiGianDaLam)) % 3600) % 60) as s
FROM ThoiGianHoatDong where NhanVienID = '234397'

select 