package com.dj.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.dj.application.dao.support.JdbcDaoSupport;
import com.dj.dao.BookingDao;
import com.dj.dto.BookingHistory;
import com.dj.dto.User;

@Repository
public class BookingDaoImpl extends JdbcDaoSupport implements BookingDao {

	@Override
	public void bookBand(User user) {
		StringBuffer query = new StringBuffer();
		query.append("insert into booking_history(booked_user_id,user_type,status,booking_start_date,booking_end_date,user_id)");
		query.append(" values(?,?,?,?,?,?)");
		Object[] params = {
				user.getBookingHistory().get(0).getBookedUserId(),
				user.getUserType().getUserTypeId(),
				1,
				user.getBookingHistory().get(0).getBookingStartDate(),
				user.getBookingHistory().get(0).getBookingEndDate(),
				user.getUserId()
		};
		try {
			getJdbcTemplate().update(query.toString(), params);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean cancelBooking(int bookingId) {
		String query = "update booking_history set status=2 where booking_history_id=?";
		boolean flag = false;
		if(getJdbcTemplate().update(query,bookingId)==1){
			flag=true;
		}
		return flag;
	}

	@Override
	public List<BookingHistory> viewBooking(User user) {
		List<BookingHistory> list = new ArrayList<BookingHistory>();
		String query = "select * from booking_history where user_id=?";
		try {
			list = getJdbcTemplate().query(query, new BeanPropertyRowMapper<BookingHistory>(BookingHistory.class),user.getUserId());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
