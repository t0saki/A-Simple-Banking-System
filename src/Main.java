public class Main {
    public static void main(String[] args) {
        JuniorAccount junior1 = new JuniorAccount(1, "John Doe", "London", 1000, 15);
        junior1.checkBalance();
        junior1.withdraw(50);
        junior1.checkBalance();
        junior1.withdraw(70);
        junior1.checkBalance();
        junior1.clearWithdrawalToday();
        junior1.withdraw(70);
        junior1.checkBalance();
    }
}
