package Entity;

public class CurrentAccount extends BankAccount {
    public double overdraftLimit;
    public int type = 2;

    public CurrentAccount(int accountNumber, String accountName, String location, double balance) {
        super(accountNumber, accountName, location, balance);
        overdraftLimit = 500;
    }

    public CurrentAccount(int accountNumber) {
        super(accountNumber);
        overdraftLimit = 500;
    }

    public String[] getHeader() {
        return new String[]{"accountNumber", "accountName", "location", "hashedPIN", "balance", "unClearedBalance", "suspended", "type", "overdraftLimit"};
    }

    public String[] getRecord() {
        return new String[]{String.valueOf(getAccountNumber()), getAccountName(), getLocation(), getHashedPIN(), String.format("%.2f", getBalance()), String.format("%.2f", getUnClearedBalance()), String.valueOf(isSuspended()), String.valueOf(getType()), String.format("%.2f", getOverdraftLimit())};
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public int setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
        return 0;
    }

    public int withdraw(double amount) {
        if (amount > getBalance() + overdraftLimit) {
            System.out.println("Withdrawal failed: exceeded overdraft limit.");
            return 1;
        } else {
            if (!suspended) {
                balance -= amount;
                return 0;
            } else {
                System.out.println("Withdrawal failed: account is suspended.");
                return 2;
            }
        }
    }

    public int getType() {
        return type;
    }
}
