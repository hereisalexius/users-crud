package com.hereisalexius.userscrud.web.auth;

import org.apache.wicket.Component;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.request.component.IRequestableComponent;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hereisalexius.userscrud.services.LoginService;

@Service
public class BasicAuthorizationStrategy implements IAuthorizationStrategy{

	@Autowired
	private LoginService loginService;
	
	@Override
	public <T extends IRequestableComponent> boolean isInstantiationAuthorized(Class<T> componentClass) {
		return loginService.isLoggedIn();
	}

	@Override
	public boolean isActionAuthorized(Component component, Action action) {
		return loginService.isLoggedIn();
	}

	@Override
	public boolean isResourceAuthorized(IResource resource, PageParameters parameters) {
		return loginService.isLoggedIn();
	}

}
