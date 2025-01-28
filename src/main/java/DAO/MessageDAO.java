package DAO;

import Model.Message;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    
    public Message insertMessage(Message message)
    {
        Connection connection = ConnectionUtil.getConnection();
        try {

            String sql = "INSERT INTO Message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?)" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());
            
            preparedStatement.executeUpdate();
            
            ResultSet keyResultSet = preparedStatement.getGeneratedKeys();
            if(keyResultSet.next()){
                int generated_id = (int) keyResultSet.getInt(1);
                message.setMessage_id(generated_id);
                return message;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
        return null;
    }

    public List<Message> retrieveAllMessages()
    {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messageList = new ArrayList<>();
        try
        {
            String sqlString = "SELECT * FROM Message";
            PreparedStatement ps = connection.prepareStatement(sqlString);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Message tempMessage = new Message(rs.getInt("message_id"),
                rs.getInt("posted_by"), rs.getString("message_text"), 
                rs.getLong("time_posted_epoch"));
                messageList.add(tempMessage);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }

        return messageList;

    }

    public Message getMessageById(int messageId)
    {
        Connection connection = ConnectionUtil.getConnection();
        Message foundMessage;
        try
        {
            String sqlString = "SELECT * FROM Message WHERE message_id = ?";
            PreparedStatement ps = connection.prepareStatement(sqlString);
            ps.setInt(1, messageId);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                foundMessage = new Message(rs.getInt("message_id"),
                rs.getInt("posted_by"), rs.getString("message_text"), 
                rs.getLong("time_posted_epoch"));
                return foundMessage;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }


        return null;

    }

    public Message deleteMessageById(int messageId)
    {
        Connection connection = ConnectionUtil.getConnection();
        Message foundMessage;
        try
        {
            String sqlDelete = "DELETE FROM Message WHERE message_id = ?";
            String sqlRetrieve = "SELECT * FROM Message WHERE message_id = ?";

            PreparedStatement ps = connection.prepareStatement(sqlRetrieve);
            ps.setInt(1, messageId);
            ResultSet rs = ps.executeQuery();

            ps = connection.prepareStatement(sqlDelete);
            ps.setInt(1, messageId);
            ps.executeUpdate();
            while(rs.next())
            {
                if (rs.getInt("message_id") == messageId)
                {
                    foundMessage = new Message(rs.getInt("message_id"),
                    rs.getInt("posted_by"), rs.getString("message_text"), 
                    rs.getLong("time_posted_epoch"));
                    return foundMessage;
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }


        return null;

    }

    public Message updateMessageById(int messageId, String newText)
    {
        Connection connection = ConnectionUtil.getConnection();
        Message updatedMessage;
        try
        {
            String sqlUpdate = "UPDATE Message SET message_text = ? WHERE message_id = ?";
            String sqlRetrieve = "SELECT * FROM Message WHERE message_id = ?";

            PreparedStatement ps = connection.prepareStatement(sqlUpdate);
            ps.setString(1, newText);
            ps.setInt(2, messageId);
            int found = ps.executeUpdate();
            if (found == 0)
            {
                return null;
            }

            ps = connection.prepareStatement(sqlRetrieve);
            ps.setInt(1, messageId);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                if (rs.getInt("message_id") == messageId)
                {
                    updatedMessage = new Message(rs.getInt("message_id"),
                    rs.getInt("posted_by"), rs.getString("message_text"), 
                    rs.getLong("time_posted_epoch"));
                    return updatedMessage;
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }


        return null;


    }

    public List<Message> retrieveAllMessagesByUser(int accountId)
    {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messageList = new ArrayList<>();
        try
        {
            String sqlString = "SELECT * FROM Message WHERE posted_by = ?";
            PreparedStatement ps = connection.prepareStatement(sqlString);
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Message tempMessage = new Message(rs.getInt("message_id"),
                rs.getInt("posted_by"), rs.getString("message_text"), 
                rs.getLong("time_posted_epoch"));
                messageList.add(tempMessage);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }


        return messageList;

    }
}
