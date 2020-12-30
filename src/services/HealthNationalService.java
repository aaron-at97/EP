package services;
import medicalconsultation.ProductSpecification;
import medicalconsultation.MedicalPrescription;
import data.HealthCardID;
import java.net.ConnectException;
import java.util.*;
import services.exceptions.*;
import data.exceptions.*;
import medicalconsultation.exceptions.*;
 /**
* External service for managingand storing ePrescriptions from population
*//*
 public interface HealthNationalService {
    MedicalPrescription  getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException;
    List<ProductSpecification> getProductsByKW(String keyWord) throws AnyKeyWordMedicineException, ConnectException;
    ProductSpecification getProductSpecific(int opt) throws AnyMedicineSearchException, ConnectException;
    MedicalPrescription sendePrescription(MedicalPrescription ePresc) throws ConnectException, NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription;
}*/