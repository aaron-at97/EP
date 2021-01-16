package medicalconsultation;


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
    static TakingGuideline tg, tg2, tg3;
    static MedicalPrescriptionLine mpl, mpl2, mpd;
    static ProductID productID2,productID;


    @BeforeAll
    static void init(){
        productID2 = new ProductID("111111111111");
        productID = new ProductID("123456789102");
        hcID = new HealthCardID("ARTO1234567891");
        tg = new TakingGuideline(dayMoment.BEFOREBREAKFAST, 15, "Test assssfaj", 2, 4, FqUnit.HOUR);
        mpl = new MedicalPrescriptionLine(productID2, tg); //234736484763
        tg2 = new TakingGuideline(dayMoment.BEFOREDINNER, 10, "Prueba test, Hola", 2, 8, FqUnit.DAY);
        mpl2 = new MedicalPrescriptionLine(productID, tg2); //182736484763
        tg3 = new TakingGuideline(dayMoment.AFTERDINNER, 66, "avdjashvdxsdafsdv", 3, 8, FqUnit.WEEK);
        mpd = new MedicalPrescriptionLine(new ProductID("123456789102"), tg3);

    }

    @BeforeEach
    void generaryVaciar() {
        lines = new ArrayList<>();
        list= new ArrayList<>();
        lines.add(mpl);
        lines.add(mpl2);
        list.add(mpl);
        list.add(mpl2);
        mp = new MedicalPrescription(123, lines);
        equal = new MedicalPrescription(123, list);
    }



    @Test
    public void addLine() throws Exception {


        String[] instruct = new String[] {"AFTERDINNER", "66", "avdjashvdxsdafsdv", "3", "8", "WEEK"};

        /// modificar valores y nombres de los String
        String[] incorrect = new String[] {"BEFOREBREAKFAST","4","5","4","HOUR"};
        String[] incorrect2 = new String[] {"AFTERBREAKFAST","AATT","Hola","5","4","DAY"};
        String[] incorrect3 = new String[] {"AFTERDINNER","3","Hola","5","9","CASA"};
        String[] incorrect4 = new String[] {"CASA","2","Hola","5","4","DAY"};
        String[] incorrect5 = new String[] {"BEFOREBREAKFAST","","Hola","5","2","MONTH"};


        mp.addLine(productID2, instruct);
        assertNotEquals(mpd, mp.buscarProducto(productID2));
        mp.addLine(productID, instruct);
        assertEquals(mpd, mp.buscarProducto(new ProductID("123456789102")));



        assertThrows(IncorrectTakingGuidelinesException.class, () -> {mp.addLine(productID, incorrect);});
        assertThrows(IncorrectTakingGuidelinesException.class, () -> {mp.addLine(productID, incorrect2);});
        assertThrows(IncorrectTakingGuidelinesException.class, () -> {mp.addLine(productID, incorrect3);});
        assertThrows(IncorrectTakingGuidelinesException.class, () -> {mp.addLine(productID, incorrect4);});
        assertThrows(IncorrectTakingGuidelinesException.class, () -> {mp.addLine(productID, incorrect5);});


    }

    @Test
    public void modifyLine() throws Exception {

        ProductID productID = new ProductID("123456789102");
        TakingGuideline tg2 = new TakingGuideline(dayMoment.DURINGDINNER, 65, "asvsdvsbc", 5, 4, FqUnit.DAY);
        String[] instruct2 = new String[] {"DURINGDINNER","65","asvsdvsbc","5","4","DAY"};
        String[] instruct = new String[] {"AFTERBREAKFAST","7","sadjsakdkjasbdkjbkasbkabskhdbkasjdvsjahd","24","2","HOUR"};

        mp.addLine(productID, instruct);
        mp.modifyLine(productID, instruct2);

        MedicalPrescriptionLine expectedMod = new MedicalPrescriptionLine(new ProductID("123456789102"), tg2);

        assertEquals(expectedMod, mp.buscarProducto(new ProductID("123456789102")));
        assertThrows(ProductNotInPrescription.class, () -> {mp.buscarProducto(new ProductID("321456789236"));});

    }

    @Test
    public void removeLine() throws Exception {


        MedicalPrescription expectedEmptyLines = new MedicalPrescription(123);

        mp.removeLine(productID2);
        equal.removeLine(productID2);

        assertEquals(equal, mp);
        mp.removeLine(productID);
        assertEquals(expectedEmptyLines,mp);
        String[] instruct = new String[] {"AFTERBREAKFAST","2","Hola","3","4","HOUR"};
        mp.addLine(new ProductID("987654321567"),instruct);
        mp.removeLine(new ProductID("987654321567"));

        assertThrows(ProductNotInPrescription.class, () -> {mp.buscarProducto(new ProductID("987654321567"));});

    }

}