package medicalconsultation.exceptions;

public class NotValidePrescription extends Exception{
    public NotValidePrescription(String msg) {
        super(msg);
    }
}