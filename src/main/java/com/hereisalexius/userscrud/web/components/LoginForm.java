package com.hereisalexius.userscrud.web.components;

import org.apache.logging.log4j.util.Strings;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.hereisalexius.userscrud.services.LoginService;

public class LoginForm extends StatelessForm {

	@SpringBean
	private LoginService loginService;

	private final Class<? extends WebPage> responsePageClass;

	private String username;
	private String password;

	public LoginForm(String id, String usernameId, String passwordId, Class<? extends WebPage> responsePageClass) {
		super(id);
		this.responsePageClass = responsePageClass;
		setDefaultModel(new CompoundPropertyModel(this));
		add(new TextField(usernameId));
		add(new PasswordTextField(passwordId));

	}

	@Override
	protected void onSubmit() {
		if (Strings.isEmpty(username))
			return;
		boolean authResult = AuthenticatedWebSession.get().signIn(username, password);
		// if authentication succeeds redirect user to the requested page
		if (authResult && loginService.login(username, password)) {
			setResponsePage(responsePageClass);
		} else {
			return;
		}
	}

}
