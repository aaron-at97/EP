package medicalconsultation;

import data.*;

import medicalconsultation.exceptions.*;
import java.util.*;

public class MedicalPrescription { // A class that represents medical prescription
    private int prescCode;
    private Date prescDate;
    private Date endDate;
    private HealthCardID hcID; // the healthcard ID of the patient
    private DigitalSignature eSign; // the eSignature of the doctor

    //??? // Its components, that is, the set of medical prescription lines

    private List<TakingGuideline> listTaking;
    List<MedicalPrescriptionLine> listPres;
    // objeto inicializar preinscripcion
    private MedicalPrescriptionLine mdl;
    // Objeto TakingGuideline
    //private TakingGuideline tg;




    public MedicalPrescription (int prescCode, Date prescDate, Date endDate, HealthCardID hcID, DigitalSignature eSign) {
        this.prescCode=prescCode;
        this.prescDate=prescDate;
        this.endDate=endDate;
        this.hcID=hcID;
        this.eSign=eSign;
    }
    public MedicalPrescription (int prescCode) {
        this.prescCode=prescCode;
        this.listPres = new ArrayList<>();
    }

    // Makes some inicialization
    public void addLine(ProductID prodID, String[] instruc) throws  IncorrectTakingGuidelinesException {

        mdl = new MedicalPrescriptionLine(prodID,instruc);
        listPres.add(mdl);
    }

    public void modifyLine(ProductID prodID, String[] instruc) throws ProductNotInPrescription, IncorrectTakingGuidelinesException {
        listPres.add(mdl);
        boolean flag = false;
        for (int i = 0; i < listPres.size(); i++) {
            if (prodID.equals(listPres.get(i).getProdID())) {
                mdl.setInstruc(instruc);
                flag=true;
            }
        }
        if (!flag) {
            throw new ProductNotInPrescription(" No existe el producto ");
        }
    }
    public void removeLine(ProductID prodID) throws ProductNotInPrescription {

        listPres.add(mdl);
        boolean flag = false;
        for (int i = 0; i < listPres.size(); i++) {
            if (prodID.equals(listPres.get(i).getProdID())) {
                listPres.remove(i);
                flag=true;
            }
        }
        if (!flag) {
            throw new ProductNotInPrescription(" No existe el producto ");
        }
    }

    public DigitalSignature geteSign() {
        return eSign;
    }

    public void seteSign(DigitalSignature eSign) {
        this.eSign = eSign;
    }

    public List<MedicalPrescriptionLine> getListPres() {
        return listPres;
    }
    public void setListPres(List<MedicalPrescriptionLine> listPres) {
        this.listPres = listPres;
    }
    public Date getPrescDate() {
        return prescDate;
    }

    public void setPrescDate(Date prescDate) {
        this.prescDate = prescDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}