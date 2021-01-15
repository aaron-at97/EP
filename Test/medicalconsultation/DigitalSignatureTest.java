package medicalconsultation;

import data.DigitalSignature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DigitalSignatureTest {
    private DigitalSignature ds;
    private DigitalSignature ds2;

    @BeforeEach
    void init(){
        ds = new  DigitalSignature(true);
        ds2 = new DigitalSignature(false);

    }
    @Test
    void getSignatureTest(){

        assertEquals(true, ds.getSignature());
        assertEquals(false, ds2.getSignature());
        assertNotEquals(false,ds.getSignature() );
        assertNotEquals(true, ds2.getSignature());
    }
    @Test
    void equalsTest(){
        DigitalSignature Equals = new DigitalSignature(true);
        DigitalSignature Equals2 = new DigitalSignature(false);

        assertEquals(ds,Equals);
        assertEquals(ds2,Equals2);
        assertNotEquals(ds,Equals2);
        assertNotEquals(ds2,Equals);
    }
    @Test
    void toStringTest(){
        String Equals= ("DigitalSignature Signature{" + "Signature: '" + true + '\'' + '}');
        String Equals2= ("DigitalSignature Signature{" + "Signature: '" + false + '\'' + '}');

        assertEquals(Equals, ds.toString());
        assertEquals(Equals2, ds2.toString());
        assertNotEquals(Equals, ds2.toString());
        assertNotEquals(Equals2, ds.toString());
    }
}
