package Service;

import data.DigitalSignature;
import data.HealthCardID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import services.ScheduledVisitAgendaImple;
import services.exceptions.HealthCardException;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ScheduledVisitAgendaImpleTest {

    static ScheduledVisitAgendaImple agenda;
    static ScheduledVisitAgendaImple agenda2;

    @BeforeAll
    static void init(){
        agenda = new ScheduledVisitAgendaImple(new Date(2021-1900, Calendar.MARCH, 8,17,30));
        agenda2 = new ScheduledVisitAgendaImple(new Date(2022-1900, Calendar.FEBRUARY, 7,18,22));
    }
    @Test
    void getHealthCardID() throws Exception{
        assertEquals(agenda.getHealthCardID(), new HealthCardID("LLSE1111111111"));
        assertNotEquals(agenda.getHealthCardID(), new HealthCardID("CASA1236547893"));

        assertThrows(HealthCardException.class, () -> {agenda2.getHealthCardID();});
    }


}
