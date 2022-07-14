package com.rest.oauth2.data;

public class Data {

	public static String randValues() {
		
		String alphabet[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
		
		int val = (int) (Math.random()*26);
		String str = alphabet[val];
		String strVal = str + (int) (Math.random()*100000);
		return strVal;
	}
}
