package Control;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {
    @Test
    void testBank(){
        Bank bank = new Bank();
        int id = bank.openAccount("Saver", "John Doe", "London", 1000);
        assertEquals(0, bank.getAccount(id).checkBalance());
    }
}