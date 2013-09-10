package com.skula.link.models;

import com.skula.link.enums.EventType;

public class Event {
	private EventType type;
	private String linkerLogin;
	private boolean ask;
	private String status;
	private String date;
	private String label;

	public Event() {
	}

	public Event(EventType type, String linkerLogin, boolean ask,
			String status, String date, String label) {
		super();
		this.type = type;
		this.linkerLogin = linkerLogin;
		this.ask = ask;
		this.status = status;
		this.date = date;
		this.label = label;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public String getLinkerLogin() {
		return linkerLogin;
	}

	public void setLinkerLogin(String linkerLogin) {
		this.linkerLogin = linkerLogin;
	}

	public boolean isAsk() {
		return ask;
	}

	public void setAsk(boolean ask) {
		this.ask = ask;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}