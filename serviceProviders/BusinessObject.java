package com.serviceProviders;
	
	import java.io.FileInputStream;
	import java.util.Properties;
	import com.services.LoginServices;
	
		//object building method to hide the actual business logic class
	
		public class BusinessObject
		{
			public static LoginServices provideObject()
			{
				LoginServices obj=null;
				FileInputStream fis = null;

				try
				{
					fis = new FileInputStream(".//Resources//LoginInfo.properties" + "");
					Properties p = new Properties();
					p.load(fis);
					
					String className=p.getProperty("businessClass");
					obj=(LoginServices)Class.forName(className).newInstance();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				return obj; 

				
			}
		}