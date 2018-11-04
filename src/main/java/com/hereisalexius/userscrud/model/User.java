package com.hereisalexius.userscrud.model;

import java.text.ParseException;

import org.apache.wicket.util.io.IClusterable;

import com.github.openjson.JSONObject;
import com.googlecode.wicket.jquery.core.utils.DateUtils;

public class User implements java.io.Serializable, IClusterable {

	private Long id = -1L;
	private String username;
	private String password;
	private String fullName;

	public User() {
	}

	public User(String username, String password, String fullName) {
		this.username = username;
		this.password = password;
		this.fullName = fullName;
	}

	public User(Long id, String username, String password, String fullName) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public static User of(JSONObject object) {
		User user = new User(object.optLong("id"), object.optString("username"), object.optString("password"),
				object.optString("fullName"));
		return user;
	}
}
