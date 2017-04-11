package com.dj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dj.dao.BookingDao;
import com.dj.dto.BookingHistory;
import com.dj.dto.User;
import com.dj.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	BookingDao bookingDao;

	@Override
	public void bookBand(User user) {
		bookingDao.bookBand(user);
	}

	@Override
	public boolean cancelBooking(int bookingId) {
		return bookingDao.cancelBooking(bookingId);
	}

	@Override
	public List<BookingHistory> viewBooking(User user) {
		return bookingDao.viewBooking(user);
	}

}
