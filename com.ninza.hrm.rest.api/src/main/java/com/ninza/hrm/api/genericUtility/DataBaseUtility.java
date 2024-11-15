package com.ninza.hrm.api.genericUtility;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.Assert;

public class DataBaseUtility {
	static Connection c ;
	FileUtility f = new FileUtility();
	
	public void getConnection() throws SQLException, IOException {
		Driver d = new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(d);
		 c =  DriverManager.getConnection(f.getDataFromFile("DBURL") ,
		f.getDataFromFile("DB_UN") ,f.getDataFromFile("DB_PWD") );
	}
	
	
	public boolean verifyData(String query , int col_num , String expectedData) throws SQLException, IOException
	{
		ResultSet result = c.createStatement().executeQuery(query);
		boolean flag = false ;
		while(result.next())
		{
//			System.out.println(result.getString(col_num));
			if(result.getString(col_num).equals(expectedData))
				flag = true ;
			
		}
		if(flag)
			System.out.println(expectedData + " data found in DataBAse");
		else
			System.out.println(expectedData + " data is Not found in DataBAse");
		return flag ;
			
	}
	
	public void closeConnection() throws SQLException {
		c.close();
	}
}
