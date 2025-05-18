

import java.util.Scanner;

import entity.HRM;

public class Main {
	public static void main(String[] args) {
        HRM hrm = new HRM();
        boolean continue_ = true;
        hrm.initDemoData();
        while(continue_) {
        	Scanner sc = new Scanner(System.in);
        	System.out.println("Chon chuc nang:");
        	System.out.println("1. Xem toan bo danh sach nhan su.");
        	System.out.println("2. Xem danh sach sinh vien.");
        	System.out.println("3. Xem danh sach giang vien.");
        	System.out.println("4. Tim nhan su.");
        	System.out.println("5. Them nhan su.");
        	System.out.println("6. Nhap diem sinh vien.");
        	System.out.println("7. Dang ky mon hoc.");
        	System.out.println("8. Thoat.");
        	int choice = sc.nextInt();
        	sc.nextLine();
        	switch (choice) {
			case 1:
				hrm.printHRList();
				System.out.println("\n>> Nhan Enter de quay lai menu...");
				sc.nextLine();
				break;
			case 2:
				hrm.printStudent();
				System.out.println("\n>> Nhan Enter de quay lai menu...");
				sc.nextLine();
				break;
			case 3:
				hrm.printLecturer();
				System.out.println("\n>> Nhan Enter de quay lai menu...");
				sc.nextLine();
				break;
			case 4:
				System.out.println("Nhap vao ma code cua nhan su:");
				String key = sc.nextLine();
				System.out.println(hrm.searchHuman(key));
				System.out.println("\n>> Nhan Enter de quay lai menu...");
				sc.nextLine();
				break;
			case 5:
				hrm.addHR(sc);
				System.out.println("\n>> Nhan Enter de quay lai menu...");
				sc.nextLine();
				break;
			case 6:
				hrm.addHR(sc);
				System.out.println("\n>> Nhan Enter de quay lai menu...");
				sc.nextLine();
				break;
			case 7:
				hrm.registerSubject(sc);
				System.out.println("\n>> Nhan Enter de quay lai menu...");
				sc.nextLine();
				break;
			case 8:
				continue_ = false;
				break;
			default:
				System.out.println("Nhap khong dung!Thu lai");
				break;
			}
        }
	}
}
