package com.millesBornes.web.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.millesBornes.web.jsonview.Views;

public class User {

	@JsonView(Views.Public.class)
	String username;

	public User() {
	}

	public User(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User [username=" + username + "]";
	}

}
