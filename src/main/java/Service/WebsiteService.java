package Service;
import DAO.*;
import Model.*;
import java.util.ArrayList;
import java.util.List;

public class WebsiteService {
    AccountDAO accountDAO;
    MessageDAO messageDAO;

    public WebsiteService()
    {
        accountDAO = new AccountDAO();
        messageDAO = new MessageDAO();
    }

    public Account registerAccount(Account account)
    {
        return null;
    }

    public Account login(Account account)
    {
        return null;
    }

    public Message createMessage(Message message)
    {
        return null;
    }

    public List<Message> retrieveAllMessages()
    {
        return null;
    }

    public Message retrieveMessage(int messageId)
    {
        return null;
    }

    public Message deleteMessage(int messageId)
    {
        return null;
    }

    public Message updateMessage(int messageId)
    {
        return null;
    }

    public List<Message> retrieveMessagesByUser(int userId)
    {
        return null;
    }

}
