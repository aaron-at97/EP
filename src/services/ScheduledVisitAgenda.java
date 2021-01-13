package services;
import data.HealthCardID;
import services.exceptions.HealthCardException;
public interface ScheduledVisitAgenda {

    HealthCardID getHealthCardID() throws HealthCardException;
}
