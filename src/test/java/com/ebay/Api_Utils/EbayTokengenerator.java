package com.ebay.Api_Utils;

import java.util.Base64;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class EbayTokengenerator {



private static final String CLIENT_ID = "AtharvaT-Automati-SBX-b8e98c9a6-55db5234";
private static final String CLIENT_SECRET = "SBX-8e98c9a688f2-97be-4e4b-8ae9-5d9c";
private static final String TOKEN_URL = "https://api.sandbox.ebay.com/identity/v1/oauth2/token";

 
 @Test
  public static String GetAccessToken(String clientId, String clientSecret) {
	  
	  

	// Encode credentials in Base64
	String credentials = CLIENT_ID + ":" + CLIENT_SECRET;
	String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
    
	Response response=(Response) RestAssured.given()
			.header("Content-Type","application/x-www-form-urlencoded")
			.header("Authorization", "Basic " + encodedCredentials)
			.header("grant_type","client_credentials")
			.header("scope", "https://api.ebay.com/oauth/api_scope")
			.post(TOKEN_URL);
	
	  if(response.statusCode()==200) {
		  
		    String token= response.jsonPath().getString("access_token");
		    System.out.println("Access Token is "+token);
		    return token;
	  }else {
		  

            System.err.println("Failed to get token. Status Code: " + response.statusCode());
            System.err.println("Response: " + response.getBody().asString());

	  }
			
	     
	   return null;
  }


}
