package com.Exception;

// Invalid Account No Exception Class

public class InvalidUsernameException extends Exception
{
private String messagee;
	
	public InvalidUsernameException(String messagee)
	{
		this.messagee=messagee;
	}
	public String toString()
	{
		return "Problem occured due to..." +messagee;
	}

}
