package medicalconsultation;

import java.util.Objects;

public class TakingGuideline  {

    private dayMoment dayMoment;
    private float duration;
    private String instructions;
    private Posology posology;

    public TakingGuideline(dayMoment dM, float du, String i, float d, float f, FqUnit u) {
        this.dayMoment=dM;
        this.duration=du;
        this.instructions=i;
        this.posology = new Posology(d, f, u);
    }

    public TakingGuideline() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TakingGuideline that = (TakingGuideline) o;
        return Float.compare(that.duration, duration) == 0 &&
                dayMoment == that.dayMoment &&
                Objects.equals(instructions, that.instructions) &&
                Objects.equals(posology, that.posology);
    }

    @Override
    public String toString() {
        return "TakingGuideline{" +
                "dayMoment=" + dayMoment +
                ", duration=" + duration +
                ", instructions='" + instructions + '\'' +
                ", posology=" + posology +
                '}';
    }
}