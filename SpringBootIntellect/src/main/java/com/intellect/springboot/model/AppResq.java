package com.intellect.springboot.model;


public class AppResq {
	
	
	public AppResq(){
		
	}
	
	private String id;
	private String fName;
	private String lName;
	private String email;
	private int pinCode;
	private String birthDate;
	private boolean isActive;
	
	
	
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPinCode() {
		return pinCode;
	}
	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public AppResq(String id, String fName, String lName, String email, int pinCode, String birthDate) {
		super();
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.pinCode = pinCode;
		this.birthDate = birthDate;
	}
	@Override
	public String toString() {
		return "AppResq [id=" + id + ", fName=" + fName + ", lName=" + lName + ", email=" + email + ", pinCode="
				+ pinCode + ", birthDate=" + birthDate + "]";
	}
}
