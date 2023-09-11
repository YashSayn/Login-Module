package com.servicesImplementations;

import com.Exception.InvalidUsernameException;
import com.bean.Login;
import com.daoImplementation.DaoImpl;
import com.services.LoginServices;

public class LoginOperations implements LoginServices
{
	private Login lg;
	DaoImpl dao = new DaoImpl();
	// Sign Up Method
	
	public String signUp(String username, String password, String securityquestion, String securitypassword) 
	{
		lg = new Login(username, password, securityquestion, securitypassword);
		
		//put data into the database
		
		dao.insertData(lg);
		return lg.getUsername();
		
	}
	
	// Validating the username
	
	public boolean validateUserName(String username) throws InvalidUsernameException
	{
		boolean val = false;
		if(lg.getUsername().equals(username))
		{
			val =  true;
		}
		else
		{
			throw new InvalidUsernameException("Incorrect Username ...");
		}
		return val;
	}

	// Sign In Method
	
	public boolean signin(String username, String password) 
	{
		boolean loginStatus = false;
		
		try 
		{
			if(validateUserName(username))
			{
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
			}
			else
			{
				System.out.println("Invalid Username..... Please try again");
			}
		} 
		catch (InvalidUsernameException e) 
		{
			
			e.printStackTrace();
		}
		
		return loginStatus;
		
	}

	// Forgot Password Method
	
	public String forgetPassword(String username, String securityquestion, String securitypassword) 
	{
		String oldPassword = null;
		System.out.println(securityquestion);
		
		try
		{
			if(validateUserName(username))
			{
				if(lg.getSecuritypassword().equals(securitypassword))
				{
					System.out.println("The security question has been correctly answered.");
					System.out.println("The login password is :");
					oldPassword = lg.getPassword();
				}
				else
				{
					System.out.println("The answer of security question is incorrect....... please try again.");
					oldPassword = null;
				}	
			}
			else
			{
				System.out.println("Invalid Username..... Please try again");
			}
			
		}
		catch(InvalidUsernameException e)
		{
			e.printStackTrace();
		}
		return oldPassword;
		
		
	}
	
	//Password Update Method

	public String updatePassword(String username, String password, String newPassword) 
	{
		String upPass = null;
		
		try 
		{
			if(validateUserName(username))
			{
				lg.setPassword(newPassword);
				
				upPass = lg.getPassword();
				System.out.println("The password has been updated. ");
				System.out.println("The new password is  : " +upPass);
			}
			else
			{
				System.out.println("The username or the password is incorrect....please try again");
				upPass = null;
			}
		} 
		catch (InvalidUsernameException e) 
		{
			e.printStackTrace();
		}
		
		return upPass;
	}

	
}
