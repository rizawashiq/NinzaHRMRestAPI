package test;

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

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.POJOClass.EmployeePOJO;
import com.ninza.hrm.POJOClass.ProjectPOJO;
import com.ninza.hrm.api.genericUtility.DataBaseUtility;
import com.ninza.hrm.api.genericUtility.FileUtility;
import com.ninza.hrm.api.genericUtility.JsonUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Demo {
	public static void main(String[] args) throws IOException, SQLException {
		JsonUtility j = new JsonUtility();
		System.out.println(j.getToken());
		
		
		
//		DataBaseUtility d = new DataBaseUtility();
//		int x = new Random().nextInt(5000);
//		String proj_name = "Game_Of_Thrones_"+x;
////		System.out.println(new FileUtility().getDataFromFile("DBURL"));
//		Assert.assertTrue(d.verifyData("Select * from project", 4, proj_name));
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
