package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    
    public Account insertAccount(Account account)
    {
        Connection connection = ConnectionUtil.getConnection();
        try {

            String sql = "INSERT INTO Account (username, password) VALUES (?, ?)" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            
            preparedStatement.executeUpdate();

            ResultSet keyResultSet = preparedStatement.getGeneratedKeys();
            if(keyResultSet.next()){
                int generated_id = (int) keyResultSet.getInt(1);
                account.setAccount_id(generated_id);
                return account;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
        return null;
    }

    public List<Account> retrieveAllAccounts()
    {
        Connection connection = ConnectionUtil.getConnection();
        List<Account> accountList = new ArrayList<>();
        try
        {
            String sqlString = "SELECT * FROM Account";
            PreparedStatement ps = connection.prepareStatement(sqlString);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Account tempAccount = new Account(rs.getInt("account_id"),
                rs.getString("username"), rs.getString("password"));
                accountList.add(tempAccount);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }

        return accountList;

    }
}
