package medicalConsultation;



import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import medicalconsultation.*;
import medicalconsultation.exceptions.IncorrectTakingGuidelinesException;
import medicalconsultation.exceptions.ProductNotInPrescription;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class MedicalPrescriptionTest {


    MedicalPrescription mp;
    MedicalPrescription equal;
    static List<MedicalPrescriptionLine> lines, list;
    static HealthCardID hcID;
    static DigitalSignature sign;
    static TakingGuideline tg, tg2, tg3;
    static MedicalPrescriptionLine mpl, mpl2, mpd,mpl3,mp2;

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
    static void starting() throws Exception {
        hcID = new HealthCardID("BBBBBBBBKQ728364923473928463");
      //  sign = new DigitalSignature("AB".getBytes());

        tg = new TakingGuideline(dayMoment.AFTERMEALS, 7, "abc", 5, 4, FqUnit.HOUR);
        mpl = new MedicalPrescriptionLine(new ProductID("234736484763"), tg);
        //mp2 = new MedicalPrescriptionLine(new ProductID("234736484763"), tg);

        tg2 = new TakingGuideline(dayMoment.AFTERBREAKFAST, 7, "abc", 5, 4, FqUnit.DAY);
        mpl2 = new MedicalPrescriptionLine(new ProductID("182736484763"), tg2);
        //mpl3 = new MedicalPrescriptionLine(new ProductID("182736484763"), tg2);

        tg3 = new TakingGuideline(dayMoment.AFTERBREAKFAST, 7, "abc", 5, 4, FqUnit.DAY);
        mpd = new MedicalPrescriptionLine(new ProductID("772734584763"), tg3);
    }

    @Test
    void getMedicalPrescriptionLine() throws Exception{
        //System.out.println(mp);
        assertEquals(mpl, mp.getMedicalPrescriptionLine(new ProductID("234736484763")));
        assertNotEquals(mpl2, mp.getMedicalPrescriptionLine(new ProductID("234736484763")));
        assertThrows(ProductNotInPrescription.class, () -> {mp.getMedicalPrescriptionLine(new ProductID("999736484763"));});
    }

    @Test
    public void addLine() throws Exception {

        //ProductID productID = new ProductID("772734584763");
        //String[] instruct = new String[] {"BEFOREDINNER","46","vsjahd","45","2","DAY"};

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
        //System.out.println(mp.getMedicalPrescriptionLine(new ProductID("772734584763")));
        //System.out.println(mpd);


        assertEquals(mpd, mp.getMedicalPrescriptionLine(new ProductID("772734584763")));

        mp.addLine(productID2, instruct);
        assertNotEquals(mpd, mp.getMedicalPrescriptionLine(productID2));
        //System.out.println(mp);

        assertThrows(IncorrectTakingGuidelinesException.class, () -> {mp.addLine(productID, wrongLengthInstruct);});
        assertThrows(IncorrectTakingGuidelinesException.class, () -> {mp.addLine(productID, wrongParseInstruct);});
        assertThrows(IncorrectTakingGuidelinesException.class, () -> {mp.addLine(productID, wrongDayMoment);});
        assertThrows(IncorrectTakingGuidelinesException.class, () -> {mp.addLine(productID, wrongFQ);});
        assertThrows(IncorrectTakingGuidelinesException.class, () -> {mp.addLine(productID, emptyDayMoment);});
        assertThrows(IncorrectTakingGuidelinesException.class, () -> {mp.addLine(productID, emptyDose);});
        // System.out.println(ob.toString());

    }

    @Test
    public void modifyLine() throws Exception {

        ProductID productID = new ProductID("456789101123");
        TakingGuideline tg = new TakingGuideline(dayMoment.BEFOEMEALS, 656, "sadjsakdkjasbdkjbkasbkabskhdbkasjdvsjahd", 55, 6, FqUnit.HOUR);
        MedicalPrescription modify = new MedicalPrescription(1);
        TakingGuideline tg2 = new TakingGuideline(dayMoment.DURINGDINNER, 65, "asvsdvsbc", 5, 4, FqUnit.DAY);
        String[] instruct2 = new String[] {"DURINGDINNER","65","asvsdvsbc","5","4","DAY"};
        String[] instruct = new String[] {"AFTERBREAKFAST","7","sadjsakdkjasbdkjbkasbkabskhdbkasjdvsjahd","24","2","HOUR"};

        mp.addLine(productID, instruct);
        mp.modifyLine(productID, instruct2);

        MedicalPrescriptionLine expectedMod = new MedicalPrescriptionLine(new ProductID("456789101123"), tg2);

        assertEquals(expectedMod, mp.getMedicalPrescriptionLine(new ProductID("456789101123")));
        assertThrows(ProductNotInPrescription.class, () -> {mp.getMedicalPrescriptionLine(new ProductID("654616515"));});
       // assertThrows(IncorrectTakingGuidelinesException.class, () -> {mp.getMedicalPrescriptionLine(new ProductID("654616515"));});

    }

    @Test
    public void removeLine() throws Exception {

       /* ProductID productID = new ProductID("123456789101");
        ProductID productID1 = new ProductID("4");
        ProductID productID2 = new ProductID("1");
        ProductID productID3 = new ProductID("5");
        ProductID productID4 = new ProductID("9");

        TakingGuideline tg = new TakingGuideline(dayMoment.BEFOREDINNER, 46, "vsjahd", 45, 2, FqUnit.DAY);
        TakingGuideline tg1 = new TakingGuideline(dayMoment.AFTERBREAKFAST, 26, "4565sjahd", 15, 1, FqUnit.DAY);
        TakingGuideline tg2 = new TakingGuideline(dayMoment.AFTERDINNER, 66, "avdjashvdxsdafsdv", 65, 2, FqUnit.WEEK);
        TakingGuideline tg3 = new TakingGuideline(dayMoment.BEFOREBREAKFAST, 96, "aafsdffd", 525, 2, FqUnit.MONTH);
        TakingGuideline tg4 = new TakingGuideline(dayMoment.DURINGLUNCH, 56, "vsjahd", 25, 2, FqUnit.HOUR);

        MedicalPrescription modify = new MedicalPrescription(1);
        modify.addLine(productID, instrucGet(tg));
        modify.addLine(productID1, instrucGet(tg1));
        modify.addLine(productID2, instrucGet(tg2));
        modify.addLine(productID3, instrucGet(tg3));
        modify.addLine(productID4, instrucGet(tg4));
        modify.removeLine(productID2);*/
        MedicalPrescription expectedEmptyLines = new MedicalPrescription(123);

        //mp.addLine(new ProductID("234736484763"), instruct);
        //mp.addLine(new ProductID("182736484763"), instruct2);

        mp.removeLine(new ProductID("234736484763"));
        equal.removeLine(new ProductID("234736484763"));
        //System.out.println(mp);
        assertEquals(equal, mp);
        mp.removeLine(new ProductID("182736484763"));
        assertEquals(expectedEmptyLines,mp);
        String[] instruct = new String[] {"AFTERBREAKFAST","7","abc","5","4","DAY"};

       /* mp.addLine(new ProductID("123456879110"),instruct);
        mp.removeLine(new ProductID("123456879110"));
        mp.getMedicalPrescriptionLine(new ProductID("654616515"));*/
        assertThrows(ProductNotInPrescription.class, () -> {mp.getMedicalPrescriptionLine(new ProductID("123456879110"));});

    }

}