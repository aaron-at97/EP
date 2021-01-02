package medicalconsultation;

import data.HealthCardID;
import services.HealthNationalService;
import medicalconsultation.exceptions.*;
import data.exceptions.*;
import services.ScheduledVisitAgenda;
import services.exceptions.*;
import java.net.ConnectException;
import java.util.*;
public class ConsultationTerminal implements HealthNationalService {

    private HealthCardID hc;
    private ScheduledVisitAgenda hcr;
    private HealthNationalService sns;
    private MedicalPrescription presc;
    private List<ProductSpecification> listProduct = new ArrayList<>();
    private ProductSpecification producEsp;

    public ConsultationTerminal(){

    }

    public void initRevision() throws HealthCardException, NotValidePrescriptionException, ConnectException {
        try {
            hc = hcr.getHealthCardID();
            if(hc == null){
                throw new HealthCardException("El paciente no tiene visita o no se encuentra registrado en la agenda de visitas concertadas");
            }
            presc = sns.getePrescription(hc);
        }
        catch (ConnectException | NotValidePrescriptionException ce){
            throw new ConnectException();
        }
    }

    public void initPrescriptionEdition() throws AnyCurrentPrescriptionException, NotFinishedTreatmentException {

    }
    public void searchForProducts(String keyWord) throws AnyKeyWordMedicineException, ConnectException {
        try {
            listProduct = sns.getProductsByKW(keyWord);
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
        }
        catch (ConnectException ce){
            throw new ConnectException();
        }
    }
    public void enterMedicineGuidelines(String[] instruc) throws AnySelectedMedicineException, IncorrectTakingGuidelinesException {

    }
    public void enterTreatmentEndingDate(Date date throws IncorrectEndingDateException{

    }
    public void sendePrescription() throws ConnectException, eSignatureException, NotCompletedMedicalPrescription, HealthCardException, NotValidePrescriptionException {
        try {
            if (sns.getePrescription(hc) == null) {
                throw new NotValidePrescriptionException("La prescripcion no es valida");
            }
        }
        catch (ConnectException ce){
            throw new ConnectException();
        }
    }
    public void printePresc() throws printingException {

    }

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
