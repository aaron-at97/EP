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

    private List<MedicalPrescription> medP = new ArrayList<>();
    private List<ProductSpecification> listProd = new ArrayList<>();
    private HealthCardID id;
    private int prescCode=0;
    private MedicalPrescription md = new MedicalPrescription();
    private boolean flag;

    public HealthNationalServiceImple () throws Exception {
        CatalogoProdTest();
    }

    @Override
    public MedicalPrescription getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException {
        try {
            if (hcID==null) {
                throw new HealthCardException(" No has pasado tarjeta para poder buscar la prescripcion ");
            }
            return buscarTarjeta(hcID);

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
                throw new AnyKeyWordMedicineException("Palabra clave no buscada");
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
            if (!listProd.isEmpty()) {
                return listProd.get(opt);

            } else {
                throw new AnyMedicineSearchException(" No se a realizado una busqueda");
            }

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
            medP.add(ePresc);
            return buscarTarjeta(id);
        } catch (Exception e) {
            throw new ConnectException();
        }
    }

    private MedicalPrescription buscarTarjeta(HealthCardID hcID) throws NotValidePrescriptionException {
        flag = false;
        for (int i = 0; i < listProd.size(); i++) {
            if (hcID.equals(medP.get(i).getHcID())) {
                flag = true;
                id = hcID;
                return medP.get(i);
            }
        }

        if (flag=true) {
            throw new NotValidePrescriptionException("Tarjeta sanitaria incorrecta");
        }
        return null;
    }
    private void CatalogoProdTest() throws Exception{

        DigitalSignature sign = new DigitalSignature("ATT".getBytes());
        String[] instruc = new String[] {"AFTERBREAKFAST","","ghcgcgvh","","8"};
        ProductID prod = new ProductID("111111111111");
        HealthCardID hc = new HealthCardID("ARTO1234567890");
        MedicalPrescription mp = new MedicalPrescription(1234, "", new Date(2022-1900, Calendar.DECEMBER, 29), hc, sign);
        mp.addLine(prod, instruc);
        medP.add(mp);
        ProductID prod2 = new ProductID("222222222222");
        ProductSpecification paracetamol = new ProductSpecification("paracetamol","150g", prod, 5);
        ProductSpecification aspirina = new ProductSpecification("paracetamol","250g", prod2, 8);
        listProd.add(paracetamol);
        listProd.add(aspirina);

    }
}
