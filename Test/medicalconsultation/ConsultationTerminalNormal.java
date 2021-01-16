package medicalconsultation;


import data.*;
import medicalconsultation.exceptions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.*;
import java.text.SimpleDateFormat;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;


public class ConsultationTerminalNormal {
    static ProductSpecification epecificacion;
    static ProductSpecification epecificacion1;
    static MedicalPrescription mp,mp2;
    static HealthCardID hcID;
    static SNSTest snsTest;
    static SVATest svaTest;
    static String[] instruct;
    static SimpleDateFormat actual;
    static ConsultationTerminal terminal;
    static List<MedicalPrescriptionLine> listPres;
    static List<ProductSpecification> listProd;
    @BeforeAll
    static void inicializar() throws Exception {

        // Inicializamos las clases que implementan las interfaces las cuales disponemos en nulls
        svaTest = new SVATest();
        snsTest = new SNSTest();
        hcID = new HealthCardID("ARTO728364923473");
        // Inicializamos todos los formatos de texto
        actual = new SimpleDateFormat("dd/MM/yyyy");
        instruct = new String[] {"AFTERBREAKFAST","20","abskhdbkasjdvsjahd","2","2","HOUR"};
        Date data = new Date();
        DigitalSignature sign = new DigitalSignature("firma".getBytes());

        TakingGuideline tg = new TakingGuideline(dayMoment.AFTERMEALS, 7, "abc", 5, 4, FqUnit.HOUR);
        MedicalPrescriptionLine mpl = new MedicalPrescriptionLine(new ProductID("234736484763"), tg);

        TakingGuideline tg2 = new TakingGuideline(dayMoment.AFTERBREAKFAST, 7, "abc", 5, 4, FqUnit.DAY);
        MedicalPrescriptionLine mpl2 = new MedicalPrescriptionLine(new ProductID("182736484763"), tg2);

        List<MedicalPrescriptionLine> listPres = new ArrayList<>();
        listPres.add(mpl);
        listPres.add(mpl2);
        ProductID ibuprofeno = new ProductID("111111111111");
        ProductID  ibuprofeno2 = new ProductID("222222222222");

        epecificacion = new ProductSpecification("150mg", ibuprofeno, 3);
        epecificacion1 = new ProductSpecification("250mg", ibuprofeno2, 4);

        listProd = new ArrayList<>();
        listProd.add(epecificacion);
        mp = new MedicalPrescription(123, actual.format(data), new Date(2020-1900, Calendar.FEBRUARY, 18), hcID, null, listPres);
        mp2 = new MedicalPrescription(123, null, null, hcID, sign, listPres);

    }

    @BeforeEach
    void actulizar () {

        terminal = new ConsultationTerminal(snsTest,svaTest);

    }
    @Test
    void initRevision() throws Exception {
        terminal.initRevision();
        assertEquals(hcID, terminal.getHc());
        assertEquals(mp, terminal.getPresc());

    }

    @Test
    void searchForProducts() throws Exception{
        terminal.initRevision();
        terminal.searchForProducts("paracetamol");
        assertEquals(listProd, terminal.getListProduct());

    }

    @Test
    void selectProduct() throws Exception {

        terminal.initRevision();
        terminal.searchForProducts("paracetamol");
        terminal.selectProduct(1);
        assertEquals(new ProductID("111111111111"), terminal.getProductID());

    }

    @Test
    void enterMedicineGuidelines() throws Exception{
        assertThrows(AnySelectedMedicineException.class, () -> {terminal.enterMedicineGuidelines(null);});
        terminal.initRevision();
        terminal.searchForProducts("paracetamol");
        terminal.selectProduct(1);
        terminal.enterMedicineGuidelines(instruct);
        mp.addLine(new ProductID("444444444444"), instruct);
        assertEquals(terminal.getPresc(), mp);
    }

    @Test
    void enterTreatmentEndingDate() throws Exception{
        assertThrows(IncorrectEndingDateException.class, () -> {terminal.enterTreatmentEndingDate(new Date(2019-1900, Calendar.DECEMBER, 15));});

        Date end = new Date(2021-1900, Calendar.DECEMBER, 30);
        Date data = new Date();

        terminal.initRevision();
        terminal.searchForProducts("ibuprofeno");
        terminal.selectProduct(1);
        terminal.enterMedicineGuidelines(instruct);
        terminal.enterTreatmentEndingDate(end);

        assertEquals(end, terminal.getPresc().getEndDate());
        assertEquals(actual.format(data), terminal.getPresc().getPrescDate());

    }

    private static class SNSTest implements HealthNationalService {



        @Override
        public MedicalPrescription getePrescription(HealthCardID hcID)  {
            return mp; // retorno Medical Prescription nos facilita retornar la lista
        }

        @Override
        public List<ProductSpecification> getProductsByKW(String keyWord) {
            return listProd;

        }

        @Override
        public ProductSpecification getProductSpecific(int opt) {
            return epecificacion;
        }

        @Override
        public MedicalPrescription sendePrescription(MedicalPrescription ePresc) {
            return ePresc;
        }
    }


    private static class SVATest implements ScheduledVisitAgenda {
        @Override
        public HealthCardID getHealthCardID()  {
            return new HealthCardID("ARTO728364923473");
        }
    }

}