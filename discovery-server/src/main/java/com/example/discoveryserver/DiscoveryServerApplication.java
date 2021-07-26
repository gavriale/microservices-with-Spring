package com.example.discoveryserver;

/**
 * The application uses microservices kinda like Imdb
 * users can save the movies that they watched and rate them
 * So we can go to the system and say -  I have seen 5 movies last year,this how i rate them
 * You should call an API and get back that movie information and the ratings for a particular movie
 * We used the discovery-server and eureka technology to have the services talk to each other
 *
 * we broke it down to 3 microservices -
 * 1.movie-info-service - takes a movieId and returns movie information - Movie Object
 * 2.rating-data-service - stored the users ratings we hard coded it because we didnt wanna do all the
 * data base stuff, it was hard coded of movies Ids and ratings that everybody get.(next challange is
 * to take this and connect it to a database)
 *
 * 3.The movie-catalog-service is the main service, kinda like the entry point for somebody who is calling it
 * movie-catalog-service tells to ratings-data-service go get me the all the movies that the user rated,and then
 * for each movieId there call the movie-info-service and get the information of the movie, bundle it together
 * with the rating for the movie, and return all the movies with the ratings that the user watched in one JSON
 * response
 *
 * What next? Phase 2 of the course
 * MovieDB is a site - kind of imdb, containing movie information, no more 3 hard coded movies
 * We want to get from MovieDB movie information based on the movieId we are passing in, we want to use it
 * to make external call for existing API and get live movie information to show to our users.
 * It's good example of showing how we can make an external call, and how there are points of failure
 * that can happen because of interactions - more interactions - things can fail.
 *
 * We will create account in MovieDB, go to API section and we can use the API.
 * When creating an account we get an API key, the key is what required for making API request.
 * We will use this API to have the movie-info-service to make an actual call and get actual
 * Movie information
 *
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApplication {

	/**
	 * Recap of what we did :
	 * 1.We started up multiple microservices
	 * 2.Had them communicate with each other using RestTemplate and WebClient - with hard coded Urls
	 * 3.We learned how it's bad, we dont want hard coded url's
	 * 4.So we used a concept of service discovery - created a eureka server
	 * 5.Add all those microservices register as eureka clients, by adding them to the classpath
	 * 6.We used the @LoadBalanced annotation on RestTemplate to leverage the eureka server with
	 * with very minimal code changes we were able to get all this functionality by having client-side
	 * load balancing
	 *
	 * Next Steps -
	 *
	 *
	 */


	/**
	 * Now we registered the services, how do we now consume it? You have to have a way
	 * for you to say : "Hi Eureka Server give me the Service"
	 * Where is the consuming happening? In the movie-catalog-service
	 *
	 * But now the code in movie-catalog-service is bad because it's hard coded.
	 * The way to consume it is......another annotation!
	 *
	 * when you have RestTemplate that you are using, you can say ok i will use a RestTemplate
	 * to make an API call to service-discovery get service location, and use the same RestTemplate
	 * to make another API call
	 *
	 * RestTemplate has the ability to hide...I can tell RestTemplate i want you to call the
	 * service-discovery every time, I will give to the RestTemplate the service name and RestTemplate
	 * will call service-discovery every time
	 *
	 * The annotation we use is @LoadBalanced
	 *
	 */


	public static void main(String[] args) {
		SpringApplication.run(DiscoveryServerApplication.class, args);
	}

}
