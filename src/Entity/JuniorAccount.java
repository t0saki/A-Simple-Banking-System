package Entity;

public class JuniorAccount extends BankAccount {
    public int age;
    public final int MAX_WITHDRAWAL = 100;
    public double withdrawalToday = 0;
    public int type = 3;

    public JuniorAccount(int accountNumber, String accountName, String location, double balance, int age) {
        super(accountNumber, accountName, location, balance);
        this.age = age;
        if (this.age > 16) {
            System.out.println("Age is above 16, may not be a junior account.");
        }
    }

    public JuniorAccount(int accountNumber){
        super(accountNumber);
    }

    public String[] getHeader() {
        return new String[]{"accountNumber", "accountName", "location", "hashedPIN", "balance", "unClearedBalance", "suspended", "age", "withdrawalToday"};
    }

    public String[] getRecord() {
        return new String[]{String.valueOf(getAccountNumber()), getAccountName(), getLocation(), getHashedPIN(), String.format("%.2f", getBalance()), String.format("%.2f", getUnClearedBalance()), String.valueOf(isSuspended()), String.valueOf(getAge()), String.format("%.2f", getWithdrawalToday())};
    }

    public double getWithdrawalToday() {
        return withdrawalToday;
    }

    public int getAge() {
        return age;
    }

    public int clearWithdrawalToday() {
        withdrawalToday = 0;
        return 0;
    }

    @Override
    public int withdraw(double amount) {
        if (withdrawalToday + amount > MAX_WITHDRAWAL) {
            System.out.println("Withdrawal failed: amount exceeds maximum withdrawal limit.");
            return 1;
        } else {
            if (super.withdraw(amount) == 0) {
                withdrawalToday += amount;
                return 0;
            } else {
                return 1;
            }
        }
    }

    public int getType() {
        return type;
    }

    public int nextDay() {
        return clearWithdrawalToday();
    }
}
