package medicalconsultation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PosologyTest {
    private Posology p;
    private Posology p2;

    @BeforeEach
    void init() {
        p = new Posology(5, 6, FqUnit.DAY);
        p2 = new Posology(7, 8, FqUnit.HOUR);

    }

    @Test
    void equalsTest() {
        Posology equals = new Posology(5, 6, FqUnit.DAY);
        Posology equals1 = p2 = new Posology(7, 8, FqUnit.HOUR);

        assertEquals(p, equals);
        assertEquals(p2, equals1);
    }
    @Test
    void notEqualsTest() {
        Posology notEquals = new Posology(4, 6, FqUnit.DAY);
        Posology notEqualsEmpty = new Posology();

        assertNotEquals(p, notEquals);
        assertNotEquals(p2, notEqualsEmpty);

    }
}