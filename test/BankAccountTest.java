import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void testBankAccount() {
        BankAccount bankAccount = new BankAccount(1, "John Doe", 1000);
        assertEquals(1, bankAccount.getAccountNumber());
        assertEquals("John Doe", bankAccount.getAccountName());
        assertEquals(1000, bankAccount.getBalance());
        assertEquals(1, bankAccount.withdraw(2000));
        assertEquals(1000, bankAccount.getBalance());
    }
}