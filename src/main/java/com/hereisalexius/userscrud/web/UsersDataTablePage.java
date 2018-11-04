package com.hereisalexius.userscrud.web;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
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
import com.googlecode.wicket.kendo.ui.datatable.column.CurrencyPropertyColumn;
import com.googlecode.wicket.kendo.ui.datatable.column.DatePropertyColumn;
import com.googlecode.wicket.kendo.ui.datatable.column.IColumn;
import com.googlecode.wicket.kendo.ui.datatable.column.IdPropertyColumn;
import com.googlecode.wicket.kendo.ui.datatable.column.PropertyColumn;
import com.googlecode.wicket.kendo.ui.panel.KendoFeedbackPanel;
import com.hereisalexius.userscrud.dao.UserDAO;
import com.hereisalexius.userscrud.model.User;
import com.hereisalexius.userscrud.web.components.UserDataProvider;

public class UsersDataTablePage extends WebPage {

	@SpringBean
	private UserDAO userDao;

	public UsersDataTablePage() {
		// FeedbackPanel //
		//final KendoFeedbackPanel feedback = new KendoFeedbackPanel("feedback");
		//this.add(feedback);

		// DataTable //
		Options options = new Options();
		options.set("height", 430);
		options.set("editable", Options.asString("inline"));
		options.set("pageable", true);
		options.set("toolbar", "[ { name: 'create', text: 'New' } ]"); /*
																		 * 'toolbar' option can be used as long as
																		 * #getToolbarButtons returns no button
																		 */

		final DataTable<User> table = new DataTable<User>("datatable", newColumnList(), newDataProvider(userDao.list()),
				25, options) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onCancel(AjaxRequestTarget target) {
				this.info("Cancelled...");
				//target.add(feedback);
			}

			@Override
			public void onCreate(AjaxRequestTarget target, JSONObject object) {
				User user = User.of(object);
				userDao.create(user);

				this.warn("Inserted #" + user.getId());
				//target.add(feedback);
			}

			@Override
			public void onUpdate(AjaxRequestTarget target, JSONObject object) {
				User user = User.of(object);
				userDao.update(user);

				this.warn("Updated #" + user.getId());
				//target.add(feedback);
			}

			@Override
			public void onDelete(AjaxRequestTarget target, JSONObject object) {
				User user = User.of(object);
				userDao.delete(user);

				this.warn("Deleted #" + user.getId());
				//target.add(feedback);
			}
		};

		this.add(table);
	}

	private static IDataProvider<User> newDataProvider(List<User> list) {
		return new UserDataProvider(list);
	}

	private static List<IColumn> newColumnList() {
		List<IColumn> columns = Generics.newArrayList();

		columns.add(new IdPropertyColumn("ID", "id", 40)); /* Important, for being sent back to server */
		columns.add(new PropertyColumn("username", "name"));
		columns.add(new PropertyColumn("password", "password"));
		columns.add(new PropertyColumn("fullname", "description"));
		columns.add(new CommandColumn(170) {

			private static final long serialVersionUID = 1L;

			@Override
			public List<CommandButton> newButtons() {
				/*
				 * 'edit' and 'destroy' are built-in buttons/commands, no property has to be to
				 * supply #onUpdate(AjaxRequestTarget target, JSONObject object) will be
				 * triggered #onDelete(AjaxRequestTarget target, JSONObject object) will be
				 * triggered #onClick(AjaxRequestTarget, CommandButton, String) will not be
				 * triggered
				 */
				return Arrays.asList(new CommandButton("edit", Model.of("Edit")),
						new CommandButton("destroy", Model.of("Delete")));
			}
		});

		return columns;
	}
}
