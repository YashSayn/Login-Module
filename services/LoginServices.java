package com.services;

public interface LoginServices 
{
	//menu methods
	
	public String signUp(String username, String password, String securityquestion, String sercuritypassword);
	public boolean signin(String username, String password);
	public String forgetPassword(String username, String securityquestion, String sercuritypassword);
	public String updatePassword(String username, String password, String newPassword);
	
}
