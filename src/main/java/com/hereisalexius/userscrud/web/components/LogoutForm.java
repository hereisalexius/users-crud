package com.hereisalexius.userscrud.web.components;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.model.CompoundPropertyModel;

public class LogoutForm extends StatelessForm {

	private final Class<? extends WebPage> responsePageClass;

	public LogoutForm(String id, Class<? extends WebPage> responsePageClass) {
		super(id);
		this.responsePageClass = responsePageClass;
		setDefaultModel(new CompoundPropertyModel(this));
	}

	@Override
	protected void onSubmit() {
		AuthenticatedWebSession.get().signOut();
		setResponsePage(responsePageClass);
	}

}
