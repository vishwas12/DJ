package com.dj.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.dj.application.dao.support.JdbcDaoSupport;
import com.dj.dao.ApplicationDao;
import com.dj.dto.UserType;

@Repository
public class ApplicationDaoImpl extends JdbcDaoSupport implements ApplicationDao{

	@Override
	public UserType getUserType(String userTpe) {
		String query = "select * from user_type where user_type=?";
		UserType userType = new UserType();
		try {
			userType = getJdbcTemplate().queryForObject(query, new BeanPropertyRowMapper<UserType>(UserType.class),userTpe);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return userType;
	}

}
