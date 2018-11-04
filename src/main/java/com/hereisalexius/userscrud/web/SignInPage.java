package com.hereisalexius.userscrud.web;

import org.apache.logging.log4j.util.Strings;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.hereisalexius.userscrud.services.LoginService;
import com.hereisalexius.userscrud.web.components.LoginForm;

public class SignInPage extends WebPage {
   
	public SignInPage() {
		
		add(new LoginForm("loginForm", "username", "password", HomePage.class));
		
	}
    
	/*
    @SpringBean
    private LoginService loginService; 
    
    private String username;
    private String password;
    @Override
    protected void onInitialize() {
        super.onInitialize();
        StatelessForm form = new StatelessForm("loginForm") {
            @Override
            protected void onSubmit() {
                if (Strings.isEmpty(username))
                    return;
                boolean authResult = AuthenticatedWebSession.get().signIn(username, password);
                // if authentication succeeds redirect user to the requested page
                if (authResult && loginService.login(username, password)){
                    setResponsePage(HomePage.class);
                }else{
                    return;
                }
            }
        };
        form.setDefaultModel(new CompoundPropertyModel(this));
        form.add(new TextField("username"));
        form.add(new PasswordTextField("password"));
        add(form);
    }
    */
}
