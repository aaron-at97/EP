package data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class HealthCardIDTest {

    static HealthCardID correct;
    static HealthCardID incorrect1;
    static HealthCardID incorrect2;
    static HealthCardID incorrect3;
    static HealthCardID incorrect4;
    static HealthCardID incorrect5;


    @BeforeAll
    static void setupAll() {
         correct = new HealthCardID("ARTO1234567891");
         incorrect1 = new HealthCardID("ARTO12345678910"); // mas de 10 digitos
         incorrect2 = new HealthCardID("AAA1234567890"); // menos caracteres
         incorrect3 = new HealthCardID("ARTOO1234567891"); //mas caracteres
         incorrect4 = new HealthCardID("1234567890AAAA"); //diferente orden
         incorrect5 = new HealthCardID("");
    }

    @Test
    void getterTest() throws Exception {

        Assertions.assertEquals(correct.getPersonalID(), "ARTO1234567891");
        Assertions.assertTrue(correct.CodeCardID());
        Assertions.assertFalse(incorrect1.CodeCardID());
        Assertions.assertFalse(incorrect2.CodeCardID());
        Assertions.assertFalse(incorrect3.CodeCardID());
        Assertions.assertFalse(incorrect4.CodeCardID());
        Assertions.assertFalse(incorrect5.CodeCardID());

    }
}