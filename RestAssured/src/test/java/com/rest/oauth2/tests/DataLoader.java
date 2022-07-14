package com.rest.oauth2.tests;

import java.util.Properties;
import com.rest.oauth2.utils.PropertyUtils;

public class DataLoader {

	private final Properties properties;
	private static DataLoader dataLoader;
	
	private DataLoader() {
		properties = PropertyUtils.propertyLoader("src/test/resources/data.properties");
	}
	
	public static DataLoader getInstance() {
		if (dataLoader == null) {
			dataLoader = new DataLoader();
		}
		return dataLoader;
	}
	
	
	public String getPlaylistIdValue() {
		String prop = properties.getProperty("get_playlist_id");
		if (prop != null )	return prop;
		else throw new RuntimeException("get_playlist_id is not specified in the confi.properties file");
			
	}
	
	public String getPlaylistIdToUpdate() {
		String prop = properties.getProperty("update_laylist_id");
		if (prop != null )	return prop;
		else throw new RuntimeException("update_laylist_id is not specified in the confi.properties file");
			
	}
}
