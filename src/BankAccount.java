public class BankAccount {
    private int accountNumber;
    private String accountName;
    private String location;
    private int PIN;
    private double balance;
    private double unClearedBalance;
    private boolean suspended;

    public BankAccount(int accountNumber, String accountName, String location, double balance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.location = location;
        this.balance = balance;
        this.suspended = false;
        this.unClearedBalance = 0;
        generatePIN();
        System.out.println("Account created successfully, PIN: " + PIN);
    }

    private int generatePIN() {
        return PIN = (int) (Math.random() * 9000) + 1000;
    }

    public int getPIN() {
        return PIN;
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
            System.out.println("Balance: " + balance);
            return 0;
        } else {
            System.out.println("Check balance failed: account is suspended.");
            return 1;
        }
    }

    public void suspend() {
        suspended = true;
    }

    public void reinstate() {
        suspended = false;
    }

    public int closeAccount() {
        if (!suspended&&balance==0) {
            System.out.println("Account closed successfully.");
            return 0;
        } else {
            System.out.println("Close account failed: account is suspended or balance is not zero.");
            return 1;
        }
    }
}
