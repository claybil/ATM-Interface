/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author claybilladeau
 */

import java.util.Date;

public class Transaction
{    
    private double amount;
    private Date timestamp;
    private String memo;
    private Account inAccount;
    
    public Transaction(double amount, Account account)
    {
        this.amount = amount;
        this.inAccount = account;
        this.timestamp = new Date();
        this.memo = "";
    }
    
    public Transaction(double amount, String memo, Account account)
    {
        this(amount, account);       
        this.memo = memo;
    }
    
    public double getAmount()
    {
        return this.amount;
    }
    
    public String getSummaryLine()
    {
        if (this.amount >= 0)
        {
            return String.format("%s : $%.02f : %s", this.timestamp.toString(), 
                    this.amount, this.memo);
        }
        else
        {
            return String.format("%s : $%.02f : %s", this.timestamp.toString(), 
                    this.amount, this.memo);
        }
    }    
}
