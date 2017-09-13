package com.dj.transformers;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dj.dto.BookingHistory;
import com.dj.dto.SearchResults;
import com.dj.dto.User;

public class SearchResultsTransformer {


    public static Map<String, SearchResults> transformUserSearchResults(List<User> users) {
        //List<SearchResults> searchResultList =  new ArrayList<>();
        Map<String, SearchResults> map = new HashMap<String, SearchResults>();
        for(User user : users) {
            int verifiedBookingCount = 0;
            SearchResults searchResults = new SearchResults();
            searchResults.setUserId(user.getUserId());
            searchResults.setFirstName(user.getFirstName());
            searchResults.setLastName(user.getLastName());
            searchResults.setCreatedOn(user.getCreatedOn());
            searchResults.setStatus(user.getStatus());
            //searchResults.setHourlyRate(user.getPricingDetails().getDailyRate());
            /*for (BookingHistory bookingHistory : user.getBookingHistory()) {
                verifiedBookingCount++;
                Set<Date> futureBookedDates =  new HashSet<>();
                if(bookingHistory !=null && bookingHistory.getBookingStartDate()!= null && bookingHistory.getBookingStartDate().after(new Date())) {
                    futureBookedDates.add(bookingHistory.getBookingStartDate());
                }
                if(bookingHistory !=null && bookingHistory.getBookingEndDate()!= null && bookingHistory.getBookingEndDate().after(new Date())) {
                    futureBookedDates.add(bookingHistory.getBookingEndDate());
                }
                
            }*/
            searchResults.setNumberOfVerifiedBookings(verifiedBookingCount);
            //searchResults.setDataLocation(user.getUserDataCollection().get(0).getDataLink());
            //searchResults.setReviews(user.getReviews());
            map.put(Long.toString(searchResults.getUserId()), searchResults);
        }
        return map;
    }
    


}
