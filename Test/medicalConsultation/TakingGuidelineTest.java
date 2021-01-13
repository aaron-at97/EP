package medicalConsultation;

import medicalconsultation.FqUnit;
import medicalconsultation.TakingGuideline;
import medicalconsultation.dayMoment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TakingGuidelineTest {

private TakingGuideline tg;
private TakingGuideline tg1;

    @BeforeEach
    void init() throws Exception{
         tg = new TakingGuideline(dayMoment.AFTERBREAKFAST, 26, "4565sjahd", 15, 1, FqUnit.DAY);
         tg1 = new TakingGuideline(dayMoment.AFTERDINNER, 66, "avdjashvdxsdafsdv", 65, 2, FqUnit.WEEK);

    }

    @Test
    void equalsTest() {
        TakingGuideline equals = new TakingGuideline(dayMoment.AFTERBREAKFAST, 26, "4565sjahd", 15, 1, FqUnit.DAY);
        TakingGuideline equals1 = new TakingGuideline(dayMoment.AFTERDINNER, 66, "avdjashvdxsdafsdv", 65, 2, FqUnit.WEEK);

        assertEquals(tg, equals);
        assertEquals(tg1, equals1);
    }

    @Test
    void notEqualsTest() {
        TakingGuideline notEqual = new TakingGuideline(dayMoment.DURINGLUNCH, 56, "vsjahd", 25, 2, FqUnit.HOUR);
        TakingGuideline notEqual2 = new TakingGuideline();

        assertNotEquals(tg, notEqual);
        assertNotEquals(tg1, notEqual2);

    }


}
