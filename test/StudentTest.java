import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    @Test
    void testCreate() {
        Student student = new Student("Jane Smith");
        assertEquals("Jane Smith", student.getName());
        Student student2 = new Student("Tom Grey");
        assertEquals("Tom Grey", student2.getName());

        // Demostate failure
        assertEquals("Tom Grey", student.getName());
    }
}