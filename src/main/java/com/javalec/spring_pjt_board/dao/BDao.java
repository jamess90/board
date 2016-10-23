package com.javalec.spring_pjt_board.dao;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.support.GenericTypeAwareAutowireCandidateResolver;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.javalec.spring_pjt_board.command.BListCommand;
import com.javalec.spring_pjt_board.command.ULoginCommand;
import com.javalec.spring_pjt_board.dto.BDto;
import com.mysql.jdbc.ResultSetMetaData;

public class BDao 
{
	DataSource dataSource;
	private static BDao dao;
	public boolean is_upgrade = false;

	public static BDao getinstance()
	{	if(dao == null)
		{
			dao = new BDao();
		}
		return dao;
	}
	
	public void write(String bName, String bTitle, String bContent, String UserID)
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BListCommand listcommand = null;
		
		
		String query;
		try
		{
			connection = dataSource.getConnection();
			if(listcommand.blist == "list")
			{	
				query = "insert into mvc_board (bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) "
									+ "values (?, ?, ?, 0, LAST_INSERT_ID() + 1, 0, 0)";
				upGrade(UserID);
			}
			else
			{
				query = "insert into mvc_vip_board (bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) "
									+ "values (?, ?, ?, 0, LAST_INSERT_ID() + 1, 0, 0)";
			}	
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, bName);
				preparedStatement.setString(2, bTitle);
				preparedStatement.setString(3, bContent);
				
			
			int rn = preparedStatement.executeUpdate();
	
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
	}
	
	public BDto contentView(String strId)
	{
		upHit(strId);
		
		BDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		BListCommand listcommand = null;
		String query;
		try 
		{
			connection = dataSource.getConnection();
			
			if(listcommand.blist == "list")
			{	
				query = "select * from mvc_board where bNum = ?";
			}
			else
			{
				query = "select * from mvc_vip_board where bNum = ?";
			}
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(strId));
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next())
			{
				int bNum = resultSet.getInt("bNum");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				dto = new BDto(bNum, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
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

		return dto;
	}
	
	private void upHit(String bNum)
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BListCommand listcommand = null;
		String query;
		try
		{
			connection = dataSource.getConnection();
			if(listcommand.blist == "list")
			{
				query = "update mvc_board set bHit = bHit+1 where bNum = ?";
			}
			else
			{
				query = "update mvc_vip_board set bHit = bHit+1 where bNum = ?";
			}
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,  bNum);
			
			int rn = preparedStatement.executeUpdate();
		} 
		
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
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
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			
		}
		
		
	}
	public void upGrade(String uId)
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query;
		String Grade = "우수회원";
		try
		{
			connection = dataSource.getConnection();

				query = "update user_info set uGrade = ? where uId = ?";

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,  Grade);
			preparedStatement.setString(2,  uId);
			
			int rn = preparedStatement.executeUpdate();
			
			is_upgrade = true;
		} 
		
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
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
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			
		}
		
		
	}
	
	public void modify(String bNum, String bName, String bTitle, String bContent) {
        // TODO Auto-generated method stub
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        BListCommand listcommand = null;
		String query;
		try
		{
			connection = dataSource.getConnection();
			if(listcommand.blist == "list")
			{
				query = "update mvc_board set bName = ?, bTitle = ?, bContent = ? where bNum = ?";
			}
			else
			{
				query =	"update mvc_vip_board set bName = ?, bTitle = ?, bContent = ? where bNum = ?";
			}
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, bName);
            preparedStatement.setString(2, bTitle);
            preparedStatement.setString(3, bContent);
            preparedStatement.setInt(4, Integer.parseInt(bNum));
            int rn = preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            } catch (Exception e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }
        }
    }

	public void delete(String strId) {
        // TODO Auto-generated method stub
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        BListCommand listcommand = null;
		String query;
		try
		{
			connection = dataSource.getConnection();
			if(listcommand.blist == "list")
			{
				query = "delete from mvc_board where bNum = ?";
			}
			else
			{
				query =	"delete from mvc_vip_board where bNum = ?";
			}
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(strId));
            int rn = preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            } catch (Exception e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }
        }
    }

	public BDto reply_view(String strId) {
        // TODO Auto-generated method stub
        BDto dto = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        BListCommand listcommand = null;
      		String query;
      		try
      		{
      			connection = dataSource.getConnection();
      			if(listcommand.blist == "list")
      			{
      				query = "select * from mvc_board where bNum = ?";
      			}
      			else
      			{
      				query =	"select * from mvc_vip_board where bNum = ?";
      			}
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(strId));
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()) 
            {
                int bNum = resultSet.getInt("bNum");
                String bName = resultSet.getString("bName");
                String bTitle = resultSet.getString("bTitle");
                String bContent = resultSet.getString("bContent");
                Timestamp bDate = resultSet.getTimestamp("bDate");
                int bHit = resultSet.getInt("bHit");
                int bGroup = resultSet.getInt("bGroup");
                int bStep = resultSet.getInt("bStep");
                int bIndent = resultSet.getInt("bIndent");
                
                
                System.out.println(bGroup);
                
                dto = new BDto(bNum, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
            }
            
        }
        catch (Exception e) 
        {
            // TODO: handle exception
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
                // TODO: handle exception
                e2.printStackTrace();
            }
        }
        
        return dto;
    }

	public void reply(String bNum, String bName, String bTitle, String bContent, String bGroup, String bStep, String bIndent) {
        // TODO Auto-generated method stub
        
        replyShape(bGroup, bStep);
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        BListCommand listcommand = null;
      		String query;
      		try
      		{
      			connection = dataSource.getConnection();
      			if(listcommand.blist == "list")
      			{
      				query = "insert into mvc_board (bName, bTitle, bContent, bGroup, bStep, bIndent) values (?, ?, ?, ?, ?, ?)";
      			}
      			else
      			{
      				query =	"insert into mvc_vip_board (bName, bTitle, bContent, bGroup, bStep, bIndent) values (?, ?, ?, ?, ?, ?)";
      			}
	          
	            preparedStatement = connection.prepareStatement(query);
	            
	            preparedStatement.setString(1, bName);
	            preparedStatement.setString(2, bTitle);
	            preparedStatement.setString(3, bContent);
	            preparedStatement.setInt(4, Integer.parseInt(bGroup));
	            preparedStatement.setInt(5, Integer.parseInt(bStep) + 1);
	            preparedStatement.setInt(6, Integer.parseInt(bIndent) + 1);
	            
	            int rn = preparedStatement.executeUpdate();
	        } catch (Exception e) {
	            // TODO: handle exception
	            e.printStackTrace();
	        } finally {
	            try {
	                if(preparedStatement != null) preparedStatement.close();
	                if(connection != null) connection.close();
	            } catch (Exception e2) {
	                // TODO: handle exception
	                e2.printStackTrace();
	            }
	        }
        
    }

	private void replyShape( String strGroup, String strStep) {
        // TODO Auto-generated method stub
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        BListCommand listcommand = null;
      		String query;
      		try
      		{
      			connection = dataSource.getConnection();
      			if(listcommand.blist == "list")
      			{
      				query = "update mvc_board set bStep = bStep + 1 where bGroup = ? and bStep > ?";
      			}
      			else
      			{
      				query =	"update mvc_vip_board set bStep = bStep + 1 where bGroup = ? and bStep > ?";
      			}
         
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(strGroup));
            preparedStatement.setInt(2, Integer.parseInt(strStep));
            
            int rn = preparedStatement.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            } catch (Exception e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }
        }
    }

	
	public BDao() 
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

	public ArrayList<BDto> list(String blist)
	{
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try 
		{
			String query;
			connection = dataSource.getConnection();
			if(blist == "list")
			{
				query = "select bNum, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent from mvc_board order by bGroup desc, bStep asc";
			}
			else
			{
				query = "select bNum, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent from mvc_vip_board order by bGroup desc, bStep asc";
			}
				
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
					
			
			while(resultSet.next())
			{
				int bNum = resultSet.getInt("bNum");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");

				BDto dto = new BDto(bNum, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				dtos.add(dto);
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
					if(resultSet != null) resultSet.close();
					if(preparedStatement != null) preparedStatement.close();
					if(connection != null) connection.close();
				} 
				catch (Exception e2) 
				{
					e2.printStackTrace();
				}
		}
		
		return dtos;
		
	}
	
//	public int getGrade(String uId)
//	{
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//		int uGrade = 0;
//		try 
//		{
//			connection = dataSource.getConnection();
//			
//			String query;
//				query = "select uGrade from user_info where uId = ?";
//
//			preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setString(1, uId);
//			resultSet = preparedStatement.executeQuery();
//
//			while(resultSet.next())
//			{
//				uGrade = resultSet.getInt(uGrade);
//			}
//		}
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		finally
//		{
//				try 
//				{
//					if(preparedStatement != null) preparedStatement.close();
//					if(connection != null) connection.close();
//					if(resultSet != null) resultSet.close();
//				} 
//				catch (Exception e2) 
//				{
//					e2.printStackTrace();
//				}
//		}
//		return uGrade;
//	}
	
	public int count_rows(String blist)
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int rows_num = 0;
		try 
		{
			connection = dataSource.getConnection();
			
			String query;
			if(blist == "list")
				query = "select * from mvc_board";
			else
				query = "select * from mvc_vip_board";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			resultSet.last();
			rows_num = resultSet.getRow();
			if(rows_num == 0)
				rows_num = 1;
			System.out.println(rows_num);
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
		return rows_num;
	}

}
