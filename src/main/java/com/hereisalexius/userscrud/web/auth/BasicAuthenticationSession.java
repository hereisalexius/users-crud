package com.hereisalexius.userscrud.web.auth;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

public class BasicAuthenticationSession extends AuthenticatedWebSession {



	public BasicAuthenticationSession(Request request) {
		super(request);
	}

	@Override
	public boolean authenticate(String username, String password) {
		return true;
	}

	@Override
	public Roles getRoles() {
		return null;
	}
}
