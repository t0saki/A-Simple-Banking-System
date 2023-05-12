import java.util.ArrayList;

public class Bank {
    private ArrayList<BankAccount> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    public int openAccount(BankAccount account) {
        accounts.add(account);
        return 0;
    }

    public int closeAccount(int accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                accounts.remove(account);
                break;
            }
        }
        return 0;
    }

    public int operateOnAccounts(int operates) {
        for (BankAccount account : accounts) {
            if (account instanceof CurrentAccount) {
                CurrentAccount currentAccount = (CurrentAccount) account;
                if (currentAccount.getBalance() < 0) {
                    System.out.println("Current account " + currentAccount.getAccountNumber() + " is in overdraft.");
                }
            }
        }
        return 0;
    }
}
