package com.dj.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dj.application.dao.support.JdbcDaoSupport;
import com.dj.dao.BookingDao;
import com.dj.dto.BookingHistory;
import com.dj.dto.PricingDetails;
import com.dj.dto.Review;
import com.dj.dto.User;
import com.dj.dto.UserDataCollection;

@Repository
public class BookingDaoImpl extends JdbcDaoSupport implements BookingDao {

	@Override
	public void bookBand(User user) {
		StringBuffer query = new StringBuffer();
		query.append("insert into booking_history(booked_user_id,user_type,status,booking_start_date,booking_end_date,user_id)");
		query.append(" values(?,?,?,?,?,?)");
		Object[] params = {
				/*user.getBookingHistory().get(0).getBookedUserId(),
				user.getUserType().getUserTypeId(),
				1,
				user.getBookingHistory().get(0).getBookingStartDate(),
				user.getBookingHistory().get(0).getBookingEndDate(),
				user.getUserId()*/
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
	
	@Override
    public List<User> viewBandDetails(String location, String category) {
        List<Integer> list =  new ArrayList<>();
        List<User> users = new ArrayList<User>(); 
          String query =new String();
        
          
          query =         "SELECT DISTINCT(USER_ID) FROM user_locality WHERE CITY_ID IN("
                          + "SELECT CITY_ID FROM CITY WHERE description ='"+location+ "')"
                          + "AND USER_ID IN (select distinct(user_id) from user_category where category_id in "
                          + "(SELECT CATEGORY_ID FROM category WHERE description = '"+category+"'))";
          
        try {
            list = getJdbcTemplate().queryForList(query, Integer.class );
            
        }
        catch (DataAccessException e) {
            e.printStackTrace();
        }        
        if (!CollectionUtils.isEmpty(list)) {
            for(Integer userId : list) {
                String finalquery = "SELECT usr.user_id, usr.first_name, usr.last_name, usr.status, "
                                + " bkg.booking_start_date as start_date, bkg.booking_end_date as end_date, "
                                + " pcng.hourly_rate as hourly_rate, pcng.daily_rate as daily_rate, "
                                + " udc.data_url as data_url, "
                                + " rvw.rating as rating, rvw.description as description "
                                + " FROM user usr "
                                + " left join booking_history bkg on "
                                + " usr.user_id = bkg.booked_user_id "
                                + " left join pricing_details pcng on "
                                + " pcng.user_id = usr.user_id "
                                + " and "
                                + " pcng.user_locality_id =(select user_locality_id from user_locality where user_id =? and city_id = (select city_id from city where description = ?)) "
                                + " left join user_data_collection udc on "
                                + " usr.user_id = udc.user_id "
                                + " and "
                                + " udc.category_id = (Select category_id from category where description =?) "
                                + " left join review rvw on "
                                + " rvw.booked_user_id = usr.user_id "
                                + " and "
                                + " rvw.category_id = (select category_id from category where description = ?) "
                                + "WHERE usr.user_id = ?; ";
                
                Object[] params = {userId,location,category,category,userId};
               List<User> allUserData = getJdbcTemplate().query(finalquery,params ,new RowMapper<User>(){
                
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {

                    User user = new User();

                    user.setUserId(rs.getLong("user_id"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setStatus(rs.getInt("status"));
                    List<BookingHistory> bhl =  new ArrayList<>();
                    BookingHistory bh = new BookingHistory();
                    bh.setBookingStartDate(rs.getDate("start_date"));
                    bh.setBookingEndDate(rs.getDate("end_date"));
                    bhl.add(bh);
                   // user.setBookingHistory(bhl);
                    PricingDetails pricingDetails = new PricingDetails();
                    pricingDetails.setHourlyRate(rs.getDouble("hourly_rate"));
                    pricingDetails.setDailyRate(rs.getDouble("daily_rate"));
                   // user.setPricingDetails(pricingDetails);
                    List<Review> list =  new ArrayList<>();
                    Review review =  new Review();
                    review.setDescription(rs.getString("description"));
                    review.setRating(rs.getFloat("rating"));
                    list.add(review);
                    //user.setReviews(list);
                    List<UserDataCollection> udcList =  new ArrayList<>();
                    UserDataCollection udc =  new UserDataCollection();
                    udc.setDataLink(rs.getString("data_url"));
                    udcList.add(udc);
                   // user.setUserDataCollection(udcList);

                    return user;
                }
                });
                users.addAll(allUserData);
                  
        }
            
        }
        return users;
}
	
}
