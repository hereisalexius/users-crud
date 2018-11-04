package com.hereisalexius.userscrud.web;

import java.util.Arrays;
import java.util.List;
import org.apache.wicket.Application;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.lang.Generics;
import com.github.openjson.JSONObject;
import com.googlecode.wicket.jquery.core.Options;
import com.googlecode.wicket.kendo.ui.datatable.DataTable;
import com.googlecode.wicket.kendo.ui.datatable.button.CommandButton;
import com.googlecode.wicket.kendo.ui.datatable.column.CommandColumn;
import com.googlecode.wicket.kendo.ui.datatable.column.IColumn;
import com.googlecode.wicket.kendo.ui.datatable.column.IdPropertyColumn;
import com.googlecode.wicket.kendo.ui.datatable.column.PropertyColumn;
import com.googlecode.wicket.kendo.ui.panel.KendoFeedbackPanel;
import com.hereisalexius.userscrud.dao.UserDAO;
import com.hereisalexius.userscrud.model.User;
import com.hereisalexius.userscrud.services.StoreSearchDataService;
import com.hereisalexius.userscrud.web.components.FullnameSearchFilterForm;
import com.hereisalexius.userscrud.web.components.LogoutForm;
import com.hereisalexius.userscrud.web.components.UserDataProvider;

public class HomePage extends WebPage {

	@SpringBean
	private UserDAO userDao;

	@SpringBean
	private StoreSearchDataService storeSearchDataService;

	public HomePage() {
		// FeedbackPanel //
		final KendoFeedbackPanel feedback = new KendoFeedbackPanel("feedback");
		this.add(feedback);

		// DataTable //
		Options options = new Options();
		options.set("height", 430);
		options.set("editable", Options.asString("inline"));
		options.set("pageable", true);
		options.set("toolbar", "[ { name: 'create', text: 'New' } ]");
		// options.set("filterable", "{ mode: 'menu, row' }");

		final DataTable<User> table = new DataTable<User>("datatable", newColumnList(), newDataProvider(makeUserList()),
				25, options) {

			private void refreshData() {

				((UserDataProvider) getDataProvider()).repopulateDataWith(makeUserList());
			}

			@Override
			public void onCancel(AjaxRequestTarget target) {
				this.info("Cancelled...");
				target.add(feedback);
			}

			@Override
			public void onCreate(AjaxRequestTarget target, JSONObject object) {
				User user = User.of(object);
				userDao.create(user);
				refreshData();
				this.warn("Inserted #" + user.getId());
				target.add(feedback);
			}

			@Override
			public void onUpdate(AjaxRequestTarget target, JSONObject object) {
				User user = User.of(object);
				userDao.update(user);
				refreshData();
				this.warn("Updated #" + user.getId());
				target.add(feedback);
			}

			@Override
			public void onDelete(AjaxRequestTarget target, JSONObject object) {
				User user = User.of(object);
				userDao.delete(user);
				refreshData();
				this.warn("Deleted #" + user.getId());
				target.add(feedback);
			}
		};

		add(new LogoutForm("logoutForm", this.getClass()));
		add(new FullnameSearchFilterForm("search", this.getClass()));
		add(table);
	}

	@Override
	protected void onConfigure() {
		super.onConfigure();
		AuthenticatedWebApplication app = (AuthenticatedWebApplication) Application.get();
		// if user is not signed in, redirect him to sign in page
		if (!AuthenticatedWebSession.get().isSignedIn())
			app.restartResponseAtSignInPage();
	}

	private static IDataProvider<User> newDataProvider(List<User> list) {
		return new UserDataProvider(list);
	}

	private static List<IColumn> newColumnList() {
		List<IColumn> columns = Generics.newArrayList();

		columns.add(new IdPropertyColumn("id", "id", 40)); /* Important, for being sent back to server */
		columns.add(new PropertyColumn("username", "username"));
		columns.add(new PropertyColumn("password", "password"));
		columns.add(new PropertyColumn("fullName", "fullName"));
		columns.add(new CommandColumn(170) {

			@Override
			public List<CommandButton> newButtons() {
				return Arrays.asList(new CommandButton("edit", Model.of("Edit")),
						new CommandButton("destroy", Model.of("Delete")));
			}
		});

		return columns;
	}

	private List<User> makeUserList() {
		String lookup = storeSearchDataService.getData();
		storeSearchDataService.setData("");
		if (lookup != null && !lookup.isEmpty()) {
			return userDao.streamFindAllInFullname(lookup);
		}
		return userDao.list();

	}

}
