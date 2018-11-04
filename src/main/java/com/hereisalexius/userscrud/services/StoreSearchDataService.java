package com.hereisalexius.userscrud.services;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope(value = "singleton")
@Service
public class StoreSearchDataService {
	
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
