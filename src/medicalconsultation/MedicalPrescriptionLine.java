package medicalconsultation;
import data.*;
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
}
