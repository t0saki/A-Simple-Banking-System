public class JuniorAccount extends BankAccount {
    private int age;
    private final int MAX_WITHDRAWAL = 100;
    private int withdrawalToday = 0;

    public JuniorAccount(int accountNumber, String accountName, double balance, int age) {
        super(accountNumber, accountName, balance);
        this.age = age;
        if (this.age > 16) {
            System.out.println("Age is above 16, may not be a junior account.");
        }
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
}
