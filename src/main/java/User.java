/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author claybilladeau
 */

import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User
{    
    private String firstName;
    private String lastName;
    private String id;
    private byte pinHash[];
    private ArrayList<Account> accounts;
    
    public User()
    {
        
    }
    
    public User(String firstName, String lastName, String pin, Bank bank)
    {    
        this.firstName = firstName;
        this.lastName = lastName;
        
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());                   
        }
        catch (NoSuchAlgorithmException e)
        {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }        
        this.id = bank.getNewUserID();        
        this.accounts = new ArrayList<Account>();
        System.out.printf("New user %s, %s with ID %s created.\n", lastName, firstName, this.id);
    }
    
    public void addAccount(Account account)
    {
        this.accounts.add(account);
    }
    
    public String getID()
    {
        return this.id;
    }
    
    public boolean validatePin(String pin)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(pin.getBytes()), this.pinHash);
        }
        catch (NoSuchAlgorithmException e)
        {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }        
        return false;
    }
    
    public String getFirstName()
    {
        return this.firstName;
    }
    
    public void printAccountsSummary() 
    {
        System.out.printf("\n\n%s's accounts summary\n", this.firstName);
        for (int a = 0; a < this.accounts.size(); a++)
        {
            System.out.printf("  %d) %s\n", a+1, this.accounts.get(a).getSummaryLine());
        }
        System.out.println();       
    }
    
    public int numAccounts()
    {
        return this.accounts.size();
    }
    
    public void printAccountTransactionHistory(int accountIndex)
    {
        this.accounts.get(accountIndex).printTransactionHistory();
    }
    
    public double getAccountBalance(int accountIndex)
    {
        return this.accounts.get(accountIndex).getBalance();
    }
    
    public String getAccountID(int accountIndex)
    {
        return this.accounts.get(accountIndex).getID();
    }
    
    public void addAccountTransaction(int accountIndex, double amount, String memo)
    {
        this.accounts.get(accountIndex).addTransaction(amount, memo);
    }
}
