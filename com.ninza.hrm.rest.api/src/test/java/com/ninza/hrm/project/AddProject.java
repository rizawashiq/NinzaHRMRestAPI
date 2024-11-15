package com.ninza.hrm.project;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.POJOClass.ProjectPOJO;
import com.ninza.hrm.api.genericUtility.DataBaseUtility;
import com.ninza.hrm.api.genericUtility.FileUtility;
import com.ninza.hrm.api.genericUtility.JavaUtility;
import com.ninza.hrm.baseClass.BaseClass;
import com.ninza.hrtm.endPoints.IEndPoint;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class AddProject extends BaseClass {
	
	
	@Test
	public void addProj() throws SQLException, IOException {
		int x = j.getRandomNumber();
		String proj_name = "Game_Of_Thrones_"+x;
		ProjectPOJO project = new ProjectPOJO("Arya_Stark", proj_name, 
				"Created", 0);
		

		Response resp =   given()
							.spec(reqSpecObj)
							.body(project)
						.when()
							.post(IEndPoint.addProj) ;
		
		resp.then().assertThat().statusCode(201)
					.assertThat().time(Matchers.lessThan(3000L))
					.spec(respSpecObj)
					.log().all();
		
		String expSucMsg = "Successfully Added";
		String actMsg = resp.jsonPath().get("msg"); 
		Assert.assertEquals(expSucMsg, actMsg);
		
		Assert.assertEquals(proj_name, resp.jsonPath().get("projectName"));
		Assert.assertEquals("Arya_Stark", resp.jsonPath().get("createdBy"));
		
		Assert.assertTrue(d.verifyData("Select * from project", 4, proj_name));
	}
}









