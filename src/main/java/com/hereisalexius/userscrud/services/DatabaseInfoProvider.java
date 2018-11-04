package com.hereisalexius.userscrud.services;

public class DatabaseInfoProvider {
	
	private static DatabaseInfoProvider instance = new DatabaseInfoProvider();
	
	public static DatabaseInfoProvider inst() {
		return instance;
	}
	
	private DatabaseInfoProvider() {}
	
	private String url;
	private String username;
	private String password;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	
	
	

}
