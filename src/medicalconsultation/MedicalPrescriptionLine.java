package medicalconsultation;
import data.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MedicalPrescriptionLine {
    ProductID prodID;
    TakingGuideline instruc;

    public MedicalPrescriptionLine (ProductID prodID, TakingGuideline instruc) {
        this.prodID=prodID;
        this.instruc=instruc;
    }


    public ProductID getProdID() {
        return prodID;
    }

    public TakingGuideline getInstruc() {
        return instruc;
    }

    public void setInstruc(TakingGuideline instruc) {
        this.instruc = instruc;
    }

    @Override
    public String toString() {
        return "MedicalPrescriptionLine{" +
                "prodID=" + prodID +
                ", instruc=" + instruc +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalPrescriptionLine line = (MedicalPrescriptionLine) o;
        return prodID.equals(line.prodID) &&
                instruc.equals(line.instruc);
    }

}
