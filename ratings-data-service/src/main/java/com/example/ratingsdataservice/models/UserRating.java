package com.example.ratingsdataservice.models;

import java.util.Arrays;
import java.util.List;

public class UserRating {

    private String userId;
    private List<Rating> userRating;

    public UserRating() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Rating> getUserRating() {
        return userRating;
    }

    public void setUserRating(List<Rating> userRating) {
        this.userRating = userRating;
    }

    public void initData(String userId){
        this.userId = userId;
        this.setUserRating(Arrays.asList(
                new Rating("100",9),
                new Rating("200",5),
                new Rating("300",6),
                new Rating("400",7),
                new Rating("500",8)
        ));
    }
}
