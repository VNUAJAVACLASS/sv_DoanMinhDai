package com.edu.vnua.fita.service;

import com.edu.vnua.fita.utils.JsoupUtil;
import com.edu.vnua.fita.entity.Subject;
import com.edu.vnua.fita.entity.Day;
import com.edu.vnua.fita.entity.Schedule;
import com.edu.vnua.fita.entity.Week;
import com.edu.vnua.fita.utils.DateHelper;
import com.edu.vnua.fita.utils.PlaywrightUtil;

import java.time.LocalDate;
import java.util.Map;

public class ScheduleService {
    private Schedule schedule;

    public ScheduleService() {
        try {
            String dataSchedule = PlaywrightUtil.CrawlData();
            this.schedule = JsoupUtil.parseSchedule(dataSchedule);
        } catch (Exception e) {
            System.out.println("Không thể tải thời khóa biểu: " + e.getMessage());
            this.schedule = new Schedule();
        }
    }

    public void printScheduleToday() {
        LocalDate today = LocalDate.now();
        int tuan = DateHelper.getWeekFromDate(today);
        int thu = DateHelper.getThuFromDate(today);

        System.out.println("📅 Hôm nay là: " + today + " (Tuần " + tuan + ", " + getThuString(thu) + ")");

        Week t = schedule.getWeekMap().get(tuan);
        if (t == null) {
            System.out.println("⛔ Không có dữ liệu thời khóa biểu cho tuần này.");
            return;
        }
        Day day = t.getDay(thu);
        printDaySchedule(thu, today, day);
    }

    public void printCurrentWeekSchedule() {
        LocalDate today = LocalDate.now();
        int tuan = DateHelper.getWeekFromDate(today);

        System.out.println("📅 Hôm nay: " + today + " → Tuần " + tuan);
        Week t = schedule.getWeekMap().get(tuan);

        if (t == null) {
            System.out.println("⛔ Không có dữ liệu thời khóa biểu cho tuần này.");
            return;
        }

        for (int thu = 2; thu <= 7; thu++) {
            Day day = t.getDay(thu);
            LocalDate date = DateHelper.START_DATE.plusDays((tuan - 1) * 7L + (thu - 2));
            printDaySchedule(thu, date, day);
        }
    }

    public void printWeekSchedule(int tuan) {
        Week t = schedule.getWeekMap().get(tuan);
        if (t == null) {
            System.out.println("⛔ Không có thời khóa biểu cho tuần " + tuan);
            return;
        }
        System.out.println("📅 Thời khóa biểu tuần " + tuan + ":");
        printAll(tuan, t);
    }

    public void printDaySchedule(LocalDate date) {
        int tuan = DateHelper.getWeekFromDate(date);
        int thu = DateHelper.getThuFromDate(date);

        Week t = schedule.getWeekMap().get(tuan);
        if (t == null) {
            System.out.println("⛔ Không có thời khóa biểu cho tuần " + tuan);
            return;
        }

        Day day = t.getDay(thu);
        System.out.println("📅 " + date + " (Tuần " + tuan + ", " + getThuString(thu) + "):");
        printDaySchedule(thu, date, day);
    }

    public void printAllSchedule() {
        for (Map.Entry<Integer, Week> entryTuan : schedule.getWeekMap().entrySet()) {
            int soTuan = entryTuan.getKey();
            Week week = entryTuan.getValue();
            System.out.println("====== Tuần " + soTuan + " ======");
            printAll(soTuan, week);
        }
    }

    private void printAll(int soTuan, Week week) {
        for (Map.Entry<Integer, Day> entryNgay : week.getDayList().entrySet()) {
            int thu = entryNgay.getKey();
            Day day = entryNgay.getValue();
            LocalDate date = DateHelper.START_DATE.plusDays((soTuan - 1) * 7L + (thu - 2));
            printDaySchedule(thu, date, day);
        }
    }

    private void printDaySchedule(int thu, LocalDate date, Day day) {
        System.out.println(getThuString(thu) + (date != null ? " (" + date + ")" : "") + ":");
        if (day == null || day.getSubjectList().isEmpty()) {
            System.out.println("     🛌 Không có môn học.");
            return;
        }
        for (Subject mh : day.getSubjectList()) {
            printMonHoc(mh);
        }
    }

    private void printMonHoc(Subject mh) {
        System.out.println("   ✅ " + mh.getMaMon() + " - " + mh.getTenMon());
        System.out.println("      Tiết BD: " + mh.getTietBD() + ", Số tiết: " + mh.getSoTiet());
        System.out.println("      Phòng: " + mh.getPhong() + ", GV: " + mh.getGv());
    }

    private String getThuString(int thu) {
        switch (thu) {
            case 2:
                return "Thứ 2";
            case 3:
                return "Thứ 3";
            case 4:
                return "Thứ 4";
            case 5:
                return "Thứ 5";
            case 6:
                return "Thứ 6";
            case 7:
                return "Thứ 7";
            case 8:
                return "Chủ Nhật";
            default:
                return "Không xác định";
        }
    }
}
