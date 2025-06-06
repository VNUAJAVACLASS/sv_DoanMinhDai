package entity;

import java.util.Scanner;

public abstract class Human {
	protected String fullname;
	protected String address;
	protected String code;
	
	public Human(){
	};
	
	public Human(String code){
		this.code = code;
	}

	public Human(String code, String fullname){
		this(code);
		this.fullname = fullname;
	}
	
	public Human(String code, String fullname, String address){
		this(code,fullname);
		this.address = address;
	}
	
	public abstract void enterInfo(Scanner sc);

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
}
