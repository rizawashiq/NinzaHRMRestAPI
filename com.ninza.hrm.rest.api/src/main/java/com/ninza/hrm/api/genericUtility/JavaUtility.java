package com.ninza.hrm.api.genericUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	public int getRandomNumber() {
		return new Random().nextInt(5000);
	}
	
	public String getDateInFormat(String format)
	{
		String x = new SimpleDateFormat(format).format(new Date());
		return x ;
	}
	
	
	
}
