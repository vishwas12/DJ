package com.dj.service;

import java.util.List;

import com.dj.dto.BookingHistory;
import com.dj.dto.User;

public interface BookingService {
	public void bookBand(User user);
	public boolean cancelBooking(int bookingId);
	public List<BookingHistory> viewBooking(User user);
}
