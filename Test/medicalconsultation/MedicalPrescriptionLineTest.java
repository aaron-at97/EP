package medicalconsultation;

import data.ProductID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicalPrescriptionLineTest {
    private TakingGuideline tg, tg2;
    private MedicalPrescriptionLine mpl;
    private MedicalPrescriptionLine mpl2;

    @BeforeEach
    void init(){

        tg = new TakingGuideline(dayMoment.AFTERMEALS, 7, "abc", 5, 4, FqUnit.HOUR);
        mpl = new MedicalPrescriptionLine(new ProductID("234736484763"), tg);
        tg2 = new TakingGuideline(dayMoment.AFTERBREAKFAST, 7, "abc", 5, 4, FqUnit.DAY);
        mpl2 = new MedicalPrescriptionLine(new ProductID("182736484763"), tg2);
    }

    @Test
    void equalsTest() {
        MedicalPrescriptionLine equals = new MedicalPrescriptionLine(new ProductID("234736484763"), tg);
        MedicalPrescriptionLine equals1 = new MedicalPrescriptionLine(new ProductID("182736484763"), tg2);

        assertEquals(mpl, equals);
        assertEquals(mpl2, equals1);
    }

    @Test
    void notEqualsTest() {
        MedicalPrescriptionLine notEqual = new MedicalPrescriptionLine(new ProductID("182736484761"), tg);
        MedicalPrescriptionLine notEqual2 = new MedicalPrescriptionLine(new ProductID("182736484765"), tg2);

        assertNotEquals(mpl, notEqual);
        assertNotEquals(mpl2, notEqual2);

    }
    @Test
    void getProdID() {
        assertEquals(mpl.getProdID(), new ProductID("234736484763"));
        assertEquals(mpl2.getProdID(), new ProductID("182736484763"));
    }

    @Test
    void setInstruc() {
        mpl.setInstruc(tg);
        mpl2.setInstruc(tg2);
        assertEquals(mpl.getInstruc(), tg);
        assertEquals(mpl2.getInstruc(), tg2);

    }
}