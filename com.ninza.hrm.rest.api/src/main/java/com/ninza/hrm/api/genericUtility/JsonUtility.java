package com.ninza.hrm.api.genericUtility;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.List;

import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

public class JsonUtility {
	FileUtility f = new FileUtility();
	
	public boolean verifyDataOnJsonPath(Response r , String jsonPath , String expectedData)
	{
		List<String> list = JsonPath.read(r.asString(), jsonPath);
		boolean flag = false ;
		for(String x : list)
		{
			if(x.equals(expectedData))
				flag = true ;
		}
		if(flag)
			System.out.println(expectedData + " data found in Json ");
		else
			System.out.println(expectedData + " data is Not found in Json");
		
		return flag ;
	}
	
	public String getToken() throws IOException {
		Response r = given()
						.formParam("client_id", f.getDataFromFile("ClientID"))
						.formParam("client_secret" , f.getDataFromFile("ClientSecret"))
						.formParam("grant_type", "client_credentials")
					.when()
						.post("http://49.249.28.218:8180/auth/realms/ninza/protocol/openid-connect/token");
		r.then()
			.log().all();
		
		return r.jsonPath().getString("access_token");
		
	}
}
