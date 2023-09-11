package com.databaseConnectionProvider;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;
import java.sql.Statement; 

public class DatabaseConnectionProvider 
{
	static Connection con = null;
	static Statement stmt;
	ResultSet rs;
	static FileInputStream fis=null;
	
	public static Connection databaseConnection()
	{
		try 
		{
			fis = new FileInputStream(".//Resources//LoginDBConnection.properties");
			Properties p = new Properties();
			p.load(fis);
			String driver = p.getProperty("driverclass");
			String url = p.getProperty("url");
			String username = p.getProperty("username");
			String password = p.getProperty("password");
			
			//connection is created
			
			Class.forName(driver);
			con=DriverManager.getConnection(url,username,password);
			
			stmt = con.createStatement();
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
		
		// data retrieving method
	
		public void retriveData()
		{
			try 
			{
				rs = stmt.executeQuery("select * from loginData");
				
				while(rs.next())
				{
					String username = rs.getString(1);
					String password = rs.getString(2);
					String securityquestion = rs.getString(3);
					String securitypassword = rs.getString(4);
					
					System.out.println("Login Data is ......" + username + " / " + password + " / " + securityquestion + " / " +securitypassword);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		public static void main(String [] args)
		{
			DatabaseConnectionProvider dcp = new DatabaseConnectionProvider();
			DatabaseConnectionProvider.databaseConnection();
			dcp.retriveData();
		}
}
	
	

	
