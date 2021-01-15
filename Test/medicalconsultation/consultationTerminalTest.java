
package medicalconsultation;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import data.exceptions.eSignatureException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.HealthNationalService;
import services.ScheduledVisitAgenda;
import services.exceptions.*;
import medicalconsultation.exceptions.*;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class consultationTerminalTest {

    static ConsultationTerminal ct;
    static HNSdoble hns;
    static HNSdobleNull hnsNull;
    static AgendaDoble agenda;
    static AgendaDobleNull agendaNull;
    static HealthCardID hcID;
    static MedicalPrescription mp;
    static String[] instruct;
    static List<ProductSpecification> expectedLastSearch;
    static SimpleDateFormat actual;

    @BeforeAll
    static void start() throws Exception {
        actual = new SimpleDateFormat("dd/MM/yyyy");
        ct = new ConsultationTerminal(new DigitalSignature("firma".getBytes()));
        hns = new HNSdoble();
        hnsNull = new HNSdobleNull();
        agenda = new AgendaDoble();
        agendaNull = new AgendaDobleNull();
        hcID = new HealthCardID("ARTO7283649234");
        instruct = new String[] {"AFTERBREAKFAST","5","ghcgcgvh","2","8","DAY"};

        expectedLastSearch = hns.getProductsByKW("");

        DigitalSignature sign = new DigitalSignature("firma".getBytes());

        TakingGuideline tg = new TakingGuideline(dayMoment.AFTERMEALS, 7, "abc", 5, 4, FqUnit.HOUR);
        MedicalPrescriptionLine mpl = new MedicalPrescriptionLine(new ProductID("234736484763"), tg);

        TakingGuideline tg2 = new TakingGuideline(dayMoment.AFTERBREAKFAST, 7, "abc", 5, 4, FqUnit.DAY);
        MedicalPrescriptionLine mpl2 = new MedicalPrescriptionLine(new ProductID("182736484763"), tg2);

        ArrayList<MedicalPrescriptionLine> lines = new ArrayList<>();
        lines.add(mpl);
        lines.add(mpl2);

        Date data = new Date();

        mp = new MedicalPrescription(123, actual.format(data), new Date(2020-1900, Calendar.JANUARY, 18), hcID, sign, lines);
    }

    @BeforeEach
    void setup() {
        ct = new ConsultationTerminal(new DigitalSignature("firma".getBytes()));
        ct.setSns(hns);
        ct.setHcr(agenda);
    }

    @Test
    void initRevision() throws Exception {
        ct.initRevision();
        assertEquals(hcID, ct.getHc());
        assertEquals(mp, ct.getPresc());

    }
    @Test
    void initRevision2()  {
        ct.setHcr(agendaNull);
        assertThrows(HealthCardException.class, () -> {ct.initRevision();});
        ct.setHcr(agenda);
        ct.setSns(hnsNull);
        assertThrows(NotValidePrescriptionException.class, () -> {ct.initRevision();});
    }

    @Test
    void initPrescriptionEdition() throws Exception{

        assertThrows(AnyCurrentPrescriptionException.class, () -> {ct.initPrescriptionEdition();});

        ct.initRevision();
       /* assertDoesNotThrow(() -> {ct.initPrescriptionEdition();});
        System.out.println(ct.toString());

        ct.getPresc().setEndDate(new Date(2022-1900, Calendar.FEBRUARY, 25));
        System.out.println(ct.getPresc().getEndDate());
        assertThrows(NotFinishedTreatmentException.class, () -> {ct.initPrescriptionEdition();});

        ConsultationTerminal term = new ConsultationTerminal(new DigitalSignature(true));
        term.setHcr(agendaNull);
        ct.setSns(hnsNull);
        //assertThrows(HealthCardException.class, () -> {ct.initRevision();});*/

    }

    @Test
    void searchForProducts() throws Exception{
        ct.initRevision();
        ct.searchForProducts("paracetamol");
        assertEquals(expectedLastSearch, ct.getListProduct());

        setup();
        ct.setSns(hnsNull);
        System.out.println(ct.getListProduct());
        assertThrows(AnyKeyWordMedicineException.class, () -> {ct.searchForProducts("paracetamol");});
    }

    @Test
    void selectProduct() throws Exception {

        ct.initRevision();
        ct.searchForProducts("paracetamol");
        ct.selectProduct(1);
        assertEquals(new ProductID("333333333333"), ct.getProductID());

        System.out.println(ct.getListProduct());

        //assertThrows(AnyMedicineSearchException.class, () -> {ct.selectProduct(1);});
    }

    @Test
    void selectProductNull() throws Exception {
        ct.initRevision();
        ct.searchForProducts("paracetamol");
        assertEquals(expectedLastSearch, ct.getListProduct());

        setup();
        ct.setSns(hnsNull);
        System.out.println(ct.getListProduct());
        assertThrows(AnyMedicineSearchException.class, () -> {ct.selectProduct(1);});
        //ct.initRevision();
        //ct.setSns(hnsNull);

        //start();
        System.out.println(ct.getListProduct());

//        ct.searchForProducts("paracetamol");
        //assertThrows(AnyMedicineSearchException.class, () -> {ct.selectProduct(1);});
    }

    @Test
    void enterMedicineGuidelines() throws Exception{
        assertThrows(AnySelectedMedicineException.class, () -> {ct.enterMedicineGuidelines(null);});

        setup();
        String[] instruct = new String[] {"AFTERBREAKFAST","7","abc","5","4","DAY"};

        ct.initRevision();
        ct.searchForProducts("paracetamol");
        ct.selectProduct(1);
        ct.enterMedicineGuidelines(instruct);

        mp.addLine(new ProductID("000736484763"), instruct);

        assertEquals(ct.getPresc(), mp);
        mp.removeLine(new ProductID("000736484763"));
    }

    @Test
    void enterTreatmentEndingDate() throws Exception{
        assertThrows(IncorrectEndingDateException.class, () -> {ct.enterTreatmentEndingDate(new Date(2019-1900, Calendar.DECEMBER, 15));});

        Date end = new Date(2021-1900, Calendar.DECEMBER, 30);
        Date data = new Date();

        ct.initRevision();
        ct.searchForProducts("paracetamol");
        ct.selectProduct(1);
        ct.enterMedicineGuidelines(instruct);
        ct.enterTreatmentEndingDate(end);

        assertEquals(end, ct.getPresc().getEndDate());
        assertEquals(actual.format(data), ct.getPresc().getPrescDate());

    }

    @Test
    void sendePrescription() throws Exception{
        ct.initRevision();
        ct.searchForProducts("paracetamol");
        ct.selectProduct(1);
        ct.enterMedicineGuidelines(instruct);
        ct.enterTreatmentEndingDate(new Date(2021-1900, Calendar.DECEMBER, 30));
        MedicalPrescription before = ct.getPresc();
        assertEquals(new DigitalSignature("firma".getBytes()) ,before.geteSign());

        ct.sendePrescription();
        MedicalPrescription after = ct.getPresc();
        assertEquals(new DigitalSignature("firma".getBytes()) ,after.geteSign());
        assertEquals(before, after);

        System.out.println();

        ct.initRevision();
        ct.searchForProducts("paracetamol");
        ct.selectProduct(1);
        ct.enterMedicineGuidelines(instruct);
        ct.enterTreatmentEndingDate(new Date(2021-1900, Calendar.DECEMBER, 30));
        ct.setSns(hnsNull);
        assertThrows(NotValidePrescription.class, () -> {ct.sendePrescription();});
    }

    private static class HNSdoble implements HealthNationalService {
        ProductID paracetamol;
        ProductID paracetamol2;
        ProductID paracetamol3;

        ProductSpecification par;
        ProductSpecification par2;
        ProductSpecification par3;

        @Override
        public MedicalPrescription getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException {
            return mp;
        }

        @Override
        public List<ProductSpecification> getProductsByKW(String keyWord) throws AnyKeyWordMedicineException, ConnectException {

            try {
                paracetamol = new ProductID("111111111111");
                paracetamol2 = new ProductID("222222222222");
                paracetamol3 = new ProductID("333333333333");
            } catch (Exception e) {
                e.printStackTrace();
            }

            par = new ProductSpecification("300mg", paracetamol, new Float("4.99"));
            par2 = new ProductSpecification("500mg", paracetamol2, new Float("6.99"));
            par3 = new ProductSpecification("1g", paracetamol3, new Float("8.99"));

            return new ArrayList<>(Arrays.asList(par, par2, par3));

        }

        @Override
        public ProductSpecification getProductSpecific(int opt) throws AnyMedicineSearchException, ConnectException {
            return par3;
        }

        @Override
        public MedicalPrescription sendePrescription(MedicalPrescription ePresc) throws ConnectException, NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription {
            return ePresc;
        }
    }

    private static class HNSdobleNull implements HealthNationalService {


        @Override
        public MedicalPrescription getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException {
            return null;
        }

        @Override
        public List<ProductSpecification> getProductsByKW(String keyWord) throws AnyKeyWordMedicineException, ConnectException {
            return null;
        }

        @Override
        public ProductSpecification getProductSpecific(int opt) throws AnyMedicineSearchException, ConnectException {
            return null;
        }

        @Override
        public MedicalPrescription sendePrescription(MedicalPrescription ePresc) throws ConnectException, NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription {
            return null;
        }
    }

    private static class AgendaDoble implements ScheduledVisitAgenda {
        @Override
        public HealthCardID getHealthCardID() throws HealthCardException {
            try {
                return new HealthCardID("ARTO728364923473");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private static class AgendaDobleNull implements ScheduledVisitAgenda {
        @Override
        public HealthCardID getHealthCardID() throws HealthCardException {
            return null;
        }
    }
}