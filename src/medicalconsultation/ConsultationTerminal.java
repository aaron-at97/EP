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

    public ConsultationTerminal(){
    }

    public void initRevision() throws HealthCardException, NotValidePrescriptionException, ConnectException {
        try {
            hc = hcr.getHealthCardID();
            presc = sns.getePrescription(hc);
        }catch (ConnectException ce){
            throw new ConnectException();
        }
    }

    public void initPrescriptionEdition() throws AnyCurrentPrescriptionException, NotFinishedTreatmentException { /*. . . */}
    public void searchForProducts(String keyWord) throws AnyKeyWordMedicineException, ConnectException { /*. . .*/ }
    public void selectProduct(int option) throws AnyMedicineSearchException, ConnectException { /*. . . */}
    public void enterMedicineGuidelines(String[] instruc) throws AnySelectedMedicineException, IncorrectTakingGuidelinesException { /*. . .*/ }
    public void enterTreatmentEndingDate(Date date throws IncorrectEndingDateException {/* . . . */}
    public void sendePrescription() throws ConnectException, NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription {/* . . . */}
    public void printePresc() throws printingException {/* . . . */}

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
