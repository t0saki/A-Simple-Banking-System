package Entity;

public class SaverAccount extends BankAccount {
    public int type = 1;

    public SaverAccount(int accountNumber, String accountName, String location, double balance) {
        super(accountNumber, accountName, location, balance);
    }

    public SaverAccount(int accountNumber) {
        super(accountNumber);
    }

    public int getType() {
        return type;
    }

    public int withdraw(double amount) {
        if (!suspended) {
            if (amount > balance) {
                System.out.println("Withdrawal failed: insufficient balance.");
                return 1;
            } else {
                unClearedBalance -= amount;
                return 0;
            }
        } else {
            System.out.println("Withdrawal failed: account is suspended.");
            return 1;
        }
    }
}
