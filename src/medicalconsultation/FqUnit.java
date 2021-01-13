package medicalconsultation;

public enum FqUnit {
    HOUR, DAY, WEEK, MONTH;

    public static FqUnit getFqUnit(String u){
        FqUnit event = null;
        switch (u) {
            case "HOUR":
                event=HOUR;
                break;
            case "DAY":
                event=DAY;
                break;
            case "WEEK":
                event=WEEK;
                break;
            case "MONTH":
                event=MONTH;
                break;
            default:
                event=null;
                break;
        }

        return event;
    }

}
