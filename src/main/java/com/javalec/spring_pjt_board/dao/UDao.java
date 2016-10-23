package com.javalec.spring_pjt_board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.omg.CORBA.ULongLongSeqHolder;

import com.javalec.spring_pjt_board.command.ULoginCommand;
import com.javalec.spring_pjt_board.dto.BDto;
import com.javalec.spring_pjt_board.dto.UDto;

public class UDao 
{
	DataSource dataSource;
	public Boolean check_error;
	
	private static UDao dao;
	private UDao() 
	{
		try
		{
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/mysqlLocal");
		}
		catch(NamingException e)
		{
			e.printStackTrace();
		}
	}

	public static UDao getinstance()
	{	if(dao == null)
		{
			dao = new UDao();
		}
		return dao;
	}

	public UDto getuserinfo(String ID)
	{
		UDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String uId = null;
		String uPassword = null;
		String uName = null;
		String uGrade = null;
		
		String query;
		try
		{
			connection = dataSource.getConnection();
				query = "SELECT uId, uPassword, uName, uGrade FROM user_info WHERE uId = ?";

				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, ID);
				resultSet = preparedStatement.executeQuery();
				
			  while(resultSet.next())
			  {
	                uId = resultSet.getString("uId");
	                uPassword = resultSet.getString("uPassword");
	                uName = resultSet.getString("uName");
	                uGrade = resultSet.getString("uGrade");
	               
	                
	                dto = new UDto(uId, uPassword, uName, uGrade);
			  }
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
				try 
				{
					if(preparedStatement != null) preparedStatement.close();
					if(connection != null) connection.close();
				} 
				catch (Exception e2) 
				{
					e2.printStackTrace();
				}
		}
	
		return dto;
	}
	
	public void check_account(String uId, String uPassword)
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String rcv_uId = null;
		String rcv_uPw = null;
		
		try 
		{
			connection = dataSource.getConnection();
			
			String query = "select uId, uPassword from user_info where uId = ?";
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, uId);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				rcv_uId = resultSet.getString("uId");
				rcv_uPw = resultSet.getString("uPassword");
			}	
				
			
			if(rcv_uId != null && rcv_uPw != null)
			{
				if(rcv_uId.equals(uId) && rcv_uPw.equals(uPassword))
				{
					check_error = false;
					System.out.println("체크에러="+check_error);
				}
				else
				{
					check_error = true;
					System.out.println("체크에러="+check_error);
				}
				
			}
			else
			{
				check_error = true;
				System.out.println("체크에러="+check_error);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				if(resultSet != null) resultSet.close();
			} 
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
	}
	
	public void register(String uId, String uPassword, String uName)
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try 
		{
			connection = dataSource.getConnection();
			
			String query = "insert into user_info (uId, uPassword, uName, uGrade) values (?, ?, ?, ?)";
			
			String grade = "일반회원";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, uId);
			preparedStatement.setString(2, uPassword);
			preparedStatement.setString(3, uName);
			preparedStatement.setString(4, grade);
			
			
			int rn = preparedStatement.executeUpdate();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
				try 
				{
					if(preparedStatement != null) preparedStatement.close();
					if(connection != null) connection.close();
				} 
				catch (Exception e2) 
				{
					e2.printStackTrace();
				}
		}

		
	}

	
	
	public void check_ID(String uId)
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String rcv_uId = null;
		
		try 
		{
			connection = dataSource.getConnection();
			
			String query = "select uId from user_info where uId = ?";
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, uId);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
				rcv_uId = resultSet.getString("uId");
			
			if(rcv_uId != null)
				check_error = true;
			else
				check_error = false;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
				try 
				{
					if(preparedStatement != null) preparedStatement.close();
					if(connection != null) connection.close();
					if(resultSet != null) resultSet.close();
				} 
				catch (Exception e2) 
				{
					e2.printStackTrace();
				}
		}
	}

}
