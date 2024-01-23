/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author claybilladeau
 */

import java.util.ArrayList;

public class Account
{    
    private String name;
    private String id;
    private User user;
    private ArrayList<Transaction> transactions;
    
    public Account(String name, User user, Bank bank)
    {
        this.name = name;
        this.user = user;       
        this.id = bank.getNewAccountID();
        this.transactions = new ArrayList<Transaction>();    
    }
    
    public String getID()
    {
        return this.id;
    }
    
    public String getSummaryLine()
    {
        double balance = this.getBalance();
        
        if (balance >= 0)
        {
            return String.format("%s : $%.02f : %s", this.id, balance, this.name);              
        }
        else
        {
            return String.format("%s : $(%.02f) : %s", this.id, balance, this.name);
        }
    }
    
    public double getBalance()
    {
        double balance = 0;
        for (Transaction t : this.transactions)
        {
            balance += t.getAmount();
        }
        return balance;
    }
    
    public void printTransactionHistory()
    {
        System.out.printf("\nTransaction history for account %s\n", this.id);
        for (int t = this.transactions.size()-1; t >= 0; t--)
        {
            System.out.println(this.transactions.get(t).getSummaryLine());            
        }
        System.out.println();
    }
    
    public void addTransaction(double amount, String memo)
    {
        Transaction newTrans = new Transaction(amount, memo, this);
        this.transactions.add(newTrans);
    }
}
