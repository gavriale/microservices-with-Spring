package com.example.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * We have 3 different Spring boot applications, and we want to start the communication between
 * the apps. How do we do it?
 *
 * How to make a REST call from your code?
 * 1)calling REST APIs programatically
 * 2)Using a REST client library
 * 3)Spring boot comes with a client already in your classpath - RestTemplate - This is
 * what we use to make REST APIs calls
 *
 * we have to write code that makes the code, gets the response in a String/JSON form and parses
 * it to the object instance that needed and then send it back to the client that made the request
 *
 *
 */


@SpringBootApplication
public class MovieCatalogServiceApplication {

	@Bean
	/**
	 * This method executes just once,anybody autowires RestTemplate they gonna get only one instance
	 */
	/**
	 * The @LoadBalanced annotation. It does service discovery in a @LoadBalanced way, dont go to the service
	 * wherever url im giving you this url is a hint to what service you need to discover
	 */
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

}
