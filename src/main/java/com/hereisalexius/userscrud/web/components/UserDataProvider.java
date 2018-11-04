package com.hereisalexius.userscrud.web.components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.core.util.lang.PropertyResolver;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.util.SingleSortState;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.hereisalexius.userscrud.dao.UserDAO;
import com.hereisalexius.userscrud.model.User;

public class UserDataProvider extends ListDataProvider<User> implements ISortStateLocator<String> {
	private static final long serialVersionUID = 1L;

	private List<User> users;

	private final SingleSortState<String> state = new SingleSortState<String>();

	public UserDataProvider(List<User> list) {
		users = list;
	}

	@Override
	protected List<User> getData() {
		List<User> list = new ArrayList<>(users);
		
		SortParam<String> param = this.state.getSort();

		if (param != null) {
			Collections.sort(list, new UserComparator(param.getProperty(), param.isAscending()));
		}

		users = list;
		return users;
	}
	
	public void repopulateDataWith(List<User> list) {
		users.clear();
		users.addAll(list);
	}

	@Override
	public ISortState<String> getSortState() {
		return this.state;
	}

	static class UserComparator implements Comparator<User>, Serializable {
		private static final long serialVersionUID = 1L;

		private final String property;
		private final boolean ascending;

		public UserComparator(String property, boolean ascending) {
			this.property = property;
			this.ascending = ascending;
		}

		@Override
		public int compare(User p1, User p2) {
			Object o1 = PropertyResolver.getValue(this.property, p1);
			Object o2 = PropertyResolver.getValue(this.property, p2);

			if (o1 != null && o2 != null) {
				Comparable<Object> c1 = toComparable(o1);
				Comparable<Object> c2 = toComparable(o2);

				return c1.compareTo(c2) * (this.ascending ? 1 : -1);
			}

			return 0;
		}

		@SuppressWarnings("unchecked")
		private static Comparable<Object> toComparable(Object o) {
			if (o instanceof Comparable<?>) {
				return (Comparable<Object>) o;
			}

			throw new WicketRuntimeException("Object should be a Comparable");
		}
	}
}