package services;



import data.HealthCardID;
import services.exceptions.HealthCardException;
import java.util.*;

public class ScheduledVisitAgendaImple implements ScheduledVisitAgenda {

    private Map<Date, HealthCardID> listCardID = new HashMap<>();
    private Date visita;

    public ScheduledVisitAgendaImple(Date visita) {
        this.visita=visita;
    }

    @Override
    public HealthCardID getHealthCardID() throws HealthCardException {

        listCardID.put(new Date(2021-1900, Calendar.APRIL, 6, 17,30),new HealthCardID("ARTO1234567891"));
        listCardID.put(new Date(2021-1900, Calendar.AUGUST, 7,17,30),new HealthCardID("BELE1987654321"));
        listCardID.put(new Date(2021-1900, Calendar.MARCH, 8,17,30),new HealthCardID("LLSE1111111111"));
        listCardID.put(new Date(2021-1900, Calendar.NOVEMBER, 10,17,30),new HealthCardID("BATO7894561230"));

        for (int i=0; i <listCardID.size(); i++) {
            if (listCardID.containsKey(visita)) {
                return listCardID.get(visita);
            }
        }

        throw new HealthCardException(" Health Card no esta ");
    }

}
