package com.edu.vnua.fita.controller;

import com.edu.vnua.fita.service.TKBService;
import java.time.LocalDate;
import java.util.Scanner;

public class TKBController {
    private static final String TKB_URL = "tkb_doanminhdai.html";
    private TKBService tkbService;

    public TKBController() {
        this.tkbService = new TKBService(TKB_URL);
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
                    tkbService.printAllTKB();
                    break;
                case 2:
                    tkbService.printTKBToday();
                    break;
                case 3:
                    tkbService.inTKBTuanHienTai();
                    break;
                case 4:
                    // Nhập tuần từ người dùng
                    System.out.print("Nhập số tuần: ");
                    int tuan = scanner.nextInt();
                    tkbService.inTKBTheoTuan(tuan);
                    break;
                case 5:
                    // Nhập ngày từ người dùng
                    System.out.print("Nhập ngày (yyyy-MM-dd): ");
                    String dateInput = scanner.next();
                    LocalDate date = LocalDate.parse(dateInput);
                    tkbService.inTKBTheoNgay(date);
                    break;
                case 6:
                    System.out.println("Cảm ơn bạn đã sử dụng chương trình!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
            }
        } while (choice != 6);

        scanner.close();
    }
}
