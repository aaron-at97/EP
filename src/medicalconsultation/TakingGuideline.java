package medicalconsultation;

import java.util.Objects;

public class TakingGuideline  {  // Represents the taking guidelines of a medicine

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TakingGuideline that = (TakingGuideline) o;
        return Float.compare(that.duration, duration) == 0 &&
                dayMoment == that.dayMoment &&
                instructions.equals(that.instructions) &&
                posology.equals(that.posology);
    }



    public dayMoment getDayMoment() {
        return dayMoment;
    }

    public void setDayMoment(dayMoment dayMoment) {
        this.dayMoment = dayMoment;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Posology getPosology() {
        return posology;
    }

    public void setPosology(Posology posology) {
        this.posology = posology;
    }
}