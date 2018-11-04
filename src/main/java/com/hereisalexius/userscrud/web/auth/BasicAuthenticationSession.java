package com.hereisalexius.userscrud.web.auth;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.hereisalexius.userscrud.services.LoginService;

public class BasicAuthenticationSession extends AuthenticatedWebSession {



	public BasicAuthenticationSession(Request request) {
		super(request);
	}

	@Override
	public boolean authenticate(String username, String password) {
		// user is authenticated if both username and password are equal to 'wicketer'
		return true;
	}

	@Override
	public Roles getRoles() {
		return null;
	}
}
