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
    // private ArrayList<BankAccount> accounts;
    // private HashMap<Integer, BankAccount> accounts;

    public Bank() {
        // accounts = new HashMap<>();
    }

    public int[] openAccount(String type, String accountName, String location, double balance) {
        int accountNumber = RamdomIDGenerator.generate(6);
        while (getAccount(accountNumber) != null) {
            accountNumber = RamdomIDGenerator.generate(6);
        }
        BankAccount tmpAccount;
        if (type.equals("Saver")) {
            tmpAccount = new SaverAccount(accountNumber, accountName, location, balance);
        } else if (type.equals("Current")) {
            tmpAccount = new CurrentAccount(accountNumber, accountName, location, balance);
        } else {
            System.out.println("Account type not supported or input error.");
            return new int[]{-1, -1};
        }
        tmpAccount.appendFile();
        return new int[]{accountNumber, tmpAccount.getPIN()};
    }

    public int[] openAccount(String type, String accountName, String location, double balance, int age) {
        int accountNumber = RamdomIDGenerator.generate(6);
        while (getAccount(accountNumber) != null) {
            accountNumber = RamdomIDGenerator.generate(6);
        }
        BankAccount tmpAccount;
        if (type.equals("Junior")) {
            tmpAccount = new JuniorAccount(accountNumber, accountName, location, balance, age);
        } else {
            System.out.println("Account type not supported or input error.");
            return new int[]{-1, -1};
        }
        tmpAccount.appendFile();
        return new int[]{accountNumber, tmpAccount.getPIN()};
    }

    public int closeAccount(int accountNumber) {
        BankAccount tmpAccount = getAccount(accountNumber);
        if (tmpAccount != null) {
            if (tmpAccount.isSuspended()) {
                System.out.println("Account is suspended, please contact the bank.");
                return 1;
            }
            return tmpAccount.closeAccount();
        } else {
            System.out.println("Account not found.");
            return 1;
        }
    }

    public BankAccount getAccount(int accountNumber) {
        BaseHandler baseHandler = new BaseHandler();
        baseHandler.open("src\\Data\\BankAccount.csv");
        int lineCount = baseHandler.getLast("accountNumber", String.valueOf(accountNumber));
        if (lineCount == -1) {
            System.out.println("Account not found.");
            return null;
        } else {
            int type = Integer.parseInt(baseHandler.getElement("type", lineCount));
            switch (type) {
                case 1 -> {
                    return new SaverAccount(accountNumber);
                }
                case 2 -> {
                    return new CurrentAccount(accountNumber);
                }
                case 3 -> {
                    return new JuniorAccount(accountNumber);
                }
                default -> {
                    System.out.println("Account type not supported.");
                    return null;
                }
            }
        }
    }

    public int checkLogin(int accountNumber, int PIN) {
        System.out.println("checkLogin");
        BaseHandler baseHandler = new BaseHandler();
        baseHandler.open("src\\Data\\BankAccount.csv");
        int lineCount = baseHandler.getLast("accountNumber", String.valueOf(accountNumber));
        if (lineCount == -1) {
            baseHandler.close();
            JDialog dialog = new JDialog();
            dialog.setAlwaysOnTop(true);
            // JOptionPane.showMessageDialog(dialog, "Account number not found.");
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
            // JOptionPane.showMessageDialog(dialog, "Wrong PIN.");
            return 2;
        }
    }
}
