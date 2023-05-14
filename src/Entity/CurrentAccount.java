package Entity;

public class CurrentAccount extends BankAccount {
    private double overdraftLimit;
    private int type;

    public CurrentAccount(int accountNumber, String accountName, String location, double balance) {
        super(accountNumber, accountName, location, balance);
        overdraftLimit = 500;
        this.type = 1;
    }

    public String[] getHeader() {
        return new String[]{"accountNumber", "accountName", "location", "hashedPIN", "balance", "unClearedBalance", "suspended", "type", "overdraftLimit"};
    }

    public String[] getRecord() {
        return new String[]{String.valueOf(getAccountNumber()), getAccountName(), getLocation(), getHashedPIN(), String.valueOf(getBalance()), String.valueOf(getUnClearedBalance()), String.valueOf(isSuspended()), String.valueOf(getType()), String.valueOf(getOverdraftLimit())};
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public int setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
        return 0;
    }

    @Override
    public int withdraw(double amount) {
        if (amount > getBalance() + overdraftLimit) {
            System.out.println("Withdrawal failed: exceeded overdraft limit.");
            return 1;
        } else {
            return super.withdraw(amount);
        }
    }
}
