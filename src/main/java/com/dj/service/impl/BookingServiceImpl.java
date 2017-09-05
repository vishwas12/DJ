package com.dj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dj.dao.BookingDao;
import com.dj.dto.BookingHistory;
import com.dj.dto.SearchResults;
import com.dj.dto.User;
import com.dj.service.BookingService;
import com.dj.transformers.SearchResultsTransformer;

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
	/*@Override
    public Map<String, SearchResults> viewBandDetails(String location, String category) {
        List<User> users = new ArrayList<User>();
        users = bookingDao.viewBandDetails(location, category);
        Map<Integer, User> userMap = new HashMap<>();
        for (User user : users) {
            if (MapUtils.isEmpty(userMap)) {
                userMap.put(user.getUserId(), user);
            }
            else {
                if (userMap.get(user.getUserId()) == null) {
                    userMap.put(user.getUserId(), user);
                }
                else {
                    List<BookingHistory> bh = userMap.get(user.getUserId()).getBookingHistory();
                    bh.addAll(user.getBookingHistory());
                    user.setBookingHistory(bh);
                    userMap.put(user.getUserId(), user);
                }
            }
        }
        Map<String, SearchResults> userIdUserMap = SearchResultsTransformer.transformUserSearchResults(new ArrayList<>(userMap.values()));

        return userIdUserMap;
    }
*/
}
