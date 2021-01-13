package data;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductIDTest {

     ProductID correct;
     ProductID incorrect1;
     ProductID incorrect2;
     ProductID incorrect3;
     ProductID incorrect4;

    @BeforeEach
    void setupAll() {
        correct = new ProductID("123456789101");
        incorrect1 = new ProductID("");
        incorrect2 = new ProductID("12345678"); // menos de 12 digitos
        incorrect3 = new ProductID("123456789012345"); // mas de 12 digitos
        incorrect4 = new ProductID("ABCDEFGHIJKL"); //12 digitos caracteres
    }

    @Test
    public void getterProductID() throws Exception{

        Assertions.assertEquals(correct.getProductID(), "123456789101");
        Assertions.assertTrue(correct.CompProductCode());
        Assertions.assertFalse(incorrect1.CompProductCode());
        Assertions.assertFalse(incorrect2.CompProductCode());
        Assertions.assertFalse(incorrect3.CompProductCode());
        Assertions.assertFalse(incorrect4.CompProductCode());

    }
}