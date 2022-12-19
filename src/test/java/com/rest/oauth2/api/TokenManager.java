package com.rest.oauth2.api;

import java.time.Instant;
import java.util.HashMap;

import com.rest.oauth2.utils.ConfigLoader;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class TokenManager {

	private static String access_token;
	private static Instant expiry_time;
	
	public static String getToken() {
		
		try {
			if (access_token == null || Instant.now().isAfter(expiry_time)) {
				System.out.println("Renewing token...");
				Response response = renewToken();
				access_token = response.path("access_token");
				int expiryDurationInSeconds = response.path("expires_in");
				//note: "expires_in": 3600 sec = 60mins = 1hr => add 3300 sec to expiry time
				expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300); //subtract 5mins from expiry  time to be safe
			}
			else {
				System.out.println("Token is still valid...");
			}
			
		} catch (Exception e) {
			throw new RuntimeException("ABORT!!! Renew Token Failed");
		}
		
		return access_token;
		
	}
	
	private static Response renewToken() {
		
		HashMap<String, String> formParams = new HashMap<String, String>();
		formParams.put("client_id", ConfigLoader.getInstance().getClientId());
		formParams.put("client_secret", ConfigLoader.getInstance().getClientSecret());
		formParams.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());
		formParams.put("grant_type", ConfigLoader.getInstance().getGrantType());
		
		Response response = RestResource.postNewTokenAccount(formParams);
		
		if (response.statusCode() != 200) {
			throw new RuntimeException("ABORT!!! Renew Token Failed");
		}
		
		return response;
	}
}
