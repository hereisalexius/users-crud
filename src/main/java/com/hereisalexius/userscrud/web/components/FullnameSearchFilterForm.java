package com.hereisalexius.userscrud.web.components;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.hereisalexius.userscrud.services.StoreSearchDataService;

public class FullnameSearchFilterForm extends StatelessForm {

	@SpringBean
	private StoreSearchDataService storeSearchDataService;
	
	private String fullname;
	private final Class<? extends WebPage> responsePageClass;

	public FullnameSearchFilterForm(String id, Class<? extends WebPage> responsePageClass) {
		super(id);
		this.responsePageClass = responsePageClass;
		setDefaultModel(new CompoundPropertyModel(this));
		add(new TextField("fullname"));
	}

	@Override
	protected void onSubmit() {
		storeSearchDataService.setData(fullname);
		setResponsePage(responsePageClass);
	}
	
}
