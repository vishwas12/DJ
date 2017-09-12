package com.dj.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.dj.application.dao.support.JdbcDaoSupport;
import com.dj.application.exception.CustomGenericException;
import com.dj.dao.UserDao;
import com.dj.dto.AuthUser;
import com.dj.dto.MusicType;
import com.dj.dto.User;

@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao{

	@Override
	public void insertUserLoginDetails(User user) {
		StringBuffer query = new StringBuffer();
		query.append("insert into user_login_hist(user_id,user_device_id,");
		query.append("access_token,status,created_on,modified_on,created_by,modified_by)");
		query.append(" values(?,?,?,?,?,?,?,?)");
		
		Object[] params = new Object[] { 
				user.getUserId(),
				user.getDeviceId(),
				user.getAccessToken(),
				1,
				new Date(),
				new Date(),
				user.getUserId(),
				user.getUserId()
		};

		try {
			getJdbcTemplate().update(query.toString(), params);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getUserByAccessToken(String accessToken) {
		StringBuffer query = new StringBuffer();
		query.append("select * ");
		query.append("from user_login_hist loginHist ");
		query.append("where  loginHist.access_token='").append(accessToken).append("' and loginHist.status=1");
		
		User user = null;
		try {
			user = getJdbcTemplate().queryForObject(query.toString(), new BeanPropertyRowMapper<User>(User.class));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if(user==null){
			throw new CustomGenericException("Invalid or Missing Token", HttpStatus.BAD_REQUEST);
		}
		return user;
	}
	
	@Override
	public boolean logout(String accessToken) {
		StringBuffer query = new StringBuffer();
		boolean flag = true;
		query.append("update user_login_hist set status=? where access_token=? AND status=1");
		
		if(!(getJdbcTemplate().update(query.toString(), "2",accessToken)==1)){
			throw new CustomGenericException("User not logged In", HttpStatus.BAD_REQUEST);
		}
		return flag;
	}

	@Override
	public void registerUser(User user) {
		StringBuffer query = new StringBuffer();
		query.append("insert into user(email,password,mobile_number,status,created_on) ");
		query.append("values(?,?,?,?,?)");
		
		Object[] params = {
				user.getEmail(),
				user.getPassword(),
				user.getMobileNumber(),
				1,
				new Date()
		};
		
		try {
			getJdbcTemplate().update(query.toString(),params);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void registerAsUpdatedUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean emailValidate(String email) {
		boolean flag= false;
		String query = "select if(COUNT('*')>0,true,false) as count from user where email=?";
		try {
			flag = getJdbcTemplate().queryForObject(query,Boolean.class,email);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<MusicType> getAllMusicTypes() {
		String query = "select * from music_type";
		List<MusicType> list = new ArrayList<MusicType>();
		try {
			list = getJdbcTemplate().query(query, new BeanPropertyRowMapper<MusicType>(MusicType.class));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<User> getMusicians(List<MusicType> musicTypes) {
		StringBuffer query = new StringBuffer();
		List<User> list = new ArrayList<User>();
		query.append("select user.* from User user, user_music_category umc , music_type mc");
		query.append(" where user.user_id = umc.user_id and umc.music_type = mc.music_type ");
		query.append("and mc.music_type in(");
		if(musicTypes.size()>0){
			for(int i=0;i<musicTypes.size();i++){
				if(i!=musicTypes.size()-1){
					query.append("?,");
				}
				else{
					query.append("?");
				}
			}
		}
		query.append(")");
		Object[] params = new Object[musicTypes.size()];
		for(int i=0;i<musicTypes.size();i++){
			params[i]=musicTypes.get(i);
		}
		list = getJdbcTemplate().query(query.toString(), new BeanPropertyRowMapper<User>(User.class),params);
		return list;
	}

	@Override
	public AuthUser getUserByUsername(String username) {
		// TODO Auto-generated method stub
		AuthUser user = null;
		try{
			String query = "select * from AUTH_USER where email=?";
			user = getJdbcTemplate().queryForObject(query.toString(), new BeanPropertyRowMapper<AuthUser>(AuthUser.class),username);
		}catch (DataAccessException e) {
			e.printStackTrace();
		}
		if(user==null){
			throw new CustomGenericException("Invalid Login Credentials", HttpStatus.UNAUTHORIZED);
		}
		return user;
	}

}
