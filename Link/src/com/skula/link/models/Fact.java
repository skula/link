package com.skula.link.models;

public class Fact  {
	private String linkerLogin;
	private String status;
	private String date;
	private String label;

	public Fact() {
	}

	public Fact(String linkerLogin, String label, String date,  String status) {
		this.linkerLogin = linkerLogin;
		this.status = status;
		this.date = date;
		this.label = label;
	}

	public void setLinkerLogin(String linkerLogin) {
		this.linkerLogin = linkerLogin;
	}

	public String getLinkerLogin() {
		return linkerLogin;
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
