package com.skula.link.models;

public class Fact  {
	private String id;
	private String label;
	private String date;

	public Fact() {
	}

	public Fact(String id, String label, String date) {
		this.id = id;
		this.label = label;
		this.date = date;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}
}
