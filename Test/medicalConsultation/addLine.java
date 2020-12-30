package medicalConsultation;


import data.ProductID;
import medicalconsultation.*;
import medicalconsultation.exceptions.IncorrectTakingGuidelinesException;
import medicalconsultation.exceptions.ProductNotInPrescription;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class addLine {
    @Test
    public void addLine() throws IncorrectTakingGuidelinesException {

        ProductID productID = new ProductID("5");
        TakingGuideline tg = new TakingGuideline(545, 656, "sadjsakdkjasbdkjbkasbkabskhdbkasjdvsjahd", 55, 6, FqUnit.HOUR);

        MedicalPrescription ob = new MedicalPrescription(1);

        ob.addLine(productID, instrucGet(tg));
       // System.out.println(ob.toString());

    }
    @Test
    public void modifyLine() throws IncorrectTakingGuidelinesException, ProductNotInPrescription {

        ProductID productID = new ProductID("5");

        TakingGuideline tg = new TakingGuideline(545, 656, "sadjsakdkjasbdkjbkasbkabskhdbkasjdvsjahd", 55, 6, FqUnit.HOUR);
        MedicalPrescription modify = new MedicalPrescription(1);
        modify.addLine(productID, instrucGet(tg));

        TakingGuideline tg2 = new TakingGuideline(5, 6, "vsjahd", 5, 2, FqUnit.DAY);
        modify.modifyLine(productID, instrucGet(tg2));
      //  System.out.println(modify.toString());

    }

    @Test
    public void removeLine() throws ProductNotInPrescription, IncorrectTakingGuidelinesException {

        ProductID productID = new ProductID("88");
        ProductID productID1 = new ProductID("4");
        ProductID productID2 = new ProductID("1");
        ProductID productID3 = new ProductID("5");
        ProductID productID4 = new ProductID("9");

        TakingGuideline tg = new TakingGuideline(1, 46, "vsjahd", 45, 2, FqUnit.DAY);
        TakingGuideline tg1 = new TakingGuideline(2, 26, "4565sjahd", 15, 1, FqUnit.DAY);
        TakingGuideline tg2 = new TakingGuideline(3, 66, "avdjashvdxsdafsdv", 65, 2, FqUnit.WEEK);
        TakingGuideline tg3 = new TakingGuideline(4, 96, "aafsdffd", 525, 2, FqUnit.MONTH);
        TakingGuideline tg4 = new TakingGuideline(5, 56, "vsjahd", 25, 2, FqUnit.HOUR);

        MedicalPrescription modify = new MedicalPrescription(1);
        modify.addLine(productID, instrucGet(tg));
        modify.addLine(productID1, instrucGet(tg1));
        modify.addLine(productID2, instrucGet(tg2));
        modify.addLine(productID3, instrucGet(tg3));
        modify.addLine(productID4, instrucGet(tg4));
        modify.removeLine(productID2);
        System.out.println(modify.toString());

    }

    public String [] instrucGet (TakingGuideline tg) {
        List<TakingGuideline> listTaking = new ArrayList<>();
        listTaking.add(tg);
        String[] instruc = new String[listTaking.size()];
        for (int i = 0; i < listTaking.size(); i++) {
            //System.out.println(listTaking.get(i));
            instruc[i] = String.valueOf(listTaking.get(i));
        }

        return instruc;
    }
}