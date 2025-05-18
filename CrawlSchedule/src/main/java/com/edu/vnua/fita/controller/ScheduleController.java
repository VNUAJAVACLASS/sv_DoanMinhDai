package com.edu.vnua.fita.controller;

import com.edu.vnua.fita.service.ScheduleService;
import java.time.LocalDate;
import java.util.Scanner;

public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController() {
        this.scheduleService = new ScheduleService();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n=== THỜI KHÓA BIỂU ===");
            System.out.println("1. In thời khóa biểu cả kỳ");
            System.out.println("2. In thời khóa biểu hôm nay");
            System.out.println("3. In thời khóa biểu tuần hiện tại");
            System.out.println("4. In thời khóa biểu theo tuần");
            System.out.println("5. In thời khóa biểu theo ngày");
            System.out.println("6. Thoát");
            System.out.print("Chọn hành động (1-6): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scheduleService.printAllSchedule();
                    break;
                case 2:
                    scheduleService.printScheduleToday();
                    break;
                case 3:
                    scheduleService.printCurrentWeekSchedule();
                    break;
                case 4:
                    System.out.print("Nhập số tuần: ");
                    int tuan = scanner.nextInt();
                    scheduleService.printWeekSchedule(tuan);
                    break;
                case 5:
                    System.out.print("Nhập ngày (yyyy-MM-dd): ");
                    String dateInput = scanner.next();
                    LocalDate date = LocalDate.parse(dateInput);
                    scheduleService.printDaySchedule(date);
                    break;
                case 6:
                    System.out.println("Cảm ơn bạn đã sử dụng chương trình!");
                    System.exit(0);
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                    break;
            }
        } while (true);
    }
}
