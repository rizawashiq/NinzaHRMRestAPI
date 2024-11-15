package com.ninza.hrm.api.genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	public String getDataFromFile(String prop) throws IOException
	{
		FileInputStream fis = new FileInputStream("./data.properties");
		Properties p = new Properties();
		p.load(fis);
		return p.getProperty(prop);
		
	}
}
