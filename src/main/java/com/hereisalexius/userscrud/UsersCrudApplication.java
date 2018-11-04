package com.hereisalexius.userscrud;

import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import com.googlecode.wicket.kendo.ui.resource.KendoAllResourceReference;
import com.googlecode.wicket.kendo.ui.settings.KendoUILibrarySettings;
import com.hereisalexius.userscrud.web.HomePage;
import com.hereisalexius.userscrud.web.SignInPage;
import com.hereisalexius.userscrud.web.auth.BasicAuthenticationSession;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.util.crypt.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
@EnableAutoConfiguration
@ComponentScan
public class UsersCrudApplication extends AuthenticatedWebApplication implements CommandLineRunner{

	@Autowired
	private ApplicationContext applicationContext;
	
    @Autowired
    JdbcTemplate jdbcTemplate;

	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}
	
	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass(){
		return BasicAuthenticationSession.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return SignInPage.class;
	}

	@Override
	public void init() {
		super.init();
		getResourceSettings().setThrowExceptionOnMissingResource(false);

		KendoUILibrarySettings settings = KendoUILibrarySettings.get();
		settings.setJavaScriptReference(KendoAllResourceReference.get());
		
		getRequestCycleSettings().setResponseRequestEncoding(CharEncoding.UTF_8);
		getMarkupSettings().setDefaultMarkupEncoding(CharEncoding.UTF_8);
		getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext));
		mountPage("/home", HomePage.class);
		mountPage("/login", SignInPage.class);
	}

	public static void main(String[] args) {
		 SpringApplication.run(UsersCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		jdbcTemplate.execute("DROP TABLE IF EXISTS USERS");
        jdbcTemplate.execute("CREATE TABLE USERS(\n" +  
        		"   ID SERIAL PRIMARY KEY,\n" + 
        		"   USERNAME VARCHAR(20) NOT NULL UNIQUE,\n" + 
        		"   PASSWORD VARCHAR(20) NOT NULL,\n" + 
        		"   FULLNAME VARCHAR(100) NOT NULL\n" + 
        		");");
        jdbcTemplate.execute("INSERT INTO USERS ("
        		+ "USERNAME,PASSWORD,FULLNAME) "
        		+ "VALUES ('admin','admin','Tommy Wiseau');");
		
	}
}
