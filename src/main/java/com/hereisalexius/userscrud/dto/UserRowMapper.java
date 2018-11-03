package com.hereisalexius.userscrud.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.hereisalexius.userscrud.model.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();

		user.setId(rs.getLong("ID"));
		user.setUsername(rs.getString("USERNAME"));
		user.setPassword(rs.getString("PASSWORD"));
		user.setFullName(rs.getString("FULLNAME"));

		return user;
	}

}
