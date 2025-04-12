package entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Student extends Human {
	private String class_;
	private List<String> subjectList = new LinkedList<String>();
	
	public Student(){
		
	}
	
	public Student(String code){
		super(code);
	}
	
	public Student(String code, String fullname){
		super(code, fullname);
	}
	
	public Student(String code, String fullname, String class_){
		super(code, fullname);
		this.class_ = class_;
	}

	public Student(String code, String fullname, String class_, String address){
		super(code, fullname, address);
		this.class_ = class_;
	}

	public void AddSubject(Subject sub) {
		
	}
	
	public float calTermAverageMark() {
		return 0;
	}
	
	public void enterInfo(Scanner sc) {
		super.enterInfo(sc);
	}

	public String getClass_() {
		return class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	@Override
	public String toString() {
		return "Student [class_=" + class_ + ", subjectList=" + subjectList + ", address=" + address + ", code=" + code
				+ ", fullname=" + fullname + "]";
	}
	
	
}
