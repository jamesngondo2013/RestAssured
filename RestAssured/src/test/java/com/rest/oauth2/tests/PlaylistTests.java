package com.rest.oauth2.tests;

import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

import com.rest.oauth2.pojo.ErrorRoot;
import com.rest.oauth2.pojo.Playlist;
import com.rest.oauth2.pojo.PlaylistItem;
import com.rest.oauth2.utils.ConfigLoader;
import com.rest.oauth2.api.applicationApi.PlaylistApi;
import com.rest.oauth2.data.Data;


public class PlaylistTests {
	
	public PlaylistItem playlistBuilder(String name, String description, Boolean _public) {
		return new PlaylistItem().
		setName(name).
		setDescription(description).
		setPublic(_public);

	}
	
	public void assertPlaylistEquals(PlaylistItem requestPlaylist, PlaylistItem responsePlaylist ) {
		assertEquals(responsePlaylist.getName(), requestPlaylist.getName());
        assertEquals(responsePlaylist.getDescription(), requestPlaylist.getDescription());
        assertEquals(responsePlaylist.getPublic(), requestPlaylist.getPublic());
	}
	
	public void assertStatusCode(int actualStatusCode, int expectedStatusCode) {
		 assertEquals(actualStatusCode, expectedStatusCode);
	}
	
	public void assertError(ErrorRoot responseError, int expectedStatusCode, String expectedMessage) {
		 assertEquals(responseError.getError().getStatus(), expectedStatusCode);
	     assertEquals(responseError.getError().getMessage(), expectedMessage);
	}

    @Test
    public void createPlaylist(){   
        PlaylistItem requestPlaylist = playlistBuilder("LivingWaters Church " + Data.randValues(),
        		"New playlist description",false);
        		
        
        Response response = PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(), 201);
        //deserialize json to this PlaylistItem object
        assertPlaylistEquals(response.as(PlaylistItem.class), requestPlaylist);             
    }


    @Test
    public void updatePlaylist(){
    	PlaylistItem requestPlaylist = playlistBuilder("Updated AMAI IRE",
        		"New playlist description",false);

    	Response response = PlaylistApi.update(DataLoader.getInstance().getPlaylistIdToUpdate(), requestPlaylist);
    	assertStatusCode(response.statusCode(), 200);   	   
    }
    
    @Test
    public void getPlaylist(){
    	PlaylistItem requestPlaylist = playlistBuilder("LivingWaters Church Glasgow Scotland",
        		"New playlist description",false);
    	
    	Response response = PlaylistApi.get(DataLoader.getInstance().getPlaylistIdValue());
    	assertStatusCode(response.statusCode(), 200);   
         assertPlaylistEquals(response.as(PlaylistItem.class), requestPlaylist); 
    }

    //Negative Test
    @Test
    public void shouldNOTcreatePlaylistWithoutName(){
    	PlaylistItem requestPlaylist = playlistBuilder("",
        		"New playlist description",false);
    	
    	Response response = PlaylistApi.post(requestPlaylist);
    	assertStatusCode(response.getStatusCode(), 400);
        assertError(response.as(ErrorRoot.class), 400, "Missing required field: name");
    }

    @Test
    public void shouldNOTcreatePlaylistWithExpiredTooken(){
    	PlaylistItem requestPlaylist = playlistBuilder("LivingWaters Church Glasgow Scotland",
        		"New playlist description",false);
    	
    	Response response = PlaylistApi.post(ConfigLoader.getInstance().getInvalidToken(), requestPlaylist);
    	assertStatusCode(response.getStatusCode(), 401);
        assertError(response.as(ErrorRoot.class), 401, "Invalid access token");
    }

}
