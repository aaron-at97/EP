package services;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import data.exceptions.eSignatureException;
import medicalconsultation.MedicalPrescription;
import medicalconsultation.ProductSpecification;
import medicalconsultation.exceptions.*;
import services.exceptions.HealthCardException;
import java.net.ConnectException;
import java.util.*;

public class HealthNationalServiceImple implements HealthNationalService{

    private Map<HealthCardID, MedicalPrescription> prescriptionsDatabase = new HashMap<>();
    private List<ProductSpecification> listProd = new ArrayList<>();
    private HealthCardID id;
    private int prescCode=0;
    private boolean flag;

    public HealthNationalServiceImple () throws Exception {
        CatalogoProdTest();
    }

    @Override
    public MedicalPrescription getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException {
        try {
            if (!prescriptionsDatabase.containsKey(hcID)) {
                id = null;
                throw new HealthCardException("");
            }
            if (prescriptionsDatabase.get(hcID) == null) {
                id = null;
                throw new NotValidePrescriptionException("");
            }
            id = hcID;
            return prescriptionsDatabase.get(hcID);
        } catch (Exception e) {
            id = null;
            throw new ConnectException();
        }
    }

    @Override
    public List<ProductSpecification> getProductsByKW(String keyWord) throws AnyKeyWordMedicineException, ConnectException {
        try {
            flag = false;
            for (int i = 0; i < listProd.size(); i++) {
                if (keyWord.equals(listProd.get(i).getNombre())) {
                    flag = true;
                    return listProd;
                }
            }
            if (flag=true) {
                throw new AnyKeyWordMedicineException("");
            }
        } catch (Exception e) {
            listProd = null;
            throw new ConnectException();
        }
        return null;
    }

    @Override
    public ProductSpecification getProductSpecific(int opt) throws AnyMedicineSearchException, ConnectException {
        try {
            if (listProd.isEmpty()) {
                throw new AnyMedicineSearchException("");
            }
            return listProd.get(opt);
        } catch (Exception e) {
            throw new ConnectException();
        }
    }

    @Override
    public MedicalPrescription sendePrescription(MedicalPrescription ePresc) throws ConnectException, NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription {
        try {
            if (ePresc.geteSign()!=null) {
                throw new eSignatureException("No dispone de Firma Digital");
            }

            if (ePresc == null) {
                throw new NotValidePrescription("La prescripcion no es valida");
            }

            if (ePresc.getPrescDate() == "" || ePresc.getEndDate() == null) {
                throw new NotCompletedMedicalPrescription("No inicializado Medical Prescription");
            }

            if(ePresc.getPrescCode()!=0) {
                prescCode=ePresc.getPrescCode();
            }
            else {
                prescCode++;
            }
            ePresc.setPrescCode(prescCode);
            prescriptionsDatabase.put(id, ePresc);
            return prescriptionsDatabase.get(id);
        } catch (Exception e) {
            throw new ConnectException();
        }
    }

    private void CatalogoProdTest() throws Exception{

        DigitalSignature sign = new DigitalSignature("ATT".getBytes());
        String[] instruc = new String[] {"AFTERBREAKFAST","","ghcgcgvh","","8"};
        ProductID prod = new ProductID("111111111111");


        HealthCardID hc = new HealthCardID("ARTO1234567890");
        MedicalPrescription mp = new MedicalPrescription(1234, "", new Date(2022-1900, Calendar.DECEMBER, 29), hc, sign);
        mp.addLine(prod, instruc);
        prescriptionsDatabase.put(hc, mp);

        ProductID prod1 = new ProductID("111111111111");
        ProductID prod2 = new ProductID("222222222222");
        ProductSpecification paracetamol = new ProductSpecification("paracetamol","150g", prod1, 5);
        ProductSpecification aspirina = new ProductSpecification("paracetamol","250g", prod2, 8);
        listProd.add(paracetamol);
        listProd.add(aspirina);

    }
}
