package medicalConsultation;


import data.ProductID;
import medicalconsultation.*;
import medicalconsultation.exceptions.IncorrectTakingGuidelinesException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class addLine {
    @Test
    public void addLine() throws IncorrectTakingGuidelinesException {
        ProductID productID = new ProductID("5");
        String[] instruc = new String[0];

        List<TakingGuideline> listTaking = null;
        TakingGuideline tg = new TakingGuideline(545, 656, "sadjsakdkjasbdkjbkasbkabskhdbkasjdvsjahd", 55, 6, FqUnit.HOUR);

        MedicalPrescription ob = new MedicalPrescription(1, tg);
        ob.addLine(productID, instruc);
        MedicalPrescriptionLine ob1 = new MedicalPrescriptionLine()
        System.out.println(ob.toString());

    }
}