package medicalconsultation;

import data.*;
import java.util.*;
import medicalconsultation.exceptions.*;

public class MedicalPrescription { // A class that represents medical prescription
    private int prescCode;
    private Date prescDate;
    private Date endDate;
    private HealthCardID hcID; // the healthcard ID of the patient
    private DigitalSignature eSign; // the eSignature of the doctor
    //??? // Its components, that is, the set of medical prescription lines
    private boolean isClosed; // flag to know if IncorrectTakingGuidelinesException
    private List<MedicalPrescriptionLine> listPres;
    private MedicalPrescriptionLine mdl;

    public MedicalPrescription () { /*. . .*/ } // Makes some inicialization
    public void addLine(ProductID prodID, String[] instruc) throws  IncorrectTakingGuidelinesException {
        if(isClosed){
            throw new IncorrectTakingGuidelinesException("The sale is closed");
        }
        mdl = new MedicalPrescriptionLine(prodID,instruc);
        listPres.add(mdl);
    }
    public void modifyLine(ProductID prodID,String[] instruc) throws ProductNotInPrescription, IncorrectTakingGuidelinesException {/* . . . */}
    public void removeLine(ProductID prodID) throws ProductNotInPrescription {/* . . . */}

    //??? the gettersandsetters

}