package com.rest.oauth2.api;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import io.restassured.response.Response;

public class RestResource {
	
	public static Response post(String path, String token, Object requestPlaylist) {
		
		return given(SpecBuilder.getRequestSpec()).
					//header("Authorization", "Bearer " + token).
					auth().oauth2(token).
	                body(requestPlaylist).
	                when().post(path).
	                then().spec(SpecBuilder.getResopnseSpec()).
	                extract().
	                response();
	             
	}
	
	
	public static Response get(String path, String token) {
		  return given(SpecBuilder.getRequestSpec()).
				  	auth().oauth2(token).
				  	//header("Authorization", "Bearer " + token).
	                when().get(path).
	                then().spec(SpecBuilder.getResopnseSpec()).
	                extract().
	                response();
	}
	
	public static Response update(String path, String token, Object requestPlaylist) {
		 return given(SpecBuilder.getRequestSpec()).
				auth().oauth2(token).
				//header("Authorization", "Bearer " + token).
		        body(requestPlaylist).
		        when().put(path).
		        then().spec(SpecBuilder.getResopnseSpec()).
		        extract().
		        response();
         
	}
	
	public static Response postNewTokenAccount(HashMap<String, String> formParams) {
		return given(SpecBuilder.getNewTokenAccountRequestSpec()).
				formParams(formParams).
				log().all().
				when().post(Route.API + Route.TOKEN).
				then().spec(SpecBuilder.getResopnseSpec()).
				extract().
				response();
	}

}
