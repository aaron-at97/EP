package medicalconsultation;
import data.*;

import java.util.Arrays;

public class MedicalPrescriptionLine {
    ProductID prodID;
    String[] instruc;
    public MedicalPrescriptionLine (ProductID prodID, String[] instruc) {
        this.prodID=prodID;
        this.instruc=instruc;
    }

    public ProductID getProdID() {
        return prodID;
    }

    public void setProdID(ProductID prodID) {
        this.prodID = prodID;
    }

    public String[] getInstruc() {
        return instruc;
    }

    public void setInstruc(String[] instruc) {
        this.instruc = instruc;
    }

    @Override
    public String toString() {
        return "MedicalPrescriptionLine{" +
                "prodID=" + prodID +
                ", instruc=" + Arrays.toString(instruc) +
                '}';
    }
}
