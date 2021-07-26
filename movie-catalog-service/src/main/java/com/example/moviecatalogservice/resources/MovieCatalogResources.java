package com.example.moviecatalogservice.resources;

import com.example.moviecatalogservice.models.CatalogItem;
import com.example.moviecatalogservice.models.Movie;
import com.example.moviecatalogservice.models.Rating;
import com.example.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class responsible for accept request: give me the catalog for the specific id.
 * It wil return a list of movies
 */


@RestController
@RequestMapping("/catalog")
public class MovieCatalogResources {

    //you need to tell spring boot to treat it as an api that is accesible at /catalog/userId

    /**
     * Somebody have a bean of RestTemplate out there, inject me that instance
     *
     * Is it necessary to add the @Bean annotation? or does Spring register all classes that are
     * @Autowired?
     * @Autowired is a consumer, when you annotate with autowired you say to Spring : Give me something
     * @Bean annotation is a producer, your telling spring : hey you have something that other objects will need.
     * So execute the method that annotated with @Bean, save it somewhere, and when someone asks for it with
     * autowired give it to them, inject it - thats what @Bean annotation does
     *
     * When does the method to create the bean get executed? when the bean is used/called?
     *
     */
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")

    //get all movie rated Ids - this will be hard-coded
    //for each movieId call movie-info-service and get details - this is our focus
    //put them all together

    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        /**
         * the RestTemplate getForObject() takes 2 arguments
         * 1)First argument is the url we want to call, it can be any url, and what it gets back
         * its a String, if we provide a class with the same properties as the JSON in the classpath, RestTemplate
         * will create the instance of the class populate the properties and give you fully formed object
         */
        //Movie movie = restTemplate.getForObject("http://localhost:8081/movies/Matrix", Movie.class);

        /**
         * we are assuming that this is the response we got from rating-data-api
         * Somebody requested all the movies some user as watched and the details
         * After we have all the movies he watched we need to get the details
         * and for the details we have to make the api call
         */

        /**
         * it needs to return List of ratings Object, but we cant do List.class, becuase List is generic.
         * You create an instance of the class called parameterizedType and you pass in the type that
         * you want
         */

        /**
         * Eureka doesnt know what is port 8083, Eureka just knows the names given to the apps
         * so instead using this "http://localhost:8083/ratingsdata/users/"
         * "http://ratings-data-service/ratingsdata/users/" - it looks like a url but its not
         * but we told RestTemplate do not take the url literally with the @LoadBalanced annotation
         */
        //get all movie rated Ids - this will be hard-coded
       UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId,
                UserRating.class);

        /**
         * We need the create an instance of RestTemplate -> RestTemplate is gonna be the utility
         * Object which lets me make this calls(to other services)
         */

        //for every rated movie, I need to take the movieId and to call the movie-info-api with that id

        //for each movieId call movie-info-service and get details - this is our focus
        return ratings.getUserRating().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getName(),movie.getDescription(),rating.getRating());
            //put them all together
        }).collect(Collectors.toList());
    }


}
