import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaverAccountTest {
    @Test
    void testSaverAccount() {
        SaverAccount saverAccount = new SaverAccount(1, "John Doe", "London", 1000);
        assertEquals(1, saverAccount.getAccountNumber());
        assertEquals("John Doe", saverAccount.getAccountName());
        assertEquals(1000, saverAccount.getBalance());
        assertEquals(1, saverAccount.withdraw(2000));
        assertEquals(1000, saverAccount.getBalance());
        assertEquals(0, saverAccount.deposit(1000));
        assertEquals(1000, saverAccount.getBalance());
        assertEquals(0, saverAccount.clearFunds());
        assertEquals(2000, saverAccount.getBalance());
        assertEquals(1, saverAccount.closeAccount());
    }
}