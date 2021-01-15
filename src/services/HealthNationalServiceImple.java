package services;

import data.HealthCardID;
import data.exceptions.eSignatureException;
import medicalconsultation.MedicalPrescription;
import medicalconsultation.ProductSpecification;
import medicalconsultation.exceptions.*;
import services.exceptions.HealthCardException;

import java.net.ConnectException;
import java.util.List;

public class HealthNationalServiceImple implements HealthNationalService{
    @Override
    public MedicalPrescription getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException {
        return null;
    }

    @Override
    public List<ProductSpecification> getProductsByKW(String keyWord) throws AnyKeyWordMedicineException, ConnectException {
        return null;
    }

    @Override
    public ProductSpecification getProductSpecific(int opt) throws AnyMedicineSearchException, ConnectException {
        return null;
    }

    @Override
    public MedicalPrescription sendePrescription(MedicalPrescription ePresc) throws ConnectException, NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription {
        return null;
    }
}
