package application;

import java.util.Scanner;

import service.SubjectService;
import service.UserService;

public class Main {
	public static void main(String[] args) {
        UserService userService = new UserService();
        SubjectService subjectService = new SubjectService();
        boolean continue_ = true;
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
				System.out.println(userService.printHRList());
				System.out.println("\n>> Nhan Enter de quay lai menu...");
				sc.nextLine();
				break;
			case 2:
				System.out.println(userService.printStudent());
				System.out.println("\n>> Nhan Enter de quay lai menu...");
				sc.nextLine();
				break;
			case 3:
				System.out.println(userService.printLecturer());
				System.out.println("\n>> Nhan Enter de quay lai menu...");
				sc.nextLine();
				break;
			case 4:
				System.out.println("Nhap vao ma code cua nhan su:");
				String key = sc.nextLine();
				System.out.println(userService.searchHuman(key));
				System.out.println("\n>> Nhan Enter de quay lai menu...");
				sc.nextLine();
				break;
			case 5:
				userService.addHR(sc);
				System.out.println("\n>> Nhan Enter de quay lai menu...");
				sc.nextLine();
				break;
			case 6:
				userService.addHR(sc);
				System.out.println("\n>> Nhan Enter de quay lai menu...");
				sc.nextLine();
				break;
			case 7:
				System.out.println(userService.printStudent());
				System.out.println("Nhập mã sinh viên");
				String student_code = sc.nextLine();
				System.out.println(subjectService.printAllSubject());
				System.out.println("Nhập mã môn học");
				String subject_code = sc.nextLine();
				userService.registerSubject(student_code, subject_code);
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
