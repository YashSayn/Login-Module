package com.client;


	import java.io.FileInputStream;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.Properties;
	import java.util.Scanner; 
	import java.sql.Statement;
	import com.bean.Login;

	public class ConnecProvider 
	{
		Connection con;
		Statement stmt;
		ResultSet rs;
		FileInputStream fis=null;
		
		public void databaseConnection()
		{
			try 
			{
				FileInputStream fis = new FileInputStream(".//Resources//LoginDBConnection.properties" + "");
				Properties p = new Properties();
				p.load(fis);
				String driver = p.getProperty("driverclass");
				String url = p.getProperty("url");
				String username = p.getProperty("username");
				String password = p.getProperty("password");
				Class.forName(driver);
				con=DriverManager.getConnection(url,username,password);
				stmt = con.createStatement();
			}

			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
			
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
			
			public void insertData(String username, String password, String securityquestion, String securitypassword)
			{
				try
				{
					int updatecount = stmt.executeUpdate("insert into loginData values('Tejas','tejas','whatdayistomorrow','wednesday')");
					
					if(updatecount>0)
					{
						System.out.println("query is successful");
					}
					else
					{
						System.out.println("query failed");
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}
			
			public void updateData()
			{
				try
				{
					int updatecount = stmt.executeUpdate("update loginData set securityquestion = 'whatdayis' where username = 'Tejas'");
					
					if(updatecount>0)
					{
						System.out.println("query is successful");
					}
					else
					{
						System.out.println("query failed");
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}
			public void deleteData()
			{
				try
				{
					int updatecount = stmt.executeUpdate("delete from loginData where username = 'Tejas'");
					
					if(updatecount>0)
					{
						System.out.println("query is successful");
					}
					else
					{
						System.out.println("query failed");
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
			private Login lg;
			public String signUp(String username, String password, String securityquestion, String securitypassword) 
			{
				lg = new Login(username, password, securityquestion, securitypassword);
				return lg.getUsername();
				
			}
			
			public boolean validateUserName(String username)
			{
				
				if(lg.getUsername().equals(username))
				{
					return true;
				}
				else
				{
					
					return false;
				}
			}

			public boolean signin(String username, String password) 
			{
				boolean loginStatus = false;
				
				if(lg.getUsername().equals(username) && lg.getPassword().equals(password))
				{
					System.out.println("You have signed in..");
					loginStatus = true;
				}
				else
				{
					System.out.println("The username or the password is incorrect....please try again");
					loginStatus = false;
				}
				return loginStatus;
				
			}
			
			
		public static void main(String [] args)
		{
			
			ConnecProvider j1 = new ConnecProvider();
			j1.databaseConnection();
			j1.retriveData();
			
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Please enter your username");
			String username = sc.next();
			
			System.out.println("Please enter your password");
			String password = sc.next();
			
			System.out.println("Please enter your securityquestion");
			String securityquestion = sc.next();
			
			System.out.println("Please enter your securitypassword");
			String securitypassword = sc.next();
			
			String accountDetails = j1.signUp(username, password, securityquestion, securitypassword);
			System.out.println("Account has been created with the username as : "+accountDetails);
			
			boolean signinDetails = j1.signin(username, password);
			System.out.println(signinDetails);
			
			
			j1.insertData(username, password, securityquestion, securitypassword);
			j1.updateData();
			j1.deleteData();
			j1.retriveData();

		}

}
