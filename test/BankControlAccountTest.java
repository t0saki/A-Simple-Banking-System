import Entity.BankAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankControlAccountTest {

    @Test
    void testBankAccount() {
        BankAccount bankAccount = new BankAccount(1, "John Doe", "London", 1000);
        assertEquals(1, bankAccount.getAccountNumber());
        assertEquals("John Doe", bankAccount.getAccountName());
        assertEquals(1000, bankAccount.getBalance());
        assertEquals(1, bankAccount.withdraw(2000));
        assertEquals(1000, bankAccount.getBalance());
        assertEquals(0, bankAccount.deposit(1000));
        assertEquals(1000, bankAccount.getBalance());
        assertEquals(0, bankAccount.clearFunds());
        assertEquals(2000, bankAccount.getBalance());
    }
}