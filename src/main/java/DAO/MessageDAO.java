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



        return null;
    }

    public List<Message> retrieveAllMessages()
    {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messageList = new ArrayList<>();


        return messageList;

    }

    public Message getMessageById(int updateMessageById)
    {
        Connection connection = ConnectionUtil.getConnection();



        return null;

    }

    public Message deleteMessageById(int messageId)
    {
        Connection connection = ConnectionUtil.getConnection();


        
        return null;

    }

    public Message updateMessageById(int messageId)
    {
        Connection connection = ConnectionUtil.getConnection();


        
        return null;

    }

    public List<Message> retrieveAllMessagesByUser(int accountId)
    {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messageList = new ArrayList<>();


        return messageList;

    }
}
