package com.client;

import java.util.Scanner;

import com.serviceProviders.BusinessObject;
import com.services.LoginServices;

public class ClientCode 
{

	public static void main(String[] args) 
	{
		LoginServices loginServices = BusinessObject.provideObject();
		
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the username of your choice");
		String username = scanner.next();
		
		System.out.println("Please enter the password of your choice");
		String password = scanner.next();
		
		System.out.println("Please enter the security question of your choice");
		String securityquestion = scanner.next();
		
		System.out.println("Please enter the security password of your choice");
		String securitypassword = scanner.next();
		
		String accountDetails = loginServices.signUp(username, password, securityquestion, securitypassword);
		System.out.println("Account has been created with the username as : "+accountDetails);
		
		boolean signinDetails = loginServices.signin(username, password);
		System.out.println(signinDetails);
		
		String forgetPasswordDetails = loginServices.forgetPassword(username, securityquestion, securitypassword);
		System.out.println(forgetPasswordDetails);
	
		System.out.println("Please enter the new password of your choice");
		String newPassword = scanner.next();
		
		String updatePasswordDetails = loginServices.updatePassword(username, password, newPassword);
		System.out.println(updatePasswordDetails);

	}

}

