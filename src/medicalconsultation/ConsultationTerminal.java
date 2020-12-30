package medicalconsultation;

import services.HealthNationalService;
import medicalconsultation.exceptions.*;
import data.exceptions.*;
import services.exceptions.*;
import java.net.ConnectException;
import java.util.*;
public class ConsultationTerminal implements HealthNationalService {


    // ???


    public void initRevision() { /*. . .*/ } throws HealthCardException, NotValidePrescriptionException, ConnectException;
    public void initPrescriptionEdition() { /*. . . */} throws AnyCurrentPrescriptionException, NotFinishedTreatmentException;
    public void searchForProducts(String keyWord) { /*. . .*/ } throws AnyKeyWordMedicineException, ConnectException;
    public void selectProduct(int option) { /*. . . */} throws AnyMedicineSearchException, ConnectException;
    public void enterMedicineGuidelines(String[] instruc) { /*. . .*/ }  AnySelectedMedicineException, IncorrectTakingGuidelinesException;
    public void enterTreatmentEndingDate(Date date) {/* . . . */} throws IncorrectEndingDateException;
    public void sendePrescription() {/* . . . */} throws ConnectException, NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription;
    public void printePresc() {/* . . . */} throws printingException;

    //??? Other methods, apart from the input events
}
