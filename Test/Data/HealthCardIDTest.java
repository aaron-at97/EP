package Data;

import data.HealthCardID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class HealthCardIDTest {

    @Test
    public void getterTest() throws Exception {
        HealthCardID correct = new HealthCardID("ARTO1234567891");
        HealthCardID incorrect1 = new HealthCardID("ARTO12345678910"); // mas de 10 digitos
        HealthCardID incorrect2 = new HealthCardID("AAA1234567890"); // menos caracteres
        HealthCardID incorrect3 = new HealthCardID("1234567890AAAA"); //diferente orden
        HealthCardID incorrect4 = new HealthCardID("ARTOO1234567891"); //diferente orden
        HealthCardID incorrect5 = new HealthCardID("");

        Assertions.assertEquals(correct.getPersonalID(), "ARTO1234567891");
        Assertions.assertTrue(correct.CodeCardID());
        Assertions.assertFalse(incorrect1.CodeCardID());
        Assertions.assertFalse(incorrect2.CodeCardID());
        Assertions.assertFalse(incorrect3.CodeCardID());
        Assertions.assertFalse(incorrect4.CodeCardID());
        Assertions.assertFalse(incorrect5.CodeCardID());

    }



}