import Entity.JuniorAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JuniorAccountTest {

    @Test
    void testWithdraw(){
        JuniorAccount junior1 = new JuniorAccount(1, "John Doe", "London", 1000, 15);
        junior1.withdraw(50);
        assertEquals(950, junior1.getBalance());
        junior1.withdraw(70);
        assertEquals(950, junior1.getBalance());
        junior1.clearWithdrawalToday();
        junior1.withdraw(70);
        assertEquals(880, junior1.getBalance());
    }
}