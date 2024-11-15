package com.ninza.hrm.baseClass;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.ninza.hrm.api.genericUtility.DataBaseUtility;
import com.ninza.hrm.api.genericUtility.FileUtility;
import com.ninza.hrm.api.genericUtility.JavaUtility;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static  io.restassured.RestAssured.*;

public class BaseClass {
	

	public JavaUtility j = new JavaUtility();
	public DataBaseUtility d = new DataBaseUtility();
	public FileUtility f = new FileUtility();
	public static RequestSpecification reqSpecObj ; 
	public static ResponseSpecification respSpecObj;
	
	@BeforeSuite
	public void ConfigBS() throws SQLException, IOException {
		d.getConnection();
		System.out.println("=========Connected to DB ==========");
		RequestSpecBuilder b = new RequestSpecBuilder();
		b.setContentType(ContentType.JSON);
//		b.setAuth(basic("username", "password"));
//		b.addHeader("", "");
		b.setBaseUri(f.getDataFromFile("BaseURL"));
		reqSpecObj =  b.build();
		
		ResponseSpecBuilder builder = new ResponseSpecBuilder();
		builder.expectContentType(ContentType.JSON);
		respSpecObj = builder.build();
		
		
	}
	
	
	
	@AfterSuite
	public void ConfigAS() throws SQLException, IOException {
		d.closeConnection();
		System.out.println("=========Disconnected From DB ==========");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
