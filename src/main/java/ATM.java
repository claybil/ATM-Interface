/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author claybilladeau
 * pawprint: csb6bz
 */

import java.util.Scanner;

public class ATM
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);        
        Bank bank = new Bank("Bank of Billadeau");
 
        User curUser;
        while (true)
        {
            curUser = ATM.createAccount(bank, sc);
            curUser = ATM.mainMenuPrompt(bank, sc);            
            ATM.printUserMenu(curUser, sc);
        }
    }
    
    public static User createAccount(Bank bank, Scanner sc)
    {
        User user = null;
        String firstName;
        String lastName;
        String pin;
        System.out.println();
        System.out.println();
        System.out.println("Enter first name: ");
        firstName =  sc.nextLine();
        System.out.println("Enter last name: ");
        lastName = sc.nextLine();
        
        do
        {
            System.out.println("Enter 4-digit pin: ");
            pin = sc.nextLine();
            if (pin.length() != 4)
            {
                System.out.println("Invalid pin. Please try again.");
            }
        } while(pin.length() != 4);
 
        user = bank.addUser(firstName, lastName, pin);
        return user;
    }
    
    public static User mainMenuPrompt(Bank bank, Scanner sc)
    {
        String userID;
        String pin;
        User authUser;
        
        do
        {
            System.out.printf("\n\nWelcome to %s\n\n", bank.getName());
            System.out.println("Enter user ID: ");
            userID = sc.nextLine();
            System.out.println();
            System.out.println("Enter pin: ");
            pin = sc.nextLine();
            
            authUser = bank.userLogin(userID, pin);
            if (authUser == null)
            {
                System.out.println("Incorrect user ID/pin combination. Please try again.");
            }
            
        } while(authUser == null);
        
        return authUser;
    }
    
    public static void printUserMenu(User user, Scanner sc)
    {       
        int choice;
        
        do
        {
            user.printAccountsSummary();
            System.out.printf("Welcome %s, what would you like to do?\n", user.getFirstName());
            System.out.println("  1) Show account transaction history");
            System.out.println("  2) Withdraw");
            System.out.println("  3) Deposit");
            System.out.println("  4) Transfer");
            System.out.println("  5) Quit");
            System.out.println();
            System.out.println("Enter Choice: ");
            choice = sc.nextInt();
            
            if (choice < 1 || choice > 5)
            {
                System.out.println("Invalid choice. Please choose 1-5");
            }
        } while(choice < 1 || choice > 5);
        
        switch (choice)
        {
            case 1:
                ATM.showTransactionHistory(user, sc);
                break;
            case 2:
                ATM.withdrawFunds(user, sc);
                break;
            case 3:
                ATM.depositFunds(user, sc);
                break;
            case 4:
                ATM.transferFunds(user, sc);
                break;
            case 5:
                sc.nextLine();
                break;
        }
        
        if (choice != 5)
        {
            ATM.printUserMenu(user, sc);
        }                
    }
    
    public static void showTransactionHistory(User user, Scanner sc)
    {
        int account;
        
        do
        {
            System.out.println("Enter the number (1-" + user.numAccounts() +
                    ") of the account whose transactions you want to see: ");
            account = sc.nextInt()-1;
            if (account < 0 || account >= user.numAccounts())
            {
                System.out.println("Invalid account. Please try again");
            }
        } while (account < 0 || account >= user.numAccounts());
        
        user.printAccountTransactionHistory(account);
    }
    
    public static void transferFunds(User user, Scanner sc)
    {
        int fromAccount;
        int toAccount;
        double amount;
        double accountBal;
        
        do
        {
            System.out.println("Enter the number (1-" + user.numAccounts() + 
                    ") of the account to transfer from: ");
            fromAccount = sc.nextInt()-1;
            if (fromAccount < 0 || fromAccount >= user.numAccounts())
            {
                System.out.println("Invalid account. Please try again.");
            }
        } while (fromAccount < 0 || fromAccount >= user.numAccounts());
        
        accountBal = user.getAccountBalance(fromAccount);
        
        do
        {
            System.out.println("Enter the number (1-" + user.numAccounts() +
                    ") of the account to transfer to: ");
            toAccount = sc.nextInt()-1;
            if (toAccount < 0 || toAccount >= user.numAccounts())
            {
                System.out.println("Invalid account. Please try again.");
            }
        } while (toAccount < 0 || toAccount >= user.numAccounts());
        
        do
        {
            System.out.println("Enter the amount to transfer (max $" + accountBal + "): ");
            amount = sc.nextDouble();
            if (amount < 0)
            {
                System.out.println("Amount must be greater than zero.");
            }
            else if (amount > accountBal)
            {
                System.out.printf("Amount must not be greater than balance "
                        + "of $" + accountBal + ".\n");
            }
        } while (amount < 0 || amount > accountBal);
        
        user.addAccountTransaction(fromAccount, -1*amount, String.format(
                "Transfer to account %s", user.getAccountID(toAccount)));
        user.addAccountTransaction(toAccount, amount, String.format(
                "Transfer from account %s", user.getAccountID(fromAccount)));
    }
    
    public static void withdrawFunds(User user, Scanner sc)
    {
        int fromAccount;
        double amount;
        double accountBal;
        String memo;
        
        do
        {
            System.out.println("Enter the number (1-" + user.numAccounts() +
                    ") of the account to withdraw from: ");
            fromAccount = sc.nextInt()-1;
            if (fromAccount < 0 || fromAccount >= user.numAccounts())
            {
                System.out.println("Invalid account. Please try again.");
            }
        } while (fromAccount < 0 || fromAccount >= user.numAccounts());
        
        accountBal = user.getAccountBalance(fromAccount);
        
        do
        {
            System.out.println("Enter the amount to withdraw (max $" + accountBal + "): CLa");
            amount = sc.nextDouble();
            if (amount < 0)
            {
                System.out.println("Amount must be greater than zero.");
            }
            else if (amount > accountBal)
            {
                System.out.println("Amount must not be greater than balance "
                        + "of $" + accountBal + ".\n");
            }
        } while (amount < 0 || amount > accountBal);
        
        sc.nextLine();
        System.out.println("Enter a memo: ");
        memo = sc.nextLine();        
        user.addAccountTransaction(fromAccount, -1*amount, memo);
    }
    
    public static void depositFunds(User user, Scanner sc)
    {
        int toAccount;
        double amount;
        double accountBal;
        String memo;
        
        do
        {
            System.out.println("Enter the number (1-" + user.numAccounts() +
                    ") of the account to deposit in: ");
            toAccount = sc.nextInt()-1;
            if (toAccount < 0 || toAccount >= user.numAccounts())
            {
                System.out.println("Invalid account. Please try again.");
            }
        } while (toAccount < 0 || toAccount >= user.numAccounts());
        
        accountBal = user.getAccountBalance(toAccount);
        
        do
        {
            System.out.println("Enter the amount to deposit: ");
            amount = sc.nextDouble();
            if (amount < 0)
            {
                System.out.println("Amount must be greater than zero.");
            }
        } while (amount < 0);
        
        sc.nextLine();
        System.out.println("Enter a memo: ");
        memo = sc.nextLine();        
        user.addAccountTransaction(toAccount, amount, memo);
    }
    
}
