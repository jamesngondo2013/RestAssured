
package com.rest.oauth2.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExternalUrls__1 {

    @JsonProperty("spotify")
    private String spotify;

//    @JsonProperty("spotify")
//    public String getSpotify() {
//        return spotify;
//    }
//
//    @JsonProperty("spotify")
//    public void setSpotify(String spotify) {
//        this.spotify = spotify;
//    }

}
