package medicalconsultation;


import data.*;
import data.exceptions.eSignatureException;
import medicalconsultation.exceptions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.*;
import services.exceptions.*;
import java.text.SimpleDateFormat;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;


public class ConsultationTerminalNullTest {

    static MedicalPrescription mp,mp2;
    static HealthCardID hcID;
    static SNSNull snsNull;
    static SVANull svaNull;
    static String[] instruct;
    static SimpleDateFormat actual;
    static ConsultationTerminal terminal;

    @BeforeAll
    static void inicializar() throws Exception {

        // Inicializamos las clases que implementan las interfaces las cuales disponemos en nulls
        svaNull = new SVANull();
        snsNull = new SNSNull();

        // Inicializamos todos los formatos de texto
        actual = new SimpleDateFormat("dd/MM/yyyy");
        instruct = new String[] {"AFTERBREAKFAST","","ghcgcgvh","","8"};
        Date data = new Date();
        DigitalSignature sign = new DigitalSignature("firma".getBytes());
        List<MedicalPrescriptionLine> listPres = new ArrayList<>();
        mp = new MedicalPrescription(123, actual.format(data), new Date(2020-1900, Calendar.FEBRUARY, 18), hcID, null, listPres);
        mp2 = new MedicalPrescription(123, null, null, hcID, sign, listPres);

    }

    @BeforeEach
    void actulizar () {

        terminal = new ConsultationTerminal(snsNull,svaNull);

    }
    @Test
    void initRevisionTest() {
        assertThrows(HealthCardException.class, () -> {terminal.initRevision();});
        ScheduledVisitAgenda schul = new ScheduledVisitAgenda() {
            @Override
            public HealthCardID getHealthCardID() {
                return new HealthCardID("ARTO728364923473");
            }
        };
        terminal.setHcr(schul);
        assertThrows(NotValidePrescriptionException.class, () -> {terminal.initRevision();});
    }

    @Test
    void initPrescriptionEditionTest()  {
        assertThrows(AnyCurrentPrescriptionException.class, () -> {terminal.initPrescriptionEdition();});
        terminal.setPresc(mp);
        terminal.getPresc().setEndDate(new Date(2025-1900, Calendar.MARCH, 12));
        assertThrows(NotFinishedTreatmentException.class, () -> {terminal.initPrescriptionEdition();});
    }

    @Test
    void searchForProductsTest() {
        assertThrows(AnyKeyWordMedicineException.class, () -> {terminal.searchForProducts("Ibuprofeno");});
        assertThrows(AnyKeyWordMedicineException.class, () -> {terminal.searchForProducts("");});

    }

    @Test
    void selectProductTest() {
        assertThrows(AnyMedicineSearchException.class, () -> {terminal.selectProduct(1);});
    }

    @Test
    void enterMedicineGuidelinesTest() {
        assertThrows(AnySelectedMedicineException.class, () -> {terminal.enterMedicineGuidelines(instruct);});
        terminal.setProductID(new ProductID("111111111111"));
        terminal.setPresc(mp2);
        assertThrows(IncorrectTakingGuidelinesException.class, () -> {terminal.enterMedicineGuidelines(instruct);});
    }

    @Test
    void enterTreatmentEndingDateTest() {
        assertThrows(IncorrectEndingDateException.class, () -> {terminal.enterTreatmentEndingDate(new Date(2018-1900, Calendar.OCTOBER, 15));});
    }

    @Test
    void sendePrescriptionTest() {

        assertThrows(NotValidePrescription.class, () -> {terminal.sendePrescription();});
        terminal.setPresc(mp);
        //
        assertThrows(eSignatureException.class, () -> {terminal.sendePrescription();});
        //
        terminal.setPresc(mp2);
        assertThrows(NotCompletedMedicalPrescription.class, () -> {terminal.sendePrescription();});
    }
    @Test
    void printPrescriptionTest() {

        assertThrows(printingException.class, () -> {terminal.printePresc();});
        terminal.setPresc(mp);
        assertDoesNotThrow(() -> {terminal.printePresc();});

    }

    private static class SNSNull implements HealthNationalService {


        @Override
        public MedicalPrescription getePrescription(HealthCardID hcID)  {
            return null;
        }

        @Override
        public List<ProductSpecification> getProductsByKW(String keyWord) {
            return null;
        }

        @Override
        public ProductSpecification getProductSpecific(int opt) {
            return null;
        }

        @Override
        public MedicalPrescription sendePrescription(MedicalPrescription ePresc) {
            return null;
        }
    }


    private static class SVANull implements ScheduledVisitAgenda {
        @Override
        public HealthCardID getHealthCardID()  {
            return null;
        }
    }

}