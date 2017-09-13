package com.dj.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.dj.application.dao.support.JdbcDaoSupport;
import com.dj.application.exception.CustomGenericException;
import com.dj.dao.LoginDao;
import com.dj.dto.Login;
import com.dj.dto.User;

@Repository
public class LoginDaoImpl extends JdbcDaoSupport implements LoginDao {

	@Override
	public User validateLogin(Login login) {
		User user = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("select * from User where email=? and password=?");
			user = getJdbcTemplate().queryForObject(query.toString(), new BeanPropertyRowMapper<User>(User.class),login.getEmail(),login.getPassword());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if(user==null){
			throw new CustomGenericException("Invalid Login Credentials", HttpStatus.UNAUTHORIZED);
		}
		return user;
	}

	@Override
	public boolean invalidateLogin(Long userId) {
		String query = "update user_login_hist set status=2 where user_id=? and user_device_id='9999'";
		boolean flag = false;
		if(getJdbcTemplate().update(query,userId)==1){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean userAlreadyLoggedIn(Long userId) {
		StringBuffer query = new StringBuffer();
		boolean flag = false;
		query.append("select if(COUNT('*')>0,true,false) as count FROM user_login_hist userLoginHist");
		query.append(" where userLoginHist.user_id=? AND userLoginHist.status='1' and user_device_id!='9999'");
		
		try {
			flag = getJdbcTemplate().queryForObject(query.toString(),Boolean.class,userId);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if(flag){
			throw new CustomGenericException("Login Conflict", HttpStatus.CONFLICT);
		}
		return flag;
	}

	@Override
	public boolean validateToken(String accessToken) {
		StringBuffer query = new StringBuffer();
		boolean flag = false;
		query.append("select if(COUNT('*')>0,true,false) as count FROM user_login_hist userLoginHist");
		query.append(" where userLoginHist.access_token='").append(accessToken).append("' AND userLoginHist.status='2'");
		
		try {
			flag = getJdbcTemplate().queryForObject(query.toString(),Boolean.class);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
