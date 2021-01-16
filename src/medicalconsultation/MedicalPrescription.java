package medicalconsultation;

import data.*;
import medicalconsultation.exceptions.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class MedicalPrescription { // A class that represents medical prescription
    private int prescCode=0;  // Igual a 0 control SNS
    private String prescDate;
    private Date endDate;
    private SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
    private HealthCardID hcID; // the healthcard ID of the patient
    private DigitalSignature eSign; // the eSignature of the doctor

    List<MedicalPrescriptionLine> listPres;
    private MedicalPrescriptionLine mdl;


    public MedicalPrescription (int prescCode, String prescDate, Date endDate, HealthCardID hcID, DigitalSignature eSign, List<MedicalPrescriptionLine> listPres) {
        this.prescCode=prescCode;
        this.prescDate=prescDate;
        this.endDate=endDate;
        this.hcID=hcID;
        this.eSign=eSign;
        this.listPres=listPres;
    }
    // For testing purpose

    public MedicalPrescription (int prescCode, String prescDate, Date endDate, HealthCardID hcID, DigitalSignature eSign) {
        this.prescCode = prescCode;
        this.prescDate = prescDate;
        this.endDate = endDate;
        this.hcID = hcID;
        this.eSign = eSign;
        this.listPres = new ArrayList<>();
    }
    public MedicalPrescription (int prescCode, List listPres) {
        this.prescCode=prescCode;
        this.prescDate=null;
        this.endDate=null;
        this.hcID=null;
        this.eSign=null;
        this.listPres = listPres;
    }

    public MedicalPrescription (int prescCode) {
        this.prescCode=prescCode;
        this.listPres = new ArrayList<>();
    }

    public MedicalPrescription () {
        this.listPres = new ArrayList<>();
    }

    public void addLine(ProductID prodID, String[] instruc) throws  IncorrectTakingGuidelinesException {
        if (!comprobacionTaking(instruc)){
            throw new IncorrectTakingGuidelinesException("Error: Itroduccion de lines incorrecta");
        }
        try {
            TakingGuideline tg = new TakingGuideline(dayMoment.getdayMoment(instruc[0]), Float.parseFloat(instruc[1]),
                    instruc[2], Float.parseFloat(instruc[3]), Float.parseFloat(instruc[4]), FqUnit.getFqUnit(instruc[5]));

            mdl = new MedicalPrescriptionLine(prodID, tg);
            listPres.add(new MedicalPrescriptionLine(prodID, tg));
        } catch (Exception e) {
            throw new IncorrectTakingGuidelinesException("Error: Itroduccion de lines incorrecta");
        }
    }

    public void modifyLine(ProductID prodID, String[] instruc) throws ProductNotInPrescription, IncorrectTakingGuidelinesException {
        if (!comprobacionTaking(instruc)){
            throw new IncorrectTakingGuidelinesException("Error: Itroduccion de lines incorrecta");
        }
        try {
            mdl = getMedicalPrescriptionLine(prodID);
            // Modificacion de Objeto MedicalPrescriptionLine
            TakingGuideline tg = new TakingGuideline(dayMoment.getdayMoment(instruc[0]), Float.parseFloat(instruc[1]),
                    instruc[2], Float.parseFloat(instruc[3]), Float.parseFloat(instruc[4]), FqUnit.getFqUnit(instruc[5]));
            mdl.setInstruc(tg);
        } catch (Exception e) {
            throw new IncorrectTakingGuidelinesException("Error: Itroduccion de lines incorrecta");
        }
    }

    public void removeLine(ProductID prodID) throws ProductNotInPrescription {

       mdl = getMedicalPrescriptionLine(prodID);
       listPres.remove(mdl);

    }

    public MedicalPrescriptionLine getMedicalPrescriptionLine(ProductID pd) throws ProductNotInPrescription{
        boolean flag = false;
        for (int i = 0; i < listPres.size(); i++) {
            if (pd.equals(listPres.get(i).getProdID())) {
                mdl= listPres.get(i);
                flag=true;
            }
        }
        if (!flag) {
            throw new ProductNotInPrescription(" No existe el producto ");
        }
        return mdl;
    }
    public Boolean comprobacionTaking(String[] instruc)  {
        if (instruc.length!=6) {
            return false;
        }
        if(dayMoment.getdayMoment(instruc[0]) == null || instruc[1].isEmpty() || instruc[2].isEmpty() ||
                instruc[3].isEmpty() || instruc[4].isEmpty() ||FqUnit.getFqUnit(instruc[5]) == null) {
            return false;
        }
        return true;
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
    public void setListPres(ArrayList<MedicalPrescriptionLine> listPres) {
        this.listPres = listPres;
    }
    public String getPrescDate() {
        return prescDate;
    }

    public void setPrescDate(String prescDate) {
        this.prescDate = prescDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalPrescription that = (MedicalPrescription) o;
        return prescCode == that.prescCode &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(prescDate, that.prescDate) &&
                Objects.equals(hcID, that.hcID) &&
                Objects.equals(eSign, that.eSign) &&
                Objects.equals(listPres, that.listPres);

    }

    @Override
    public int hashCode() {
        return Objects.hash(prescCode, prescDate, endDate, sdformat, hcID, eSign, listPres, mdl);
    }

    public void setPrescCode(int prescCode) {
        this.prescCode = prescCode;
    }

    public int getPrescCode() {
        return prescCode;
    }

    @Override
    public String toString() {
        return "MedicalPrescription{" +
                "prescCode=" + prescCode +
                ", prescDate='" + prescDate + '\'' +
                ", endDate=" + endDate +
                ", sdformat=" + sdformat +
                ", hcID=" + hcID +
                ", eSign=" + eSign +
                ", listPres=" + listPres +
                ", mdl=" + mdl +
                '}';
    }
}