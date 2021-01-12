package medicalconsultation;

import java.util.Objects;

public class Posology{  // A class that represents the posology of amedicine
    private float dose;
    private float freq;
    private FqUnit freqUnit;
    public Posology(float d, float f, FqUnit u) {
        this.dose=d;
        this.freq=f;
        this.freqUnit=u;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Posology posology = (Posology) o;
        return Float.compare(posology.dose, dose) == 0 &&
                Float.compare(posology.freq, freq) == 0 &&
                freqUnit == posology.freqUnit;
    }

    public float getDose() {
        return dose;
    }

    public void setDose(float dose) {
        this.dose = dose;
    }

    public float getFreq() {
        return freq;
    }

    public void setFreq(float freq) {
        this.freq = freq;
    }

    public FqUnit getFreqUnit() {
        return freqUnit;
    }

    public void setFreqUnit(FqUnit freqUnit) {
        this.freqUnit = freqUnit;
    }

}