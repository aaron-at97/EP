package medicalconsultation;

import data.ProductID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductSpecificationTest {
    private ProductSpecification ps1;
    private ProductSpecification ps2;

    @BeforeEach
    void init(){
        ps1 = new ProductSpecification ("Paracetamol", new ProductID("123655890734"), 10);
        ps2 = new ProductSpecification ("Cortisona", new ProductID("983450123496"), (float) 15.9);
    }
    @Test
    void equalsTest(){
      ProductSpecification equals = new ProductSpecification("Paracetamol", new ProductID("123655890734"), 10);
      ProductSpecification equals1 = new ProductSpecification("Cortisona", new ProductID("983450123496"), (float) 15.9);

      assertEquals(ps1,equals);
      assertEquals(ps2,equals1);
    }
    @Test
    void nonEqualsTest(){
        ProductSpecification nonEqual = new ProductSpecification("Paracetamol", new ProductID("1236558907349"), 10);
        ProductSpecification nonEqual1 = new ProductSpecification("Cortisona", new ProductID("983450123496"), (float) 15.7);
        ProductSpecification nonEqual2 = new ProductSpecification("Ibuprofeno", new ProductID("123655890734"), 10);
        ProductSpecification nonEqual3 = new ProductSpecification("Cortisona", new ProductID("983450123490"), (float) 15.9);

        assertNotEquals(ps1, nonEqual);
        assertNotEquals(ps1, nonEqual2);
        assertNotEquals(ps2,nonEqual1);
        assertNotEquals(ps2,nonEqual3);
    }
    @Test
    void getProductCodeTest(){
        assertEquals(ps1.getProductCode(),new ProductID("123655890734"));
        assertNotEquals(ps1.getProductCode(),new ProductID("127655890734"));
        assertEquals(ps2.getProductCode(), new ProductID("983450123496"));
        assertNotEquals(ps2.getProductCode(), new ProductID("983450123957"));
    }
    @Test
    void getDescriptionTest(){
        assertEquals(ps1.getDescription(), "Paracetamol");
        assertNotEquals(ps1.getDescription(), "Paracatamol");
        assertEquals(ps2.getDescription(), "Cortisona");
        assertNotEquals(ps2.getDescription(), "Cortusona");
    }

    @Test
    void setDescriptionTest(){
        ps1.setDescription("Ibuprofeno");
        ps2.setDescription("Paracetamol");

        assertEquals(ps1.getDescription(),"Ibuprofeno");
        assertNotEquals(ps1.getDescription(),"Paracetamol");
        assertEquals(ps2.getDescription(),"Paracetamol");
        assertNotEquals(ps2.getDescription(), "Cortisona");
    }

    @Test
    void getPriceTest(){
        assertEquals(ps1.getPrice(), 10);
        assertNotEquals(ps1.getPrice(), 11);
        assertEquals(ps2.getPrice(), (float) 15.9);
        assertNotEquals(ps2.getPrice(), (float)15.7);
    }

    @Test
    void setPriceTest(){
        ps1.setPrice((float)10.5);
        ps2.setPrice(17);

        assertEquals(ps1.getPrice(), (float) 10.5);
        assertNotEquals(ps1.getPrice(), 10);
        assertEquals(ps2.getPrice(), 17);
        assertNotEquals(ps2.getPrice(), (float) 15.9);
    }

}
