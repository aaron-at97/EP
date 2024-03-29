package medicalconsultation;


public class Posology{  // A class that represents the posology of amedicine
    private float dose;
    private float freq;
    private FqUnit freqUnit;

    public Posology(float d, float f, FqUnit u) {
        this.dose=d;
        this.freq=f;
        this.freqUnit=u;
    }
    public Posology() {
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


    @Override
    public String toString() {
        return "Posology{" +
                "dose=" + dose +
                ", freq=" + freq +
                ", freqUnit=" + freqUnit +
                '}';
    }
}