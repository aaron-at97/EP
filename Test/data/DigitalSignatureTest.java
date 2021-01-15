package data;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DigitalSignatureTest {
    static DigitalSignature ds;
    static DigitalSignature ds2;

    @BeforeAll
    static void init(){
        ds = new  DigitalSignature("Firma".getBytes());
        ds2 = new DigitalSignature("AAT".getBytes());

    }
    @Test
    void getSignatureTest(){

        assertEquals( Arrays.toString("Firma".getBytes()), Arrays.toString(ds.getFirma()));
        assertEquals(Arrays.toString("AAT".getBytes()), Arrays.toString(ds2.getFirma()));
        assertNotEquals(Arrays.toString("MBL".getBytes()),Arrays.toString(ds.getFirma()));
        assertNotEquals(Arrays.toString("OLI".getBytes()), Arrays.toString(ds2.getFirma()));
    }

    @Test
    void equalsTest(){
        DigitalSignature Equals = new DigitalSignature("Firma".getBytes());
        DigitalSignature Equals2 = new DigitalSignature("AAT".getBytes());

        assertEquals(ds,Equals);
        assertEquals(ds2,Equals2);
        assertNotEquals(ds,Equals2);
        assertNotEquals(ds2,Equals);
    }

    @Test
    void toStringTest(){
        String Equals= ("DigitalSignature Signature{" + "Signature: '" + Arrays.toString("Firma".getBytes()) + '\'' + '}');
        String Equals2= ("DigitalSignature Signature{" + "Signature: '" + Arrays.toString("AAT".getBytes()) + '\'' + '}');

        assertEquals(Equals, ds.toString());
        assertEquals(Equals2, ds2.toString());
        assertNotEquals(Equals, ds2.toString());
        assertNotEquals(Equals2, ds.toString());
    }
}
