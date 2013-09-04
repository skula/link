package com.skula.link.models;

public class Member  {
	private String id;
	private String login;
	private String passwd;
	private String city;
	private String email;
	private String status;
	private String description;
	private String gender;
	private String points;
	private String birth;

	public Member() {
	}

	public Member(String id, String login, String passwd, String city, String email, String status, String description, String gender, String points, String birth) {
		this.id = id;
		this.login = login;
		this.passwd = passwd;
		this.city = city;
		this.email = email;
		this.status = status;
		this.description = description;
		this.gender = gender;
		this.points = points;
		this.birth = birth;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getPoints() {
		return points;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getBirth() {
		return birth;
	}
}
