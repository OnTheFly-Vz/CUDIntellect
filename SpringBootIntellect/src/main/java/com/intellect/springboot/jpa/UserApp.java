package com.intellect.springboot.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class UserApp {
	
	
	@Override
	public String toString() {
		return "UserApp [id=" + id + ", fName=" + fName + ", eMailid=" + eMailid + ", lName=" + lName + ", pinCode="
				+ pinCode + ", birthDate=" + birthDate + ", isActive=" + isActive + "]";
	}

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	@Column(name="fname")
	private String fName;
	
	@Column(name="emailid")
	private String eMailid;
	
	private String lName;
	
	private int pinCode;
	
	private String birthDate;
	
	private boolean isActive;

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String geteMailid() {
		return eMailid;
	}

	public void seteMailid(String eMailid) {
		this.eMailid = eMailid;
	}
	
	
	

}
