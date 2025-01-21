package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    
    public Account insertAccount(Account account)
    {
        Connection connection = ConnectionUtil.getConnection();

        return null;
    }

    public List<Account> retrieveAllAccounts()
    {
        Connection connection = ConnectionUtil.getConnection();
        List<Account> accountList = new ArrayList<>();


        return accountList;

    }
}
