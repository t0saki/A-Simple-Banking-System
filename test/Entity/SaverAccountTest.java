package Entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaverAccountTest {
    @Test
    void testType(){
        SaverAccount saverAccount = new SaverAccount(1, "Tim", "Singapore", 1000);
        assertEquals(1, saverAccount.getType());
    }

}