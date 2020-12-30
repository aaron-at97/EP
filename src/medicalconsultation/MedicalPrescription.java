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
    private List<MedicalPrescriptionLine> listPres;
    // objeto inicializar preinscripcion
    private MedicalPrescriptionLine mdl;
    // Objeto TakingGuideline
    private TakingGuideline tg;


    public MedicalPrescription (int prescCode, TakingGuideline tg) {
        this.prescCode=prescCode;
        this.prescDate= new Date();
        this.tg = tg;
        this.listTaking = new ArrayList<>();
        this.listPres = new ArrayList<>();
    }
    public MedicalPrescription (int prescCode) {
        this.prescCode=prescCode;
    }

    // Makes some inicialization
    public void addLine(ProductID prodID, String[] instruc) throws  IncorrectTakingGuidelinesException {

        listTaking.add(tg);

        for (int i = 0; i < instruc.length; i++) {
            instruc[i]= String.valueOf(listTaking.get(i));
        }

        mdl = new MedicalPrescriptionLine(prodID,instruc);

    }

    public void modifyLine(ProductID prodID, String[] instruc) throws ProductNotInPrescription, IncorrectTakingGuidelinesException {
        mdl = new MedicalPrescriptionLine(prodID,instruc);
        listPres.add(mdl);
        boolean flag = false;
        for (int i = 0; i < listPres.size(); i++) {
            System.out.println (listPres.get(i));
            if (prodID.equals(listPres.get(i))) {
                listPres.set(i, mdl);
                flag=true;
            }
        }
        if (flag==false) {
            throw new ProductNotInPrescription(" No existe el producto ");
        }
    }
    public void removeLine(ProductID prodID) throws ProductNotInPrescription {
        boolean flag = false;
        for (int i = 0; i < listPres.size(); i++) {
            System.out.println (listPres.get(i));
            if (prodID.equals(listPres.get(i))) {
                listPres.remove(i);
                flag=true;
            }
        }
        if (flag==false) {
            throw new ProductNotInPrescription(" No existe el producto ");
        }
    }

//??? the gettersandsetters

}