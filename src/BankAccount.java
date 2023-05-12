public class BankAccount {
    private int accountNumber;
    private String accountName;
    private double balance;
    private boolean suspended;

    public BankAccount(int accountNumber, String accountName, double balance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = balance;
        this.suspended = false;
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

    public void deposit(double amount) {
        if (!suspended) {
            balance += amount;
        } else {
            System.out.println("Deposit failed: account is suspended.");
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
}
