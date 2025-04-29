package com.edu.vnua.fita.service;

import com.edu.vnua.fita.entity.MonHoc;
import com.edu.vnua.fita.entity.Ngay;
import com.edu.vnua.fita.entity.ThoiKhoaBieu;
import com.edu.vnua.fita.entity.Tuan;
import com.edu.vnua.fita.ultils.ReadHtmlWithJsoup;
import com.edu.vnua.fita.ultils.TKBDateHelper;

import java.time.LocalDate;
import java.util.Map;

public class TKBService {

    private final ThoiKhoaBieu tkb;

    public TKBService(String url) {
        try {
            this.tkb = ReadHtmlWithJsoup.parseTKB(url);
        } catch (Exception e) {
            throw new RuntimeException("Không thể đọc thời khóa biểu từ URL: " + url, e);
        }
    }

    public void printTKBToday() {
        LocalDate today = LocalDate.now();
        int tuan = TKBDateHelper.getWeekFromDate(today);
        int thu = TKBDateHelper.getThuFromDate(today);

        System.out.println("📅 Hôm nay là: " + today + " (Tuần " + tuan + ", " + getThuString(thu) + ")");

        Tuan t = tkb.getDsTuan().get(tuan);
        if (t == null) {
            System.out.println("⛔ Không có dữ liệu thời khóa biểu cho tuần này.");
            return;
        }

        Ngay ngay = t.getNgay(thu);
        printNgay(thu, today, ngay);
    }

    public void inTKBTuanHienTai() {
        LocalDate today = LocalDate.now();
        int tuan = TKBDateHelper.getWeekFromDate(today);

        System.out.println("📅 Hôm nay: " + today + " → Tuần " + tuan);
        Tuan t = tkb.getDsTuan().get(tuan);

        if (t == null) {
            System.out.println("⛔ Không có dữ liệu thời khóa biểu cho tuần này.");
            return;
        }

        for (int thu = 2; thu <= 7; thu++) { // Lưu ý: Thứ 1 (CN) không in ra trong in theo tuần
            Ngay ngay = t.getNgay(thu);
            LocalDate date = TKBDateHelper.START_DATE.plusDays((tuan - 1) * 7 + (thu - 2));
            printNgay(thu, date, ngay);
        }
    }

    public void inTKBTheoTuan(int tuan) {
        Tuan t = tkb.getDsTuan().get(tuan);
        if (t == null) {
            System.out.println("⛔ Không có thời khóa biểu cho tuần " + tuan);
            return;
        }
        System.out.println("📅 Thời khóa biểu tuần " + tuan + ":");
        printAll(tuan, t);
    }

    public void inTKBTheoNgay(LocalDate date) {
        int tuan = TKBDateHelper.getWeekFromDate(date);
        int thu = TKBDateHelper.getThuFromDate(date);

        Tuan t = tkb.getDsTuan().get(tuan);
        if (t == null) {
            System.out.println("⛔ Không có thời khóa biểu cho tuần " + tuan);
            return;
        }

        Ngay ngay = t.getNgay(thu);
        System.out.println("📅 " + date + " (Tuần " + tuan + ", " + getThuString(thu) + "):");
        printNgay(thu, date, ngay);
    }

    public void printAllTKB() {
        for (Map.Entry<Integer, Tuan> entryTuan : tkb.getDsTuan().entrySet()) {
            int soTuan = entryTuan.getKey();
            Tuan tuan = entryTuan.getValue();
            System.out.println("====== Tuần " + soTuan + " ======");
            printAll(soTuan, tuan);
        }
    }

    private void printAll(int soTuan, Tuan tuan) {
        for (Map.Entry<Integer, Ngay> entryNgay : tuan.getDsNgay().entrySet()) {
            int thu = entryNgay.getKey();
            Ngay ngay = entryNgay.getValue();
            LocalDate date = TKBDateHelper.START_DATE.plusDays((soTuan - 1) * 7 + (thu - 2));
            printNgay(thu, date, ngay);
        }
    }

    private void printNgay(int thu, LocalDate date, Ngay ngay) {
        System.out.println(getThuString(thu) + (date != null ? " (" + date + ")" : "") + ":");
        if (ngay == null || ngay.getDsMon().isEmpty()) {
            System.out.println("     🛌 Không có môn học.");
            return;
        }
        for (MonHoc mh : ngay.getDsMon()) {
            printMonHoc(mh);
        }
    }

    private void printMonHoc(MonHoc mh) {
        System.out.println("   ✅ " + mh.getMaMon() + " - " + mh.getTenMon());
        System.out.println("      Tiết BD: " + mh.getTietBD() + ", Số tiết: " + mh.getSoTiet());
        System.out.println("      Phòng: " + mh.getPhong() + ", GV: " + mh.getGv());
    }

    private String getThuString(int thu) {
        switch (thu) {
            case 2: return "Thứ 2";
            case 3: return "Thứ 3";
            case 4: return "Thứ 4";
            case 5: return "Thứ 5";
            case 6: return "Thứ 6";
            case 7: return "Thứ 7";
            case 8: return "Chủ Nhật";
            default: return "Không xác định";
        }
    }
}
