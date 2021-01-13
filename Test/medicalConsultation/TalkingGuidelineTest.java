package medicalConsultation;

import medicalconsultation.FqUnit;
import medicalconsultation.TakingGuideline;
import medicalconsultation.dayMoment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class  TakingGuidelineTest {

private TakingGuideline tg;
    @BeforeEach
    void setup() throws Exception{
            tg = new TakingGuideline(dayMoment.AFTERMEALS, 6, "abc", 5, 4, FqUnit.HOUR);
            }

    @Test
    void getDayMoment() {
            assertEquals(tg.getDayMoment(), dayMoment.AFTERMEALS);
            assertNotEquals(tg.getDayMoment(), dayMoment.AFTERBREAKFAST);
            }

    @Test
    void getDuration() {
            assertEquals(tg.getDuration(), 6);
            assertNotEquals(tg.getDuration(), 7);
    }
}
