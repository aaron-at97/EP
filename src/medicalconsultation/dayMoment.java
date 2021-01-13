package medicalconsultation;

public enum dayMoment {
    BEFOREBREAKFAST, DURINGBREAKFAST, AFTERBREAKFAST, BEFORELUNCH,
    DURINGLUNCH,AFTERLUNCH, BEFOREDINNER, DURINGDINNER, AFTERDINNER,
    BEFOEMEALS,DURINGMEALS, AFTERMEALS;
    public static dayMoment getdayMoment(String d){
        dayMoment tiempo = null;
        switch (d) {
            case "BEFOREBREAKFAST":
                tiempo=BEFOREBREAKFAST;
                break;
            case "DURINGBREAKFAST":
                tiempo=DURINGBREAKFAST;
                break;
            case "AFTERBREAKFAST":
                tiempo=AFTERBREAKFAST;
                break;
            case "BEFORELUNCH":
                tiempo=BEFORELUNCH;
                break;
            case "DURINGLUNCH":
                tiempo=DURINGLUNCH;
                break;
            case "AFTERLUNCH":
                tiempo=AFTERLUNCH;
                break;
            case "BEFOREDINNER":
                tiempo=BEFOREDINNER;
                break;
            case "DURINGDINNER":
                tiempo=DURINGDINNER;
                break;
            case "AFTERDINNER":
                tiempo=AFTERDINNER;
                break;
            case "BEFOEMEALS":
                tiempo=BEFOEMEALS;
                break;
            case "DURINGMEALS":
                tiempo=DURINGMEALS;
                break;
            case "AFTERMEALS":
                tiempo=AFTERMEALS;
                break;
            default :
                tiempo=null;
                break;
        }
        return tiempo;
    }
}