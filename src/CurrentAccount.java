public class CurrentAccount extends BankAccount {
    private double overdraftLimit;

    public CurrentAccount(int accountNumber, String accountName, String location, double balance) {
        super(accountNumber, accountName, location, balance);
        overdraftLimit = 500;
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
