package Data;

import data.ProductID;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

public class ProductIDTest {

    @Test
    public void getterProductID() throws Exception{
        ProductID correct = new ProductID("123456789101");
        ProductID incorrect1 = new ProductID("");
        ProductID incorrect2 = new ProductID("12345678"); // menos de 12 digitos
        ProductID incorrect3 = new ProductID("123456789012345"); // mas de 12 digitos
        ProductID incorrect4 = new ProductID("ABCDEFGHIJKL"); //12 digitos caracteres


        Assertions.assertEquals(correct.getProductID(), "123456789101");
        Assertions.assertTrue(correct.CompProductCode());
        Assertions.assertFalse(incorrect1.CompProductCode());
        Assertions.assertFalse(incorrect2.CompProductCode());
        Assertions.assertFalse(incorrect3.CompProductCode());
        Assertions.assertFalse(incorrect4.CompProductCode());

    }
}