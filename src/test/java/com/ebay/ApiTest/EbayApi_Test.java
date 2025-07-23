package com.ebay.ApiTest;

import org.testng.annotations.Test;

import com.ebay.Api_Utils.EbayTokengenerator;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class EbayApi_Test {
	
      @Test 
	  public void TestEbayAPI() {
		     
		      String token=EbayTokengenerator.GetAccessToken("AtharvaT-Automati-SBX-b8e98c9a6-55db5234","SBX-8e98c9a688f2-97be-4e4b-8ae9-5d9c");
		      
		    
		      Response response=RestAssured
		      .given()
		      .header("Authorization" ,"Bearer" + token)
		      .header("Content-Type","application/json")
		      .get("https://api.ebay.com/sell/inventory/v1/inventory_item");

		      System.out.println(response.getBody().asString());
	  }
}
