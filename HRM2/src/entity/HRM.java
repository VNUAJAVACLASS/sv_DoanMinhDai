package entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HRM {
	private Map<String,Human> hrList = new HashMap<String,Human>();
	private Map<String,Subject> subList = new HashMap<String,Subject>();

	public HRM() {
		
	}
	
	public void addHm(Human hm) {
		this.hrList.put(hm.getCode(), hm);
	}
	
	public void addSub(Subject sub) {
		this.subList.put(sub.getSubjectCode(), sub);
	}
	
	public void addHR(Scanner sc) {
        System.out.print("Nhap loai nhan su (1 - Lecturer, 2 - Student): ");
        int type = sc.nextInt();
        sc.nextLine();
        
        Human hm;
        if (type == 1) {
            hm = new Lecturer();
            hm.enterInfo(sc);
        } else if (type == 2) {
            hm = new Student();
            hm.enterInfo(sc);
        } else {
            System.out.println("Loai nhan su khong hop le!");
            return;
        }
        this.addHm(hm);
	}


	public void printHRList() {
		System.out.println("Danh sach nhan su la:");
	    for (Human human : hrList.values()) {
	        System.out.println(human);
	    }
	}
	
	public void printLecturer() {
		System.out.println("Danh sach giang vien la:");
	    for (Human human : hrList.values()) {
	    	if (human instanceof Lecturer) {
	            System.out.println(human);			
	        }
		}
	}
	
	public void printStudent() {
		System.out.println("Danh sach sinh vien la:");
	    for (Human human : hrList.values()) {
	    	if (human instanceof Student) {
	            System.out.println(human);			
	        }
		}
	}
	
	public Human searchHuman(String code) {
		for (Human human : hrList.values()) {
			if (human.getCode().toLowerCase().equals(code)) {
				return human;
			}
		}
		return null;
	}
	
	public Subject searchSubject(String code) {
		Subject sub = subList.get(code);
		return sub;
	}
	
	public void printAllSubject() {
		for (ICreditSubject sub : subList.values()) {
			System.out.println("(" + sub.getSubjectCode() +")" + sub.getSubjectName() + " | " + sub.getCredit());
		}
	}
	
	public void registerSubject(Scanner sc) {
		System.out.println("Chon sinh vien: ");
		printStudent();
	    System.out.print("Nhap ma sinh vien muon dang ky: ");
	    String studentCode = sc.nextLine();
	    
		System.out.println("Chon mon hoc muon dang ky: ");
		printAllSubject();
		
	    System.out.print("Nhap ma mon hoc muon dang ky: ");
	    String subjectCode = sc.nextLine();
	    
	    Student student = (Student) searchHuman(studentCode);
	    student.addSubject(searchSubject(subjectCode));
	}
	
	
	public void initDemoData() {
		addHm(new Lecturer("MTI01", "Nguyen Van A", "Ha Noi"));
        addHm(new Lecturer("MTI02", "Tran Thi B", "Hai Phong"));
        
        addHm(new Student("671259", "Doan Minh Dai", "K67CNTTA" , "Ninh Binh"));   
        addHm(new Student("671101", "Pham Thanh D", "Ha Nam"));
        
		Subject javaSub =  new JavaSubject("TH01","Lap trinh java",3);
		Subject pythonSub = new PythonSubject("TH02", "Lap trinh Python", 2); 

		addSub(pythonSub);
		addSub(javaSub);
    }
}