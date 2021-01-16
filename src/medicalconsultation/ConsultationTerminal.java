package medicalconsultation;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import services.HealthNationalService;
import medicalconsultation.exceptions.*;
import data.exceptions.*;
import services.ScheduledVisitAgenda;
import services.exceptions.*;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.*;
public class ConsultationTerminal {

    private HealthCardID hc;
    private ScheduledVisitAgenda hcr;
    private HealthNationalService sns;
    private MedicalPrescription presc;
    private SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
    private List<ProductSpecification> listProduct;
    private ProductID productID;
    private final Date actual = new Date();
    private ProductSpecification producEsp;
    private DigitalSignature eSign;

    public ConsultationTerminal(DigitalSignature eSign)  {
        this.eSign = eSign;
        this.listProduct = new ArrayList<>();
        this.presc = new MedicalPrescription();

    }

    public ConsultationTerminal(HealthNationalService sns,ScheduledVisitAgenda hcr)  {
        this.sns = sns;
        this.hcr = hcr;
        this.listProduct = new ArrayList<>();
    }

    public void initRevision() throws HealthCardException, NotValidePrescriptionException, ConnectException {
        try {
            if (hcr.getHealthCardID()==null) {
                throw new HealthCardException("El paciente no tiene visita o no se encuentra registrado en la agenda de visitas concertadas");
            }
            hc = hcr.getHealthCardID();
            presc = sns.getePrescription(hc);
            if(presc == null) {
                throw new NotValidePrescriptionException("El paciente no tiene prescripcion");
            }
        }
        catch (ConnectException ce){
            throw new ConnectException(""+ce);
        }
    }

    public void initPrescriptionEdition() throws AnyCurrentPrescriptionException, NotFinishedTreatmentException {
        if(presc == null) {
            throw new AnyCurrentPrescriptionException("El paciente no tiene prescripcion");
        }
        if (presc.getEndDate().after(actual)) {
            throw new NotFinishedTreatmentException("Tratamiento no finalizado");
        }

    }

    public void searchForProducts(String keyWord) throws AnyKeyWordMedicineException, ConnectException {
        try {
            listProduct = sns.getProductsByKW(keyWord);
            if (listProduct==null) {
                throw new AnyKeyWordMedicineException(" Producto no esta en la descripcion, Prueba con otro ");
            }
        }
        catch (ConnectException ce){
            throw new ConnectException(""+ce);
        }
    }

    public void selectProduct(int option) throws AnyMedicineSearchException, ConnectException {
        try {
            if (sns.getProductSpecific(option)!=null) {
                producEsp = sns.getProductSpecific(option);
            } else {
                throw new AnyMedicineSearchException("No se a realizado una busqueda previa");
            }
            productID = producEsp.getProductCode();
        }
        catch (ConnectException ce){
            throw new ConnectException();
        }
    }

    public void enterMedicineGuidelines(String[] instruc) throws AnySelectedMedicineException, IncorrectTakingGuidelinesException {
        if (productID == null) {
            throw new AnySelectedMedicineException(" No se a realizado la seleccion anterior");
        }
        // Ya esta implementado IncorrectTakingGuideLinesException
        presc.addLine(productID, instruc);
    }

    public void enterTreatmentEndingDate(Date date) throws IncorrectEndingDateException{
        if(date.before(actual)) {
            throw new IncorrectEndingDateException(" La data que has introducido ya a expirado, Introduce una data posterior a la de hoy. ");
        }
        presc.setEndDate(date);
        presc.setPrescDate(sdformat.format(actual));
    }

    public void sendePrescription() throws ConnectException, NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription {
        try {

            if (eSign!=null) {
                presc.seteSign(eSign);
            }

            if (presc == null) {
                throw new NotValidePrescription("La prescripcion no es valida");
            }

            if (presc.geteSign()==null) {
                throw new eSignatureException("No dispone de Firma Digital");
            }

            if (presc.getPrescDate() == "" || presc.getEndDate() == null) {
                throw new NotCompletedMedicalPrescription("No inicializado Medical Prescription");
            }
            presc = sns.sendePrescription(presc);

        }
        catch (ConnectException ce){
            throw new ConnectException();
        }

    }

    public void printePresc() throws printingException {
        if (presc == null) {
            throw new printingException(" Error al printar, MedicalPrescription vacio");
        }
        System.out.println(presc);
    }

    public HealthCardID getHc() {
        return hc;
    }

    public void setHcr(ScheduledVisitAgenda hcr) {
        this.hcr = hcr;
    }

    public void setSns(HealthNationalService sns) {
        this.sns = sns;
    }

    public MedicalPrescription getPresc() {
        return presc;
    }

    public void setPresc(MedicalPrescription presc) {
        this.presc = presc;
    }

    public List<ProductSpecification> getListProduct() {
        return listProduct;
    }

    public ProductID getProductID() {
        return productID;
    }

    public void setProductID(ProductID productID) {
        this.productID = productID;
    }


}