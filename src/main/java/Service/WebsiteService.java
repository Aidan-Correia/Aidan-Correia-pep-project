package Service;
import DAO.*;
import Model.*;

import static org.mockito.ArgumentMatchers.nullable;

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

    private boolean testValidAccount(Account account)
    {
        if (account.getUsername().length() < 1)
        {
            return false;
        }
        if (account.getPassword().length() < 4)
        {
            return false;
        }
        List<Account> accounts = accountDAO.retrieveAllAccounts();
        if (accounts == null)
        {
            return false;
        }
        for(Account a : accounts)
        {
            if (a.getUsername().equals(account.getUsername()))
            {
                return false;
            }
        }
        return true;
    }

    public Account registerAccount(Account account)
    {
        if (testValidAccount(account))
        {
            return accountDAO.insertAccount(account);
        }
        return null;
    }

    public Account login(Account account)
    {
        List<Account> accounts = accountDAO.retrieveAllAccounts();
        if (accounts == null)
        {
            return null;
        }
        for(Account a : accounts)
        {
            if (a.getUsername().equals(account.getUsername()) && (a.getPassword().equals(account.getPassword())))
            {
                return a;
            }
        }
        return null;
    }

    private boolean testValidMessage(Message message)
    {
        if ((message.getMessage_text().length() < 1) || (message.getMessage_text().length() > 255))
        {
            return false;
        }
        List<Account> accounts = accountDAO.retrieveAllAccounts();
        if (accounts == null)
        {
            return false;
        }
        for(Account a : accounts)
        {
            if (a.getAccount_id() == message.getPosted_by())
            {
                return true;
            }
        }
        return false;

    }

    public Message createMessage(Message message)
    {
        if (testValidMessage(message))
        {
            return messageDAO.insertMessage(message);
        }
        return null;
    }

    public List<Message> retrieveAllMessages()
    {
        return messageDAO.retrieveAllMessages();
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
