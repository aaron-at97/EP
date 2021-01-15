package data;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductIDTest {

     static ProductID correct;
     static ProductID incorrect1;
     static ProductID incorrect2;
     static ProductID incorrect3;
     static ProductID incorrect4;

    @BeforeAll
    static void setupAll() {
        correct = new ProductID("123456789101");
        incorrect1 = new ProductID("");
        incorrect2 = new ProductID("12345678"); // menos de 12 digitos
        incorrect3 = new ProductID("123456789012345"); // mas de 12 digitos
        incorrect4 = new ProductID("ABCDEFGHIJKL"); //12 digitos caracteres
    }

    @Test
    public void getterProductID() throws Exception{

        Assertions.assertEquals(correct.getProductID(), "123456789101");
        assertThrows(Exception.class, () -> {incorrect1.getProductID();});
        assertThrows(Exception.class, () -> {incorrect2.getProductID();});
        assertThrows(Exception.class, () -> {incorrect3.getProductID();});
        assertThrows(Exception.class, () -> {incorrect4.getProductID();});

    }

    @Test
    public void erroresProductID() {

        Assertions.assertTrue(correct.CompProductCode());
        Assertions.assertFalse(incorrect1.CompProductCode());
        Assertions.assertFalse(incorrect2.CompProductCode());
        Assertions.assertFalse(incorrect3.CompProductCode());
        Assertions.assertFalse(incorrect4.CompProductCode());

    }

}