package Control;

import Entity.BankAccount;
import Entity.CurrentAccount;
import Entity.JuniorAccount;
import Entity.SaverAccount;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

import static Control.HashHandler.checkPassword;

public class Bank {
    //    private ArrayList<BankAccount> accounts;
    private HashMap<Integer, BankAccount> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public int openAccount(String type, String accountName, String location, double balance) {
        int accountNumber = RamdomIDGenerator.generate(6);
        while (accounts.containsKey(accountNumber)) {
            accountNumber = RamdomIDGenerator.generate(6);
        }
        BankAccount tmpAccount = null;

        if (type.equals("Saver")) {
            tmpAccount = new SaverAccount(accountNumber, accountName, location, balance);
        } else if (type.equals("Current")) {
            tmpAccount = new CurrentAccount(accountNumber, accountName, location, balance);
        }else {
            System.out.println("Account type not supported or input error.");
            return -1;
        }
        accounts.put(accountNumber, tmpAccount);
        return accountNumber;
    }

    public int openAccount(String type, String accountName, String location, double balance, int age) {
        int accountNumber = RamdomIDGenerator.generate(6);
        while (accounts.containsKey(accountNumber)) {
            accountNumber = RamdomIDGenerator.generate(6);
        }
        if (type.equals("Junior")) {
            BankAccount tmpAccount = new JuniorAccount(accountNumber, accountName, location, balance, age);
            accounts.put(accountNumber, tmpAccount);

        } else {
            System.out.println("Account type not supported or input error.");
            return -1;
        }
        return accountNumber;
    }

    public int closeAccount(int accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            getAccount(accountNumber).closeAccount();
            accounts.remove(accountNumber);
        } else {
            System.out.println("Account not found.");
            return 1;
        }
        return 0;
    }

    public BankAccount getAccount(int accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            return accounts.get(accountNumber);
        } else {
            System.out.println("Account not found.");
            return null;
        }
    }

    public int checkLogin(int accountNumber, int PIN) {
        System.out.println("checkLogin");
        BaseHandler baseHandler = new BaseHandler();
        baseHandler.open("src\\Data\\BankAccount.csv");
        int lineCount = baseHandler.getFirstRowIndexByHeaderAndVal("accountNumber", String.valueOf(accountNumber));
        if (lineCount == -1) {
            baseHandler.close();
            JDialog dialog = new JDialog();
            dialog.setAlwaysOnTop(true);
//            JOptionPane.showMessageDialog(dialog, "Account number not found.");
            return 1;
        }
        String hashedPIN = baseHandler.getElement("hashedPIN", lineCount);
        if (checkPassword(PIN, hashedPIN)) {
            baseHandler.close();
            return 0;
        } else {
            baseHandler.close();
            JDialog dialog = new JDialog();
            dialog.setAlwaysOnTop(true);
//            JOptionPane.showMessageDialog(dialog, "Wrong PIN.");
            return 2;
        }
    }
}
