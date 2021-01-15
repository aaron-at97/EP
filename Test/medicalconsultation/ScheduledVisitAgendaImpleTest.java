package medicalconsultation;

import data.HealthCardID;
import org.junit.jupiter.api.Test;
import services.ScheduledVisitAgendaImple;
import services.exceptions.HealthCardException;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ScheduledVisitAgendaImpleTest {
    @Test
    void getHealthCardID() throws Exception{

        ScheduledVisitAgendaImple agenda = new ScheduledVisitAgendaImple(new Date(2021-1900, Calendar.MARCH, 8,17,30));
        assertEquals(agenda.getHealthCardID(), new HealthCardID("LLSE1111111111"));
        assertNotEquals(agenda.getHealthCardID(), new HealthCardID("CASA1236547893"));
        ScheduledVisitAgendaImple agenda2 = new ScheduledVisitAgendaImple(new Date(2022-1900, Calendar.FEBRUARY, 7,18,22));
        assertThrows(HealthCardException.class, () -> {agenda2.getHealthCardID();});
    }


}
