package com.skula.link.models;

public class Request  {
	private String id;
	private String memberId;
	private String type;
	private String label;

	public Request() {
	}

	public Request(String id, String memberId, String type, String label) {
		this.id = id;
		this.memberId = memberId;
		this.type = type;
		this.label = label;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
