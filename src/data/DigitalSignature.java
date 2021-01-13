package data;

public class DigitalSignature {
    private final Boolean firma;

    public DigitalSignature (Boolean firm){
        this.firma = firm;
    }

    public Boolean getSignature() {
        return firma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DigitalSignature signature = (DigitalSignature) o;
        return firma.equals(signature.firma);

    }

    @Override
    public int hashCode() {
        return firma.hashCode();
    }

    @Override
    public String toString() {
        return "DigitalSignature Signature{" + "Signature: '" + firma.toString() + '\'' + '}';
    }
}

