package com.jolley.models;

public class User {
	
	private int id;
	private String fName;
	private String lName;
	private String email;
	private String pass;
	private String position;
	private String sAddress;
	private String city;
	private String state;
	private int zip;
	
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
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getAddress() {
		return sAddress;
	}
	public void setAddress(String sAddress) {
		this.sAddress = sAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	public User(int id, String fName, String lName, String email, String pass, String position, String sAddress, String town,
			String state, int zip) {
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.pass = pass;
		this.position = position;
		this.sAddress = sAddress;
		this.city = town;
		this.state = state;
		this.zip = zip;
	}
	public User(String fName, String lName, String email, String pass, String position, String sAddress, String town,
			String state, int zip) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.pass = pass;
		this.position = position;
		this.sAddress = sAddress;
		this.city = town;
		this.state = state;
		this.zip = zip;
	}
}
