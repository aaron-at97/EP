package medicalconsultation;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import services.HealthNationalService;
import medicalconsultation.exceptions.*;
import data.exceptions.*;
import services.ScheduledVisitAgenda;
import services.exceptions.*;
import java.net.ConnectException;
import java.util.*;
public class ConsultationTerminal {

    private HealthCardID hc;
    private ScheduledVisitAgenda hcr;
    private HealthNationalService sns;
    private MedicalPrescription presc;
    private List<ProductSpecification> listProduct = new ArrayList<>();
    private ProductID productID;
    private final Date today = new Date();
    private ProductSpecification producEsp;
    private DigitalSignature eSign;

    public ConsultationTerminal(DigitalSignature eSign) throws Exception {
        this.eSign = eSign;
    }

    public void initRevision() throws HealthCardException, NotValidePrescriptionException, ConnectException {
        try {
            hc = hcr.getHealthCardID();
            if(hc == null){
                throw new HealthCardException("El paciente no tiene visita o no se encuentra registrado en la agenda de visitas concertadas");
            }
            presc = sns.getePrescription(hc);
            if(presc == null) {
                throw new NotValidePrescriptionException("El paciente no tiene prescripcion");
            }
        }
        catch (ConnectException ce){
            throw new ConnectException();
        }
    }

    public void initPrescriptionEdition() throws AnyCurrentPrescriptionException, NotFinishedTreatmentException {
        if(presc == null) {
            throw new AnyCurrentPrescriptionException("El paciente no tiene prescripcion");
        }
        if (presc.getEndDate().after(today)) {
            throw new NotFinishedTreatmentException("Tratamiento no finalizado");
        }

    }

    public void searchForProducts(String keyWord) throws AnyKeyWordMedicineException, ConnectException {
        try {
            listProduct = sns.getProductsByKW(keyWord);
            if (listProduct==null) {
                throw new AnyKeyWordMedicineException("");
            }
        }
        catch (ConnectException ce){
            throw new ConnectException();
        }
    }
    public void selectProduct(int option) throws AnyMedicineSearchException, ConnectException {
        try {
            if (listProduct!= null) {
                producEsp = sns.getProductSpecific(option);
            } else {
                throw new AnyMedicineSearchException("No se a realizado una busqueda previa");
            }
            productID = producEsp.getProductCode();
        }
        catch (ConnectException ce){
            throw new ConnectException();
        }
    }
    public void enterMedicineGuidelines(String[] instruc) throws AnySelectedMedicineException, IncorrectTakingGuidelinesException {
        if (productID== null) {
            throw new AnySelectedMedicineException("");
        }
        presc.addLine(productID, instruc);
        MedicalPrescriptionLine mdl = presc.getListPres().get(presc.getListPres().size()-1);
    }
    public void enterTreatmentEndingDate(Date date) throws IncorrectEndingDateException{
        if(date.before(today)) {
            throw new IncorrectEndingDateException("");
        }
        presc.setEndDate(date);
        presc.setPrescDate(today);
    }
    public void sendePrescription() throws ConnectException, NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription {

        try {
            presc.seteSign(eSign);
            presc = sns.sendePrescription(presc);
            if (presc == null) {
                throw new NotValidePrescription("La prescripcion no es valida");
            }
            if (presc.geteSign()==null) {
                throw new eSignatureException("No dispone de Firma Digital");
            }
            if (presc.getPrescDate() == null || presc.getEndDate() == null) {
                throw new NotCompletedMedicalPrescription("NO inicializado Medical Prescription");
            }
        }
        catch (ConnectException ce){
            throw new ConnectException();
        }

    }
    public void printePresc() throws printingException {
        if (presc == null) {
            throw new printingException("");
        }
        System.out.println(presc);
    }

}