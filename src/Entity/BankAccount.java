package Entity;

import Control.BaseHandler;
import Control.RamdomIDGenerator;

import static Control.HashHandler.hashPassword;

public class BankAccount {
    public int accountNumber;
    public String accountName;
    public String location;
    public String hashedPIN;
    public int PIN;
    public double balance;
    public double unClearedBalance;
    public boolean suspended;
    public int type = 0;

    public BankAccount(int accountNumber, String accountName, String location, double balance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.location = location;
        this.balance = balance;
        this.suspended = false;
        this.unClearedBalance = 0;
        int PIN = generatePIN();
        System.out.println("Account created successfully, Account Number: " + accountNumber + ", PIN: " + PIN);
        this.hashedPIN = hashPassword(PIN);
        // appendFile();
    }

    public BankAccount(int accountNumber) {
        // load from file
        BaseHandler baseHandler = new BaseHandler();
        baseHandler.open("src\\Data\\BankAccount.csv");
        int lineCount = baseHandler.getLast("accountNumber", String.valueOf(accountNumber));
        if (lineCount == -1) {
            System.out.println("Account not found.");
        } else {
            this.accountNumber = accountNumber;
            this.accountName = baseHandler.getElement("accountName", lineCount);
            this.location = baseHandler.getElement("location", lineCount);
            this.hashedPIN = baseHandler.getElement("hashedPIN", lineCount);
            this.balance = Double.parseDouble(baseHandler.getElement("balance", lineCount));
            this.unClearedBalance = Double.parseDouble(baseHandler.getElement("unClearedBalance", lineCount));
            this.suspended = Boolean.parseBoolean(baseHandler.getElement("suspended", lineCount));
            this.type = Integer.parseInt(baseHandler.getElement("type", lineCount));
        }
    }

    public int updateFile() {
        BaseHandler baseHandler = new BaseHandler();
        baseHandler.open("src\\Data\\BankAccount.csv");
        int lineCount = baseHandler.getLast("accountNumber", String.valueOf(accountNumber));
        if (lineCount == -1) {
            System.out.println("Account not found.");
            return 1;
        } else {
            baseHandler.deleteRecord("accountNumber", String.valueOf(accountNumber));
            baseHandler.close();
            appendFile();
            return 0;
        }
    }

    public int appendFile() {
        BaseHandler baseHandler = new BaseHandler();
        baseHandler.open("src\\Data\\BankAccount.csv");
        String[] headerData = getHeader();
        String[] recordData = getRecord();
        baseHandler.addRecord(headerData, recordData);
        baseHandler.close();
        return 0;
    }

    public String[] getHeader() {
        return new String[] { "accountNumber", "accountName", "location", "hashedPIN", "balance", "unClearedBalance",
                "suspended", "type" };
    }

    public String[] getRecord() {
        return new String[] { String.valueOf(getAccountNumber()), getAccountName(), getLocation(), getHashedPIN(),
                String.format("%.2f", getBalance()), String.format("%.2f", getUnClearedBalance()),
                String.valueOf(isSuspended()), String.valueOf(getType()) };
    }

    public int getType() {
        return type;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public double getUnClearedBalance() {
        return unClearedBalance;
    }

    public String getHashedPIN() {
        return hashedPIN;
    }

    public int getPIN() {
        return PIN;
    }

    public int generatePIN() {
        return PIN = RamdomIDGenerator.generate(6);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public double getBalance() {
        return balance;
    }

    public String getLocation() {
        return location;
    }

    public int deposit(double amount) {
        if (!suspended) {
            unClearedBalance += amount;
            return 0;
        } else {
            System.out.println("Deposit failed: account is suspended.");
            return 1;
        }
    }

    public int clearFunds() {
        if (!suspended) {
            balance += unClearedBalance;
            unClearedBalance = 0;
            return 0;
        } else {
            System.out.println("Clear funds failed: account is suspended.");
            return 1;
        }
    }

    public int withdraw(double amount) {
        if (!suspended) {
            if (amount > balance) {
                System.out.println("Withdrawal failed: insufficient balance.");
                return 1;
            } else {
                balance -= amount;
                return 0;
            }
        } else {
            System.out.println("Withdrawal failed: account is suspended.");
            return 1;
        }
    }

    public int checkBalance() {
        if (!suspended) {
            System.out.println("Account Name: " + accountName);
            System.out.println("Account Number: " + accountNumber);
            System.out.println("Location: " + location);
            System.out.println("Balance: " + balance);
            return 0;
        } else {
            System.out.println("Check balance failed: account is suspended.");
            return 1;
        }
    }

    public void suspend() {
        suspended = !suspended;
    }

    public void reinstate() {
        suspended = false;
    }

    public int closeAccount() {
        if (!suspended && balance == 0) {
            BaseHandler baseHandler = new BaseHandler();
            baseHandler.open("src\\Data\\BankAccount.csv");
            int lineCount = baseHandler.getLast("accountNumber", String.valueOf(accountNumber));
            if (lineCount == -1) {
                System.out.println("Account not found.");
                baseHandler.close();
                return 1;
            } else {
                baseHandler.deleteRecord("accountNumber", String.valueOf(accountNumber));
                baseHandler.close();
            }
            System.out.println("Account closed successfully.");
            return 0;
        } else {
            System.out.println("Close account failed: account is suspended or balance is not zero.");
            return 1;
        }
    }

    public String getTypeByString() {
        int type = getType();
        return switch (type) {
            case 0 -> "Bank Account";
            case 1 -> "Current Account";
            case 2 -> "Saving Account";
            case 3 -> "Student Account";
            default -> "Unknown";
        };
    }

    public int nextDay() {
        clearFunds();
        return 0;
    }
}
