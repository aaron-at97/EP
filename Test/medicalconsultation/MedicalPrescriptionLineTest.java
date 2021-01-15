package medicalconsultation;

import data.ProductID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicalPrescriptionLineTest {
    private TakingGuideline tg, tg2;
    private MedicalPrescriptionLine mpLine;
    private MedicalPrescriptionLine mpLine2;

    @BeforeEach
    void init(){

        tg = new TakingGuideline(dayMoment.BEFOREBREAKFAST, 15, "Test assssfaj", 2, 4, FqUnit.HOUR);
        mpLine = new MedicalPrescriptionLine(new ProductID("111111111111"), tg);
        tg2 = new TakingGuideline(dayMoment.BEFOREDINNER, 10, "Prueba test, Hola", 2, 8, FqUnit.DAY);
        mpLine2 = new MedicalPrescriptionLine(new ProductID("222222222222"), tg2);
    }

    @Test
    void equalsTest() {
        MedicalPrescriptionLine equals = new MedicalPrescriptionLine(new ProductID("111111111111"), tg);
        MedicalPrescriptionLine equals1 = new MedicalPrescriptionLine(new ProductID("222222222222"), tg2);

        assertEquals(mpLine, equals);
        assertEquals(mpLine2, equals1);
    }

    @Test
    void notEqualsTest() {
        MedicalPrescriptionLine notEqual = new MedicalPrescriptionLine(new ProductID("222222222222"), tg);
        MedicalPrescriptionLine notEqual2 = new MedicalPrescriptionLine(new ProductID("123456789102"), tg2);

        assertNotEquals(mpLine, notEqual);
        assertNotEquals(mpLine2, notEqual2);

    }
    @Test
    void getProdID() {
        assertEquals(mpLine.getProdID(), new ProductID("111111111111"));
        assertEquals(mpLine2.getProdID(), new ProductID("222222222222"));
    }

    @Test
    void setInstruc() {
        mpLine.setInstruc(tg);
        mpLine2.setInstruc(tg2);
        assertEquals(mpLine.getInstruc(), tg);
        assertEquals(mpLine2.getInstruc(), tg2);

    }
}