package medicalconsultation;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import medicalconsultation.exceptions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class MedicalPrescriptionTest {

    MedicalPrescription mp;
    MedicalPrescription equal;
    List<MedicalPrescriptionLine> lines, list;
    static HealthCardID hcID;
    static DigitalSignature sign;
    static TakingGuideline tg, tg2, tg3;
    static MedicalPrescriptionLine mpl, mpl2, mpd;

    @BeforeEach
    void setup() {
        lines = new ArrayList<>();
        list= new ArrayList<>();
        lines.add(mpl);
        lines.add(mpl2);
        list.add(mpl);
        list.add(mpl2);
        mp = new MedicalPrescription(123, lines);
        equal = new MedicalPrescription(123, list);
    }

    @BeforeAll
    static void starting(){
        hcID = new HealthCardID("ARTO1234567891");
        tg = new TakingGuideline(dayMoment.AFTERMEALS, 7, "abc", 5, 4, FqUnit.HOUR);
        mpl = new MedicalPrescriptionLine(new ProductID("234736484763"), tg);
        tg2 = new TakingGuideline(dayMoment.AFTERBREAKFAST, 7, "abc", 5, 4, FqUnit.DAY);
        mpl2 = new MedicalPrescriptionLine(new ProductID("182736484763"), tg2);
        tg3 = new TakingGuideline(dayMoment.AFTERBREAKFAST, 7, "abc", 5, 4, FqUnit.DAY);
        mpd = new MedicalPrescriptionLine(new ProductID("772734584763"), tg3);
    }


    @Test
    public void addLine() throws Exception {

        ProductID productID2 = new ProductID("234736484763");
        ProductID productID = new ProductID("772734584763");
        String[] instruct = new String[] {"AFTERBREAKFAST","7","abc","5","4","DAY"};
        String[] wrongLengthInstruct = new String[] {"AFTERBREAKFAST","7","abc","5","4"};
        String[] wrongParseInstruct = new String[] {"AFTERBREAKFAST","a","abc","5","4","DAY"};
        String[] wrongDayMoment = new String[] {"ABC","7","abc","5","4","DAY"};
        String[] wrongFQ = new String[] {"AFTERBREAKFAST","7","abc","5","4","AA"};
        String[] emptyDayMoment = new String[] {"","7","abc","5","4","DAY"};
        String[] emptyDose = new String[] {"AFTERBREAKFAST","7","abc","","4","DAY"};

        mp.addLine(productID, instruct);
        assertEquals(mpd, mp.getMedicalPrescriptionLine(new ProductID("772734584763")));

        mp.addLine(productID2, instruct);
        assertNotEquals(mpd, mp.getMedicalPrescriptionLine(productID2));

        assertThrows(IncorrectTakingGuidelinesException.class, () -> {mp.addLine(productID, wrongLengthInstruct);});
        assertThrows(IncorrectTakingGuidelinesException.class, () -> {mp.addLine(productID, wrongParseInstruct);});
        assertThrows(IncorrectTakingGuidelinesException.class, () -> {mp.addLine(productID, wrongDayMoment);});
        assertThrows(IncorrectTakingGuidelinesException.class, () -> {mp.addLine(productID, wrongFQ);});
        assertThrows(IncorrectTakingGuidelinesException.class, () -> {mp.addLine(productID, emptyDayMoment);});
        assertThrows(IncorrectTakingGuidelinesException.class, () -> {mp.addLine(productID, emptyDose);});

    }

    @Test
    public void modifyLine() throws Exception {

        ProductID productID = new ProductID("456789101123");
        TakingGuideline tg2 = new TakingGuideline(dayMoment.DURINGDINNER, 65, "asvsdvsbc", 5, 4, FqUnit.DAY);
        String[] instruct2 = new String[] {"DURINGDINNER","65","asvsdvsbc","5","4","DAY"};
        String[] instruct = new String[] {"AFTERBREAKFAST","7","sadjsakdkjasbdkjbkasbkabskhdbkasjdvsjahd","24","2","HOUR"};

        mp.addLine(productID, instruct);
        mp.modifyLine(productID, instruct2);

        MedicalPrescriptionLine expectedMod = new MedicalPrescriptionLine(new ProductID("456789101123"), tg2);

        assertEquals(expectedMod, mp.getMedicalPrescriptionLine(new ProductID("456789101123")));
        assertThrows(ProductNotInPrescription.class, () -> {mp.getMedicalPrescriptionLine(new ProductID("654616515"));});

    }

    @Test
    public void removeLine() throws Exception {


        MedicalPrescription expectedEmptyLines = new MedicalPrescription(123);

        mp.removeLine(new ProductID("234736484763"));
        equal.removeLine(new ProductID("234736484763"));

        assertEquals(equal, mp);
        mp.removeLine(new ProductID("182736484763"));
        assertEquals(expectedEmptyLines,mp);
        String[] instruct = new String[] {"AFTERBREAKFAST","7","abc","5","4","DAY"};
        mp.addLine(new ProductID("654616515"),instruct);
        mp.removeLine(new ProductID("654616515"));

        assertThrows(ProductNotInPrescription.class, () -> {mp.getMedicalPrescriptionLine(new ProductID("654616515"));});

    }

}