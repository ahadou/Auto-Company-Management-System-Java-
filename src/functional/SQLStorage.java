package functional;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

//class for handling calls to mySQL database
public class SQLStorage
{
	public static Connection con = null;
	public static Statement stmnt = null;
	private ResultSet results = null;

	//constructor
	public SQLStorage()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auto_company","root","athenry24");
			stmnt = con.createStatement();   //Creating a "Statement" which handles sql statements in the program
		}
		catch(SQLException se)
		{
			JOptionPane.showMessageDialog(null, "There has been a database connection error - try again",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception se)
		{
			JOptionPane.showMessageDialog(null, "Something is wrong - please try again",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

	}//end constructor

	//methods
	public void executeStatement(String statement)
	{
		try
		{
			stmnt.executeUpdate(statement);
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "There has been a database connection error - try again",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

	}//end executeStatement method

	public ResultSet getResults(String statement)
	{

		try
		{
			results = stmnt.executeQuery(statement);
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "There has been a database connection error - try again",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return results;

	}//end getResults Method

}//end SOLStorage class
