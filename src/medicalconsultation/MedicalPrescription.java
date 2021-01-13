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
    private TakingGuideline tg;

    public MedicalPrescription (int prescCode, Date prescDate, Date endDate, HealthCardID hcID, DigitalSignature eSign, List<MedicalPrescriptionLine> listPres) {
        this.prescCode=prescCode;
        this.prescDate=prescDate;
        this.endDate=endDate;
        this.hcID=hcID;
        this.eSign=eSign;
        this.listPres=listPres;
    }
    // For testing purpose

    public MedicalPrescription (int prescCode, Date prescDate, Date endDate, HealthCardID hcID, DigitalSignature eSign) {
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
        this.prescDate=null;
        this.endDate=null;
        this.hcID=null;
        this.eSign=null;
        this.listPres = new ArrayList<>();
    }

    public MedicalPrescription () {
        this.listPres = new ArrayList<>();
    }

    // Makes some inicialization
    public void addLine(ProductID prodID, String[] instruc) throws  IncorrectTakingGuidelinesException {
       if(instruc.length != 6 || dayMoment.getdayMoment(instruc[0]) == null || instruc[1].isEmpty() || instruc[2].isEmpty() ||
                instruc[3].isEmpty() || instruc[4].isEmpty() ||FqUnit.getFqUnit(instruc[5]) == null) {
            throw new IncorrectTakingGuidelinesException(" Error: Itroduccion de lines incorrecta");
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
        if(instruc.length != 6 || dayMoment.getdayMoment(instruc[0]) == null || instruc[1].isEmpty() || instruc[2].isEmpty() ||
                instruc[3].isEmpty() || instruc[4].isEmpty() ||FqUnit.getFqUnit(instruc[5]) == null) {
            throw new IncorrectTakingGuidelinesException(" Error: Itroduccion de lines incorrecta");
        }
        // Al llamar a la funcion getMedicalPrescription estamos comprobando la excepcion ProductNotInPrescription
        mdl = getMedicalPrescriptionLine(prodID);
        // Modificacion de Objeto MedicalPrescriptionLine
        TakingGuideline tg = new TakingGuideline(dayMoment.getdayMoment(instruc[0]), Float.parseFloat(instruc[1]),
                instruc[2], Float.parseFloat(instruc[3]), Float.parseFloat(instruc[4]), FqUnit.getFqUnit(instruc[5]));
        mdl.setInstruc(tg);

        // Este for solo actualiza la lista, para no tener duplicados en la prueba de errores
       /* for (int i = 0; i < listPres.size(); i++) {
            if (prodID.equals(listPres.get(i).getProdID())) {
                listPres.set(i,new MedicalPrescriptionLine(prodID, tg));
            }
        }*/

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalPrescription that = (MedicalPrescription) o;
        return prescCode == that.prescCode &&
                Objects.equals(prescDate, that.prescDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(hcID, that.hcID) &&
                Objects.equals(eSign, that.eSign) &&
                Objects.equals(listPres, that.listPres);
    }

    @Override
    public String toString() {
        return "MedicalPrescription{" +
                ", listPres=" + listPres +
                "\n mdl= "+ mdl +
                '}';
    }
}