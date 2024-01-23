/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author claybilladeau
 */

import java.util.ArrayList;
import java.util.Random;

public class Bank
{    
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;
    
    public Bank(String name)
    {
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }
    
    public String getNewUserID()
    {
        String id;
        Random num = new Random();
        int length = 6;
        boolean nonUnique;
        
        do
        {
            id = "";
            for (int c = 0; c < length; c++)
            {
                id += ((Integer)num.nextInt(10)).toString();
            }
            
            nonUnique = false;
            for (User u : this.users)
            {
                if (id.compareTo(u.getID()) == 0)
                {
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);
        
        return id;
    }
    
    public String getNewAccountID()
    {
        String id;
        Random num = new Random();
        int length = 10;
        boolean nonUnique;
        
        do
        {
            id = "";
            for (int c = 0; c < length; c++)
            {
                id += ((Integer)num.nextInt(10)).toString();
            }
            
            nonUnique = false;
            for (Account a : this.accounts)
            {
                if (id.compareTo(a.getID()) == 0)
                {
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);
        
        return id;       
    }
    
    public void addAccount(Account account)
    {
        this.accounts.add(account);
    }
    
    public User addUser(String firstName, String lastName, String pin)
    {
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);        
        Account newAccount = new Account("Savings", newUser, this);
        Account newAccount2 = new Account("Checking", newUser, this);
        newUser.addAccount(newAccount);
        newUser.addAccount(newAccount2);
        this.addAccount(newAccount);
        this.addAccount(newAccount2);
        
        return newUser;
    }
    
    public User userLogin(String userID, String pin)
    {
        for (User u : this.users)
        {            
            if (u.getID().compareTo(userID) == 0 && u.validatePin(pin))
            {
                return u;
            }
        }        
        return null;
    }
    
    public String getName()
    {
        return this.name;
    }
}
