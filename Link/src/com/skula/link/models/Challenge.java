package com.skula.link.models;

public class Challenge  {
	private String id;
	private String askerLogin;
	private String makerLogin;
	private String status;
	private String date;
	private String label;

	public Challenge() {
	}

	public Challenge(String id, String askerLogin, String makerLogin, String status, String date, String label) {
		this.id = id;
		this.askerLogin = askerLogin;
		this.makerLogin = makerLogin;
		this.status = status;
		this.date = date;
		this.label = label;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setAskerLogin(String askerLogin) {
		this.askerLogin = askerLogin;
	}

	public String getAskerLogin() {
		return askerLogin;
	}

	public void setMakerLogin(String makerLogin) {
		this.makerLogin = makerLogin;
	}

	public String getMakerLogin() {
		return makerLogin;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
