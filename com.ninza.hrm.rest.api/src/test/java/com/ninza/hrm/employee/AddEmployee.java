package com.ninza.hrm.employee;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.POJOClass.EmployeePOJO;
import com.ninza.hrm.POJOClass.ProjectPOJO;
import com.ninza.hrm.api.genericUtility.DataBaseUtility;
import com.ninza.hrm.api.genericUtility.FileUtility;
import com.ninza.hrm.api.genericUtility.JavaUtility;
import com.ninza.hrm.baseClass.BaseClass;
import com.ninza.hrtm.endPoints.IEndPoint;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AddEmployee  extends BaseClass{
	int x = j.getRandomNumber();
	ProjectPOJO project ;
	String proj_name = "Game_Of_Thrones_"+x;
	@Test
	public void addProj() throws IOException {
		 project = new ProjectPOJO("Arya_Stark", proj_name, 
				"Created", 0);

		Response resp =   given()
				.spec(reqSpecObj)
							.body(project)
						.when()
							.post( f.getDataFromFile("BaseURL") +IEndPoint.addProj) ;
		resp.then().assertThat().statusCode(201)
					.assertThat().contentType(ContentType.JSON)
					.assertThat().time(Matchers.lessThan(3000L))
					.spec(respSpecObj)
					.log().all();
		
	}
	
	@Test(dependsOnMethods = "addProj")
	public void addEmployee() throws SQLException, IOException {
		String ename = "Jon_Snow"+x;
		String date = j.getDateInFormat("dd/MM/yyyy");
		EmployeePOJO emp = new EmployeePOJO("King in the north", date, "jonsnow"+x+"@gmail.com",
				ename, 12.3, "9876543219",proj_name , "GateWatcher", ename);
		Response resp =  given()
				.spec(reqSpecObj)
			.body(emp)
		.when()
			.post(IEndPoint.addEmpl);
		System.out.println("=========");
		resp.then()
			.assertThat().statusCode(201)
			.assertThat().time(Matchers.lessThan(3000L))
			.spec(respSpecObj)
			.log().all();
		
		String expSucMsg = "Employee Added Successfully";
		String actMsg = resp.jsonPath().get("msg"); 
		Assert.assertEquals(expSucMsg, actMsg);

		
		Assert.assertTrue(d.verifyData("Select * from employee", 5, ename));
	
	}
	
	
	
	
	
	
}
