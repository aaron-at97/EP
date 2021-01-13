package data;

import static java.lang.Character.*;

final public class HealthCardID {
    private final String personalID;

    public HealthCardID(String code) {
        this.personalID = code;
    }
    public String getPersonalID() throws Exception{
        if(!CodeCardID()){
            throw new Exception("The Health Card ID is not valid. \n");
        }
        return personalID;
    }

    public Boolean CodeCardID(){

        if (personalID == null)
            return false;

        char [] codeArray = personalID.toCharArray();
        if (personalID.length() != 14)
            return false;

        for (int i = 0; i < 4; i++){
            if (!isAlphabetic(codeArray[i]))
                return false;
        }
        for (int j = 4; j < 14; j++ ){
            if(!isDigit(codeArray[j]))
                return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        HealthCardID hcardID= (HealthCardID) o;
        return personalID.equals(hcardID.personalID);
    }
    @Override
    public int hashCode() {
        return personalID.hashCode();
    }

    @Override public String toString() {
        return "HealthCardID{" +"personal code='" + personalID+ '\'' +'}';
    }
}