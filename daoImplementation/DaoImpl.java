package com.daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.bean.Login;
import com.dao.DaoInterface;
import com.databaseConnectionProvider.DatabaseConnectionProvider;

public class DaoImpl implements DaoInterface
{
	Connection con = DatabaseConnectionProvider.databaseConnection();

	// inserting data into the database via MYSQL queries
	
	public void insertData(Login lg) 
	{
		try
		{
			PreparedStatement pst = con.prepareStatement("insert into accountData values(?,?,?)");
			pst.setString(1, lg.getUsername());
			pst.setString(2, lg.getPassword());
			pst.setString(3, lg.getSecurityquestion());
			pst.setString(4, lg.getSecuritypassword());
			pst.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
