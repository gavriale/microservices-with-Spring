package com.example.ratingsdataservice.resources;

import com.example.ratingsdataservice.models.Rating;
import com.example.ratingsdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId")String movieId){
        return new Rating(movieId,9);
    }

    /**
     * Lets say we have created an API which is a List of Movies that the user as watched, but then i also wanna
     * add a field in the future, lets say I want to enhance the API to return the users full name also.
     * So now I cant use a List anymore I have to convert it into an Object. Any time you want to make an
     * enhancement, and the enhancement not to a particular object in the array, but you need something global.
     * You have to take it from a List to an Object, all those people who are expecting a list their code is
     * gonna break, because it's not a list anymore.
     *
     * However if you have an object the begin with, and even if you have one property of this object to be a List
     * then you can add extra properties to it and your API is still backward compatible, thats why I usually have
     * an Object wrapper in my API response even if what im returning is just a list, thats something that ensures that
     * your API is backward compatible
     *
     */

    @RequestMapping("/users/{userId}")
    public UserRating getUserRating(@PathVariable("userId")String userId ){

        UserRating userRating = new UserRating();
        userRating.initData(userId);
        return userRating;
    }



}
