package com.rest.oauth2.api.applicationApi;


import com.rest.oauth2.api.RestResource;
import com.rest.oauth2.api.Route;
import com.rest.oauth2.pojo.PlaylistItem;
import com.rest.oauth2.utils.ConfigLoader;
import com.rest.oauth2.api.TokenManager;
import io.restassured.response.Response;

public class PlaylistApi {
	
	public static Response post(PlaylistItem requestPlaylist) {
		return RestResource.post(Route.USERS + "/"+ ConfigLoader.getInstance().getUserId() + Route.PALYLIST, TokenManager.getToken(), requestPlaylist);	         
	}
	
public static Response post(String invalid_token, PlaylistItem requestPlaylist) {
		return RestResource.post(Route.USERS +"/" + ConfigLoader.getInstance().getUserId() + Route.PALYLIST, invalid_token, requestPlaylist);
	}
	
	public static Response get(String playlistId) {
		return RestResource.get(Route.PALYLIST + "/"+playlistId, TokenManager.getToken());
	}
	
	public static Response update(String playlistId, PlaylistItem requestPlaylist) {
		 return RestResource.update(Route.PALYLIST + "/"+playlistId, TokenManager.getToken(), requestPlaylist);
         
	}

}
